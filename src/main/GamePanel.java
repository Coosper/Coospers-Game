package main;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //SCREEN SETTINGS
    final int originalTileSize = 64; //64x64 tile
    final int tileScale = 1;         //No scaling
    final int tileSize = originalTileSize * tileScale; //64x64 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; //1024 pixels.
    final int screenHeight = tileSize * maxScreenRow; //768 pixels.

    KeyboardHandler keyHandler = new KeyboardHandler();
    Thread gameThread;

    //Set players default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 5;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        while(gameThread != null) {
            //1. Updating information.
            update();

            //2. Drawing the screeen with updated information
            repaint();
        }
    }

    public void update() {
        if (keyHandler.upPressed == true) {
            playerY -= playerSpeed;
        } else if (keyHandler.downPressed == true) {
            playerY += playerSpeed;
        } else if (keyHandler.leftPressed == true) {
            playerX -= playerSpeed;
        } else if (keyHandler.rightPressed == true) {
            playerX += playerSpeed;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);
        g2.fillRect(playerX,playerY,tileSize,tileSize);
        g2.dispose();
    }
}
