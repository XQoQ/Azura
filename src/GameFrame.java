import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

public class GameFrame extends JFrame {
    static final int WIDTH = 1600;
    static final int HEIGHT = 960;
    private boolean up = false;
    private boolean right = false;
    private boolean down = false;
    private boolean left = false;

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

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

}
