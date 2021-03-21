import java.util.ArrayList;

public class BillOfMaterials {
	//private Material GateList;
	//ezt a listat a SetUP() metodusok toltik fel, attol fuggoen, hogy robot, teleportkapu, vagy bazis epiteset vizsgaljuk
	private ArrayList<Material> materialList;

	public BillOfMaterials(){
		materialList= new ArrayList<Material>();
	}
	public boolean CheckMaterials(ArrayList<Material> materials) {
		//Osszehasonlitja a parameterul kapott material listaval a sajat materialList-jet.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("CheckMaterials(ArrayList<Material> materials;) : true/false");
		int i=0;
		while(materialList.size()!=0 && i<materials.size())
		{
			int j=0;
			//A settler alapanyagait hasonlitja a sajat materialListjevel
			while(j<materialList.size() && !materials.get(i).IsEquales(materialList.get(j))) j++;
			//Ha megall a ciklus, es a j meg kisebb, mint a lista merete, akkor talalt egyezest, igy torolni kell
			//a materialListbol
			if(j<materialList.size()) RemoveMaterialFromList(materialList.get(j));
			i++;
		}
		cnt.SetTab(-1);
		//Ha a BillOfMaterials materialListajabol az osszes alapanyag kitorldott,
		//akkor a settlernek van eleg alapanyaga, igy epithet.
		if(materialList.size()==0) return true;
		else return false; //Ha nem torlodott ki az osszes, akkor nem epithet
	}
	
	public void SetUpRobot() {
		//Feltolti a materialList-et a robot megepitesehez szukseges alapanyagokkal
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("SetupRobot()");
		//A teszt folyaman a robot epitesehez egy Iron és egy Carbon szukseges
		//Ezekkel tolti fel a materialListet
		materialList.add(new Iron());
		materialList.add(new Carbon());
		cnt.SetTab(-1);
	}
	
	public void SetUpGate() {
		//Feltolti a materialList-et a teleportkapu megepitesehez szukseges alapanyagokkal
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("SetUpGate()");

		//A teszt folyaman a teleportkapu epitesehez egy Uranium és egy Iron szukseges
		//Ezekkel tolti fel a materialListet
		materialList.add(new Uranium());
		materialList.add(new Iron());
		cnt.SetTab(-1);
	}
	
	public void SetUpBase() {
		//Feltolti a materialList-et az urbazis megepitesehez szukseges alapanyagokkal
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("SetUpBase()");

		//A teszt folyaman a teleportkapu epitesehez egy Carbon szukseges
		//Ezzel tolti fel a materialListet
		materialList.add(new Carbon());
		cnt.SetTab(-1);
	}
	
	public void RemoveMaterials(Settler s) {
		//A parameterul kapott telepes material listajabol kitorli a materialList-ben talalhato alapanyagokat
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("RemoveMaterials(Settler s)");

		//A materialList osszes elemet kitorli a settler materialjai kozul egyenkent
		for (int m=0;m< materialList.size();m++)
		{
			s.RemoveMaterial(materialList.get(m));
		}cnt.SetTab(-1);
	}
	
	public void RemoveMaterialFromList(Material m) {
		//Kitorli a materialList-bol a parameterkent kapott materialt.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("RemoveMaterialFromList(Material m)");
		materialList.remove(m);
		cnt.SetTab(-1);
	}
}