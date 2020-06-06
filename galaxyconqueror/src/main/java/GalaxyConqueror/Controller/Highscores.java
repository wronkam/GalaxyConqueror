package GalaxyConqueror.Controller;

import GalaxyConqueror.Model.Constants;
import GalaxyConqueror.View.View;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;

import static GalaxyConqueror.Controller.ReadScore.readScores;

public class Highscores {

    public static ArrayList<String> Scores = new ArrayList<>();

    public static void start(Stage stage)
    {
        readScores();
        ArrayList<Button> scores = new ArrayList<>();
        VBox buttons = new VBox();
        buttons.setPadding(new Insets(260, 300, 300, 885));
        for(int i = 1; i <= 8; i++)
        {
            scores.add(new Button());
        }
        for(Integer i = 1; i <= 8; i++) //Integer is intentional here
        {
            scores.get(i-1).setText(i.toString()+".          " + Scores.get(i-1));
            scores.get(i-1).setPrefHeight(30);
            scores.get(i-1).setStyle("-fx-border-color: transparent; -fx-border-width:3px; " +
                    "-fx-background-color: transparent; -fx-text-fill: #00FF00; -fx-font-size: 20");
        }
        for(Button b : scores)
        {
            buttons.getChildren().add(b);
        }
        ImageView bg = new ImageView(View.bg);
        StackPane root = new StackPane();
        root.getChildren().add(bg);
        root.getChildren().add(buttons);
        VBox hs = new VBox();
        Button Highs = new Button();
        Highs.setText("Highscores");
        Highs.setStyle("-fx-border-color: transparent; -fx-border-width:3px; " +
                "-fx-background-color: transparent; -fx-text-fill: #00FF00; -fx-font-size: 30");
        hs.getChildren().add(Highs);
        VBox exit = new VBox();
        Button ExitButton = new Button();
        ExitButton.setText("Back to Menu");
        ExitButton.setMaxSize(200, 60);
        exit.setPadding(new Insets(640, 300, 300, 860));
        exit.getChildren().add(ExitButton);
        root.getChildren().add(hs);
        root.getChildren().add(exit);
        hs.setPadding(new Insets(180, 300, 300, 860));
        ExitButton.setOnAction(value -> Menu.start(stage));
        stage.setScene(new Scene(root, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        try
        {
            AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    if (ExitButton.isHover()) {
                        ExitButton.setStyle("-fx-border-color: transparent; -fx-border-width:3px; " +
                                "-fx-background-color: transparent; -fx-text-fill: #FFFFFF; -fx-font-size: 20");
                    } else {
                        ExitButton.setStyle("-fx-border-color: transparent; -fx-border-width:3px; " +
                                "-fx-background-color: transparent; -fx-text-fill: #00FF00; -fx-font-size: 20");
                    }
                }
            };
            timer.start();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
