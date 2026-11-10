package com.example.panalsuite.DTO;

import com.example.panalsuite.model.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {
    private String name;
    private String email;

    private String department;

    private UserRole role;
}
