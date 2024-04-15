import javax.swing.*;
import java.awt.*;

public class Background {
    private int x = 0;
    private int y = 0;
    private int bgID = 0;
    private Image img = null;

    public void draw(int id, Graphics g) {
        this.bgID = id;
        img = new ImageIcon("image/Background/ground" + bgID + ".png").getImage();
        g.drawImage(img, x, y, null);
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

    public int getBgID() {
        return bgID;
    }

    public void setBgID(int bgID) {
        this.bgID = bgID;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }
}
