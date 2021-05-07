package Frame;

import Modell.Drawable;
import Modell.GRobot;
import Modell.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class FieldPanel extends JPanel{
    static JLabel image;
    static JLabel robot;

    public FieldPanel() {
        try {
            image = new JLabel(new ImageIcon(ImageIO.read(new File("background.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setMaximumSize(new Dimension(10, 10));
        this.setLayout(new BorderLayout());
        JLabel lines=new JLabel();

        this.add(image);
        this.setVisible(true);
        this.setVisible(true);
    }
    @Override public void paint(Graphics g) {
        try {
            Point upPt = new Point(0, 0);
            Point dnPt = new Point(700, 650);
            final BufferedImage upImg = ImageIO.read(new File("background.png"));
            final BufferedImage dnImg = ImageIO.read(new File("asteroid.png"));
            if (upImg != null && dnImg != null) {

                // draw both images at their respective locations
                g.drawImage(upImg, upPt.x, upPt.y, this);
                g.drawImage(dnImg, 700, 400,30,30, this);
                //g.drawImage(dnImg, dnPt.x, dnPt.y, this);

                // to get a smooth line, use rendering hiints
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setStroke(new BasicStroke(2f));

                g.drawLine(700, 400, 730, 600);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public static void init(String image_icon, Drawable drawable) {
        try {
            robot = new JLabel(new ImageIcon(ImageIO.read(new File(image_icon))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        robot.setBounds(drawable.GetX(),drawable.GetY(),200,200);
        image.add(robot);
        /*JLabel lines=new JLabel();
        Graphics g=lines.getGraphics();
        g.setColor(Color.ORANGE);
        g.fillRect(0,0,600,400);
        g.setColor(Color.BLACK);
        // Keep this until I figured out if it's painted on load or not.
        g.drawLine(700, 300, 800, 440);
        lines.setBounds(700,700,500,500);
        image.add(lines);*/
    }

}
