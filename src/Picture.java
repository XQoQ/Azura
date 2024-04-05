import java.awt.*;

public class Picture {
    private float x, y;
    Image img = null;

    public Picture(float x, float y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
    }

    public void draw(Graphics g) {
        g.drawImage(img, (int) x, (int) y, null);
    }

    public void adjustX(float x) {
        this.x += x;
    }

    public void adjustY(float y) {
        this.y += y;
    }
}

