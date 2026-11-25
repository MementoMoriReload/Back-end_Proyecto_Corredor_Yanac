package com.example.panalsuite.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@AllArgsConstructor
public class CommentResponseDTO {
    @Schema (description = "ID", example = "1")
    private Long commentId;

    @Schema (description = "Autor", example = "Victor Castro")
    private String author;

    @Schema (description = "Contenido del comentario", example = "Faltan enchufes")
    private String text;

    @Schema (description = "Tiempo de publicación")
    private LocalDateTime timestamp;
}
