package GalaxyConqueror.Controller;

import static GalaxyConqueror.Model.Constants.SCREEN_WIDTH;
import static GalaxyConqueror.Model.Model.*;
import static GalaxyConqueror.Model.Model.bullets;

// Class used when you start new game to reset everything

public class Restart {
    public static void reset(){
        player.moveTo(SCREEN_WIDTH / 2, 970);
        player.hp = 10;
        score = 0;
        for(int i = enemies.size()-1; i >= 0; i--) {
            root.getChildren().remove(enemies.get(i).me);
            enemies.remove(i);
        }
        for(int i = bullets.size()-1; i >= 0; i--) {
            root.getChildren().remove(bullets.get(i).me);
            bullets.remove(i);
        }
    }
}
