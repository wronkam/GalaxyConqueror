package GalaxyConqueror.Model;


import GalaxyConqueror.Controller.ActionControl;
import GalaxyConqueror.Model.Ships.Enemy;
import GalaxyConqueror.Model.Ships.Player;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.util.ArrayList;

import static GalaxyConqueror.View.View.background;

public class Model {

    public static Player player = new Player();
    public static ArrayList<Bullet> bullets = new ArrayList<>();
    public static ArrayList<Enemy> enemies = new ArrayList<>();

    public static VBox box = new VBox();
    public static BorderPane root = new BorderPane(box);
    public static ImageView splashView = new ImageView(background);



}