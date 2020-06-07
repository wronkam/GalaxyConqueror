package GalaxyConqueror.Model.Ships;

import GalaxyConqueror.Model.Bullet;
import GalaxyConqueror.Model.Factory;
import javafx.scene.image.ImageView;

import static GalaxyConqueror.Model.Constants.*;
import static GalaxyConqueror.View.View.greenbullet;
import static GalaxyConqueror.View.View.spaceship;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class Player extends Ship {

    public Player () {
        super();
        width = PLAYER_WIDTH;
        height = PLAYER_HEIGHT;
        collisionId = 0;
        hp = 10;
        dmg=1;
        me = new ImageView(spaceship);
        moveTo((double) SCREEN_WIDTH / 2, 970);
        me.setRotate(-90);
        radius=me.getBoundsInLocal().getHeight()/2;
        gun=new Factory(this);
        gun.add(new Bullet(greenbullet,0,1,0),2);
    }
    /*
    public void move(double x, double y,double rot)
    {
        final double cx = me.getBoundsInLocal().getWidth()  / 2;
        final double cy = me.getBoundsInLocal().getHeight() / 2;
        if(x - cx >= -10 && x + cx <= SCREEN_WIDTH + 10 && y - cy >= -10 && y + cy <= SCREEN_HEIGHT - 50)
        {
            me.relocate(x - cx, y - cy);
        }
        me.setRotate(me.getRotate()+rot);
    }
*/
    public void move(double dx, double dy,double rotate) {
        if (dx == 0 && dy == 0 && rotate==0)
        {
            return;
        }
        final double cx = me.getBoundsInLocal().getWidth()  / 2;
        final double cy = me.getBoundsInLocal().getHeight() / 2;
        double x = cx + me.getLayoutX() + dx;
        double y = cy + me.getLayoutY() + dy;
        me.setRotate(me.getRotate()+rotate);
        x=max(x,me.getBoundsInLocal().getWidth()/2+1);
        x=min(x,SCREEN_WIDTH-me.getBoundsInLocal().getWidth()/2-1);
        y=max(y,me.getBoundsInLocal().getHeight()/2+1);
        y=min(y,SCREEN_HEIGHT-me.getBoundsInLocal().getHeight()/2-1);
        moveTo(x,y);
    }

    public void moveTo(double x, double y)
    {
        final double cx = me.getBoundsInLocal().getWidth()  / 2;
        final double cy = me.getBoundsInLocal().getHeight() / 2;
        me.relocate(x - cx, y - cy);
        this.x -= cx;
        this.y -= cy;
    }
    public void shoot() {
        gun.shoot();
    }
}