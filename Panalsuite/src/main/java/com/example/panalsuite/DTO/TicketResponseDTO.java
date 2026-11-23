package com.example.panalsuite.DTO;

import com.example.panalsuite.model.TicketCategory;
import com.example.panalsuite.model.TicketPriority;
import com.example.panalsuite.model.TicketStatus;
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
    private TicketCategory category;
    private TicketPriority priority;
    private TicketStatus status;
    private String location;
    private Long requesterId;
    private Long assignedId;
    private List<CommentResponseDTO> comments;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
