package GalaxyConqueror.Model;

import GalaxyConqueror.Controller.Garbage;
import GalaxyConqueror.Model.Ships.Enemy;
import GalaxyConqueror.Model.Ships.Player;

import static GalaxyConqueror.Controller.ActionControl.*;
import static GalaxyConqueror.Controller.ActionControl.goLeft;
import static GalaxyConqueror.Controller.Shoot.shoot;
import static GalaxyConqueror.Model.Constants.*;
import static GalaxyConqueror.Model.Constants.PLAYER_DX;
import static GalaxyConqueror.Model.Model.*;
import static GalaxyConqueror.Model.Model.bullets;
import static GalaxyConqueror.View.View.greenbullet;

public class Engine {
    private static long enemySpawnLast = 0;
    private static long bulletDelayLast = 0;


    public static void engine() {
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
        moveEnemies();
        moveBullets();
        movePlayer();
        Garbage.clean();

    }

    private static void movePlayer() {
        int dx = 0, dy = 0;
        if (goUp) dy -= PLAYER_DY;
        if (goDown) dy += PLAYER_DY;
        if (goRight) dx -= PLAYER_DX;
        if (goLeft) dx += PLAYER_DX;
        player.move(dx, dy);
    }

    private static void moveBullets() {
        for (Bullet b : bullets)
            b.move(5);
    }

    private static void moveEnemies() {
        for (Enemy e : enemies) {
            e.autoMove();
        }
    }

    private static void shootPlayer () {
        if (Player.isShooting)
            shoot(player, 0, -1, greenbullet);
    }

    //na ten moment enemiesy się respią w tym samym czasie co strzelają
    private static void shootAndSpawnEnemies () {
        for (Enemy enemy : enemies) {
            enemy.autoShoot();
        }

        Enemy e = new Enemy();
        enemies.add(e);
        root.getChildren().add(e.ship);
    }

}