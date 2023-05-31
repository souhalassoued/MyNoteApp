package com.MyNote.MyNote.repository;

import com.MyNote.MyNote.jwt.Token;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends MongoRepository<Token,Integer> {

    @Query("{ 'user.ID' : ?0, $or: [ { 'expired' : false }, { 'revoked' : false } ] }")
    List<Token> findAllValidTokenByUser(String ID);
    Optional<Token> findByToken(String token);
}
