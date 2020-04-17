package GalaxyConqueror.Controller;

import GalaxyConqueror.Model.Bullet;
import GalaxyConqueror.Model.Ships.Enemy;
import GalaxyConqueror.Model.Ships.Player;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static GalaxyConqueror.Controller.ActionControl.*;


import static GalaxyConqueror.Controller.Shoot.shoot;
import static GalaxyConqueror.Model.Constants.*;
import static GalaxyConqueror.Model.Engine.engine;
import static GalaxyConqueror.View.View.*;
import static GalaxyConqueror.Model.Model.*;

public class Controller {
    public static void start (Stage stage) {
        Scanner l = null;
        try {
            l = new Scanner(new File("./resources/moveList.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if(l==null)
                throw new NullPointerException("Scanner imput missing");
            while (l.hasNext()) {
                String line = l.nextLine();
                String[] move = line.split(";");
                ArrayList<Pair<Double, Double>> Array = new ArrayList<>();
                for (String cords : move) {
                    String[] xy = cords.split(",");
                    Array.add(new Pair<>(Double.parseDouble(xy[0]), Double.parseDouble(xy[1])));
                }
                moveList.add(Array);
            }
        }catch (Exception e){
            System.out.println(e+"error on moveList.txt");
        }
        try {

            stage.setTitle("Galaxy Conqueror");
            box.getChildren().add(splashView);

            root.getChildren().add(player.ship);
            Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
            stage.setScene(scene);

            stage.show();
            scene.addEventFilter(KeyEvent.KEY_PRESSED, ActionControl::keyPressed);
            scene.addEventFilter(KeyEvent.KEY_RELEASED, ActionControl::keyReleased);


            AnimationTimer timer = new AnimationTimer() {
                private long last = 0;

                @Override
                public void handle(long now) {


                    if (now - last >= TICK) {
                        engine();
                        last = now;
                    }
                }
            };

            timer.start();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}