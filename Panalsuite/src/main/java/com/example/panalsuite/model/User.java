package com.example.panalsuite.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID", example = "1")
    private Long userId;

    @Schema (description = "Nombre", example = "Diego")
    private String name;

    @Column(unique = true, nullable = false)
    @Schema (description = "Correo", example = "Acevedo@Panal.cl")
    private String email;

    @Column(nullable = false)
    @Schema(description = "Contraseña", example = "1234")
    private String password;

    @Schema (description = "Departamento", example = "Call Center")
    private String department;

    @Enumerated(EnumType.STRING)
    @Schema (description = "Rol (por defecto REQUESTER)", example = "REQUESTER")
    private UserRole role;
}
