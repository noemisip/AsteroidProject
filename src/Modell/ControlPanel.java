package Modell;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;

public class ControlPanel extends JPanel{
    private Settler s;
    private ArrayList<String> text=new ArrayList<String>();
    private JPanel jp;
    private Asteroid asteroid;
    private Material material;
    private Gate gate;
    JButton move;
    JButton drill;
    JButton mine;
    JButton restore;
    JButton create_robot;
    JButton create_gate;
    JButton place_gate;
    JLabel settler_label;
    JLabel asteroid_label;
    JLabel material_label;
    JLabel gate_label;
    JLabel image;

    public ControlPanel() throws IOException {
        image=new JLabel(new ImageIcon(ImageIO.read(new File("controlpanel.png"))));
        this.add(image);

        s=new Settler();
        Init();
        this.setLayout(new BorderLayout());
        //this.add(settler_label,BorderLayout.NORTH);
        this.setSize(100,100);
        this.add(jp);
        this.setVisible(true);
    }

    public void SetText(){
        text.add("Asteroid: "+Main.getInstance().GetKey(s.GetAsteroid()));
        String gates="";
        for(int i=0;i<s.GetGateList().size();i++){
            gates=gates+Main.getInstance().GetKey(s.GetGateList().get(i))+",";
        }
        gates.substring(0, gates.length() - 1);
        text.add("Gates: "+gates);
        String materials="";
        for(int i=0;i<s.GetMaterials().size();i++){
            materials=materials+Main.getInstance().GetKey(s.GetMaterials().get(i))+",";
        }
        materials.substring(0, materials.length() - 1);
        text.add("Materials: "+materials);

    }
    public void Init(){
        jp=new JPanel(new BorderLayout());
        JPanel settlerpanel=new JPanel(new BorderLayout());
        settler_label=new JLabel("settler1");
        settlerpanel.add(settler_label);
        jp.add(settlerpanel,BorderLayout.NORTH);
        JPanel buttons=new JPanel(new BorderLayout());
        move=new JButton("Move");
        buttons.add(move,BorderLayout.NORTH);
        jp.add(buttons,BorderLayout.CENTER);


        drill=new JButton("Drill");
        mine=new JButton("Mine");
        restore=new JButton("Restore");
        create_robot=new JButton("Create Robot");
        create_gate=new JButton("Create Gate");
        place_gate=new JButton("Place Gate");
        //jp.add(move);jp.add(drill);jp.add(mine);jp.add(restore);jp.add(create_robot);
        //jp.add(create_gate); jp.add(place_gate);
        //settler_label=new JLabel(Main.getInstance().GetKey(s));

        //asteroid_label=new JLabel(text.get(0));
        //material_label=new JLabel(text.get(1));
        //gate_label=new JLabel(text.get(2));
        asteroid_label=new JLabel("Asteroid: asteroid1");
        material_label=new JLabel("Materials:uran:2,ice:0,iron:0,carbon:0");
        gate_label=new JLabel("Gates:gate1,gate2");
        //jp.add(settler_label);jp.add(asteroid_label);jp.add(material_label);jp.add(gate_label);
    }
    public void SetSettler(Settler settler){s=settler;}
    public void Update(){
        SetText();
        settler_label.setText(text.get(0));
        material_label.setText(text.get(1));
        gate_label.setText(text.get(2));
    }
    public Gate GetGate(){return gate;}
    public Asteroid GetAsteroid(){return asteroid;}
    public Material GetMaterial(){return material;}
    public int UserInput(){return 1;}
}
