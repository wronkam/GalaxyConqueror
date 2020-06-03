package GalaxyConqueror.Controller;

import GalaxyConqueror.Model.Bullet;
import GalaxyConqueror.Model.Ships.Ship;

public class Modifiers {
    public static HealthIncrease healthIncrease=new HealthIncrease();
    static public class HealthIncrease implements Modifier{
        @Override
        public void update(Ship x) {
            x.hp++;
        }
        @Override
        public void update(Bullet x) {
        }
    }
}
