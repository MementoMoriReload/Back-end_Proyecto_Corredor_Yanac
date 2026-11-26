package com.example.panalsuite.DTO;

import com.example.panalsuite.model.TicketCategory;
import com.example.panalsuite.model.TicketPriority;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketRequestDTO {
    @NotBlank(message = "El titulo es obligatorio")
    @Schema(description = "Titulo", example = "Telefono sin conectar")
    private String title;

    @NotBlank(message = "La descripcion es obligatoria")
    @Schema (description = "Descripcion", example = "Telefono carece de número para hacer y recibir llamadas")
    private String description;

    @NotBlank(message = "La ubicación es obligatoria")
    @Schema (description = "Ubicación", example = "Piso 1")
    private String location;

    @NotNull(message= "La categoria es obligatoria")
    @Schema (description = "Categoria", example = "INTERNAL_SUPPORT")
    private TicketCategory category;

    @Schema (description = "Prioridad (por defecto MEDIUM)", example = "MEDIUM")
    private TicketPriority priority;

    @NotNull(message = "El solicitante es obligatorio")
    @Schema (description = "ID del solicitante del Ticket", example = "1" )
    private Long requesterId;

    @Schema (description = "ID del resolutor asignado al Ticket", example = "2")
    private Long assignedId;
}
