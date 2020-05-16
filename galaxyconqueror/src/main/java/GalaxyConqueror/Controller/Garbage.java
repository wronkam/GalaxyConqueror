package GalaxyConqueror.Controller;

import GalaxyConqueror.Model.Model.*;
import GalaxyConqueror.Model.Bullet;
import GalaxyConqueror.Model.Ships.Enemy;
import javafx.scene.Node;

import static GalaxyConqueror.Model.Constants.*;
import static GalaxyConqueror.Model.Model.*;

public class Garbage {

    public static boolean isInBounds (Node x) {
        return (x.getLayoutX() >= 0 && x.getLayoutX() <= SCREEN_WIDTH && x.getLayoutY() >= 0 && x.getLayoutY() <= SCREEN_HEIGHT);
    }

    public static void clean () {
        for (int i = bullets.size()-1; i >= 0; i--) {
            if (!isInBounds(bullets.get(i).me)) {
                root.getChildren().remove(bullets.get(i).me);
                bullets.remove(i);
            }
        }
        for (int i = enemies.size()-1; i >= 0; i--) {
            if (!isInBounds(enemies.get(i).me)) {
                root.getChildren().remove(enemies.get(i).me);
                enemies.remove(i);
            }
        }
    }
}