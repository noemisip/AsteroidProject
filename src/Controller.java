import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Controller {
    private HashMap<String, Object> hash = new HashMap<String, Object>(); //objektumokat tartalmazo hashmap
    private Space sp; //jatek szintereul szolgalo space
    //az alabbi szamlalok az egyes objektumok elnevezeseert felelosek
    private int settlerCnt =1;      //settlerek szama
    private int gateCnt = 1;        //kapuk szama 
    private int asteroidCnt = 1;    //aszteroidak szama
    private int ufoCnt =1;          //ufok szama
   private int uraniumCnt =1;       //uraniumok szama
    private int ironCnt =1;         //vasak szama
    private int iceCnt =1;          //jegek szama
    private int carbonCnt =1;       //szenek szama
    private int robotCnt =1;        //robotok szama
    private ArrayList<String> output = new ArrayList<String>();         //a tesztek vegeredmenye, ez jelenik meg a kimeneten
    private ArrayList<String> ideal_output=new ArrayList<String>();     //elvart kimenet
    private ArrayList<String> input = new ArrayList<String>();          //teszteset bemenete

    public void Menu(){
        Scanner s = new Scanner(System.in);

        while (s.hasNextLine()) {
            String in=s.nextLine();
            settlerCnt =1;
            gateCnt = 1;
            asteroidCnt = 1;
            ufoCnt =1;
            uraniumCnt =1;
            ironCnt =1;
            iceCnt =1;
            carbonCnt =1;
            robotCnt =1;

            if(in.equals("Test")){ //elore megirt tesztek futtatasa
                Scanner i = new Scanner(System.in);
                String[] cmd = {""}; output.clear();ideal_output.clear();input.clear();
                String text=i.nextLine();cmd = text.split(" ");
                if(cmd[0].equals("open")) Open(cmd[1]);
            }
            else if(in.equals("Game: A program a sor vege jelig olvassa a parancsokat, majd kiirja a kimenetet")){ //olvasas konzolrol, egyeni parancsok futattasa
                ReadFromConsole();
            }

        }
    }


    public void ReadFromInput() { //olvasas filebol
        String[] cmd = {""};
       for(int i=0;i<input.size();i++) {
           String line = input.get(i);
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
        }printOutput();
        boolean good=true;
        if(output.size()==ideal_output.size())
        for(int i=0;i<output.size();i++){
            if(!(output.get(i).equals(ideal_output.get(i)))) good=false;
        }
        if(good) System.out.println("Successful test");
        else System.out.println("Unsuccessful test");
        hash.clear();
        Game.getInstance().GetSettlers().clear();
    }

    public void ReadFromConsole() { //egyeni tesztek/parancsok futtatasa konzolrol
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
        hash.clear();
        Game.getInstance().GetSettlers().clear();
    }

    public void Place(String[] cmd) { //egy kapu lehelyezese
        Settler s = (Settler) hash.get(cmd[1]);
        Gate g = (Gate) hash.get(cmd[2]);
        if(s==null || g==null) { //ha a kapu vagy a telepes nem letezik, akkor sikertelen a parancs futasa
            addOutput("Unsuccessful");
            return;
        }
        s.PlaceGate(g);
    }

    public void Kerge_Gate(String[] cmd) { //kapu keregere allitasa
        Gate g = (Gate) hash.get(cmd[1]);
        if(g==null) { //ha a kapu nem letezik, akkor sikertelen a aprancs futasa
            addOutput("Unsuccessful");
            return;
        }
        g.SetKerge(true);
    }

    public void Move(String[] cmd) { //egy creature/kapu mozgatasa
        Asteroid a ;
        if(cmd[1].contains("gate")){ //kapu mozog
            if(!(cmd[2].equals("-"))){
                Gate c = (Gate) hash.get(cmd[1]);
                a= (Asteroid) hash.get(cmd[2]);
                if(c==null || a==null) { //ha a kapu, vagy az aszteroida nem letezik, akkor sikertelen a parancs futtatasa
                    addOutput("Unsuccessful");
                    return;
                }
                else c.Move(a); //elore kivalasztott aszteroidara mozog
            }
            else{
                AI ai=(AI) hash.get(cmd[1]); //random aszteroidara mozog
                ai.WhereToMove();
            }
        }
        else if(cmd[1].contains("robot") || cmd[1].contains("ufo")){ //robot vagy ufo mozog
            if(!(cmd[2].equals("-"))){
                Creature c = (Creature) hash.get(cmd[1]);
                a= (Asteroid) hash.get(cmd[2]);
                if(c==null || a==null) { //ha a creature vagy az aszteroida nem letezik, akkor sikertelen a parancs futtatasa
                    addOutput("Unsuccessful");
                    return;
                }
                else c.Move(a);                 //elore kivalasztott aszteroidara mozog
            }
            else{
                AI ai=(AI) hash.get(cmd[1]);    //random aszteroidara mozog
                ai.WhereToMove();
            }
        }
        if(cmd[1].contains("settler")) {    //telepes mozog
            Settler s = (Settler) hash.get(cmd[1]);

            a = (Asteroid) hash.get(cmd[2]);
            if (s == null || a == null) { //ha a telepes vagy az aszteroida nem letezik, akkor sikertelen a parancs futtatasa
                addOutput("Unsuccessful");
                    return;
            }
            s.Move(a);
        }
    }

    public void Drill(String[] cmd) { //egy creature (robot, vagy settler) fur
        Creature s = (Creature) hash.get(cmd[1]);
        if(s==null){ //ha a creature nem letezik, akkor sikertelen a parancs futtatasa
            addOutput("Unsuccessful");
            return;
        }
        s.Drill();
    }

    public void Mine(String[] cmd) { //egy ufo vagy telepes banyaszik
        if(cmd[1].contains("ufo")){
            Ufo u = (Ufo) hash.get(cmd[1]);
            if(u==null){ //ha az ufo nem letezik, akkor sikertelen a parancs futtatasa
                addOutput("Unsuccessful");
                return;
            }
            u.Mine();
        }
        else if(cmd[1].contains("settler")) {
            Settler s = (Settler) hash.get(cmd[1]);
            if (s == null) { //ha a telepes nem letezik, akkor sikertelen a parancs futtatasa
                addOutput("Unsuccessful");
                return;
            }
            s.Mine();
        }
        else addOutput("Unsuccessful"); //ha hibas a bemenet, akkor sikertelen a parancs futtatasa
    }

    public void Create(String[] cmd) { //a telepes keszit egy robotot vagy kaput
        if (cmd[1].equals("robot")) { //a telepes robotot akar kesziteni
            Settler s = (Settler) hash.get(cmd[3]);
            if(s==null){        //ha a telepes nem letezik, akkor sikertelen a parancs futtatasa
                addOutput("Unsuccessful");
                return;
            }
            int num = s.GetAsteroid().GetCreatures().size(); //megjegyzi, hogy kezdetben hany creature van az aszteroidan
            s.CreateRobot(); //a telepes egy robotot csinal
            if(s.GetAsteroid().GetCreatures().size()>num) //ha tobb creature lett az aszteroidan (sikeres volt az epites), akkor felveszi az ujat a hashmapbe (creature lista vegen levo objektum)
                hash.put(cmd[2], s.GetAsteroid().GetCreatures().get(s.GetAsteroid().GetCreatures().size()-1));
        } else if (cmd[1].equals("gate")) { //a telepes egy kapupart csinal
            Settler s = (Settler) hash.get(cmd[4]);
            if(s==null){        //ha a telepes nem letezik, akkor sikertelen a parancs futtatasa
                addOutput("Unsuccessful");
                return;
            }
            int num = s.GetGateList().size(); //megjegyzi, hogy hany kapu van a telepesnel
            s.CreateGate(); //a telepes kaput keszit
            if(s.GetGateList().size()>num) { //ha tobb kapu lett a telepesnel, azaz sikeres az epites, akkor felveszi a kapukat a hashmapbe
                hash.put(cmd[2], s.GetGate(0));
                hash.put(cmd[3], s.GetGate(1));
            }
        }
        else{
            addOutput("Unsuccessful"); //ha hibas a bemenet, akkor sikertelen a parancs futtatasa
        }
    }

    public void Restore(String[] cmd) { //a telepes lehelyez egy nala levo nyersanyagot arra az aszteroidara amin epp all
        Settler s = (Settler) hash.get(cmd[1]);
        if(s==null){    //ha a telepes nem letezik, akkor sikertelen a parancs futtatasa
            addOutput("Unsuccessful");
            return;
        }
        Material m = null;
        switch (cmd[2]) { //eldonti milyen tipusu nyersanyagot helyez vissza az aszteroidaba
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

    public void Settler_Properties(String[] cmd) { //kiirja a telepes adatait
        Settler s = (Settler) hash.get(cmd[2]);
        if(s==null){        //ha a telepes nem letezik, akkor sikertelen a parancs futtatasa
            addOutput("Unsuccessful");
            return;
        }
        //telepesnel levo nyersanyagok listazasa
        String materials="";
        if(s.GetMaterials().size()==0) materials="-";
        else {
            for (Material m : s.GetMaterials()) {
                materials += getKey(m) + ";";
            }
            materials = materials.substring(0, materials.length() - 1);
        }
        //telepesnel levo kapuk listazasa
        String gates="";
        if(s.GetGateList().size()==0) gates="-";
        else {
            for (Gate g : s.GetGateList()) {
                gates += getKey(g) + ";";
            }
            gates = gates.substring(0, gates.length() - 1);
        }
        //a telepes aszteroidajanak kiirasa
        String asteroid;
        if(s.GetAsteroid()==null) asteroid="-";
        else asteroid=getKey(s.GetAsteroid());
        //kimeneti string osszeallitasa
        addOutput(cmd[2]+": "+ asteroid+" " + materials + " " + gates );
    }

    public void Asteroid_Properties(String[] cmd) { //aszteroida adatainak listazasa
        Asteroid a = (Asteroid) hash.get(cmd[2]);
        if(a==null){ //ha az aszteroida nem letezik, akkor sikertelen a parancs futtatasa
            addOutput("Unsuccessful");
            return;
        }
        //aszteroida nyersanyaganak kiirasa
        String material = "";
        if(a.GetMaterial()==null) material="-";
        else material = getKey(a.GetMaterial());
        //aszteroida szomszedainak listazasa
        String neighbours="";
        if(a.GetNeighbours()==null || a.GetNeighbours().size()==0) neighbours="-"; //szomszedok kiirasa
        else {
            for (Transport t : a.GetNeighbours()) {
                neighbours += getKey(t) + ";";
            }
            neighbours = neighbours.substring(0, neighbours.length() - 1);
        }
        //aszteroidan levo lenyek listazasa
        String creatures="";
        if(a.GetCreatures().size()==0) creatures="-";
        else {
            for (Creature c : a.GetCreatures()) {
                creatures += getKey(c) + ";";
            }
            creatures = creatures.substring(0, creatures.length() - 1);
        }
        //kiemneti string osszeallitasa
        addOutput(cmd[2]+": "+ material+" " +a.GetLayer()+" "+a.GetCloseToSun()+" "+ neighbours + " " + creatures );
    }

    public void Check(String[] cmd) { //ellenorzesek
        if (cmd[1].equals("settlers")) { //telepesek szamanak ellenorzese
            if(Game.getInstance().GetSettlers().size()==0) {
                addOutput("0");
            }
            else { //ha meg van eletben telepes kiirja, hogy melyikek azok
                String settlers="";
                for (Settler s : Game.getInstance().GetSettlers()) {
                    settlers += getKey(s) + ";";
                }
                settlers = settlers.substring(0, settlers.length() - 1);
                addOutput(Game.getInstance().GetSettlers().size() + " " + settlers);
            }
        }

        else if (cmd[1].equals("base")) { //ellenorzi, hogy fel tudjak-e epiteni az aszteroidan a bazist, nyertek-e a telepesek
            Asteroid a = (Asteroid) hash.get(cmd[2]);
            if(a==null){    //ha az aszteroida nem letezik, akkor sikertelen a parancs futtatasa
                addOutput("Unsuccessful");
                return;
            }
            Game.getInstance().CheckBase(a);
            addOutput(Game.getInstance().GetOnGame()? "": "Win");
        }
    }

    public void Setup(String[] cmd) { //objektumok letrehozasa
        int k = 0;
        String objects="";
        //Setup space------------------------------------
        if (cmd.length == 1) { //letrehozza a jatek szinteret
            sp = new Space();
            Game.getInstance().SetSpace(sp);
            addOutput("Created sp game");
            return;
        }
        else if (cmd.length >2 ){
                if( cmd[2].equals("r")) { //ha nincs megadva, hogy hany objektumot hozzon letre, random szamut fog
                    k = new Random().nextInt();
                }
                else { //megadott szamu objektum letrehozasa
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
                hash.put("asteroid"+asteroidCnt, a); //felveszi a hashmapbe
                objects+="asteroid"+asteroidCnt+";";
                asteroidCnt++;
            }

        }
        //setup settler --------------------------------------
        else if (cmd[1].equals("settler")) {
            for (int i = 0; i < k; i++) {
                Settler s = new Settler();
                hash.put("settler"+settlerCnt, s); //felveszi a hasmapbe
                objects+="settler"+settlerCnt+";";
                settlerCnt++;
                Game.getInstance().AddSettler(s);   //hozzaadja a game settler listajahoz
                sp.AddCreature(s);                  //hozzaadja a space creature listajahoz
            }
        }
        //setup ufo --------------------------------------
        else if (cmd[1].equals("ufo")) {
            for (int i = 0; i < k; i++) {
                Ufo u = new Ufo();
                hash.put("ufo"+ufoCnt, u);  //felveszi a hashmapbe
                objects+="ufo"+ufoCnt+";";
                ufoCnt++;
                Game.getInstance().AddSteppable(u); //hozzaadja a game steppable listajahoz
                sp.AddCreature(u);                  //hozzaadja a space creature listajahoz
            }
        }
        //setup robot --------------------------------------
        else if (cmd[1].equals("robot")) {
            for (int i = 0; i < k; i++) {
                Robot r = new Robot();
                hash.put("robot"+robotCnt, r); //felveszi a hashmapbe
                objects+="robot"+robotCnt+";";
                robotCnt++;
                Game.getInstance().AddSteppable(r); //hozzaadja a game steppable listajahoz
                sp.AddCreature(r);                  //hozzaadja a space creature listajahoz
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
            Material m ; //beallitja az aszteroidaban talalhato nyersanyagot
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
                m=a.GetMaterial();
            } else {
                addOutput("Unsuccessful");
                return;
            }
            a.SetMaterial(m);
            int l =0; //beallitja az aszteroida kergenek vastagsagat
            if(!cmd[3].equals("-")) {
                l = parseInt(cmd[3]);
            }
            if(l<0){
                addOutput("Unsuccessful");
                return;
            }
            a.SetLayer(l);
            if(!(cmd[4].equals("true") || cmd[4].equals("false"))){ //beallitja, hogy napkozelben van-e az aszteroida
                addOutput("Unsuccessful");
                return;
            }
            a.SetCloseToSun(cmd[4].equals("true"));
            if (!cmd[5].equals("-")) { //szomszedok hozzaadasa----------------------
                String[] neighbours = cmd[5].split(";");
                for (String s : neighbours) {
                    if(s==null){
                        addOutput("Unsuccessful");
                        return;
                    }
                    else {
                        if(a.GetNeighbours()!=null &&!a.GetNeighbours().contains((Transport) hash.get(s)))
                        a.AddNeighbour((Transport) hash.get(s));
                        if(s.contains("gate")){
                            Gate g = (Gate) hash.get(s);
                            g.GetPair().SetAsteroid(a);
                        }
                    }
                }
            }
            if (!cmd[6].equals("-")) {
                String[] creatures = cmd[6].split(";");
                for (String n : creatures) {
                    if((Creature) hash.get(n)==null){
                        addOutput("Unsuccessful");
                        return;
                    }
                    else {
                        if(!a.GetCreatures().contains((Creature) hash.get(n))) {
                            a.AddCreature((Creature) hash.get(n));
                            ((Creature) hash.get(n)).SetAsteroid(a);
                        }
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
            //u beallitasa
            Ufo u = (Ufo) hash.get(cmd[1]);
            //ha u nem letezik
            if(u==null){
                addOutput("Unsuccessful");
                return;
            }
            //u jelenlegi aszteroidajanak beallitasa
            u.SetAsteroid((Asteroid) hash.get(cmd[2]));
        }
        //set robot----------------------------------------------------------------
        else if (cmd[1].contains("robot")) {
            // r beallitasa
            Robot r = (Robot) hash.get(cmd[1]);
            //ha nem leteznek
            if(r==null){
                addOutput("Unsuccessful");
                return;
            }
            //r jelenlegi aszteroidajanak beallitasa
            r.SetAsteroid((Asteroid) hash.get(cmd[2]));
        }
        //set uranium------------------------------------------------------------
        else if (cmd[1].contains("uranium")) {
            //a es u beallitasa
            Asteroid a = (Asteroid) hash.get(cmd[2]);
            Uranium u = (Uranium)a.GetMaterial();
            //ha nem leteznek
            if(u==null || a==null){
                addOutput("Unsuccessful");
                return;
            }
            //material es cnt beallitasa
            a.SetMaterial(u); u.SetCnt(parseInt(cmd[3]));
        }
    }
    public void Help() { //utmutato a parancsok hasznalatahoz
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
    //Napvihar parancs
    public void SolarStorm (String[]cmd){
        //melyik aszteroidan
        //if(!cmd[1].equals("r");
           Asteroid a = (Asteroid) hash.get(cmd[1]);
         //else {int rand=new Random().NextInt(space.GetAsteroid().size()); Asteroid a=(Asteroid) hash.get(}
        if(a==null){
            addOutput("Unsuccessful");
            return;
        }
        a.SolarStorm(1);
    }

    public void addOutput(String str){
        //a parameterben kapott string hozzafuzese az output-hoz
        output.add(str);
    }

    public void printOutput(){
        //output kiirasa a console-ra
        for (String line: output){
            System.out.println(line);
        }
    }

    public String getKey(Object value){
        //HashMap key lekerese
        for(String k: hash.keySet()){
            if(hash.get(k).equals(value)) return k;
        }
        return null;
    }

    public void Open(String cmd)
    {
        try
        {
           //egesz teszteset beolvasasa az inputba
            FileReader fr=new FileReader(cmd);
            BufferedReader br=new BufferedReader(fr);
            StringBuffer sb=new StringBuffer();
            String line;
            while((line=br.readLine())!=null)
            {
                input.add(line); //inputhoz hozzaadja a sort
            }
            fr.close();
            //input szetvalasztasa (eleje input,*,vege ideal_output)
            for(int i=0;i<input.size();i++){
                if(input.get(i).equals("*")){
                    input.remove(i); //torli a csillagot
                    int j=0;
                    while(input.size()!=i){
                        ideal_output.add(input.get(i)); //ideal_outputhoz hozzaadja a sort
                        input.remove(i); //inputbol torli a sort
                    }
                }
            }
            ReadFromInput(); //teszteset futtatasa
        }
        catch(IOException e)
        {
            //hibák kezelese
            e.printStackTrace();
        }
    }

}
