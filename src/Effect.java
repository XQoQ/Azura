import javax.swing.*;
import java.awt.*;

public class Effect {
    private Image effect;
    private int efID;
    private int x, y;
    private long lifespan, timePassed;


    public Effect(int ID, int x, int y) {
        this.efID = ID;
        this.x = x;
        this.y = y;
        this.lifespan = 500;
        this.timePassed = 0;
        this.effect = new ImageIcon("image/Effect/ef" + efID + ".gif").getImage();
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
}
