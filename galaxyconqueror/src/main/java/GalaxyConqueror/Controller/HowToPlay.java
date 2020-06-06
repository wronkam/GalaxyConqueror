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

public class HowToPlay {
    public static void start(Stage stage)
    {
        ImageView bg = new ImageView(View.htpbg);
        StackPane root = new StackPane();
        root.getChildren().add(bg);
        VBox exit = new VBox();
        Button ExitButton = new Button();
        ExitButton.setText("Back to Menu");
        ExitButton.setMaxSize(200, 60);
        exit.setPadding(new Insets(520, 300, 300, 865));
        exit.getChildren().add(ExitButton);
        root.getChildren().add(exit);
        ExitButton.setOnAction(value -> Menu.start(stage));
        stage.setScene(new Scene(root, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        try
        {
            AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                        if (ExitButton.isHover()) {
                            ExitButton.setStyle("-fx-border-color: transparent; -fx-border-width:3px; " +
                                    "-fx-background-color: transparent; -fx-text-fill: #00FF00; -fx-font-size: 20");
                        } else {
                            ExitButton.setStyle("-fx-border-color: transparent; -fx-border-width:3px; " +
                                    "-fx-background-color: transparent; -fx-text-fill: #087C0F; -fx-font-size: 20");
                        }
                }
            };
            timer.start();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
