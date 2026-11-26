package com.example.panalsuite.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignRequestDTO {
    @Schema (description = "ID del resolutor al que se asignara", example = "2")
    private Long assignedId;
}
