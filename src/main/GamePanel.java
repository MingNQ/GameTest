package main;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel {
    private BufferedImage[][] frames;
    private BufferedImage[] idleFrames;

    private int currFrame;
    private int direction; // 0: up , 1: down, 2: left, 3: right

    private boolean isMoving;

    public GamePanel() {
        try {
            frames = new BufferedImage[4][4];
            idleFrames = new BufferedImage[4];

            for (int i = 0; i < 4; i++) {
                if (i == 0) {
                    idleFrames[i] = ImageIO.read(getClass().getResourceAsStream("/player/up_1.png"));
                } else if (i == 1) {
                    idleFrames[i] = ImageIO.read(getClass().getResourceAsStream("/player/down_1.png"));
                } else if (i == 2) {
                    idleFrames[i] = ImageIO.read(getClass().getResourceAsStream("/player/left_1.png"));
                } else if (i == 3) {
                    idleFrames[i] = ImageIO.read(getClass().getResourceAsStream("/player/right_1.png"));
                }
                for (int j = 0; j < 4; j++) {
                    if (i == 0) {
                        frames[i][j] = ImageIO.read(getClass().getResourceAsStream("/player/up_" + (j + 1) + ".png")); 
                    } else if (i == 1) {
                        frames[i][j] = ImageIO.read(getClass().getResourceAsStream("/player/down_" + (j + 1) + ".png"));  
                    } else if (i == 2) {
                        frames[i][j] = ImageIO.read(getClass().getResourceAsStream("/player/left_" + (j + 1) + ".png")); 
                    } else if (i == 3) {
                        frames[i][j] = ImageIO.read(getClass().getResourceAsStream("/player/right_" + (j + 1) + ".png")); 
                    }
                }
            }

            currFrame = 0;
            direction = 1;
            isMoving = false;

            Timer timer = new Timer(100, e -> {
                if (isMoving) {
                    currFrame = (currFrame + 1) % frames[direction].length;
                }
                repaint();
            });
            timer.start();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (isMoving) {
            g2.drawImage(frames[direction][currFrame], 0, 0, this);
        } else {
            g2.drawImage(idleFrames[direction], 0, 0, this);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Animation Example");
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new GamePanel());
        frame.setVisible(true);
    }
}