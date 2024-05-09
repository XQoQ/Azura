import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GameFrame extends JFrame {
    static final int WIDTH = 1600;
    static final int HEIGHT = 960;
    private boolean up = false;
    private boolean right = false;
    private boolean down = false;
    private boolean left = false;
    private Ally Ally;
    private ArrayList<Mob> mobs;
    private ArrayList<Bullet> bullets;
    private GameMap gameMap = new GameMap(this);
    private KeyListener kl = new KeyHandler(this);

    public GameFrame() {
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

        this.addKeyListener(kl);

        Runnable r = () -> {
            while (true) {
                if ((int)(Math.random() * 100) + 1 == 1) {
                    int x =  (int) (Math.random() * 1500) + 50;
                    int y =  (int) (Math.random() * 900) + 50;
                    mobs.add(new Mob(x, y, 0, 2));
                }
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

        gameMap.getBg().draw(0, g);
        Ally.updateAlly();
        Ally.renderAlly(this, g2d);
        for (int i = 0; i < mobs.size(); i ++) {
            if (mobs.get(i).getHp() > 0) {
                mobs.get(i).renderMob(this, g2d);
            }
        }

        for (int i = 0; i < bullets.size(); i++) {
            for (Mob m: mobs) {
                if (bullets.get(i).getRect().intersects(m.getHitBox())) {
                    bullets.get(i).setDead(true);
                    m.adjustHp(bullets.get(i).getAtk());
                }
            }
            if (!bullets.get(i).isDead()) {
                bullets.get(i).renderBullet(this, g2d);
                if (bullets.get(i).getX() > 1520 || bullets.get(i).getX() < 0) {
                    bullets.remove(i).setDead(true);
                } else if (bullets.get(i).getY() < 20 || bullets.get(i).getY() > 880) {
                    bullets.remove(i).setDead(true);
                }
            } else {
                bullets.remove(i);
                i--;
            }
        }
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

    public Ally getAlly() {
        return Ally;
    }

    public void setAlly(Ally ally) {
        Ally = ally;
    }

    public ArrayList<Mob> getMobs() {
        return mobs;
    }

    public void setMobs(ArrayList<Mob> mobs) {
        this.mobs = mobs;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }
}
