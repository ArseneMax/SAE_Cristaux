package modele;

import vue.VBoxCanvas;

import java.util.Collection;
import java.util.HashMap;

/**
 * Classe qui va gérer notre apprenti
 */
public class Apprenti {
    private Position position;
    private static Collection<Temple> temples;
    private int cristal =0;
    String scenario;

    public Apprenti(){ position = new Position(0,0); }
    public Apprenti(Collection<Temple> parTemples){ position = new Position(0,0);temples=parTemples;}

    public void setTemples(Collection<Temple> parTemple ) {
        temples = parTemple;
        VBoxCanvas.placetemple(temples);
    }
    public void setScenario(String parScenario){
        scenario=parScenario.replace(".txt","");
        String numero=scenario.replace("scenario","");
        scenario="scénario "+numero;
    }

    public String getScenario() {
        return scenario;
    }

    public Collection<Temple> getTemples(){
        return temples;
    }

    public int getCristal() {
        return cristal;
    }

    /**
     * Fait un échange du cristal de l'apprenti et celui du temple
     * @param temple le temple où l'apprenti se trouve
     */
    public void recupCristal(Temple temple){
        int ancienCristal = cristal;
        cristal= temple.getCristal();
        temple.setCristal(ancienCristal);
    }

    /**
     * Parcours la liste des temples et vérifie si ils ont tous le bon cristal
     * @return true si c'est le cas sinon return false
     */
    public boolean fini(){
        for (Temple temple: temples){
            if(!temple.bonCristal())
                return false;
        }
        return true;
    }

    /**
     *Parcours les temples pour trouver celui qui correspond a la couleur demandée
     * @param couleur
     * @return le temple de la couleur demandée ou null si le temple n'existe pas
     */
    public static Temple getTemple(int couleur){
        for(Temple temple:temples){
            if (temple.getCouleur()==couleur)
                return temple;
        }
        return null;
    }

    /**
     * Cette méthode permet de reinitialiser les temples tels qu'ils apparaissent dans le scénario
     * @param save dictionnaire comprenant la couleur et le cristal initiaux de chaques temples
     */
    public static void resetTemples(HashMap save){
        for (Temple temple : temples){
            temple.setCristal((int) save.get(temple.getCouleur()));
        }
    }

    /**
     * Cette méthode permet de récupérer le temple sur lequel un cristal est posé
     * @param cristal la couleur du cristal recherché
     * @return le temple portant le cristal. null si apprenti porteur
     */
    public static Temple rechercheCristal(int cristal){
        for (Temple temple: temples){
            if (temple.getCristal()==cristal)
                return temple;
        }
        return null;
    }
}
