package GalaxyConqueror.Model.Ships;

import javafx.scene.image.ImageView;

import java.util.Random;

import static GalaxyConqueror.Controller.Shoot.shoot;
import static GalaxyConqueror.Model.Constants.*;
import static GalaxyConqueror.View.View.bullet;
import static GalaxyConqueror.View.View.enemy;

public class Enemy extends Ship {
    private static Random rand = new Random();
    public int scoreForDeath = 10;
    public Enemy (double direction) {
        width = ENEMY_WIDTH;
        height = ENEMY_HEIGHT;
        collisionId = 1;
        hp = 1;
        me = new ImageView(enemy);
        moveTo(rand.nextInt(SCREEN_WIDTH), 50);
        me.setRotate(direction);
        radius=me.getBoundsInLocal().getHeight()/2;
        //moveTo (W/2, H/2);
    }

//    public void auto () {
//        autoMove();
//        autoShoot();
//    }

    public void autoMove () {
        move(ENEMY_DX, ENEMY_DY,0);
    }

    public void autoShoot() {
        shoot(this, 0, 1, bullet, this.collisionId);
    }

}