package com.example.panalsuite.DTO;

import com.example.panalsuite.model.UserRole;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserResponseDTO {
    @Schema(description = "ID", example = "1")
    private Long userId;
    
    @Schema (description = "Nombre", example = "Diego")
    private String name;

    @Schema (description = "Correo", example = "Acevedo@Panal.cl")
    private String email;

    @Schema (description = "Departamento", example = "Call Center")
    private String department;

    @Schema (description = "Rol", example = "REQUESTER")
    private UserRole role;
}
