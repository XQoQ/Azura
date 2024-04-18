import javax.swing.*;
import java.awt.*;

public class Bullet {
    private int ID;
    private int x, y;
    private int speed;
    private boolean isDead;
    private String direction;
    private Rectangle rect;
    private Image img = null;
    private Ally Ally;


    public Bullet(int ID, Ally Ally) {
        this.ID = ID;
        switch (this.ID) {
            case 0 -> this.speed = 10;
            case 1 -> this.speed = 8;
            default -> this.speed = 5;
        }
        this.Ally = Ally;
        this.direction = Ally.getDirection();
        this.img = new ImageIcon("image/Bullet/weapon" + ID + "_" + Ally.getDirection().substring(0, 1) + "_" + 1 + ".png").getImage();
        x = Ally.getX();
        y = Ally.getY();
        isDead = false;
    }

    public void move() {
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

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }
}
