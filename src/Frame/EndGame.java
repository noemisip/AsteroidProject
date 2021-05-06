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

public class EndGame {
	private JFrame frame = new JFrame();
	private JButton okButton = new JButton("Ok");
	
	public EndGame(boolean result){
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try{
            frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("background.png")))));

        }catch(IOException e)
        {
            e.printStackTrace();

        }
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        frame.setLayout(null);
        
        if(result) {
        	JLabel winningLabel = new JLabel("You have won!!!!");
        	winningLabel.setBounds(400, 220, 800, 60);
        	winningLabel.setFont(new Font("Dialog", Font.PLAIN, 65));
        	winningLabel.setForeground(Color.white);
        	frame.add(winningLabel);
        	
        	JLabel winningLabel2 = new JLabel("YAAAY!!!!");
        	winningLabel2.setBounds(530, 350, 500, 60);
        	winningLabel2.setFont(new Font("Dialog", Font.PLAIN, 50));
        	winningLabel2.setForeground(Color.white);
        	frame.add(winningLabel2);
        	
        	okButton.setBounds(600, 500, 100, 50);
        	okButton.setFont(new Font("Dialog", Font.PLAIN, 30));
        	okButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	frame.setVisible(false);
	            }
	        }); 
			
        	frame.getContentPane().add(okButton);
        
        }
        else {
        	JLabel loosingLabel = new JLabel("You have lost! :("); 
        	loosingLabel.setBounds(400, 300, 800, 60);
        	loosingLabel.setFont(new Font("Dialog", Font.PLAIN, 65));
        	loosingLabel.setForeground(Color.white);
        	frame.add(loosingLabel);
        	
        	okButton.setBounds(600, 450, 100, 50);
        	okButton.setFont(new Font("Dialog", Font.PLAIN, 30));
        	okButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	frame.setVisible(false);
	            }
	        }); 
			
        	frame.getContentPane().add(okButton);
        }
    }
	
}