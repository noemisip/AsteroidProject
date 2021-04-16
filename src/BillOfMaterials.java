	import java.util.ArrayList;

	public class BillOfMaterials {
		//private Material GateList;
		//ezt a listat a SetUP() metodusok toltik fel, attol fuggoen, hogy robot, teleportkapu, vagy bazis epiteset vizsgaljuk
		private ArrayList<Material> materialList;
		
		public boolean CheckMaterials(ArrayList<Material> materials) {
			//osszehasonlitja a parameterul kapott material listaval a sajat materialList-jet.
			Controller c = new Controller();
			int i=0;
			while(materialList.size()!=0 && i<materials.size())
			{
				int j=0;
				//A settler alapanyagait hasonlitja a sajat materialListjevel
				while(!materials.get(i).IsEquales(materialList.get(j)) && j<materialList.size()) j++;
				//Ha megall a ciklus, es a j meg kisebb, mint a lista merete, akkor talalt egyezest, igy torolni kell
				//a materialListbol
				if(j<materialList.size()) RemoveMaterialFromList(materialList.get(j));
			}
			//Ha a BillOfMaterials materialListajabol az osszes alapanyag kitorlodott,
			//akkor a settlernek van eleg alapanyaga, igy epithet.
			if(materialList.size()==0) return true;
			else return false; //Ha nem torlodott ki az osszes, akkor nem epithet
		}
		
		public void SetUpRobot() {
			//Feltolti a materialList-et a robot megepitesehez szukseges alapanyagokkal
			Controller c = new Controller();
			//A teszt folyaman a robot epitesehez egy Iron es egy Carbon szukseges
			//Ezekkel tolti fel a materialListet
			materialList.add(new Iron());
			materialList.add(new Carbon());
		}
		
		public void SetUpGate() {
			//Feltolti a materialList-et a teleportkapu megepitesehez szukseges alapanyagokkal
			Controller c = new Controller();
			//A teszt folyaman a teleportkapu epitesehez egy Uranium es egy Iron szukseges
			//Ezekkel tolti fel a materialListet
			materialList.add(new Uranium());
			materialList.add(new Iron());
		}
		
		public void SetUpBase() {
			//Feltolti a materialList-et az urbazis megepitesehez szukseges alapanyagokkal
			Controller c = new Controller();
			//A teszt folyaman a teleportkapu epitesehez egy Carbon szukseges
			//Ezzel tolti fel a materialListet
			materialList.add(new Carbon());
		}
		
		public void RemoveMaterials(Settler s) {
			//A parameterul kapott telepes material listajabol kitorli a materialList-ben talalhato alapanyagokat
			Controller c = new Controller();
			//A materialList osszes elemet kitorli a settler materialjai kozul egyenkent
			for (int m=0;m< materialList.size();m++)
			{
				s.RemoveMaterial(materialList.get(m));
			}
		}
		
		public void RemoveMaterialFromList(Material m) {
			//Kitorli a materialList-bol a parameterkent kapott materialt.
			Controller c = new Controller();
			materialList.remove(m);
		}
	}

