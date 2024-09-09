package com.example.material_save.Controllers;

import com.example.material_save.IDB.DBConfig;
import com.example.material_save.Models.Material;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

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
    private TableColumn<?, ?> col_date;

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
    private ComboBox<?> comboCategorie;

    @FXML
    private ComboBox<?> comboStatut;

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
    private Button updateMaterial;

    @FXML
    private AnchorPane HomeForm;

    private Connection connection;
    private PreparedStatement preparedStatement;

    private Statement statement;

    private ResultSet resultSet;




    public void close(){
        System.exit(0);
    }

    public  void minimize(){
        Stage stage = (Stage)Main_Form.getScene().getWindow();
        stage.setIconified(true);
    }

    public  void switchForm( ActionEvent event){
        if(event.getSource() == Home_Btn){
            home_Form.setVisible(true);

            Material_Add_Form.setVisible(false);

            Form_categories.setVisible(false);

            Maintenance_Form.setVisible(false);
            System.out.println("Home appuye");

        }
        else if (event.getSource() == AddMateriel_Btn)
        {
            home_Form.setVisible(false);
            home_Form.setManaged(false);

            Material_Add_Form.setVisible(true);
            Material_Add_Form.setManaged(true);

            Form_categories.setVisible(false);
            Form_categories.setManaged(false);

            Maintenance_Form.setVisible(false);
            Maintenance_Form.setManaged(false);

            System.out.println("Formulaire de materiel appuye");

        }
        else if (event.getSource() == addCategories)
        {

            home_Form.setVisible(false);
            home_Form.setManaged(false);

            Material_Add_Form.setVisible(false);
            Material_Add_Form.setManaged(false);

            Form_categories.setVisible(true);
            Form_categories.setManaged(true);
            System.out.println("categorie  trouve");

            Maintenance_Form.setVisible(false);
            Maintenance_Form.setManaged(false);


        }
        else if (event.getSource() == AddMaintenances_Btn)
        {
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
    public void addcategorieList(){
        List<String> categoriesL = new ArrayList<>();
        for(String data : categoriesList ){
            categoriesL.add(data);
        }

        ObservableList Oblist = FXCollections.observableList(categoriesL);
        comboCategorie.setItems(Oblist);
    }

    private final String[] statutList = {"Disponible", "Non-disponible", "Retire", "En maintenance", "En reserve"};

    public void addstatutList(){
        List<String> statutL =  new ArrayList<>();
        for (String data  : statutList){
            statutL.add(data);
        }
        ObservableList Oblist = FXCollections.observableList(statutL);
        comboStatut.setItems(Oblist);
    }







   public void addMaterial(){

        String insertData = "INSERT INTO  material "
                + "(numeroMateriel, nom, marque,etat,local,categorie,date,utilisateur,statut)"
                + "VALUES (?,?,?,?,?,?,?,?,?) ";

            connection = DBConfig.getConnection();
            try{

                Alert alert;

                if (textfieldMaterialNumber.getText().isEmpty()
                        || textfieldMateriamName.getText().isEmpty()
                        || TextfieldMaterialMarque.getText().isEmpty()
                        || textfieldMaterial_state.getText().isEmpty()
                        || comboCategorie.getSelectionModel().getSelectedItem() == null
                        || textefieldLocate.getText().isEmpty()
                        || addDate.isShowWeekNumbers()
                        || textfield_Material_User.getText().isEmpty()
                        || comboStatut.getSelectionModel().getSelectedItem() == null
                ){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Priere de remplir tout les champs!");
                    alert.showAndWait();

                } else{
                    String checkData = "SELECT numeroMateriel FROM  material WHERE numeroMateriel = ' "
                            + textfieldMaterialNumber.getText() + " ' ";

                     statement = connection.createStatement();
                     resultSet = statement.executeQuery(checkData);

                     if (resultSet.next())
                     {
                         alert = new Alert(Alert.AlertType.ERROR);
                         alert.setTitle("Error Message");
                         alert.setHeaderText(null);
                         alert.setContentText("Numero Materiel " + textfieldMaterialNumber.getText() + "existe dejà !" );
                         alert.showAndWait();
                     }
                     else {
                         preparedStatement = connection.prepareStatement(insertData);
                         preparedStatement.setString(1,textfieldMaterialNumber.getText());
                         preparedStatement.setString(2, textfieldMateriamName.getText());
                         preparedStatement.setString(3,TextfieldMaterialMarque.getText());
                         preparedStatement.setString(4,textfieldMaterial_state.getText());
                         preparedStatement.setString(5, (String) comboCategorie.getSelectionModel().getSelectedItem());
                         preparedStatement.setString(6, textefieldLocate.getText());
                         preparedStatement.setString(7, String.valueOf(addDate.getValue()));
                         preparedStatement.setString(8, textfield_Material_User.getText());
                         preparedStatement.setString(9, (String) comboStatut.getSelectionModel().getSelectedItem());

                         Date date = new Date();
                         java.sql.Date sqlDate = new  java.sql.Date(date.getTime());
                         preparedStatement.setString(10, String.valueOf(sqlDate));

                         preparedStatement.executeUpdate();


                     }

                }








            }catch (Exception e){
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

        addstatutList();
        addcategorieList();








    }
}
