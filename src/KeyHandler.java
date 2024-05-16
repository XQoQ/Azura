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
            gp.getGw().getAlly().setUp(true);
        }
        if (key == 'd') {
            gp.getGw().getAlly().setRight(true);
        }
        if (key == 's') {
            gp.getGw().getAlly().setDown(true);
        }
        if (key == 'a') {
            gp.getGw().getAlly().setLeft(true);
        }

        if (key == 'j') {
            if (timePassed > 250) {
                startTime = System.currentTimeMillis();
                timePassed = 0;
                Bullet bullet = new Bullet(0, gp.getGw().getAlly().getDirection());
                switch (gp.getGw().getAlly().getDirection()) {
                    case ("up") -> {
                        bullet.setX(gp.getGw().getAlly().getX() + 15);
                        bullet.setY(gp.getGw().getAlly().getY() - 30);
                    }
                    case ("right") -> {
                        bullet.setX(gp.getGw().getAlly().getX() + 10);
                        bullet.setY(gp.getGw().getAlly().getY() + 20);
                    }
                    case ("down") -> {
                        bullet.setX(gp.getGw().getAlly().getX() + 15);
                        bullet.setY(gp.getGw().getAlly().getY() + 30);
                    }
                    case ("left") -> {
                        bullet.setX(gp.getGw().getAlly().getX() - 10);
                        bullet.setY(gp.getGw().getAlly().getY() + 20);
                    }
                }
                gp.getGw().getBullets().add(bullet);
            } else {
                timePassed = System.currentTimeMillis() - startTime;
            }
        } else {
            startTime = 500;
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
