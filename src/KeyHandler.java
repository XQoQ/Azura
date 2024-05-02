import java.awt.event.KeyEvent;
public class KeyHandler implements java.awt.event.KeyListener {
    private GameFrame gf;
    private long timePassed = 500;
    private long startTime;

    public KeyHandler(GameFrame gf) {
        this.gf = gf;
    }

    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();

        if (key == 'w') {
            gf.getAlly().setUp(true);
        }
        if (key == 'd') {
            gf.getAlly().setRight(true);
        }
        if (key == 's') {
            gf.getAlly().setDown(true);
        }
        if (key == 'a') {
            gf.getAlly().setLeft(true);
        }

        if (key == 'j') {
            if (timePassed > 250) {
                startTime = System.currentTimeMillis();
                timePassed = 0;
                Bullet bullet = new Bullet(0, gf.getAlly().getDirection());
                switch (gf.getAlly().getDirection()) {
                    case ("up") -> {
                        bullet.setX(gf.getAlly().getX() + 15);
                        bullet.setY(gf.getAlly().getY() - 30);
                    }
                    case ("right") -> {
                        bullet.setX(gf.getAlly().getX() + 10);
                        bullet.setY(gf.getAlly().getY() + 20);
                    }
                    case ("down") -> {
                        bullet.setX(gf.getAlly().getX() + 15);
                        bullet.setY(gf.getAlly().getY() + 30);
                    }
                    case ("left") -> {
                        bullet.setX(gf.getAlly().getX() - 10);
                        bullet.setY(gf.getAlly().getY() + 20);
                    }
                }
                gf.getBullets().add(bullet);
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
            gf.getAlly().setUp(false);
            gf.getAlly().setRunning(false);
        }
        if (key == 'd') {
            gf.getAlly().setRight(false);
            gf.getAlly().setRunning(false);
        }
        if (key == 's') {
            gf.getAlly().setDown(false);
            gf.getAlly().setRunning(false);
        }
        if (key == 'a') {
            gf.getAlly().setLeft(false);
            gf.getAlly().setRunning(false);
        }
    }
}
