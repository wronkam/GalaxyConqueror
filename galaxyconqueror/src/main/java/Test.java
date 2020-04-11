package main.java;
/*
TODO:
-obiekty
-przemieszcznanie obiektów
-action: shoot
*/
/*
Schemat:
Node -> abstract Bullet -> Spaceship -> each and every enemy
               |                |
               V               > Player

*/
/*
0.shoot
1.check colisons
2.react to colisions
3.move
*/

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.ArrayList;

public class Test extends Application {

    private static int W = 1920;
    private static int H = 1080;
    private Image spaceship = new Image("main/resources/spaceship.png", 130, 110, true,  true);
    private  Image background = new Image("main/resources/backgound.jpg", W, H, false, true);
    private Image bulletView = new Image("main/resources/bullet.png", 40, 50, true, true);
    private Node  player;
    boolean  goUp, goDown, goLeft, goRight;
    boolean isShooting;
    VBox box = new VBox();
    BorderPane root = new BorderPane(box);
    //ArrayList<? extends Enemies> Enemy;
    ArrayList<Bullet> bullets = new ArrayList<>();
    ArrayList<Image> xd = new ArrayList<Image>();// tu trzymamy obrazki
    ArrayList<ArrayList<Pair<Integer,Integer>>> MoveList;//z txt: fomat n:[[x1,y1],[x2,y2],...,[xn,yn]] w kolejnych linijkach
    public enum Direction {
        LEFT, DOWN, RIGHT, UP;
    }
    class PhysicalObject{
        ImageView me;
        double x;
        double y;
        int idImage;//index of my image in Resources
        int hp;//1 in case of bullets
        int dmg;//substracted on collision
        int idMove;//index of my MoveList
        int curMove;//current move on Move List
        double scaleMove;//scales move from MoveList by this value
    }

    class Bullet extends PhysicalObject {
        Bullet(int idImage,double startX,double startY) {
            this.x=startX;
            this.y=startY;
            this.idImage=idImage;
            me = new ImageView(xd.get(idImage));
        }
        Bullet(int idImage,int startX,int startY,int hp,int dmg) {
            this.x=startX;
            this.y=startY;
            this.idImage=idImage;
        }
        void setMoves(int idMove){
            this.idMove=idMove;
            this.curMove=0;
            this.scaleMove=1;
        }
        void setMoves(int idMove,double scaleMove){
            this.idMove=idMove;
            this.curMove=0;
            this.scaleMove=scaleMove;
        }
        void move() {
            //x+=(int)MoveList.get(idMove).get(curMove).first*scaleMove;
            //y+=(int)MoveList.get(idMove).get(curMove).second*scaleMove;
            curMove = (curMove + 1) % MoveList.get(idMove).size();
            me.relocate(x, y);
        }
        void move(int a,int b){
            x+=a;
            y+=b;
            me.relocate(x,y);
        }
    }

    @Override
    public void start(Stage stage) throws Exception
    {

        stage.setTitle("Galaxy Conqueror");
        xd.add(bulletView);
        ImageView splashView = new ImageView(background);
        box.getChildren().add(splashView);

        player = new ImageView(spaceship);
        root.getChildren().add(player);
        movePlayerTo(W / 2, 970);

        Scene scene = new Scene(root, W, H);
        stage.setScene(scene);

        scene.setOnKeyPressed(event -> {
            switch (event.getCode())
            {
                case UP:    goUp = true; break;
                case DOWN:  goDown = true; break;
                case LEFT:  goRight  = true; break;
                case RIGHT: goLeft  = true; break;
                case SPACE: isShooting = true; break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode())
            {
                case UP:    goUp = false; break;
                case DOWN:  goDown = false; break;
                case LEFT:  goRight  = false; break;
                case RIGHT: goLeft  = false; break;
                case SPACE: isShooting = false; break;
            }
        });

        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                if (isShooting)
                    shoot();
                for(int i = 0; i < bullets.size(); i++)
                {
                    bullets.get(i).move(0, -5);;
                }
                int dx = 0, dy = 0;
                if (goUp) dy -= 5;
                if (goDown) dy += 5;
                if (goRight)  dx -= 8;
                if (goLeft)  dx += 8;
                movePlayer(dx, dy);
            }
        };
        timer.start();
    }

    private void movePlayer(int dx, int dy) {
        if (dx == 0 && dy == 0)
        {
            return;
        }
        final double cx = player.getBoundsInLocal().getWidth()  / 2;
        final double cy = player.getBoundsInLocal().getHeight() / 2;
        double x = cx + player.getLayoutX() + dx;
        double y = cy + player.getLayoutY() + dy;
        movePlayerTo(x, y);
    }

    private void movePlayerTo(double x, double y)
    {
        final double cx = player.getBoundsInLocal().getWidth()  / 2;
        final double cy = player.getBoundsInLocal().getHeight() / 2;
        if(x - cx >= -10 && x + cx <= W + 10 && y - cy >= -10 && y + cy <= H - 50)
        {
            player.relocate(x - cx, y - cy);
        }
    }

    private void shoot () {
        Bullet bullet = new Bullet(0, player.getLayoutX() + player.getBoundsInLocal().getWidth()  / 2 - 20, player.getLayoutY());//Resources[0] = bulletView;
        bullets.add(bullet);
        root.getChildren().add(bullet.me);
        // bullet.setMoves(0); //MoveList[0] to ruchy dla pocisku
    }

    public static void main(String[] args)
    {
        launch(args);
    }

}
