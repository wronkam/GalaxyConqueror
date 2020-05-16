package GalaxyConqueror.Model;

import GalaxyConqueror.Controller.Garbage;
import GalaxyConqueror.Model.Ships.Enemy;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Random;

import static GalaxyConqueror.Controller.ActionControl.*;
import static GalaxyConqueror.Model.Constants.*;
import static GalaxyConqueror.Model.Model.*;
import static GalaxyConqueror.View.View.bullet;
import static GalaxyConqueror.View.View.enemy;

public class Engine {
    private static long enemySpawnLast = 0;
    private static long bulletDelayLast = 0;
    private static Random random=new Random();
    public static void engine() {
        scoreLabel.setText("Score: " + score);
        hpLabel.setText("HP: " + player.hp);
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

        checkCollisions();
        reactToCollisions();
        moveEnemies();
        moveBullets();
        movePlayer();
        Garbage.clean();

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
        for (Enemy enemy : enemies) {
            enemy.autoShoot();
        }
        Enemy e = new Enemy(enemy, 0, 0.5, 1);
        e.addBullet(new Bullet(bullet,0,e.mvScale*2,1),2);
        e.setPosition(random.nextInt((int) (SCREEN_WIDTH-e.me.getBoundsInLocal().getWidth())),50,90);
        enemies.add(e);
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
            //allied objects
            if (b.collisionId == 0) {
                for (Enemy e : enemies) {
                    if (b.me.getBoundsInParent().intersects(e.me.getBoundsInParent())) {
                        e.subtractHp(b.getDmg());
                        b.kill();
                    }
                }
            }
            //enemy objects
            if (b.collisionId == 1) {
                if (b.me.getBoundsInParent().intersects(player.me.getBoundsInParent()) ) {
                    player.subtractHp(b.getDmg());
                    b.kill();
                }
            }
        }
        for (Enemy e : enemies) {
            if (e.me.getBoundsInParent().intersects(player.me.getBoundsInParent())) {
                player.kill();
            }
        }

    }

    private static void gameOver () {
        System.out.println("Game over");
    }

}