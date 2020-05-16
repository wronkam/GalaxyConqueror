package GalaxyConqueror.View;

import javafx.scene.image.Image;

import static GalaxyConqueror.Model.Constants.*;

public class View {
    public static Image spaceship = new Image("file:resources/spaceship.png", PLAYER_WIDTH, PLAYER_HEIGHT, true, true);
    public static Image background = new Image("file:resources/background.png", SCREEN_WIDTH, SCREEN_HEIGHT, false, true);
    public static Image bullet = new Image("file:resources/bullet.png",BULLET_WIDTH,BULLET_HEIGHT,true,true);
    public static Image greenbullet = new Image("file:resources/greenbullet.png", BULLET_WIDTH, BULLET_HEIGHT, true, true);
    public static Image enemy = new Image("file:resources/enemy.png", ENEMY_WIDTH, ENEMY_HEIGHT, true, true);
    public static Image menubg = new Image("file:resources/menubackground.jpg", SCREEN_WIDTH, SCREEN_HEIGHT, true, true);
    public static Image dot = new Image("file:resources/dot.png", 1, 1, true, true);
    public static Image star = new Image("file:resources/star.png", 20, 20, true, true);
}