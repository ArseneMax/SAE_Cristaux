package modele;

import java.util.ArrayList;

/**
 * Classe qui nous permet d'afficher les résultats dans VBoxAffichageTable
 */
public class Resultat {
    private int nbPas;
    private String nomAlgo;
    private ArrayList<Integer> templesVus;
    private String scenario;

     public Resultat(int nbPas, String nomAlgo, ArrayList<Integer> templesVus,String scenario) {
         this.nbPas = nbPas;
         this.nomAlgo = nomAlgo;
         this.templesVus=templesVus;
         this.scenario=scenario;
     }

    public String getScenario() {
        return scenario;
    }

    public String getNomAlgo() {
        return nomAlgo;
    }

    public int getNbPas() {
        return nbPas;
    }
    public ArrayList<Integer> getTemplesVus() {
        return templesVus;
    }
}
