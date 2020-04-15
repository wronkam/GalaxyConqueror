package GalaxyConqueror.Controller;

import GalaxyConqueror.Model.Bullet;
import GalaxyConqueror.Model.Ships.Enemy;
import GalaxyConqueror.Model.Ships.Player;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

import static GalaxyConqueror.Controller.ActionControl.*;


import static GalaxyConqueror.Controller.Shoot.shoot;
import static GalaxyConqueror.Model.Constants.*;
import static GalaxyConqueror.Model.Engine.engine;
import static GalaxyConqueror.View.View.*;
import static GalaxyConqueror.Model.Model.*;

public class Controller {
    public static void start (Stage stage) {
        try {

            stage.setTitle("Galaxy Conqueror");
            box.getChildren().add(splashView);

            root.getChildren().add(player.ship);
            Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
            stage.setScene(scene);

            stage.show();
            scene.addEventFilter(KeyEvent.KEY_PRESSED, ActionControl::keyPressed);
            scene.addEventFilter(KeyEvent.KEY_RELEASED, ActionControl::keyReleased);


            AnimationTimer timer = new AnimationTimer() {
                private long last = 0;

                @Override
                public void handle(long now) {


                    if (now - last >= TICK) {
                        engine();
                        last = now;
                    }
                }
            };

            timer.start();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}