package vue;

import controleur.Controleur;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import modele.Apprenti;
import modele.Position;
import modele.Resultat;
import modele.Temple;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Timer;
import java.util.TimerTask;

public class VBoxCanvas extends VBox  implements modele.ConstantesCanvas {
    public static Label labelNbPas;
    public Label nomTri;
    public Canvas canvasCarte;
    public  static GraphicsContext graphicsContext2D;
    public static Position positionApprenti = new Position(LARGEUR_CANVAS/CARRE/2,HAUTEUR_CANVAS/CARRE/2) ;
    public Controleur controleur ;


    public VBoxCanvas(){
        labelNbPas = new Label (">Nombre de pas : 0");
        nomTri  =new Label("");
        canvasCarte = new Canvas();
        canvasCarte.setWidth(LARGEUR_CANVAS);
        canvasCarte.setHeight(HAUTEUR_CANVAS);
        graphicsContext2D = canvasCarte.getGraphicsContext2D();
        graphicsContext2D.setStroke(COULEUR_GRILLE);
        controleur = VBoxRoot.getControleur();
        Button bouton = new Button("Lancer l'algorithme");
        bouton.setUserData("Tri");
        Button recup = new Button("Récupérer le cristal");
        recup.setUserData("recup");
        Button restart = new Button("Réinitialiser");
        restart.setUserData("restart");


        for ( int i=0 ; i<LARGEUR_CANVAS; i+= CARRE){
            for (int j = 0 ; j<HAUTEUR_CANVAS; j+=CARRE){
                graphicsContext2D.strokeRect(i,j,CARRE,CARRE);
            }
        }
        HBox boxLabel = new HBox();
        boxLabel.getChildren().addAll(labelNbPas,nomTri);
        HBox.setMargin(labelNbPas,new Insets(10));
        HBox.setMargin(nomTri,new Insets(10));
        this.getChildren().add(boxLabel);
        VBox.setMargin(boxLabel,new Insets(30));
        this.getChildren().add(canvasCarte);
        VBox.setMargin(canvasCarte, new Insets(10));


        bouton.setOnAction(controleur);

        recup.setOnAction(controleur);

        restart.setOnAction(controleur);
        HBox hbox = new HBox();
        hbox.getChildren().addAll(recup,bouton,restart);
        HBox.setMargin(recup,new Insets(10));
        HBox.setMargin(bouton,new Insets(10));
        HBox.setMargin(restart,new Insets(10));
        this.getChildren().add(hbox);
        VBox.setMargin(bouton,new Insets(30));


        int numCol =-15;
        graphicsContext2D.setFill(COULEUR_GRILLE);
        for (int i=CARRE;i< LARGEUR_CANVAS;i+=CARRE){
            if (numCol>-10)
                graphicsContext2D.fillText(Integer.toString(numCol),i+CARRE/3,CARRE/2);
            else
                graphicsContext2D.fillText(Integer.toString(numCol),i+CARRE/3-5,CARRE/2);

            numCol++;
        }

        int numLigne =-15;
        graphicsContext2D.setFill(COULEUR_GRILLE);
        for (int i=CARRE;i< HAUTEUR_CANVAS;i+=CARRE){
            if (numLigne>-10)
                graphicsContext2D.fillText(Integer.toString(numLigne),CARRE/3,i+CARRE/2);
            else
                graphicsContext2D.fillText(Integer.toString(numLigne),CARRE/3-5,i+CARRE/2);
            numLigne++;
        }

        graphicsContext2D.setFill(COULEUR_APPRENTI);
        graphicsContext2D.fillOval(positionApprenti.getAbscisse()*CARRE + CARRE/5,positionApprenti.getOrdonnee()*CARRE+CARRE/5,LARGEUR_OVALE,HAUTEUR_OVALE);

        canvasCarte.setOnMouseClicked((event->{
            if(VBoxRoot.getApprenti().getTemples() != null){
                int abscisse =(int) event.getX() /CARRE;
                int ordonnee = (int) event.getY() / CARRE;
                Position positionCliquee = new Position(abscisse,ordonnee);
                //graphicsContext2D.setFill(COULEUR_SELECT);
                //graphicsContext2D.fillRect(positionCliquee.getAbscisse()*CARRE+2,positionCliquee.getOrdonnee()*CARRE+2, CARRE-5,CARRE-5);
                this.deplacementAvecTimer(positionApprenti,positionCliquee);
                System.out.println(positionCliquee);
            }
            else
                System.out.println("Veuillez d'abord choisir un scenario");

        }));

    }







    private void deplacementAvecTimer(Position positionCourante,Position positionCible){
        Timer timer = new Timer();
        Apprenti apprenti = VBoxRoot.getApprenti();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                //Effacer apprenti
                graphicsContext2D.setFill(COULEUR_BLANC);
                graphicsContext2D.fillRect(positionApprenti.getAbscisse()*CARRE+2,positionApprenti.getOrdonnee()*CARRE+2, CARRE-5,CARRE-5);

                //Si apprenti était sur temple
                Temple temple = surTemple(positionCourante);
                if (temple!=null)
                    placeUnTemple(temple);


                //Déplacer la position de l'apprenti
                if (positionCourante.getAbscisse()!= positionCible.getAbscisse()){
                    positionCourante.setAbscisse(positionCourante.getAbscisse()- positionCourante.compareTo(positionCible));
                }
                else{
                    positionCourante.setOrdonnee(positionCourante.getOrdonnee()-positionCourante.compareTo(positionCible));
                }


                //Dessiner apprenti
                //graphicsContext2D.fillRect(positionApprenti.getAbscisse()*CARRE+2,positionApprenti.getOrdonnee()*CARRE+2, CARRE-5,CARRE-5);
                graphicsContext2D.setFill(COULEUR_APPRENTI);
                graphicsContext2D.fillOval(positionApprenti.getAbscisse()*CARRE + CARRE/5,positionApprenti.getOrdonnee()*CARRE+CARRE/5,LARGEUR_OVALE,HAUTEUR_OVALE);

                //Dessiner le cristal s'il en a un
                if (apprenti.getCristal()!=0){
                    graphicsContext2D.setFill(COULEURS_TEMPLES[apprenti.getCristal()]);
                    graphicsContext2D.fillOval(positionApprenti.getAbscisse() * CARRE + 10, positionApprenti.getOrdonnee() * CARRE + 10, 7, 7);
                }

                //Ajout pas
                Platform.runLater(()->{
                    labelNbPas.setText("Nombre de pas : "+Position.getNbPas());
                });

                //Si fini
                if (positionCourante.compareTo(positionCible)==0){
                    timer.cancel();
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask,1000,200);
    }
    public void deplacementAvecTimerListe(Position positionCourante, ArrayList<Position> positionsCibles){
        int [] indice = {0};
        Timer timer = new Timer();
        Apprenti apprenti = VBoxRoot.getApprenti();
        ArrayList<Integer> listeTemplesVus = new ArrayList<>();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                //Prochaine position
                Position positionCible = positionsCibles.get(indice[0]);

                //Effacer apprenti
                graphicsContext2D.setFill(COULEUR_BLANC);
                graphicsContext2D.fillRect(positionApprenti.getAbscisse()*CARRE+2,positionApprenti.getOrdonnee()*CARRE+2, CARRE-5,CARRE-5);

                //Si apprenti était sur temple
                Temple temple = surTemple(positionCourante);
                if (temple!=null)
                    placeUnTemple(temple);



                //Déplacer la position de l'apprenti
                if (positionCourante.getAbscisse()!= positionCible.getAbscisse()){
                    positionCourante.setAbscisse(positionCourante.getAbscisse()- positionCourante.compareTo(positionCible));
                }
                else{
                    positionCourante.setOrdonnee(positionCourante.getOrdonnee()-positionCourante.compareTo(positionCible));
                }


                //Dessiner apprenti
                graphicsContext2D.setFill(COULEUR_APPRENTI);
                graphicsContext2D.fillOval(positionApprenti.getAbscisse()*CARRE + CARRE/4,positionApprenti.getOrdonnee()*CARRE+CARRE/4,LARGEUR_OVALE,HAUTEUR_OVALE);//Dessiner le cristal s'il en a un

                //Dessiner le cristal s'il en a un
                if (apprenti.getCristal()!=0){
                    graphicsContext2D.setFill(COULEURS_TEMPLES[apprenti.getCristal()]);
                    graphicsContext2D.fillOval(positionApprenti.getAbscisse() * CARRE + 10, positionApprenti.getOrdonnee() * CARRE + 10, 7, 7);
                }

                //Ajout pas
                Platform.runLater(()->{
                    labelNbPas.setText("Nombre de pas : "+Position.getNbPas());
                });

                //Si cible atteinte
                if (positionCourante.compareTo(positionCible)==0){
                    temple=surTemple(positionCourante);
                    if(temple!=null){
                        apprenti.recupCristal(temple);
                        listeTemplesVus.add(temple.getCouleur());
                    }

                    indice[0]++;

                }
                //Si fini
                if (indice[0]>positionsCibles.size()-1){
                    Resultat resultat= new Resultat(Position.getNbPas(),nomTri.getText(),listeTemplesVus,VBoxRoot.getApprenti().getScenario());
                    VBoxRoot.getVueTable().getResultats().add(resultat);
                    VBoxRoot.getVueTable().updateTable();
                    timer.cancel();
                }


            }
        };
        timer.scheduleAtFixedRate(timerTask,500,50);
    }

    /**
     * réinitialise le jeu en redessinant par dessus
     */
    public static void reinitialiser() {
        Position.setNbPas(0);
        labelNbPas.setText("Nombre de pas : "+Position.getNbPas());
        graphicsContext2D.setFill(COULEUR_BLANC);

        graphicsContext2D.fillRect(0,0,LARGEUR_CANVAS,HAUTEUR_CANVAS);
        graphicsContext2D.setStroke(COULEUR_GRILLE);
        for (int i = 0; i < LARGEUR_CANVAS-20; i += CARRE) {
            for (int j = 0; j < HAUTEUR_CANVAS-20; j += CARRE) {
                graphicsContext2D.strokeRect(i, j, CARRE, CARRE);
            }

        }

        int numCol =-15;
        graphicsContext2D.setFill(COULEUR_GRILLE);
        for (int i=CARRE;i< LARGEUR_CANVAS;i+=CARRE){
            if (numCol>-10)
                graphicsContext2D.fillText(Integer.toString(numCol),i+CARRE/3,CARRE/2);
            else
                graphicsContext2D.fillText(Integer.toString(numCol),i+CARRE/3-5,CARRE/2);

            numCol++;
        }

        int numLigne =-15;
        graphicsContext2D.setFill(COULEUR_GRILLE);
        for (int i=CARRE;i< HAUTEUR_CANVAS;i+=CARRE){
            if (numLigne>-10)
                graphicsContext2D.fillText(Integer.toString(numLigne),CARRE/3,i+CARRE/2);
            else
                graphicsContext2D.fillText(Integer.toString(numLigne),CARRE/3-5,i+CARRE/2);
            numLigne++;
        }
        positionApprenti =new Position(LARGEUR_CANVAS/CARRE/2, HAUTEUR_CANVAS/CARRE/2);
        graphicsContext2D.setFill(COULEUR_APPRENTI);
        graphicsContext2D.fillOval(positionApprenti.getAbscisse()*CARRE + CARRE/5, positionApprenti.getOrdonnee()*CARRE + CARRE/5, LARGEUR_OVALE, HAUTEUR_OVALE);

    }

    /**
     *méthode qui dessine les temples
     * @param temples
     */
    public static void placetemple(Collection<Temple> temples){

        reinitialiser();

        for (Temple temple : temples){
            graphicsContext2D.setFill(COULEURS_TEMPLES [temple.getCouleur()]);
            graphicsContext2D.fillRect(temple.getPosition().getAbscisse()*CARRE +2 , temple.getPosition().getOrdonnee()*CARRE+2, CARRE-4, CARRE-4);
            graphicsContext2D.setFill(Paint.valueOf("darkgrey"));
            graphicsContext2D.fillRect(temple.getPosition().getAbscisse()*CARRE +7 , temple.getPosition().getOrdonnee()*CARRE+7, CARRE/2, CARRE/2);
            if (temple.getCristal()!=0) {
                graphicsContext2D.setFill(COULEURS_TEMPLES[temple.getCristal()]);
                graphicsContext2D.fillOval(temple.getPosition().getAbscisse() * CARRE + 10, temple.getPosition().getOrdonnee() * CARRE + 10, 7, 7);
            }

        }
    }

    /**
     * méthode qui dessine un temple
     * @param temple
     */
    public static void placeUnTemple(Temple temple){
        graphicsContext2D.setFill(COULEURS_TEMPLES [temple.getCouleur()]);
        graphicsContext2D.fillRect(temple.getPosition().getAbscisse()*CARRE +2 , temple.getPosition().getOrdonnee()*CARRE+2, CARRE-4, CARRE-4);
        graphicsContext2D.setFill(Paint.valueOf("darkgrey"));
        graphicsContext2D.fillRect(temple.getPosition().getAbscisse()*CARRE +7 , temple.getPosition().getOrdonnee()*CARRE+7, CARRE/2, CARRE/2);
        if (temple.getCristal()!=0) {
            graphicsContext2D.setFill(COULEURS_TEMPLES[temple.getCristal()]);
            graphicsContext2D.fillOval(temple.getPosition().getAbscisse() * CARRE + 10, temple.getPosition().getOrdonnee() * CARRE + 10, 7, 7);
        }
    }

    /**
     * méthode qui vérifie si la position est la même que celle d'un temple
     * @param pos
     * @return
     */
    public static Temple surTemple(Position pos){
        for (Temple temple : VBoxRoot.getApprenti().getTemples()){
            if (temple.getPosition().compareTo(pos)==0)
                return temple;
        }
        return null;
    }

    public static Position getPositionApprenti(){
        return positionApprenti;
    }



}
