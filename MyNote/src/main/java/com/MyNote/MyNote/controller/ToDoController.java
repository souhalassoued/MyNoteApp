package com.MyNote.MyNote.controller;

import com.MyNote.MyNote.model.ToDoListe;
import com.MyNote.MyNote.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ToDo")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ToDoController {
    private final ToDoService service;
    @PostMapping("/register")
    public ResponseEntity<ToDoListe> register (@RequestBody ToDoListe ToDoListe) {
        return ResponseEntity.ok(service.RegisterToDo(ToDoListe));
    }
    @DeleteMapping("/deleteToDoByID/{ID}")
    public void deleteToDoByID(@PathVariable String ID) {
         service.deleteToDoByID(ID);
    }
    @GetMapping("/getAllToDo")

    public List<ToDoListe> getAllToDo() {
        return service.getAllToDo();
    }
}

