package GalaxyConqueror.Controller;

import GalaxyConqueror.Model.Bullet;
import GalaxyConqueror.Model.Ships.Ship;

import static java.lang.Math.max;

public class Modifiers {
    public static HealthIncrease healthIncrease=new HealthIncrease();
    static public class HealthIncrease implements Modifier{
        @Override
        public void update(Ship x) {
            x.hp=max(5,x.hp+1);
        }
        @Override
        public void update(Bullet x) {
        }
    }
}
