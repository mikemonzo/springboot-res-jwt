package com.example.springboot_res_jwt.note.model;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Note {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private String author;
    private boolean important;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
