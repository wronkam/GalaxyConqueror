package GalaxyConqueror.Controller;


import javafx.scene.input.KeyEvent;

import static GalaxyConqueror.Model.Model.player;


public class ActionControl {
    public static boolean goUp, goDown, goLeft, goRight,rotateLeft,rotateRight;
    public double shootingDelay;
    public static void keyPressed (KeyEvent key) {
        switch (key.getCode()) {
            case A:     rotateLeft = true; break;
            case D:     rotateRight = true; break;
            case UP:    goUp = true; break;
            case DOWN:  goDown = true; break;
            case LEFT:  goRight  = true; break;
            case RIGHT: goLeft  = true; break;
            case SPACE: player.isShooting = true; break;
        }
    }
    public static void keyReleased (KeyEvent key) {
        switch (key.getCode())
        {
            case A:     rotateLeft = false; break;
            case D:     rotateRight = false; break;
            case UP:    goUp = false; break;
            case DOWN:  goDown = false; break;
            case LEFT:  goRight  = false; break;
            case RIGHT: goLeft  = false; break;
            case SPACE: player.isShooting = false; break;
        }
    }




}