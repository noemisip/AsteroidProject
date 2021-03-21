import java.util.Scanner;

public class Controller {
	static  int tab;
	static boolean inTest;

	public boolean menu() {
		boolean test = true;
		System.out.println("1. Settler drills");
		System.out.println("2. Ice sublimates in sunclose");
		System.out.println("3. Robot chooses to drill");
		System.out.println("4. Settler restores");
		System.out.println("5. Robot cannot move and dies");
		System.out.println("6. Settler moves with gate and check base");
		System.out.println("7. Settler moves to neighbour asteroid and check base");
		System.out.println("8. Create Robot");
		System.out.println("9. Create Gate");
		System.out.println("10. Place Gate and Remove from Settler");
		System.out.println("11. Robot moves with gate");
		System.out.println("12. Robot moves to neighbour asteroid");
		System.out.println("13. Settler mines");
		System.out.println("14. SolarStorm on an asteroid where Robot and Settler can hide");
		System.out.println("15. SolarStorm hits Settler and Robot");
		System.out.println("16. Asteroid Explosion and Game Over");
		System.out.println("17. Exit");

		int  x = new Scanner(System.in).nextInt();

		switch(x){
			case 1: SettlerDrills();
					break;
			case 2: IceSublimateInSunclose(); 
					break;
			case 3: RobotChoosesToDrill();
					break;
			case 4: SettlerRestores();
					break;
			case 5: RobotCannotMoveAndDies();
					break;
			case 6: SettlerMovesWithGate();
					break;
			case 7:SettlerMovesNeighbourAsteroid();
					break;
			case 8: CreateRobot();
					break;
			case 9: CreateGate();
					break;
			case 10: PlaceGateandRemovefromSettler();
					break;
			case 11: RobotMovesWithGate();
					break;
			case 12: RobotMovesNeighbourAsteroid();
					break;
			case 13: SettlerMines();
					break;
			case 14: SolarStormInCaseWhenCreaturesCanHide();
					break;
			case 15: SolarStormHitsSettlerAsteroid();
					break;
			case 16: AsteroidExplosionandGameOver();
						break;
			case 17: test=false;
					break;
			default: break;
		}
		return test;
	}


	public void SettlerDrills(){
		// objektumok inicializalasa, palya felepitese
		Asteroid a = new Asteroid();
		Settler s = new Settler();
		Ice i = new Ice();
		a.SetMaterial(i);
		a.AddCreature(s);
		s.SetAsteroid(a);

		// A teszteset elinditasa a s Settlerre meghivott Drill fuggvennyel
		System.out.println("Settler drills: ");
		tab = 0;
		inTest=true;
		s.Drill();
		System.out.println("-----Teszteset vege----");
		inTest=false;
	}
	public void IceSublimateInSunclose(){
		// objektumok inicializalasa, palya felepitese
		Asteroid a = new Asteroid();
		Ice i = new Ice();
		a.SetMaterial(i);

		// A teszteset elinditasa az a Asteroidara meghivott Step fuggvennyel
		System.out.println("Ice sublimates in sunclose: ");
		tab = 0;
		inTest=true;
		a.Step();
		System.out.println("-----Teszteset vege----");
		inTest=false;
	}
	public void RobotChoosesToDrill(){
		// objektumok inicializalasa, palya felepitese
		Asteroid a = new Asteroid();
		Robot r = new Robot();
		Ice i = new Ice();
		a.SetMaterial(i);
		a.AddCreature(r);
		r.SetAsteroid(a);

		// A teszteset elinditasa a Robotra meghivott Step fuggvennyel
		System.out.println("Robot chooses To Drill: ");
		tab = 0;
		inTest=true;
		r.Step();
		System.out.println("-----Teszteset vege----");
		inTest=false;
	}
	public void SettlerRestores(){
		// objektumok inicializalasa, palya felepitese
		Asteroid a = new Asteroid();
		Settler s = new Settler();
		Ice i = new Ice();
		a.SetMaterial(null);
		a.AddCreature(s);
		s.AddMaterial(i);
		s.SetAsteroid(a);

		// A teszteset elinditasa a s Settlerre meghivott RestoreMaterial(i) fuggvennyel
		System.out.println("Settler restores: ");
		tab = 0;
		inTest=true;
		s.RestoreMaterial(i);
		System.out.println("-----Teszteset vege----");
		inTest=false;

	}
	public void SettlerMovesWithGate(){
		//A teszthez szukseges objektumok letrehozasa
		Asteroid a1 = new Asteroid(); //1. aszteroida, itt all kezdetben a telepes
		Asteroid a2 = new Asteroid(); //2.a szteroida, ide utazik at a telepes
		Gate g1 = new Gate(); //az elso aszteroidan talalhato teleportkapu
		Gate g2 = new Gate(); //a masodik aszteroidan talalhato teleportkapu
		Settler s = new Settler(); //a telepes peldanya
		Ice i = new Ice(); //a telepsnel levo nyersanyag

		//teleportkapu-par elhelyezese az aszteroidakon + parjaik beallitasa
		g1.SetPair(g2);
		g2.SetPair(g1);
		g1.SetAsteroid(a1);
		g2.SetAsteroid(a2);
		a1.AddNeighbour(g1);
		a2.AddNeighbour(g2);

		//a settler peldany elhelyezese az a1 aszteroidan
		a1.AddCreature(s);
		s.SetAsteroid(a1);
		s.AddMaterial(i);

		//Game osztalyban valo elhelyezes, a kesobbi bazis ellenorzes erdekeben
		Game.getInstance().AddSettler(s);
		Game.getInstance().AddSteppable(a1);
		Game.getInstance().AddSteppable(a2);

		//A teszteset elinditasa a settler osztaly Move() függgvényének segitsegevel
		System.out.println("Settler moves with gate and check base:");
		tab = 0;
		inTest=true;
		s.Move(a2);
		System.out.println("-----Teszteset vege----");
		inTest=false;

	}
	public void SettlerMovesNeighbourAsteroid(){
		//A teszthez szukseges objektumok letrehozása
		Asteroid a1 = new Asteroid(); //1. aszteroida, itt all kezdetben a telepes
		Asteroid a2 = new Asteroid(); //2.a szteroida, ide utazik at a telepes
		Settler s = new Settler(); //a telepes peldanya
		Ice i = new Ice(); //a telepsnel levo nyersanyag

		//a settler peldany elhelyezese az a1 aszteroidan
		a1.AddCreature(s);
		s.SetAsteroid(a1);
		s.AddMaterial(i);

		//Game osztalyban valq elhelyezes, a kesobbi bazis ellenorzes erdekeben
		Game.getInstance().AddSettler(s);
		Game.getInstance().AddSteppable(a1);
		Game.getInstance().AddSteppable(a2);

		//A teszteset elinditasa a settler osztaly Move() fugggvenyenek segitsegevel
		System.out.println("Settler moves to neighbour asteroid and check base:");
		tab = 0;
		inTest=true;
		s.Move(a2);
		System.out.println("-----Teszteset vege----");
		inTest=false;

	}
	public void RobotMovesWithGate(){
		//A teszthez szukseges objektumok letrehozása
		Asteroid a1 = new Asteroid(); //1. aszteroida, itt all kezdetben a telepes
		Asteroid a2 = new Asteroid(); //2.a szteroida, ide utazik at a telepes
		Gate g1 = new Gate(); //az elso aszteroidan talalhato teleportkapu
		Gate g2 = new Gate(); //a masodik aszteroidan talalhato teleportkapu
		Robot r = new Robot(); //robot peldanya

		//teleportkapu-par elhelyezese az aszteroidakon + parjaik beallitasa
		g1.SetPair(g2);
		g2.SetPair(g1);
		g1.SetAsteroid(a1);
		g2.SetAsteroid(a2);
		a1.AddNeighbour(g1);
		a2.AddNeighbour(g2);

		//a robot peldany elhelyezese az a1 aszteroidan
		a1.AddCreature(r);
		r.SetAsteroid(a1);

		//A teszteset elinditasa a settler osztaly Move() függgvenyenek segitsegevel
		System.out.println("Robot moves with gate:");
		tab = 0;
		inTest=true;
		r.Move(a2);
		System.out.println("-----Teszteset vege----");
		inTest=false;

	}
	public void RobotMovesNeighbourAsteroid(){
		//A teszthez szukseges objektumok letrehozása
		Asteroid a1 = new Asteroid(); //1. aszteroida, itt all kezdetben a robot
		Asteroid a2 = new Asteroid(); //2.a szteroida, ide utazik at a robot
		Robot r = new Robot(); //robot peldanya

		//a robot peldany elhelyezése az a1 aszteroidan
		a1.AddCreature(r);
		r.SetAsteroid(a1);

		//A teszteset elinditasa a settler osztaly Move() fugggvenyenek segitsegevel
		System.out.println("Robot moves to neighbour asteroid:");
		tab = 0;
		inTest=true;
		r.Move(a2);
		System.out.println("-----Teszteset vege----");
		inTest=false;
	}
	public void SolarStormHitsSettlerAsteroid() {
		//A palya felepitese a tesztesethez
		Settler s=new Settler();
		Space sp=new Space();
		Robot r=new Robot();
		Asteroid a=new Asteroid();
		Settler s2=new Settler();
		Asteroid a2=new Asteroid();
		Iron i=new Iron();

		//s settler es r robot elhelyezese az a jeg magu aszteroidan
		a.SetMaterial(i);
		a.AddCreature(s);
		a.AddCreature(r);
		s.SetAsteroid(a);
		r.SetAsteroid(a);
		a.SetSpace(sp);

		//Az objektumok urbe helyezese
		sp.AddAsteroid(a);
		sp.AddAsteroid(a2);
		sp.AddCreature(s);
		sp.AddCreature(s2);
		sp.AddCreature(r);

		//s2 settler elhelyezese az a2 ures aszteroidan
		a2.AddCreature(s2);
		s2.SetAsteroid(a2);
		a2.SetMaterial(null);

		Game.getInstance().AddSteppable(a);
		Game.getInstance().AddSteppable(a2);
		Game.getInstance().AddSteppable(r);
		Game.getInstance().AddSteppable(sp);
		Game.getInstance().AddSettler(s);
		Game.getInstance().AddSettler(s2);
		//A teszteset elinditasa az asteroid osztaly SolarStorm() fugggvenyenek segitsegevel
		System.out.println("SolarStorm hits Settler and Robot:");
		tab = 0;
		inTest=true;
		a.SolarStorm();
		System.out.println("-----Teszteset vege----");
		inTest=false;
	}
	public void RobotCannotMoveAndDies(){
		//A palya felepitese a tesztesethez
		Space sp=new Space();
		Robot r=new Robot();
		Asteroid a=new Asteroid();

		//r robot elhelyezese egy olyan a aszteroidara, amelynek nincs szomszedja
		r.SetAsteroid(a);
		a.AddCreature(r);
		a.SetSpace(sp);

		sp.AddAsteroid(a);
		sp.AddCreature(r);

		Game.getInstance().AddSteppable(a);
		Game.getInstance().AddSteppable(r);
		Game.getInstance().AddSteppable(sp);

		//A teszteset elinditasa a robot osztaly WhereToMove() fugggvenyenek segitsegevel
		System.out.println("Robot cannot move and dies:");
		tab = 0;
		inTest=true;
		r.WhereToMove();
		System.out.println("-----Teszteset vege----");
		inTest=false;
	}
	public void AsteroidExplosionandGameOver(){
	    //A teszthez szukseges objektumok letrehozasa
		Asteroid b = new Asteroid();          //Ide kerul a robot, miutan felrobbant az "a" aszteroida
		Uranium u = new Uranium();
	    Asteroid a = new Asteroid();          //Ez az aszteroida fog robbanni
		Space sp = new Space();
		Robot r = new Robot();               //Az aszteroidan levo robot
		Settler s = new Settler();           //Az aszteroidan levo telepes

		//Aszteroida beallitasai
		a.SetMaterial(u);
		a.AddCreature(s);
		a.AddCreature(r);
		a.SetSpace(sp);

		//Robot és Settler beallitasai
		r.SetAsteroid(a);
		s.SetAsteroid(a);

		//Singleton beallitasa
		Game.getInstance().AddSettler(s);
		Game.getInstance().AddSteppable(a);
		Game.getInstance().AddSteppable(b);

		//A teszteset elinditasa
		System.out.println("Asteroid Explosion and GameOver:");
		tab = 0;
		inTest=true;
		a.Step();
		System.out.println("-----Teszteset vege----");
		inTest=false;
	}
	public void CreateGate(){
	    //A teszthez szukseges objektumok letrehozasa
		Iron i = new Iron();
		Uranium u = new Uranium();
		Settler s = new Settler();
		BillOfMaterials b = new BillOfMaterials();

		//Settler beallitasai
		s.AddMaterial(i);
		s.AddMaterial(u);
		
		//A teszteset elinditasa
		System.out.println("Create Gate:");
		tab = 0;
		inTest=true;
		s.CreateGate();
		System.out.println("-----Teszteset vege----");
		inTest=false;
	}
	public void CreateRobot(){
	    //A teszthez szukseges objektumok letrehozasa
		Iron i = new Iron();
		Carbon c = new Carbon();
		Space sp = new Space();
		Asteroid a = new Asteroid();
		Settler s = new Settler();
        BillOfMaterials b = new BillOfMaterials();

		//Settler beallitasai
		s.AddMaterial(i);
		s.AddMaterial(c);

		s.SetAsteroid(a);
		a.SetSpace(sp);

		//A teszteset elinditasa
		System.out.println("Create Robot:");
		tab = 0;
		inTest=true;
		s.CreateRobot();
		System.out.println("-----Teszteset vege----");
		inTest=false;
	}
	public void PlaceGateandRemovefromSettler(){
		//A teszthez szukseges objektumok letrehozasa
		Asteroid a1 = new Asteroid();
		Gate g1 = new Gate();
		Settler s = new Settler();
		Gate g2 = new Gate();

		//Aszteroida beallitasa
		a1.AddCreature(s);

		//Settler beallitasai
		s.AddGate(g1);
		s.AddGate(g2);
		s.SetAsteroid(a1);

		//A teszteset elinditasa
		System.out.println("Place Gate and Remove from Settler:");
		tab = 0;
		inTest=true;
		s.PlaceGate();
		System.out.println("-----Teszteset vege----");
		inTest=false;
	}
	public void SettlerMines(){
		//A tesztesethez szukseges objektumok letrehozasa
		Settler s = new Settler();
		Asteroid a = new Asteroid();
		Carbon c = new Carbon();
		
		//Asztreoida beallitasa
		BillOfMaterials b = new BillOfMaterials(); 
		a.SetMaterial(c);
		a.AddCreature(s);
		
		//Game beallitasa
		Game.getInstance().AddSettler(s);
		Game.getInstance().AddSteppable(a);
		
		//Settler beallitasa
		s.SetAsteroid(a);

		//A teszteset elinditasa
		System.out.println("Settler mines:");
		tab = 0;
		inTest=true;
		s.Mine();
		System.out.println("-----Teszteset vege----");
		inTest=false;
	}
	public void SolarStormInCaseWhenCreaturesCanHide(){
		//A tesztesethez szukseges objektumok letrehozasa
		Settler s = new Settler();
		Asteroid a = new Asteroid();
		Robot r = new Robot();
		
		//Az aszteroida parametereinek beallitasa es a retegek szamanak bekerese
		a.SetMaterial(null);
		a.AddCreature(s);
		a.AddCreature(r);
		
		//Settler beallitasa
		s.SetAsteroid(a);
		
		//Robot beallitasa
		r.SetAsteroid(a);
		
		//A teszteset elinditasa
		System.out.println("SolarStorm on an asteroid where Robot and Settler can hide:");
		tab = 0;
		inTest=true;
		a.SolarStorm();
		System.out.println("-----Teszteset vege----");
		inTest=false;
	}

	public void PrintFunc(String s){
		if(inTest) {
			for (int i = 0; i < tab; i++) {
				System.out.print("\t");
			}
			System.out.println(s);
		}
	}
	public void SetTab(int i){
		tab+=i;
	}

}