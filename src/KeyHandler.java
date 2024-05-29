import java.awt.event.KeyEvent;
public class KeyHandler implements java.awt.event.KeyListener {
    private GamePanel gp;
    private long timePassed = 500;
    private long startTime;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    public void keyTyped(KeyEvent e) {

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
            if (timePassed > 250) {
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
            } else {
                timePassed = System.currentTimeMillis() - startTime;
            }
        } else {
            startTime = 500;
        }

        if (gp.getGw().isAllyCollidingWithAnItem() && key == 'e') {
            gp.getGw().swapWeapon();
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

//        if (gp.getGw().getAlly().isUp()) {
//            gp.getGw().getAlly().setDirection("up");
//        }
//        if (gp.getGw().getAlly().isRight()) {
//            gp.getGw().getAlly().setDirection("right");
//        }
//        if (gp.getGw().getAlly().isDown()) {
//            gp.getGw().getAlly().setDirection("down");
//        }
//        if (gp.getGw().getAlly().isLeft()) {
//            gp.getGw().getAlly().setDirection("left");
//        }
    }
}
