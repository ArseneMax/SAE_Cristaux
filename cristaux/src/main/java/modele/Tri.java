package modele;

import vue.VBoxRoot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Tri {



    public static int[] triBulle(int[] liste) {

        return liste;
    }

    public static ArrayList<Position> triSelection(){
        Collection<Temple> listeTemple = VBoxRoot.getApprenti().getTemples();
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

        return listePosition;
    }

}
