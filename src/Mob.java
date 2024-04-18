import javax.swing.*;
import java.awt.*;

public class Mob {
    private int x, y;
    private int ID;
    private String direction;
    private Image img = null;

    public Mob() {
        this.x = 32;
        this.y = 600;
        this.ID = 1;
        this.direction = "d";
        img = new ImageIcon("image/Mob/mob" + ID + "_" + direction + ".png").getImage();
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Image getImg() {
        return img;
    }

}
