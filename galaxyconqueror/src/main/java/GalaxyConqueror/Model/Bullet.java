package GalaxyConqueror.Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Pair;

public class Bullet {
    public int collisionId;
    public ImageView me;
    public double x;
    public double y;
    public double dirx;
    public double diry;
    public int mvListId;
    public double mvScale;
    public int hp;
    public int dmg;

    public double getX () {
        return me.getX();
    }

    public double getY () {
        return me.getY();
    }

    public double getWidth () {
        return me.getFitWidth();
    }

    public double getHeight () {
        return me.getFitHeight();
    }

    public int getDmg () {
        return dmg;
    }
    public Bullet () {
    }

    public Bullet (Image image, double x, double y, double dirx, double diry, int collisionId) {
        this.x = x;
        this.y = y;
        this.me = new ImageView(image);
        this.dirx = dirx;
        this.diry = diry;
        this.collisionId = collisionId;
        dmg = 1;
    }
    public void move (double c) {
        x += c*dirx;
        y += c*diry;
        me.relocate(x,y);
    }
//    public void move(int a, int b) {
//        x += a;
//        y += b;
//        me.relocate(x,y);
//    }

    public void move(int dx, int dy) {
        if (dx == 0 && dy == 0)
        {
            return;
        }
        final double cx = me.getBoundsInLocal().getWidth()  / 2;
        final double cy = me.getBoundsInLocal().getHeight() / 2;
        double x = cx + me.getLayoutX() + dx;
        double y = cy + me.getLayoutY() + dy;
        moveTo(x, y);
    }

    public void moveTo(double x, double y)
    {
        final double cx = me.getBoundsInLocal().getWidth()  / 2;
        final double cy = me.getBoundsInLocal().getHeight() / 2;
        me.relocate(x - cx, y - cy);
    }

    public boolean isDead () {
        return (hp <= 0);
    }

    public void substractHp (int d) {
        hp -= d;
    }


}