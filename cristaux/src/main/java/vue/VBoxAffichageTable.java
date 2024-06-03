package vue;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import modele.Resultat;

import java.util.ArrayList;
import java.util.TreeSet;


public class VBoxAffichageTable extends VBox {
    private TableView<Resultat> tableInfo;
    private ArrayList<Resultat> resultats;


    public VBoxAffichageTable(){


        tableInfo = new TableView<>();
        resultats = new ArrayList<>();

        TableColumn<Resultat, String> scenarioColumn = new TableColumn<>("Scénario");
        scenarioColumn.setCellValueFactory(new PropertyValueFactory<>("scenario"));

        TableColumn<Resultat, Integer> pasColumn = new TableColumn<>("Nombre de pas");
        pasColumn.setCellValueFactory(new PropertyValueFactory<>("nbPas"));

        TableColumn<Resultat, String> triColumn = new TableColumn<>("algorithme ");
        triColumn.setCellValueFactory(new PropertyValueFactory<>("nomAlgo"));

        TableColumn<Resultat, ArrayList<Integer>> cheminColumn = new TableColumn<>("Temples vus");
        cheminColumn.setCellValueFactory(new PropertyValueFactory<>("templesVus"));



        tableInfo.getColumns().addAll(scenarioColumn,pasColumn,triColumn,cheminColumn);

        this.getChildren().add(tableInfo);
        this.updateTable();

    }

    public void updateTable() {
        tableInfo.getItems().clear();
        if(resultats!=null){
            for(Resultat resultat : resultats){
                tableInfo.getItems().add(resultat);
            }
        }


    }

    public ArrayList<Resultat> getResultats() {
        return resultats;
    }
}