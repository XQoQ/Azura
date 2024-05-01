import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class GameFrame extends JFrame implements KeyListener {
    static final int WIDTH = 1600;
    static final int HEIGHT = 960;
    private long timePassed = 500;
    private long startTime;
    private boolean up = false;
    private boolean right = false;
    private boolean down = false;
    private boolean left = false;
    private Ally Ally;
    private ArrayList<Mob> mobs;
    private ArrayList<Bullet> bullets;
    private GameMap gameMap = new GameMap(this);

    public GameFrame() {
        this.addKeyListener(this);

        //creates the main character
        Ally = new Ally();

        this.mobs = new ArrayList<Mob>();
        this.bullets = new ArrayList<Bullet>();

        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Azura");
        this.setIconImage(new ImageIcon("image/MainCharacter/Ally.png").getImage());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);//screen pop up in the middle
        this.setResizable(false);
        this.setVisible(true);

        Runnable r = () -> {
            while (true) {
//                if ((int)(Math.random() * 100) + 1 > 50) {
//                    int x =  (int) (Math.random() * 1500) + 50;
//                    int y =  (int) (Math.random() * 900) + 50;
//                    mobs.add(new Mob(x, y, 1, 2));
//                }
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
        for (int i = 0; i < mobs.size(); i ++) {
            g2d.drawImage(mobs.get(i).getImg(), mobs.get(i).getX(), mobs.get(i).getY(), this);
        }

        for (int i = 0; i < bullets.size(); i++) {
            if (!bullets.get(i).isDead()) {
                bullets.get(i).renderBullet(this, g2d);
                if (bullets.get(i).getX() > 1520 || bullets.get(i).getX() < 0) {
                    bullets.remove(i).setDead(true);
                } else if (bullets.get(i).getY() < 20 || bullets.get(i).getY() > 880) {
                    bullets.remove(i).setDead(true);
                }
            }
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
            if (timePassed > 250) {
                startTime = System.currentTimeMillis();
                timePassed = 0;
                Bullet bullet = new Bullet(0, Ally.getDirection());
                switch (this.Ally.getDirection()) {
                    case ("up") -> {
                        bullet.setX(this.Ally.getX() + 15);
                        bullet.setY(this.Ally.getY() - 30);
                    }
                    case ("right") -> {
                        bullet.setX(this.Ally.getX() + 10);
                        bullet.setY(this.Ally.getY() + 20);
                    }
                    case ("down") -> {
                        bullet.setX(this.Ally.getX() + 15);
                        bullet.setY(this.Ally.getY() + 30);
                    }
                    case ("left") -> {
                        bullet.setX(this.Ally.getX() - 10);
                        bullet.setY(this.Ally.getY() + 20);
                    }
                }
                bullets.add(bullet);
            } else {
                timePassed = System.currentTimeMillis() - startTime;
            }
        } else {
            startTime = 500;
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
