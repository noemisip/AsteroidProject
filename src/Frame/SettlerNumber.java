package Frame;

import Modell.Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SettlerNumber extends JFrame {  //azt a nezetet valositja meg,ahol a jatek bekeri a felhasznalotol,hogy hany telepessel szeretne jatszani

    private int settlerNr;        //telepesek szama
    private JButton okButton;     //dontes veglegesito ok gomb

    public SettlerNumber() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("background.png")))));        //jatek hatterenek beallitasa

        } catch (IOException e) {
            e.printStackTrace();

        }
        this.getContentPane().setLayout(new BorderLayout());
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLayout(null);

        JLabel text = new JLabel("How many settlers will play?");     //bekerjuk a telepesek szamat
        text.setBounds(200, 230, 800, 60);                            //meretek beallitasa
        text.setFont(new Font("Dialog", Font.PLAIN, 50));
        text.setForeground(Color.white);
        this.add(text);

        final JTextField textField = new JTextField();              //ide irja a felhasznalo a valaszat
        textField.setBounds(400, 350, 200, 50);
        textField.setColumns(10);
        this.add(textField);

        okButton = new JButton("Ok");                              //veglegesites
        okButton.setBounds(450, 500, 100, 50);                      //meretek beallitasa
        okButton.setFont(new Font("Dialog", Font.PLAIN, 30));
        this.add(okButton);                                        //gomb hozzaadasa
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    settlerNr = Integer.parseInt(textField.getText()); //a felhasznalo megadja hayn settler jatszik
                    if(settlerNr==0){dispose();Main.GetView().Menu(); //ha 0-t adott meg, akkor visszalep a menube
                    }
                    else{ //ha nem 0-t adott meg, akkor elkezdodik a jatek
                    dispose();
                    Main.Load(settlerNr);}                              //betolti a jatekot a megadott telepesek szamaval
                } catch (NumberFormatException | IOException nex) {
                    nex.printStackTrace();
                }
            }
        });
    }

}
