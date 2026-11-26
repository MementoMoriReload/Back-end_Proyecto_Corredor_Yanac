package com.example.panalsuite.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema (description = "ID", example = "1")
    private Long ticketId;

    @Schema(description = "Titulo", example = "Telefono sin conectar")
    private String title;

    @Schema (description = "Descripcion", example = "Telefono carece de número para hacer y recibir llamadas")
    private String description;

    @Enumerated(EnumType.STRING)
    @Schema (description = "Categoria", example = "INTERNAL_SUPPORT")
    private TicketCategory category;

    @Enumerated(EnumType.STRING)
    @Schema (description = "Prioridad (por defecto MEDIUM)", example = "MEDIUM")
    private TicketPriority priority;

    @Enumerated(EnumType.STRING)
    @Schema (description = "Estado", example = "CLOSED")
    private TicketStatus status;

    @Schema (description = "Ubicación", example = "Piso 1")
    private String location;

    @ManyToOne(optional = false)
    @JoinColumn(name="requester_id")
    @Schema (description = "ID del solicitante del Ticket", example = "1" )
    private User requester;

    @ManyToOne
    @JoinColumn(name="assigned_id")
    @Schema (description = "ID del resolutor asignado al Ticket", example = "2")
    private User assigned;

    @OneToMany(mappedBy = "ticket",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @Schema (description = "Lista de comentarios")
    private List<TicketComment> comments = new ArrayList<>();

    @Schema (description = "Hora de Creación (se aplica automaticamente con la hora del sistema)")
    private LocalDateTime createdAt;

    @Schema (description = "Hora de Actualización (se aplica automaticamente con la hora del sistema)")
    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        createdAt = updatedAt = LocalDateTime.now();
        if (status == null) status = TicketStatus.OPEN;
        if (priority == null) priority = TicketPriority.MEDIUM;
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
