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
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setResizable(false);
//        frame.setLayout(new BorderLayout());
//        frame.setSize(2000,1000);
//        frame.setVisible(true);
//
//       JLabel contentPane = new JLabel(){
//        public void paintComponent(Graphics g){
//            Image img = Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("menu.png"));
//            g.drawImage(img, 0,0,this.getWidth(), this.getHeight(), this);
//        }
//       };
//       contentPane.setBorder(new EmptyBorder(5,5,5,5));
//       contentPane.setLayout(new BorderLayout(0,0));
//       frame.setContentPane(contentPane);

        super("window");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try{
            setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:\\MenuSima.jpg")))));

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
