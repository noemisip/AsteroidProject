package Frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Menu extends JFrame{
    private JFrame frame = new JFrame("Aszteroida banyaszat");
    private final JButton start = new JButton("START");
    private final JButton exit = new JButton("Exit");
    JLabel image;


    public Menu() throws IOException {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        image = new JLabel(new ImageIcon(ImageIO.read(new File("menu.png"))));
        image.setBounds(0,0,1000, 666);
        frame.add(image);
        start.setBounds(440, 280, 120, 40);
        image.add(start);
        exit.setBounds(440, 350, 120, 40);
        image.add(exit);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
}
