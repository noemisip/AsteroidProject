package Frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Menu extends JFrame {
    private JFrame frame = new JFrame("Aszteroida bányászat");
    private final JButton start = new JButton("START");
    private final JButton exit = new JButton("Exit");

    public Menu(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try{
            frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("menu.png")))));

        }catch(IOException e)
        {
            e.printStackTrace();

        }
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

    }
}
