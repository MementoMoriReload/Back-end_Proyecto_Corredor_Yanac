package com.example.panalsuite.DTO;

import com.example.panalsuite.model.TicketCategory;
import com.example.panalsuite.model.TicketPriority;
import com.example.panalsuite.model.TicketStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class TicketResponseDTO {
    @Schema (description = "ID", example = "1")
    private Long ticketId;

    @Schema(description = "Titulo", example = "Telefono sin conectar")
    private String title;

    @Schema (description = "Descripcion", example = "Telefono carece de número para hacer y recibir llamadas")
    private String description;

    @Schema (description = "Categoria", example = "INTERNAL_SUPPORT")
    private TicketCategory category;

    @Schema (description = "Prioridad (por defecto MEDIUM)", example = "MEDIUM")
    private TicketPriority priority;

    @Schema(description = "Estado", example = "CLOSED")
    private TicketStatus status;

    @Schema (description = "Ubicación", example = "Piso 1")
    private String location;

    @Schema (description = "ID del solicitante del Ticket", example = "1" )
    private Long requesterId;

    @Schema (description = "ID del resolutor asignado al Ticket", example = "2")
    private Long assignedId;

    @Schema (description = "Lista de comentarios")
    private List<CommentResponseDTO> comments;

    @Schema (description = "Hora de Creación (se aplica automaticamente con la hora del sistema)")
    private LocalDateTime createdAt;

    @Schema (description = "Hora de Actualización (se aplica automaticamente con la hora del sistema)")
    private LocalDateTime updatedAt;


}
