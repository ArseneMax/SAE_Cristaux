package test;

import static org.junit.Assert.*;

import modele.*;
import controleur.*;
import org.junit.Test;
import vue.VBoxCanvas;

import java.util.ArrayList;
import java.util.Collection;


public class TestTemple {

    @Test
    public final void bonCristalTest(){
        Temple temple = new Temple(new Position(12,12),4,4);
        Temple temple1 = new Temple(new Position(13,13),5,8);
        if (!temple.bonCristal())
            fail("Le temple ne detecte pas qu'il a le bon cristal");
        if(temple1.bonCristal())
            fail("Le temple ne détecte pas qu'il n'a pas le bon cristal");
    }

    @Test
    public final void templeTest(){
        int cristal = 2;
        Temple temple = new Temple(new Position(5,4),2,5);
        temple.setCristal(cristal);
        if (temple.getCristal()!=cristal)
            fail("Le temple n'a pas pris en compte le chnagement de cristal");


    }
}
