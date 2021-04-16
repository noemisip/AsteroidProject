import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Controller {
    private HashMap<String, Object> hash = new HashMap<String, Object>();
    private int settlerCnt =0;
    private int gateCnt = 0;
    private int asteroidCnt = 0;
    private int ufoCnt =0;

    public void ReadFromConsole() {
        Scanner s = new Scanner(System.in);
        String[] cmd = {""};
        while (s.hasNextLine()) {
            String line = s.nextLine();
            if (!line.isEmpty()) cmd = line.split(" ");
        }
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

    public void Place(String[] cmd) {
        Settler s = (Settler) hash.get(cmd[1]);
        Gate g = (Gate) hash.get(cmd[2]);
        s.PlaceGate(g);
    }

    public void Kerge_Gate(String[] cmd) { //TODO
        Gate g = (Gate) hash.get(cmd[1]);
    }

    public void Move(String[] cmd) {
        Creature c = (Creature) hash.get(cmd[1]);
        c.Move((Asteroid) hash.get(cmd[2]));
    }

    public void Drill(String[] cmd) {
        Settler s = (Settler) hash.get(cmd[1]);
        s.Drill();
    }

    public void Mine(String[] cmd) {
        Settler s = (Settler) hash.get(cmd[1]);
        s.Mine();
    }

    public void Create(String[] cmd) {
        if (cmd[1].equals("robot")) {
            Settler s = (Settler) hash.get(cmd[2]);
            s.CreateRobot();
        } else if (cmd[1].equals("gate")) {
            Settler s = (Settler) hash.get(cmd[3]);
            s.CreateGate();
            hash.put(cmd[1], s.GetGate(0));
            hash.put(cmd[2], s.GetGate(1));
        }
    }

    public void Restore(String[] cmd) {
        Settler s = (Settler) hash.get(cmd[1]);
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
        s.RemoveMaterial(m);
    }

    public void Settler_Properties(String[] cmd) {
        Settler s = (Settler) hash.get(cmd[2]);
    }

    public void Asteroid_Properties(String[] cmd) {
        Asteroid a = (Asteroid) hash.get(cmd[2]);
    }

    public void Open(String[] cmd) {

    }

    public void Check(String[] cmd) {
        if (cmd[1].equals("settlers")) {
            Game.getInstance().CheckSettlers();
        } else if (cmd[1].equals("base")) {
            Asteroid a = (Asteroid) hash.get(cmd[2]);
            Game.getInstance().CheckBase(a);
        }
    }

    public void Setup(String[] cmd) {
        int k = 0;
        if (cmd.length == 1) {
            Space sp = new Space();
            Game.getInstance().SetSpace(sp);
            return;
        } else if (cmd[2].equals("r")) {

        } else {
            k = parseInt(cmd[2]);
        }
        if (cmd[1].equals("asteroid")) {
            for (int i = 0; i < k; i++) {
                Asteroid a = new Asteroid();
                hash.put("a"+asteroidCnt, a);
                asteroidCnt++;
            }
        } else if (cmd[1].equals("settler")) {
            for (int i = 0; i < k; i++) {
                Settler s = new Settler();
                hash.put("s"+settlerCnt, s);
                settlerCnt++;
            }
        } else if (cmd[1].equals("ufo")) {
            for (int i = 0; i < k; i++) {
                Ufo u = new Ufo();
                hash.put("u"+ufoCnt, u);
                ufoCnt++;
            }
        } else if (cmd[1].equals("gate")) {
            for (int i = 0; i < k; i++) {
                Gate g1 = new Gate();
                Gate g2 = new Gate();
                g1.SetPair(g2);
                g2.SetPair(g1);
                hash.put("g"+gateCnt, g1);
                gateCnt++;
                hash.put("g"+gateCnt, g2);
                gateCnt++;
            }
        }
    }

    public void Set(String[] cmd) {
        if (cmd[1].contains("asteroid")) {
            Asteroid a = (Asteroid) hash.get(cmd[1]);
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
            a.SetMaterial(m);
            a.SetLayer(parseInt(cmd[3]));
            a.SetCloseToSun(cmd[4].equals("true"));
            if (!cmd[5].equals("-")) {
                String[] creatures = cmd[5].split(";");
                for (String s : creatures) {
                    a.AddCreature((Creature) hash.get(s));
                }
            }
            if (!cmd[6].equals("-")) {
                String[] neighbours = cmd[6].split(";");
                for (String s : neighbours) {
                    a.AddCreature((Creature) hash.get(s));
                }
            }
        } else if (cmd[1].contains("settler")) {
            Settler s = (Settler) hash.get(cmd[1]);
            s.SetAsteroid((Asteroid) hash.get(cmd[2]));
            if (!cmd[3].equals("-")) {
                String[] materials = cmd[3].split(";");
                for (String str : materials) {
                    s.AddMaterial((Material) hash.get(str));
                }
            }
            if (!cmd[4].equals("-")) {
                String[] gates = cmd[4].split(";");
                for (String str : gates) {
                    s.AddGate((Gate) hash.get(str));
                }
            }
        }
        else if (cmd[1].contains("ufo")) {
            Ufo u = (Ufo) hash.get(cmd[1]);
            u.SetAsteroid((Asteroid) hash.get(cmd[2]));
        }
        else if (cmd[1].contains("uranium")) {
            Uranium u = (Uranium)hash.get(cmd[1]);
            Asteroid a = (Asteroid) hash.get(cmd[2]);
            a.SetMaterial(u);
            u.SetCnt(parseInt(cmd[3]));
        }
    }
    public void Help() {

    }
    public void SolarStorm (String[]cmd){
        Asteroid a = (Asteroid) hash.get(cmd[1]);
        a.SolarStorm(1);
    }

}