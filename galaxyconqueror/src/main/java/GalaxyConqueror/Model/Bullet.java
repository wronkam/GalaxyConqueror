package GalaxyConqueror.Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Pair;

public class Bullet {
    public ImageView me;
    public double x;
    public double y;
    public double dirx;
    public double diry;
    public int mvListId;
    public double mvScale;
    public int hp;
    public int dmg;
    public Bullet (Image image, double x, double y, double dirx, double diry) {
        this.x = x;
        this.y = y;
        this.me = new ImageView(image);
        this.dirx = dirx;
        this.diry = diry;
    }
    public void move (double c) {
        x += c*dirx;
        y += c*diry;
        me.relocate(x,y);
    }
    public void move(int a, int b) {
        x += a;
        y += b;
        me.relocate(x,y);
    }

}