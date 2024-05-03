package modele;

import javafx.scene.paint.Paint;

public class Temple implements ConstantesCanvas{
    private Position position;
    private Paint couleur;
    private Cristal cristal;
    public Temple(Position pos, int idCouleur, Cristal parCristal){
        position = pos;
        couleur = COULEURS_TEMPLES[idCouleur];
        cristal = parCristal;
    }
}
