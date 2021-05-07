package Frame;

import Modell.Drawable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class FieldPanel extends JPanel{
    static JLabel image;
    static JLabel icon;

    public FieldPanel() {
        try {
            image = new JLabel(new ImageIcon(ImageIO.read(new File("background.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setMaximumSize(new Dimension(10, 10));
        this.setLayout(new BorderLayout());
        this.add(image);
        this.setVisible(true);
    }

    public static void init(String image_icon, Drawable drawable) {
        try {
            icon = new JLabel(new ImageIcon(ImageIO.read(new File(image_icon))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        icon.setBounds(drawable.GetX(),drawable.GetY(),200,200);
        image.add(icon);
    }
}
