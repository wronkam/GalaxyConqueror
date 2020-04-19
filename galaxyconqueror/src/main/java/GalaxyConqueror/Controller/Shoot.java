package GalaxyConqueror.Controller;

import GalaxyConqueror.Model.Bullet;
import GalaxyConqueror.Model.Ships.Ship;
import javafx.scene.image.Image;

import static GalaxyConqueror.Model.Model.bullets;
import static GalaxyConqueror.Model.Model.root;
import static java.lang.Math.*;

public class Shoot {

    public static void shoot(Ship ship, double dirx, double diry, Image image, int collisionId) {
            Bullet bullet = new Bullet(
                    image,
                    ship.me.getLayoutX() + ship.me.getBoundsInLocal().getWidth() / 2+(ship.radius+image.getWidth()/2)*cos(Math.toRadians(ship.me.getRotate()))-image.getWidth()/2,
                    ship.me.getLayoutY()+ship.me.getBoundsInLocal().getHeight()/2+(ship.radius+image.getHeight()/2)*sin(Math.toRadians(ship.me.getRotate()))-image.getHeight()/2,
                    dirx, diry, collisionId,ship.me.getRotate());
            bullets.add(bullet);
            root.getChildren().add(bullet.me);
    }

}