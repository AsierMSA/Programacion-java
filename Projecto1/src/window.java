import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
public class window extends JFrame  {
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel texto= new JLabel();                   
	 private static JButton boton= new JButton(); 
	 private static JButton botont= new JButton(); 
	 private int edad;
	 private JPanel panel;
	 private JTextArea tareas=new JTextArea();
	 JScrollPane scroll;
	 String act="";
	 ArrayList<JTextArea> ja=new ArrayList<JTextArea>();
	 public static JButton getboton() {
		 return boton;
	 }
	 public static JButton getbotont() {
		 return botont;
	 }
	 
	 
	 
	 
	 
	 public window() {
			scroll=new JScrollPane();
			scroll.setBounds(35,40,220,200);
			panel=new JPanel();
			
			
			panel.setLayout(null);
			panel.setPreferredSize(new Dimension(100,300));
			act="";
			for (java.util.Map.Entry<Cliente, ArrayList<Tarea>> q: window2.getMapa().entrySet()) {
				if(q.getValue()!=null && q.getKey().getNombre().equals(window2.getNombre())) {
					for(int i=0;i<q.getValue().size();i++) {
						if(q.getValue().get(i)!=null) {
						act+=q.getValue().get(i).toString();
						
						
						}
						}
				}
			}
			tareas.setText(act);
			tareas.setCaretPosition(0);
			
			tareas.setBounds(0, 0, 200, 300);
			panel.add(tareas);
			scroll.setViewportView(panel);
			
		 	this.setLayout(null);
		 	this.setSize(310, 310);  
		    texto.setText("Hola " + window2.getNombre());    
		    texto.setBounds(10, 10, 125, 25);  
		    boton.setText("Añadir Tarea"); 
		    boton.setBounds(5, 240, 140, 30);  
		    botont.setText("Borrar Tarea");
		    botont.setBounds(150, 240, 140, 30);  
		    this.add(texto);
		    this.add(boton);
		    this.add(botont);
		    this.add(scroll);
		    acciones();
		    JOptionPane.showMessageDialog(null, "Operación realizada ");
		 }
	 public void setField(String text) {
		    tareas.setText(text);
		}
	
	public window(int p) {
		String getnombre = JOptionPane.showInputDialog("Nombre");
		String getcontraseña = JOptionPane.showInputDialog("Contraseña");
		numero();
		 Cliente a;
		 a = new Cliente(getnombre, getcontraseña, edad);
		 window2.anadirmapa(a, new ArrayList<Tarea>());
		 window2.guardar("mapa.dat");
		 System.out.println(window2.getMapa());
		 new window2();
	}
	 
	public void numero() {
		 try{
			 
			 String getedad = JOptionPane.showInputDialog("Edad");
			 edad= Integer.parseInt(getedad);
		
		 }catch(NumberFormatException e) {
			 
			 System.out.println("Error, escribe un número");
			 numero();
		
		 }
		
	}

	
	
	 private void acciones(){


				
		 		boton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					String nv="";
					String getnombre = JOptionPane.showInputDialog("Nombre de Tarea");
					Hora h=new Hora();
					 JDialog.setDefaultLookAndFeelDecorated(true);
					    Object[] selectionValues = { "Examen", "Reunion" };
					    String initialSelection = "Examen";
					    Object selection = JOptionPane.showInputDialog(null, "Tipo de Tarea",
					        "Tarea", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
					    if (selection!=null) {
					    if(selection.equals("Examen") ) {
					    	Examen ex=new Examen(getnombre,h);
					    	for (java.util.Map.Entry<Cliente, ArrayList<Tarea>> q: window2.getMapa().entrySet()) {
								if (q.getKey().getNombre()!=null && q.getKey().getNombre().equals(window2.getNombre())) {	
									q.getValue().add(ex);
									window2.mapa.replace(q.getKey(), null, q.getValue());
									nv=ex.toString();
									
								
								}
					    	}
					    }
					    if((selection.equals("Reunion") )) {
					    	Reunion re=new Reunion(getnombre,h);
					    	for (java.util.Map.Entry<Cliente, ArrayList<Tarea>> q: window2.getMapa().entrySet()) {
								if (q.getKey().getNombre()!=null && q.getKey().getNombre().equals(window2.getNombre())) {	
									q.getValue().add(re);
									window2.mapa.replace(q.getKey(), null, q.getValue());
									nv=re.toString();
									
					    	
					    }
					    	}
					    }
					    }
					    
					    window2.guardar("mapa.dat");
					    setField(tareas.getText()+nv);
					    
				}
		 		});
				
		 		botont.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					String nv="";
					String getnombres = JOptionPane.showInputDialog("¿Que Tarea quiere borrar?");
					try {
					for (java.util.Map.Entry<Cliente, ArrayList<Tarea>> q: window2.getMapa().entrySet()) {
						if (q.getKey().getNombre()!=null && q.getKey().getNombre().equals(window2.getNombre())) {	
							for(int i=0;i<q.getValue().size();i++) {
								if(q.getValue().get(i).getNombre().equals(getnombres)) {
									q.getValue().remove(i);
									window2.mapa.replace(q.getKey(), null, q.getValue());
									JOptionPane.showMessageDialog(null, "Operación realizada ");
							    	window2.guardar("mapa.dat");
							    	
									
									
								}
							}
						
					}
						
											
					}
					for (java.util.Map.Entry<Cliente, ArrayList<Tarea>> q: window2.getMapa().entrySet()) {
						if(q.getValue()!=null && q.getKey().getNombre().equals(window2.getNombre())) {
							for(int i=0;i<q.getValue().size();i++) {
								if(q.getValue().get(i)!=null) {
								nv+=q.getValue().get(i).toString();
								
								
								}
								}
						}
					}
					tareas.setText(nv);
				
					}catch(Exception ex){
					JOptionPane.showMessageDialog(null, "Tarea no encontrada");
				}
				}
					
	 });
				
		
		 
	 }


}

