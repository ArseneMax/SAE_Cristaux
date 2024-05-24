package modele;

import vue.VBoxCanvas;

import java.util.Collection;

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
}
