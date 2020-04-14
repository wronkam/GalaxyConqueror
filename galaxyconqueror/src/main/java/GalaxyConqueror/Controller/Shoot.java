package GalaxyConqueror.Controller;

import GalaxyConqueror.Model.Bullet;
import GalaxyConqueror.Model.Ships.Ship;
import GalaxyConqueror.View.View;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Pair;

import static GalaxyConqueror.Model.Model.*;
public class Shoot {

    public static void shoot(Ship ship, double dirx, double diry, Image image) {
            Bullet bullet = new Bullet(
                    image,
                    ship.ship.getLayoutX() + ship.ship.getBoundsInLocal().getWidth() / 2 - 20,
                    ship.ship.getLayoutY(), dirx, diry);
            bullets.add(bullet);
            root.getChildren().add(bullet.me);
    }

}