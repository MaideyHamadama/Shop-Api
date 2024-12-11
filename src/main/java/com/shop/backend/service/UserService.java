package com.shop.backend.service;

import com.shop.backend.entity.User;
import com.shop.backend.repository.UserRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service responsable de la création des utilisateurs.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Crée un utilisateur après avoir validé les données.
     * 
     * @param user Le modèle de l'utilisateur à créer
     * @param result Le résultat de la validation
     * @return Un message de succès ou une liste d'erreurs de validation
     */
    public String createUser(@Valid User user, BindingResult result) {
        // Vérifier s'il y a des erreurs de validation
        if (result.hasErrors()) {
            // Récupérer les erreurs sous forme de texte
            List<String> errorMessages = result.getAllErrors().stream()
                                                .map(error -> error.getDefaultMessage())
                                                .collect(Collectors.toList());
            // Retourner un message d'erreur
            return "Erreurs de validation : " + String.join(", ", errorMessages);
        }

        // Sauvegarder l'utilisateur dans la base de données
        userRepository.save(user);
        return "Utilisateur créé avec succès !";
    }
}
