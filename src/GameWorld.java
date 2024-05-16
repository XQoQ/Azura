import java.awt.*;
import java.util.ArrayList;

public class GameWorld {
    private Ally Ally;
    private ArrayList<Mob> mobs;
    private final int MAX_MOB_NUM = 5;
    private ArrayList<Bullet> bullets;
    private Background bg;

    public GameWorld() {
        this.Ally = new Ally();
        this.mobs = new ArrayList<Mob>();
        this.bullets = new ArrayList<Bullet>();
        this.bg = new Background();
    }

    public Ally getAlly() {
        return Ally;
    }

    public void drawAlly(GamePanel gp, Graphics2D g2d) {
        this.Ally.updateAlly();
        this.Ally.renderAlly(gp, g2d);
    }


    public ArrayList<Mob> getMobs() {
        return mobs;
    }

    public void drawMob(GamePanel gp, Graphics2D g2d) {
        for (int i = 0; i < mobs.size(); i ++) {
            if (mobs.get(i).getHp() > 0) {
                mobs.get(i).renderMob(gp, g2d);
            }
        }
    }

    public void generateMob(int ID) {
        if ((int)(Math.random() * 10) + 1 == 1 && mobs.size() < MAX_MOB_NUM) {
            int x =  (int) (Math.random() * 1500) + 50;
            int y =  (int) (Math.random() * 900) + 50;
            mobs.add(new Mob(x, y, ID, 2));
        }
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void drawBullet(GamePanel gp, Graphics2D g2d) {
        for (int i = 0; i < bullets.size(); i++) {
            if (!bullets.get(i).isDead()) {
                bullets.get(i).renderBullet(gp, g2d);
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

    public void detectCollision() {
        if (mobs.size() != 0) {
            for (Mob m: mobs) {
                if (Ally.getRec().intersects(m.getHitBox())) {
                    Ally.adjustHp(m.getAttack());
                }
            }
        }

        if (bullets.size() != 0) {
            for (int i = 0; i < bullets.size(); i++) {
                for (Mob m : mobs) {
                    if (bullets.get(i).getRect().intersects(m.getHitBox())) {
                        bullets.get(i).setDead(true);
                        m.adjustHp(bullets.get(i).getAtk());
                    }
                }
            }
        }
    }

    public Background getBg() {
        return bg;
    }

    public void drawBackground(Graphics g, int ID) {
        this.bg.draw(ID, g);
    }

}
