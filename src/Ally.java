import javax.swing.*;
import java.awt.*;

public class Ally {
    private Image img = (new ImageIcon("image/MainCharacter/Ally_d.gif")).getImage();
    private int speed;
    private String direction;

    public Ally() {
        this.speed = 4;
        this.direction = "down";
    }

    public Image getImg() {return img;}
}
