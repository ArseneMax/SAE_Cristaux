package modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class LectureScenario implements ConstantesCanvas{
    public static Collection<Temple> lecture(File fichierScenario){
        Collection<Temple> templesDuScenario = new ArrayList<>();
        try{
            Scanner scanner = new Scanner(fichierScenario);
            Temple temple;
            while (scanner.hasNext()){
                int posx = scanner.nextInt() + LARGEUR_CANVAS/(2*CARRE);
                int posy = scanner.nextInt() + HAUTEUR_CANVAS/(2*CARRE);
                int couleur = scanner.nextInt();
                int crystal = scanner.nextInt();
                temple = new Temple(new Position(posx, posy), couleur, crystal);
                System.out.println(temple.toString());
                templesDuScenario.add(temple);
            }
            scanner.close();
        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        return templesDuScenario;
    }
}
