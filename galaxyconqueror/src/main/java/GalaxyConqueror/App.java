package GalaxyConqueror;


//is maven working in intellij?
//does it work?

import GalaxyConqueror.Controller.Menu;
import GalaxyConqueror.View.View;
import javafx.application.Application;
import javafx.stage.Stage;

import static GalaxyConqueror.Controller.ReadScore.readScores;
public class App extends Application {
    //=====================================================================
    //=====================================================================
    public static boolean canIConfigureJavaFXMedia=true;
    //=====================================================================
    //=====================================================================
    public static void main(String[] args) {
        launch(args);
        readScores();
    }

    @Override
    public void start(Stage stage) {
        View.set();
        Menu.start(stage);
    }
}
