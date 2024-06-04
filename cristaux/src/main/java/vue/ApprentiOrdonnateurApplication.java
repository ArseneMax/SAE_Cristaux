package vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ApprentiOrdonnateurApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        VBoxRoot root = new  VBoxRoot();
        Scene scene = new Scene(root, 1700, 1000);
        stage.setTitle("Hello JavaFX");
        stage.centerOnScreen();
        stage.show();
        stage.setScene(scene);

    }

    public static void main(String[] args) {
        Application.launch();
    }

}
