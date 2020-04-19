package GalaxyConqueror.Controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static GalaxyConqueror.View.View.menubg;

/* First version of main menu
TODO:
- zmienic przyciski na obrazki lub dodac im background
- zmienic font napisow na przyciskach na jakis bardziej pixelartowy
-
*/

public class Menu {

    public static void start (Stage stage) {

        ImageView bg = new ImageView(menubg);

        Button NewGameButton = new Button("New Game");
        Button SettingsButton = new Button("Settings");
        Button HsButton = new Button("Highscores");
        Button ExitButton = new Button("Exit");

        NewGameButton.setPrefSize(200, 60);
        NewGameButton.setStyle("-fx-border-color: #ffffff; -fx-border-width:3px; -fx-background-color: transparent; -fx-text-fill: #ffffff; -fx-font-size: 15");
        SettingsButton.setPrefSize(200, 60);
        SettingsButton.setStyle("-fx-border-color: #ffffff; -fx-border-width:3px; -fx-background-color: transparent; -fx-text-fill: #ffffff; -fx-font-size: 15");
        HsButton.setPrefSize(200, 60);
        HsButton.setStyle("-fx-border-color: #ffffff; -fx-border-width:3px; -fx-background-color: transparent; -fx-text-fill: #ffffff; -fx-font-size: 15");
        ExitButton.setPrefSize(200, 60);
        ExitButton.setStyle("-fx-border-color: #ffffff; -fx-border-width:3px; -fx-background-color: transparent; -fx-text-fill: #ffffff; -fx-font-size: 15");

        NewGameButton.setOnAction(value -> Controller.start(stage));
        SettingsButton.setOnAction(value -> SettingsButton.setText("Work in progress"));
        HsButton.setOnAction(value -> HsButton.setText("Work in progress"));
        ExitButton.setOnAction(value -> {
            if(ExitButton.getText().equals("Exit")) {
                ExitButton.setText("Are you sure?");
            }
            else{
                stage.close();
            }
        });

        VBox menubox = new VBox();
        menubox.setAlignment(Pos.TOP_LEFT);
        menubox.setPadding(new Insets(280,0 , 0, 860));
        menubox.setSpacing(25);
        menubox.getChildren().add(NewGameButton);
        menubox.getChildren().add(SettingsButton);
        menubox.getChildren().add(HsButton);
        menubox.getChildren().add(ExitButton);

        BorderPane test = new BorderPane(menubox);
        StackPane menu = new StackPane();
        menu.getChildren().add(bg);
        menu.getChildren().add(test);

        Scene scene = new Scene(menu, 1920, 1080);
        stage.setScene(scene);
        stage.show();
    }
}



