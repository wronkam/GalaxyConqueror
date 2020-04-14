package GalaxyConqueror.Controller;

import GalaxyConqueror.Model.Bullet;
import GalaxyConqueror.Model.Ships.Enemy;
import GalaxyConqueror.Model.Ships.Player;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.ListIterator;

import static GalaxyConqueror.Controller.ActionControl.*;


import static GalaxyConqueror.Controller.Shoot.shoot;
import static GalaxyConqueror.View.View.*;
import static GalaxyConqueror.Model.Model.*;

public class Controller {
    public static void start (Stage stage) {
        try {


            stage.setTitle("Galaxy Conqueror");
            ImageView splashView = new ImageView(background);
            box.getChildren().add(splashView);
            Player player = new Player();

            root.getChildren().add(player.ship);
            Scene scene = new Scene(root, W, H);
            stage.setScene(scene);

            stage.show();
            scene.addEventFilter(KeyEvent.KEY_PRESSED, ActionControl::keyPressed);
            scene.addEventFilter(KeyEvent.KEY_RELEASED, ActionControl::keyReleased);
            AnimationTimer timer = new AnimationTimer() {
                long timeLast = 0;
                long timeLast2 = 0;
                Enemy e;

                @Override
                public void handle(long now) {
                    long timeNow = System.currentTimeMillis();
                    long time = timeNow - timeLast;
                    long time2 = timeNow - timeLast2;
                    if (time2 < 0 || time2 > 50) {

                        if (Player.isShooting)
                            shoot(player, 0, -1);
                        timeLast2 = timeNow;
                    }
                    if (time < 0 || time > 1000) {
                        for (Enemy e : enemies) {
                            e.autoShoot();
                        }
                        e = new Enemy();
                        enemies.add(e);
                        root.getChildren().add(e.ship);
                        timeLast = timeNow;

                    }
                    for (Enemy e : enemies) {
                        e.autoMove();
                    }
                    for (int i = 0; i < bullets.size(); i++) {
                        bullets.get(i).move(5);
                    }
                    int dx = 0, dy = 0;
                    if (goUp) dy -= player.speedY;
                    if (goDown) dy += player.speedY;
                    if (goRight) dx -= player.speedX;
                    if (goLeft) dx += player.speedX;
                    player.move(dx, dy);
                    Garbage.clean();
                }
            };
            timer.start();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}