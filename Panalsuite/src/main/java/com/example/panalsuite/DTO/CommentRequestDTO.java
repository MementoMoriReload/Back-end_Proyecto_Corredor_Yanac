package com.example.panalsuite.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDTO {
    @NotBlank(message = "El autor es obligatorio")
    @Schema (description = "Autor", example = "Victor Castro")
    private String author;

    @NotBlank(message = "El comentario no puede estar vacio")
    @Schema (description = "Contenido del comentario", example = "Faltan enchufes")
    private String text;
}
