package com.MyNote.MyNote.controller;

import auth.authentificationRequest;
import auth.authentificationResponse;
import auth.registerRequest;
import com.MyNote.MyNote.service.authentificationService;
import com.MyNote.MyNote.model.Employe;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class authentificationController {
    private final authentificationService service;
    @PostMapping("/register")
    public ResponseEntity<authentificationResponse> register(
            @RequestBody registerRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<authentificationResponse> authenticate(
            @RequestBody authentificationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken( request, response);
    }
    //@PreAuthorize()
    //("hasRole("ADMIN")')

    @GetMapping("/CurrentAuthent")
    public ResponseEntity<Object> Employe(
    ) {
        return ResponseEntity.ok(service.getCurrentUser());
    }

    @GetMapping("/getAllEmployee")
    public List<Employe> getAllEmployee() {
        return service.getAllEmployee();
    }
    // Get employee by Id
    @GetMapping("/getEmployeeByID/{ID}")
    public Employe getEmployeeByID(@PathVariable String ID) {
        return service.getEmployeeByID(ID);
    }

    // Get employee by name
    @GetMapping("/getEmployeeByEmail/{Email}")
    public Optional<Employe> getEmployeeByEmail(@PathVariable String Email) {
        return service.getEmployeeByEmail(Email);
    }

    // Update employee
    @PutMapping("/updateEmployee")
    public Employe updateEmployee(@RequestBody Employe employe) {
        return service.updateEmployee(employe);
    }

    // Delete employee
    @DeleteMapping("/deleteEmployeeByID/{ID}")
    public void deleteEmployeeByID(@PathVariable String ID) {
        service.deleteEmployeeByID(ID);
    }}
