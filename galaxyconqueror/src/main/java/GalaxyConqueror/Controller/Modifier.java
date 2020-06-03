package GalaxyConqueror.Controller;

import GalaxyConqueror.Model.Bullet;
import GalaxyConqueror.Model.Ships.Ship;

public interface Modifier {
        void update(Ship x);
        void update(Bullet x);
}
