package modele;

import javafx.scene.layout.VBox;
import vue.VBoxCanvas;
import vue.VBoxRoot;

import java.util.*;

public class Algorithmes implements ConstantesCanvas{


    public static ArrayList<Position> triInsertion() {

        return new ArrayList<Position>();
    }
    public static ArrayList<Position> triBulle() {

        return new ArrayList<Position>();
    }

    /**
     * Fait un tri par séléction des temples en fonction de leur cristaux et retourne une liste de déplacement
     * @return une liste de position indiquant les déplacements a faire pour le tri
     */
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


    public static ArrayList<Position> algoHeuristique(){
        int distanceMin=5000;
        int cristalTenu=0;
        Collection<Temple> temples = VBoxRoot.getApprenti().getTemples();
        Temple templeCible =new Temple();
        HashMap<Integer,Integer> save = new HashMap<>();
        ArrayList<Position> listePosition = new ArrayList<>();
        while (!VBoxRoot.getApprenti().fini()){
            if(cristalTenu==0){
                for (Temple temple : temples){
                    if(listePosition.size()==0)//On garde une save des temples de base
                        save.put(temple.getCouleur(), temple.getCristal());
                    Temple templeCristal = Apprenti.rechercheCristal(temple.getCouleur());
                    int distance = Position.distance(temple.getPosition(),templeCristal.getPosition())+Position.distance(VBoxCanvas.getPositionApprenti(),templeCristal.getPosition());
                    if (distanceMin>distance && !temple.bonCristal()){
                        templeCible = temple;
                        distanceMin=distance;
                    }
                }
                listePosition.add(Apprenti.rechercheCristal(templeCible.getCouleur()).getPosition());
                listePosition.add(templeCible.getPosition());
                cristalTenu=templeCible.getCristal();
                Apprenti.rechercheCristal(templeCible.getCristal()).setCristal(0);
                templeCible.setCristal(templeCible.getCouleur());
            }
            else {
                templeCible=Apprenti.getTemple(cristalTenu);
                listePosition.add(templeCible.getPosition());
                cristalTenu= templeCible.getCristal();
                templeCible.setCristal(templeCible.getCouleur());
            }
            System.out.println(cristalTenu+ " "+templeCible.getCristal());
        }

        Apprenti.resetTemples(save);

        return listePosition;

    }


    public static ArrayList<Position> algoDijkstra(){
        ArrayList<Position> listePosition = new ArrayList<>();
        PriorityQueue file = new PriorityQueue();
        Collection<Temple> temples = VBoxRoot.getApprenti().getTemples();
        HashMap<Integer,Integer> etatDepart = new HashMap<>();
        TreeSet<Configuration> dejaVues = new TreeSet<>();
        etatDepart.put(0,0);
        for (Temple temple : temples){
            etatDepart.put(temple.getCouleur(), temple.getCristal());
        }
        Configuration depart = new Configuration(new ArrayList<>(),etatDepart,0,VBoxCanvas.getPositionApprenti());
        file.add(depart);
        dejaVues.add(depart);
        while (!file.isEmpty()){
            Configuration courante = (Configuration) file.poll();
            System.out.println(courante.getEtat());
            for(Configuration conf : courante.accessibles()){
                if (!dejaVues.contains(conf)){
                    dejaVues.add(conf);
                    file.add(conf);
                }
            }
        }
        Configuration meilleureConf = null;
        for (Configuration conf : dejaVues){
            if (meilleureConf==null ){
                if ( conf.getTemplesVus().size()== VBoxRoot.getApprenti().getTemples().size()-1)
                    meilleureConf = conf;

            }
            else if (meilleureConf.getNombreDePas()> conf.getNombreDePas()) {
                meilleureConf=conf;
            }
        }
        for (Temple temple :meilleureConf.getTemplesVus()){
            listePosition.add(temple.getPosition());
        }
        return listePosition;
    }

}
