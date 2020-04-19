package GalaxyConqueror.Model;

public class Constants {
    //TUTAJ TRZEBA OGARNĄĆ CZY POTRZEBNE NAM SĄ INTY CZY DOUBLE
    //Zdecydowanie double, int w całości nie wykożystamy, a double są natywne do obsługi pozycji i dadzą lepszą dokładność

    //ile trwa jeden frame
    public static final double TICK = 1000;

    //o ile się poruszają obiekty
    public static final double PLAYER_DX = 8;
    public static final double PLAYER_DY = 8;
    public static final double PLAYER_ROT = 5;
    public static final int ENEMY_DX = 0;
    public static final int ENEMY_DY = 2;
    public static final double BULLET_DX = 8;
    public static final double BULLET_DY = 8;

    //co ile się spawnią rzeczy
    public static final double ENEMY_SPAWN_TICK = 1000;

    //delaye
    public static final double BULLET_DELAY = 50;

    //wymiary planszy
    public static  final int SCREEN_WIDTH = 1920;
    public static  final int SCREEN_HEIGHT = 1080;

    //wymiary obiektów
    public static final double PLAYER_WIDTH = 130;
    public static final double PLAYER_HEIGHT = 110;
    public static final double ENEMY_WIDTH = 130;
    public static final double ENEMY_HEIGHT = 110;
    public static final double BULLET_WIDTH = 40;
    public static final double BULLET_HEIGHT = 50;





}