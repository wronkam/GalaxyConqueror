package GalaxyConqueror.Model.Ships;

import javafx.scene.image.ImageView;

import java.util.Random;
import static GalaxyConqueror.Controller.Shoot.*;
import static GalaxyConqueror.Model.Constants.*;
import static GalaxyConqueror.Model.Model.*;
import static GalaxyConqueror.View.View.bullet;
import static GalaxyConqueror.View.View.enemy;
import static java.awt.image.ImageObserver.WIDTH;

public class Enemy extends Ship {
    public static Random rand = new Random();
    public Enemy () {

        ship = new ImageView(enemy);
        moveTo(rand.nextInt(SCREEN_WIDTH), 50);
        //moveTo (W/2, H/2);
    }

//    public void auto () {
//        autoMove();
//        autoShoot();
//    }

    public void autoMove () {
        move(ENEMY_DX, ENEMY_DY);
    }

    public void autoShoot() {
        shoot(this, 0, 1, bullet);
    }

}