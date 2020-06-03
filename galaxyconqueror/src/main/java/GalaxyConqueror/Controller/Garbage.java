package GalaxyConqueror.Controller;

import javafx.scene.Node;

import static GalaxyConqueror.Model.Constants.SCREEN_HEIGHT;
import static GalaxyConqueror.Model.Constants.SCREEN_WIDTH;
import static GalaxyConqueror.Model.Model.*;

public class Garbage {

    public static boolean isOutOfBounds (Node x) {
        return !(x.getLayoutX() >= 0 && x.getLayoutX() <= SCREEN_WIDTH && x.getLayoutY() >= (-100) && x.getLayoutY() <= SCREEN_HEIGHT);
    }

    public static void clean () {
        for (int i = bullets.size()-1; i >= 0; i--) {
            if (isOutOfBounds(bullets.get(i).me)) {
                root.getChildren().remove(bullets.get(i).me);
                bullets.remove(i);
            }
        }
        for (int i = enemies.size()-1; i >= 0; i--) {
            if (isOutOfBounds(enemies.get(i).me)) {
                root.getChildren().remove(enemies.get(i).me);
                enemies.remove(i);
            }
        }
    }
}