package GalaxyConqueror.Model;

import GalaxyConqueror.Controller.Garbage;
import GalaxyConqueror.Model.Ships.Enemy;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Random;

import static GalaxyConqueror.Controller.ActionControl.*;
import static GalaxyConqueror.Controller.Modifiers.*;
import static GalaxyConqueror.Model.Constants.*;
import static GalaxyConqueror.Model.Model.*;
import static GalaxyConqueror.View.View.*;
import static java.lang.Math.abs;
import static java.lang.Math.min;

public class Engine {
    private static long enemySpawnLast = 0;
    private static long bulletDelayLast = 0;
    public static Random random=new Random();
    public static boolean initiate=false;
    public static void engine() {
        scoreLabel.setText("Score: " + score);
        hpLabel.setText("HP: " + player.hp);
        dmgLabel.setText("Dmg: "+player.dmg);
        long timeNow = System.currentTimeMillis();
        long bulletDelay = timeNow - bulletDelayLast;
        long enemySpawn = timeNow - enemySpawnLast;
        if (bulletDelay >= BULLET_DELAY) {
            shootPlayer();
            bulletDelayLast = timeNow;
        }
        if (enemySpawn >= ENEMY_SPAWN_TICK) {
            shootAndSpawnEnemies();
            enemySpawnLast = timeNow;
        }
        if(!initiate){
            initiate=true;
            addStars(random.nextInt(5)+5);
            initialEnemiesMaker();
        }

        checkCollisions();
        reactToCollisions();
        moveEnemies();
        moveBullets();
        movePlayer();
        Garbage.clean();
        player.me.toFront();

    }

    private static void movePlayer() {
        double dx = 0, dy = 0,rotate=0;
        if (goUp) dy -= PLAYER_DY;
        if (goDown) dy += PLAYER_DY;
        if (goRight) dx -= PLAYER_DX;
        if (goLeft) dx += PLAYER_DX;
        if (rotateLeft) rotate -= PLAYER_ROT;
        if (rotateRight) rotate += PLAYER_ROT;
        player.move(dx, dy,rotate);
    }

    private static void moveBullets() {
        for (Bullet b : bullets) {
            b.move();
        }
    }

    private static void moveEnemies() {
        for (Enemy e : enemies) {
            e.autoMove();
        }
    }

    private static void shootPlayer () {
        if (player.isShooting) {
            player.shoot();
        }
    }

    //na ten moment enemiesy się respią w tym samym czasie co strzelają
    private static void shootAndSpawnEnemies () {
        int quEnamies=enemies.size();
        //number of enemies can change during shooting!!!
        for (int i=0;i<quEnamies;) {
            enemies.get(i).autoShoot();
            i++;
        }
    }
    //usuwa enemiesy, które nie żyją
    private static void reactToCollisions () {
        if (player.isDead()) {
            gameOver();
        }
        ArrayList<ImageView> imagesToRemove = new ArrayList<>();
        for (Enemy e: enemies) {
            if (e.isDead()) {
                score += e.scoreForDeath;
                imagesToRemove.add(e.me);
            }
        }
        for (Bullet b : bullets) {
            if (b.isDead()) {
                imagesToRemove.add(b.me);
            }
        }
        root.getChildren().removeAll(imagesToRemove);
        enemies.removeIf(Bullet::isDead);
        bullets.removeIf(Bullet::isDead);
    }

    private static void checkCollisions () {
        for (Bullet b : bullets) {
            //allied objects 0
            if (b.collisionId == 0) {
                for (Enemy e : enemies) {
                    if(e.collisionId==1) {
                        if (b.me.getBoundsInParent().intersects(e.me.getBoundsInParent())) {
                            e.subtractHp(b.getDmg());
                            b.kill();
                        }
                    }
                }
                //enemy objects 1
            }else if (b.collisionId == 1) {
                if (b.me.getBoundsInParent().intersects(player.me.getBoundsInParent()) ) {
                    Bounds enemyBounds = b.me.localToScene(b.me.getBoundsInLocal());
                    Bounds playerBounds = player.me.localToScene(player.me.getBoundsInLocal());
                    double x = playerBounds.getMinX() + PLAYER_WIDTH / 2;
                    double y = playerBounds.getMinY() + PLAYER_HEIGHT / 2;
                    double x2 = enemyBounds.getMinX() + BULLET_WIDTH / 2;
                    double y2 = enemyBounds.getMinY() + BULLET_HEIGHT / 2;
                    if((x + 55 >= x2 && x - 55 <= x2 &&
                            (y + 55 >= y2 && y - 55 <= y2))){
                        player.subtractHp(b.getDmg());
                        b.kill();
                    }
                }
            }else if(b.collisionId==3){
                if (b.me.getBoundsInParent().intersects(player.me.getBoundsInParent()) ) {
                    b.kill();
                }
            }
        }
        for (Enemy e : enemies) {
            if(e.collisionId==1) {
                Bounds enemyBounds = e.me.localToScene(e.me.getBoundsInLocal());
                Bounds playerBounds = player.me.localToScene(player.me.getBoundsInLocal());
                double x = playerBounds.getMinX() + player.width / 2;
                double y = playerBounds.getMinY() + player.height / 2;
                if((x + 10 >= enemyBounds.getMinX() && x - 10 <= enemyBounds.getMaxX()) &&
                        (y + 10 >= enemyBounds.getMinY() && y - 10 <= enemyBounds.getMaxY())){
                            player.kill();
                }
            }else if(e.collisionId==3){
                if (e.me.getBoundsInParent().intersects(player.me.getBoundsInParent()) ) {
                    e.kill();
                }

            }
        }
    }

    private static void gameOver () {
        System.out.println("Game over");
    }

    public static void addStars(int x){
        Bullet starB=new Bullet(star,0,min(abs((random.nextDouble())+0.1),0.4),2);
        for(int i=0;i<x;i++) {
            Enemy starShooter = new Enemy(dot, 0, 1, 2, true);
            starShooter.addBullet(starB, random.nextInt(20) + 2, random.nextInt(10) + 3);
            starShooter.setPosition(random.nextInt(SCREEN_WIDTH), -20, 90);
            enemies.add(starShooter);
        }
    }
    public static void initialEnemiesMaker(){
        //reminder: del is a pause between consecutive instances of fire, tim sets initial state of fire
        // setting del=5 and tim=4 will result in bullets being fired 1,6,11,16,... game ticks after creation of ship
        //enemy 1
        Enemy Spawn= new Enemy(dot,0,1,2,true);
        Spawn.setPosition((double)SCREEN_WIDTH/2,-20,90);
        Enemy E1Spawn= new Enemy(dot,0,1,2,true);
        Enemy e = new Enemy(enemy, 0, 0.5, 1);
        e.setModifier(infHealthIncrease,5,15);
        e.addBullet(new Bullet(bullet,0,e.mvScale*2,1),3,1);
        E1Spawn.addBullet(e,8,4);
        Spawn.addBullet(E1Spawn,19,18);


        //enemy2 boss
        Enemy e2=new Enemy(enemy2,6,0.2,1);
        e2.hp=30;
        e2.setModifier(healthIncreaseBy20,0,1);
        Bullet blueBulletR=new Bullet(bluebullet,0,0.8,1);
        blueBulletR.dmg=3;
        blueBulletR.setModifier(rightTwister10,0,1);
        Bullet blueBulletL=new Bullet(bluebullet,0,0.8,1);
        blueBulletL.dmg=3;
        blueBulletL.setModifier(leftTwister10,0,1);
        e2.addBullet(blueBulletL,3);
        e2.addBullet(blueBulletL.copy(90),3);
        e2.addBullet(blueBulletL.copy(180),3);
        e2.addBullet(blueBulletL.copy(270),3);
        e2.addBullet(blueBulletR,3);
        e2.addBullet(blueBulletR.copy(90),3);
        e2.addBullet(blueBulletR.copy(180),3);
        e2.addBullet(blueBulletR.copy(270),3);
        Enemy E2Spawn=new Enemy(dot,7,1,2,false);
        E2Spawn.setPosition((double)SCREEN_WIDTH/2,-10,90);
        E2Spawn.addBullet(e2,70,30);
        enemies.add(Spawn);
        enemies.add(E2Spawn);

        //enemy3 sail
        Enemy e3=new Enemy(enemy3,0,0.3,1);
        e3.hp=5;
        e3.setModifier(healthIncreaseBy3,0,3);
        Enemy E3Spawn=new Enemy(dot,0,1,2,true);
        Bullet redSideMoveR30=new Bullet(bullet,0,0.75,1);
        redSideMoveR30.setModifier(lrShower8in30at30,0,1);
        redSideMoveR30.rotateOffSet=-30;
        Bullet redSideMoveR60=new Bullet(bullet,0,0.75,1);
        redSideMoveR60.setModifier(lrShower8in30at60,0,1);
        redSideMoveR60.rotateOffSet=-60;
        Bullet redSideMoveL30=new Bullet(bullet,0,0.75,1);
        redSideMoveL30.setModifier(rlShower8in30at30,0,1);
        redSideMoveL30.rotateOffSet=30;
        Bullet redSideMoveL60=new Bullet(bullet,0,0.75,1);
        redSideMoveL60.setModifier(rlShower8in30at60,0,1);
        redSideMoveL60.rotateOffSet=60;
        e3.addBullet(redSideMoveR30,4);
        e3.addBullet(redSideMoveR60,4);
        e3.addBullet(redSideMoveL30,4);
        e3.addBullet(redSideMoveL60,4);
        E3Spawn.addBullet(e3,22,12);
        Spawn.addBullet(E3Spawn,115,100);

        //enemy4 normal+ speedyBoi
        Enemy e4=new Enemy(enemy4,8,0.7,1);
        e4.setModifier(infHealthIncrease,4,8);
        Bullet purpleBullet=new Bullet(purplebullet,0,1,1);
        purpleBullet.dmg=2;
        e4.addBullet(purpleBullet.copy(-8),1);
        e4.addBullet(purpleBullet.copy(8),1);
        E1Spawn.addBullet(e4,17,7);
        //shoot by e1 spawn

        //powerUp
        Enemy PowerDrop=new Enemy(dot,0,1,2,true);
        PowerDrop.setPosition((double) SCREEN_WIDTH/2,-40,90);
        Bullet powerUp=new Bullet(powerup,0,0.3,3);
        powerUp.setDeathModifier(randomPlayerBonus);
        PowerDrop.addBullet(powerUp,15,7);

        enemies.add(PowerDrop);
    }

}