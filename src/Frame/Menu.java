package Frame;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {
    private JFrame frame = new JFrame("Aszteroda bányászat");
    private final JButton start = new JButton("START");
    private final JButton exit = new JButton("Exit");

    public Menu(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
        JLabel backround = new JLabel(new ImageIcon());
        add(backround);
        backround.setLayout(new FlowLayout());

        JPanel btnpanel = new JPanel();
        btnpanel.setLayout(new BoxLayout(btnpanel, BoxLayout.Y_AXIS));
        btnpanel.add(start);
        btnpanel.add(Box.createRigidArea(new Dimension(0,5)));
        btnpanel.add(exit);

        start.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);

        Dimension size = btnpanel.getPreferredSize();
        btnpanel.setBounds(180,150,size.width,size.height);

        frame.add(btnpanel);
    }
}
