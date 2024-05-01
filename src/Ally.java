import javax.swing.*;
import java.awt.*;

public class Ally{
    private Image img = (new ImageIcon("image/MainCharacter/Ally_r.png")).getImage();
    private int x, y;
    private int speed;
    private String direction;
    private boolean isRunning;
    private Rectangle rec;

    public Ally() {
        this.x = 32;
        this.y = 600;
        this.speed = 4;
        this.direction = "right";
        this.rec = new Rectangle(x, y, img.getWidth(null), img.getWidth(null));
    }

    public Image getImg() {return img;}

    public void checkDirection() {
        if (isRunning) {
            if (direction.equals("down")) {
                img = (new ImageIcon("image/MainCharacter/Ally_d.gif")).getImage();
            } else if (direction.equals("left")) {
                img = (new ImageIcon("image/MainCharacter/Ally_l.gif")).getImage();
            } else if (direction.equals(("up"))) {
                img = (new ImageIcon("image/MainCharacter/Ally_u.gif")).getImage();
            } else if (direction.equals("right")) {
                img = (new ImageIcon("image/MainCharacter/Ally_r.gif")).getImage();
            }
        } else {
            if (direction.equals("down")) {
                img = (new ImageIcon("image/MainCharacter/Ally_d.png")).getImage();
            } else if (direction.equals("left")) {
                img = (new ImageIcon("image/MainCharacter/Ally_l.png")).getImage();
            } else if (direction.equals(("up"))) {
                img = (new ImageIcon("image/MainCharacter/Ally_u.png")).getImage();
            } else if (direction.equals("right")) {
                img = (new ImageIcon("image/MainCharacter/Ally_r.png")).getImage();
            }
        }
    }

    public void setDirection(String d) {
        direction = d;
    }

    public void setRunning(boolean running) {
        isRunning = running;
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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getDirection() {
        return direction;
    }
}
