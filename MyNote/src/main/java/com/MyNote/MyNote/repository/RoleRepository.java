package com.MyNote.MyNote.repository;

import com.MyNote.MyNote.model.ERole;
import com.MyNote.MyNote.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}