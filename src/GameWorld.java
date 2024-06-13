import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameWorld {
    private final Ally Ally;
    private final ArrayList<Mob> mobs;
    private final ArrayList<Bullet> bullets;
    private final ArrayList<Effect> effects;
    private final ArrayList<Item> items;
    private final ArrayList<Recovery> backpack;
    private final Image RED_FLASH = ImageIO.read(new File("image/UI/HPF.png"));
    private Recovery currentRecoveryItem;
    private Background bg;
    private long timePassed = 1500;
    private long startTime;

    public GameWorld() throws IOException {
        this.Ally = new Ally();
        this.mobs = new ArrayList<Mob>();
        this.bullets = new ArrayList<Bullet>();
        this.effects = new ArrayList<Effect>();
        this.items = new ArrayList<Item>();
        this.backpack = new ArrayList<Recovery>();
        this.backpack.add(new Recovery(40, 860, "apple"));
        this.currentRecoveryItem = backpack.get(0);
        this.bg = new Background();
    }

    public Ally getAlly() {
        return Ally;
    }

    public void drawAlly(GamePanel gp, Graphics2D g2d) throws IOException {
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
        if ((int)(Math.random() * 100) + 1 == 1 && mobs.size() < MAX_MOB_NUM) {
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

    public void drawBackpack(GamePanel gp, Graphics2D g2d) {
        sortBackpack();
        int xIncrement = 40;
        for (int i = 1; i < backpack.size() + 1; i++) {
            int x = 87 + backpack.get(i - 1).getImg().getWidth(null) * i + xIncrement * (i - 1);
            int y = 883;
            g2d.drawImage(backpack.get(i - 1).getImg(), x , y, gp);
            g2d.drawString("" + backpack.get(i - 1).getNumInBackpack(), x + backpack.get(i - 1).getImg().getWidth(null), y + backpack.get(i - 1).getImg().getHeight(null));
        }
    }

    private void sortBackpack() {
        while (backpack.get(0) != currentRecoveryItem) {
            Recovery temp = backpack.get(0);

            for (int i = 0; i < backpack.size() - 1; i++) {
                backpack.set(i, backpack.get(i + 1));
            }

            backpack.set(backpack.size() - 1, temp);
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

    private void drawRedFlash(GamePanel gp, Graphics2D g2d) {
        g2d.drawImage(RED_FLASH.getScaledInstance(1600, 960, Image.SCALE_DEFAULT), 0, 0, gp);
    }

    public void detectCollision(GamePanel gp, Graphics2D g2d) throws IOException {
        if (!mobs.isEmpty()) {
            for (Mob m: mobs) {
                if (Ally.getRec().intersects(m.getHitBox())) {
                    if (timePassed > 500) {
                        startTime = System.currentTimeMillis();
                        timePassed = 0;
                        Ally.adjustHp(-m.getAttack());
                    } else {
                        drawRedFlash(gp, g2d);
                        timePassed = System.currentTimeMillis() - startTime;
                    }
                }
            }
        }

        if (!bullets.isEmpty()) {
            for (int i = 0; i < bullets.size(); i++) {
                for (int j = 0; j < mobs.size(); j++) {
                    if (!bullets.isEmpty() && bullets.get(i).getRect().intersects(mobs.get(j).getHitBox())) {
                        effects.add(new Effect(bullets.get(i).getID(), bullets.get(i).getX(), bullets.get(i).getY(), bullets.get(i).getDirection(), System.currentTimeMillis()));
                        mobs.get(j).adjustHp(bullets.get(i).getAtk());
                        if (bullets.get(i).getDisappearOnContact() == 1) {
                            bullets.remove(i);
                            i--;
                        }
                        if (i == -1) {
                            i = 0;
                        }
                        if (mobs.get(j).getHp() <= 0) {
                            int spawnChance = (int)(Math.random() * 100) + 1;
                            if (spawnChance <= 20) {
                                Item item = new Weapon(mobs.get(j).getX(), mobs.get(j).getY(), 1);
                                items.add(item);
                                clearWeapons();
                            } else if (spawnChance > 30 && spawnChance <= 60) {
                                int randomValue = (int)(Math.random() * 41) + 10;
                                Item item = new Coin(mobs.get(j).getX(), mobs.get(j).getY(), randomValue);
                                items.add(item);
                            } else if (spawnChance > 60 && spawnChance <= 90) {
                                Item apple = new Recovery(mobs.get(j).getX(), mobs.get(j).getY(), "apple");
                                items.add(apple);
                            }
                            mobs.remove(j);
                            j--;
                        }
                    }
                }
            }
        }

        if (!items.isEmpty()) {
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i) instanceof Weapon || items.get(i) instanceof Recovery) {
                    if (Ally.getRec().intersects(items.get(i).getRect())) {
                        drawPickUpText(g2d, items.get(i));
                    }
                } else if (items.get(i) instanceof Coin) {
                    if (Ally.getRec().intersects(items.get(i).getRect())) {
                        Ally.setCoinAmount(((Coin) items.get(i)).getValue());
                        items.remove(i);
                        i--;
                    }
                }
            }
        }
    }

    private void clearWeapons() {
        int numOfWeapon = 0;
        for (Item i: items) {
            if (i instanceof Weapon) {
                numOfWeapon++;
            }
        }

        if (numOfWeapon > 5) {
            int difference = numOfWeapon - 5;
            while (difference > 0) {
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i) instanceof Weapon) {
                        items.remove(i);
                        difference--;
                        i = items.size();
                    }
                }
            }
        }
    }

    public boolean isAllyCollidingWithAnItem() {
        for (Item item : items) {
            if (Ally.getRec().intersects(item.getRect())) {
                return true;
            }
        }

        return false;
    }

    public Item getItemAllyIsColliding() {
        for (int i = 0; i < items.size(); i++) {
            if (Ally.getRec().intersects(items.get(i).getRect())) {
                return items.get(i);
            }
        }

        return null;
    }

    public void removeItemCollected() {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getRect().intersects(Ally.getRec())) {
                items.remove(i);
                i = items.size();
            }
        }
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

    private void drawPickUpText(Graphics2D g2d, Item i) {
        Font font = new Font("Courier New", Font.PLAIN, 18);
        g2d.setFont(font);
        g2d.setColor(Color.WHITE);
        Rectangle2D f = font.getStringBounds("\"E\" Pick Up", g2d.getFontRenderContext());

        g2d.drawString("\"F\" Pick Up", i.getX() - (int) f.getWidth() / 2, i.getY() - 5);
    }

    public void drawHitEffect(GamePanel gp, Graphics2D g2d) {
        if (!effects.isEmpty()) {
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

    public void checkRadius() {
        for (int i = 0; i < mobs.size(); i++) {
            int mobX = mobs.get(i).getX() + mobs.get(i).getImg().getWidth(null) / 2;
            int mobY = mobs.get(i).getY() + mobs.get(i).getImg().getHeight(null) / 2;
            int AllyX = Ally.getX() + Ally.getImg().getWidth(null) / 2;
            int AllyY = Ally.getY() + Ally.getImg().getHeight(null) / 2;
            int dX = AllyX - mobX;
            int dY = AllyY - mobY;
            double hypotenuse = Math.sqrt(Math.pow(AllyX - mobX , 2) + Math.pow(AllyY - mobY, 2));
            double sinFactor = dY / hypotenuse;
            double cosFactor = dX / hypotenuse;
            double verticalSpeed = mobs.get(i).getSpeed() * sinFactor;
            double horizontalSpeed = mobs.get(i).getSpeed() * cosFactor;
            if (hypotenuse <= 200) {
                mobs.get(i).adjustX((int) horizontalSpeed);
                mobs.get(i).adjustY((int) verticalSpeed);
            }
        }
    }

    public boolean isGameOver() {
        return Ally.getHp() <= 0;
    }

    public Recovery getCurrentRecoveryItem() {
        return currentRecoveryItem;
    }

    public ArrayList<Recovery> getBackpack() {
        return backpack;
    }

    public void setCurrentRecoveryItem(Recovery currentRecoveryItem) {
        this.currentRecoveryItem = currentRecoveryItem;
    }

}
