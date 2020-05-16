package GalaxyConqueror.Model;

import GalaxyConqueror.Model.Ships.Ship;

import java.util.ArrayList;

import static GalaxyConqueror.Model.Model.bullets;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Factory<S extends Ship,T extends Bullet> {
    public ArrayList<T> ammo;
    public ArrayList<Integer> fireDelay;
    public ArrayList<Integer> timer;
    S shooter;
    public Factory(S s){
        this.shooter=s;
        ammo=new ArrayList<>();
        fireDelay=new ArrayList<>();
        timer=new ArrayList<>();
    }
    public Factory<S,T> add(T x,int del){
        ammo.add(x);
        fireDelay.add(del);
        timer.add(0);
        return this;
    }
    public Factory<S,T> add(T x,int del,int tim){
        ammo.add(x);
        fireDelay.add(del);
        timer.add(tim);
        return this;
    }
    Factory<S,T> copy(S gunner){
        Factory<S,T> x=new Factory<>(gunner);
            x.ammo.addAll(ammo);
            x.fireDelay.addAll(fireDelay);
            x.timer.addAll(timer);
        return x;
    }
    public void shoot(){
        for (int i=0;i<ammo.size();i++) {
            if(timer.get(i)==0) {
                Bullet bullet = ammo.get(i).copy();
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
            timer.set(i, (timer.get(i) + 1) % fireDelay.get(i));
        }
    }
}
