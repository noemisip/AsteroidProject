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
        super("Aszteroida bányászat");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try{
            setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("menu.png")))));

        }catch(IOException e)
        {
            e.printStackTrace();

        }
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.pack();
        this.setVisible(true);

    }
}
