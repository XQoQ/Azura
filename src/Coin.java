import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Coin extends Item{
    private int value;

    public Coin(int x, int y, int value) throws IOException {
        super(x, y, ImageIO.read(new File("image/Item/prop/coin.png")));
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
