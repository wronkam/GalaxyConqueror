package GalaxyConqueror.Model.Ships;

import GalaxyConqueror.Model.Bullet;
import GalaxyConqueror.Model.Factory;
import javafx.scene.image.Image;

import static GalaxyConqueror.Model.Constants.ENEMY_HEIGHT;
import static GalaxyConqueror.Model.Constants.ENEMY_WIDTH;

public class Enemy extends Ship {
    public int scoreForDeath = 10;
    public Enemy (Image image, int moveListid, double moveScale, int collisionId) {
        super(image, moveListid, moveScale, collisionId);
        width = ENEMY_WIDTH;
        height = ENEMY_HEIGHT;
        hp = 1;
        gun=new Factory<>(this);
        //hangar=new Factory<Enemy,Enemy>(this);
        radius=me.getBoundsInLocal().getHeight()/2;
    }
    public Enemy addBullet(Bullet x,int del){
        gun.add(x,del);
        return this;
    }
    public Enemy addBullet(Factory<? extends Ship,Bullet> x){
        for(int i=0;i<x.ammo.size();i++){
            gun.ammo.add(x.ammo.get(i));
            gun.timer.add(x.timer.get(i));
            gun.fireDelay.add(x.fireDelay.get(i));
        }
        return this;
    }
    public Enemy copy(){
        return new Enemy(this.me.getImage(),this.mvListId,this.mvScale,this.collisionId).addBullet(this.gun);
    }
    public void autoMove () {
        this.move();
    }

    public void autoShoot() {
        gun.shoot();
        //hangar.shoot(); TO BE ADDED IN CASE WE WANT IT, alongside addShip in both variants and alternative shoot in factory that adds to enemy list
    }

}