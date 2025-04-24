package com.example.springboot_res_jwt.note.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.example.springboot_res_jwt.note.model.Note;
import com.example.springboot_res_jwt.note.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteRepository noteRepository;

    @GetMapping("/")
    public ResponseEntity<List<Note>> getAll() {
        return buildResponseOfAList(noteRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getById(@PathVariable Long id) {
        return ResponseEntity.of(noteRepository.findById(id));
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<Note>> getByAuthor(@PathVariable String author) {
        return buildResponseOfAList(noteRepository.findByAuthor(author));
    }

    private ResponseEntity<List<Note>> buildResponseOfAList(List<Note> notes) {
        if (notes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(notes);
    }

    @PostMapping("/")
    public ResponseEntity<Note> createNewNote(@RequestBody Note note) {
        Note entity = noteRepository.save(note);

        URI createdURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(entity.getId()).toUri();

        return ResponseEntity.created(createdURI).body(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> edit(@PathVariable Long id, @RequestBody Note note) {
        return ResponseEntity.of(noteRepository.findById(id).map(entity -> {
            entity.setTitle(note.getTitle());
            entity.setContent(note.getContent());
            entity.setAuthor(note.getAuthor());
            entity.setImportant(note.isImportant());
            return noteRepository.save(entity);
        }));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        noteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
