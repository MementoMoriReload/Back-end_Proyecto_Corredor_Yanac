package com.example.panalsuite.controller;

import com.example.panalsuite.DTO.CommentRequestDTO;
import com.example.panalsuite.DTO.TicketRequestDTO;
import com.example.panalsuite.DTO.TicketResponseDTO;
import com.example.panalsuite.Service.TicketService;
import com.example.panalsuite.model.Ticket;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.Comment;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ticket")
public class TicketController {
    private final TicketService service;


    public TicketController(TicketService service) {
        this.service = service;
    }

    @GetMapping
    public List<TicketResponseDTO> getAllTickets() {
        return service.getAllTickets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> getTicketById(@PathVariable long id) {
        return service.getTicketById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TicketResponseDTO> saveTicket(@Valid @RequestBody TicketRequestDTO dto) {
        TicketResponseDTO created = service.saveTicket(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("{id}/comments")
    public ResponseEntity<TicketResponseDTO> addComment(@PathVariable Long id, @Valid @RequestBody CommentRequestDTO dto) {
        TicketResponseDTO updated = service.addComment(id, dto);
        return ResponseEntity.ok(updated);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable long id) {
        if (service.getTicketById(id).isPresent()) {
            service.deleteTicket(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();

    }
}
