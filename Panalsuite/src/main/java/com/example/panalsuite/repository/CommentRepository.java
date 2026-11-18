package com.example.panalsuite.repository;

import com.example.panalsuite.model.TicketComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<TicketComment, Long> {
}
