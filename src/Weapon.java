import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Weapon extends Item{
    private int ID;

    public Weapon(int x, int y, int ID) throws IOException {
        super(x, y, ImageIO.read(new File("image/Item/Weapon/weapon" + ID + ".png")));
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

}
