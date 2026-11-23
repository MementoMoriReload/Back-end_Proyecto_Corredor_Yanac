package com.example.panalsuite.controller;

import com.example.panalsuite.DTO.LoginRequestDTO;
import com.example.panalsuite.DTO.UserResponseDTO;
import com.example.panalsuite.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService service;

    public AuthController(UserService service) {

        this.service = service;
    }

    @PostMapping("/login")
    public UserResponseDTO login(@Valid @RequestBody LoginRequestDTO dto){
        return service.login(dto);
    }
}
