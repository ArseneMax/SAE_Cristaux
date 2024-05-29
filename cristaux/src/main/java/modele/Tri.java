package modele;

import vue.VBoxCanvas;
import vue.VBoxRoot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

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
        HashMap<Integer,Integer> save = new HashMap<>();
        ArrayList<Position> listePosition = new ArrayList<>();
        for (int i =1; i<listeTemple.size();i++){
            Temple templeIndex = Apprenti.getTemple(i);
            Temple templeCristal = null;
            for (Temple temple: listeTemple){
                if(i==1)//On garde une save des temples de base
                    save.put(temple.getCouleur(), temple.getCristal());
                if (temple.getCristal()==templeIndex.getCouleur() && !templeIndex.bonCristal()){
                    templeCristal = temple;
                    //On ajoute les déplacements à effectuer
                    listePosition.add(templeIndex.getPosition());
                    listePosition.add(temple.getPosition());
                    listePosition.add(templeIndex.getPosition());


                }

            }
            //On effectue les changements de cristaux
            if (templeCristal!=null){
                templeCristal.setCristal(templeIndex.getCristal());
                templeIndex.setCristal(i);
            }



        }
        System.out.println(listePosition);

        Apprenti.resetTemples(save);

        return listePosition;
    }

}
