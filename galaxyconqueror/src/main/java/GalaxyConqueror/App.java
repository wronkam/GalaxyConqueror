package GalaxyConqueror;


//is maven working in intellij?

import GalaxyConqueror.Controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

/*
TODO:
-obiekty
-przemieszcznanie obiektów
-action: shoot
*/
/*
Schemat:
Node -> abstract Bullet -> Spaceship -> each and every enemy
               |                |
               V               > Player
*/
/*
0.shoot
1.check colisons
2.react to colisions
3.move
*/

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Controller.start(stage);
    }
}