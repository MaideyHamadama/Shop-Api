package com.shop.backend.controller;

import com.shop.backend.entity.User;
import com.shop.backend.service.AuthService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Key;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private AuthService authService;

    private Key secretKey;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
        this.secretKey = authService.getSecretKey();
    }

    //Inscription
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (user == null || user.getEmail() == null || user.getPassword() == null) {
            return ResponseEntity.badRequest().body("Tous les champs sont requis.");
        }

            try {
            String rawPassword = user.getPassword();

            authService.register(user);
            String token = authService.login(user.getEmail(), rawPassword);

            return ResponseEntity.ok(Map.of(
                    "message", "Inscription réussie !",
                    "token", token
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "message", "Erreur lors de l'inscription",
                    "error", e.getMessage()
            ));
        }
    }

    // Connexion
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            String token = authService.login(user.getEmail(), user.getPassword());
            return ResponseEntity.ok(Map.of(
                    "message", "Connexion réussie !",
                    "token", token
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
    // Recuperation des infos utilisateurs via le token pour les afficher
    @GetMapping("/me")
    public ResponseEntity<?> getAuthenticatedUser(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            String token = authorizationHeader.substring(7);
            String email = extractEmailFromToken(token);

            if (email == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token invalide.");
            }

            User authenticatedUser = authService.findByEmail(email);
            if (authenticatedUser == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utilisateur non trouvé.");
            }

            return ResponseEntity.ok(authenticatedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la récupération de l'utilisateur : " + e.getMessage());
        }
    }

    // Méthode de décodage de l'email du token pour pouvoir connecter directement après l'inscription
    private String extractEmailFromToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (Exception e) {
            System.err.println("Erreur lors de la validation du token : " + e.getMessage());
            return null;
        }
    }
}
