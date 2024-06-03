package modele;

import java.util.TreeSet;

public class Resultat implements ConstantesCanvas{
    private int nbPas;
    private String nomAlgo;
    private TreeSet<Integer> templesVus;

     public Resultat(int nbPas, String nomAlgo, TreeSet<Integer> templesVus) {
         this.nbPas = nbPas;
         this.nomAlgo = nomAlgo;
         this.templesVus=templesVus;
     }
}
