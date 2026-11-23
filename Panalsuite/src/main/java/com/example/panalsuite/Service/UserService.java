package com.example.panalsuite.Service;

import com.example.panalsuite.DTO.LoginRequestDTO;
import com.example.panalsuite.DTO.UserRequestDTO;
import com.example.panalsuite.DTO.UserResponseDTO;
import com.example.panalsuite.Exception.ApiException;
import com.example.panalsuite.model.User;
import com.example.panalsuite.model.UserRole;
import com.example.panalsuite.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.regex.Pattern;

@Service
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repository, PasswordEncoder encoder) {

        this.repository = repository;
        this.encoder = encoder;
    }

    private static final Pattern NAME_PATTERN = Pattern.compile("^[\\p{L}]+(?:[ '-][\\p{L}]+)*$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@panal\\.cl$");

    public List<UserResponseDTO> getAllUsers() {
        return repository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public User find(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Usuario %d no encontrado", +id)));
    }

    public UserResponseDTO getUserById(long id) {
        return mapToResponseDTO(find(id));
    }

    public UserResponseDTO saveUser(UserRequestDTO dto) {
        String name = dto.getName() == null ? null : dto.getName().trim();
        String email = dto.getEmail();
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new RuntimeException("El correo electrónico debe pertenecer al dominio @panal.cl");
        }

        if (repository.findByEmail(email).isPresent()) {
            throw new RuntimeException("El correo electrónico ya está registrado");
        }

        if (name == null || !NAME_PATTERN.matcher(name).matches()) {
            throw new RuntimeException("El nombre solo puede contener letras");
        }
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setDepartment(dto.getDepartment());
        user.setRole(dto.getRole()
                != null ? dto.getRole(): UserRole.REQUESTER);

        return mapToResponseDTO(repository.save(user));
    }

    public UserResponseDTO login(LoginRequestDTO dto){
        User user = repository.findByEmail(dto.getEmail().trim())
                .orElseThrow(() -> ApiException.unauthorized("Correo o contraseña incorrectas"));
        if (!encoder.matches(dto.getPassword(), user.getPassword())){
            throw ApiException.unauthorized("Correo o contraseña incorrectas");
        }
        return mapToResponseDTO(user);
    }

    public UserResponseDTO updateRole(Long id, UserRole role){
        User user = find(id);
        user.setRole(role);
        return mapToResponseDTO(repository.save(user));
    }

    public void deleteUser(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException(String.format("Usuario %d no encontrado", +id));
        }
        repository.deleteById(id);
    }

    private UserResponseDTO mapToResponseDTO(User u) {
        return new UserResponseDTO(
                u.getUserId(),
                u.getName(),
                u.getEmail(),
                u.getDepartment(),
                u.getRole());
    }

}
