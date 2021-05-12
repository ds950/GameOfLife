/**
* Conway's Game of Life
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class GameOfLife {

    final String NAME_OF_GAME = "Conway's Game of Life"; // window's title
    final int START_LOCATION = 200; //const start dot
    final int LIFE_SIZE = 50; // count of dots
    final int POINT_RADIUS = 10; // radius of dot (visual)
    final int FIELD_SIZE = (LIFE_SIZE + 1) * POINT_RADIUS - 3; // field size
    final int BTN_PANEL_HEIGHT = 58; // height of button panel (footer)
    Random random = new Random();
    boolean[][] lifeGeneration = new boolean [LIFE_SIZE][LIFE_SIZE]; // current gen
    boolean[][] nextGeneration = new boolean [LIFE_SIZE][LIFE_SIZE]; // next gen
    JFrame frame; // window object
    Canvas canvasPanel; // area to draw smth

    public static void main(String[] args) {
            new GameOfLife().go();
    }

    void go() {
        frame = new JFrame (NAME_OF_GAME); // new window object
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE); // Close on X
        frame.setSize (FIELD_SIZE, FIELD_SIZE + BTN_PANEL_HEIGHT); // window size
        frame.setLocation (START_LOCATION, START_LOCATION); // up-left corner
        frame.setResizable (false); // resizable or not

        canvasPanel = new Canvas(); // game field (visual)
        canvasPanel.setBackground(Color.white); // field color

        JButton fillButton = new JButton("Fill"); // add "fill" button
        fillButton.addActionListener(new fillButtonListener()); // button click handler

        JButton stepButton = new JButton("Step");

        JPanel btnPanel = new JPanel(); // button panel
        btnPanel.add(fillButton);
        btnPanel.add(stepButton);

        frame.getContentPane().add(BorderLayout.CENTER, canvasPanel); // main field
        frame.getContentPane().add(BorderLayout.SOUTH, btnPanel); // button panel
        frame.setVisible (true); // visible

    }

    //random fill cells
    public class fillButtonListener implements ActionListener {
        public void actionPerformed (ActionEvent ev) {
            //countGeneration = 1;
            for (int x = 0; x < LIFE_SIZE; x++) {
                for (int y = 0; y < LIFE_SIZE; y++) {
                    lifeGeneration[x][y] = random.nextBoolean();
                }
            }
            canvasPanel.repaint();
        }
    }

    public class Canvas extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            for (int x = 0; x < LIFE_SIZE; x++) {
                for (int y = 0; y < LIFE_SIZE; y++) {
                    if (lifeGeneration[x][y]) {
                        g.fillOval(x*POINT_RADIUS, y*POINT_RADIUS, POINT_RADIUS, POINT_RADIUS);
                    }
                }
            }
        }
    }

}
