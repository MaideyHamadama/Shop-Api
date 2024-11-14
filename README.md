# Étapes d'Initialisation de la Base de Données

Pour initialiser la base de données `shop` avec les tables et les données, suivez ces étapes :

1. **Créer la Base de Données dans phpMyAdmin** :
    - Ouvrez phpMyAdmin et créez une nouvelle base de données nommée `shop`.

2. **Configurer le Serveur API pour la Création des Tables** :
    - Assurez-vous que la ligne `spring.sql.init.mode=always` dans `application.properties` est **commentée**.
    - Définissez `spring.jpa.hibernate.ddl-auto=create`.
    - Lancez le serveur API. Hibernate créera automatiquement les tables nécessaires dans la base de données `shop`.

3. **Vérifier la Création des Tables** :
    - Dans phpMyAdmin, confirmez que les tables ont bien été créées.

4. **Charger les Données dans les Tables** :
    - Arrêtez le serveur API.
    - Modifiez `spring.jpa.hibernate.ddl-auto` en `update`.
    - Décommentez `spring.sql.init.mode=always`.
    - Redémarrez le serveur API. Cela chargera les données du fichier `data.sql` dans la base de données `shop`.

5. **Étape Finale** :
    - Vérifiez dans phpMyAdmin que les données ont été correctement ajoutées aux tables.
    - Commentez à nouveau la ligne `spring.sql.init.mode=always` dans `application.properties` pour éviter une réinitialisation des données lors des prochains démarrages du serveur.
