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

        //creates the main character
        Ally = new Ally();

        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Azura");
        this.setIconImage(new ImageIcon("image/MainCharacter/Ally.png").getImage());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);//screen pop up in the middle
        this.setResizable(false);
        this.setVisible(true);

        Ally.start();
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
        Graphics2D g2d = (Graphics2D) g;
        g.drawImage(new ImageIcon("image/white_bg.png").getImage(), 0, 0, this);
        g2d.drawImage(Ally.getImg(), Ally.getX(), Ally.getY(), this);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();

        if (key == 'w') {
            Ally.setRunning(true);
            Ally.setDirection("up");
            Ally.checkDirection();
            Ally.setY(Ally.getY() - Ally.getSpeed());
        }
        if (key == 'd') {
            Ally.setRunning(true);
            Ally.setDirection("right");
            Ally.checkDirection();
            Ally.setX(Ally.getX() + Ally.getSpeed());
        }
        if (key == 's') {
            Ally.setRunning(true);
            Ally.setDirection("down");
            Ally.checkDirection();
            Ally.setY(Ally.getY() + Ally.getSpeed());
        }
        if (key == 'a') {
            Ally.setRunning(true);
            Ally.setDirection("left");
            Ally.checkDirection();
            Ally.setX(Ally.getX() - Ally.getSpeed());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char key = e.getKeyChar();

        if (key == 'w') {
            Ally.setDirection("up");
        }
        if (key == 'd') {
            Ally.setDirection("right");
        }
        if (key == 's') {
            Ally.setDirection("down");
        }
        if (key == 'a') {
            Ally.setDirection("left");
        }

        Ally.setRunning(false);
        Ally.checkDirection();
    }
}
