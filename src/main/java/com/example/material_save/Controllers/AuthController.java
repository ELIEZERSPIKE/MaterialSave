package com.example.material_save.Controllers;

import com.example.material_save.HelloApplication;
import com.example.material_save.Models.Manager;
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
    @FXML
    private void onSubmitRegister() {
        try {
            String Username = username.getText().trim();
            String Email = email.getText().trim();
            String Password = password.getText().trim();

            if (!Username.isEmpty() && !Password.isEmpty() && !Email.isEmpty()) {
                Manager manager = new Manager();
                manager.setUsername(Username);
                manager.setEmail(Email);
                manager.setPassword(Password);

                // Assurez-vous que register() ne lance pas une SQLException non gérée
                manager.register(manager);

                // Changement de la scène
                Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("LogIn.fxml")));
                Stage stage = (Stage) username.getScene().getWindow();
                stage.setScene(new Scene(root));
            } else {
                // Afficher un message d'erreur si les champs sont vides
//                System.out.println("Tous les champs doivent être remplis.");
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Ehec d'authentification");
//                alert.setTitle("Nom d'utilisateur ou Mot de passe obligatoires");
//                alert.setContentText("Tous les champs doivent être remplis.");
//                alert.show();
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

        if (!Username.isEmpty() && !Password.isEmpty()){
            Manager manager = new Manager();
            manager.setUsername(Username);
            manager.setPassword(Password);

            if (manager.SignIn(manager) ) {
                Stage stage = (Stage) username.getScene().getWindow();
                Stage newStage =  new Stage();
               Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("dashboard.fxml")));
                newStage.setTitle("Material_Save");
                newStage.setScene(new Scene(root));
                newStage.show();
                stage.close();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ehec d'authentification");
                alert.setTitle("Nom d'utilisateur ou Mot de passe incorrecte");
                alert.setContentText("Nous ne parvenons pas à vous connecter");
                alert.show();
            }
        }

    }
}
