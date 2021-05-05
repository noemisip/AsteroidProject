package Frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FieldPanel extends JPanel{
    JLabel image;
    public FieldPanel() throws IOException {
        image=new JLabel(new ImageIcon(ImageIO.read(new File("background.png"))));
        setMaximumSize(new Dimension(10,10));
        this.add(image);


        this.setVisible(true);
    }
}
