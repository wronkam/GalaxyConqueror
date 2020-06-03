package GalaxyConqueror.Model;

import GalaxyConqueror.Model.Ships.Enemy;
import GalaxyConqueror.Model.Ships.Ship;

import java.util.ArrayList;

import static GalaxyConqueror.Model.Model.bullets;
import static GalaxyConqueror.Model.Model.enemies;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Factory{
    public ArrayList<Bullet> ammo;
    public ArrayList<Enemy> ships;
    public ArrayList<Integer> fireDelayA;
    public ArrayList<Integer> timerA;
    public ArrayList<Integer> fireDelayS;
    public ArrayList<Integer> timerS;
    Ship shooter;
    public Factory(Ship s){
        this.shooter=s;
        ammo=new ArrayList<>();
        fireDelayA=new ArrayList<>();
        timerA=new ArrayList<>();
        ships=new ArrayList<>();
        fireDelayS=new ArrayList<>();
        timerS=new ArrayList<>();
    }
    public Factory add(Bullet x,int del){
        ammo.add(x);
        fireDelayA.add(del);
        timerA.add(0);
        return this;
    }
    public Factory add(Enemy x,int del){
        ships.add(x);
        fireDelayS.add(del);
        timerS.add(0);
        return this;
    }
    public Factory add(Bullet x,int del,int tim){
        ammo.add(x);
        fireDelayA.add(del);
        timerA.add(tim);
        return this;
    }
    public Factory add(Enemy x,int del,int tim){
        ships.add(x);
        fireDelayS.add(del);
        timerS.add(tim);
        return this;
    }
    public void add(Factory x) {
        for(int i=0;i<x.ammo.size();++i) {
            ammo.add(x.ammo.get(i));
            timerA.add(x.timerA.get(i));
            fireDelayA.add(x.fireDelayA.get(i));
        }
        for(int i=0;i<x.ships.size();++i) {
            ships.add(x.ships.get(i));
            timerS.add(x.timerS.get(i));
            fireDelayS.add(x.fireDelayS.get(i));
        }
    }
    Factory copy(Ship gunner){
        Factory x=new Factory(gunner);
        x.ammo.addAll(ammo);
        x.fireDelayA.addAll(fireDelayA);
        x.timerA.addAll(timerA);
        x.ships.addAll(ships);
        x.fireDelayS.addAll(fireDelayS);
        x.timerS.addAll(timerS);
        return x;
    }
    public void shoot(){
        for (int i=0;i<ammo.size();i++) {
            if(timerA.get(i)==0) {
                Bullet bullet =  ammo.get(i).copy();
                bullet.setPosition(
                        shooter.me.getLayoutX() + shooter.me.getBoundsInLocal().getWidth() / 2
                                + (shooter.radius + bullet.me.getBoundsInLocal().getWidth() / 2)
                                * cos(Math.toRadians(shooter.me.getRotate())) - bullet.me.getBoundsInLocal().getWidth() / 2,
                        shooter.me.getLayoutY() + shooter.me.getBoundsInLocal().getHeight() / 2
                                + (shooter.radius + bullet.me.getBoundsInLocal().getHeight() / 2)
                                * sin(Math.toRadians(shooter.me.getRotate())) - bullet.me.getBoundsInLocal().getHeight() / 2,
                        shooter.me.getRotate()
                );
                    bullets.add(bullet);
            }
            timerA.set(i, (timerA.get(i) + 1) % fireDelayA.get(i));
        }
        for (int i=0;i<ships.size();i++) {
            if(timerS.get(i)==0) {
                Enemy bullet =  ships.get(i).copy();
                bullet.setPosition(
                        shooter.me.getLayoutX() + shooter.me.getBoundsInLocal().getWidth() / 2
                                + (shooter.radius + bullet.me.getBoundsInLocal().getWidth() / 2)
                                * cos(Math.toRadians(shooter.me.getRotate())) - bullet.me.getBoundsInLocal().getWidth() / 2,
                        shooter.me.getLayoutY() + shooter.me.getBoundsInLocal().getHeight() / 2
                                + (shooter.radius + bullet.me.getBoundsInLocal().getHeight() / 2)
                                * sin(Math.toRadians(shooter.me.getRotate())) - bullet.me.getBoundsInLocal().getHeight() / 2,
                        shooter.me.getRotate()
                );
                enemies.add(bullet);
            }
            timerS.set(i, (timerS.get(i) + 1) % fireDelayS.get(i));
        }
    }

}
