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

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

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
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("El correo electrónico ya está registrado");
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
                user.getRole()
        );
    }

}
