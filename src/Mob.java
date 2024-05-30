import javax.swing.*;
import java.awt.*;

public class Mob {
    private int x, y;
    private int ID;
    private int direction;
    private int hp, maxHP;
    private int attack;
    private int speed;
    private Rectangle hitBox;
    private Image img = null;
    private int[][] mobList = {
            //0hp 1attack 2speed
            {100,      5   , 4} // slime
    };

    public Mob(int x, int y, int ID, int direction) {
        this.x = x;
        this.y = y;
        this.ID = ID;
        this.direction = direction;
        this.hp = mobList[ID][0];
        this.maxHP = mobList[ID][0];
        this.attack = mobList[ID][1];
        this.speed = mobList[ID][2];

        img = new ImageIcon("image/Mob/mob" + ID + "_" + direction + ".png").getImage();
        this.hitBox = new Rectangle(x, y, img.getWidth(null), img.getHeight(null));
    }

    public void renderMob(GamePanel gf, Graphics2D g2d) {
        hitBox.x = x;
        hitBox.y = y;
        g2d.drawImage(img, x, y, gf);

        g2d.setColor(Color.RED);
        g2d.fillRect(x, y - 15, 32, 10);
        g2d.setColor(Color.GREEN);
        g2d.fillRect(x, y - 15, (int)(32 * (double) hp / maxHP), 10);
    }

    public int getX() {
        return x;
    }

    public void adjustX(int x) {
        this.x += x;
    }

    public int getY() {
        return y;
    }

    public void adjustY(int y) {
        this.y += y;
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

    public int getAttack() {
        return attack;
    }

    public int getSpeed() {
        return speed;
    }
}
