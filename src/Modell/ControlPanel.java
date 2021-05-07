package Modell;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private int result;
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
        this.setLayout(new BorderLayout());
        image.setBounds(0,0,100,600);
        this.add(image);
        Init();
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
        settler_label=new JLabel("settler1");
        settler_label.setFont(new Font(settler_label.getFont().getName(), Font.PLAIN, 40));
        settler_label.setBounds(100, 40, 200, 50);
        image.add(settler_label);

        move=new JButton("Move"); //move gonb: a telepes mozog
        move.setBounds(20, 120, 140, 40);
        image.add(move);
        move.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    result = 1;
            }
        });

        drill=new JButton("Drill"); //drill gomb: a telepes fur
        drill.setBounds(20, 180, 140, 40);
        image.add(drill);
        drill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result = 2;
            }
        });

        mine=new JButton("Mine"); //mine gomb: a telepes banyaszik az aszteroidan
        mine.setBounds(20, 240, 140, 40);
        image.add(mine);
        mine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result = 3;
            }
        });

        restore=new JButton("Restore"); //restore gomb: a telepes visszahelyez egy nyersanyagot az aszteroidara
        restore.setBounds(20, 300, 140, 40);
        image.add(restore);
        restore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result = 4;
            }
        });

        create_robot=new JButton("Create Robot"); //create robot gomb: a telepes megprobal letrehozni egy robotot
        create_robot.setBounds(20, 360, 140, 40);
        image.add(create_robot);
        create_robot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result = 5;
            }
        });

        create_gate=new JButton("Create Gate"); //create gate gomb: a telepes megprobal letrehozni egy teleportkapu-part
        create_gate.setBounds(20, 420, 140, 40);
        image.add(create_gate);
        create_gate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result = 6;
            }
        });

        place_gate=new JButton("Place Gate"); //place gate gomb: a telepes lehelyez egy kaput az aszteroidan
        place_gate.setBounds(20, 480, 140, 40);
        image.add(place_gate);
        place_gate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result = 7;
            }
        });

        asteroid_label=new JLabel("Asteroid: asteroid1");
        asteroid_label.setFont(new Font(asteroid_label.getFont().getName(), Font.PLAIN, 15));
        asteroid_label.setBounds(20, 560, 200, 20);
        image.add(asteroid_label);
        material_label=new JLabel("Materials:uran:2,ice:0,iron:0,carbon:0");
        material_label.setFont(new Font(material_label.getFont().getName(), Font.PLAIN, 15));
        material_label.setBounds(20, 590, 300, 20);
        image.add(material_label);
        gate_label=new JLabel("Gates:gate1,gate2");
        gate_label.setFont(new Font(gate_label.getFont().getName(), Font.PLAIN, 15));
        gate_label.setBounds(20, 620, 200, 20);
        image.add(gate_label);
        JComboBox leg=new JComboBox();
        leg.setBounds(200,128,100,20);
        image.add(leg);
        JComboBox res=new JComboBox();
        res.setBounds(200,308,100,20);
        image.add(res);
        JComboBox gat=new JComboBox();
        gat.setBounds(200,488,100,20);
        image.add(gat);
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