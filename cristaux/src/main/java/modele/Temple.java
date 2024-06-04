package modele;

/**
 * Classe qui gére les temples
 */
public class Temple implements ConstantesCanvas{
    private Position position;
    private int couleur;
    private int cristal;

    public Temple(Position pos, int idCouleur, int parCristal){
        position = pos;
        couleur = idCouleur;
        cristal=parCristal;
    }
    public Temple(){
        position = new Position(0,0);
        couleur = 0;
        cristal = 0;
    }
    public String toString(){return "Temple : ("+ (position.getAbscisse() - LARGEUR_CANVAS/(2*CARRE))+","+ (position.getOrdonnee() - HAUTEUR_CANVAS/(2*CARRE))+") "+ couleur+ " "+cristal;}

    public int getCouleur(){
        return couleur;
    }

    public int getCristal() {
        return cristal;
    }

    public void setCristal(int parCristal) {
        cristal = parCristal;
    }

    public Position getPosition(){
        return position;
    }

    /**
     * Vérifie si le temple a le bon cristal
     * @return true si c'est le cas et sinon return false
     */
    public boolean bonCristal(){
        if (cristal == couleur)
            return true;
        return false;
    }
}
