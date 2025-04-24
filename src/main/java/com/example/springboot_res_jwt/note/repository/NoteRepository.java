package com.example.springboot_res_jwt.note.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springboot_res_jwt.note.model.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByAuthor(String author);
}
