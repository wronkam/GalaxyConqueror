package GalaxyConqueror.View;

import javafx.scene.image.Image;

import static GalaxyConqueror.Model.Constants.*;

public class View {
    public static Image spaceship = new Image("file:resources/spaceship.png", PLAYER_WIDTH, PLAYER_HEIGHT, true, true);
    public static Image background = new Image("file:resources/background.jpg", SCREEN_WIDTH, SCREEN_HEIGHT, false, true);
    public static Image bullet = new Image("file:resources/bullet.png",BULLET_WIDTH,BULLET_HEIGHT,true,true);
    public static Image greenbullet = new Image("file:resources/greenbullet.png", BULLET_WIDTH, BULLET_HEIGHT, true, true);
    public static Image enemy = new Image("file:resources/enemy.png", ENEMY_HEIGHT, ENEMY_WIDTH, true, true);
    public static Image menubg = new Image("file:resources/menubackground.jpg", SCREEN_WIDTH, SCREEN_HEIGHT, true, true);
}