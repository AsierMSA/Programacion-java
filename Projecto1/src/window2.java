import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class window2 extends JFrame implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static HashMap<Cliente, ArrayList<Tarea>> mapa= new HashMap<Cliente, ArrayList<Tarea>>();
	JLabel usuLabel,cLabel;
	JButton regButton,IButton;
	
	JTextField usuText,cText;
	static String Nombre;
	public static void anadirmapa(Cliente c,ArrayList<Tarea> t) {
		mapa.put(c, t);
	}
	public static HashMap<Cliente, ArrayList<Tarea>> getMapa() {
		return mapa;
	}

	public static String getNombre() {
		return Nombre;
	}


	public window2() {
		File fichero = new File( "mapa.dat" );
		if (fichero.exists()) { 
			cargar( "mapa.dat" );
		}
		 setBounds(0,0,330,150);
		 JPanel panel=new JPanel();
		 components(panel);
		 add(panel);
		 initAcciones();
		 
		 
		 setVisible(true);
	}
	
		public void components(JPanel panel) {
			usuLabel = new JLabel("Usuario");
			usuLabel.setBounds(10, 10, 80, 25);
			panel.add(usuLabel);

			usuText = new JTextField(20);
			usuText.setBounds(100, 10, 160, 25);
			panel.add(usuText);
			
			cText = new JPasswordField(20);
			cText.setBounds(100, 40, 160, 25);
			panel.add(cText);


			cLabel = new JLabel("Contraseña");
			cLabel.setBounds(10, 40, 80, 25);
			panel.add(cLabel);
			
			regButton = new JButton("Registrar");
			regButton.setBounds(160, 80, 100, 25);
			panel.add(regButton);
			
			

			
			IButton = new JButton("Iniciar Sesión");
			IButton.setBounds(10, 80, 120, 25);
			panel.add(IButton);
			panel.setLayout(null);
		
		
	}
		private void initAcciones() {
	
			IButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						boolean si=true;
						for (java.util.Map.Entry<Cliente, ArrayList<Tarea>> q: mapa.entrySet()) {
							if (q.getKey().getNombre()!=null && q.getKey().getNombre().equals(usuText.getText())) {	
								si=false;
								Nombre=usuText.getText();
							
						}
						}
							if(si) {
								JOptionPane.showMessageDialog( null, "Usuario incorrecto");
								usuText.setText("");
								
							}
						
						for (java.util.Map.Entry<Cliente, ArrayList<Tarea>> q: mapa.entrySet()) {
							if (q.getKey().getNombre()!=null && q.getKey().getNombre().equals(usuText.getText())) {
								if(q.getKey().getContraseña().equals(cText.getText())) {
									JOptionPane.showMessageDialog( null, "Contraseña correcta");
									dispose();
									window a=new window();
									a.setVisible(true);
								}
								else {
									JOptionPane.showMessageDialog( null, "Contraseña incorrecta");
									cText.setText("");
								}
								
							}
							
							
						}
						
						
					
					}
					});
			setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					int option = JOptionPane.showConfirmDialog(
						null, 
						"¿Estás seguro de que quieres cerrar la aplicación?",
						"Confirmación de cierre", 
						JOptionPane.YES_NO_OPTION, 
						JOptionPane.QUESTION_MESSAGE);
					if (option == JOptionPane.YES_OPTION) {
						guardar("mapa.dat");
						System.exit(0);
						
					}
				}
			});

			
			regButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				dispose();
				window a=new window(1);
				a.setVisible(true);
				}
				});
			
		
		}
		
		public static void guardar(String e) {
			try {
				ObjectOutputStream bf= new ObjectOutputStream(new FileOutputStream(e));
				bf.writeObject(mapa);
				bf.close();
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		@SuppressWarnings("unchecked")
		private void cargar(String e) {
			
			try {
				ObjectInputStream bf= new ObjectInputStream(new FileInputStream(e));
				Object leido=bf.readObject();
				mapa=(HashMap<Cliente, ArrayList<Tarea>>) leido;
				System.out.println(mapa);
				bf.close();
				
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		public static void main(String[] args) {
			Cliente cl=new Cliente("Asier","teresa",20);
			ArrayList<Tarea> at=new ArrayList<Tarea>();
		    anadirmapa(cl,at);
			new window2();

			
		}
}

	
	
	

