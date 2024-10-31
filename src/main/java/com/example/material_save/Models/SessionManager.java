package com.example.material_save.Models;

public class SessionManager {
    private static String currentUsername;

    public static void setCurrentUsername(String username) {
        currentUsername = username;
    }

    public static String getCurrentUsername() {
        return currentUsername;
    }

    public static void clearSession() {
        currentUsername=null;
 }

}
