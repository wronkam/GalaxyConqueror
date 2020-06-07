package GalaxyConqueror.Controller;

import GalaxyConqueror.Model.Model;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static GalaxyConqueror.App.canIConfigureJavaFXMedia;
import static GalaxyConqueror.Controller.ReadScore.readScores;
import static GalaxyConqueror.Model.Constants.*;
import static GalaxyConqueror.Model.Engine.engine;
import static GalaxyConqueror.Model.Model.*;
import static GalaxyConqueror.View.View.bgPlayer;

public class Controller {
    static int first = 0;
    static Scene scene = null;
    public static void start (Stage stage) {
        Scanner l = null;
        try {
            l = new Scanner(new File("./resources/moveList.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if(l==null)
                throw new NullPointerException("Scanner input missing");
            while (l.hasNext()) {
                String line = l.nextLine();
                String[] move = line.split(";");
                ArrayList<Model.moveVal> Array = new ArrayList<>();
                for (String cords : move) {
                    String[] xy = cords.split(",");
                    Array.add(new Model.moveVal(Double.parseDouble(xy[0]), Double.parseDouble(xy[1]),Double.parseDouble(xy[2])));
                }
                try {
                    moveList.add(Array);
                }catch (Exception e){
                    System.out.println(e+" lmao ");
                }
            }
        }catch (Exception e){
            System.out.println(e+"error on moveList.txt");
        }
        if(canIConfigureJavaFXMedia) {
            bgPlayer.setOnEndOfMedia(new Runnable() {
                public void run() {
                    bgPlayer.seek(Duration.ZERO);
                }
            });
            bgPlayer.setVolume(0.15);
        }
        try {
            if(first == 0) {
                first++;
                VBox labels = new VBox();
                dmgLabel.setTextFill(Color.WHITE);
                dmgLabel.setAlignment(Pos.TOP_LEFT);
                dmgLabel.setFont(new Font(40));
                hpLabel.setTextFill(Color.WHITE);
                hpLabel.setAlignment(Pos.TOP_LEFT);
                hpLabel.setFont(new Font(40));
                scoreLabel.setTextFill(Color.WHITE);
                scoreLabel.setAlignment(Pos.TOP_LEFT);
                scoreLabel.setFont(new Font(40));
                labels.getChildren().addAll(scoreLabel, hpLabel,dmgLabel);
                stage.setTitle("Galaxy Conqueror");
                box.getChildren().add(splashView);
                root.getChildren().add(player.me);
                root.getChildren().addAll(labels);
                scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
                scene.addEventFilter(KeyEvent.KEY_PRESSED, ActionControl::keyPressed);
                scene.addEventFilter(KeyEvent.KEY_RELEASED, ActionControl::keyReleased);
            }
            Restart.reset();
            stage.setScene(scene);
            stage.show();
            AnimationTimer timer = new AnimationTimer() {
                private long last = 0;
                private int temp = 0;

                @Override
                public void handle(long now) {
                    if (now - last >= TICK) {
                        if(player.isDead() && temp == 0) {

                            readScores();
                            Highscores.Scores.add(String.valueOf(score));
                            Highscores.Scores.sort((s, t1) -> {
                                if(s.length() > t1.length())
                                {
                                    return -1;
                                }
                                else if(s.length() == t1.length())
                                {
                                    return t1.compareTo(s);
                                }
                                else
                                {
                                    return 1;
                                }
                            });
                            Highscores.Scores.remove(8);
                            try {
                                PrintWriter writer = new PrintWriter("./resources/scores.txt");
                                writer.close();
                                BufferedWriter writer2 = new BufferedWriter(new FileWriter("./resources/scores.txt"));
                                for(String s : Highscores.Scores)
                                {
                                    writer2.write(s+",");
                                }
                                writer2.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            hpLabel.setText("HP: " + 0); //no to powinno być zmienione
                            temp = 1;
                            this.stop();
                            VBox ShowScore = new VBox();
                            Label result = new Label();
                            result.setText("Your score: " + score);
                            ShowScore.setPadding(new Insets(400,0 , 0, 620));
                            ShowScore.setSpacing(40);
                            ShowScore.getChildren().add(result);
                            int right = 0;
                            while(score >= 10){
                                right++;
                                score = score / 10;
                            }
                            Button ExitGame = new Button();
                            ExitGame.setPrefSize(590 + right * 50, 100);
                            ExitGame.setText("Exit");
                            ExitGame.setOnAction(value ->
                            {
                                Menu.start(stage);
                                root.getChildren().remove(ShowScore);
                            });
                            ShowScore.getChildren().add(ExitGame);
                            root.getChildren().add(ShowScore);
                            try
                            {
                                AnimationTimer timer= new AnimationTimer() {
                                    @Override
                                    public void handle(long now) {
                                            if (ExitGame.isHover()) {
                                                ExitGame.setStyle("-fx-border-color: #00ff00;" +
                                                        "-fx-border-width:3px;" +
                                                        "-fx-background-color: transparent;" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-text-fill: #00ff00; -fx-font-size: 40");
                                                result.setStyle("-fx-text-fill: #00ff00;" +
                                                        "-fx-font-size: 80;" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-background-color: transparent;");
                                            } else {
                                                ExitGame.setStyle("-fx-border-color: #ffffff;" +
                                                        "-fx-border-width:3px;" +
                                                        "-fx-background-color: transparent;" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-text-fill: #ffffff;" + "-fx-font-size: 40");
                                                result.setStyle("-fx-text-fill: #ffffff;" +
                                                        "-fx-font-size: 80;" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-background-color: transparent;");
                                            }
                                    }
                                };
                                timer.start();
                            }catch(Exception e) {
                                e.printStackTrace();
                            }
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