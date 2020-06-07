package GalaxyConqueror.View;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

import static GalaxyConqueror.App.canIConfigureJavaFXMedia;
import static GalaxyConqueror.Model.Constants.*;

public class View<bgM> {
    public static Image spaceship = new Image("file:resources/spaceship.png", PLAYER_WIDTH, PLAYER_HEIGHT, true, true);
    public static Image background = new Image("file:resources/background.png", SCREEN_WIDTH, SCREEN_HEIGHT, false, true);
    public static Image bullet = new Image("file:resources/bullet.png",BULLET_WIDTH,BULLET_HEIGHT,true,true);
    public static Image greenbullet = new Image("file:resources/greenbullet.png", BULLET_WIDTH, BULLET_HEIGHT, true, true);
    public static Image bluebullet = new Image("file:resources/bluebullet.png", BULLET_WIDTH, BULLET_HEIGHT, true, true);
    public static Image purplebullet = new Image("file:resources/purplebullet.png", BULLET_WIDTH, BULLET_HEIGHT, true, true);
    public static Image enemy = new Image("file:resources/enemy.png", ENEMY_HEIGHT, ENEMY_WIDTH, true, true);
    public static Image enemy2 = new Image("file:resources/enemy2.png", ENEMY_HEIGHT*1.2, ENEMY_WIDTH*1.2, true, true);
    public static Image enemy3 = new Image("file:resources/enemy3.png", ENEMY_HEIGHT*1.1, ENEMY_WIDTH*1.1, true, true);
    public static Image enemy4 = new Image("file:resources/enemy4.png", ENEMY_HEIGHT*0.8, ENEMY_WIDTH, true, true);
    public static Image menubg = new Image("file:resources/menubackground.jpg", SCREEN_WIDTH, SCREEN_HEIGHT+60, true, true);
    public static Image dot = new Image("file:resources/dot.png", 1, 1, true, true);
    public static Image star = new Image("file:resources/star.png", 20, 20, true, true);
    public static Image htpbg = new Image("file:resources/htpbg.jpg", SCREEN_WIDTH, SCREEN_HEIGHT+60, true, true);
    public static Image bg = new Image("file:resources/bg.jpg", SCREEN_WIDTH, SCREEN_HEIGHT+60, true, true);
    public static Image powerup = new Image("file:resources/powerup.png", 75, 75, true,true);
    public static Image creditsbg = new Image("file:resources/creditsbg.jpg", SCREEN_WIDTH, SCREEN_HEIGHT+60, true, true);
    static File Ama ;
    static Media bgM ;
    public static MediaPlayer bgPlayer ;
    public static void set() {
        if (canIConfigureJavaFXMedia) {
             Ama = new File("resources/Amanita.mp3");
             bgM = new Media(Ama.toURI().toString());
             bgPlayer = new MediaPlayer(bgM);
        }
    }
}