package com.MyNote.MyNote.repository;


import com.MyNote.MyNote.model.Employe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MyNoteRepository extends MongoRepository<Employe, String> {
    Optional<Employe> findByEmail(String email);
    Employe getByID(String ID);
    Optional<Employe> findByID(String ID);
    void deleteByID(String ID);
    Employe getEmployeeByID(String ID);
}