package GalaxyConqueror.Controller;

import GalaxyConqueror.Model.Bullet;
import GalaxyConqueror.Model.Ships.Ship;

import static GalaxyConqueror.Model.Engine.random;
import static GalaxyConqueror.Model.Model.player;
import static GalaxyConqueror.View.View.greenbullet;
import static java.lang.Math.*;

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
    public static RandomPlayerBonus randomPlayerBonus=new RandomPlayerBonus();
    public static InfHealthIncrease infHealthIncrease=new InfHealthIncrease();
    public static LRShower lrShower5in35=new LRShower(5,35);
    static public class InfHealthIncrease implements Modifier{
        private int limit;
        @Override
        public void update(Ship x) {
            x.hp++;
        }
        @Override
        public void update(Bullet x) {
        }
    }
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
    static  public class LRShower implements Modifier{
        private int limit;
        private int step;
        LRShower(int step,int limit){
            this.step=step;
            this.limit=limit;
        }
        @Override
        public void update(Ship x) {
            x.rotateOffSet+=step;
            if(abs(x.rotateOffSet)>limit)
            {
                x.rotateOffSet=limit*(x.rotateOffSet<0 ? -1 : 1);
                step*=-1;
            }
        }
        @Override
        public void update(Bullet x) {
           x.rotateOffSet+=step;
           if(abs(x.rotateOffSet)>limit)
           {
               x.rotateOffSet=limit*(x.rotateOffSet<0 ? -1 : 1);
               step*=-1;
           }
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
    static public class RandomPlayerBonus implements Modifier{
        Bullet shower=new Bullet(greenbullet,0,0.8,0).setModifier(lrShower5in35,0,1);
        @Override
        public void update(Ship x) {
            int i=random.nextInt()%3;
            System.out.println("hello"+ i);
            switch (i){
                case 0 : {
                    player.dmg++;
                    for(Bullet a:player.gun.ammo)
                        a.dmg++;
                    break;
                }
                case 1: player.hp++; break;
                case 2: {
                    player.gun.add(shower,5);
                    break;
                }
            }
        }
        @Override
        public void update(Bullet x) {
            int i=random.nextInt(3);
            switch (i){
                case 0 : {
                    player.dmg+=3;
                    for(Bullet a:player.gun.ammo)
                        a.dmg+=3;
                    break;
                }
                case 1: player.hp+=5; break;
                case 2: {
                    player.gun.add(new Bullet(greenbullet,0,0.8,0).setModifier(lrShower5in35,0,1),3);
                    break;
                }
            }
        }

    }
}
