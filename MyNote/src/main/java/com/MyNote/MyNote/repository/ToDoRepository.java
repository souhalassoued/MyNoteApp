package com.MyNote.MyNote.repository;

import com.MyNote.MyNote.model.ToDoListe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends MongoRepository<ToDoListe, String> {
    ToDoListe getByID(String ID);
    void deleteByID(String ID);
}
