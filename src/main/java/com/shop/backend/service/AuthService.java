package com.shop.backend.service;

import java.security.Key;
import java.util.Date;

import com.shop.backend.entity.ShoppingCart;
import com.shop.backend.entity.ShoppingCartLine;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.shop.backend.entity.User;
import com.shop.backend.repository.UserRepository;
import com.shop.backend.repository.ShoppingCartRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ShoppingCartService shoppingCartService;

    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);


    // Inscription
    public User register(User user) throws Exception {

        // Vérifier si l'email est déjà utilisé
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new Exception("Email déjà utilisé.");
        }

        // Hachage du mot de passe
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Assignation du mot de passe haché à l'utilisateur
        user.setPassword(encoder.encode(user.getPassword()));

        // Enregistrement de l'utilisateur dans la db
        return userRepository.save(user);
    }

    // Assigner le panier provenant des cookies à l'utilisateur
    @Transactional
    public void assignCartIdToUser(String email, String cartId) {

        // Récupérer l'utilisateur par son email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouve avec l'email : " + email));

        // Récupérer le panier déjà associé à l'utilisateur dans la db (s'il existe)
        ShoppingCart existingCart = user.getShoppingCart();

        // Récupérer le panier provenant des cookies
        ShoppingCart cookieCart = shoppingCartRepository.findById(Integer.parseInt(cartId))
                .orElseThrow(() -> new RuntimeException("Cart not productFound"));

        // Si panier en db et en cookies fusionner les deux paniers
        if (existingCart != null) {
            shoppingCartService.mergeCarts(existingCart, cookieCart);
        } else {
            // Si aucun panier n'est associé, utiliser directement le panier des cookies
            user.setShoppingCart(cookieCart);
            cookieCart.setUser(user);
        }

        // Sauvegarder l'utilisateur avec le panier mis à jour
        userRepository.save(user);

        // Supprimer l'ancien panier des cookies (si nécessaire)
//        if (existingCart != null) {
//            System.out.println("Suppression de l'ancien panier des cookies avec ID: " + cookieCart.getCartId());
//            shoppingCartRepository.delete(cookieCart);
//        }
    }


    private void recalculateCartTotals(ShoppingCart cart) {
        // Faire la somme des prix totaux des produits dans le panier
        double totalPrice = cart.getCartProducts().stream()
                .mapToDouble(ShoppingCartLine::getTotalPrice)
                .sum();

        // Calculer le prix total hors TVA
        double totalPriceExcludingVAT = totalPrice / (1 + cart.getTvaRate());

        // Mettre à jour les totaux du panier
        cart.setCartTotalPrice(totalPrice);
        cart.setCartTotalPriceExcludingVAT(totalPriceExcludingVAT);
    }

    // Connexion
    public String login(String email, String password) throws Exception {
        // Récupérer l'utilisateur par son email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("Utilisateur non trouvé."));

        // Vérifier le mot de passe
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(password, user.getPassword())) {
            throw new Exception("Mot de passe incorrect.");
        }

        // Générer un token JWT
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 jour
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    // Récupérer un utilisateur par son email
    public User findByEmail(String email) throws Exception {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("Utilisateur non trouvé."));
    }

    // Récupérer la clé secrète pour le controller
    public Key getSecretKey() {
        return this.secretKey;
    }

    // Méthode de décodage de l'email du token pour pouvoir connecter user directement après l'inscription
    public String extractEmailFromToken(String token) {
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
