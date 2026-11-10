package com.example.panalsuite.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class TicketResponseDTO {
    private Long ticketId;
    private String title;
    private String description;
    private String location;
    private Long requesterId;
    private Long assignedId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
