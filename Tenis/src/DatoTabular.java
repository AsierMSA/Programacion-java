import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public abstract class DatoTabular {
	
	static ArrayList<String[]> cabeceras= new ArrayList<String[]>();
	static ArrayList<String[]> valores=new ArrayList<String[]>();
	
	public static void cargarcsv(String csvFile) {
	
	BufferedReader br = null;
	String line = "";
	
	{
	try {
	    br = new BufferedReader(new FileReader(csvFile));
	    System.out.println(br.readLine());
	    while ((line = br.readLine()) != null ) { 
	    	String[] ordenado= dividePorComas(line);
	    	valores.add(ordenado);
	    	Tenista c=new Tenista(ordenado[1], ordenado[3]);
			Tenista cg=new Tenista(ordenado[4], ordenado[6]);
			Resultado res=new Resultado(ordenado[0],c,cg,ordenado[2],ordenado[5],ordenado[7]);
			main.getListares().add(res);
		    	}

	    anadirten();
	    }

	catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException ew) {
	    ew.printStackTrace();
	} finally {
	    if (br != null) {
	        try {
	            br.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
}
	}
	public static void anadirten() {
		for(int i=0;i< main.getListares().size();i++) {
			main.anadir(main.getListares().get(i).getGanador());
			main.anadir(main.getListares().get(i).getSubcampeon());
		}
	}
	public static void guardarcsv(String csv) throws IOException {
		 FileWriter fichero = new FileWriter(csv,true);
         PrintWriter pw = new PrintWriter(fichero);
         System.out.println("SUI");
         for(int i=0;i<TablaDatos.getDatos().length;i++) {
         String aux=TablaDatos.getDatos()[i][0] + "," +TablaDatos.getDatos()[i][1] +","+ TablaDatos.getDatos()[i][2] +","+ TablaDatos.getDatos()[i][3] +","+ TablaDatos.getDatos()[i][4] + "," +TablaDatos.getDatos()[i][5]  +","+ TablaDatos.getDatos()[i][6] +","+ TablaDatos.getDatos()[i][7];
         pw.println(aux);
         }
         if (null != fichero) {
             fichero.close();
         }
         
	}
	
	public static String[] dividePorComas( String linea ) {
		ArrayList<String> listaTokens = new ArrayList<String>();
		boolean entreComillas = false;
		StringBuilder b = new StringBuilder();
		for (char c : linea.toCharArray()) {
		 if (c==',') {
		 if (entreComillas) {
		 b.append(c);
		 } else {
		 listaTokens.add( b.toString() );
		 b = new StringBuilder();
		 }
		 } else if (c=='\"') {
		 entreComillas = !entreComillas;
		 } else {
		 b.append(c);
		 }
		}
		listaTokens.add( b.toString() );
		return listaTokens.toArray( new String[0] );
}

	public static ArrayList<String[]> getValores() {
		return valores;
	}

	public static void setValores(ArrayList<String[]> valores) {
		DatoTabular.valores = valores;
	}

	public static ArrayList<String[]> getCabeceras() {
		return cabeceras;
	}

	public static void setCabeceras(ArrayList<String[]> cabeceras) {
		DatoTabular.cabeceras = cabeceras;
	}
	

	
		
	
public static String getValor(String Cabecera) {
	return "";
}
}

