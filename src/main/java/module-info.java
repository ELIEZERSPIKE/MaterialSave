module com.example.material_save {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.jetbrains.annotations;


    opens com.example.material_save to javafx.fxml;
    exports com.example.material_save;

    // Si vous avez d'autres packages que vous devez exporter, ajoutez-les ici.

    opens com.example.material_save.Controllers to javafx.fxml;
    exports com.example.material_save.Controllers;

    opens com.example.material_save.IDB to javafx.fxml;
    exports com.example.material_save.IDB;


    opens com.example.material_save.Interfaces to javafx.fxml;
    exports com.example.material_save.Interfaces;

    opens com.example.material_save.Models  to javafx.fxml;
    exports com.example.material_save.Models;

}
