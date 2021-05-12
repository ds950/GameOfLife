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
    boolean[][] lifeGeneration = new boolean [LIFE_SIZE][LIFE_SIZE]; // current gen
    boolean[][] nextGeneration = new boolean [LIFE_SIZE][LIFE_SIZE]; // next gen
    volatile boolean goNextGeneration = false;
    int showDelay = 200;
    Canvas canvasPanel; // area to draw smth
    JFrame frame;
    Random random = new Random();

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
        stepButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                processOfLife();
                canvasPanel.repaint();
            }
        });

        final JButton goButton = new JButton("Play");
        goButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goNextGeneration = !goNextGeneration;
                goButton.setText(goNextGeneration ? "Stop" : "Play");
            }
        });

        JPanel btnPanel = new JPanel(); // button panel
        btnPanel.add(fillButton);
        btnPanel.add(stepButton);
        btnPanel.add(goButton);

        frame.getContentPane().add(BorderLayout.CENTER, canvasPanel); // main field
        frame.getContentPane().add(BorderLayout.SOUTH, btnPanel); // button panel
        frame.setVisible(true); // visible

        while (true) {
            if (goNextGeneration) {
                processOfLife();
                canvasPanel.repaint();
                try {
                    Thread.sleep(showDelay);
                } catch (InterruptedException e) {e.printStackTrace(); }
            }
        }
    }

    //randomly fill cells
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

    int countNeighbors(int x, int y) {
        int count = 0;
        for (int dx = -1; dx < 2; dx++) {
            for (int dy = -1; dy < 3; dy++) {
                int nX = x + dx;
                int nY = y + dy;
                nX = (nX < 0) ? LIFE_SIZE -1 : nX;
                nY = (nY < 0) ? LIFE_SIZE -1 : nY;
                nX = (nX > LIFE_SIZE - 1) ? 0 : nX;
                nY = (nY > LIFE_SIZE - 1) ? 0 : nY;
                count += (lifeGeneration[nX][nY]) ? 1 : 0;
            }
        }
        if (lifeGeneration[x][y]) {
            count--;
        }
        return count;
    }


    // main process
    void processOfLife() {
        for (int x = 0; x < LIFE_SIZE; x++) {
            for (int y = 0; y < LIFE_SIZE; y++) {
                int count = countNeighbors(x, y);
                nextGeneration[x][y] = lifeGeneration[x][y];
                // if are 3 neighbore around empty cells - cell become alive
                nextGeneration[x][y] = (count == 3) ? true : nextGeneration[x][y];
                // if cell has less than 2 or more than 3 neighbors - it will die
                nextGeneration[x][y] = (count < 2) || (count > 3) ? false : nextGeneration[x][y];
            }
        }
        for (int x = 0; x < LIFE_SIZE; x ++) {
            System.arraycopy(nextGeneration[x], 0, lifeGeneration[x], 0, LIFE_SIZE);
        }
        //countGeneration++;
    }

    // visual view of filled cells
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
