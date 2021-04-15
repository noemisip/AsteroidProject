import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Controller {
	static  int tab;
	static boolean inTest;
	private HashMap<String, Object> hash = new HashMap<String, Object>();


	public void ReadFromConsole() {
		Scanner s = new Scanner(System.in);
		String[] cmd = {""};
		while(s.hasNextLine()) {
			String line = s.nextLine();
			if(!line.isEmpty()) cmd=line.split(",");
		}
		switch (cmd[0]){
			case "place" :
				Place(cmd);
				break;
			case "kerge" :
				Kerge_Gate(cmd);
				break;
			case "move" :
				Move(cmd);
				break;
			case "drill" :
				Drill(cmd);
				break;
			case "mine" :
				Mine(cmd);
				break;
			case "create" :
				Create(cmd);
				break;
			case "restore" :
				Restore(cmd);
				break;
			case "settler" :
				Settler_Properties(cmd);
				break;
			case "asteroid" :
				Asteroid_Properties(cmd);
				break;
			case "open" :
				Open(cmd);
				break;
			case "check" :
				Check(cmd);
				break;
			case "setup" :
				Setup(cmd);
				break;
			case "set" :
				Set(cmd);
				break;
			case "help" :
				Help();
				break;
			case "SolarStorm" :
				SolarStorm(cmd);
				break;
			default : break;
		}

	}
	public void Place(String[] cmd){
		Settler s = (Settler) hash.get(cmd[1]);
		Gate g = (Gate) hash.get(cmd[2]);
		s.PlaceGate(g);
	}
	public void Kerge_Gate(String[] cmd){ //TODO
		Gate g = (Gate) hash.get(cmd[1]);
	}
	public void Move(String[] cmd){
		Creature c = (Creature) hash.get(cmd[1]);
		c.Move((Transport) hash.get(cmd[2]));
	}
	public void Drill(String[] cmd){
		Settler s = (Settler) hash.get(cmd[1]);
		s.Drill();
	}
	public void Mine(String[] cmd){
		Settler s = (Settler) hash.get(cmd[1]);
		s.Mine();
	}
	public void Create(String[] cmd){
		if(cmd[1].equals("robot")){
			Settler s = (Settler) hash.get(cmd[2]);
			s.CreateRobot();
		}
		else if(cmd[1].equals("gate")){
			Settler s = (Settler) hash.get(cmd[3]);
			s.CreateGate();
			hash.put(cmd[1], s.GetGate(0));
			hash.put(cmd[2], s.GetGate(1));
		}
	}
	public void Restore(String[] cmd){
		Settler s = (Settler) hash.get(cmd[1]);
		Material m = null;
		switch (cmd[2]){
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
			default:break;
		}
		s.RemoveMaterial(m);
	}
	public void Settler_Properties(String[] cmd){
		Settler s = (Settler) hash.get(cmd[2]);
	}
	public void Asteroid_Properties(String[] cmd){
		Asteroid a = (Asteroid) hash.get(cmd[2]);
	}
	public void Open(String[] cmd){

	}
	public void Check(String[] cmd){
		if(cmd[1].equals("settlers")){
			Game.getInstance().CheckSettlers();
		}
		else if(cmd[1].equals("base")){
			Asteroid a = (Asteroid) hash.get(cmd[2]);
			Game.getInstance().CheckBase(a);
		}
	}
	public void Setup(String[] cmd){
		if(cmd.length==1){
			Space sp = new Space();
			Game.getInstance().SetSpace(sp);
		}
		else if(cmd[1].equals("asteroid")){}
		else if(cmd[1].equals("settler")){}
		else if(cmd[1].equals("ufo")){}
		else if(cmd[1].equals("gate")){}
	}
	public void Set(String[] cmd){

	}
	public void Help(){

	}
	public void SolarStorm(String[] cmd){

	}

}