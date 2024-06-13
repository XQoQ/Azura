import java.awt.event.KeyEvent;
public class KeyHandler implements java.awt.event.KeyListener {
    private GamePanel gp;
    private long timePassed = 500;
    private long startTime;

    public long getTimePassed() {
        return timePassed;
    }

    public void setTimePassed(long timePassed) {
        this.timePassed = timePassed;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    public void keyTyped(KeyEvent e) {
        char key = e.getKeyChar();

        if (key == 'j') {
            if (timePassed > 450) {
                startTime = System.currentTimeMillis();
                timePassed = 0;
                Bullet bullet = new Bullet(gp.getGw().getAlly().getWp().getID(), gp.getGw().getAlly().getDirection());
                switch (gp.getGw().getAlly().getDirection()) {
                    case ("up") -> {
                        bullet.setX(gp.getGw().getAlly().getX() + gp.getGw().getAlly().getImg().getWidth(null) / 2 - bullet.getImg().getWidth(null) / 2);
                        bullet.setY(gp.getGw().getAlly().getY() + gp.getGw().getAlly().getImg().getHeight(null) / 2 - bullet.getImg().getHeight(null));
                    }
                    case ("right") -> {
                        bullet.setX(gp.getGw().getAlly().getX() + gp.getGw().getAlly().getImg().getWidth(null) / 2);
                        bullet.setY(gp.getGw().getAlly().getY() + gp.getGw().getAlly().getImg().getHeight(null) / 2 - bullet.getImg().getHeight(null) / 2);
                    }
                    case ("down") -> {
                        bullet.setX(gp.getGw().getAlly().getX() + gp.getGw().getAlly().getImg().getWidth(null) / 2 - bullet.getImg().getWidth(null) / 2);
                        bullet.setY(gp.getGw().getAlly().getY() + gp.getGw().getAlly().getImg().getHeight(null) / 2);
                    }
                    case ("left") -> {
                        bullet.setX(gp.getGw().getAlly().getX() - bullet.getImg().getWidth(null));
                        bullet.setY(gp.getGw().getAlly().getY() + gp.getGw().getAlly().getImg().getHeight(null) / 2 - bullet.getImg().getHeight(null) / 2);
                    }
                }
                gp.getGw().getBullets().add(bullet);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();

        if (key == 'w') {
            if (gp.getGw().getAlly().getDirection().equals("up") && gp.getGw().isAllyCollidingWithMob()) {
                gp.getGw().getAlly().setUp(false);
            } else {
                gp.getGw().getAlly().setUp(true);
            }
        }
        if (key == 'd') {
            if (gp.getGw().getAlly().getDirection().equals("right") && gp.getGw().isAllyCollidingWithMob()) {
                gp.getGw().getAlly().setRight(false);
            } else {
                gp.getGw().getAlly().setRight(true);
            }
        }
        if (key == 's') {
            if (gp.getGw().getAlly().getDirection().equals("down") && gp.getGw().isAllyCollidingWithMob()) {
                gp.getGw().getAlly().setDown(false);
            } else {
                gp.getGw().getAlly().setDown(true);
            }
        }
        if (key == 'a') {
            if (gp.getGw().getAlly().getDirection().equals("left") && gp.getGw().isAllyCollidingWithMob()) {
                gp.getGw().getAlly().setLeft(false);
            } else {
                gp.getGw().getAlly().setLeft(true);
            }
        }
        if (key == 'j') {
            if (timePassed > 450) {
                startTime = System.currentTimeMillis();
                timePassed = 0;
                Bullet bullet = new Bullet(gp.getGw().getAlly().getWp().getID(), gp.getGw().getAlly().getDirection());
                switch (gp.getGw().getAlly().getDirection()) {
                    case ("up") -> {
                        bullet.setX(gp.getGw().getAlly().getX() + gp.getGw().getAlly().getImg().getWidth(null) / 2 - bullet.getImg().getWidth(null) / 2);
                        bullet.setY(gp.getGw().getAlly().getY() + gp.getGw().getAlly().getImg().getHeight(null) / 2 - bullet.getImg().getHeight(null));
                    }
                    case ("right") -> {
                        bullet.setX(gp.getGw().getAlly().getX() + gp.getGw().getAlly().getImg().getWidth(null) / 2);
                        bullet.setY(gp.getGw().getAlly().getY() + gp.getGw().getAlly().getImg().getHeight(null) / 2 - bullet.getImg().getHeight(null) / 2);
                    }
                    case ("down") -> {
                        bullet.setX(gp.getGw().getAlly().getX() + gp.getGw().getAlly().getImg().getWidth(null) / 2 - bullet.getImg().getWidth(null) / 2);
                        bullet.setY(gp.getGw().getAlly().getY() + gp.getGw().getAlly().getImg().getHeight(null) / 2);
                    }
                    case ("left") -> {
                        bullet.setX(gp.getGw().getAlly().getX() - bullet.getImg().getWidth(null));
                        bullet.setY(gp.getGw().getAlly().getY() + gp.getGw().getAlly().getImg().getHeight(null) / 2 - bullet.getImg().getHeight(null) / 2);
                    }
                }
                gp.getGw().getBullets().add(bullet);
            }
        }

        if (gp.getGw().isAllyCollidingWithAnItem() && key == 'f') {
            if (gp.getGw().getItemAllyIsColliding() instanceof Weapon) {
                gp.getGw().swapWeapon();
            }
            if (gp.getGw().getItemAllyIsColliding() instanceof Recovery) {
                for (int i = 0; i < gp.getGw().getBackpack().size(); i++) {
                    if (gp.getGw().getBackpack().get(i).getItemName().equals(((Recovery) gp.getGw().getItemAllyIsColliding()).getItemName()) && gp.getGw().getBackpack().get(i).getNumInBackpack() < 10) {
                        gp.getGw().getBackpack().get(i).increaseNumInBackpack();
                        gp.getGw().removeItemCollected();
                    }
                }
            }
        }

        if (gp.getGw().getAlly().getHp() < 100 && gp.getGw().getCurrentRecoveryItem().getNumInBackpack() > 0 && key == 'e') {
            gp.getGw().getAlly().adjustHp(gp.getGw().getCurrentRecoveryItem().getRecoveryAmount());
            gp.getGw().getCurrentRecoveryItem().decreaseNumInBackpack();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char key = e.getKeyChar();

        if (key == 'w') {
            gp.getGw().getAlly().setUp(false);
            gp.getGw().getAlly().setRunning(false);
        }
        if (key == 'd') {
            gp.getGw().getAlly().setRight(false);
            gp.getGw().getAlly().setRunning(false);
        }
        if (key == 's') {
            gp.getGw().getAlly().setDown(false);
            gp.getGw().getAlly().setRunning(false);
        }
        if (key == 'a') {
            gp.getGw().getAlly().setLeft(false);
            gp.getGw().getAlly().setRunning(false);
        }

    }
}
