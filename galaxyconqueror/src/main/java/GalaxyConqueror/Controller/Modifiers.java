package GalaxyConqueror.Controller;

import GalaxyConqueror.Model.Bullet;
import GalaxyConqueror.Model.Ships.Ship;

import static java.lang.Math.min;

public class Modifiers {
    public static HealthIncrease healthIncreaseTo5=new HealthIncrease(5);
    public static HealthIncrease healthIncreaseTo10=new HealthIncrease(10);
    public static RightTwister rightTwister5=new RightTwister(5);
    public static RightTwister rightTwister10=new RightTwister(10);
    public static LeftTwister leftTwister5=new  LeftTwister(5);
    public static LeftTwister leftTwister10=new LeftTwister(10);
    public static DmgUp dmgUp1=new DmgUp(1);
    public static DmgUp dmgUp3=new DmgUp(3);
    public static HpUp hpUp1=new HpUp(1);
    public static HpUp hpUp3=new HpUp(3);
    static public class HealthIncrease implements Modifier{
        private int limit;
        HealthIncrease(int x){
            limit=x;
        }
        @Override
        public void update(Ship x) {
            x.hp=min(limit,x.hp+1);
        }
        @Override
        public void update(Bullet x) {
        }
    }
    static public class RightTwister implements Modifier{
        private int step;
        RightTwister(int step){
            this.step=step;
        }
        @Override
        public void update(Ship x) {
            x.rotateOffSet+=step;
        }
        @Override
        public void update(Bullet x) {
            x.rotateOffSet+=step;
        }
    }
    static public class LeftTwister implements Modifier{
        private int step;
        LeftTwister(int step){
            this.step=step;
        }
        @Override
        public void update(Ship x) {
            x.rotateOffSet-=step;
        }
        @Override
        public void update(Bullet x) {
            x.rotateOffSet-=step;
        }
    }
    static public class DmgUp implements Modifier{
        private int up;
        DmgUp(int x){
             up=x;
        }
        @Override
        public void update(Ship x) {
            for(Bullet a:x.gun.ammo)
                a.dmg+=up;
        }
        @Override
        public void update(Bullet x) {
            x.dmg+=up;
        }
    }
    static public class HpUp implements Modifier{
        private int up;
        HpUp(int x){
            up=x;
        }
        @Override
        public void update(Ship x) {
            x.hp+=up;
        }
        @Override
        public void update(Bullet x) {
        }

    }
}
