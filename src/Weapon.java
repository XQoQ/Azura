import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Weapon extends Item{
    private int x, y;
    private int ID;
    private Image img;

    public Weapon(int x, int y, int ID) throws IOException {
        super(x, y, ImageIO.read(new File("image/Weapon/weapon" + ID + ".png")));
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void updateImg() throws IOException {
        this.img = ImageIO.read(new File("image/Weapon/weapon" + ID + ".png"));
    }
}
