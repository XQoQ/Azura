import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Bullet {
    private int ID;
    private int x, y;
    private int atk;
    private int speed;
    private int disappearOnContact;
    private long startTime, timePassed, frameNumber;
    private String direction;
    private Rectangle rect;
    private Image img;
    private int[][] bulletList = {
            //0atk 1speed 2disappearOnContact
            {10,    10,    1}, //arrow
            {20,    15,    0}  //frost arrow
    };


    public Bullet(int ID, String direction) {
        this.ID = ID;
        this.atk = bulletList[ID][0];
        this.speed = bulletList[ID][1];
        this.disappearOnContact = bulletList[ID][2];
        this.direction = direction;
        this.img = new ImageIcon("image/Bullet/Weapon" + ID + "/weapon" + ID + "_" + direction.substring(0, 1) + "_" + 1 + ".png").getImage();
        this.x = 0;
        this.y = 0;
        this.rect = new Rectangle(x, y, img.getWidth(null), img.getHeight(null));
        this.startTime = System.currentTimeMillis();
        this.timePassed = 0;
        this.frameNumber = 1;
    }

    public void renderBullet(GamePanel gp, Graphics2D g2d) throws IOException {
        timePassed = System.currentTimeMillis() - startTime;
        if (timePassed < 100) {
            frameNumber = 1;
        } else if (timePassed < 200) {
            frameNumber = 2;
        } else if (timePassed < 300) {
            frameNumber = 3;
        } else if (timePassed < 400) {
            frameNumber = 4;
        } else {
            startTime = System.currentTimeMillis();
            timePassed = 0;
            frameNumber = 1;
        }

        img = ImageIO.read(new File("image/Bullet/Weapon" + ID + "/weapon" + ID + "_" + direction.substring(0, 1) + "_" + frameNumber + ".png"));

        if (direction.equalsIgnoreCase("up")) {
            y -= speed;
        }
        if (direction.equalsIgnoreCase("right")) {
            x += speed;
        }
        if (direction.equalsIgnoreCase("down")) {
            y += speed;
        }
        if (direction.equalsIgnoreCase("left")) {
            x -= speed;
        }
        rect.x = x;
        rect.y = y;
        g2d.drawImage(img, x, y, gp);
    }

    public Image getImg() {
        return img;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rectangle getRect() {
        return rect;
    }

    public int getAtk() {
        return atk;
    }

    public int getSpeed() {
        return speed;
    }

    public String getDirection() {
        return direction;
    }

    public int getDisappearOnContact() {
        return disappearOnContact;
    }
}
