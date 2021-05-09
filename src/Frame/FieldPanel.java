package Frame;

import Modell.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FieldPanel extends JPanel{
    static JLabel image;
    static JLabel icon;
    static View view;
    private boolean solarstorm=false;

    public FieldPanel() {
        try {
            image = new JLabel(new ImageIcon(ImageIO.read(new File("background.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setLayout(new BorderLayout());
        this.add(image, BorderLayout.LINE_START);
        this.setVisible(true);
    }
    @Override public void paint(Graphics g) {

        try {
            Point upPt = new Point(350, 0);
            final BufferedImage upImg = ImageIO.read(new File("background.png"));
            if (upImg != null) {
                g.drawImage(upImg, upPt.x, upPt.y, this);

                if (view.GetDrawables() != null) {
                    for(int h=0;h<view.GetDrawables().size();h++) { //vegigmegy a drawableken
                        if(view.GetDrawables().get(h).GetName()!=null) { //ha megtalalhato a drawable
                            if (view.GetDrawables().get(h).GetName().contains("asteroid")) {
                                Asteroid ast = (Asteroid) Main.getInstance().GetHash().get(view.GetDrawables().get(h).GetName());
                                if (ast.GetNeighbours() != null) {
                                    for (int l = 0; l < ast.GetNeighbours().size(); l++) {
                                        if(ast.GetNeighbours().get(l).GetAsteroid()!=null) { //csak akkor jelenjen meg szomszedsag ha kapu mindket fele le van rakva
                                            String peldanynev = Main.getInstance().GetKey(ast.GetNeighbours().get(l).GetAsteroid()); //a szomszedos aszteroida neve
                                            for (int u = 0; u < view.GetDrawables().size(); u++) {
                                                if (peldanynev!=null && peldanynev.equals(view.GetDrawables().get(u).GetName())) { //szomszedsag megjelenitese
                                                    g.drawLine(view.GetDrawables().get(h).GetX() + 10, view.GetDrawables().get(h).GetY() + 10,
                                                            view.GetDrawables().get(u).GetX() + 10, view.GetDrawables().get(u).GetY() + 10);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    for(int j=0;j<view.GetDrawables().size();j++){
                        view.GetDrawables().get(j).Draw(g,this);
                    }
                }
                if(solarstorm)
                {
                    g.setColor(Color.RED);
                    g.drawString("SOLARSTORM!!!",700,40);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        solarstorm=false;
    }

    public void SetView(View v){view=v;}

    public void SetSolarstorm() {
        solarstorm = true;
    }
}
