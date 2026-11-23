package com.example.panalsuite.Service;

import com.example.panalsuite.DTO.CommentRequestDTO;
import com.example.panalsuite.DTO.CommentResponseDTO;
import com.example.panalsuite.DTO.TicketRequestDTO;
import com.example.panalsuite.DTO.TicketResponseDTO;
import com.example.panalsuite.Exception.ApiException;
import com.example.panalsuite.model.*;
import com.example.panalsuite.repository.CommentRepository;
import com.example.panalsuite.repository.TicketRepository;
import com.example.panalsuite.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {
    private final TicketRepository repository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public TicketService(TicketRepository repository, UserRepository userRepository, CommentRepository commentRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    public List<TicketResponseDTO> getAllTickets() {
        return repository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    private Ticket findTicket(long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Ticket %d no encontrado", +id)));
    }

    private User findUser(long id, String label){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format(label + "%d no encontrado", +id)));

    }

    public TicketResponseDTO getTicketById(Long id) {
        return mapToResponseDTO(findTicket(id));
    }

    public TicketResponseDTO saveTicket(TicketRequestDTO dto) {
        User requester = findUser(dto.getRequesterId(), "Solicitante");

        User assigned = dto.getAssignedId() != null ?
                findUser(dto.getAssignedId(), "Resolutor") : null;

        Ticket ticket = new Ticket();
        ticket.setTitle(dto.getTitle().trim());
        ticket.setDescription(dto.getDescription().trim());
        ticket.setLocation(dto.getLocation().trim());
        ticket.setCategory(dto.getCategory());
        ticket.setPriority(dto.getPriority() != null ? dto.getPriority() : TicketPriority.MEDIUM);
        ticket.setStatus(TicketStatus.OPEN);
        ticket.setRequester(requester);
        ticket.setAssigned(assigned);

        return mapToResponseDTO(repository.save(ticket));
    }

    public TicketResponseDTO addComment(long ticketId, CommentRequestDTO dto) {
        Ticket ticket = findTicket(ticketId);
        if(ticket.getStatus() == TicketStatus.CLOSED){
            throw ApiException.badRequest("No se puede comentar un ticket cerrado");
        }

        TicketComment comment = new TicketComment();
        comment.setAuthor(dto.getAuthor().trim());
        comment.setText(dto.getText().trim());
        comment.setTicket(ticket);
        commentRepository.save(comment);

        ticket.getComments().add(comment);
        ticket.setUpdatedAt(LocalDateTime.now());
        return mapToResponseDTO(repository.save(ticket));
    }

    public TicketResponseDTO updateStatus(long id, TicketStatus status){
        Ticket ticket = findTicket(id);
        ticket.setStatus(status);
        ticket.setUpdatedAt(LocalDateTime.now());
        return mapToResponseDTO(repository.save(ticket));
    }

    public TicketResponseDTO assign(long id, Long resolverId){
        Ticket ticket = findTicket(id);
        if (resolverId == null){
            ticket.setAssigned(null);
        } else {
            User resolver = findUser(resolverId, "Resolutor");
            if (resolver.getRole()!=UserRole.RESOLVER){
                throw ApiException.badRequest("El usuario seleccionado no es un resolutor");
            }
            ticket.setAssigned(resolver);
        }
        ticket.setUpdatedAt(LocalDateTime.now());
        return mapToResponseDTO(repository.save(ticket));
    }

    public void deleteTicket(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException(String.format("Ticket %d no encontrado", +id));
        }
        repository.deleteById(id);
    }

    private TicketResponseDTO mapToResponseDTO(Ticket t) {
        List<CommentResponseDTO> comments = t.getComments().stream()
                .map(c -> new CommentResponseDTO(
                        c.getCommentId(),
                        c.getAuthor(),
                        c.getText(),
                        c.getTimestamp()))
                .toList();

        return new TicketResponseDTO(
                t.getTicketId(),
                t.getTitle(),
                t.getDescription(),
                t.getCategory(),
                t.getPriority(),
                t.getStatus(),
                t.getLocation(),
                t.getRequester().getUserId(),
                t.getAssigned() != null ? t.getAssigned().getUserId() : null,
                comments,
                t.getCreatedAt(),
                t.getUpdatedAt()
        );

    }
}
