package modele;

import vue.VBoxCanvas;

import java.util.Collection;

public class Apprenti {
    private Position position;
    private Collection<Temple> temples;
    private int cristal =0;

    public Apprenti(){
        position = new Position(0,0);
    }

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
}
