import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    private Ally Ally;
    private ArrayList<Mob> mobs;
    private ArrayList<Bullet> bullets;
    public GamePanel() {
        this.setFocusable(true);
        this.Ally = new Ally();
        this.mobs = new ArrayList<Mob>();
        this.bullets = new ArrayList<Bullet>();

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
        Thread t = new Thread(r);
        t.start();
    }

    public void paintComponent(Graphics g) {

    }
}
