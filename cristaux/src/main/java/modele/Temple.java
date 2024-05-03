package modele;

import javafx.scene.paint.Paint;

public class Temple implements ConstantesCanvas{
    private Position position;
    private Paint couleur;

    public Temple(Position pos, int idCouleur){
        position = pos;
        couleur = COULEURS_TEMPLES[idCouleur];
    }
}
