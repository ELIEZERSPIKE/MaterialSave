package com.example.material_save.Controllers;

import com.example.material_save.IDB.DBConfig;
import com.example.material_save.Models.Material;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
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
    private AreaChart<?, ?> GraphDisponible;

    @FXML
    private LineChart<?, ?> GraphNonDispo;

    @FXML
    private BarChart<?, ?> GraphiqueMateriel;

    @FXML
    private Button Home_Btn;

    @FXML
    private Button LogOut_Btn;

    @FXML
    private AnchorPane Main_Form;

    @FXML
    private TableView<?> Maintenance_tableView;


    @FXML
    private Button Maintenance_Update_Btn;

    @FXML
    private Button Maintenance_add_Btn;

    @FXML
    private TextField Maintenance_search;


    @FXML
    private AnchorPane Material_Add_Form;

    @FXML
    private TableView<Material> Material_tableView;

    @FXML
    private Label MaterielIndisponible;

    @FXML
    private TextField TextfieldMaterialMarque;

    @FXML
    private Label TotalDisponible;

    @FXML
    private Label TotalEnrolled;

    @FXML
    private Button addCategorie_Btn;

    @FXML
    private Button addCategories;

    @FXML
    private DatePicker addDate;

    @FXML
    private Button addMaterial;

    @FXML
    private TableView<?> categorie_tableView;

    @FXML
    private TextField charge_materiel;

    @FXML
    private TableColumn<?, ?> col_NumeroMa;

    @FXML
    private TableColumn<?, ?> col_categorie;

    @FXML
    private TableColumn<Material, String> col_category;

    @FXML
    private TableColumn<Material, Date> col_date;

    @FXML
    private TableColumn<Material, Date> col_date_main;

    @FXML
    private TableColumn<?, ?> col_description_categorie;

    @FXML
    private TableColumn<Material, String> col_etat;

    @FXML
    private TableColumn<?, ?> col_etat_categorie;
    @FXML
    private TableColumn<?, ?> col_categoryName;

    @FXML
    private TableColumn<Material, String> col_local;

    @FXML
    private TableColumn<?, ?> col_main_Char;

    @FXML
    private TableColumn<?, ?> col_main_Lo;

    @FXML
    private TableColumn<?, ?> col_main_prob;

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
    private ComboBox<String> BoxCategorie;

    @FXML
    private ComboBox<String> comboStatut;

    @FXML
    private DatePicker date_materiel;

    @FXML
    private Button deleteCategorie_Btn;

    @FXML
    private Button deleteMaterial;

    @FXML
    private TextField description_categorie;

    @FXML
    private TextField etat_categorie;

    @FXML
    private AnchorPane home_Form;

    @FXML
    private TextField local_materiel;

    @FXML
    private TextField numero_materiel;

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
    private TextField dateMaintenace;

    @FXML
    private TextField textFieldMaterialId;

    @FXML
    private TextField chargeMaintenace_textfield;

    @FXML
    private Button updateMaterial;

    @FXML
    private AnchorPane HomeForm;

    private Connection connection;
    private PreparedStatement preparedStatement;

    private Statement statement;

    private ResultSet resultSet;

    private Alert alert;

    private ObservableList<Material> materialData;


    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) Main_Form.getScene().getWindow();
        stage.setIconified(true);
    }

    public void switchForm(ActionEvent event) {
        if (event.getSource() == Home_Btn) {
            home_Form.setVisible(true);

            Material_Add_Form.setVisible(false);

            Form_categories.setVisible(false);

            Maintenance_Form.setVisible(false);
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


        } else if (event.getSource() == AddMaintenances_Btn) {
            home_Form.setVisible(false);
            home_Form.setManaged(false);

            Material_Add_Form.setVisible(false);
            Maintenance_Form.setManaged(false);

            Form_categories.setVisible(false);
            Form_categories.setManaged(false);

            Maintenance_Form.setVisible(true);
            Maintenance_Form.setManaged(true);

            System.out.println(" Maintenance  trouve");
        }


    }


    private final String[] categoriesList = {"Electronique", "Mobilier", "Agricole", "Equipement mobilier", "Equipement agricole", "Equipement mobilier"};

    public void addcategorieList() {
        List<String> categoriesL = new ArrayList<>();
        for (String data : categoriesList) {
            categoriesL.add(data);
        }

        ObservableList Oblist = FXCollections.observableList(categoriesL);
        BoxCategorie.setItems(Oblist);
    }

    private final String[] statutList = {"Disponible", "Non-disponible", "Retire", "En maintenance", "En reserve"};

    public void addstatutList() {
        List<String> statutL = new ArrayList<>();
        for (String data : statutList) {
            statutL.add(data);
        }
        ObservableList Oblist = FXCollections.observableList(statutL);
        comboStatut.setItems(Oblist);
    }

    public ObservableList<Material> materialListData() {
        ObservableList<Material> listData = FXCollections.observableArrayList();
        String selectData = "SELECT * FROM material";
        connection = DBConfig.getConnection();

        try {
            preparedStatement = connection.prepareStatement(selectData);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Material sData = new Material(
//                        resultSet.getInt("id"),
                        resultSet.getInt("numeroMateriel"),
                        resultSet.getString("nom"),
                        resultSet.getString("marque"),
                        resultSet.getString("etat"),
                        resultSet.getString("local"),
                        resultSet.getString("categorie"),
                        resultSet.getDate("date"),
                        resultSet.getString("utilisateur"),
                        resultSet.getString("statut"),
                        resultSet.getString("problem"),
                        resultSet.getString("charge")


                );
                listData.add(sData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;
    }

//    public boolean checkMaterialNumber(int numeroMateriel) throws SQLException {
//        connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//
//        try {
//            // Connexion à la base de données
//            connection = DBConfig.getConnection();
//
//
////            String req = "SELECT numeroMateriel FROM material WHERE numeroMateriel = ? ";
//                String req = "SELECT count(*)FROM material WHERE numeroMateriel = ?  ";
//
//            preparedStatement = connection.prepareStatement(req);
//            preparedStatement.setInt(1, numeroMateriel);
//
//            // Exécution de la requête
//            resultSet = preparedStatement.executeQuery();
//
//            // Si un résultat est trouvé, le matériel existe
//            if (resultSet.next()) {
//                alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Error Message");
//                alert.setHeaderText(null);
//                alert.setContentText("Material Number " + numeroMateriel + " le materiel exist  dejà!");
//                alert.showAndWait();
//                return true;  // Le matériel existe déjà
//            } else {
//                System.out.println("veuillez remplir le formulaire pour ajouter le materiel.");
//                return false;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw e;  // On relance l'exception pour gérer l'erreur à un niveau supérieur
//        } finally {
//
//            if (resultSet != null) {
//                resultSet.close();
//            }
//            if (preparedStatement != null) {
//                preparedStatement.close();
//            }
//            if (connection != null) {
//                connection.close();
//            }
//        }
//    }

    public void AjouterMateriel() throws IOException {

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

        // Vérification que tous les champs sont remplis
        if (!materialNumber.isEmpty() && !name.isEmpty() && !marque.isEmpty() && !etat.isEmpty() && !locale.isEmpty()
                && category != null && !utilisateur.isEmpty() && statut != null && date != null) {

            try {
                // Vérification si le numéro de matériel existe déjà
                boolean checkMaterialNumber = checkMaterialNumber(Integer.parseInt(materialNumber));

                if (checkMaterialNumber) {
                    // Si le matériel existe déjà, afficher une alerte d'erreur
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Le matériel existe déjà");
                    alert.showAndWait();
                } else {
                    // Si le matériel n'existe pas, on peut le créer

                    // Création de l'objet Material et assignation des valeurs
                    Material material = new Material();
                    material.setMaterialNumber(Integer.parseInt(materialNumber));
                    material.setName(name);
                    material.setMarque(marque);
                    material.setEtat(etat);
                    material.setLocale(locale);
                    material.setCategory(category);
                    material.setDate(Date.valueOf(date));
                    material.setUtilisateur(utilisateur);
                    material.setStatut(statut);

                    try {
                        // Enregistrement du matériel en base de données
                        material.register(material);
                        materialShowData();

                        // Afficher un message de confirmation après l'ajout réussi
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Succès");
                        alert.setHeaderText(null);
                        alert.setContentText("Matériel ajouté avec succès !");
                        alert.showAndWait();
                    } catch (SQLException e) {
                        // Gestion des exceptions SQL
                        e.printStackTrace();
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur");
                        alert.setHeaderText(null);
                        alert.setContentText("Une erreur est survenue lors de l'ajout du matériel.");
                        alert.showAndWait();


                    }
                }
            } catch (Exception e) {
                // Gestion des exceptions générales
                e.printStackTrace();
            }
        } else {
            // Afficher un message d'erreur si des champs sont vides
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
        }
    }


    public boolean checkMaterialNumber(int numeroMateriel) throws SQLException {
        boolean exists = false;


        String query = "SELECT numeroMateriel FROM material WHERE numeroMateriel = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, numeroMateriel);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                exists = resultSet.getInt(1) > 0;  // Vérifie si le matériel existe
            }
        }

        return exists;
    }





public void MettreAjour() throws IOException{

    String materialNumber = textfieldMaterialNumber.getText().trim();
    String name = textfieldMateriamName.getText().trim();
    String marque = TextfieldMaterialMarque.getText().trim();
    String etat = textfieldMaterial_state.getText().trim();
    String locale = textefieldLocate.getText().trim();
    String category = (String) BoxCategorie.getSelectionModel().getSelectedItem();
    LocalDate date = addDate.getValue();
    String utilisateur = textfield_Material_User.getText().trim();
    String statut = comboStatut.getSelectionModel().getSelectedItem();

    if(materialNumber.isEmpty() || name.isEmpty() || marque.isEmpty() || etat.isEmpty() || locale.isEmpty()
    || category == null || utilisateur.isEmpty() || statut == null || date == null ){
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Remplir tout les champs");
        alert.showAndWait();
        return;
    }
    alert = new Alert(Alert.AlertType.CONFIRMATION);

    alert.setTitle("sur de modifier ? ");
    alert.setHeaderText(null);
    alert.setContentText("sur de modifier " + materialNumber + "?");
    Optional<ButtonType> option =  alert.showAndWait();
    if (option.get().equals(ButtonType.OK)){
        try{
            connection = DBConfig.getConnection();

            int materialNumberInt = Integer.parseInt(materialNumber);

            Material material = new Material(  materialNumberInt, name, marque, etat,locale, category, Date.valueOf(date),  utilisateur, statut, null, null);
            boolean success = material.updateMaterial(material);

            if (success){
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("mise a jou");
                alert.showAndWait();
                materialShowData();
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("echec");
                alert.showAndWait();
                materialShowData();
            }

        }catch (Exception e){
            e.printStackTrace();
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

        try {
            int materialNumber = Integer.parseInt(materialNumberText);


            Material material = new Material();
            material.setMaterialNumber(materialNumber);


            Material materialService = new Material();
            boolean success = material.DeleteMaterial(material);
            materialShowData();

            if (success) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès de la suppression");
                alert.setHeaderText(null);
                alert.setContentText("Le matériel avec le numéro " + materialNumber + " a été supprimé avec succès.");
                alert.showAndWait();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de suppression");
                alert.setHeaderText(null);
                alert.setContentText("Le matériel avec le numéro " + materialNumber + " n'a pas été trouvé.");
                alert.showAndWait();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de format");
            alert.setHeaderText(null);
            alert.setContentText("Le numéro de matériel doit être un nombre valide.");
            alert.showAndWait();
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
        materialData = materialListData();

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

//            Conversion de Date en localeDate

//            if (sData.getDate() != null ){
//                addDate.setValue(sData.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//            }
            textfield_Material_User.setText(sData.getUtilisateur());
            comboStatut.setValue(sData.getStatut());

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

        addstatutList();
        addcategorieList();
        materialShowData();



    }

}













