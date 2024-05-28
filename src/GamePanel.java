import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    private GameWorld gw;
    private KeyHandler kh;

    public GamePanel() throws IOException {
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

        gw.generateMob(0);
        gw.drawBackground(g, 0);
        try {
            gw.detectCollision(this, g2d);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        gw.drawAlly(this, g2d);
        gw.drawMob(this, g2d);
        gw.drawBullet(this, g2d);
        gw.drawItem(this, g2d);
        gw.drawHitEffect(this, g2d);
    }

    public GameWorld getGw() {
        return gw;
    }
}
