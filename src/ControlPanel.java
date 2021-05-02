import java.util.ArrayList;

public class ControlPanel {
    private Settler s;
    private ArrayList<String> text=new ArrayList<String>();

    public void SetSettler(Settler settler){s=settler;}
    public void Update(){}
    public int UserInput(){return 1;}
}
