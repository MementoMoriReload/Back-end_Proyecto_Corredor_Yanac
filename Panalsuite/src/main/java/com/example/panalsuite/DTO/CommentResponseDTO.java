package com.example.panalsuite.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class CommentResponseDTO {
    private Long commentId;
    private String author;
    private String text;
    private LocalTime timestamp;
}
