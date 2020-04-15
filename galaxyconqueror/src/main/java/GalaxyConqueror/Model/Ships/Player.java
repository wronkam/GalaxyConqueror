package GalaxyConqueror.Model.Ships;

import GalaxyConqueror.Model.Ships.Ship;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static GalaxyConqueror.Model.Constants.*;

import static GalaxyConqueror.Model.Model.*;
import static GalaxyConqueror.View.View.spaceship;

public class Player extends Ship {

    public Player () {
        ship = new ImageView(spaceship);
        moveTo(SCREEN_WIDTH / 2, 970);
    }
    @Override
    public void moveTo(double x, double y)
    {
        final double cx = ship.getBoundsInLocal().getWidth()  / 2;
        final double cy = ship.getBoundsInLocal().getHeight() / 2;
        if(x - cx >= -10 && x + cx <= SCREEN_WIDTH + 10 && y - cy >= -10 && y + cy <= SCREEN_HEIGHT - 50)
        {
            ship.relocate(x - cx, y - cy);
        }
    }

}