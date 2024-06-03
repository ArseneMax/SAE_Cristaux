package vue;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;



public class VBoxAffichageTable extends VBox {
    private TableView<VBoxCanvas> tableInfo;

    public VBoxAffichageTable(){


        tableInfo = new TableView<>();


        TableColumn<VBoxCanvas, Integer> pasColumn = new TableColumn<>("Nombre de pas");
        pasColumn.setCellValueFactory(new PropertyValueFactory<>("pas"));

        TableColumn<VBoxCanvas, String> triColumn = new TableColumn<>("algorithme utilisé");
        triColumn.setCellValueFactory(new PropertyValueFactory<>("algorithme"));

        TableColumn<VBoxCanvas, String> cheminColumn = new TableColumn<>("chemin emprunté");
        cheminColumn.setCellValueFactory(new PropertyValueFactory<>("temples"));



        tableInfo.getColumns().addAll(pasColumn,triColumn,cheminColumn);
        this.getChildren().add(tableInfo);

    }

    public void updateTable() {
        tableInfo.getItems().clear();

    }

}