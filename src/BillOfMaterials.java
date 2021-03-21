import java.util.ArrayList;

public class BillOfMaterials {
	//private Material GateList;
	//ezt a list�t a SetUP() met�dusok t�ltik fel, att�l f�gg�en, hogy robot, teleportkapu, vagy b�zis �p�t�s�t vizsg�ljuk
	private ArrayList<Material> materialList;

	public BillOfMaterials(){
		materialList= new ArrayList<Material>();
	}
	public boolean CheckMaterials(ArrayList<Material> materials) {
		//�sszehasonl�tja a param�ter�l kapott material list�val a saj�t materialList-j�t.
		Controller c = new Controller();
		c.PrintFunc("CheckMaterials(ArrayList<Material> materials;) : true/false");
		int i=0;
		while(materialList.size()!=0 && i<materials.size())
		{
			int j=0;
			//A settler alapanyagait hasonl�tja a saj�t materialListj�vel
			while(!materials.get(i).IsEquales(materialList.get(j)) && j<materialList.size()) j++;
			//Ha meg�ll a ciklus, �s a j m�g kisebb, mint a lista m�rete, akkor tal�lt egyez�st, �gy t�r�lni kell
			//a materialListb�l
			if(j<materialList.size()) RemoveMaterialFromList(materialList.get(j));
			i++;
		}
		//Ha a BillOfMaterials materialList�j�b�l az �sszes alapanyag kit�rl�d�tt,
		//akkor a settlernek van el�g alapanyaga, �gy �p�thet.
		if(materialList.size()==0) return true;
		else return false; //Ha nem t�rl�d�tt ki az �sszes, akkor nem �p�thet
	}
	
	public void SetUpRobot() {
		//Felt�lti a materialList-et a robot meg�p�t�s�hez sz�ks�ges alapanyagokkal
		Controller c = new Controller();
		c.PrintFunc("SetupRobot()");
		//A teszt folyam�n a robot �p�t�s�hez egy Iron �s egy Carbon sz�ks�ges
		//Ezekkel t�lti fel a materialListet
		materialList.add(new Iron());
		materialList.add(new Carbon());
	}
	
	public void SetUpGate() {
		//Felt�lti a materialList-et a teleportkapu meg�p�t�s�hez sz�ks�ges alapanyagokkal
		Controller c = new Controller();
		c.PrintFunc("SetUPGate()");
		//A teszt folyam�n a teleportkapu �p�t�s�hez egy Uranium �s egy Iron sz�ks�ges
		//Ezekkel t�lti fel a materialListet
		materialList.add(new Uranium());
		materialList.add(new Iron());
	}
	
	public void SetUpBase() {
		//Felt�lti a materialList-et az �rb�zis meg�p�t�s�hez sz�ks�ges alapanyagokkal
		Controller c = new Controller();
		c.PrintFunc("SetUpBase()");
		//A teszt folyam�n a teleportkapu �p�t�s�hez egy Carbon sz�ks�ges
		//Ezzel t�lti fel a materialListet
		materialList.add(new Carbon());
	}
	
	public void RemoveMaterials(Settler s) {
		//A param�ter�l kapott telepes material list�j�b�l kit�rli a materialList-ben tal�lhat� alapanyagokat
		Controller c = new Controller();
		c.PrintFunc("RemoveMaterials(s)");
		//A materialList �sszes elem�t kit�rli a settler materialjai k�z�l egyenk�nt
		for (int m=0;m< materialList.size();m++)
		{
			s.RemoveMaterial(materialList.get(m));
		}
	}
	
	public void RemoveMaterialFromList(Material m) {
		//Kit�rli a materialList-b�l a param�terk�nt kapott materialt.
		Controller c = new Controller();
		c.PrintFunc("RemoveMaterials(s)");
		materialList.remove(m);
	}
}
