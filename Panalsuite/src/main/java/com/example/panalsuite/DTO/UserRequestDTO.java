package com.example.panalsuite.DTO;

import com.example.panalsuite.model.UserRole;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {
    @NotBlank(message = "El nombre es obligatorio")
    @Schema (description = "Nombre", example = "Diego")
    private String name;

    @NotBlank(message = "El correo es obligatorio")
    @Schema (description = "Correo", example = "Acevedo@panal.cl")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Schema (description = "Contraseña", example = "6767")
    private String password;
    
    @Schema (description = "Departamente", example = "Call Center")
    private String department;

    @Schema (description = "Rol", example = "REQUESTER")
    private UserRole role;
}
