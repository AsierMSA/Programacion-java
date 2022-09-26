import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class main {
	static ArrayList<Resultado> listares = new ArrayList<Resultado>();
	static ArrayList<Tenista> listaten = new ArrayList<Tenista>();
	static ArrayList<String> nombres=new ArrayList<String>();
	static Map<String,ArrayList<String[]>> mapat= new HashMap<String,ArrayList<String[]>>();
	Map<String,ArrayList<String[]>> mapac= new HashMap<String,ArrayList<String[]>>();
	static Map<String, Tenista> mapaten=new HashMap<String, Tenista>();
	
	static String direc;
	
	public static ArrayList<Resultado> getListares() {
		return listares;
	}

	public void setListares(ArrayList<Resultado> listares) {
		this.listares = listares;
	}

	public static ArrayList<Tenista> getListaten() {
		return listaten;
	}

	public void setListaten(ArrayList<Tenista> listaten) {
		this.listaten = listaten;
	}
	
	public static String getDirec() {
		return direc;
	}

	public static void setDirec(String direc) {
		main.direc = direc;
	}

	public  static void anadir(Tenista t) {
			t.getnumvic();
			if(!(nombres.contains(t.getNombre()))){
				nombres.add(t.getNombre());
				
				listaten.add(t);
				
				
			}
		}
	public static void rellenarMapas(String Torneo) {
		mapat.put(Torneo, DatoTabular.valores);
		DatoTabular.setValores(new ArrayList<String[]>());

		
		for(int i=0;i<main.getListaten().size();i++) {
			mapaten.put(main.getListaten().get(i).getNombre(), main.getListaten().get(i));
		}
		System.out.println(mapaten);
		
		
	}
	

	public main() {
		super();
		direc="C:\\Users\\Estudiar\\git\\repository2\\Tenis\\src\\Australia.csv";
		DatoTabular.cargarcsv(direc);
		rellenarMapas("Australia");
		
		
		
		direc="C:\\Users\\Estudiar\\git\\repository2\\Tenis\\src\\Wimbledon.csv";
		
		DatoTabular.cargarcsv(direc);
		rellenarMapas("Wimbledon");
		VentanaGrandSlam v= new VentanaGrandSlam();
		
		
		
	}

	public static void main(String[] args) {
		
		
		main m= new main();
		
		
		
		
	}

}
