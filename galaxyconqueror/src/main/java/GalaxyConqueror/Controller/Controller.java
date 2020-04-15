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
import static GalaxyConqueror.View.View.*;
import static GalaxyConqueror.Model.Model.*;

public class Controller {
    public static void start (Stage stage) {
        try {

            stage.setTitle("Galaxy Conqueror");
            //ImageView splashView = new ImageView(background);
            box.getChildren().add(splashView);
            //Player player = new Player();

            root.getChildren().add(player.ship);
            Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
            stage.setScene(scene);

            stage.show();
            scene.addEventFilter(KeyEvent.KEY_PRESSED, ActionControl::keyPressed);
            scene.addEventFilter(KeyEvent.KEY_RELEASED, ActionControl::keyReleased);





            AnimationTimer timer = new AnimationTimer() {
                private long last = 0;
                private long enemySpawnLast = 0;
                private long bulletDelayLast = 0;
                Enemy e;



                @Override
                public void handle(long now) {


                    if (now - last >= TICK) {
                        long timeNow = System.currentTimeMillis();
                        long bulletDelay = timeNow - bulletDelayLast;
                        long enemySpawn = timeNow - enemySpawnLast;
                        if (bulletDelay >= BULLET_DELAY) {
                            if (Player.isShooting)
                                shoot(player, 0, -1, greenbullet);
                            bulletDelayLast = timeNow;
                        }

                        if (enemySpawn >= ENEMY_SPAWN_TICK) {
                            for (Enemy enemy : enemies) {
                                enemy.autoShoot();
                            }

                            e = new Enemy();
                            enemies.add(e);
                            root.getChildren().add(e.ship);
                            enemySpawnLast = timeNow;
                        }
                        for (Enemy e : enemies) {
                            e.autoMove();
                        }
                        for (int i = 0; i < bullets.size(); i++) {
                            bullets.get(i).move(5);
                        }
                        movePlayer();
                        Garbage.clean();
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

    private static void movePlayer() {
        int dx = 0, dy = 0;
        if (goUp) dy -= PLAYER_DY;
        if (goDown) dy += PLAYER_DY;
        if (goRight) dx -= PLAYER_DX;
        if (goLeft) dx += PLAYER_DX;
        player.move(dx, dy);
    }






}