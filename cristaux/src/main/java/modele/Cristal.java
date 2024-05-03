package modele;

import javafx.scene.paint.Paint;

public class Cristal implements ConstantesCanvas{
    private Paint couleur ;
    private boolean estPorte;

    public Cristal(int idCouleur){
        couleur = COULEURS_TEMPLES[idCouleur];
        estPorte=false;
    }


}
