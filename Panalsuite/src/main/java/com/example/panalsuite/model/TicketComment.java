package com.example.panalsuite.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class TicketComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID", example = "1")
    private Long commentId;

    @Schema (description = "Autor", example = "Victor Castro")
    private String author;

    @Schema (description = "Contenido del comentario", example = "Faltan enchufes")
    private String text;

    @Schema (description = "Tiempo de publicación (se aplica automaticamente con la hora del sistema)")
    private LocalDateTime timestamp;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ticket_id")
    @Schema (description = "Ticket al que está vinculado")
    private Ticket ticket;

    @PrePersist
    void onCreate(){
        if (timestamp == null) timestamp = LocalDateTime.now();
    }
}
