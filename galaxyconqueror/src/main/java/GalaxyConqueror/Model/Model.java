package GalaxyConqueror.Model;


import GalaxyConqueror.Model.Ships.Enemy;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Model {
    public static int W = 1920;
    public static int H = 1080;
    public static Node player = null;
    public static ArrayList<Bullet> bullets = new ArrayList<>();
    public static ArrayList<Enemy> enemies = new ArrayList<>();
    public static VBox box = new VBox();
    public static BorderPane root = new BorderPane(box);
}