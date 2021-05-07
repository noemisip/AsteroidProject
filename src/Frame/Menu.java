package Frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Menu extends JFrame{
    private final JButton start = new JButton("START");
    private final JButton exit = new JButton("Exit");
    JLabel image;


    public Menu() throws IOException {
        super("Aszteroida banyaszat");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        image = new JLabel(new ImageIcon(ImageIO.read(new File("menu.png"))));
        image.setBounds(0,0,1000, 666);
        this.add(image);
        start.setBounds(440, 280, 120, 40);
        image.add(start);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        exit.setBounds(440, 350, 120, 40);
        image.add(exit);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }


}
