package com.example.material_save.Controllers;

import com.example.material_save.IDB.DBConfig;
import com.example.material_save.Models.Category;
import com.example.material_save.Models.Maintenance;
import com.example.material_save.Models.Material;
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
    private Label MaintenancesTotal;

    @FXML
    private TextField TextfieldMaterialMarque;

    @FXML
    private Label TotalDisponibleCategory;

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
    private TableView<Category> categorie_tableView;

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

//    @FXML
//    private DatePicker date_materiel;

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

//    @FXML
//    private TextField numero_materiel;

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
//    @FXML
////    private ComboBox ComboNumMateriel;

    @FXML
    private ComboBox Combo_maintenance;
//    @FXML
//    private TextField dateMaintenace;
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
            totalMaterial();
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

//    private final String[] categoriesList = {"Electronique", "Mobilier", "Agricole"};


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

    public void  totalCategories(){
        String sql = " SELECT COUNT(id) FROM category ";
        connection = DBConfig.getConnection();
        int TotalEnrolle = 0;
        try{

            preparedStatement =  connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                TotalEnrolle = resultSet.getInt("COUNT(id)");
            }
            TotalDisponibleCategory.setText(String.valueOf(TotalEnrolle));
        }catch (Exception e){
            e.printStackTrace();
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
                        resultSet.getString("statut")
                );
                listData.add(sData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;
    }

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
                // Vérification si le numéro de matériel est bien un entier
                int materialNum;
                try {
                    materialNum = Integer.parseInt(materialNumber);
                } catch (NumberFormatException e) {
                    // Si ce n'est pas un entier, afficher une alerte
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Le numéro de matériel doit être un entier.");
                    alert.showAndWait();
                    return; // Arrêter l'exécution de la méthode
                }

                // Vérification si le numéro de matériel existe déjà
                boolean checkMaterialNumber = checkMaterialNumber(materialNum);

                if (checkMaterialNumber) {
                    // Si le matériel existe déjà, afficher une alerte d'erreur
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Le matériel existe déjà.");
                    alert.showAndWait();
                    ViderChamps();
                } else {
                    // Si le matériel n'existe pas, on peut le créer

                    // Création de l'objet Material et assignation des valeurs
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

                    try {
                        // Enregistrement du matériel en base de données
                        material.register(material);
                        materialShowData();
                        ViderChamps();

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
        }catch (Exception e){
            e.printStackTrace();
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

            Material material = new Material(materialNumberInt, name, marque, etat,locale, category, Date.valueOf(date),  utilisateur, statut);
            boolean success = material.updateMaterial(material);

            if (success){
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("mise a jour");
                alert.showAndWait();

                materialShowData();
                ViderChamps();


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

            textfield_Material_User.setText(sData.getUtilisateur());
            comboStatut.setValue(sData.getStatut());

        }
    }
    public void homeDisplayBarChart() {
        GraphiqueMateriel.getData().clear();

        String sql = "SELECT date, COUNT(id) FROM material WHERE statut = 'disponible' GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 5";
        connection = DBConfig.getConnection();

        try {
            XYChart.Series<String, Number> chart = new XYChart.Series<>();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                chart.getData().add(new XYChart.Data<>(resultSet.getString(1), resultSet.getInt(2)));
            }

            // Ajoutez la série au graphique après avoir inséré toutes les données
            GraphiqueMateriel.getData().add(chart);

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
                new PieChart.Data("Non-disponible", getStatutCount("Non-Disponible")),
                new PieChart.Data("Maintenance", getStatutCount("Maintenance")),
                new PieChart.Data("Retire", getStatutCount("Retire"))
        );
        PieChartMateriel.setData(pieChartData);
    }


    private int getStatutCount(String statut){
       int count = 0;
       for(String s : statutListHome){
           if (s.equals(statut)){
               count ++;
           }
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
public ObservableList<Category> CategoriesListData(){
        ObservableList<Category> listData = FXCollections.observableArrayList();
        String selectData = "SELECT * FROM category";
        connection = DBConfig.getConnection();
        try{
            Category courseC;

            preparedStatement = connection.prepareStatement(selectData);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                courseC = new Category(resultSet.getString("NomCategory"),
                        resultSet.getString("Description"),
                resultSet.getString("Etat") );
                listData.add(courseC);
            }

        } catch (Exception e){
          e.printStackTrace();
        }
        return  listData;
}

public void addCategoryList() {
        String listCategory = "SELECT * FROM category";
        connection = DBConfig.getConnection();
        ObservableList<String> courseG = FXCollections.observableArrayList();
        try {
            preparedStatement = connection.prepareStatement(listCategory);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                courseG.add(resultSet.getString("NomCategory"));
            }
//            La rececption des categories crees par le comboBox
            BoxCategorie.setItems(courseG);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean checkCategoryExists(String NomCategory) throws SQLException {
        boolean exists = false;

        String query = "SELECT NomCategory FROM category WHERE NomCategory = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, NomCategory);
            ResultSet resultSet = statement.executeQuery();

            // Si un résultat est retourné, cela signifie que la catégorie existe
            if (resultSet.next()) {
                exists = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return exists;
    }
public void categoriesData(){
     categoriesData = CategoriesListData();
    col_categoryName.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
    col_description_categorie.setCellValueFactory(new PropertyValueFactory<>("categoryDescription"));
    col_etat_categorie.setCellValueFactory(new PropertyValueFactory<>("categoryState"));

    categorie_tableView.setItems(categoriesData);

 }
 public void ajouterCategory() throws IOException {
        String categoryName = textfield_categorie_name.getText().trim();
        String categoryDescription = description_categorie.getText().trim();
        String categoryState = etat_categorie.getText().trim();

        if (categoryName.isEmpty() || categoryDescription.isEmpty() || categoryState.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs requis.");
            alert.showAndWait();
            return;
        }

        try {
            if (checkCategoryExists(categoryName)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("La catégorie existe déjà");
                alert.showAndWait();
                ViderChampsCategory();
                return;
            }

            Category category = new Category(categoryName, categoryDescription, categoryState);

            category.register(category);
            categoriesData();
//            Methode pou recuperer le combo box:addCategoryList();
            addCategoryList();

//            Methode pour charger le tableView
            CategoriesListData();


            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Catégorie créée");
            alert.showAndWait();

            // Vider les champs après l'ajout
            ViderChampsCategory();

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Catégorie non créée");
            alert.showAndWait();
        }
    }
    public void CategorySelectData(){
        Category courseC = categorie_tableView.getSelectionModel().getSelectedItem();
        if(courseC != null){
            textfield_categorie_name.setText(courseC.getCategoryName());
            description_categorie.setText(courseC.getCategoryDescription());
            etat_categorie.setText(courseC.getCategoryState());
        }
    }
    public void  ViderChampsCategory(){
    textfield_categorie_name.setText("");
    etat_categorie.setText("");
    description_categorie.setText("");
}
public void availableCategory() {
        // Obtenir l'élément sélectionné dans la table des catégories
        Category courseC = categorie_tableView.getSelectionModel().getSelectedItem();
        int num = categorie_tableView.getSelectionModel().getSelectedIndex();

        if (num < 0) {
            return;
        }
        textfield_categorie_name.setText(courseC.getCategoryName());
        description_categorie.setText(courseC.getCategoryDescription());
        etat_categorie.setText(courseC.getCategoryState());
    }
    public void MettreAjourCategorie() throws IOException {
        String categoryName = textfield_categorie_name.getText().trim();
        String categoryDescription = description_categorie.getText().trim();
        String categoryState = etat_categorie.getText().trim();

        if (categoryName.isEmpty() || categoryDescription.isEmpty() || categoryState.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs");
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
                boolean success = category.updateCategory(category);

                if (success) {
                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Mise à jour effectuée");
                    alert.showAndWait();
                    ViderChampsCategory();
                    categoriesData();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Échec de la mise à jour");
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
    public void SupprimerCategory() throws IOException {
        String CategoryName = textfield_categorie_name.getText().trim();
        String CategoryDescription = description_categorie.getText().trim();
        String CategoryState = etat_categorie.getText().trim();
        if (CategoryName.isEmpty() && CategoryDescription.isEmpty() && CategoryState.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de validation");
            alert.setContentText("Le nom de la catégorie est obligatoire");
            alert.showAndWait();
            return;
        }

        try {
            connection = DBConfig.getConnection();
            Category category = new Category(CategoryName, CategoryDescription, CategoryState);
            boolean success = category.DeleteCategory(category);

            if (success) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setContentText("La catégorie a été supprimée avec succès.");
                alert.showAndWait();
                ViderChampsCategory();
//                availableCategory();
                categoriesData();


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


//    Methodes De Gestion Des maintenaces


    public void statutList() {
        List<String> statutL = new ArrayList<>();
        for (String data : statutList ) {
            statutL.add(data);
        }
        ObservableList ObList = FXCollections.observableArrayList(statutL);
        Combo_maintenance.setItems(ObList);

    }
    private String[] statutList = {"Preventive", "Corrective", "Conditionnelle", "Améliorative"};

    public  void clearMaintenace(){
        ComboNumMateriel.getSelectionModel().clearSelection();
        comboCategorie.getSelectionModel().clearSelection();
        chargeMaintenace_textfield.setText("");
        probleme_materiel.setText("");
        DateMaintenance.setValue(null);
        Combo_maintenance.getSelectionModel().clearSelection();
    }
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
        maintenancesData = maintenanceData();

        MaintenaceNumber.setCellValueFactory(new PropertyValueFactory<>("numeroMateriel"));
        col_charge.setCellValueFactory(new PropertyValueFactory<>("charge"));
        col_probleme.setCellValueFactory(new PropertyValueFactory<>("probleme"));
        col_dateMaintenance.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_statutMaintenance.setCellValueFactory(new PropertyValueFactory<>("statut"));
        col_MaintenanceCombo.setCellValueFactory(new PropertyValueFactory<>("categorie"));

        Maintenance_tableView.setItems(maintenancesData);
    }
    public ObservableList<Maintenance> maintenanceData() {
        ObservableList<Maintenance> listData = FXCollections.observableArrayList();
        String selectData = "SELECT * FROM maintenances";
        connection = DBConfig.getConnection();

        try {
            preparedStatement = connection.prepareStatement(selectData);
            resultSet = preparedStatement.executeQuery();

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
            Maintenance_tableView.setItems(listData);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;

    }
    public void maintenanceSelectData() {
        Maintenance sData = Maintenance_tableView.getSelectionModel().getSelectedItem();
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
        ObservableList<Integer> materialsList = FXCollections.observableArrayList();
        String query = "SELECT numeroMateriel FROM material";  // Remplace 'material' par le nom correct de ta table.

        try (Connection connection = DBConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                materialsList.add(resultSet.getInt("numeroMateriel"));
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

    // Valider les inputs du logiciel
    if (numeroMateriel == null || comboBoxMaintenance.isEmpty() || probleme.isEmpty() || charge.isEmpty() || date == null || statut == null || statut.isEmpty()) {
        showAlert(Alert.AlertType.ERROR, "Remplir tous les champs");
        return;
    }

    // Confirmation dialog
    Optional<ButtonType> option = showConfirmation("Sur de modifier ?", "Sur de modifier la maintenance effectuée sur " + numeroMateriel + "?");
    if (option.isPresent() && option.get().equals(ButtonType.OK)) {
        try {
            connection = DBConfig.getConnection();

            Maintenance maintenance = new Maintenance(numeroMateriel, comboBoxMaintenance, probleme, charge, Date.valueOf(date), statut);
            boolean success = maintenance.updateMaintenace(maintenance);
            maintenanceData();
            clearMaintenace();


            if (success) {
                showAlert(Alert.AlertType.INFORMATION, "Mise à jour réussie");
                materialShowData();
                clearMaintenace();

            } else {
                showAlert(Alert.AlertType.ERROR, "Échec de la mise à jour");
                materialShowData();
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Une erreur s'est produite : " + e.getMessage());
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

public void DeleteMaintenance(ActionEvent event) {
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

    try {
        Date date = Date.valueOf(localDate); // Conversion de LocalDate à Date si nécessaire

        // Création de l'objet Maintenance (si besoin)
        Maintenance maintenance = new Maintenance(numeroMateriel, categorie, charge, probleme, date, statut);

        // Suppression
        boolean success = maintenance.DeleteMaintenance(maintenance);
        materialShowData();

        if (success) {
            showAlert(Alert.AlertType.INFORMATION, "Succès de la suppression",
                    "Le matériel avec le numéro " + numeroMateriel + " a été supprimé avec succès.");
            clearMaintenace();

        } else {
            showAlert(Alert.AlertType.ERROR, "Erreur de suppression",
                    "Le matériel avec le numéro " + numeroMateriel + " n'a pas été trouvé.");
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

   private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



//    public void logout() {
//        try {
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle("Confirmation");
//            alert.setHeaderText(null);
//            alert.setContentText("Êtes-vous sûr de vous déconnecter ?"); // Correction de l'orthographe
//
//            Optional<ButtonType> option = alert.showAndWait();
//            if (option.isPresent() && option.get() == ButtonType.OK) { // Vérification si une option est présente et utilisation de == pour comparer
//                LogOut_Btn.getScene().getWindow().hide();
//                Parent root;
//                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("src/main/resources/com/example/material_save/LogIn.fxml")));
////                Parent root = FXMLLoader.load(getClass().getResource("/LogIn.fxml"));
//                Stage stage = new Stage();
//                Scene scene = new Scene(root);
//                stage.setScene(scene);
//                stage.show();
//            } else {
//                return;
//            }
//        }  catch (Exception e) {
//            System.out.println("Erreur lors de la déconnexion : " + e.getMessage());
//            e.printStackTrace();
//        }
//    }

    public void logout() {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de vous déconnecter ?");

            Optional<ButtonType> option = alert.showAndWait();
            if (option.isPresent() && option.get() == ButtonType.OK) {
                // Fermer la fenêtre actuelle
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

//        addstatutList();
//        addcategorieList();
        materialShowData();
        homeDisplayBarChart();
        categoriesData();
        CategorySelectData();

        CategoriesListData();

        availableCategory();

//        La Methode qui recupere les elements du combobox
        addCategoryList();
         statutList();


        maintenanceShowData();

//        comboCategorie.setItems(getCategories());
        maintenanceData();
        clearMaintenace();


        totalMaterial();
        updatePieChart();
        statutListHome();
        totalCategories();
        totalMaintenaces();




//        comboStatut.getItems().addAll(statutList);
//
//        if (comboStatut.getItems().addAll("Disponible", "Non-disponible", "En reserve", "En maintenance"));
//
//        comboStatut.setOnAction(event -> updatePieChart());

        BoxCategorie.setOnAction(event -> {
            String selectedCategory = BoxCategorie.getSelectionModel().getSelectedItem();
            if (selectedCategory != null) {
                String categoryName = selectedCategory;
                System.out.println("Nom de la catégorie sélectionnée: " + categoryName);
            }
        });


    }

}













