package GalaxyConqueror.Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static GalaxyConqueror.Model.Model.root;

public class Bullet {
    public int collisionId;
    public ImageView me;
    public double x;
    public double y;
    public double height;
    public double width;
    public double dirx;
    public double diry;
    public double radius;
    public int mvListId;
    public int mvListIter;
    public double mvScale;
    public int hp;
    public int dmg;

    public double getX () {
        return me.getX();
    }

    public double getY () {
        return me.getY();
    }

    public double getWidth () {
        return width;
    }

    public double getHeight () {
        return height;
    }

    public int getDmg () {
        return dmg;
    }

    public int getHp () {
        return hp;
    }
    public Bullet(){
       //only to be used by player
    }
    public Bullet (Image image,int moveListid,double moveScale,int collisionId) {
        this.mvListIter=0;
        this.mvListId=moveListid;
        this.me=new ImageView(image);
        this.mvScale=moveScale;
        this.collisionId=collisionId;
        radius=image.getHeight()/2;
        dmg = 1;
        hp = 1;
    }
    public void setPosition(double x, double y, double direction){
        this.x=x;
        this.y=y;
        me.relocate(x,y);
        me.setRotate(direction);
        root.getChildren().add(this.me);
    }
    Bullet copy(){
        return new Bullet(me.getImage(),mvListId,mvScale,collisionId);
    }
    public void move (double c) {
        x += c*dirx;
        y += c*diry;
        me.relocate(x,y);
    }
    public void move(){
        me.setRotate(me.getRotate()+Model.moveList.get(mvListId).get(mvListIter).rotation*mvScale);
        x+=Math.cos(Math.toRadians(me.getRotate()))*Model.moveList.get(mvListId).get(mvListIter).x*mvScale
                +Math.sin(Math.toRadians(me.getRotate()))*Model.moveList.get(mvListId).get(mvListIter).y*mvScale
        ;
        y+=Math.sin(Math.toRadians(me.getRotate()))*Model.moveList.get(mvListId).get(mvListIter).x*mvScale
                -Math.cos(Math.toRadians(me.getRotate()))*Model.moveList.get(mvListId).get(mvListIter).y*mvScale
        ;
        mvListIter=(mvListIter+1)% Model.moveList.get(mvListId).size();
        me.relocate(x,y);
    }


    public boolean isDead () {
        return (hp <= 0);
    }

    public void subtractHp (int d) {
        hp -= d;
    }

    public void kill () {
        subtractHp(hp);
    }



}