package modele;

import vue.VBoxRoot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Tri implements ConstantesCanvas{


    public static ArrayList<Position> triInsertion() {

        return new ArrayList<Position>();
    }
    public static ArrayList<Position> triBulle() {

        return new ArrayList<Position>();
    }

    public static ArrayList<Position> triSelection(){
        Collection<Temple> listeTemple = VBoxRoot.getApprenti().getTemples();
        if (listeTemple==null)
            return null;
        ArrayList<Position> listePosition = new ArrayList<>();
        for (int i =0; i<listeTemple.size();i++){
            Temple templeIndex = Apprenti.getTemple(i);
            for (Temple temple: listeTemple){
                if (temple.getCristal()==i){
                    Temple templeCristal  = temple;
                    listePosition.add(templeIndex.getPosition());
                    listePosition.add(templeCristal.getPosition());
                    listePosition.add(templeIndex.getPosition());
                }
            }

        }
        listePosition.add(new Position(LARGEUR_CANVAS/CARRE/2,HAUTEUR_CANVAS/CARRE/2));
        System.out.println(listePosition);

        return listePosition;
    }

}
