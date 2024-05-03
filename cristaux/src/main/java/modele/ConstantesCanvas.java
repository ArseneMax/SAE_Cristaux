package modele;

import javafx.scene.paint.Paint;

public interface ConstantesCanvas {
    final int LARGEUR_CANVAS = 500 ;
    final int HAUTEUR_CANVAS = 500 ;
    final int CARRE = 50 ;
    final int HAUTEUR_OVALE = CARRE/2;
    final int LARGEUR_OVALE = CARRE/2;
    final Paint COULEUR_GRILLE = Paint.valueOf("purple");
    final Paint COULEUR_APPRENTI = Paint.valueOf("black");
    final Paint COULEUR_SELECT = Paint.valueOf("lightgrey");
    final Paint COULEUR_BLANC = Paint.valueOf("whitesmoke");


}
