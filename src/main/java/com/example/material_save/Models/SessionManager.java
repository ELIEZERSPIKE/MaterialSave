package com.example.material_save.Models;

public class SessionManager {

    private static String currentUsername;
    private static int currentUserId;
    private static String currentUserRole;

    public static void setCurrentUsername(String username) {
        currentUsername = username;
    }

    public static void setCurrentUserId(int userId) {
        currentUserId = userId;
    }

    public static int getCurrentUserId() {
        return currentUserId;
    }

    public static String getCurrentUsername() {
        return currentUsername;
    }

    public static void setCurrentUserRole(String role) {
        currentUserRole = role;
    }
    public static String getCurrentUserRole() {
        return currentUserRole; // Méthode pour récupérer le rôle
    }


    // Ajout d'un type de retour (void) et réinitialisation des valeurs
    public static void clearSession() {
        currentUsername = null;
        currentUserId = -1; // Ou toute autre valeur indiquant qu'aucun utilisateur n'est connecté
    }

}
