import javax.swing.*;
import java.awt.*;

public class Effect {
    private Image effect;
    private int efID;
    private int x, y;
    private long lifespan, startTime, timePassed;


    public Effect(int ID, int x, int y, String direction, long startTime) {
        this.efID = ID;
        this.startTime = startTime;
        this.lifespan = 500;
        this.timePassed = 0;
        this.effect = new ImageIcon("image/Effect/ef" + efID + ".gif").getImage();
        switch (direction) {
            case "up" -> {
                this.x = x - effect.getWidth(null) / 2;
                this.y = y - effect.getHeight(null) / 2;
            }
            case "right" -> {
                this.x = x;
                this.y = y - effect.getHeight(null) / 2;
            }
            case "down" -> {
                this.x = x - effect.getWidth(null) / 2;
                this.y = y;
            }
            case "left" -> {
                this.x = x - effect.getWidth(null) / 3;
                this.y = y - effect.getHeight(null) / 2;
            }
        }

    }

    public Image getEffect() {
        return effect;
    }

    public void renderEffect(GamePanel gp, Graphics2D g2d) {
        g2d.drawImage(effect, x, y, gp);
    }

    public long getLifespan() {
        return lifespan;
    }

    public long getTimePassed() {
        return timePassed;
    }

    public void setTimePassed(long timePassed) {
        this.timePassed = timePassed;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public long getStartTime() {
        return startTime;
    }
}
