package com.example.panalsuite.Service;

import com.example.panalsuite.DTO.CommentRequestDTO;
import com.example.panalsuite.DTO.TicketRequestDTO;
import com.example.panalsuite.DTO.TicketResponseDTO;
import com.example.panalsuite.model.Ticket;
import com.example.panalsuite.model.TicketComment;
import com.example.panalsuite.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService {
    private final TicketRepository repository;

    public TicketService(TicketRepository repository) {
        this.repository = repository;
    }

    public List<TicketResponseDTO> getAllTickets() {
        return repository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<TicketResponseDTO> getTicketById(Long id) {
        return repository.findById(id)
                .map(this::mapToResponseDTO);
    }



    public TicketResponseDTO saveTicket(TicketRequestDTO dto) {

        Ticket ticket = new Ticket();
        ticket.setTitle(dto.getTitle());
        ticket.setDescription(dto.getDescription());
        ticket.setLocation(dto.getLocation());
        ticket.setRequesterId(dto.getRequesterId());

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
        repository.deleteById(id);
    }

    private TicketResponseDTO mapToResponseDTO(Ticket ticket) {
        return new TicketResponseDTO(
                ticket.getTicketId(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getLocation(),
                ticket.getRequesterId(),
                ticket.getAssignedId(),
                ticket.getCreatedAt(),
                ticket.getUpdatedAt()
        );

    }
}
