import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
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
                kh.setTimePassed(System.currentTimeMillis() - kh.getStartTime());
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
        gw.checkRadius();
        try {
            gw.drawBullet(this, g2d);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        gw.drawItem(this, g2d);
        gw.drawHitEffect(this, g2d);
        try {
            this.drawUI(g2d);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public GameWorld getGw() {
        return gw;
    }

    public void drawUI(Graphics2D g2d) throws IOException {

        Image UIBlackTopLeft = (new ImageIcon("image/UI/UI_black_tl.png")).getImage();
        Image UIBlackBotLeft = (new ImageIcon("image/UI/UI_black_bl.png")).getImage();
        Image UIBlackBotRight = (new ImageIcon("image/UI/UI_black_br.png")).getImage();
        Image ItemBackImg = (new ImageIcon("image/UI/item_back.png")).getImage();
        Image CoinBackImg = (new ImageIcon("image/UI/coin_back.png")).getImage();
        Image HPBar = (new ImageIcon("image/UI/HPT.png")).getImage();
        Image UITop = (new ImageIcon("image/UI/UI_top.png")).getImage();
        Image coinImage = new Coin(-100, -100, 99999).getImg();
        Image weaponIcon = gw.getAlly().getWp().getImg();

        //UI Font
        Font HPfont = new Font("Courier New", Font.PLAIN, 24);
        Font coinFont = new Font("Courier New", Font.PLAIN, 18);

        //draw UI images
        g2d.drawImage(UIBlackTopLeft, 0, 0, this);
        g2d.drawImage(UIBlackBotLeft, 0, 645, this);
        g2d.drawImage(UIBlackBotRight, 1380, 805, this);
        g2d.drawImage(UITop, 0, 0, this);
        g2d.drawImage(CoinBackImg, 1440, 870, this);
        g2d.drawImage(ItemBackImg, 0, 830, this);
        g2d.drawImage(weaponIcon.getScaledInstance(50, 50, Image.SCALE_DEFAULT), 23, 850, this);
        g2d.drawImage(coinImage.getScaledInstance(38, 38, Image.SCALE_DEFAULT), 1443, 870, this);
        g2d.drawImage(HPBar, 32, 13, (int) ((double) 300 * gw.getAlly().getHp() / gw.getAlly().getMaxHp()), 30, null);

        //draw UI text
        g2d.setColor(Color.white);
        g2d.setFont(HPfont);
        g2d.drawString("" + gw.getAlly().getHp() + "/" + gw.getAlly().getMaxHp(), 43, 37);

        g2d.setFont(coinFont);
        g2d.drawString(gw.getAlly().getCoinAmount() + "", 1485, 896);

    }
}
