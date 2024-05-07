package controleur;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import modele.LectureScenario;
import modele.Temple;
import vue.VBoxRoot;

import java.io.File;
import java.util.Collection;

public class Controleur implements EventHandler {
    @Override
    public void handle(Event event) {
        Object userData = ((MenuItem) event.getSource()).getUserData();
        if (userData instanceof File){
            File fichierScenario = (File)userData;
            System.out.println(fichierScenario.getName());
            File scenario = fichierScenario;
            Collection<Temple>temples= LectureScenario.lecture(fichierScenario);
            VBoxRoot.getApprenti().setTemples(temples);
            System.out.println(VBoxRoot.getApprenti());
        }
    }
}
