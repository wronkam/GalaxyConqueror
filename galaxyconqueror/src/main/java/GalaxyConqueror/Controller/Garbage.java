package GalaxyConqueror.Controller;

import GalaxyConqueror.Model.Model.*;
import GalaxyConqueror.Model.Bullet;
import GalaxyConqueror.Model.Ships.Enemy;
import javafx.scene.Node;

import static GalaxyConqueror.Model.Model.*;

public class Garbage {

    public static boolean isInBounds (Node x) {
        return (x.getLayoutX() >= 0 && x.getLayoutX() <= W && x.getLayoutY() >= 0 && x.getLayoutY() <= H);
    }

    public static void clean () {
        for (int i = 0; i < bullets.size(); i++) {
            if (!isInBounds(bullets.get(i).me)) {
                root.getChildren().remove(bullets.get(i).me);
                bullets.remove(i);
            }
        }
        for (int i = 0; i < enemies.size(); i++) {
            if (!isInBounds(enemies.get(i).ship)) {
                root.getChildren().remove(enemies.get(i).ship);
                enemies.remove(i);
            }
        }
    }
}