package modele;

import javafx.scene.paint.Paint;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

public interface ConstantesCanvas {
    final int LARGEUR_CANVAS = 800 ;
    final int HAUTEUR_CANVAS = LARGEUR_CANVAS;
    final int CARRE = LARGEUR_CANVAS/32 ;
    final int HAUTEUR_OVALE = CARRE*2/3;
    final int LARGEUR_OVALE = CARRE*2/3;
    final Paint COULEUR_GRILLE = Paint.valueOf("black");
    final Paint COULEUR_APPRENTI = Paint.valueOf("purple");
    final Paint COULEUR_SELECT = Paint.valueOf("lightgrey");
    final Paint COULEUR_BLANC = Paint.valueOf("whitesmoke");
    final Paint[] COULEURS_TEMPLES = {Paint.valueOf("aliceblue"),Paint.valueOf("red"),Paint.valueOf("green"),Paint.valueOf("pink"),Paint.valueOf("yellow"),Paint.valueOf("olive"),Paint.valueOf("brown"),Paint.valueOf("orange"),Paint.valueOf("grey"),Paint.valueOf("cyan")};
    final String INTITULE_MENU_SCENARIO = "Menu scenario";
    final String INTITULE_MENU_ALGORITHMES= "Menu algorithmes";
    final String[] INTITULES_ALGOS = new String[]{"Tri à insertion","Tri à bulle","Tri à selection"};

}

