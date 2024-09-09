package com.example.material_save.Controllers;

import com.example.material_save.HelloApplication;
import com.example.material_save.Interfaces.HomeViewInterface;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private StackPane AreatStackPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("home-view.fxml")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AreatStackPane.getChildren().removeAll();
        AreatStackPane.getChildren().setAll(root);
    }

    @FXML
    private void onBackToHome() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("home-view.fxml")));
        AreatStackPane.getChildren().removeAll();
        AreatStackPane.getChildren().setAll(root);
    }


    @FXML
    private void onReadProducts() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("dashboard.fxml")));
        AreatStackPane.getChildren().removeAll();
        AreatStackPane.getChildren().setAll(root);
    }
    @FXML
    private  void AccessDasboard() throws  IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("dashboard.fxml")));
        AreatStackPane.getChildren().removeAll();
        AreatStackPane.getChildren().setAll(root);

    }
    @FXML
    private void Maintenances() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("maintenances.fxml")));
        AreatStackPane.getChildren().removeAll();
        AreatStackPane.getChildren().setAll(root);
    }
    @FXML
    private void Location() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("location.fxml")));
        AreatStackPane.getChildren().removeAll();
        AreatStackPane.getChildren().setAll(root);
    }
    @FXML
    private void Parameters() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("parameters.fxml")));
        AreatStackPane.getChildren().removeAll();
        AreatStackPane.getChildren().setAll(root);
    }
    @FXML
    private  void LogOut() throws  IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("logout.fxml")));
        AreatStackPane.getChildren().removeAll();
        AreatStackPane.getChildren().setAll(root);
    }

}
