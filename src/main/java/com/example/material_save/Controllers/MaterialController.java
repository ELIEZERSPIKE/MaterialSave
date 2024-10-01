package com.example.material_save.Controllers;

import com.example.material_save.IDB.DBConfig;
import com.example.material_save.Models.Category;
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

            System.out.println(" Maintenance  trouve");
        }


    }

//    private final String[] categoriesList = {"Electronique", "Mobilier", "Agricole"};
private final String[] categoriesList = {"", "", ""};
//    public void addcategorieList() {
//        List<String> categoriesL = new ArrayList<>();
//        for (String data : categoriesList) {
//            categoriesL.add(data);
//        }
//
//        ObservableList Oblist = FXCollections.observableList(categoriesL);
//        BoxCategorie.setItems(Oblist);
//    }


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
                    ViderChamps();
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

            Material material = new Material(  materialNumberInt, name, marque, etat,locale, category, Date.valueOf(date),  utilisateur, statut, null, null);
            boolean success = material.updateMaterial(material);

            if (success){
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("mise a jou");
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

//            Conversion de Date en localeDate

//            if (sData.getDate() != null ){
//                addDate.setValue(sData.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//            }
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
    private void updatePieChart() {
        String selectedStatut = comboStatut.getValue();
        System.out.println("Statut sélectionné : " + selectedStatut);

        PieChartMateriel.getData().clear();

        if (selectedStatut != null) {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            try {
                connection = DBConfig.getConnection();
                String sql = "SELECT statut, COUNT(id) AS count FROM material WHERE statut = ? GROUP BY statut";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, selectedStatut);
                resultSet = preparedStatement.executeQuery();

                int count = 0;
                if (resultSet.next()) {
                    count = resultSet.getInt("count");
                    System.out.println("Matériels : " + count);
                } else {
                    System.out.println("Aucune donnée trouvée pour : " + selectedStatut);
                }

                int total = getTotaCount(connection);
                PieChartMateriel.getData().addAll(
                        new PieChart.Data(selectedStatut, count),
                        new PieChart.Data("Autres", total - count)
                );

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // Assurer la fermeture des ressources
                try { if (resultSet != null) resultSet.close(); } catch (Exception e) { e.printStackTrace(); }
                try { if (preparedStatement != null) preparedStatement.close(); } catch (Exception e) { e.printStackTrace(); }
                try { if (connection != null) connection.close(); } catch (Exception e) { e.printStackTrace(); }
            }
        }
    }
    private int getTotaCount(Connection connection){

        String total = "SELECT COUNT(id) AS total FROM material ";
        try{
            connection = DBConfig.getConnection();
            preparedStatement = connection.prepareStatement(total);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt("total");
            }
        }catch (Exception e){
           e.printStackTrace();
        }
        return 0;
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
        ObservableList<String> courseC = FXCollections.observableArrayList();
        try {
            preparedStatement = connection.prepareStatement(listCategory);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                courseC.add(resultSet.getString("NomCategory"));
            }
            BoxCategorie.setItems(courseC);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


public boolean checkCategoryExists(String NomCategory) throws SQLException {
        boolean exists = false;


        String query = "SELECT NomCategory FROM category WHERE NomCategory = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, NomCategory);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                exists = resultSet.getInt(1) > 0;  // Vérifie si le matériel existe
            }
        } catch (Exception e){
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

//    public void MettraAjourCategorie() throws IOException{
//        String CategoryName = textfield_categorie_name.getText().trim();
//        String CategoryDescription = description_categorie.getText().trim();
//        String  CategoryState = etat_categorie.getText().trim();
//
//        if(CategoryName.isEmpty() || CategoryDescription.isEmpty() || CategoryState.isEmpty()){
//            alert = new Alert(Alert.AlertType.ERROR);
//            alert.setHeaderText(null);
//            alert.setContentText("Veuillez remplir tout les champs");
//            alert.showAndWait();
//            return;
//        }
//        alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setHeaderText(null);
//        alert.setContentText("êtes vous sûr de vouloir modifier" + CategoryName + "?");
//        Optional<ButtonType> option = alert.showAndWait();
//        if(option.get().equals(ButtonType.OK)){
//            try{
//                connection = DBConfig.getConnection();
//                String CategorieName = CategoryName;
//                Category category = new Category(CategorieName, CategoryDescription, CategoryState);
//                boolean success = category.updateCategory(category);
//
//                if (success){
//                    alert = new Alert(Alert.AlertType.CONFIRMATION);
//                    alert.setHeaderText(null);
//                    alert.setContentText("Mise a jour effectue");
//                    alert.showAndWait();
//                    ViderChampsCategory();
//                    categoriesData();
//
//
//                } else{
//
//                    alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setHeaderText(null);
//                    alert.setContentText("Echec");
//                    alert.showAndWait();
//                    ViderChampsCategory();
//                    categoriesData();
//                }
//            } catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }

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

//        comboStatut.getItems().addAll(statutList);
        if (comboStatut.getItems().addAll("Disponible", "Non-disponible", "En reserve", "En maintenance"));

        comboStatut.setOnAction(event -> updatePieChart());

        BoxCategorie.setOnAction(event -> {
            String selectedCategory = BoxCategorie.getSelectionModel().getSelectedItem();
            if (selectedCategory != null) {
                String categoryName = selectedCategory;
                System.out.println("Nom de la catégorie sélectionnée: " + categoryName);
            }
        });


    }

}













