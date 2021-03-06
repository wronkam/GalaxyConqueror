package GalaxyConqueror.Model;


import GalaxyConqueror.Model.Ships.Enemy;
import GalaxyConqueror.Model.Ships.Player;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

import static GalaxyConqueror.View.View.background;

public class Model {
    public static boolean gameOver = false;
    public static VBox box = new VBox();
    public static ImageView splashView = new ImageView(background);
    public static Pane root = new Pane(box);
    public static int score = 0;
    public static Label scoreLabel = new Label();
    public static Label hpLabel = new Label();
    public static Label dmgLabel = new Label();
    public static Player player = new Player();
    public static ArrayList<Bullet> bullets = new ArrayList<>();
    public static ArrayList<Enemy> enemies = new ArrayList<>();
    public static  ArrayList<ArrayList<moveVal>> moveList=new ArrayList<>();
    public static ArrayList<Enemy> BGFactories=new ArrayList<>();
    public static ArrayList<Enemy> templateEnemies;
    public static ArrayList<Bullet> templateBullets;
    public static class moveVal {
        public double x;
        public double y;
        public double rotation;
        public moveVal(double x, double y, double rot){
            this.x=x;
            this.y=y;
            this.rotation=rot;
        }
    }





}
