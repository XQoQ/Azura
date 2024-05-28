import org.w3c.dom.css.Rect;

import java.awt.*;

public class Item {
    private int x, y;
    private Rectangle rect;
    private Image img;

    public Item(Image img) {
        this.img = img;
    }

    public Item(int x, int y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
        this.rect = new Rectangle(x, y, img.getWidth(null), img.getHeight(null));
    }

    public void renderItem(GamePanel gp, Graphics2D g2d) {
        g2d.drawImage(img, x, y, gp);
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

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }
}
