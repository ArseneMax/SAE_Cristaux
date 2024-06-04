package test;

import static org.junit.Assert.*;

import modele.*;
import controleur.*;
import org.junit.Test;
import vue.VBoxCanvas;

import java.util.ArrayList;
import java.util.Collection;

public class TestApprenti {
    @Test
    public final void recupTest(){
        Apprenti apprenti = new Apprenti();
        Temple temple = new Temple(new Position(0,0),4,5);
        int apprentiAttendu = temple.getCristal();
        int templeAttendu = apprenti.getCristal();
        apprenti.recupCristal(temple);
        if (apprenti.getCristal()!=apprentiAttendu)
            fail("L'apprenti n'a pas récupérer le cristal du temple");
        if (temple.getCristal()!=templeAttendu)
            fail("Le temple n'a pas reçu le cristal de l'apprenti");
    }

    @Test
    public final void finTest(){
        Collection<Temple> temples = new ArrayList<>();
        temples.add(new Temple(new Position(10,12),3,3));
        temples.add(new Temple(new Position(5,15),1,1));
        temples.add(new Temple(new Position(1,1),2,2));
        Apprenti apprenti = new Apprenti(temples);
        if (!apprenti.fini())
            fail("L'apprenti ne detecte pas que tous les temples sont complet");
        temples.add(new Temple(new Position(14,12),5,6));
        if (apprenti.fini())
            fail("L'apprenti ne détecte pas qu'il reste un temple avec le mauvais cristal");
    }

    @Test
    public final void rechercheTest(){
        Temple temple1 = new Temple(new Position(1,1),1,2);
        Temple temple2  =new Temple(new Position(4,5),2,1);
        Collection<Temple> temples = new ArrayList<>();
        temples.add(temple1);
        temples.add(temple2);
        Apprenti apprenti = new Apprenti(temples);
        if (Apprenti.rechercheCristal(2)!=temple1)
            fail("L'apprenti n'a pas su trouver le cristal");
    }
}
