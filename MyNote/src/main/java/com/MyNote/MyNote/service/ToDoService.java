package com.MyNote.MyNote.service;

import com.MyNote.MyNote.model.ToDoListe;
import com.MyNote.MyNote.repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ToDoService {
    private ToDoRepository Repository;

    public ToDoService(ToDoRepository repository) {
        Repository = repository;
    }

    public ToDoListe RegisterToDo(ToDoListe ToDoListe) {
        return Repository.save(ToDoListe);
    }
        public boolean deleteToDoByID(String ID) {
        ToDoListe existingEMP = Repository.getByID(ID);
        if(existingEMP != null) {
            Repository.deleteByID(ID);
            return true;
        }
        return false;
    }
    public List<ToDoListe> getAllToDo() {
        return Repository.findAll();
    }
}
