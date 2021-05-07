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

public class SettlerNumber extends JFrame {

    private int settlerNr;
    private JButton okButton;

    public SettlerNumber() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("background.png")))));

        } catch (IOException e) {
            e.printStackTrace();

        }
        this.getContentPane().setLayout(new BorderLayout());
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLayout(null);

        JLabel text = new JLabel("How many settlers will play?");
        text.setBounds(330, 230, 800, 60);
        text.setFont(new Font("Dialog", Font.PLAIN, 50));
        text.setForeground(Color.white);
        this.add(text);

        final JTextField textField = new JTextField();
        textField.setBounds(550, 350, 200, 50);
        textField.setColumns(10);
        this.add(textField);

        okButton = new JButton("Ok");
        okButton.setBounds(600, 500, 100, 50);
        okButton.setFont(new Font("Dialog", Font.PLAIN, 30));
        this.add(okButton);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    settlerNr = Integer.parseInt(textField.getText());
                    dispose();
                    Main.Load(settlerNr);
                    //plusz meg hogy letrehozzon annyi settlert ahanyat kapott csak ez meg kerdeses hogy hogy, talan returnoljon
                    //egy szamot??
                } catch (NumberFormatException | IOException nex) {
                    // frame.showMessage("Szamot kell beirni!");
                }
            }
        });


    }

    public int GetSettlerNumber(){
        return settlerNr;
    }

    public JButton GetButton(){return okButton;}
}
