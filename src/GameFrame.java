import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameFrame extends JFrame implements KeyListener {
    static final int WIDTH = 1280;
    static final int HEIGHT = 720;
    private Ally Ally;

    public GameFrame() {
        this.addKeyListener(this);

        Ally = new Ally();

        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Azura");
        this.setIconImage(new ImageIcon("image/Item/arm/arm5.png").getImage());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);//screen pop up location
        this.setResizable(false);
        this.setVisible(true);

        Runnable r = () -> {
            while (true) {
                this.repaint();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread gameLine = new Thread(r);
        gameLine.start();
    }

    public void paint(Graphics g) {
        g.drawImage(Ally.getImg(), 100, 100, this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
