package Frame;

import Modell.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FieldPanel extends JPanel{
    static JLabel image; //hatter
    static View view; //megjelenitesert felelos view
    private boolean solarstorm=false; //szamontartja volt-e solarstorm

    public FieldPanel() {
        try { //beallitjuk a hatteret
            image = new JLabel(new ImageIcon(ImageIO.read(new File("background.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setLayout(new BorderLayout());
        this.add(image);
        this.setVisible(true); //legyen lathato

    }
    @Override public void paint(Graphics g) { 

        try {
            Point upPt = new Point(0, 0);
            final BufferedImage upImg = ImageIO.read(new File("background.png"));
            if (upImg != null) {
                g.drawImage(upImg, upPt.x, upPt.y, this);
                //A szomszedsagok kirajzolasa
                if (view.GetDrawables() != null) {
                    for(int h=0;h<view.GetDrawables().size();h++) { //vegigmegy a drawableken
                        if(view.GetDrawables().get(h).GetName()!=null) { //ha megtalalhato a drawable
                            if (view.GetDrawables().get(h).GetName().contains("asteroid")) {
                                //a hash map segitsegevel megkeressuk a megfelelo aszteroidat
                                Asteroid ast = (Asteroid) Main.getInstance().GetHash().get(view.GetDrawables().get(h).GetName());
                                if (ast.GetNeighbours() != null) { //ha van szomszedja az aszteroidanak
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
                    //minden drawable-t kirajzolunk a jelenlegi poziciojara
                    for(int j=0;j<view.GetDrawables().size();j++){
                        view.GetDrawables().get(j).Draw(g,this);
                    }
                }
                //a napvihar megjelenitese
                if(solarstorm)
                {
                    g.setColor(Color.RED);
                    g.drawString("SOLARSTORM!!!",450,40);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        solarstorm=false; //mindig false-ra állitjuk
    }

    public void SetView(View v){view=v;}

    public void SetSolarstorm() {
        solarstorm = true;
    }
}
