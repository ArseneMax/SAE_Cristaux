package modele;

public class Temple implements ConstantesCanvas{
    private Position position;
    private int couleur;
    private int crystal;

    public Temple(Position pos, int idCouleur, int parCrystal){
        position = pos;
        couleur = idCouleur;
        crystal=parCrystal;
    }
    public String toString(){return "Temple : ("+ (position.getAbscisse() - LARGEUR_CANVAS/(2*CARRE))+","+ (position.getOrdonnee() - HAUTEUR_CANVAS/(2*CARRE))+") "+ couleur+ " "+crystal;}
    public int getCouleur(){
        return couleur;
    }

    public int getCrystal() {
        return crystal;
    }

    public Position getPosition(){
        return position;
    }
}
