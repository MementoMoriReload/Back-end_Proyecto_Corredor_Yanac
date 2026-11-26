package com.example.panalsuite.DTO;

import com.example.panalsuite.model.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleUpdateDTO {
    @NotNull(message="El rol es obligatorio")
    @Schema (description = "Rol a cambiar", example = "RESOLVER")
    private UserRole role;
}
