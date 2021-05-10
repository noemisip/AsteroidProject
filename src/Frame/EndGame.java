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
import javax.swing.*;

public class EndGame {
	private JFrame frame = new JFrame(); //megjelenik az ablak
	private JButton okButton = new JButton("Ok");
	
	public EndGame(boolean result){
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try{  
		//hatter beallitasa
            frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("background.png")))));

        }catch(IOException e)
        {
            e.printStackTrace();

        }
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setResizable(false); //ne lehessen atmeretezni
        frame.pack();
        frame.setVisible(true); //legyen lathato
        frame.setLayout(null);
        //Ha nyertek, akkor megjelenitjuk, hogy nyertek
        if(result) {
        	JLabel winningLabel = new JLabel("You have won!!!!"); //szoveg beallitasa
        	winningLabel.setBounds(260, 220, 500, 60); //pozicio beallitasa
        	winningLabel.setFont(new Font("Dialog", Font.PLAIN, 65)); //betumeret beallitasa
        	winningLabel.setForeground(Color.white); //feher betuszin
        	frame.add(winningLabel); //Label hozzaadasa az ablakhoz
        	
        	JLabel winningLabel2 = new JLabel("YAAAY!!!!"); //szoveg beallitasa
        	winningLabel2.setBounds(400, 350, 400, 60); //pozicio beallitasa
        	winningLabel2.setFont(new Font("Dialog", Font.PLAIN, 50)); //betumeret beallitasa
        	winningLabel2.setForeground(Color.white); //feher betuszin
        	frame.add(winningLabel2); //Label hozzaadasa az ablakhoz
        
        }
		//ha nem nyertek, akkor megjelenitjuk, hogy nem nyertek
        else {
        	JLabel loosingLabel = new JLabel("You have lost! :("); //szoveg beallitasa
        	loosingLabel.setBounds(270, 300, 800, 60); //pozicio beallitasa
        	loosingLabel.setFont(new Font("Dialog", Font.PLAIN, 65)); //betumeret allitasa miatt
        	loosingLabel.setForeground(Color.white); //feher betuszin
        	frame.add(loosingLabel); //Label hozzaadasa az ablakhoz

        }
		//Gomb beallitasa
		okButton.setBounds(450, 500, 100, 50);
		okButton.setFont(new Font("Dialog", Font.PLAIN, 30));
		okButton.addActionListener(new ActionListener() { //nyomasra az ablak bezarodik, es megjelenik a Menu ablak
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				try {
					Main.GetView().Menu();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
			}
		});

		frame.getContentPane().add(okButton); //Gomb hozzaadasa az ablakhoz
    }
	
}
