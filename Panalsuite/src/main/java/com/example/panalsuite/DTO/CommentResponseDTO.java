package com.example.panalsuite.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class CommentResponseDTO {
    private Long commentId;
    private String author;
    private String text;
    private LocalDateTime timestamp;
}
