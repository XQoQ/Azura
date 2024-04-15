import javax.swing.*;

public class GameMap {
    private GameFrame gf;
    private Background bg = new Background();

    public GameMap(GameFrame gf) {
        this.gf = gf;
    }

    public Background getBg() {
        return bg;
    }
}
