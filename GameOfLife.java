/**
* Conway's Game of Life
*/

import java.awt.*;
import javax.swing.*;

public class GameOfLife {

    final String NAME_OF_GAME = "Conway's Game of Life"; // window's title
    final int START_LOCATION = 200; //const start dot
    final int LIFE_SIZE = 50; // life count in one dimention
    boolean[][] lifeGeneration = new boolean [LIFE_SIZE][LIFE_SIZE]; // current gen
    boolean[][] nextGeneration = new boolean [LIFE_SIZE][LIFE_SIZE]; // next gen
    JFrame frame; // window object
    Canvas canvasPanel; // area to draw smth

    public static void main(String[] args) {
            new GameOfLife().go();
    }

    void go() {
        frame = new JFrame (NAME_OF_GAME);
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE); // Close on X
        frame.setSize (300, 300); // window size
        frame.setLocation (START_LOCATION, START_LOCATION); // up-left corner
        frame.setResizable (false); // resizable or not
        frame.setVisible (true); // visible



    }

}
