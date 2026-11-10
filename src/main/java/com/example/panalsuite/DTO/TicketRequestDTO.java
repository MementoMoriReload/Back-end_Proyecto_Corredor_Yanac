package com.example.panalsuite.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketRequestDTO {
    private String title;
    private String description;
    private String location;
    private Long requesterId;
}
