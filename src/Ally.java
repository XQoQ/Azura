import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Ally{
    private Image img = (new ImageIcon("image/MainCharacter/Ally_r.png")).getImage();
    private int x, y;
    private int speed;
    private int hp, maxHp;
    private String direction;
    private boolean isRunning;
    private boolean up, right, down, left;
    private Rectangle rec;
    private Weapon wp;

    public Ally() throws IOException {
        this.x = 32;
        this.y = 600;
        this.hp = 100;
        this.maxHp = 100;
        this.speed = 5;
        this.direction = "right";
        this.wp = new Weapon(-100, -100, 0);
        this.rec = new Rectangle(x, y, img.getWidth(null), img.getWidth(null));
    }

    public Image getImg() {return img;}

    public void checkDirection() {
        if (isRunning) {
            img = (new ImageIcon("image/MainCharacter/Ally_" + direction.charAt(0) + ".gif")).getImage();
        } else {
            img = (new ImageIcon("image/MainCharacter/Ally_" + direction.charAt(0) + ".png")).getImage();
        }
    }

    public void updateAlly() {
        if (up) {
            isRunning = true;
            direction = "up";
            y -= speed;
        }
        if (right) {
            isRunning = true;
            direction = "right";
            x += speed;
        }
        if (down) {
            isRunning = true;
            direction = "down";
            y += speed;
        }
        if (left) {
            isRunning = true;
            direction = "left";
            x -= speed;
        }
        checkDirection();
    }

    public void renderAlly(GamePanel gp, Graphics2D g2d) {
        rec.x = x;
        rec.y = y;
        g2d.drawImage(img, x, y, gp);

        g2d.setColor(Color.RED);
        g2d.fillRect(x - 3, y - 20, 50, 10);
        g2d.setColor(Color.GREEN);
        g2d.fillRect(x - 3, y - 20, (int)(50 * (double) hp / maxHp), 10);
    }

    public void setRunning(boolean running) {
        isRunning = running;
        checkDirection();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getDirection() {
        return direction;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public int getHp() {
        return hp;
    }

    public void adjustHp(int hp) {
        this.hp -= hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public Rectangle getRec() {
        return rec;
    }


    public Weapon getWp() {
        return wp;
    }

    public void setWp(Weapon wp) {
        this.wp = wp;
    }
}
