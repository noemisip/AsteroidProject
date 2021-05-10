package Frame;import Modell.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameFrame extends JFrame {                   //jatekter megjelenitese, bal oldalt a controlpanellel jobb oldalon pedig a jatek megjelenitesevel, telepesek, aszteroidak stb..
    FieldPanel field=new FieldPanel();                      //panelek letrehozasa
    ControlPanel control=new ControlPanel();
    View view;

    public GameFrame(View v) throws IOException {            //panelek hozzaadasa
        super("Aszteroida banyaszat");
        view=v;
        field.SetView(v);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(control, BorderLayout.WEST);
        this.add(field, BorderLayout.EAST);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }
    public ControlPanel GetControlPanel(){            //controlpanel getter fuggveny
        return control;
    }
    public FieldPanel GetFieldPanel(){return field;}  //fieldpanel getter fuggveny
    public void Exit(){                             //kilepes
        dispose();
    }
}
