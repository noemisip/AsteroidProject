package Frame;

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

public class SettlerNumber {

    private JFrame frame = new JFrame();
    private int settlerNr;

    public SettlerNumber() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("background.png")))));

        } catch (IOException e) {
            e.printStackTrace();

        }
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        frame.setLayout(null);

        JLabel text = new JLabel("How many settlers will play?");
        text.setBounds(330, 230, 800, 60);
        text.setFont(new Font("Dialog", Font.PLAIN, 50));
        text.setForeground(Color.white);
        frame.add(text);

        final JTextField textField = new JTextField();
        textField.setBounds(550, 350, 200, 50);
        textField.setColumns(10);
        frame.add(textField);

        JButton okButton = new JButton("Ok");
        okButton.setBounds(600, 500, 100, 50);
        okButton.setFont(new Font("Dialog", Font.PLAIN, 30));
        frame.add(okButton);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    settlerNr = Integer.parseInt(textField.getText());
                    System.out.println("szia:" + settlerNr);
                    frame.setVisible(false);
                    //plusz meg hogy letrehozzon annyi settlert ahanyat kapott csak ez meg kerdeses hogy hogy, talan returnoljon
                    //egy szamot??
                } catch (NumberFormatException nex) {
                    // frame.showMessage("Szamot kell beirni!");
                }
            }
        });


    }
}