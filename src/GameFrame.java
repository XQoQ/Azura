import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameFrame extends JFrame implements KeyListener {
    static final int WIDTH = 1280;
    static final int HEIGHT = 720;
    private boolean up = false;
    private boolean right = false;
    private boolean down = false;
    private boolean left = false;
    private Ally Ally;
    private Bullet bullet;
    private boolean drawBullet;
    private GameMap gameMap = new GameMap(this);

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

        if (up) {
            Ally.setRunning(true);
            Ally.setDirection("up");
            Ally.checkDirection();
            Ally.setY(Ally.getY() - Ally.getSpeed());
        }
        if (right) {
            Ally.setRunning(true);
            Ally.setDirection("right");
            Ally.checkDirection();
            Ally.setX(Ally.getX() + Ally.getSpeed());
        }
        if (down) {
            Ally.setRunning(true);
            Ally.setDirection("down");
            Ally.checkDirection();
            Ally.setY(Ally.getY() + Ally.getSpeed());
        }
        if (left) {
            Ally.setRunning(true);
            Ally.setDirection("left");
            Ally.checkDirection();
            Ally.setX(Ally.getX() - Ally.getSpeed());
        }

        gameMap.getBg().draw(0, g);
        g2d.drawImage(Ally.getImg(), Ally.getX(), Ally.getY(), this);
        if (drawBullet) {
            g.drawImage(bullet.getImg(), Ally.getX() + 10, Ally.getY() + 10, this);
            bullet.move();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();

        if (key == 'w') {
            this.up = true;
        }
        if (key == 'd') {
            this.right = true;
        }
        if (key == 's') {
            this.down = true;
        }
        if (key == 'a') {
            this.left = true;
        }
        Ally.checkDirection();

        if (key == 'j') {
            drawBullet = true;
            this.bullet = new Bullet(0, Ally);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char key = e.getKeyChar();

        if (key == 'w') {
            this.up = false;
            Ally.setRunning(false);
        }
        if (key == 'd') {
            this.right = false;
            Ally.setRunning(false);
        }
        if (key == 's') {
            this.down = false;
            Ally.setRunning(false);
        }
        if (key == 'a') {
            this.left = false;
            Ally.setRunning(false);
        }
        Ally.checkDirection();
    }
}
