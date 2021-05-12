/**
* Conway's Game of Life
*/

import java.awt.*;
import javax.swing.*;

public class GameOfLife {

    final String NAME_OF_GAME = "Conway's Game of Life"; // window's title
    JFrame frame; // window object
    Canvas canvasPanel; // area to draw smth

    public static void main(String[] args) {
            new GameOfLife().go();
    }

    void go() {
        frame = new JFrame(NAME_OF_GAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on X
        frame.setSize(300, 300); // window size
        frame.setLocation(200, 200); // up-left corner
        frame.setResizable(false); // resizable or not
        frame.setVisible(true); // visible



    }

}
