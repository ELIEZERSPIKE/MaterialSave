package com.example.material_save.Controllers;

import com.example.material_save.HelloApplication;
import com.example.material_save.Models.Manager;
import com.example.material_save.Models.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
//import org.w3c.dom.events.MouseEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class AuthController {

    @FXML
    private TextField username;

    @FXML
    private TextField email ;

    @FXML
    private PasswordField password;


    @FXML
    private void onRegister() throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("hello-view.fxml")));
        Stage stage = (Stage) username.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    @FXML
    private  void onSignIn() throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("LogIn.fxml")));
        Stage stage = (Stage) username.getScene().getWindow();
        stage.setScene(new Scene(root));
    }


//   @FXML
//   private void onSubmitRegister() {
//    try {
//        String Username = username.getText().trim();
//        String Email = email.getText().trim();
//        String Password = password.getText().trim();
//
//        if (!Username.isEmpty() && !Password.isEmpty() && !Email.isEmpty()) {
//            Manager manager = new Manager();
//            manager.setUsername(Username);
//            manager.setEmail(Email);
//            manager.setPassword(Password);
//
//            // Enregistrement de l'utilisateur
//            manager.register(manager);
//
//            // Alerte de confirmation d'inscription
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Inscription réussie");
//            alert.setHeaderText(null);
//            alert.setContentText("Inscription effectuée avec succès !");
//            alert.showAndWait(); // Attends que l'utilisateur ferme l'alerte
//
//            // Changement de la scène
//            Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("LogIn.fxml")));
//            Stage stage = (Stage) username.getScene().getWindow();
//            stage.setScene(new Scene(root));
//        } else {
//            // Afficher un message d'erreur si les champs sont vides
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Champs manquants");
//            alert.setHeaderText(null);
//            alert.setContentText("Tous les champs doivent être remplis.");
//            alert.show();
//        }
//    } catch (IOException e) {
//        e.printStackTrace();
//        // Afficher un message d'erreur en cas d'échec de chargement de la vue
//        System.out.println("Erreur lors du chargement de la vue.");
//    } catch (SQLException e) {
//        e.printStackTrace();
//        // Afficher un message d'erreur en cas d'échec d'enregistrement dans la base de données
//        System.out.println("Erreur lors de l'enregistrement dans la base de données.");
//    }
//}
//    @FXML
//    private void onSubmitSignIn() throws IOException, SQLException {
//    String Username = username.getText().trim();
//    String Password = password.getText().trim();
//
//    if (!Username.isEmpty() && !Password.isEmpty()){
//        Manager manager = new Manager();
//        manager.setUsername(Username);
//        manager.setPassword(Password);
//
//        // Supposons que tu appelles une méthode pour enregistrer l'utilisateur ici
//        if (manager.SignIn(manager)) {
//            // Alerte pour confirmer l'inscription
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Connexion réussie");
//            alert.setHeaderText(null);
//            alert.setContentText("Connexion effectuée avec succès !");
//            alert.showAndWait(); // Attend que l'utilisateur ferme l'alerte
//
//            // Transition vers la page de connexion
//            Stage stage = (Stage) username.getScene().getWindow();
//            Stage newStage = new Stage();
//            Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("dashboard.fxml")));
//            newStage.setTitle("Material_Save");
//            newStage.setScene(new Scene(root));
//            newStage.show();
//            stage.close();
//        } else {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Échec d'authentification");
//            alert.setHeaderText(null);
//            alert.setContentText("Nom d'utilisateur ou mot de passe incorrect.");
//            alert.show();
//        }
//    }
//}

//    @FXML
//    private void onSubmitSignIn() throws IOException, SQLException {
//        String Username = username.getText().trim();
//        String Password = password.getText().trim();
//
//        if (!Username.isEmpty() && !Password.isEmpty()) {
//            Manager manager = new Manager();
//            manager.setUsername(Username);
//            manager.setPassword(Password);
//
//            // Supposons que tu appelles une méthode pour authentifier l'utilisateur ici
//            if (manager.SignIn(manager)) {
//                // Enregistrer l'utilisateur actuel dans la session
//                SessionManager.setCurrentUsername(Username);
//
//                // Alerte pour confirmer la connexion
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Connexion réussie");
//                alert.setHeaderText(null);
//                alert.setContentText("Connexion effectuée avec succès !");
//                alert.showAndWait(); // Attend que l'utilisateur ferme l'alerte
//
//                // Transition vers la page de tableau de bord
//                Stage stage = (Stage) username.getScene().getWindow();
//                Stage newStage = new Stage();
//                Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("dashboard.fxml")));
//                newStage.setTitle("Material_Save");
//                newStage.setScene(new Scene(root));
//                newStage.show();
//                stage.close();
//            } else {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Échec d'authentification");
//                alert.setHeaderText(null);
//                alert.setContentText("Nom d'utilisateur ou mot de passe incorrect.");
//                alert.show();
//            }
//        } else {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Champs manquants");
//            alert.setHeaderText(null);
//            alert.setContentText("Tous les champs doivent être remplis.");
//            alert.show();
//}
//    }
    @FXML
    private void onSubmitRegister() {
        try {
            String Username = username.getText().trim();
            String Email = email.getText().trim();
            String Password = password.getText().trim();

            if (!Username.isEmpty() && !Password.isEmpty() && !Email.isEmpty()) {
                Manager manager = new Manager();

                // Vérifie si le nom d'utilisateur est déjà utilisé
                if (!manager.isUsernameUnique(Username)) { // Condition corrigée
                    // Afficher un message d'alerte si le nom d'utilisateur est déjà pris
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Nom d'utilisateur pris");
                    alert.setHeaderText(null);
                    alert.setContentText("Le nom d'utilisateur est déjà utilisé, veuillez en choisir un autre.");
                    alert.showAndWait(); // Attend que l'utilisateur ferme l'alerte
                    return; // Sort de la méthode pour éviter de continuer l'inscription
                }

                // Si le nom d'utilisateur n'est pas pris, procéder à l'inscription
                manager.setUsername(Username);
                manager.setEmail(Email);
                manager.setPassword(Password);

                // Enregistrement de l'utilisateur
                manager.register(manager); // Assurez-vous que cette méthode est correcte

                // Alerte de confirmation d'inscription
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Inscription réussie");
                alert.setHeaderText(null);
                alert.setContentText("Inscription effectuée avec succès !");
                alert.showAndWait(); // Attend que l'utilisateur ferme l'alerte

                // Changement de la scène
                Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("LogIn.fxml")));
                Stage stage = (Stage) username.getScene().getWindow();
                stage.setScene(new Scene(root));
            } else {
                // Afficher un message d'erreur si les champs sont vides
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Champs manquants");
                alert.setHeaderText(null);
                alert.setContentText("Tous les champs doivent être remplis.");
                alert.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Afficher un message d'erreur en cas d'échec de chargement de la vue
            System.out.println("Erreur lors du chargement de la vue.");
        } catch (SQLException e) {
            e.printStackTrace();
            // Afficher un message d'erreur en cas d'échec d'enregistrement dans la base de données
            System.out.println("Erreur lors de l'enregistrement dans la base de données.");
        }
    }

    @FXML
    private void onSubmitSignIn() throws IOException, SQLException {
        String Username = username.getText().trim();
        String Password = password.getText().trim();

        if (!Username.isEmpty() && !Password.isEmpty()) {
            Manager manager = new Manager();
            manager.setUsername(Username);
            manager.setPassword(Password);

            // Vérifie si les identifiants sont corrects
            if (manager.SignIn(manager)) {
                // Enregistre la session
                SessionManager.setCurrentUsername(Username);

                // Alerte de confirmation de connexion
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Connexion réussie");
                alert.setHeaderText(null);
                alert.setContentText("Connexion effectuée avec succès !");
                alert.showAndWait();

                // Transition vers la page dashboard
                Stage stage = (Stage) username.getScene().getWindow();
                Stage newStage = new Stage();
                Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("dashboard.fxml")));
                newStage.setTitle("Material_Save");
                newStage.setScene(new Scene(root));
                newStage.show();
                stage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Échec d'authentification");
                alert.setHeaderText(null);
                alert.setContentText("Nom d'utilisateur ou mot de passe incorrect.");
                alert.show();
 }
}
    }





}
