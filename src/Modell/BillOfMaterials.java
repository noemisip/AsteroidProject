package Modell;

import java.util.ArrayList;

	public class BillOfMaterials {
		//ezt a listat a SetUP() metodusok toltik fel, attol fuggoen, hogy robot, teleportkapu, vagy bazis epiteset vizsgaljuk
		private ArrayList<Material> materialList = new ArrayList<Material>();
		
		public boolean CheckMaterials(ArrayList<Material> materials) {
			//osszehasonlitja a parameterul kapott material listaval a sajat materialList-jet.
			int i=0;
			while(materials!= null && materialList.size()!=0 && i<materials.size())
			{
				int j=0;
				//A settler alapanyagait hasonlitja a sajat materialListjevel
				while(j<materialList.size() && !materials.get(i).IsEquales(materialList.get(j))) j++;
				//Ha megall a ciklus, es a j meg kisebb, mint a lista merete, akkor talalt egyezest, igy torolni kell
				//a materialListbol
				if(j<materialList.size()) RemoveMaterialFromList(materialList.get(j));
				i++;
			}
			//Ha a Modell.BillOfMaterials materialListajabol az osszes alapanyag kitorlodott,
			//akkor a settlernek van eleg alapanyaga, igy epithet.
			if(materialList.size()==0) return true;
			else return false; //Ha nem torlodott ki az osszes, akkor nem epithet
		}
		
		public void SetUpRobot() {
			materialList.clear();
			//Feltolti a materialList-et a robot megepitesehez szukseges alapanyagokkal
			//A teszt folyaman a robot epitesehez egy Modell.Iron, egy Modell.Uranium es egy Modell.Carbon szukseges
			//Ezekkel tolti fel a materialListet
			materialList.add(new Iron());
			materialList.add(new Carbon());
			materialList.add(new Uranium());
		}
		
		public void SetUpGate() {
			materialList.clear();
			//Feltolti a materialList-et a teleportkapu megepitesehez szukseges alapanyagokkal
			//A teszt folyaman a teleportkapu epitesehez egy Modell.Uranium, egy Modell.Ice es ket Modell.Iron szukseges
			//Ezekkel tolti fel a materialListet
			materialList.add(new Uranium());
			materialList.add(new Iron());
			materialList.add(new Iron());
			materialList.add(new Ice());
		}
		
		public void SetUpBase() {
			materialList.clear();
			//Feltolti a materialList-et az urbazis megepitesehez szukseges alapanyagokkal
			//A teszt folyaman a teleportkapu epitesehez minden nyersanyagbol 3 szukseges
			//Ezzel tolti fel a materialListet
			for(int i=0; i<3; i++) {
				materialList.add(new Carbon());
				materialList.add(new Ice());
				materialList.add(new Iron());
				materialList.add(new Uranium());
			}
		}
		
		public void RemoveMaterials(Settler s) {
			//A parameterul kapott telepes material listajabol kitorli a materialList-ben talalhato alapanyagokat
			//A materialList osszes elemet kitorli a settler materialjai kozul egyenkent
			for (int m=0;m< materialList.size();m++)
			{
				s.RemoveMaterial(materialList.get(m));
			}
		}
		
		public void RemoveMaterialFromList(Material m) {
			//Kitorli a materialList-bol a parameterkent kapott materialt.
			materialList.remove(m);
		}
	}

