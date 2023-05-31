package com.MyNote.MyNote.service;

import auth.authentificationRequest;
import auth.authentificationResponse;
import auth.registerRequest;
import com.MyNote.MyNote.config.JwtService;
import com.MyNote.MyNote.jwt.Token;
import com.MyNote.MyNote.jwt.TokenType;
import com.MyNote.MyNote.repository.TokenRepository;
import com.MyNote.MyNote.repository.MyNoteRepository;
import com.MyNote.MyNote.model.Employe;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class authentificationService {
    private final MyNoteRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public authentificationResponse register(registerRequest request) {
        var user = Employe.builder()
                .Username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        Employe savedUser = repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        saveUserToken(savedUser, jwtToken);
        return authentificationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }
    public authentificationResponse authenticate(authentificationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        System.out.println(jwtToken);
        return authentificationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();

    }

    private void saveUserToken(Employe user, String jwtToken) {
        var token = Token.builder()
                .Employe(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(Employe user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getID());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.repository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = authentificationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                try {
                    new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
                } catch (java.io.IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public Object getCurrentUser() {
        return repository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

    }
    public Employe addEmployee(Employe employe) {
        return repository.save(employe);
    }


    public Optional<Employe> getEmployeeByEmail(String Email) {
        return  repository.findByEmail(Email);
    }
    public Employe getEmployeeByID(String ID) {
        return repository.getEmployeeByID(ID);
    }
    public List <Employe> getAllEmployee() {
        return repository.findAll();
    }
    public Employe updateEmployee(Employe employe) {
        Optional<Employe> existingEmpOptional = repository.findByID(employe.getID());
        if (existingEmpOptional.isPresent()) {
            Employe existingEMP = existingEmpOptional.get();
            existingEMP.setUsername(employe.getUsername());
            existingEMP.setEmail(employe.getEmail());
            existingEMP.setRoles(employe.getRoles());
            return repository.save(existingEMP);
        } else {
            System.out.println("Emp not found");
            return repository.save(employe);
        }
    }

            public boolean deleteEmployeeByID(String ID) {
        Employe existingEMP = repository.getByID(ID);
        if(existingEMP != null) {
            repository.deleteByID(ID);
            return true;
        }
        return false;
    }
}

