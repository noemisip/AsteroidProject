package Modell;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ControlPanel extends JPanel{
    private Settler settler;
    private ArrayList<String> text=new ArrayList<String>();
    private ArrayList<String> materialList = new ArrayList<>();
    private String[] neighbourList = null;
    private Asteroid asteroid;
    private Material material;
    private Gate gate;
    private JButton move;           //mozgas
    private JButton drill;          //furas
    private JButton mine;           //banyaszat
    private JButton restore;        //nyersanyag visszahelyezese
    private JButton create_robot;   //robot epites
    private JButton create_gate;    //kapu epitese
    private JButton place_gate;     //kapu elhyezese
    private JLabel settler_label;   //settler neve
    private JLabel asteroid_label;  //aszteroida neve
    private JLabel material_label;  //nyersanyagok listaja
    private JLabel gate_label;      //kapuk listaja
    private JLabel image;           //hatter
    private JComboBox leg;
    private JComboBox res;
    private JComboBox gat;

    public ControlPanel() throws IOException {
        image=new JLabel(new ImageIcon(ImageIO.read(new File("controlpanel.png"))));
        this.setLayout(new BorderLayout());
        this.add(image, BorderLayout.PAGE_START);
        Init();
        this.setSize(100,700);
        this.setVisible(true);
    }

    public void SetText(){
        text.clear();
        text.add(0,Main.getInstance().GetKey(settler));
        text.add(1, "Asteroid: "+Main.getInstance().GetKey(settler.GetAsteroid()));//aszteroida nevenek megkerese

        String gates=""; //kapuk listaja
        if(settler.GetGateList().size() > 0) { //a telepesnel van valahany kapu
            for (int i = 0; i < settler.GetGateList().size(); i++) {
                gates = gates + Main.getInstance().GetKey(settler.GetGateList().get(i)) + ",";
            }
        gates.substring(0, gates.length() - 1);
        }
        else gates = "I don't have any gates :("; //nincs a telepesnel egy kapu sem
        text.add(2,"Gates: "+gates);

        materialList.clear();
        int ironcnt= 0, icecnt = 0, carboncnt = 0, uraniumcnt =0;
        for(int i=0; i<settler.GetMaterials().size(); i++){ //nyersanyagok listazasa: mibol hany darab van
            if(settler.GetMaterials().get(i).IsEquales(new Ice())) icecnt++;
            else if(settler.GetMaterials().get(i).IsEquales(new Iron())) ironcnt++;
            else if(settler.GetMaterials().get(i).IsEquales(new Carbon())) carboncnt++;
            else if(settler.GetMaterials().get(i).IsEquales(new Uranium())) uraniumcnt++;
        }
        if(ironcnt>0) materialList.add("Iron");
        if(icecnt>0) materialList.add("Ice");
        if(carboncnt>0) materialList.add("Carbon");
        if(uraniumcnt>0) materialList.add("Uranium");
        text.add(3,"Materials: ice: "+icecnt+" iron: "+ironcnt+" carbon: "+carboncnt+" uranium: "+uraniumcnt);

    }
    public void Init(){
        settler_label=new JLabel("settler1");
        settler_label.setFont(new Font(settler_label.getFont().getName(), Font.PLAIN, 40));
        settler_label.setBounds(100, 40, 200, 50);
        image.add(settler_label);

        move=new JButton("Move"); //move gomb: a telepes mozog
        move.putClientProperty("id", 1);
        move.setBounds(20, 120, 140, 40);
        image.add(move);
        move.addActionListener(Main.getInstance());

        drill=new JButton("Drill"); //drill gomb: a telepes fur
        drill.putClientProperty("id", 2);
        drill.setBounds(20, 180, 140, 40);
        image.add(drill);
        drill.addActionListener(Main.getInstance());

        mine=new JButton("Mine"); //mine gomb: a telepes banyaszik az aszteroidan
        mine.putClientProperty("id", 3);
        mine.setBounds(20, 240, 140, 40);
        image.add(mine);
        mine.addActionListener(Main.getInstance());

        restore=new JButton("Restore"); //restore gomb: a telepes visszahelyez egy nyersanyagot az aszteroidara
        restore.putClientProperty("id",4);
        restore.setBounds(20, 300, 140, 40);
        image.add(restore);
        restore.addActionListener(Main.getInstance());

        create_robot=new JButton("Create Robot"); //create robot gomb: a telepes megprobal letrehozni egy robotot
        create_robot.putClientProperty("id",5);
        create_robot.setBounds(20, 360, 140, 40);
        image.add(create_robot);
        create_robot.addActionListener(Main.getInstance());

        create_gate=new JButton("Create Gate"); //create gate gomb: a telepes megprobal letrehozni egy teleportkapu-part
        create_gate.putClientProperty("id", 6);
        create_gate.setBounds(20, 420, 140, 40);
        image.add(create_gate);
        create_gate.addActionListener(Main.getInstance());

        place_gate=new JButton("Place Gate"); //place gate gomb: a telepes lehelyez egy kaput az aszteroidan
        place_gate.putClientProperty("id", 7);
        place_gate.setBounds(20, 480, 140, 40);
        image.add(place_gate);
        place_gate.addActionListener(Main.getInstance());

        asteroid_label=new JLabel("Asteroid:"); //melyik aszteroidan all eppen a telepes
        asteroid_label.setFont(new Font(asteroid_label.getFont().getName(), Font.PLAIN, 15));
        asteroid_label.setBounds(20, 560, 200, 20);
        image.add(asteroid_label);

        gate_label=new JLabel("Gates:"); //milyen kapuk vannak a telepesnel
        gate_label.setFont(new Font(gate_label.getFont().getName(), Font.PLAIN, 15));
        gate_label.setBounds(20, 620, 300, 20);
        image.add(gate_label);

        material_label=new JLabel("Materials:"); //milyen nyersanyagok vannaka  telepesnel
        material_label.setFont(new Font(material_label.getFont().getName(), Font.PLAIN, 15));
        material_label.setBounds(20, 590, 300, 20);
        image.add(material_label);


        leg=new JComboBox(); //azok az aszteroidak, ahova a telepes lepni tud
        leg.setBounds(200,128,100,20);
        leg.setEditable(true);
        image.add(leg);

        res=new JComboBox(); //nyersanyagok listaja, amibol vissza tud tenni
        res.setBounds(200,308,100,20);
        res.setEditable(true);
        image.add(res);

        gat=new JComboBox(); //gatek listaja, amibol vissza tud tenni
        gat.setBounds(200,488,100,20);
        gat.setEditable(true);
        image.add(gat);
    }
    public void SetSettler(Settler settler){
        this.settler =settler;
    }
    public void Update(){ //frissiti a nezetet
        SetText();
        settler_label.setText(text.get(0));
        asteroid_label.setText(text.get(1));
        gate_label.setText(text.get(2));
        material_label.setText(text.get(3));

        leg.removeAllItems();
        if(settler.GetAsteroid().GetNeighbours()!=null) {
            for (int i = 0; i < settler.GetAsteroid().GetNeighbours().size(); i++) {
                if(settler.GetAsteroid().GetNeighbours().get(i).GetAsteroid()!=null) {
                    leg.addItem(Main.getInstance().GetKey(settler.GetAsteroid().GetNeighbours().get(i).GetAsteroid()));
                }
            }
        }
        res.removeAllItems();
        for(int i=0; i<materialList.size(); i++) {
            res.addItem(materialList.get(i));
        }
        gat.removeAllItems();
        for (int i = 0; i < settler.GetGateList().size(); i++) {
           gat.addItem(Main.getInstance().GetKey(settler.GetGateList().get(i)) );
        }
    }
    public Gate GetGate(){
        gate=(Gate)Main.getInstance().GetHash().get(String.valueOf(gat.getSelectedItem()));
        return gate;
    }
    public Asteroid GetAsteroid(){
        asteroid = (Asteroid)Main.getInstance().GetHash().get(String.valueOf(leg.getSelectedItem()));
        return asteroid;
    }
    public Material GetMaterial(){
        Material m;
        switch (String.valueOf(res.getSelectedItem())) {
            case "Ice":
                m= new Ice();
                break;
            case "Iron": m =new Iron();
            break;
            case "Carbon": m=new Carbon();
            break;
            case "Uranium": m= new Uranium();
            break;
            default: m= null;
            break;
        }
        material = m;
        return material;
    }
}
