package com.shop.backend.controller;

import com.shop.backend.dto.LoginRequest;
import com.shop.backend.dto.UserDTO;
import com.shop.backend.entity.ShoppingCart;
import com.shop.backend.entity.User;
import com.shop.backend.repository.ShoppingCartRepository;
import com.shop.backend.service.AuthService;
import com.shop.backend.service.ShoppingCartService;
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

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

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

//             Vérifier si le cookie `cartId` existe
                    if (cartId != null) {
                        // Assigner le panier existant (provenant des cookies) à l'utilisateur
                        authService.assignCartIdToUser(user, cartId);
                    } else {
                        // Si aucun cookie n'est présent, créer un nouveau panier
                        ShoppingCart newCart = shoppingCartService.createNewCart(user.getUserId());
                        user.setShoppingCart(newCart); // Assigner le nouveau panier à l'utilisateur
                        shoppingCartRepository.save(newCart); // Sauvegarder le panier
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
public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
    try {
        // Authentification de l'utilisateur
        String token = authService.login(loginRequest.getEmail(), loginRequest.getPassword());

        String cartId = null;

        // Récupération du cartId depuis les cookies
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("cartId".equals(cookie.getName())) {
                    cartId = cookie.getValue();
                    break;
                }
            }
        }

        // Assignation du panier si cartId est présent
        if (cartId != null) {
            User user = authService.findByEmail(loginRequest.getEmail());
            authService.assignCartIdToUser(user, cartId);
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