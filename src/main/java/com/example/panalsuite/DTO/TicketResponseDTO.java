package com.example.panalsuite.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class TicketResponseDTO {
    private Long ticketId;
    private String title;
    private String description;
    private String location;
    private UserResponseDTO requesterId;
    private UserResponseDTO assignedId;
    private List<CommentResponseDTO> comments;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
