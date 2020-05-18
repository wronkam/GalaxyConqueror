package GalaxyConqueror.Model;

public class Constants {
    //TUTAJ TRZEBA OGARNĄĆ CZY POTRZEBNE NAM SĄ INTY CZY DOUBLE
    //Zdecydowanie double, int w całości nie wykożystamy, a double są natywne do obsługi pozycji i dadzą lepszą dokładność

    //ile trwa jeden frame
    public static final double TICK = 100000;

    //o ile się poruszają obiekty
    public static final double PLAYER_DX = 4;
    public static final double PLAYER_DY = 4;
    public static final double PLAYER_ROT = 4;

    //co ile się spawnią rzeczy
    public static final double ENEMY_SPAWN_TICK = 1000;

    //delaye
    public static final double BULLET_DELAY = 300;

    //wymiary planszy
    public static  final int SCREEN_WIDTH = 1920;
    public static  final int SCREEN_HEIGHT = 1020;

    //wymiary obiektów
    public static final double PLAYER_WIDTH = 130;
    public static final double PLAYER_HEIGHT = 110;
    public static final double ENEMY_WIDTH = 130;
    public static final double ENEMY_HEIGHT = 110;
    public static final double BULLET_WIDTH = 40;
    public static final double BULLET_HEIGHT = 50;





}