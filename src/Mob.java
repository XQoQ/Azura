import javax.swing.*;
import java.awt.*;

public class Mob {
    private int x, y;
    private int ID;
    private int direction;
    private int hp, hpLimit;
    private int speed;
    private Rectangle hitBox;
    private Image img = null;
    private int[][] mobList = {
            //0hp 1speed
            {20,   1} // slime
    };

    public Mob(int x, int y, int ID, int direction) {
        this.x = x;
        this.y = y;
        this.ID = ID;
        this.direction = direction;
        this.hp = mobList[ID][0];
        this.hpLimit = mobList[ID][0];
        this.speed = mobList[ID][1];

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

    public int getHp() {
        return hp;
    }

    public void adjustHp(int hp) {
        this.hp -= hp;
    }
}
