package com.example.panalsuite.DTO;

import com.example.panalsuite.model.UserRole;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleUpdateDTO {
    @NotNull(message="El rol es obligatorio")
    private UserRole role;
}
