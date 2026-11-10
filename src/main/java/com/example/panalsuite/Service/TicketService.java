package com.example.panalsuite.Service;

import com.example.panalsuite.DTO.CommentRequestDTO;
import com.example.panalsuite.DTO.CommentResponseDTO;
import com.example.panalsuite.DTO.TicketRequestDTO;
import com.example.panalsuite.DTO.TicketResponseDTO;
import com.example.panalsuite.DTO.UserResponseDTO;
import com.example.panalsuite.model.Ticket;
import com.example.panalsuite.model.TicketComment;
import com.example.panalsuite.model.User;
import com.example.panalsuite.repository.TicketRepository;
import com.example.panalsuite.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService {
    private final TicketRepository repository;
    private final UserRepository userRepository;

    public TicketService(TicketRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public List<TicketResponseDTO> getAllTickets() {
        return repository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public TicketResponseDTO getTicketById(Long id) {
        Ticket ticket = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Ticket %d no encontrado", +id)));
        return mapToResponseDTO(ticket);
    }

    public TicketResponseDTO saveTicket(TicketRequestDTO dto) {
        User requester = userRepository.findById(dto.getRequesterId())
                .orElseThrow(()-> new RuntimeException(String.format("Solicitante %d no encontrado",+ dto.getRequesterId())));

        User assigned = null;
        if (dto.getAssignedId() != null) {
            assigned = userRepository.findById(dto.getAssignedId())
                    .orElseThrow(() -> new RuntimeException(String.format("Técnico asignado %d no encontrado",+ dto.getAssignedId())));
        }

        Ticket ticket = new Ticket();
        ticket.setTitle(dto.getTitle());
        ticket.setDescription(dto.getDescription());
        ticket.setLocation(dto.getLocation());
        ticket.setRequester(requester);
        ticket.setAssigned(assigned);

        Ticket savedTicket = repository.save(ticket);
        return mapToResponseDTO(savedTicket);
    }

    public TicketResponseDTO addComment(long ticketId, CommentRequestDTO dto) {
        Ticket ticket = repository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException(String.format("Ticket %d no encontrado", ticketId)));

        TicketComment comment = new TicketComment();
        comment.setAuthor(dto.getAuthor());
        comment.setText(dto.getText());
        comment.setTicket(ticket);

        ticket.getComments().add(comment);

        Ticket saved = repository.save(ticket);

        return mapToResponseDTO(saved);
    }

    public void deleteTicket(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Ticket no encontrado con ID: " + id);
        }
        repository.deleteById(id);
    }

    private TicketResponseDTO mapToResponseDTO(Ticket ticket) {
        UserResponseDTO requesterDTO = new UserResponseDTO(
                ticket.getRequester().getUserId(),
                ticket.getRequester().getName(),
                ticket.getRequester().getEmail(),
                ticket.getRequester().getRole()
        );

        UserResponseDTO assignedDTO = null;
        if (ticket.getAssigned() != null) {
            assignedDTO = new UserResponseDTO(
                    ticket.getAssigned().getUserId(),
                    ticket.getAssigned().getName(),
                    ticket.getAssigned().getEmail(),
                    ticket.getAssigned().getRole()
            );
        }

        List<CommentResponseDTO> commentDTOs = ticket.getComments().stream()
                .map(comment -> new CommentResponseDTO(
                        comment.getCommentId(),
                        comment.getAuthor(),
                        comment.getText(),
                        comment.getTimestamp()
                ))
                .collect(Collectors.toList());

        return new TicketResponseDTO(
                ticket.getTicketId(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getLocation(),
                requesterDTO,
                assignedDTO,
                commentDTOs,
                ticket.getCreatedAt(),
                ticket.getUpdatedAt()
        );

    }
}
