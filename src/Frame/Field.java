package Frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Field {
    private JFrame frame = new JFrame();
    public Field(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try{
            frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("background.png")))));

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
