package com.MyNote.MyNote.repository;

import com.MyNote.MyNote.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteReposiory extends MongoRepository<Note, String> {
    Note getNoteByID(String ID);
    void deleteByID(String ID);
}
