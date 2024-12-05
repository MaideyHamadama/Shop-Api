package com.shop.backend.controller;

import com.shop.backend.entity.User;
import com.shop.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/register ")
@CrossOrigin(origins = "http://localhost:3000") 
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("auth/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        // Logique d'inscription
        if (user == null || user.getEmail() == null || user.getPassword() == null) {
            return ResponseEntity.badRequest().body("Tous les champs sont requis.");
        }
        // Simuler un enregistrement utilisateur
        System.out.println("Utilisateur enregistré : " + user);
        // Réponse réussie
        return ResponseEntity.ok("Utilisateur enregistré avec succès !");
    }

    @PostMapping("auth/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            String token = authService.login(user.getEmail(), user.getPassword());
            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
