package GalaxyConqueror.Model;


import GalaxyConqueror.Controller.ActionControl;
import GalaxyConqueror.Model.Ships.Enemy;
import GalaxyConqueror.Model.Ships.Player;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;


import java.util.ArrayList;

import static GalaxyConqueror.View.View.background;

public class Model {
    public static VBox box = new VBox();
    public static ImageView splashView = new ImageView(background);
    public static Pane root = new Pane(box);
    public static int score = 0;
    public static Label scoreLabel = new Label();
    public static Label hpLabel = new Label();
    public static Player player = new Player();
    public static ArrayList<Bullet> bullets = new ArrayList<>();
    public static ArrayList<Enemy> enemies = new ArrayList<>();
    public static  ArrayList<ArrayList<Pair<Double, Double>>> moveList=new ArrayList<>();





}