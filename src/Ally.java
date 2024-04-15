import javax.swing.*;
import java.awt.*;

public class Ally extends Thread {
    private Image img = (new ImageIcon("image/MainCharacter/Ally_d.gif")).getImage();
    private int x;
    private int y;
    private int speed;
    private String direction;
    private boolean isRunning;
    private Rectangle rec;

    public Ally() {
        this.x = 32;
        this.y = 40;
        this.speed = 4;
        this.direction = "down";
        this.rec = new Rectangle(x, y, img.getWidth(null), img.getWidth(null));
    }

    @Override
    public void run() {
        while (true) {
            if (isRunning) {
                if (direction.equals("right")) {
                }
            }
        }
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
}
