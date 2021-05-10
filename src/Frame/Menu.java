package Frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Menu extends JFrame{                                   //az elso nezet ami megjelenik a felhasznalo elott, itt tudja elinditani a jatekot, vagy kilepni
    private final JButton start = new JButton("START");             //jatek inditasa
    private final JButton exit = new JButton("Exit");               //kilepes a jatekbol
    JLabel image;


    public Menu() throws IOException {                                     //megjelenitjuk az ablakot egy keretben
        super("Aszteroida banyaszat");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        image = new JLabel(new ImageIcon(ImageIO.read(new File("menu.png"))));    //hatter beallitasa
        image.setBounds(0,0,1000, 666);                                            //meretek megadasa
        this.add(image);
        start.setBounds(440, 280, 120, 40);
        image.add(start);
        start.addActionListener(new ActionListener() {                          //esemeny start gomb nyomasa eseten
            @Override
            public void actionPerformed(ActionEvent e) {
                SettlerNumber sn = new SettlerNumber();
                dispose();
            }
        });
        exit.setBounds(440, 350, 120, 40);                                      //esemeny exit gomb nyomasa eseten
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
