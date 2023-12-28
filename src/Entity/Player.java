package Entity;

import main.GamePanel;
import main.KeyboardHandler;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.*;

public class Player extends Entity {
    BufferedImage[] spriteUp;
    BufferedImage[] spriteDown;
    BufferedImage[] spriteLeft;
    BufferedImage[] spriteRight;
    BufferedImage currentSprite;

    // Animation control
    int spriteCounter = 0;
    int spriteNum = 0;
    int animationDelay = 10;

    GamePanel gp;
    KeyboardHandler keyHandler;

    public Player (GamePanel gp, KeyboardHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;
        spriteUp = new BufferedImage[2];
        spriteDown = new BufferedImage[2];
        spriteLeft = new BufferedImage[2];
        spriteRight = new BufferedImage[2];
        setDefaultValues();
        loadSprite();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 5;
    }

    private void loadSprite() {
        try {
            spriteUp[0] = ImageIO.read(getClass().getResourceAsStream("/upstep1.png"));
            spriteUp[1] = ImageIO.read(getClass().getResourceAsStream("/upstep2.png"));
            spriteDown[0] = ImageIO.read(getClass().getResourceAsStream("/downstep1.png"));
            spriteDown[1] = ImageIO.read(getClass().getResourceAsStream("/downstep2.png"));
            spriteLeft[0] = ImageIO.read(getClass().getResourceAsStream("/leftstep1.png"));
            spriteLeft[1] = ImageIO.read(getClass().getResourceAsStream("/leftstep2.png"));
            spriteRight[0] = ImageIO.read(getClass().getResourceAsStream("/rightstep1.png"));
            spriteRight[1] = ImageIO.read(getClass().getResourceAsStream("/rightstep2.png"));
            currentSprite = spriteDown[0];
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        spriteCounter++;
        if (spriteCounter > animationDelay) {
            spriteCounter = 0;
            spriteNum = (spriteNum + 1) % 2; //This will make the sprite alternate between 1 and 2.
        }

        if (keyHandler.upPressed == true) {
            y -= speed;
            currentSprite = spriteUp[spriteNum];
        } else if (keyHandler.downPressed == true) {
            y += speed;
            currentSprite = spriteDown[spriteNum];
        } else if (keyHandler.leftPressed == true) {
            x -= speed;
            currentSprite = spriteLeft[spriteNum];
        } else if (keyHandler.rightPressed == true) {
            x += speed;
            currentSprite = spriteRight[spriteNum];
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(currentSprite, x, y, gp.tileSize, gp.tileSize, null);
    }
}
