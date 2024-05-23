import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameWorld {
    private Ally Ally;
    private ArrayList<Mob> mobs;
    private ArrayList<Bullet> bullets;
    private ArrayList<Effect> effects;
    private Background bg;
    private long timePassed = 1500;
    private long startTime;
    private ArrayList<Long> effectStartTime;
    private Image hitEffect = (new ImageIcon("image/Mob/mob0_2.png")).getImage();

    public GameWorld() {
        this.Ally = new Ally();
        this.mobs = new ArrayList<Mob>();
        this.bullets = new ArrayList<Bullet>();
        this.effects = new ArrayList<Effect>();
        this.effectStartTime = new ArrayList<Long>();
        this.bg = new Background();
    }

    public Ally getAlly() {
        return Ally;
    }

    public void drawAlly(GamePanel gp, Graphics2D g2d) {
        this.Ally.updateAlly();
        this.Ally.renderAlly(gp, g2d);
    }

    public boolean isAllyCollidingWithMob() {
        for (Mob m: mobs) {
            if (Ally.getRec().intersects(m.getHitBox())) {
                return true;
            }
        }

        return false;
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
        final int MAX_MOB_NUM = 5;
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

    public void detectCollision(GamePanel gp, Graphics2D g2d) {
        if (mobs.size() != 0) {
            for (Mob m: mobs) {
                if (Ally.getRec().intersects(m.getHitBox())) {
                    if (timePassed > 1000) {
                        startTime = System.currentTimeMillis();
                        timePassed = 0;
                        Ally.adjustHp(m.getAttack());
                    } else {
                        timePassed = System.currentTimeMillis() - startTime;
                    }
                }
            }
        }

        if (bullets.size() != 0) {
            for (int i = 0; i < bullets.size(); i++) {
                for (int j = 0; j < mobs.size(); j++) {
                    if (bullets.get(i).getRect().intersects(mobs.get(j).getHitBox())) {
                        effects.add(new Effect(bullets.get(i).getID(), bullets.get(i).getX(), bullets.get(i).getY()));
                        bullets.get(i).setDead(true);
                        mobs.get(j).adjustHp(bullets.get(i).getAtk());
                        if (mobs.get(j).getHp() <= 0) {
                            mobs.remove(j);
                            j--;
                        }
                    }
                }
            }
        }
    }

    public void drawHitEffect(GamePanel gp, Graphics2D g2d) {
        if (effects.size() != 0) {
            for (int i = 0; i < effects.size(); i++) {
                effectStartTime.add(0L);
            }

            for (int i = 0; i < effects.size(); i ++) {
                if (effectStartTime.get(i) == 0) {
                    effectStartTime.set(i, System.currentTimeMillis());
                }
                effects.get(i).setTimePassed(0);

                if (effects.get(i).getTimePassed() - effectStartTime.get(i) < effects.get(i).getLifespan()) {
                    effects.get(i).renderEffect(gp, g2d);
                    effects.get(i).setTimePassed(System.currentTimeMillis() - effectStartTime.get(i));
                } else {
                    effects.remove(i);
                    effectStartTime.remove(i);
                    i--;
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
