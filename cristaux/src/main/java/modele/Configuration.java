package modele;

import vue.VBoxRoot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.lang.Comparable;

public class Configuration implements Comparable<Configuration> {
    private Position pos;
    private ArrayList<Temple> templesVus;
    private int nombreDePas;
    private HashMap<Integer, Integer> etat;

    public Configuration(ArrayList<Temple> parTemplesVus, HashMap<Integer,Integer> parEtat, int parPas,Position parPos){
        etat = parEtat;
        templesVus = parTemplesVus;
        nombreDePas = parPas;
        pos=parPos;

    }
    public String toString(){
        return "{nombre de pas : "+nombreDePas+ "; etat : "+etat+"; temples vus : "+templesVus+"}";
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
        if(parconf.getNombreDePas()>nombreDePas )
            return -1;
        else if(parconf.getNombreDePas()<nombreDePas)
            return 1;
        return 0;
    }

    public boolean equals(Configuration parConf){
        if(parConf.getEtat().equals(etat))
            return true;
        return false;
    }

    public ArrayList<Temple> changeTemplesVus(Temple parTemple){
        ArrayList<Temple> templesVusAutres = new ArrayList<>();
        for (Temple temple : templesVus){
            templesVusAutres.add(temple);
        }
        templesVusAutres.add(parTemple);
        return templesVusAutres;
    }

    public HashMap<Integer,Integer> changeEtat(Temple temple){
        HashMap<Integer,Integer> etatChange = new HashMap<>();
        for (int i = 0 ;i<etat.size();i++){
            if (i==0)
                etatChange.put(i,temple.getCristal());
            else if(i==temple.getCouleur())
                etatChange.put(i,etat.get(0));
            else
                etatChange.put(i,etat.get(i));
        }
        return etatChange;
    }

    public Collection<Configuration> accessibles(){
        Collection<Temple> temples = VBoxRoot.getApprenti().getTemples();
        Collection<Configuration> confAccessibles = new ArrayList<>();
        for (Temple temple : temples){
            if (!templesVus.contains(temple)){
                confAccessibles.add(new Configuration(changeTemplesVus(temple),changeEtat(temple),nombreDePas+Position.distance(pos,temple.getPosition()),temple.getPosition()));
            }
        }
        return confAccessibles;
    }

    public Configuration trouveDoublon(Collection<Configuration> listeConf){
        for (Configuration conf : listeConf){
            if (this.equals(conf))
                return conf;
        }
        return null;
    }


}
