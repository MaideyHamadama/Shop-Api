package com.shop.backend.controller;

import com.shop.backend.dto.UserDTO;
import com.shop.backend.entity.User;
import com.shop.backend.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
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

    private final AuthService authService;


    private Key secretKey;
    String cartId = null;


    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
        this.secretKey = authService.getSecretKey();
    }

    // Inscription
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user, @CookieValue(value = "cartId", required = false) String cartId) {

        // Vérification des champs
        if (user == null || user.getEmail() == null || user.getPassword() == null) {
            return ResponseEntity.badRequest().body("Tous les champs sont requis.");
        }

        try {
            // Enregistrement de l'utilisateur
            String rawPassword = user.getPassword();
            authService.register(user);

            // Assigner le panier provenant des cookies à l'utilisateur
            if (cartId != null) {
                authService.assignCartIdToUser(user.getEmail(), cartId);
            }

            // Générer un token pour authentifier immédiatement l'utilisateur
            String token = authService.login(user.getEmail(), rawPassword);

            // Retourner le token et un message de succès pour tester les requêtes
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
    public ResponseEntity<?> login(@RequestBody User user, HttpServletRequest request) {
        try {
            String token = authService.login(user.getEmail(), user.getPassword());

            // Récupération du cartId depuis les cookies
            cartId = null;
            if (request.getCookies() != null) {
                for (Cookie cookie : request.getCookies()) {
                    if ("cartId".equals(cookie.getName())) {
                        cartId = cookie.getValue();
                        break;
                    }
                }
            }

            // Vérification si le cartId a été trouvé
            // Assigner le panier à l'utilisateur
            if (cartId != null) {
                authService.assignCartIdToUser(user.getEmail(), cartId);
            }

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
            String email = authService.extractEmailFromToken(token);

            // Vérification de l'email extrait du token
            if (email == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token invalide.");
            }

            // Recherche de l'utilisateur dans le service ou repository
            User authenticatedUser = authService.findByEmail(email);
            if (authenticatedUser == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utilisateur non trouvé.");
            }

            // Création du DTO avec cartId si disponible
            UserDTO userDTO = new UserDTO(authenticatedUser);
            if (authenticatedUser.getShoppingCart() != null) {
                userDTO.setCartId(authenticatedUser.getShoppingCart().getCartId());
            }

            return ResponseEntity.ok(userDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la récupération de l'utilisateur : " + e.getMessage());
        }
    }
}