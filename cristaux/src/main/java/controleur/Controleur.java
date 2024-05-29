package controleur;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import modele.*;
import vue.VBoxCanvas;
import vue.VBoxRoot;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

public class Controleur implements EventHandler, ConstantesCanvas {

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
            Apprenti apprenti = VBoxRoot.getApprenti();
            VBoxCanvas vue = VBoxRoot.getVueCanvas();

            if( bouton.getUserData()=="recup"){
                Temple temple = vue.surTemple(VBoxCanvas.getPositionApprenti());

                if (temple!=null) {
                    apprenti.recupCristal(temple);
                }
            }
            if (bouton.getUserData()=="Tri"){
                ArrayList<Position> positionsTri = null;
                 if(vue.nomTri.getText()==INTITULES_ALGOS[0]){
                     positionsTri = Algorithmes.triInsertion();
                 }

                 else if (vue.nomTri.getText()==INTITULES_ALGOS[1]){
                     positionsTri = Algorithmes.triBulle();

                 }
                 else if (vue.nomTri.getText()==INTITULES_ALGOS[2]){
                    positionsTri = Algorithmes.triSelection();

                }

                 else
                     vue.nomTri.setText("Veuillez choisir un tri");
                 if(positionsTri!=null)
                     if (positionsTri.size()!=0)
                        vue.deplacementAvecTimerListe(VBoxCanvas.getPositionApprenti(), positionsTri);
                    else
                        vue.nomTri.setText("Algorithme de tri en cours de maintennace");



            }


        }


    }
}
