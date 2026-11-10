package com.example.panalsuite.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;
    private String title;
    private String description;
    private TicketCategory category;
    private TicketPriority priority;
    private TicketStatus status;
    private String location;
    @ManyToOne
    @JoinColumn(name="requesterId")
    private User requester;
    @ManyToOne
    @JoinColumn(name="assignedId")
    private User assigned;
    @OneToMany
    private List<TicketComment> comments = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
