import java.util.ArrayList;

public class BillOfMaterials {
	//private Material GateList;
	//ezt a listát a SetUP() metódusok töltik fel, attól függõen, hogy robot, teleportkapu, vagy bázis építését vizsgáljuk
	private ArrayList<Material> materialList;

	public BillOfMaterials(){
		materialList= new ArrayList<Material>();
	}
	public boolean CheckMaterials(ArrayList<Material> materials) {
		//Összehasonlítja a paraméterül kapott material listával a saját materialList-jét.
		Controller c = new Controller();
		c.PrintFunc("CheckMaterials(ArrayList<Material> materials;) : true/false");
		int i=0;
		while(materialList.size()!=0 && i<materials.size())
		{
			int j=0;
			//A settler alapanyagait hasonlítja a saját materialListjével
			while(!materials.get(i).IsEquales(materialList.get(j)) && j<materialList.size()) j++;
			//Ha megáll a ciklus, és a j még kisebb, mint a lista mérete, akkor talált egyezést, így törölni kell
			//a materialListbõl
			if(j<materialList.size()) RemoveMaterialFromList(materialList.get(j));
			i++;
		}
		//Ha a BillOfMaterials materialListájából az összes alapanyag kitörlõdött,
		//akkor a settlernek van elég alapanyaga, így építhet.
		if(materialList.size()==0) return true;
		else return false; //Ha nem törlõdött ki az összes, akkor nem építhet
	}
	
	public void SetUpRobot() {
		//Feltölti a materialList-et a robot megépítéséhez szükséges alapanyagokkal
		Controller c = new Controller();
		c.PrintFunc("SetupRobot()");
		//A teszt folyamán a robot építéséhez egy Iron és egy Carbon szükséges
		//Ezekkel tölti fel a materialListet
		materialList.add(new Iron());
		materialList.add(new Carbon());
	}
	
	public void SetUpGate() {
		//Feltölti a materialList-et a teleportkapu megépítéséhez szükséges alapanyagokkal
		Controller c = new Controller();
		c.PrintFunc("SetUPGate()");
		//A teszt folyamán a teleportkapu építéséhez egy Uranium és egy Iron szükséges
		//Ezekkel tölti fel a materialListet
		materialList.add(new Uranium());
		materialList.add(new Iron());
	}
	
	public void SetUpBase() {
		//Feltölti a materialList-et az ûrbázis megépítéséhez szükséges alapanyagokkal
		Controller c = new Controller();
		c.PrintFunc("SetUpBase()");
		//A teszt folyamán a teleportkapu építéséhez egy Carbon szükséges
		//Ezzel tölti fel a materialListet
		materialList.add(new Carbon());
	}
	
	public void RemoveMaterials(Settler s) {
		//A paraméterül kapott telepes material listájából kitörli a materialList-ben található alapanyagokat
		Controller c = new Controller();
		c.PrintFunc("RemoveMaterials(s)");
		//A materialList összes elemét kitörli a settler materialjai közül egyenként
		for (int m=0;m< materialList.size();m++)
		{
			s.RemoveMaterial(materialList.get(m));
		}
	}
	
	public void RemoveMaterialFromList(Material m) {
		//Kitörli a materialList-bõl a paraméterként kapott materialt.
		Controller c = new Controller();
		c.PrintFunc("RemoveMaterials(s)");
		materialList.remove(m);
	}
}
