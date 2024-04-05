
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class StartFrame extends JPanel implements ActionListener {
    private int count = 0;
    private boolean stepping;
    private JFrame jf;
    private JButton newGame, load, exit;
    private Picture crystal1 = new Picture(370, 0, (new ImageIcon("image/Title/crystal1.png")).getImage());
    private Picture crystal2 = new Picture(480, 220, (new ImageIcon("image/Title/crystal2.png")).getImage());
    private Picture crystal3 = new Picture(330, 30, (new ImageIcon("image/Title/crystal3.png")).getImage());
    private Picture crystal4 = new Picture(350, 250, (new ImageIcon("image/Title/crystal4.png")).getImage());
    private Image icon = new ImageIcon("image/Item/arm/arm5.png").getImage();

    public StartFrame() {
        this.setSize(640, 480);
        jf = new JFrame("Candela");

        Font defaultFont = new Font("Courier New", Font.BOLD, 24);

        newGame = new JButton("New Game");
        newGame.setFont(defaultFont);
        newGame.setBounds(245, 240, 150, 24);
        newGame.setForeground(Color.white); // sets the color of text to white
        newGame.setContentAreaFilled(false);// removes background of button(transparent)
        newGame.setFocusPainted(false); //removes blue focus ring around the component
        newGame.addActionListener(this);

        load = new JButton("Load");
        load.setFont(defaultFont);
        load.setBounds(245, 280, 150, 24);
        load.setForeground(Color.white); // sets the color of text to white
        load.setContentAreaFilled(false);// removes background of button(transparent)
        load.setFocusPainted(false); //removes blue focus ring around the component
        load.addActionListener(this);

        exit = new JButton("Exit");
        exit.setFont(defaultFont);
        exit.setBounds(245, 320, 150, 24);
        exit.setForeground(Color.white); // sets the color of text to white
        exit.setContentAreaFilled(false);// removes background of button(transparent)
        exit.setFocusPainted(false); //removes blue focus ring around the component
        exit.addActionListener(this);

        jf.setSize(640, 450);
        jf.setIconImage(icon);
        jf.setLocationRelativeTo(null);//screen pop up location
        jf.setResizable(false);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.add(newGame);
        jf.add(load);
        jf.add(exit);
        jf.add(this);
        jf.setVisible(true);
        //frame thread
        Runnable r = () -> {
            // count decides step, which decides how the crystals at the start screen moves(up or down)
            while (true) {
                if (count < 50) {
                    count++;
                    stepping = true;
                } else if (count >= 50) {
                    count++;
                    stepping = false;
                }
                if (count == 100) {
                    count = 0;
                }
                this.repaint();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread startLine = new Thread(r);
        startLine.start();
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.scale(0.7, 0.7);
        g.drawImage((new ImageIcon("image/Title/title_back.png")).getImage(), 0, 0, this);
        crystal1.draw(g);
        crystal2.draw(g);
        crystal3.draw(g);
        crystal4.draw(g);
        if (stepping) {
            crystal1.adjustY(0.0005f);
            crystal2.adjustY(-0.001f);
            crystal3.adjustY(0.001f);
            crystal4.adjustY(-0.002f);
        } else {
            crystal1.adjustY(-0.0005f);
            crystal2.adjustY(0.001f);
            crystal3.adjustY(-0.001f);
            crystal4.adjustY(0.002f);
        }

        g2d.drawImage((new ImageIcon("image/Title/title_light.png")).getImage(), -50, -50, this);
        newGame.repaint();
        load.repaint();
        exit.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGame) {
            jf.dispose();
            GameFrame gf = new GameFrame();
        }
        if (e.getSource() == exit) {
            System.exit(0);
        }
    }
}

