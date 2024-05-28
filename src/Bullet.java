import javax.swing.*;
import java.awt.*;

public class Bullet {
    private int ID;
    private int x, y;
    private int atk;
    private int speed;
    private String direction;
    private Rectangle rect;
    private Image img;
    private int[][] bulletList = {
            //0atk 1speed
            {10,    8}, //arrow
            {20,    6}
    };


    public Bullet(int ID, String direction) {
        this.ID = ID;
        this.atk = bulletList[ID][0];
        this.speed = bulletList[ID][1];
        this.direction = direction;
        this.img = new ImageIcon("image/Bullet/weapon" + ID + "_" + direction.substring(0, 1) + "_" + 1 + ".png").getImage();
        this.x = 0;
        this.y = 0;
        this.rect = new Rectangle(x, y, img.getWidth(null), img.getHeight(null));
    }

    public void renderBullet(GamePanel gp, Graphics2D g2d) {
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
}
