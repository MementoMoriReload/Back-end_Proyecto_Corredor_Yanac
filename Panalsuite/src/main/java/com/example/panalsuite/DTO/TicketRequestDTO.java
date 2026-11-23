package com.example.panalsuite.DTO;

import com.example.panalsuite.model.TicketCategory;
import com.example.panalsuite.model.TicketPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketRequestDTO {
    @NotBlank(message = "El titulo es obligatorio")
    private String title;

    @NotBlank(message = "La descripcion es obligatoria")
    private String description;

    @NotBlank(message = "La ubicación es obligatoria")
    private String location;

    @NotNull(message= "La categoria es obligatoria")
    private TicketCategory category;

    private TicketPriority priority;

    @NotNull(message = "El solicitante es obligatorio")
    private Long requesterId;

    private Long assignedId;
}
