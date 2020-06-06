package GalaxyConqueror.Model;

import GalaxyConqueror.Controller.Modifier;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static GalaxyConqueror.Model.Constants.DIFFICULTY;
import static GalaxyConqueror.Model.Constants.SCREEN_WIDTH;
import static GalaxyConqueror.Model.Engine.random;
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
    public int copied=0;
    public int ModCoolDown=DIFFICULTY;
    public int ModTimer=1;
    public int rotateOffSet=0;
    public Modifier Mod=null;
    public Modifier DeathMod=null;

    public boolean randomSlideMovement=false;

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
    public Bullet (Image image,int moveListid,double moveScale,int collisionId,boolean randomSlideMovement) {
        this(image,moveListid,moveScale,collisionId);
        this.randomSlideMovement=randomSlideMovement;
    }
    public Bullet setModifier(Modifier Mod,int ModTimer,int ModCoolDown) {
        this.ModTimer=ModTimer;
        this.ModCoolDown=ModCoolDown;
        this.Mod=Mod;
        return this;
    }
    public Bullet setDeathModifier(Modifier DeathMod){
        this.DeathMod=DeathMod;
        return this;
    }
    public Bullet setModifier(Modifier Mod,int ModTimer,int ModCoolDown,Modifier DeathMod) {
        return this.setModifier(Mod,ModTimer,ModCoolDown).setDeathModifier(DeathMod);
    }
    public void modify() {
        if(Mod!=null) {
            ModTimer=(ModTimer+1)%ModCoolDown;
            if(ModTimer==0) {
                Mod.update(this);
            }
        }
    }
    public void setPosition(double x, double y, double direction){
        this.x=x;
        this.y=y;
        me.relocate(x,y);
        me.setRotate(direction);
        root.getChildren().add(this.me);
    }
    Bullet copy(){
        Bullet x= new Bullet(me.getImage(),mvListId,mvScale,collisionId,randomSlideMovement);
        x.hp=this.hp;
        x.dmg=this.dmg;
        x.ModTimer=this.ModTimer;
        x.ModCoolDown=this.ModCoolDown;
        x.Mod=this.Mod;
        x.DeathMod=this.DeathMod;
        x.rotateOffSet=rotateOffSet;
        return x;
    }
    public void move (double c) {
        x += c*dirx;
        y += c*diry;
        me.relocate(x,y);
    }
    public void move(){
        if(!randomSlideMovement) {
            me.setRotate(me.getRotate() + Model.moveList.get(mvListId).get(mvListIter).rotation * mvScale);
            x += Math.cos(Math.toRadians(me.getRotate())) * Model.moveList.get(mvListId).get(mvListIter).x * mvScale
                    + Math.sin(Math.toRadians(me.getRotate())) * Model.moveList.get(mvListId).get(mvListIter).y * mvScale
            ;
            y += Math.sin(Math.toRadians(me.getRotate())) * Model.moveList.get(mvListId).get(mvListIter).x * mvScale
                    - Math.cos(Math.toRadians(me.getRotate())) * Model.moveList.get(mvListId).get(mvListIter).y * mvScale
            ;
            mvListIter = (mvListIter + 1) % Model.moveList.get(mvListId).size();
            me.relocate(x, y);
        }else {
            double moveX=random.nextInt(50);
            if(x-moveX<100)
                x+=moveX;
            else if(x+moveX>SCREEN_WIDTH-100)
                x-=moveX;
            else{
                if(random.nextBoolean())
                    x+=moveX;
                else
                    x-=moveX;
            }
            me.relocate(x,y);
        }
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