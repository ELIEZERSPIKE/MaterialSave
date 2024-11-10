package com.example.material_save.Controllers;

import com.example.material_save.IDB.DBConfig;
import com.example.material_save.Models.Category;
import com.example.material_save.Models.Maintenance;
import com.example.material_save.Models.Material;
import com.example.material_save.Models.SessionManager;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.module.Configuration;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class MaterialController implements Initializable {


    @FXML
    private Button AddMaintenances_Btn;
    @FXML
    private Button AddMateriel_Btn;
    @FXML
    private AnchorPane Maintenance_Form;
    @FXML
    private StackPane AreatStackPane;
    @FXML
    private AnchorPane Form_categories;
    @FXML
    private LineChart<?, ?> GraphNonDispo;
    @FXML
    private BarChart<String, Number> GraphiqueMateriel;
    @FXML
    private Button Home_Btn;
    @FXML
    private Button LogOut_Btn;
    @FXML
    private AnchorPane Main_Form;
    @FXML
    private TableView<Maintenance> Maintenance_tableView;

    @FXML
    private TextField Maintenance_search;
    @FXML
    private AnchorPane Material_Add_Form;
    @FXML
    private TableView<Material> Material_tableView;
    @FXML
    private Label MaintenancesTotal;

    @FXML
    private TextField TextfieldMaterialMarque;
    @FXML
    private Label TotalDisponibleCategory;
    @FXML
    private Label TotalEnrolled;
    @FXML
    private Button addCategories;
    @FXML
    private DatePicker addDate;
    @FXML
    private Button addMaterial;
    @FXML
    private TableView<Category> categorie_tableView;
    @FXML
    private TableColumn<Material, String> col_category;
    @FXML
    private TableColumn<Material, Date> col_date;
    @FXML
    private TableColumn<Material, Date> col_date_main;
    @FXML
    private TableColumn<Category, String> col_description_categorie;
    @FXML
    private TableColumn<Material, String> col_etat;
    @FXML
    private TableColumn<Category, String> col_etat_categorie;
    @FXML
    private TableColumn<Category, String> col_categoryName;
    @FXML
    private TableColumn<Material, String> col_local;
    @FXML
    private TableColumn<Material, String> col_marque;
    @FXML
    private TableColumn<Material, String> col_material;
    @FXML
    private TableColumn<Material, String> col_name;
    @FXML
    private TableColumn<Material, String> col_utilisateur;
    @FXML
    private TableColumn<Material, String> colstatut;
    @FXML
    private  TableColumn<Maintenance, String> MaintenaceNumber;
    @FXML
    private  TableColumn<Maintenance, String> col_charge;
    @FXML
    private  TableColumn<Maintenance, String> col_probleme;
    @FXML
    private  TableColumn<Maintenance, String> col_dateMaintenance;
    @FXML
    private  TableColumn <Maintenance, String> col_statutMaintenance;
    @FXML
    private TableColumn<Maintenance, String>col_MaintenanceCombo;
    @FXML
    private ComboBox<Integer> ComboNumMateriel;
    @FXML
    private ComboBox<String> BoxCategorie;

    @FXML
    private ComboBox<String> comboStatut;
    @FXML
    private TextField description_categorie;
    @FXML
    private TextField etat_categorie;
    @FXML
    private AnchorPane home_Form;
    @FXML
    private TextField local_materiel;
    @FXML
    private TextField probleme_materiel;
    @FXML
    private TextField searchMaterial;
    @FXML
    private  TextField TexfieldMaterielId;
    @FXML
    private TextField textefieldLocate;
    @FXML
    private TextField textfieldMaterialNumber;
    @FXML
    private TextField textfieldMaterial_state;
    @FXML
    private TextField textfieldMateriamName;
    @FXML
    private TextField textfield_Material_User;
    @FXML
    private TextField textfield_categorie_name;
    @FXML
    private Button updateCategorie_Btn;
    @FXML
    private ComboBox <String> comboCategorie;
    @FXML
    private ComboBox Combo_maintenance;
    @FXML
    private DatePicker DateMaintenance;
    @FXML
    private TextField textFieldMaterialId;
    @FXML
    private TextField chargeMaintenace_textfield;
    @FXML
    private Button updateMaterial;
    @FXML
    private PieChart PieChartMateriel;
    @FXML
    private AnchorPane HomeForm;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;
    private Alert alert;
    private ObservableList<Material> materialData;
    private ObservableList<Category> categoriesData;
    private ObservableList<Maintenance> maintenancesData;
    public void switchForm(ActionEvent event) {
        if (event.getSource() == Home_Btn) {
            home_Form.setVisible(true);
            totalMaterial();
            Material_Add_Form.setVisible(false);
            Form_categories.setVisible(false);
            Maintenance_Form.setVisible(false);
            updatePieChart();

            System.out.println("Home appuye");

        } else if (event.getSource() == AddMateriel_Btn) {
            home_Form.setVisible(false);
            home_Form.setManaged(false);
            Material_Add_Form.setVisible(true);
            Material_Add_Form.setManaged(true);
            Form_categories.setVisible(false);
            Form_categories.setManaged(false);
            Maintenance_Form.setVisible(false);
            Maintenance_Form.setManaged(false);
            System.out.println("Formulaire de materiel appuye");

        } else if (event.getSource() == addCategories) {
            home_Form.setVisible(false);
            home_Form.setManaged(false);
            Material_Add_Form.setVisible(false);
            Material_Add_Form.setManaged(false);
            Form_categories.setVisible(true);
            Form_categories.setManaged(true);
            System.out.println("categorie  trouve");
            Maintenance_Form.setVisible(false);
            Maintenance_Form.setManaged(false);
            totalCategories();

            categoriesData();


        } else if (event.getSource() == AddMaintenances_Btn) {
            home_Form.setVisible(false);
            home_Form.setManaged(false);
            Material_Add_Form.setVisible(false);
            Maintenance_Form.setManaged(false);
            Form_categories.setVisible(false);
            Form_categories.setManaged(false);
            Maintenance_Form.setVisible(true);
            Maintenance_Form.setManaged(true);

            comboCategorie.setItems(getCategories());
            ComboNumMateriel.setItems(getMaterials());


            System.out.println(" Maintenance  trouve");
        }


    }

//    Ici on va avoir le nombre total de materiel
    public void  totalMaterial(){
        String sql = " SELECT COUNT(id) FROM material ";
        connection = DBConfig.getConnection();
        int TotalEnrolle = 0;
        try{

            preparedStatement =  connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                TotalEnrolle = resultSet.getInt("COUNT(id)");
            }
            TotalEnrolled.setText(String.valueOf(TotalEnrolle));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private Material materialService = new Material();
    public void AjouterMateriel() throws IOException {
    System.out.println("Début de l'ajout de matériel.");

    // Récupération des valeurs des champs
    String materialNumber = textfieldMaterialNumber.getText().trim();
    String name = textfieldMateriamName.getText().trim();
    String marque = TextfieldMaterialMarque.getText().trim();
    String etat = textfieldMaterial_state.getText().trim();
    String locale = textefieldLocate.getText().trim();
    String category = (String) BoxCategorie.getSelectionModel().getSelectedItem();
    LocalDate date = addDate.getValue();
    String utilisateur = textfield_Material_User.getText().trim();
    String statut = comboStatut.getSelectionModel().getSelectedItem();

    System.out.println("Valeurs récupérées : " +
            "MaterialNumber=" + materialNumber + ", Name=" + name + ", Marque=" + marque +
            ", Etat=" + etat + ", Local=" + locale + ", Category=" + category +
            ", Date=" + date + ", Utilisateur=" + utilisateur + ", Statut=" + statut);

    // Vérification que tous les champs sont remplis
    if (!materialNumber.isEmpty() && !name.isEmpty() && !marque.isEmpty() && !etat.isEmpty() && !locale.isEmpty()
            && category != null && !utilisateur.isEmpty() && statut != null && date != null) {

        try {
            // Vérification si le numéro de matériel est bien un entier
            int materialNum;
            try {
                materialNum = Integer.parseInt(materialNumber);
                System.out.println("Numéro de matériel converti en entier : " + materialNum);
            } catch (NumberFormatException e) {
                System.err.println("Erreur : Le numéro de matériel doit être un entier.");
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Le numéro de matériel doit être un entier.");
                alert.showAndWait();
                return;
            }

            // Vérification si le numéro de matériel existe déjà
            boolean checkMaterialNumber = checkMaterialNumber(materialNum);
            System.out.println("Vérification si le matériel existe déjà : " + checkMaterialNumber);

            if (checkMaterialNumber) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Le matériel existe déjà.");
                alert.showAndWait();
                ViderChamps();
            } else {
                Material material = new Material();
                material.setMaterialNumber(materialNum);
                material.setName(name);
                material.setMarque(marque);
                material.setEtat(etat);
                material.setLocale(locale);
                material.setCategory(category);
                material.setDate(Date.valueOf(date));
                material.setUtilisateur(utilisateur);
                material.setStatut(statut);
                material.setUserId(SessionManager.getCurrentUserId());

                System.out.println("Création de l'objet Material : " + material);

                try {
//                    material.register(material);// On passe le matériel pour l'enregistrement
                    materialService.register(material);

                    System.out.println("Matériel enregistré avec succès.");
                    materialShowData();
                    ViderChamps();
                    updatePieChart();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Succès");
                    alert.setHeaderText(null);
                    alert.setContentText("Matériel ajouté avec succès !");
                    alert.showAndWait();
                } catch (SQLException e) {
                    System.err.println("Erreur lors de l'ajout du matériel : " + e.getMessage());
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Une erreur est survenue lors de l'ajout du matériel.");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur inattendue : " + e.getMessage());
            e.printStackTrace();
        }
    } else {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs !");
        alert.showAndWait();
    }

    System.out.println("Fin de l'ajout de matériel.");
}
     public ObservableList<Material> materialListData(int userId) {
        ObservableList<Material> listData = FXCollections.observableArrayList();
        String selectData;

        // Vérifie si l'utilisateur est admin
        if (isAdmin(userId)) {
            selectData = "SELECT * FROM material"; // Tous les matériaux pour l'admin
        } else {
            selectData = "SELECT * FROM material WHERE userId = ?"; // Filtrer par userId
        }

        try (Connection connection = DBConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectData)) {

            // Définir l'ID de l'utilisateur si ce n'est pas un admin
            if (!isAdmin(userId)) {
                preparedStatement.setInt(1, userId);
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Material sData = new Material(
                            resultSet.getInt("numeroMateriel"),
                            resultSet.getString("nom"),
                            resultSet.getString("marque"),
                            resultSet.getString("etat"),
                            resultSet.getString("local"),
                            resultSet.getString("categorie"),
                            resultSet.getDate("date"),
                            resultSet.getString("utilisateur"),
                            resultSet.getString("statut")
                    );
                    listData.add(sData);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Ou afficher une alerte
        }

        return listData;
    }
    public boolean isAdmin(int userId) {
        // Exemple d'accès à la base de données pour vérifier le rôle
        String query = "SELECT role FROM users WHERE userId = ?";
        try (Connection connection = DBConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String role = resultSet.getString("role");
                    return "admin".equalsIgnoreCase(role); // Vérifie si le rôle est admin
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Par défaut, l'utilisateur n'est pas admin
    }

    //    Check if the materialNumber exists before add the material
    public boolean checkMaterialNumber(int numeroMateriel) throws SQLException {
        boolean exists = false;


        String query = "SELECT numeroMateriel FROM material WHERE numeroMateriel = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, numeroMateriel);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                exists = resultSet.getInt(1) > 0;  // Vérifie si le matériel existe
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return exists;
    }
//    public void MettreAjour() throws IOException {
//        String materialNumber = textfieldMaterialNumber.getText().trim();
//        String name = textfieldMateriamName.getText().trim();
//        String marque = TextfieldMaterialMarque.getText().trim();
//        String etat = textfieldMaterial_state.getText().trim();
//        String locale = textefieldLocate.getText().trim();
//        String category = (String) BoxCategorie.getSelectionModel().getSelectedItem();
//        LocalDate date = addDate.getValue();
//        String utilisateur = textfield_Material_User.getText().trim();
//        String statut = comboStatut.getSelectionModel().getSelectedItem();
//
//        // Vérification que tous les champs sont remplis
//        if (materialNumber.isEmpty() || name.isEmpty() || marque.isEmpty() || etat.isEmpty() || locale.isEmpty()
//                || category == null || utilisateur.isEmpty() || statut == null || date == null) {
//            alert = new Alert(Alert.AlertType.ERROR);
//            alert.setHeaderText(null);
//            alert.setContentText("Remplir tous les champs");
//            alert.showAndWait();
//            return;
//        }
//
//        // Vérification du rôle de l'utilisateur
//        String currentUserRole = SessionManager.getCurrentUserRole();
//        if (!"admin".equals(currentUserRole)) {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Accès refusé");
//            alert.setHeaderText(null);
//            alert.setContentText("Vous n'avez pas les droits nécessaires pour modifier un matériel.");
//            alert.showAndWait();
//            return; // Sortir de la méthode si l'utilisateur n'est pas admin
//        }
//
//        alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Êtes-vous sûr de vouloir modifier ?");
//        alert.setHeaderText(null);
//        alert.setContentText("Êtes-vous sûr de vouloir modifier " + materialNumber + "?");
//        Optional<ButtonType> option = alert.showAndWait();
//        if (option.isPresent() && option.get().equals(ButtonType.OK)) {
//            try {
//                int materialNumberInt = Integer.parseInt(materialNumber);
//                Material material = new Material(materialNumberInt, name, marque, etat, locale, category, Date.valueOf(date), utilisateur, statut);
//                boolean success = material.updateMaterial(material);
//
//                if (success) {
//                    alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setHeaderText(null);
//                    alert.setContentText("Mise à jour réussie");
//                    alert.showAndWait();
//                    materialShowData();
//                    ViderChamps();
//                    updatePieChart();
//                } else {
//                    alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setHeaderText(null);
//                    alert.setContentText("Échec de la mise à jour");
//                    alert.showAndWait();
//                    materialShowData();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }


    public void MettreAjour() throws IOException {
        String materialNumber = textfieldMaterialNumber.getText().trim();
        String name = textfieldMateriamName.getText().trim();
        String marque = TextfieldMaterialMarque.getText().trim();
        String etat = textfieldMaterial_state.getText().trim();
        String locale = textefieldLocate.getText().trim();
        String category = (String) BoxCategorie.getSelectionModel().getSelectedItem();
        LocalDate date = addDate.getValue();
        String utilisateur = textfield_Material_User.getText().trim();
        String statut = comboStatut.getSelectionModel().getSelectedItem();

        // Vérification que tous les champs sont remplis
        if (materialNumber.isEmpty() || name.isEmpty() || marque.isEmpty() || etat.isEmpty() || locale.isEmpty()
                || category == null || utilisateur.isEmpty() || statut == null || date == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Remplir tous les champs");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Êtes-vous sûr de vouloir modifier ?");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir modifier " + materialNumber + " ?");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.isPresent() && option.get().equals(ButtonType.OK)) {
            try {
                int materialNumberInt = Integer.parseInt(materialNumber);
                Material material = new Material(materialNumberInt, name, marque, etat, locale, category, Date.valueOf(date), utilisateur, statut);
                boolean success = material.updateMaterial(material);

                if (success) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Mise à jour réussie");
                    alert.showAndWait();
                    materialShowData();
                    ViderChamps();
                    updatePieChart();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Échec de la mise à jour");
                    alert.showAndWait();
                    materialShowData();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Une erreur s'est produite lors de la mise à jour.");
                errorAlert.showAndWait();
            }
        }
    }

    public void handleDeleteMaterial(ActionEvent event) {
        String materialNumberText = textfieldMaterialNumber.getText().trim();

        if (materialNumberText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de validation");
            alert.setContentText("Le numéro de matériel est requis.");
            alert.showAndWait();
            return;
        }

        // Vérification du rôle de l'utilisateur
        String currentUserRole = SessionManager.getCurrentUserRole();
        if (!"admin".equals(currentUserRole)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Accès refusé");
            alert.setHeaderText(null);
            alert.setContentText("Vous n'avez pas les droits nécessaires pour supprimer un matériel.");
            alert.showAndWait();
            return; // Sortir de la méthode si l'utilisateur n'est pas admin
        }

        try {
            int materialNumber = Integer.parseInt(materialNumberText);
            Material material = new Material();
            material.setMaterialNumber(materialNumber);

            boolean success = material.DeleteMaterial(material);
            materialShowData();
            updatePieChart();

            if (success) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès de la suppression");
                alert.setHeaderText(null);
                alert.setContentText("Le matériel avec le numéro " + materialNumber + " a été supprimé avec succès.");
                alert.showAndWait();
                ViderChamps();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de suppression");
                alert.setHeaderText(null);
                alert.setContentText("Le matériel avec le numéro " + materialNumber + " n'a pas été trouvé.");
                alert.showAndWait();
                ViderChamps();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de format");
            alert.setHeaderText(null);
            alert.setContentText("Le numéro de matériel doit être un nombre valide.");
            alert.showAndWait();
            ViderChamps();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur SQL");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de la suppression.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }
    public void materialShowData() {
        int currentUserId = SessionManager.getCurrentUserId(); // Récupère l'ID de l'utilisateur connecté
        materialData = materialListData(currentUserId); // Passe l'ID à la méthode

        col_material.setCellValueFactory(new PropertyValueFactory<>("materialNumber"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_marque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        col_etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        col_local.setCellValueFactory(new PropertyValueFactory<>("locale"));
        col_category.setCellValueFactory(new PropertyValueFactory<>("category"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_utilisateur.setCellValueFactory(new PropertyValueFactory<>("utilisateur"));
        colstatut.setCellValueFactory(new PropertyValueFactory<>("statut"));

        Material_tableView.setItems(materialData);
    }
   public void materialSelectData() {
        Material sData = Material_tableView.getSelectionModel().getSelectedItem();
        if (sData != null) {
            textfieldMaterialNumber.setText(String.valueOf(sData.getMaterialNumber()));
            textfieldMateriamName.setText(sData.getName());
            TextfieldMaterialMarque.setText(sData.getMarque());
            textfieldMaterial_state.setText(sData.getEtat());

            BoxCategorie.setValue(sData.getCategory());
            textefieldLocate.setText(sData.getLocale());

            textfield_Material_User.setText(sData.getUtilisateur());
            comboStatut.setValue(sData.getStatut());

        }
    }
    public void displayAllMaterials() {
        if (SessionManager.getCurrentUserRole() != null && SessionManager.getCurrentUserRole().equals("admin")) {
            try {
                List<Material> materials =
                        materialService.getAllMaterials();
                // Remplir le TableView avec les matériaux
                Material_tableView.getItems().setAll(materials);
                System.out.println("Rôle de l'utilisateur actuel : " + SessionManager.getCurrentUserRole());
            } catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Une erreur est survenue lors de la récupération des matériaux.");
            }
        } else {
            // Alerte pour l'accès refusé
            showAlert(Alert.AlertType.WARNING, "Accès refusé", "Vous n'avez pas les droits nécessaires pour voir tous les matériaux.");
            return; // Assurez-vous de retourner ici pour éviter de poursuivre l'exécution
        }
    }
    //      Delete material
    public void homeDisplayBarChart() {
        GraphiqueMateriel.getData().clear(); // Nettoyer le graphique
        String sql = "SELECT date, statut, COUNT(id) FROM material GROUP BY date, statut ORDER BY TIMESTAMP(date) ASC LIMIT 5";

        try (Connection connection = DBConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            Map<String, XYChart.Series<String, Number>> seriesMap = new HashMap<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy"); // Format de date

            while (resultSet.next()) {
                String dateRaw = resultSet.getString(1);
                String statut = resultSet.getString(2);
                int count = resultSet.getInt(3);

                LocalDate date = LocalDate.parse(dateRaw); // Convertir en LocalDate
                String formattedDate = date.format(formatter); // Reformater en une chaîne plus lisible

                // Créer une série pour le statut si elle n'existe pas
                seriesMap.putIfAbsent(statut, new XYChart.Series<>());
                seriesMap.get(statut).setName(statut); // Définir le nom de la série

                // Ajouter les données à la série
                seriesMap.get(statut).getData().add(new XYChart.Data<>(formattedDate, count));
            }

            // Ajouter toutes les séries au graphique
            GraphiqueMateriel.getData().addAll(seriesMap.values());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String[] statutListHome = {"Disponible", "Retire", "Maintenance", "Non-disponible"};
    public  void statutListHome(){
        List<String> statutL =  new ArrayList<>();
        for(String data : statutListHome){
            statutL.add(data);
        }
        ObservableList<String> Oblist = FXCollections.observableArrayList(statutL);
        comboStatut.setItems(Oblist);
        updatePieChart();
    }
    private void updatePieChart() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Disponible", getStatutCount("Disponible")),
                new PieChart.Data("Non-disponible", getStatutCount("Non-disponible")),
                new PieChart.Data("Maintenance", getStatutCount("Maintenance")),
                new PieChart.Data("Retire", getStatutCount("Retire"))
        );

        // Vérifie si tous les statuts ont des données
        if (pieChartData.stream().mapToDouble(PieChart.Data::getPieValue).sum() == 0) {
            pieChartData.add(new PieChart.Data("Aucune donnée", 1)); // Ajoute une section "Aucune donnée" si tout est zéro
        }

        // Met à jour le PieChart avec les nouvelles données
        PieChartMateriel.setData(pieChartData);
    }
    private int getStatutCount(String statut) {
        int count = 0;
        String sql = "SELECT COUNT(id) FROM material WHERE statut = ?";

        try (Connection connection = DBConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, statut);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt(1); // Récupérer le nombre d'enregistrements
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }
    public  void ViderChamps(){
    textfieldMaterialNumber.setText("");
    textfieldMateriamName.setText("");
    TextfieldMaterialMarque.setText("");
    textfieldMaterial_state.setText("");
    BoxCategorie.getSelectionModel().clearSelection();
    textefieldLocate.setText("");
    addDate.setValue(null);
    textfield_Material_User.setText("");
    comboStatut.getSelectionModel().clearSelection();
}


  //Logiques De Gestion des Categories
 // Méthode pour récupérer la liste des catégories depuis la base de données / Method to retrieve the list of categories from the database
   public ObservableList<Category> CategoriesListData() {
    ObservableList<Category> listData = FXCollections.observableArrayList();
    String selectData = "SELECT * FROM category";
    connection = DBConfig.getConnection(); // Établir la connexion à la base de données / Establish the connection to the database
    try {
        Category courseC; // Variable pour stocker chaque catégorie / Variable to store each category

        preparedStatement = connection.prepareStatement(selectData);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            // Créer un nouvel objet Category à partir des données récupérées / Create a new Category object from the retrieved data
            courseC = new Category(resultSet.getString("NomCategory"),
                    resultSet.getString("Description"),
                    resultSet.getString("Etat"));
            listData.add(courseC); // Ajouter la catégorie à la liste / Add the category to the list
        }

    } catch (Exception e) {
        e.printStackTrace(); // Afficher les erreurs / Print errors
    }
    return listData; // Retourner la liste des catégories / Return the list of categories
}

    // Méthode pour ajouter les catégories à un ComboBox / Method to add categories to a ComboBox
    public void addCategoryList() {
        String listCategory = "SELECT * FROM category";
        connection = DBConfig.getConnection(); // Établir la connexion à la base de données / Establish the connection to the database
        ObservableList<String> courseG = FXCollections.observableArrayList(); // Liste pour stocker les noms de catégories / List to store category names
        try {
            preparedStatement = connection.prepareStatement(listCategory);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                courseG.add(resultSet.getString("NomCategory")); // Ajouter chaque nom de catégorie à la liste / Add each category name to the list
            }
            // La réception des catégories créées par le ComboBox / The reception of categories created by the ComboBox
            BoxCategorie.setItems(courseG); // Définir les items du ComboBox / Set the items of the ComboBox

        } catch (Exception e) {
            e.printStackTrace(); // Afficher les erreurs / Print errors
        } finally {
            // Fermer les ressources pour éviter les fuites / Close resources to prevent leaks
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Afficher les erreurs / Print errors
            }
        }
    }

    // Méthode pour vérifier si une catégorie existe déjà / Method to check if a category already exists
    public boolean checkCategoryExists(String NomCategory) throws SQLException {
        boolean exists = false; // Variable pour vérifier l'existence / Variable to check existence

        String query = "SELECT NomCategory FROM category WHERE NomCategory = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, NomCategory); // Définir le paramètre de requête / Set the query parameter
            ResultSet resultSet = statement.executeQuery();

            // Si un résultat est retourné, cela signifie que la catégorie existe / If a result is returned, it means the category exists
            if (resultSet.next()) {
                exists = true; // La catégorie existe / The category exists
            }
        } catch (Exception e) {
            e.printStackTrace(); // Afficher les erreurs / Print errors
        }

        return exists; // Retourner le résultat de la vérification / Return the result of the check
    }
    // Méthode pour afficher les données des catégories dans une TableView / Method to display category data in a TableView
    public void categoriesData() {
        categoriesData = CategoriesListData(); // Récupérer les données des catégories / Retrieve category data
        col_categoryName.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        col_description_categorie.setCellValueFactory(new PropertyValueFactory<>("categoryDescription"));
        col_etat_categorie.setCellValueFactory(new PropertyValueFactory<>("categoryState"));

        categorie_tableView.setItems(categoriesData); // Définir les items de la TableView / Set the items of the TableView
    }
    // Méthode pour ajouter une nouvelle catégorie / Method to add a new category
    // Méthode pour sélectionner une catégorie dans la TableView / Method to select a category in the TableView
    public void CategorySelectData() {
        Category courseC = categorie_tableView.getSelectionModel().getSelectedItem(); // Obtenir l'élément sélectionné / Get the selected item
        if (courseC != null) {
            // Remplir les champs avec les données de la catégorie sélectionnée / Fill the fields with the selected category data
            textfield_categorie_name.setText(courseC.getCategoryName());
            description_categorie.setText(courseC.getCategoryDescription());
            etat_categorie.setText(courseC.getCategoryState());
        }
    }
    // Méthode pour vider les champs de saisie / Method to clear the input fields
    public void ViderChampsCategory() {
        textfield_categorie_name.setText(""); // Vider le champ du nom / Clear the name field
        etat_categorie.setText(""); // Vider le champ de l'état / Clear the state field
        description_categorie.setText(""); // Vider le champ de la description / Clear the description field
    }
    // Méthode pour obtenir les données disponibles d'une catégorie / Method to get available category data
    public void availableCategory() {
        Category courseC = categorie_tableView.getSelectionModel().getSelectedItem(); // Obtenir l'élément sélectionné / Get the selected item
        int num = categorie_tableView.getSelectionModel().getSelectedIndex();

        if (num < 0) {
            return; // Sortir si aucune sélection / Exit if no selection
        }
        // Remplir les champs avec les données de la catégorie sélectionnée / Fill the fields with the selected category data
        textfield_categorie_name.setText(courseC.getCategoryName());
        description_categorie.setText(courseC.getCategoryDescription());
        etat_categorie.setText(courseC.getCategoryState());
    }
    public void ajouterCategory() throws IOException {
        // Vérification du rôle de l'utilisateur
        String currentUserRole = SessionManager.getCurrentUserRole();
        if (!"admin".equals(currentUserRole)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Accès refusé");
            alert.setHeaderText(null);
            alert.setContentText("Vous n'avez pas les droits nécessaires pour créer une catégorie.");
            alert.showAndWait();
            return; // Sortir de la méthode si l'utilisateur n'est pas admin
        }

        String categoryName = textfield_categorie_name.getText().trim();
        String categoryDescription = description_categorie.getText().trim();
        String categoryState = etat_categorie.getText().trim();

        // Vérifier si les champs sont vides
        if (categoryName.isEmpty() || categoryDescription.isEmpty() || categoryState.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs requis.");
            alert.showAndWait();
            return; // Sortir de la méthode si les champs sont vides
        }

        try {
            // Vérifier si la catégorie existe déjà
            if (checkCategoryExists(categoryName)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("La catégorie existe déjà.");
                alert.showAndWait();
                ViderChampsCategory(); // Vider les champs
                return; // Sortir de la méthode
            }

            Category category = new Category(categoryName, categoryDescription, categoryState);
            category.register(category); // Enregistrer la catégorie
            categoriesData(); // Actualiser les données de la TableView
            addCategoryList(); // Actualiser le ComboBox
            totalCategories();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Catégorie créée.");
            alert.showAndWait();

            ViderChampsCategory(); // Vider les champs après l'ajout

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Catégorie non créée.");
            alert.showAndWait();
        }
    }
    // Méthode pour mettre à jour une catégorie / Method to update a category
    public void MettreAjourCategorie() throws IOException {
        // Vérification du rôle de l'utilisateur
        String currentUserRole = SessionManager.getCurrentUserRole();
        if (!"admin".equals(currentUserRole)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Accès refusé");
            alert.setHeaderText(null);
            alert.setContentText("Vous n'avez pas les droits nécessaires pour modifier une catégorie.");
            alert.showAndWait();
            return; // Sortir de la méthode si l'utilisateur n'est pas admin
        }

        String categoryName = textfield_categorie_name.getText().trim();
        String categoryDescription = description_categorie.getText().trim();
        String categoryState = etat_categorie.getText().trim();

        // Vérifier si les champs sont vides
        if (categoryName.isEmpty() || categoryDescription.isEmpty() || categoryState.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir modifier " + categoryName + "?");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.isPresent() && option.get().equals(ButtonType.OK)) {
            try {
                connection = DBConfig.getConnection();
                Category category = new Category(categoryName, categoryDescription, categoryState);
                boolean success = category.updateCategory(category); // Mettre à jour la catégorie

                if (success) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Mise à jour effectuée.");
                    alert.showAndWait();
                    ViderChampsCategory(); // Vider les champs
                    categoriesData(); // Actualiser les données de la TableView
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Échec de la mise à jour.");
                    alert.showAndWait();
                    ViderChampsCategory();
                    categoriesData();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Une erreur est survenue lors de la mise à jour.");
                errorAlert.showAndWait();
            }
        }
    }
    // Méthode pour supprimer une catégorie / Method to delete a category
    public void SupprimerCategory() throws IOException {
        // Vérification du rôle de l'utilisateur
        String currentUserRole = SessionManager.getCurrentUserRole();
        if (!"admin".equals(currentUserRole)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Accès refusé");
            alert.setHeaderText(null);
            alert.setContentText("Vous n'avez pas les droits nécessaires pour supprimer une catégorie.");
            alert.showAndWait();
            return; // Sortir de la méthode si l'utilisateur n'est pas admin
        }

        String CategoryName = textfield_categorie_name.getText().trim();
        String CategoryDescription = description_categorie.getText().trim();
        String CategoryState = etat_categorie.getText().trim();

        // Vérifier si le nom de la catégorie est vide
        if (CategoryName.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de validation");
            alert.setContentText("Le nom de la catégorie est obligatoire.");
            alert.showAndWait();
            return;
        }

        try {
            connection = DBConfig.getConnection();
            Category category = new Category(CategoryName, CategoryDescription, CategoryState);
            boolean success = category.DeleteCategory(category); // Supprimer la catégorie

            if (success) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setContentText("La catégorie a été supprimée avec succès.");
                alert.showAndWait();
                ViderChampsCategory(); // Vider les champs
                categoriesData(); // Actualiser les données de la TableView
                totalCategories();
                addCategoryList();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Échec");
                alert.setContentText("La suppression de la catégorie a échoué.");
                alert.showAndWait();
                ViderChampsCategory();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Une erreur est survenue lors de la suppression.");
            alert.showAndWait();
        }
    }
    // Méthode pour compter le nombre total de catégories / Method to count the total number of categories
    public void totalCategories() {
        String sql = " SELECT COUNT(id) FROM category ";
        connection = DBConfig.getConnection(); // Établir la connexion à la base de données / Establish the connection to the database
        int TotalEnrolle = 0; // Compteur pour le total / Counter for the total
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                TotalEnrolle = resultSet.getInt("COUNT(id)"); // Récupérer le total / Retrieve the total
            }
            TotalDisponibleCategory.setText(String.valueOf(TotalEnrolle)); // Afficher le total / Display the total
        } catch (Exception e) {
            e.printStackTrace(); // Afficher les erreurs / Print errors
        }
    }
    //    Methodes De Gestion Des maintenaces
    // Méthode pour créer la liste des statuts / Method to create the status list
    public void statutList() {
        List<String> statutL = new ArrayList<>();
        for (String data : statutList) {
            statutL.add(data); // Ajouter chaque statut à la liste / Add each status to the list
        }
        ObservableList ObList = FXCollections.observableArrayList(statutL); // Créer une ObservableList à partir de la liste / Create an ObservableList from the list
        Combo_maintenance.setItems(ObList); // Définir les items du ComboBox / Set the items of the ComboBox
    }

    private String[] statutList = {"Preventive", "Corrective", "Conditionnelle", "Améliorative"};
    // Méthode pour vider les champs de maintenance / Method to clear maintenance fields
    public  void clearMaintenace(){
        ComboNumMateriel.getSelectionModel().clearSelection();
        comboCategorie.getSelectionModel().clearSelection();
        chargeMaintenace_textfield.setText("");
        probleme_materiel.setText("");
        DateMaintenance.setValue(null);
        Combo_maintenance.getSelectionModel().clearSelection();
    }
    // Méthode pour ajouter une maintenance / Method to add maintenance
    public void addMaintenance() throws IOException {
        try {
            // Récupérer les données du ComboBox et des champs texte
            String selectedMateriel = String.valueOf(ComboNumMateriel.getSelectionModel().getSelectedItem());
            String selectedCategorie = comboCategorie.getSelectionModel().getSelectedItem();
            String charge = chargeMaintenace_textfield.getText().trim();
            String probleme = probleme_materiel.getText().trim();
            String selectedStatut = (String) Combo_maintenance.getSelectionModel().getSelectedItem();
            Date date = Date.valueOf(DateMaintenance.getValue()); // Assurez-vous que DateMaintenance est un LocalDate

            // ici je veux voir la valeur selctionne
            System.out.println("selectedMateriel: " + selectedMateriel);
            System.out.println("selectedCategorie: " + selectedCategorie);
            System.out.println("charge: " + charge);
            System.out.println("probleme: " + probleme);
            System.out.println("localDate: " + date);
            System.out.println("selectedStatut: " + selectedStatut);

            // Vérification des champs obligatoires
            if (selectedMateriel == null || selectedMateriel.trim().isEmpty() ||
                    selectedCategorie == null || selectedCategorie.trim().isEmpty() ||
                    charge.isEmpty() || probleme.isEmpty() ||
                    date == null || selectedStatut == null || selectedStatut.trim().isEmpty()) {

                showAlert("Avertissement", "Veuillez remplir tous les champs obligatoires.", Alert.AlertType.WARNING);
                return;
            }

            // Conversion du numéro de matériel
            Integer numeroMateriel;
            try {
                numeroMateriel = Integer.valueOf(selectedMateriel);
            } catch (NumberFormatException e) {
                showAlert("Erreur", "Le numéro de matériel doit être un nombre valide.", Alert.AlertType.ERROR);
                return;  // Stopper l'exécution si la conversion échoue
            }

            // Créer une instance de maintenance et l'enregistrer
            Maintenance maintenance = new Maintenance(numeroMateriel, selectedCategorie, charge, probleme, date, selectedStatut);
            maintenance.register(maintenance);
            maintenanceData();
            clearMaintenace();
            totalMaintenaces();



            // Afficher un message de succès
            showAlert("Succès", "Maintenance ajoutée avec succès !", Alert.AlertType.INFORMATION);

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erreur", "Une erreur inattendue est survenue : " + e.getMessage(), Alert.AlertType.ERROR);
}
    }

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    public void maintenanceShowData() {
        maintenancesData = maintenanceData();// Récupérer les données de maintenance / Retrieve maintenance data

        MaintenaceNumber.setCellValueFactory(new PropertyValueFactory<>("numeroMateriel"));
        col_charge.setCellValueFactory(new PropertyValueFactory<>("charge"));
        col_probleme.setCellValueFactory(new PropertyValueFactory<>("probleme"));
        col_dateMaintenance.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_statutMaintenance.setCellValueFactory(new PropertyValueFactory<>("statut"));
        col_MaintenanceCombo.setCellValueFactory(new PropertyValueFactory<>("categorie"));

        Maintenance_tableView.setItems(maintenancesData);
    }
   // Méthode pour récupérer les données de maintenance / Method to retrieve maintenance data
    public ObservableList<Maintenance> maintenanceData() {
        ObservableList<Maintenance> listData = FXCollections.observableArrayList(); // Créer une ObservableList pour les données de maintenance / Create an ObservableList for maintenance data
        String selectData = "SELECT * FROM maintenances";  // Requête pour récupérer les données / Query to retrieve data
        connection = DBConfig.getConnection(); // Établir la connexion à la base de données / Establish connection to the database

        try {
            preparedStatement = connection.prepareStatement(selectData);
            resultSet = preparedStatement.executeQuery();  // Exécuter la requête / Execute the query

            while (resultSet.next()) {
                Maintenance sData = new Maintenance(
//                        resultSet.getInt("id"),
                        resultSet.getInt("numeroMateriel"),
                       resultSet.getString("categorie"),
                        resultSet.getString("probleme"),
                        resultSet.getString("charge"),
                        resultSet.getDate("date"),
                        resultSet.getString("statut")


                        );
//                listData.add(sData);
                listData.add(sData);
            }
            Maintenance_tableView.setItems(listData); // Définir les items de la TableView / Set items of the TableView

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;

    }
    public void maintenanceSelectData() {
        Maintenance sData = Maintenance_tableView.getSelectionModel().getSelectedItem(); // Obtenir l'élément sélectionné / Get the selected item
        if (sData != null) {
            ComboNumMateriel.setValue(sData.getNumeroMateriel());
            // Vérifie si la catégorie existe dans le ComboBox avant de la définir
            if (BoxCategorie.getItems().contains(sData.getCategorie())) {
                BoxCategorie.setValue(sData.getCategorie());
            }
            chargeMaintenace_textfield.setText(sData.getCharge());
            probleme_materiel.setText(sData.getProbleme());
            col_statutMaintenance.setText(sData.getStatut());


}
    }

    public ObservableList<Integer> getMaterials() {
        // Méthode pour récupérer les matériaux / Method to retrieve materials
        ObservableList<Integer> materialsList = FXCollections.observableArrayList();
        String query = "SELECT numeroMateriel FROM material";  // Remplace 'material' par le nom correct de ta table.

        try (Connection connection = DBConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                materialsList.add(resultSet.getInt("numeroMateriel")); // Ajouter chaque numéro à la liste / Add each number to the list
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materialsList;
    }

    public ObservableList<String> getCategories() {
        ObservableList<String> categoriesList = FXCollections.observableArrayList();
        String query = "SELECT nomCategory FROM category";  // Remplace 'categorie' par le nom correct de ta table.

        try (Connection connection = DBConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                categoriesList.add(resultSet.getString("nomCategory"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoriesList;
    }
    public void MaintenanceUpdate() throws IOException {
        Integer numeroMateriel = ComboNumMateriel.getSelectionModel().getSelectedItem();
        String comboBoxMaintenance = comboCategorie.getSelectionModel().getSelectedItem();
        String probleme = probleme_materiel.getText().trim();
        String charge = chargeMaintenace_textfield.getText().trim();
        LocalDate date = DateMaintenance.getValue();
        String statut = (String) Combo_maintenance.getSelectionModel().getSelectedItem();

        // Valider les inputs
        if (numeroMateriel == null || comboBoxMaintenance == null || probleme.isEmpty() || charge.isEmpty() || date == null || statut == null || statut.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Veuillez remplir tous les champs obligatoires.");
            return;
        }

        // Confirmation avant modification
        Optional<ButtonType> option = showConfirmation("Confirmation", "Êtes-vous sûr de vouloir modifier la maintenance pour " + numeroMateriel + "?");
        if (option.isPresent() && option.get() == ButtonType.OK) {
            try {
                // Créer une instance de Maintenance
                Maintenance maintenance = new Maintenance(numeroMateriel, comboBoxMaintenance, probleme, charge, Date.valueOf(date), statut);
                boolean success = maintenance.updateMaintenace(maintenance);

                if (success) {
                    showAlert(Alert.AlertType.INFORMATION, "Succès", "Mise à jour réussie.");
                    maintenanceData();
                    clearMaintenace();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Erreur", "Échec de la mise à jour de la maintenance.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Erreur SQL", "Une erreur s'est produite lors de la mise à jour de la maintenance : " + e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Erreur", "Une erreur inattendue s'est produite : " + e.getMessage());
            }
        }
    }

    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private Optional<ButtonType> showConfirmation(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert.showAndWait();
    }

    public void deleteMaintenance(ActionEvent event) {
        // Vérification des droits d'accès
        if (!isAdmin(SessionManager.getCurrentUserId())) {
            showAlert(Alert.AlertType.ERROR, "Accès refusé", "Vous n'avez pas les droits nécessaires pour supprimer une maintenance.");
            return;
        }

        // Récupération des données des champs
        Integer numeroMateriel = ComboNumMateriel.getSelectionModel().getSelectedItem();
        String categorie = comboCategorie.getSelectionModel().getSelectedItem();
        String charge = chargeMaintenace_textfield.getText().trim();
        String probleme = probleme_materiel.getText().trim();
        LocalDate localDate = DateMaintenance.getValue(); // Utilise LocalDate directement
        String statut = (String) Combo_maintenance.getSelectionModel().getSelectedItem();

        // Vérification si le numéro de matériel est sélectionné
        if (numeroMateriel == null) {
            showAlert(Alert.AlertType.ERROR, "Erreur de validation", "Le numéro de matériel est requis.");
            return;
        }

        // Vérification si la date est sélectionnée
        if (localDate == null) {
            showAlert(Alert.AlertType.ERROR, "Erreur de validation", "La date de maintenance est requise.");
            return;
        }

        // Confirmation de la suppression
        Optional<ButtonType> option = showConfirmation("Confirmation", "Êtes-vous sûr de vouloir supprimer la maintenance du matériel " + numeroMateriel + " ?");
        if (option.isPresent() && option.get() == ButtonType.OK) {
            try {
                Date date = Date.valueOf(localDate); // Conversion de LocalDate à Date si nécessaire

                // Création de l'objet Maintenance avec les données nécessaires
                Maintenance maintenance = new Maintenance(numeroMateriel, categorie, charge, probleme, date, statut);

                // Suppression
                boolean success = maintenance.DeleteMaintenance(maintenance); // Assurez-vous que cette méthode existe

                if (success) {
                    showAlert(Alert.AlertType.INFORMATION, "Succès de la suppression",
                            "La maintenance du matériel " + numeroMateriel + " a été supprimée avec succès.");
                    maintenanceShowData(); // Actualiser l'affichage
                    clearMaintenace(); // Effacer les champs
                    totalMaintenaces(); // Actualiser le total

                } else {
                    showAlert(Alert.AlertType.ERROR, "Erreur de suppression",
                            "La maintenance pour le matériel " + numeroMateriel + " n'a pas été trouvée.");
                    clearMaintenace();
                }
            } catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, "Erreur SQL",
                        "Une erreur s'est produite lors de la suppression.");
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                showAlert(Alert.AlertType.ERROR, "Erreur de date",
                        "La date fournie est invalide.");
                e.printStackTrace();
            }
        }
    }

    public void  totalMaintenaces(){
        String sql = " SELECT COUNT(id) FROM maintenances ";
        connection = DBConfig.getConnection();
        int TotalEnrolle = 0;
        try{

            preparedStatement =  connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                TotalEnrolle = resultSet.getInt("COUNT(id)");
            }
            MaintenancesTotal.setText(String.valueOf(TotalEnrolle));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void logout() {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de vous déconnecter ?");

            Optional<ButtonType> option = alert.showAndWait();
            if (option.isPresent() && option.get() == ButtonType.OK) {
                // Fermer la fenêtre actuelle
                SessionManager.clearSession();
                Stage stage = (Stage) LogOut_Btn.getScene().getWindow();
                stage.close();

                // Charger la vue de connexion
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/material_save/LogIn.fxml")));
                Stage loginStage = new Stage();
                Scene scene = new Scene(root);
                loginStage.setScene(scene);
                loginStage.show();
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la déconnexion : " + e.getMessage());
            e.printStackTrace();
        }
}


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Maintenance_Form.setVisible(false);
        Maintenance_Form.setManaged(false);
        Form_categories.setVisible(false);
        Form_categories.setManaged(false);
        Material_Add_Form.setVisible(false);
        Material_Add_Form.setManaged(false);
        ViderChampsCategory();

        // Appels aux méthodes
        materialShowData();
        homeDisplayBarChart();
        categoriesData();
        CategorySelectData();
        CategoriesListData();
        availableCategory();
        addCategoryList();
        statutList();
        maintenanceShowData();
        maintenanceData();
        clearMaintenace();
        totalMaterial();
        updatePieChart();
        statutListHome();
        totalCategories();
        totalMaintenaces();
        displayAllMaterials();

        // Action pour le ComboBox
        BoxCategorie.setOnAction(event -> {
            String selectedCategory = BoxCategorie.getSelectionModel().getSelectedItem();
            if (selectedCategory != null) {
                System.out.println("Nom de la catégorie sélectionnée: " + selectedCategory);
            }
        });

        // Vérification de l'utilisateur connecté
        String currentUser = SessionManager.getCurrentUsername();
        if (currentUser == null) {
            // Redirige vers la page de connexion
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LogIn.fxml")));
                Stage stage = (Stage) TotalEnrolled.getScene().getWindow(); // Correction ici
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace(); // Afficher la trace d'erreur
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur lors du chargement de la vue de connexion.");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        } else {
            System.out.println("Utilisateur connecté : " + currentUser);
        }
    }
}

