package modele;

import vue.VBoxCanvas;

import java.util.Collection;
import java.util.HashMap;

public class Apprenti {
    private Position position;
    private static Collection<Temple> temples;
    private int cristal =0;

    public Apprenti(){ position = new Position(0,0); }

    public void setTemples(Collection<Temple> parTemple ) {
        temples = parTemple;
        VBoxCanvas.placetemple(temples);
    }

    public Collection<Temple> getTemples(){
        return temples;
    }

    public int getCristal() {
        return cristal;
    }

    public void recupCristal(Temple temple){
        int ancienCristal = cristal;
        cristal= temple.getCristal();
        temple.setCristal(ancienCristal);
    }

    public boolean fini(){
        for (Temple temple: temples){
            if(!temple.bonCristal())
                return false;
        }
        return true;
    }
    public static Temple getTemple(int couleur){
        for(Temple temple:temples){
            if (temple.getCouleur()==couleur)
                return temple;
        }
        return null;
    }

    /**
     * Cette méthode permet de reinitialiser les temples tels qu'ils apparaissent dans le scénario
     * @param save dictionnaire comprenant la couleur et le cristal initiaux de chaques temples
     */
    public static void resetTemples(HashMap save){
        for (Temple temple : temples){
            temple.setCristal((int) save.get(temple.getCouleur()));
        }
    }

    /**
     * Cette méthode permet de récupérer le temple sur lequel un cristal est posé
     * @param cristal la couleur du cristal recherché
     * @return le temple portant le cristal. null si apprenti porteur
     */
    public static Temple rechercheCristal(int cristal){
        for (Temple temple: temples){
            if (temple.getCristal()==cristal)
                return temple;
        }
        return null;
    }
}
