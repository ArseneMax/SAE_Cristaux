package modele;

import static java.lang.Math.abs;

public class Position {
    private static int nbPas = 0;
    private int abscisse;
    private int ordonnee;

    public Position(int abscisse, int ordonnee){
        this.abscisse = abscisse;
        this.ordonnee = ordonnee;
    }

    /**
     * Déplace l'apprenti vers la position souhaitée
     * Le déplacement se fait d'une case par une case
     * @param parPosition
     */
    public void deplacementUneCase(Position parPosition){
        nbPas++;
        if (this.abscisse> parPosition.abscisse){
            this.abscisse-=1;
            return;
        }
        if (this.abscisse< parPosition.abscisse){
            this.abscisse+=1;
            return;
        }
        if (this.ordonnee> parPosition.ordonnee){
            this.ordonnee-=1;
            return;
        }
        if (this.ordonnee< parPosition.ordonnee){
            this.ordonnee+=1;
            return;
        }
    }

    public boolean equals(Position parPosition){
        return this.ordonnee== parPosition.ordonnee && this.abscisse == parPosition.abscisse;
    }

    public int getAbscisse(){
        return abscisse;
    }

    public int getOrdonnee() {
        return ordonnee;
    }

    public static int getNbPas() {
        return nbPas;
    }

    public static void setNbPas(int nombre){nbPas=nombre;}

    public String toString(){return "("+(getAbscisse()-16)+","+(getOrdonnee()-16)+")";}

    /**
     * Compare 2 positions
     * @param parPosition
     * @return un entier négatif si la position est inférieur, zéro si la position est la même
     *         ou un entier positif si cette position est supérieure à la position spécifiée.
     */
    public int compareTo(Position parPosition){
        if (this.abscisse < parPosition.abscisse){
            return -1;
        }
        else if (this.abscisse > parPosition.abscisse){
            return 1;
        }
        else if (this.ordonnee < parPosition.ordonnee){
            return -1;
        }
        else if (this.ordonnee > parPosition.ordonnee){
            return 1;
        }
        return 0;
    }

    public void setAbscisse(int nouveau){nbPas++;this.abscisse=nouveau;}
    public void setOrdonnee(int nouveau){nbPas++;this.ordonnee=nouveau;}

    public static int distance(Position positionDepart, Position positionCible){
        return abs(positionDepart.getAbscisse()- positionCible.getAbscisse())+abs(positionDepart.getOrdonnee()- positionCible.getOrdonnee());
    }

}
