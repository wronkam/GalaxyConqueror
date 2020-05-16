package GalaxyConqueror.Model.Ships;

import GalaxyConqueror.Model.Bullet;
import GalaxyConqueror.Model.Factory;
import javafx.scene.image.Image;

public abstract class Ship extends Bullet {
    public boolean isShooting;

    Factory<? extends Ship,Bullet> gun;
    //Factory<? extends Ship,Enemy> hangar;
    public Ship(Image image, int moveListid, double moveScale, int collisionId) {
        super(image, moveListid, moveScale, collisionId);
    }

    public Ship() {
        super();
    }
}