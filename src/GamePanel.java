import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    private GameWorld gw;
    private KeyHandler kh;

    public GamePanel() {
        this.gw = new GameWorld();
        this.kh = new KeyHandler(this);

        this.setFocusable(true);
        this.addKeyListener(kh);

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
        Thread t = new Thread(r);
        t.start();
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        gw.detectCollision();
        gw.generateMob(0);

        gw.drawBackground(g, 0);
        gw.drawAlly(this, g2d);
        gw.drawMob(this, g2d);
        gw.drawBullet(this, g2d);
    }

    public GameWorld getGw() {
        return gw;
    }
}
