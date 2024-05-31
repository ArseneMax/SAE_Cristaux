package modele;

import java.util.ArrayList;
import java.util.HashMap;

public class Configuration {
    private ArrayList<Temple> templesVus;
    private int nombreDePas;
    private HashMap<Integer, Integer> etat;

    public Configuration(ArrayList<Temple> parTemplesVus, HashMap<Integer,Integer> parEtat, int parPas){
        etat = parEtat;
        templesVus = parTemplesVus;
        nombreDePas = parPas;

    }

    public ArrayList<Temple> getTemplesVus() {
        return templesVus;
    }

    public HashMap<Integer,Integer> getEtat() {
        return etat;
    }

    public int getNombreDePas() {
        return nombreDePas;
    }

    /**
     * Compare les configurations
     * @param parconf
     * @return 0 si elles n'ont pas vu les mêmes temples, 1 si le nombre de pas de this est plus petit et -1 sinon
     */
    public int compareTo(Configuration parconf){
        if(parconf.getTemplesVus() == templesVus ){
            if(nombreDePas>=parconf.getNombreDePas()){
                return -1;
            }
            else return 1;
        }
        return 0;
    }
}
