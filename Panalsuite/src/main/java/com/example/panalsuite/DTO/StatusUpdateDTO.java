package com.example.panalsuite.DTO;

import com.example.panalsuite.model.TicketStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusUpdateDTO {
    @NotNull(message = "El estado es obligatorio")
    private TicketStatus status;
}
