package com.example.panalsuite.Service;

import com.example.panalsuite.DTO.TicketResponseDTO;
import com.example.panalsuite.DTO.UserRequestDTO;
import com.example.panalsuite.DTO.UserResponseDTO;
import com.example.panalsuite.model.User;
import com.example.panalsuite.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.regex.Pattern;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    private static final Pattern NAME_PATTERN = Pattern.compile("^[\\p{L}]+(?:[ '-][\\p{L}]+)*$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@panal\\.cl$");

    public List<UserResponseDTO> getAllUsers() {
        return repository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public UserResponseDTO getUserById(long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Usuario %d no encontrado", +id)));
        return mapToResponseDTO(user);
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
        user.setDepartment(dto.getDepartment());
        user.setRole(dto.getRole());

        User savedUser = repository.save(user);
        return mapToResponseDTO(savedUser);
    }

    public void deleteUser(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException(String.format("Usuario %d no encontrado", +id));
        }
        repository.deleteById(id);
    }

    private UserResponseDTO mapToResponseDTO(User user) {
        return new UserResponseDTO(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getRole());
    }

}
