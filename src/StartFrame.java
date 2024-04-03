import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame extends JPanel implements ActionListener {
    private JFrame jf;
    private JButton newGame, load, exit;

    public StartFrame() {
        jf = new JFrame("Azura");
        jf.setLocation(700, 300);
        jf.setSize(640, 480);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font defaultFont = new Font("Courier New", Font.BOLD, 30);

        newGame = new JButton("New Game");
        newGame.setFont(defaultFont);
        newGame.setLocation(300, 200);
        newGame.setBounds(300, 200, 150, 30);
        newGame.addActionListener(this);

        load = new JButton("Load");
        load.setFont(defaultFont);
        load.setLocation(300, 240);

        exit = new JButton("Exit");
        exit.setFont(defaultFont);
        exit.setLocation(300, 280);

        jf.add(newGame);
//        jf.add(load);
//        jf.add(exit);

        jf.setVisible(true);
    }

    public void paintComponent(Graphics g) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGame) {
            System.out.println("clicked");
        }
    }
}
