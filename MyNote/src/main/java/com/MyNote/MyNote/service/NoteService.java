package com.MyNote.MyNote.service;

import com.MyNote.MyNote.model.Note;
import com.MyNote.MyNote.repository.NoteReposiory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteReposiory Repository;
    public Note RegisterNote(Note note) {
        return Repository.save(note);
    }

    public NoteService(NoteReposiory repository) {
        Repository = repository;
    }
    public List<Note> getAllNotes() {
        return Repository.findAll();
    }

    public boolean deleteNoteByID(String ID) {
        Note existing = Repository.getNoteByID(ID);
        if(existing != null) {
            Repository.deleteByID(ID);
            return true;
        }
        return false;
    }
}
