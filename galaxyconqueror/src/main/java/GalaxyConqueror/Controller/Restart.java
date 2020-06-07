package GalaxyConqueror.Controller;

import GalaxyConqueror.Model.Bullet;
import GalaxyConqueror.Model.Engine;

import static GalaxyConqueror.App.canIConfigureJavaFXMedia;
import static GalaxyConqueror.Model.Constants.SCREEN_HEIGHT;
import static GalaxyConqueror.Model.Constants.SCREEN_WIDTH;
import static GalaxyConqueror.Model.Model.*;
import static GalaxyConqueror.View.View.bgPlayer;
import static GalaxyConqueror.View.View.greenbullet;

// Class used when you start new game to reset everything

public class Restart {
    public static void reset(){
        if(canIConfigureJavaFXMedia) {
            bgPlayer.play();
        }
        player.moveTo((double) SCREEN_WIDTH / 2, (double) (SCREEN_HEIGHT*5)/6);
        player.me.setRotate(-90);
        player.hp = 10;
        player.dmg=1;//nie odpowiada za faktyczne obrazenia, one sa w pociskach, tylko do wyswietlania
        score = 0;
        player.gun.clear();
        player.gun.add(new Bullet(greenbullet,0,1,0),2);
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
