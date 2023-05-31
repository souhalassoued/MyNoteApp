package com.MyNote.MyNote.controller;

import com.MyNote.MyNote.model.Note;
import com.MyNote.MyNote.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Note")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class NoteController {
    private final NoteService service;
    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<Note> register (@RequestBody Note note) {
        return ResponseEntity.ok(service.RegisterNote(note));
    }

    @DeleteMapping("/deleteNoteByID/{ID}")
    public void deleteNoteByID(@PathVariable String ID) { service.deleteNoteByID(ID);
    }
    @GetMapping("/getAllNotes")
    public List<Note> getAllNotes() {
        return service.getAllNotes();
    }

}
