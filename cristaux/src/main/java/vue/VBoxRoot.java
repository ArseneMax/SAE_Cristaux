package vue;

import modele.Position;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class VBoxRoot extends VBox  implements modele.ConstantesCanvas {
    public Label labelNbPas;
    public Canvas canvasCarte;
    public  GraphicsContext graphicsContext2D;
    public Position positionApprenti = new Position(LARGEUR_CANVAS/CARRE/2,HAUTEUR_CANVAS/CARRE/2) ;


    public VBoxRoot(){
        labelNbPas = new Label (">Nombre de pas : 0");
        canvasCarte = new Canvas();
        canvasCarte.setWidth(LARGEUR_CANVAS);
        canvasCarte.setHeight(HAUTEUR_CANVAS);
        graphicsContext2D = canvasCarte.getGraphicsContext2D();
        graphicsContext2D.setStroke(COULEUR_GRILLE);
        Button bouton = new Button("pas au hasard");


        for ( int i=0 ; i<LARGEUR_CANVAS; i+= CARRE){
            for (int j = 0 ; j<HAUTEUR_CANVAS; j+=CARRE){
                graphicsContext2D.strokeRect(i,j,CARRE,CARRE);
            }
        }

        this.getChildren().add(labelNbPas);
        VBox.setMargin(labelNbPas,new Insets(30));
        this.getChildren().add(canvasCarte);
        VBox.setMargin(canvasCarte, new Insets(30));


        bouton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ArrayList<Position> liste = new ArrayList<Position>();
                liste.add(new Position(5,9));
                liste.add(new Position(6,4));
                liste.add(new Position(1,1));
                deplacementAvecTimerListe(positionApprenti,liste);
            }
        });
        this.getChildren().add(bouton);
        VBox.setMargin(bouton,new Insets(30));


        int numCol =1;
        graphicsContext2D.setFill(COULEUR_GRILLE);
        graphicsContext2D.fillText(Integer.toString(0),CARRE/3,CARRE/3+5);
        for (int i=CARRE;i< LARGEUR_CANVAS;i+=CARRE){
            graphicsContext2D.fillText(Integer.toString(numCol),i+CARRE/3,CARRE/2);
            numCol++;
        }

        int numLigne =1;
        graphicsContext2D.setFill(COULEUR_GRILLE);
        for (int i=CARRE;i< HAUTEUR_CANVAS;i+=CARRE){
            graphicsContext2D.fillText(Integer.toString(numLigne),CARRE/3,i+CARRE/2);
            numLigne++;
        }

        graphicsContext2D.setFill(COULEUR_APPRENTI);
        graphicsContext2D.fillOval(positionApprenti.getAbscisse()*CARRE + CARRE/4,positionApprenti.getOrdonnee()*CARRE+CARRE/4,LARGEUR_OVALE,HAUTEUR_OVALE);

        canvasCarte.setOnMouseClicked((event->{
            int abscisse =(int) event.getX() /CARRE;
            int ordonnee = (int) event.getY() / CARRE;
            Position positionCliquee = new Position(abscisse,ordonnee);
            graphicsContext2D.setFill(COULEUR_SELECT);
            graphicsContext2D.fillRect(positionCliquee.getAbscisse()*CARRE+2,positionCliquee.getOrdonnee()*CARRE+2, CARRE-5,CARRE-5);
            this.deplacementAvecTimer(positionApprenti,positionCliquee);
            System.out.println(positionCliquee);

        }));

    }





    public static void deplacement(Position parDepart,Position parFin){
        while (parDepart.compareTo(parFin)!=0){
            System.out.println(parDepart+" n'est pas egal à "+parFin);
            if (parDepart.getAbscisse()!= parFin.getAbscisse()){
                parDepart.setAbscisse(parDepart.getAbscisse()- parDepart.compareTo(parFin));
            }
            else{
                parDepart.setOrdonnee(parDepart.getOrdonnee()-parDepart.compareTo(parFin));
            }
        }
    }

    private void deplacementAvecTimer(Position positionCourante,Position positionCible){
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                //Effacer apprenti
                graphicsContext2D.setFill(COULEUR_BLANC);
                graphicsContext2D.fillRect(positionApprenti.getAbscisse()*CARRE+2,positionApprenti.getOrdonnee()*CARRE+2, CARRE-5,CARRE-5);

                //Déplacer la position de l'apprenti
                if (positionCourante.getAbscisse()!= positionCible.getAbscisse()){
                    positionCourante.setAbscisse(positionCourante.getAbscisse()- positionCourante.compareTo(positionCible));
                }
                else{
                    positionCourante.setOrdonnee(positionCourante.getOrdonnee()-positionCourante.compareTo(positionCible));
                }


                //Dessiner apprenti
                graphicsContext2D.fillRect(positionApprenti.getAbscisse()*CARRE+2,positionApprenti.getOrdonnee()*CARRE+2, CARRE-5,CARRE-5);
                graphicsContext2D.setFill(COULEUR_APPRENTI);
                graphicsContext2D.fillOval(positionApprenti.getAbscisse()*CARRE + CARRE/4,positionApprenti.getOrdonnee()*CARRE+CARRE/4,LARGEUR_OVALE,HAUTEUR_OVALE);


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

    private void deplacementAvecTimerListe(Position positionCourante, ArrayList<Position> positionsCibles){
        int [] indice = {0};
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                //Prochaine position
                Position positionCible = positionsCibles.get(indice[0]);
                graphicsContext2D.setFill(COULEUR_SELECT);
                graphicsContext2D.fillRect(positionCible.getAbscisse()*CARRE+2,positionCible.getOrdonnee()*CARRE+2, CARRE-5,CARRE-5);

                //Effacer apprenti
                graphicsContext2D.setFill(COULEUR_BLANC);
                graphicsContext2D.fillRect(positionApprenti.getAbscisse()*CARRE+2,positionApprenti.getOrdonnee()*CARRE+2, CARRE-5,CARRE-5);

                //Déplacer la position de l'apprenti
                if (positionCourante.getAbscisse()!= positionCible.getAbscisse()){
                    positionCourante.setAbscisse(positionCourante.getAbscisse()- positionCourante.compareTo(positionCible));
                }
                else{
                    positionCourante.setOrdonnee(positionCourante.getOrdonnee()-positionCourante.compareTo(positionCible));
                }


                //Dessiner apprenti
                graphicsContext2D.fillRect(positionApprenti.getAbscisse()*CARRE+2,positionApprenti.getOrdonnee()*CARRE+2, CARRE-5,CARRE-5);
                graphicsContext2D.setFill(COULEUR_APPRENTI);
                graphicsContext2D.fillOval(positionApprenti.getAbscisse()*CARRE + CARRE/4,positionApprenti.getOrdonnee()*CARRE+CARRE/4,LARGEUR_OVALE,HAUTEUR_OVALE);


                //Ajout pas
                Platform.runLater(()->{
                    labelNbPas.setText("Nombre de pas : "+Position.getNbPas());
                });

                //Si cible atteinte
                if (positionCourante.compareTo(positionCible)==0){
                    indice[0]++;

                }
                //Si fini
                if (indice[0]>positionsCibles.size()){
                    timer.cancel();
                }


            }
        };
        timer.scheduleAtFixedRate(timerTask,1000,200);
    }



}
