package vue;

import controleur.Controleur;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import modele.Apprenti;
import modele.ConstantesCanvas;

import java.io.File;

public class VBoxRoot extends VBox implements ConstantesCanvas {
    private static Apprenti apprenti;
    private static Controleur controleur;
    private static VBoxCanvas vueCanvas;

    public VBoxRoot(){
        apprenti = new Apprenti();
        controleur = new Controleur();

        MenuBar menuBar = new MenuBar();
        this.getChildren().add(menuBar);
        VBox.setMargin(menuBar,new Insets(9));

        Menu menuScenarios = new Menu(INTITULE_MENU_SCENARIO);
        Menu menuTri = new Menu(INTITULE_MENU_ALGORITHMES);
        menuBar.getMenus().addAll(menuScenarios, menuTri);

        File[] scenarios = new File("src/main/java/Scenario").listFiles();
        for (int i = 0 ; i<scenarios.length ; i++){
            MenuItem menuItem = new MenuItem(scenarios[i].getName());
            menuItem.setUserData(scenarios[i]);
            menuItem.setOnAction(controleur);
            menuScenarios.getItems().add(menuItem);
        }

        for (String tri : INTITULES_ALGOS ){
            MenuItem itemTri = new MenuItem(tri);
            itemTri.setUserData(tri);
            itemTri.setOnAction(controleur);
            menuTri.getItems().add(itemTri);
        }


        vueCanvas = new VBoxCanvas();
        this.getChildren().add(vueCanvas);
    }

    public static Apprenti getApprenti() {
        return apprenti;
    }

    public static VBoxCanvas getVueCanvas() {return vueCanvas;}
    public static Controleur getControleur(){ return controleur;}
}
