package com.example.panalsuite.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDTO {
    @NotBlank(message = "El autor es obligatorio")
    private String author;

    @NotBlank(message = "El comentario no puede estar vacio")
    private String text;
}
