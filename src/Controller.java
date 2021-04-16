import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Controller {
    private HashMap<String, Object> hash = new HashMap<String, Object>();
    private Space sp;
    private int settlerCnt =1;
    private int gateCnt = 1;
    private int asteroidCnt = 1;
    private int ufoCnt =1;
    private int uraniumCnt =1;
    private int ironCnt =1;
    private int iceCnt =1;
    private int carbonCnt =1;
    private int robotCnt =1;
    private ArrayList<String> output = new ArrayList<String>();

    public HashMap<String, Object> GetHash(){return hash;}

    public int GetrobotCnt() {return robotCnt;}

    public void ReadFromConsole() {
        Scanner s = new Scanner(System.in);
        String[] cmd = {""};
        while (s.hasNextLine()) {
            String line = s.nextLine();
            if (!line.isEmpty()) cmd = line.split(" ");

            switch (cmd[0]) {
                case "place":
                    Place(cmd);
                    break;
                case "kerge":
                    Kerge_Gate(cmd);
                    break;
                case "move":
                    Move(cmd);
                    break;
                case "drill":
                    Drill(cmd);
                    break;
                case "mine":
                    Mine(cmd);
                    break;
                case "create":
                    Create(cmd);
                    break;
                case "restore":
                    Restore(cmd);
                    break;
                case "settler":
                    Settler_Properties(cmd);
                    break;
                case "asteroid":
                    Asteroid_Properties(cmd);
                    break;
                case "open":
                    Open(cmd);
                    break;
                case "check":
                    Check(cmd);
                    break;
                case "setup":
                    Setup(cmd);
                    break;
                case "set":
                    Set(cmd);
                    break;
                case "help":
                    Help();
                    break;
                case "SolarStorm":
                    SolarStorm(cmd);
                    break;
                default:
                    break;
            }
        }
        printOutput();
    }

    public void Place(String[] cmd) {
        Settler s = (Settler) hash.get(cmd[1]);
        Gate g = (Gate) hash.get(cmd[2]);
        if(s==null || g==null) {
            addOutput("Unsuccessful");
            return;
        }
        s.PlaceGate(g);
    }

    public void Kerge_Gate(String[] cmd) { //TODO
        Gate g = (Gate) hash.get(cmd[1]);
        if(g==null) {
            addOutput("Unsuccessful");
            return;
        }
        g.SetKerge(true);
    }

    public void Move(String[] cmd) {
        Asteroid a;
        if(cmd[1].contains("robot") || cmd[1].contains("ufo")){

            if(!(cmd[2].equals("-"))){
                Creature c = (Creature) hash.get(cmd[1]);
                a= (Asteroid) hash.get(cmd[2]);
                if(c==null || a==null) {
                    addOutput("Unsuccessful");
                    return;
                }
                else c.Move(a);
            }
            else{
                AI ai=(AI) hash.get(cmd[1]);
                ai.WhereToMove();

            }
        }

        Creature c = (Creature) hash.get(cmd[1]);

        if(!(cmd[2].equals("-"))) {
            a = (Asteroid) hash.get(cmd[2]);
            if(c==null || a==null) {
                addOutput("Unsuccessful");
                return;
            }
        }



        //c.Move(a);
    }

    public void Drill(String[] cmd) {
        Settler s = (Settler) hash.get(cmd[1]);
        if(s==null){
            addOutput("Unsuccessful");
            return;
        }
        s.Drill();
    }

    public void Mine(String[] cmd) {
        Settler s = (Settler) hash.get(cmd[1]);
        if(s==null){
            addOutput("Unsuccessful");
            return;
        }
        s.Mine();
    }

    public void Create(String[] cmd) {
        if (cmd[1].equals("robot")) {
            Settler s = (Settler) hash.get(cmd[3]);
            if(s==null){
                addOutput("Unsuccessful");
                return;
            }
            int num = s.GetAsteroid().GetCreatures().size();
            s.CreateRobot();
            if(s.GetAsteroid().GetCreatures().size()>num)
                hash.put(cmd[2], s.GetAsteroid().GetCreatures().get(s.GetAsteroid().GetCreatures().size()-1));
        } else if (cmd[1].equals("gate")) {
            Settler s = (Settler) hash.get(cmd[4]);
            if(s==null){
                addOutput("Unsuccessful");
                return;
            }
            int num = s.GetGateList().size();
            s.CreateGate();
            if(s.GetGateList().size()>num) {
                hash.put(cmd[2], s.GetGate(0));
                hash.put(cmd[3], s.GetGate(1));
            }
        }
    }

    public void Restore(String[] cmd) {
        Settler s = (Settler) hash.get(cmd[1]);
        if(s==null){
            addOutput("Unsuccessful");
            return;
        }
        Material m = null;
        switch (cmd[2]) {
            case "iron":
                m = new Iron();
                break;
            case "ice":
                m = new Ice();
                break;
            case "carbon":
                m = new Carbon();
                break;
            case "uranium":
                m = new Uranium();
                break;
            default:
                break;
        }
        s.RestoreMaterial(m);
    }

    public void Settler_Properties(String[] cmd) {
        Settler s = (Settler) hash.get(cmd[2]);
        if(s==null){
            addOutput("Unsuccessful");
            return;
        }
        String materials="";
        if(s.GetMaterials().size()==0) materials="-";
        else {
            for (Material m : s.GetMaterials()) {
                materials += getKey(m) + ";";
            }
            materials = materials.substring(0, materials.length() - 1);
        }
        String gates="";
        if(s.GetGateList().size()==0) gates="-";
        else {
            for (Gate g : s.GetGateList()) {
                gates += getKey(g) + ";";
            }
            gates = gates.substring(0, gates.length() - 1);
        }
        String asteroid;
        if(s.GetAsteroid()==null) asteroid="-";
        else asteroid=getKey(s.GetAsteroid());
        addOutput(cmd[2]+": "+ asteroid+" " + materials + " " + gates );
    }

    public void Asteroid_Properties(String[] cmd) {
        Asteroid a = (Asteroid) hash.get(cmd[2]);
        if(a==null){
            addOutput("Unsuccessful");
            return;
        }
        String material = "";
        if(a.GetMaterial()==null) material="-";
        else material = getKey(a.GetMaterial());
        String neighbours="";
        if(a.GetNeighbours().size()==0) neighbours="-";
        else {
            for (Transport t : a.GetNeighbours()) {
                neighbours += getKey(t) + ";";
            }
            neighbours = neighbours.substring(0, neighbours.length() - 1);
        }
        String creatures="";
        if(a.GetCreatures().size()==0) creatures="-";
        else {
            for (Creature c : a.GetCreatures()) {
                creatures += getKey(c) + ";";
            }
            creatures = creatures.substring(0, creatures.length() - 1);
        }
        addOutput(cmd[2]+": "+ material+" " +a.GetLayer()+" "+a.GetCloseToSun()+" "+ neighbours + " " + creatures );
    }

    public void Open(String[] cmd) {

    }

    public void Check(String[] cmd) {
        if (cmd[1].equals("settlers")) {
            //Game.getInstance().CheckSettlers();
            String settlers="";
            if(Game.getInstance().GetSettlers()==null) {
                addOutput("0");
            }
            else {
                for (Settler s : Game.getInstance().GetSettlers()) {
                    settlers += getKey(s) + ";";
                }
                settlers = settlers.substring(0, settlers.length() - 1);
                addOutput(Game.getInstance().GetSettlers().size() + " " + settlers);
            }
        }
        else if (cmd[1].equals("base")) {
            Asteroid a = (Asteroid) hash.get(cmd[2]);
            if(a==null){
                addOutput("Unsuccessful");
                return;
            }
            Game.getInstance().CheckBase(a);
            addOutput(Game.getInstance().GetOnGame()? "": "Win");
        }
    }

    public void Setup(String[] cmd) {
        int k = 0;
        String objects="";
        //Setup space------------------------------------
        if (cmd.length == 1) {
            sp = new Space();
            Game.getInstance().SetSpace(sp);
            addOutput("Created sp game");
            return;
        }
        else if (cmd.length >2 ){
                if( cmd[2].equals("r")) {
                    k = new Random().nextInt();
                }
                else {
                    k = parseInt(cmd[2]);
                    if (k < 0) {
                        addOutput("Unsuccessful");
                        return;
                    }
                }
        }
        //setup asteroid --------------------------------------
        if (cmd[1].equals("asteroid")) {
            for (int i = 0; i < k; i++) {
                Asteroid a = new Asteroid(sp);
                hash.put("asteroid"+asteroidCnt, a);
                objects+="asteroid"+asteroidCnt+";";
                asteroidCnt++;
            }

        }
        //setup settler --------------------------------------
        else if (cmd[1].equals("settler")) {
            for (int i = 0; i < k; i++) {
                Settler s = new Settler();
                hash.put("settler"+settlerCnt, s);
                objects+="settler"+settlerCnt+";";
                settlerCnt++;
                Game.getInstance().AddSettler(s);
                sp.AddCreature(s);
            }
        }
        //setup ufo --------------------------------------
        else if (cmd[1].equals("ufo")) {
            for (int i = 0; i < k; i++) {
                Ufo u = new Ufo();
                hash.put("ufo"+ufoCnt, u);
                objects+="ufo"+ufoCnt+";";
                ufoCnt++;
                Game.getInstance().AddSteppable(u);
                sp.AddCreature(u);
            }
        }
        //setup robot --------------------------------------
        else if (cmd[1].equals("robot")) {
            for (int i = 0; i < k; i++) {
                Robot r = new Robot();
                hash.put("robot"+robotCnt, r);
                objects+="robot"+robotCnt+";";
                robotCnt++;
                Game.getInstance().AddSteppable(r);
                sp.AddCreature(r);
            }
        }
        //setup gate --------------------------------------
        else if (cmd[1].equals("gate")) {
                Gate g1 = new Gate();
                Gate g2 = new Gate();
                g1.SetPair(g2);
                g2.SetPair(g1);
                hash.put("gate"+gateCnt, g1);
                objects+="gate"+gateCnt+";";
                gateCnt++;
                hash.put("gate"+gateCnt, g2);
                objects+="gate"+gateCnt+";";
                gateCnt++;
        }
        objects = objects.substring(0, objects.length() - 1);
        addOutput("Created "+objects);
    }

    public void Set(String[] cmd) {
        //Set asteroid-----------------------------------------------------------
        if (cmd[1].contains("asteroid")) {
            Asteroid a = (Asteroid) hash.get(cmd[1]);
            if(a==null){
                addOutput("Unsuccessful");
                return;
            }
            Material m = null;
            String str= cmd[2];
            if(str.contains("iron")){
                m = new Iron();
                hash.put("iron"+ironCnt, m);
                ironCnt++;
            }
            else if(str.contains("ice")){
                m = new Ice();
                hash.put("ice"+iceCnt, m);
                iceCnt++;
            }
            else if(str.contains("carbon")){
                m = new Carbon();
                hash.put("carbon"+carbonCnt, m);
                carbonCnt++;
            }
            else if(str.contains("uranium")){
                m = new Uranium();
                hash.put("uranium"+uraniumCnt, m);
                uraniumCnt++;
            } else if (str.equals("-")) {
                m= null;
            } else {
                addOutput("Unsuccessful");
                return;
            }
            a.SetMaterial(m);
            int l =0;
            if(!cmd[3].equals("-")) {
                l = parseInt(cmd[3]);
            }
            if(l<0){
                addOutput("Unsuccessful");
                return;
            }
            a.SetLayer(l);
            if(!(cmd[4].equals("true") || cmd[4].equals("false"))){
                addOutput("Unsuccessful");
                return;
            }
            a.SetCloseToSun(cmd[4].equals("true"));
            if (!cmd[5].equals("-")) {
                String[] neighbours = cmd[5].split(";");
                for (String s : neighbours) {
                    if(s==null){
                        addOutput("Unsuccessful");
                        return;
                    }
                    else {
                        if(!a.GetNeighbours().contains((Transport) hash.get(s)))
                        a.AddNeighbour((Transport) hash.get(s));
                    }
                }
            }
            if (!cmd[6].equals("-")) {
                String[] creatures = cmd[6].split(";");
                for (String n : creatures) {
                    if(n==null){
                        addOutput("Unsuccessful");
                        return;
                    }
                    else {
                        if(!a.GetCreatures().contains((Creature) hash.get(n)))
                            a.AddCreature((Creature) hash.get(n));
                        ((Creature) hash.get(n)).SetAsteroid(a);
                    }
                }
            }
        }
        //set settler------------------------------------------------------------
        else if (cmd[1].contains("settler")) {
            Settler s = (Settler) hash.get(cmd[1]);
            if(s==null){
                addOutput("Unsuccessful");
                return;
            }
            Asteroid a=null;
            if(!cmd[2].equals("-")) {
                 a= (Asteroid) hash.get(cmd[2]);
                if (a == null) {
                    addOutput("Unsuccessful");
                    return;
                }
            }
            s.SetAsteroid(a);
            if(a!=null && !a.GetCreatures().contains(s)) a.AddCreature(s);
            if (!cmd[3].equals("-")) {
                String[] materials = cmd[3].split(";");
                for (String str : materials) {
                    Material m = null;
                    if(str.contains("iron")){
                        m = new Iron();
                        hash.put("iron"+ironCnt, m);
                        ironCnt++;
                    }
                    else if(str.contains("ice")){
                        m = new Ice();
                        hash.put("ice"+iceCnt, m);
                        iceCnt++;
                    }
                    else if(str.contains("carbon")){
                        m = new Carbon();
                        hash.put("carbon"+carbonCnt, m);
                        carbonCnt++;
                    }
                    else if(str.contains("uranium")){
                        m = new Uranium();
                        hash.put("uranium"+uraniumCnt, m);
                        uraniumCnt++;
                    }
                    else{
                        addOutput("Unsuccessful");
                        return;
                    }
                    s.AddMaterial(m);
                }
            }
            if (!cmd[4].equals("-")) {
                String[] gates = cmd[4].split(";");
                for (String str : gates) {
                    Gate g = (Gate) hash.get(str);
                    if(g==null){
                        addOutput("Unsuccessful");
                        return;
                    }
                    s.AddGate(g);
                }
            }
        }
        //set ufo----------------------------------------------------------------
        else if (cmd[1].contains("ufo")) {
            Ufo u = (Ufo) hash.get(cmd[1]);
            if(u==null){
                addOutput("Unsuccessful");
                return;
            }
            u.SetAsteroid((Asteroid) hash.get(cmd[2]));
        }
        //set robot----------------------------------------------------------------
        else if (cmd[1].contains("robot")) {
            Robot r = (Robot) hash.get(cmd[1]);
            if(r==null){
                addOutput("Unsuccessful");
                return;
            }
            r.SetAsteroid((Asteroid) hash.get(cmd[2]));
        }
        //set uranium------------------------------------------------------------
        else if (cmd[1].contains("uranium")) {
            Asteroid a = (Asteroid) hash.get(cmd[2]);
            Uranium u = (Uranium)a.GetMaterial();
            if(u==null || a==null){
                addOutput("Unsuccessful");
                return;
            }
            a.SetMaterial(u);
            u.SetCnt(parseInt(cmd[3]));
        }
    }
    public void Help() {
        System.out.println( "place <settlername> <gatename> \n A megadott settler peldany lehelyezi a megadott nevu gatejet arra az aszteroidara, ahol eppen all.");
        System.out.println("move <creaturename> <asteroidname/r> \n A megadott creature peldany atmegy a megadott aszteroidara. (r betu beirasa eseten random aszteroidara).");
        System.out.println("create <robot/gate> <newinstancename> <settlername> \n Letrehozza a megadott settler peldany a megadott nevu robotot/gatet.");
        System.out.println("mine <settlername/ufoname> \n A megadott settler peldany/ ufo peldany banyaszik.");
        System.out.println("create <robot/gate> <newinstancename> <settlername> \n Letrehozza a megadott settler peldany a megadott nevu robotot/gatet.");
        System.out.println("restore <settlername> <materialtype> \n A megadott nevu settler peldany lehelyez egy megadott tipusu materialt, arra az aszteroidara, amin all.");
        System.out.println("settler properties <settlername> \n A megadott settler peldany aszteroidaja, materiallistaja es gatjei kilistazodnak.");
        System.out.println("asteroid properties <asteroidname> \n A megadott aszteroida peldany anyaga, retege, sunclose tulajdonsaga, szomszedai, es a rajta allo creature-ok kilistazodnak.");
        System.out.println("open <filename> \n Beolvassa a megadott nevu fajlt.");
        System.out.println("check settlers \n Kiirja hany telepes van eletben, majd kilistazza a peldanyok nevet.");
        System.out.println("check base <asteroidname> \n Megnezi hogy van-e eleg anyag a nyereshez az aszteroidan es kiirja mibol hany van.");
        System.out.println("setup <asteroid/settler/ufo> <number/r> \n Letrehoz megadott szamu aszteroidat/telepest/ufot.");
        System.out.println("setup \n Letrehoz egy game es egy space peldanyt.");
        System.out.println("set <asteroidname> <materialtype> <layer> <sunclose> <neighbour1;neighbour2> <creature1;creature2> \n Beallitja a megadott aszteroida peldany parametereit.");
        System.out.println("set <settlername> <asteroidname> <material1;material2;material3> <gate1;gate2;gate3> \n Beallitja a megadott settler peldany parametereit.");
        System.out.println("set <ufoname> <asteroidname> \n Beallitja a megadott ufo peldany parametereit.");
        System.out.println("help \n Kiirja milyen parancsok erhetoek el.");
    }
    public void SolarStorm (String[]cmd){
        Asteroid a = (Asteroid) hash.get(cmd[1]);
        if(a==null){
            addOutput("Unsuccessful");
            return;
        }
        a.SolarStorm(1);
    }

    public void addOutput(String str){
        output.add(str);
    }

    public void printOutput(){
        for (String line: output){
            System.out.println(line);
        }
    }

    public String getKey(Object value){
        for(String k: hash.keySet()){
            if(hash.get(k).equals(value)) return k;
        }
        return null;
    }

}