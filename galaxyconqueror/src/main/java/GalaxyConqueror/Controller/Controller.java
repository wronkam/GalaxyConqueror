package GalaxyConqueror.Controller;

import GalaxyConqueror.Model.Bullet;
import GalaxyConqueror.Model.Ships.Enemy;
import GalaxyConqueror.Model.Ships.Player;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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
            VBox labels = new VBox();
          //  labels.setSpacing(10);
         //   scoreLabel.setMinWidth(SCREEN_WIDTH);
            hpLabel.setTextFill(Color.WHITE);
            hpLabel.setAlignment(Pos.TOP_LEFT);
            hpLabel.setFont(new Font(40));
            scoreLabel.setTextFill(Color.WHITE);
            scoreLabel.setAlignment(Pos.TOP_LEFT);
            scoreLabel.setFont(new Font(40));
         //   scoreLabel.setTranslateY(20);
            labels.getChildren().addAll(scoreLabel, hpLabel);
            stage.setTitle("Galaxy Conqueror");
            box.getChildren().add(splashView);
            root.getChildren().add(player.me);
            root.getChildren().addAll(labels);
            Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
            stage.setScene(scene);

            stage.show();
            scene.addEventFilter(KeyEvent.KEY_PRESSED, ActionControl::keyPressed);
            scene.addEventFilter(KeyEvent.KEY_RELEASED, ActionControl::keyReleased);



            AnimationTimer timer = new AnimationTimer() {
                private long last = 0;
                private int temp = 0;

                @Override
                public void handle(long now) {
                    if (now - last >= TICK) {
                        if(player.isDead() && temp == 0) {
                            temp = 1;
                            this.stop();
                            VBox ShowScore = new VBox();
                            Label result = new Label();
                            result.setText("Your score: " + score);
                            result.setStyle("-fx-text-fill: #00ff00; -fx-font-size: 80; -fx-font-weight: bold; -fx-background-color: transparent;");
                            ShowScore.setPadding(new Insets(440,0 , 0, 700));
                            ShowScore.setSpacing(40);
                            ShowScore.getChildren().add(result);
                            Button ExitGame = new Button();
                            int xd = 0;
                            while(score >= 10){
                                xd++;
                                score = score / 10;
                            }
                            ExitGame.setPrefSize(590 + xd * 50, 100);
                            ExitGame.setText("Exit");
                            ExitGame.setOnAction(value -> Menu.start(stage));
                            ExitGame.setStyle("-fx-border-color: #ffffff; -fx-border-width:3px; -fx-background-color: transparent; -fx-text-fill: #00ff00; -fx-font-size: 15; -fx-font-size: 35; -fx-border-color: #00ff00 ");
                            ShowScore.getChildren().add(ExitGame);
                            root.getChildren().add(ShowScore);
                        }
                        else {
                            engine();
                            last = now;
                        }
                    }
                }
            };

            timer.start();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}