package GalaxyConqueror.Model.Ships;

import javafx.scene.Node;
import javafx.scene.image.ImageView;

import static GalaxyConqueror.Model.Model.*;

public abstract class Ship {
    public static int hp;
    public int x;
    public int y;
    public Node ship;
 //   public abstract void move();
  //  public abstract void shoot();
     public void move(int dx, int dy) {
         hp = 5;
         if (dx == 0 && dy == 0)
         {
             return;
         }
         final double cx = ship.getBoundsInLocal().getWidth()  / 2;
         final double cy = ship.getBoundsInLocal().getHeight() / 2;
         double x = cx + ship.getLayoutX() + dx;
         double y = cy + ship.getLayoutY() + dy;
         moveTo(x, y);
     }
    public void moveTo(double x, double y)
    {
        final double cx = ship.getBoundsInLocal().getWidth()  / 2;
        final double cy = ship.getBoundsInLocal().getHeight() / 2;
            ship.relocate(x - cx, y - cy);
    }
    public int velocity;
    public static boolean isShooting;


}