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
        gun=new Factory(this);
        radius=me.getBoundsInLocal().getHeight()/2;
    }
    public Enemy (Image image, int moveListid, double moveScale, int collisionId,boolean randomSlideMovement) {
        super(image, moveListid, moveScale, collisionId,randomSlideMovement);
        width = ENEMY_WIDTH;
        height = ENEMY_HEIGHT;
        hp = 1;
        gun=new Factory(this);
        radius=me.getBoundsInLocal().getHeight()/2;
    }
    public Enemy addBullet(Bullet x,int del){
        gun.add(x,del);
        return this;
    }
    public Enemy addBullet(Enemy x,int del){
        gun.add(x,del);
        return this;
    }
    public Enemy addBullet(Bullet x,int del,int tim){
        gun.add(x,del,tim);
        return this;
    }
    public Enemy addBullet(Enemy x,int del,int tim){
        gun.add(x,del,tim);
        return this;
    }
    public Enemy addBullet(Factory x){
        gun.add(x);
        return this;
    }
    public Enemy copy(){
        return new Enemy(this.me.getImage(),this.mvListId,this.mvScale,this.collisionId,this.randomSlideMovement).addBullet(this.gun);
    }
    public void autoMove () {
        this.move();
    }

    public void autoShoot() {
        gun.shoot();
        //hangar.shoot(); TO BE ADDED IN CASE WE WANT IT, alongside addShip in both variants and alternative shoot in factory that adds to enemy list
    }

}