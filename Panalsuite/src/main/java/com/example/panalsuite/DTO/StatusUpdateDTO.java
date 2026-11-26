package com.example.panalsuite.DTO;

import com.example.panalsuite.model.TicketStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusUpdateDTO {
    @NotNull(message = "El estado es obligatorio")
    @Schema (description = "Estado", example = "CLOSED")
    private TicketStatus status;
}
