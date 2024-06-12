import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Recovery extends Item{
    private int numInBackpack;
    private int recoveryAmount;
    private final String itemName;
    public Recovery(int x, int y, String name) throws IOException {
        super(x, y, ImageIO.read(new File("image/Item/prop/" + name + ".png")));
        this.numInBackpack = 3;
        this.itemName = name;
        switch (itemName) {
            case "apple" ->
                this.recoveryAmount = 15;
        }
    }

    public int getRecoveryAmount() {
        return recoveryAmount;
    }

    public int getNumInBackpack() {
        return numInBackpack;
    }

    public void increaseNumInBackpack() {
        this.numInBackpack ++;
    }

    public void decreaseNumInBackpack() {
        this.numInBackpack --;
    }

    public String getItemName() {
        return itemName;
    }
}
