import java.util.ArrayList;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.*;


public class TablaDatos extends JTable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private static String[] nombres={"Year","Champion","Seed_Champion","Ctry_Champion","Runner-up","Seed_Runner-up","Ctry_Runner-up","Score in the Final"};
	private static String[] nombrest={"Nombre", "Nacionalidad","Victorias"};
	private static String[][] datos;
	private static String[][] ten= new String[main.getListaten().size()][];
	static JTable table;
	static JTable tablet;


	JScrollPane scrollpane;
	JScrollPane scrollpanet;
	JTabbedPane pestana;
	static Object selectedValue;
	
	
	




	public TablaDatos() {
		super();
		rellenar();
		table = new JTable(datos,nombres);
		tablet= new JTable(ten,nombrest);
		scrollpane = new JScrollPane(table);
		scrollpanet= new JScrollPane(tablet);
		pestana = new JTabbedPane(JTabbedPane.TOP);
		
		
		
	}

	


	public String[] getNombres() {
		return nombres;
	}




	public JScrollPane getScrollpanet() {
		return scrollpanet;
	}




	public void setScrollpanet(JScrollPane scrollpanet) {
		this.scrollpanet = scrollpanet;
	}




	public void setNombres(String[] nombres) {
		this.nombres = nombres;
	}




	public static String[][] getDatos() {
		return datos;
	}




	public void setDatos(String[][] datos) {
		this.datos = datos;
	}
	
	
	
	public JTable getTable() {
		return table;
	}




	public void setTable(JTable table) {
		this.table = table;
	}
	





	public JScrollPane getScrollpane() {
		return scrollpane;
	}




	public void setScrollpane(JScrollPane scrollpane) {
		this.scrollpane = scrollpane;
	}
	
	
	public static JTable getTablet() {
		return tablet;
	}




	public static void setTablet(JTable tablet) {
		TablaDatos.tablet = tablet;
	}
	






	public static Object getSelectedValue() {
		return selectedValue;
	}




	public void setSelectedValue(Object selectedValue) {
		this.selectedValue = selectedValue;
	}




	public static void rellenar() {
		
		Object[] possibleValues = { "Australia", "Wimbledon" };
		Object selectedValue = JOptionPane.showInputDialog(null,
		"Choose one", "Input",
		JOptionPane.INFORMATION_MESSAGE, null,
		possibleValues, possibleValues[0]);
		for(String clave:main.mapat.keySet()) {
			if(clave.equals(selectedValue)) {
				
				datos=new String[main.mapat.get(clave).size()][];
				for(int i=0;i<main.mapat.get(clave).size();i++) {
					datos[i]=main.mapat.get(clave).get(i);
					
				}
			}
			table = new JTable(datos,nombres);
			
		}
		for(int i=0; i<main.getListaten().size();i++) {
			String[] s= {main.getListaten().get(i).getNombre(), main.getListaten().get(i).getNacionalidad(),String.valueOf(main.getListaten().get(i).getNvic())};
			ten[i]=s;
		}

	}
	
}
