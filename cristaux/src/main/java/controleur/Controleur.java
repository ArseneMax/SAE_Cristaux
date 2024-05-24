package controleur;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import modele.Apprenti;
import modele.LectureScenario;
import modele.Temple;
import vue.VBoxCanvas;
import vue.VBoxRoot;

import java.io.File;
import java.util.Collection;

public class Controleur implements EventHandler {

    @Override
    public void handle(Event event) {
        if (event.getSource() instanceof MenuItem){
            Object userData = ((MenuItem) event.getSource()).getUserData();
            if (userData instanceof File){
                File fichierScenario = (File)userData;
                System.out.println(fichierScenario.getName());
                File scenario = fichierScenario;
                Collection<Temple>temples= LectureScenario.lecture(fichierScenario);
                VBoxRoot.getApprenti().setTemples(temples);
                System.out.println(VBoxRoot.getApprenti());
            }
            if (userData instanceof String){
                VBoxCanvas canvas = VBoxRoot.getVueCanvas();
                String nomTri = (String)userData;
                canvas.nomTri.setText(nomTri);
            }
        }
        if (event.getSource() instanceof Button){
            Button bouton = (Button) event.getSource();
            if( bouton.getUserData()=="recup"){
                Apprenti apprenti = VBoxRoot.getApprenti();
                VBoxCanvas vue = VBoxRoot.getVueCanvas();
                Temple temple = vue.surTemple(VBoxCanvas.getPositionApprenti());
                if (temple!=null) {
                    apprenti.recupCristal(temple);
                }
            }

        }

        //if (userData instanceof Button)
    }
}
