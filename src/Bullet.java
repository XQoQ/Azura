import javax.swing.*;
import java.awt.*;

public class Bullet {
    private int ID;
    private int x, y;
    private int speed;
    private String direction;
    private Rectangle rect;
    private Image img = null;
    private Ally Ally;


    public Bullet(int ID, Ally Ally) {
        this.ID = ID;
        this.img = new ImageIcon("image/Bullet/weapon" + ID + "_" + Ally.getDirection().substring(0, 1) + "_" + 1 + ".png").getImage();
        x = Ally.getX() + 10;
        y = Ally.getY() + 10;
    }

    public void move() {
        x += 5;
    }

    public Image getImg() {
        return img;
    }
}
