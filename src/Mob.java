import javax.swing.*;
import java.awt.*;

public class Mob {
    private int x, y;
    private int ID;
    private int direction;
    private Rectangle hitBox;
    private Image img = null;

    public Mob(int x, int y, int ID, int direction) {
        this.x = x;
        this.y = y;
        this.ID = ID;
        this.direction = direction;
        img = new ImageIcon("image/Mob/mob" + ID + "_" + direction + ".png").getImage();
        this.hitBox = new Rectangle(x, y, img.getWidth(null), img.getHeight(null));
    }

    public void renderMob(GameFrame gf, Graphics2D g2d) {
        hitBox.x = x;
        hitBox.y = y;
        g2d.drawImage(img, x, y, gf);
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

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Image getImg() {
        return img;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }
}
