package GalaxyConqueror.Controller;

import GalaxyConqueror.Model.Engine;

import static GalaxyConqueror.Model.Constants.SCREEN_HEIGHT;
import static GalaxyConqueror.Model.Constants.SCREEN_WIDTH;
import static GalaxyConqueror.Model.Model.*;
import static GalaxyConqueror.Model.Model.bullets;

// Class used when you start new game to reset everything

public class Restart {
    public static void reset(){
        player.moveTo((double) SCREEN_WIDTH / 2, (SCREEN_HEIGHT*5/6));
        player.me.setRotate(-90);
        player.hp = 10;
        score = 0;
        Engine.initiate = false;
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
