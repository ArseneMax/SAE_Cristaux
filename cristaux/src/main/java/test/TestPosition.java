package test;

import static org.junit.Assert.*;

import modele.*;
import controleur.*;
import org.junit.Test;
import vue.VBoxCanvas;

import java.util.ArrayList;
import java.util.Collection;

public class TestPosition {

    @Test
    public final void compareTest(){
        Position position1 = new Position(12,12);
        Position position2 = new Position(1,12);
        Position position3 = new Position(18,12);
        Position positionTest = new Position(12,12);
        if (!positionTest.equals(position1))
            fail("Les positions sont égales");
        if (positionTest.compareTo(position2)!=1)
            fail("L'abscisse de positionTest est plus grand");
        if (positionTest.compareTo(position3)!=-1)
            fail("L'abscisse de positionTest est plus petit");
    }

    @Test
    public final void distanceTest(){
        Position position1 = new Position(10,10);
        Position position2 = new Position(position1.getAbscisse()+3,position1.getOrdonnee());
        Position position3 = new Position(position1.getAbscisse()-7,position1.getOrdonnee()-3);
        if (Position.distance(position1,position2)!=3)
            fail("Le calcul avec une difference positive fonctionne pas");
        if (Position.distance(position1,position3)!=10)
            fail("Le calcul avec une différence négative de marche pas");
    }
}
