import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class GameWorld {
    private Ally Ally;
    private ArrayList<Mob> mobs;
    private ArrayList<Bullet> bullets;
    private ArrayList<Effect> effects;
    private ArrayList<Item> items;
    private Background bg;
    private long timePassed = 1500;
    private long startTime;
    private Image hitEffect = (new ImageIcon("image/Mob/mob0_2.png")).getImage();

    public GameWorld() throws IOException {
        this.Ally = new Ally();
        this.mobs = new ArrayList<Mob>();
        this.bullets = new ArrayList<Bullet>();
        this.effects = new ArrayList<Effect>();
        this.items = new ArrayList<Item>();
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

    public void drawItem(GamePanel gp, Graphics2D g2d) {
        for (int i = 0; i < items.size(); i++) {
            items.get(i).renderItem(gp, g2d);
        }
    }
    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void drawBullet(GamePanel gp, Graphics2D g2d) throws IOException {
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).renderBullet(gp, g2d);
            if (bullets.get(i).getX() > 1520 || bullets.get(i).getX() < 0) {
                bullets.remove(i);
                i--;
            } else if (bullets.get(i).getY() < 20 || bullets.get(i).getY() > 880) {
                bullets.remove(i);
                i--;
            }
        }
    }

    public void detectCollision(GamePanel gp, Graphics2D g2d) throws IOException {
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
                    if (bullets.size() != 0 && bullets.get(i).getRect().intersects(mobs.get(j).getHitBox())) {
                        effects.add(new Effect(bullets.get(i).getID(), bullets.get(i).getX(), bullets.get(i).getY(), bullets.get(i).getDirection(), System.currentTimeMillis()));
                        mobs.get(j).adjustHp(bullets.get(i).getAtk());
                        bullets.remove(i);
                        i--;
                        if (mobs.get(j).getHp() <= 0) {
                            if ((int)(Math.random() * 10) + 1 < 20) {
                                Item item = new Weapon(mobs.get(j).getX(), mobs.get(j).getY(), 1);
                                items.add(item);
                            }
                            mobs.remove(j);
                            j--;
                        }
                    }
                }
            }
        }

        if (items.size() != 0) {
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i) instanceof Weapon) {
                    if (Ally.getRec().intersects(items.get(i).getRect())) {
                        drawPickUpText(gp, g2d, items.get(i));
                    }
                }
            }
        }
    }

    public boolean isAllyCollidingWithAnItem() {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) instanceof Weapon && Ally.getRec().intersects(items.get(i).getRect())) {
                return true;
            }
        }

        return false;
    }

    public void swapWeapon() {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) instanceof Weapon && Ally.getRec().intersects(items.get(i).getRect())) {
                Weapon tem = Ally.getWp();
                tem.setX(items.get(i).getX());
                tem.setY(items.get(i).getY());
                Ally.setWp((Weapon) items.get(i));
                items.set(i, tem);
            }
        }
    }

    private void drawPickUpText(GamePanel gp, Graphics2D g2d, Item i) {
        g2d.setColor(Color.WHITE);
        g2d.drawString("\"E\" Pick Up", i.getX() - (int) i.getRect().getWidth() / 2, i.getY());
    }

    public void drawHitEffect(GamePanel gp, Graphics2D g2d) {
        if (effects.size() != 0) {
            for (int i = 0; i < effects.size(); i ++) {
                effects.get(i).setTimePassed(System.currentTimeMillis() - effects.get(i).getStartTime());
                if (System.currentTimeMillis() + effects.get(i).getTimePassed() - effects.get(i).getStartTime() < effects.get(i).getLifespan()) {
                    effects.get(i).renderEffect(gp, g2d);
                } else {
                    effects.remove(i);
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
