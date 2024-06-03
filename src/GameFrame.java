import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

public class GameFrame extends JFrame {
    static final int WIDTH = 1600;
    static final int HEIGHT = 960;

    public GameFrame() throws IOException {
        GamePanel gp = new GamePanel();

        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Azura");
        this.setIconImage(new ImageIcon("image/MainCharacter/Ally.png").getImage());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);//screen pop up in the middle
        this.setResizable(false);
        this.setVisible(true);

        this.add(gp);
    }



}
