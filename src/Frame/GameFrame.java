package Frame;import Modell.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameFrame extends JFrame {
    FieldPanel field=new FieldPanel();
    ControlPanel control=new ControlPanel();

    public GameFrame() throws IOException {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setContentPane(control);
        this.setLayout(new BorderLayout());
        this.add(control,BorderLayout.WEST);
        this.add(field,BorderLayout.EAST);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

    public ControlPanel GetControlPanel(){
        return control;
    }
    public FieldPanel GetFieldPanel(){return field;}
    public void Exit(){
        dispose();
    }
}