

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.net.ServerSocket;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/** Modificacion de EjemploSockets para varios clientes.
 * Obsérvense los comentarios *VARIOS CLIENTES* 
 * Ejemplo de utilización de sockets para comunicar un programa "servidor"
 * con VARIOS "clientes" en el mismo equipo. El cliente puede enviar textos
 * al servidor, que envía un mensaje de confirmación con cada texto.
 * Si se manda un mensaje "hola" se rebota a todos los clientes
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class EjemploSockets2 {

	private static String HOST = "localhost";  // IP de conexión para la comunicación
	private static int PUERTO = 4000;          // Puerto de conexión
	
	private static VentanaServidor vs;
	public static void main(String[] args) {
		ArrayList<Personaje> lista=new ArrayList<Personaje>();
		ArrayList<JFrame> listaanadidos=new ArrayList<JFrame>();
		vs = new VentanaServidor();
		vs.setVisible( true );
		(new Thread() {
			@Override
			public void run() {
				vs.lanzaServidor();
			}
		}).start();
		VentanaCliente vc = new VentanaCliente( "A" );
		lista.add(vc.getP());
		listaanadidos.add(vc);
		vc.setVisible(true);
		
		(new Thread() {
			@Override
			public void run() {
				vc.lanzaCliente();
			}
		}).start();
		// *VARIOS CLIENTES* Lanzamos más clientes tras pausita
		try {Thread.sleep(2000); } catch (InterruptedException e) {} 
		VentanaCliente vc2 = new VentanaCliente( "B" );
		lista.add(vc2.getP());
		listaanadidos.add(vc2);
		vc2.setLocation( vc2.getLocation().x, vc2.getLocation().y + 200 );  // Un poco más abajo
		vc2.setVisible(true);
		(new Thread() {
			@Override
			public void run() {
				vc2.lanzaCliente();
			}
		}).start();
		try {Thread.sleep(2000); } catch (InterruptedException e) {} 
		VentanaCliente vc3 = new VentanaCliente( "C" );
		lista.add(vc3.getP());
		listaanadidos.add(vc3);
		vc3.setLocation( vc3.getLocation().x, vc3.getLocation().y + 400 );  // Más abajo aún
		vc3.setVisible(true);
		for(int i=0;i<listaanadidos.size();i++) {
			for(int j=0;j<lista.size();j++) {
				if(lista.get(j)!=((EjemploSockets2.VentanaCliente) listaanadidos.get(i)).getP()) {
					 listaanadidos.get(i).getContentPane().add(lista.get(j));
					 ((EjemploSockets2.VentanaCliente) listaanadidos.get(i)).getAnadidos().add(lista.get(j));
					 System.out.println(listaanadidos.get(i).getTitle() + "    se a�ade   " + lista.get(j).getFile().getAbsolutePath());}
			}
		}
		
		
		(new Thread() {
			@Override
			public void run() {
				vc3.lanzaCliente();
			}
		}).start();
	
	}

	@SuppressWarnings("serial")
	public static class VentanaCliente extends JFrame {
		private JTextArea taEstado = new JTextArea();
		private JTextField tfMensaje = new JTextField( "Introduce tu mensaje y pulsa <Enter>" );
		private PrintWriter outputAServer;
		private PrintWriter outputAServer1;
        private boolean finComunicacion = false;
        private String[] botones = {"Boton A", "Boton B", "Boton C", "Boton D"};
        private String foto;
        private String nombre;
        private Personaje mover;
        private static ArrayList<Personaje> lista=new ArrayList<Personaje>();
        private ArrayList<Personaje> anadidos=new ArrayList<Personaje>();


		public ArrayList<Personaje> getAnadidos() {
			return anadidos;
		}
		public void setAnadidos(ArrayList<Personaje> anadidos) {
			this.anadidos = anadidos;
		}

		
		public  Personaje getMover() {
			return mover;
		}
		public void setMover(Personaje mover) {
			this.mover = mover;
		}
		public VentanaCliente( String nombre ) {
			this.nombre = nombre;
			setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
			setSize( 400, 300 );
			setLocation( 0, 0 );
			File file=new File("C:\\\\Users\\\\Estudiar\\\\Desktop\\\\ejercicio 3\\\\Practica1b\\\\src\\\\pok04.png");
			File file1=new File("C:\\\\Users\\\\Estudiar\\\\Desktop\\\\ejercicio 3\\\\Practica1b\\\\src\\\\pok01.png");
			File file2=new File("C:\\\\Users\\\\Estudiar\\\\Desktop\\\\ejercicio 3\\\\Practica1b\\\\src\\\\pok02.png");
			File file3=new File("C:\\\\Users\\\\Estudiar\\\\Desktop\\\\ejercicio 3\\\\Practica1b\\\\src\\\\pok03.png");
			try {
			BufferedImage bufferedImage = ImageIO.read(file);
			BufferedImage bufferedImage1 = ImageIO.read(file1);
			BufferedImage bufferedImage2 = ImageIO.read(file2);
			BufferedImage bufferedImage3= ImageIO.read(file3);
			
			Image image=bufferedImage.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
			ImageIcon imageIcon = new ImageIcon(image);
			 Personaje principal=new Personaje(imageIcon,"",file);

			
			
			
	        lista.add(principal);
	      
	        
	        Image image1=bufferedImage1.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
	        ImageIcon imageIcon1 = new ImageIcon(image1);
	        Personaje principal1=new Personaje(imageIcon1,"",file1);
	        lista.add(principal1);
	        
	        Image image2=bufferedImage2.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
	        ImageIcon imageIcon2 = new ImageIcon(image2);
	        Personaje principal2=new Personaje(imageIcon2,"",file2);
	        lista.add(principal2);
	       
	        Image image3=bufferedImage3.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
	        ImageIcon imageIcon3 = new ImageIcon(image3);
	        Personaje principal3=new Personaje(imageIcon3,"",file3);
	        lista.add(principal3);
			}catch (Exception e) {
				e.printStackTrace();
			}
			int ventana = JOptionPane.showOptionDialog(null, 
					"Pulsa un boton:", 
					"Personajes", 
					JOptionPane.DEFAULT_OPTION, 
					JOptionPane.QUESTION_MESSAGE, null, 
					botones, botones[0]);
	if(ventana == 0) {foto="C:\\Users\\Estudiar\\Desktop\\ejercicio 3\\Practica1b\\src\\pok04.png";} 
	else if(ventana == 1) {foto="C:\\Users\\Estudiar\\Desktop\\ejercicio 3\\Practica1b\\src\\pok01.png";}
	else if(ventana == 2) {foto="C:\\Users\\Estudiar\\Desktop\\ejercicio 3\\Practica1b\\src\\pok02.png";}
	else if(ventana == 3) {foto="C:\\Users\\Estudiar\\Desktop\\ejercicio 3\\Practica1b\\src\\pok03.png";}
			
			
			setTitle( "Ventana cliente " + nombre  );
			taEstado.setEditable( false );
			getContentPane().add( tfMensaje, BorderLayout.NORTH );
			getContentPane().add( taEstado, BorderLayout.CENTER );

			for(int i=0;lista.size()>i;i++) {

				if(lista.get(i).getFile().getAbsolutePath().equals(foto)) {
					mover=lista.get(i);


			        getContentPane().add(mover);
			        anadidos.add(mover);
			        
				}
			}


			tfMensaje.addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent e) {
					
					
				}

				@Override
				public void keyPressed(KeyEvent e) {
				
					if(KeyEvent.getKeyText(e.getKeyCode()).equals("Arriba")) {
						mover.setLocation(mover.getX(),mover.getY()-25);
						mover.setY(mover.getY()-25);
						outputAServer1.println(mover.getX()+","+mover.getY()+","+mover.getFile().getAbsolutePath());
						}
					if(KeyEvent.getKeyText(e.getKeyCode()).equals("Abajo")) {
						mover.setLocation(mover.getX(), mover.getY()+25);
						mover.setY(mover.getY()+25);
						outputAServer1.println(mover.getX()+","+mover.getY()+","+mover.getFile().getAbsolutePath());
						
					}
					if(KeyEvent.getKeyText(e.getKeyCode()).equals("Derecha")) {
						mover.setLocation(mover.getX()-25, mover.getY());
						mover.setX(mover.getX()+25);
						outputAServer1.println(mover.getX()+","+mover.getY()+","+mover.getFile().getAbsolutePath());

					}
					if(KeyEvent.getKeyText(e.getKeyCode()).equals("Izquierda")) {
						mover.setLocation(mover.getX()+25,mover.getY());
						mover.setX(mover.getX()-25);
						outputAServer1.println(mover.getX()+","+mover.getY()+","+mover.getFile().getAbsolutePath());

						
					}
					
							
		
					
					
				}

				@Override
				public void keyReleased(KeyEvent e) {
					
					
				}
				
			});
			tfMensaje.addFocusListener( new FocusAdapter() { // Selecciona todo el texto del cuadro en cuanto se le da el foco del teclado
				@Override
				public void focusGained(FocusEvent e) {
					tfMensaje.selectAll();
				}
			});
			tfMensaje.addActionListener( new ActionListener() { // Evento de <enter> de textfield
				@Override
				public void actionPerformed(ActionEvent e) {
					outputAServer.println( tfMensaje.getText() );
					if (tfMensaje.getText().equals("fin")) {
						finComunicacion = true;
						
					}
					tfMensaje.setText( "" );
				}
			});
			addWindowListener( new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					outputAServer.println( "fin" );
					finComunicacion = true;
				}
			});
		}
	    public Personaje getP() {
			return mover;
		}
		public void setP(Personaje p) {
			this.mover = p;
		}
		public void lanzaCliente() {
	        try (Socket socket = new Socket( HOST, PUERTO )) {
	            
	           
	            
	            BufferedReader inputDesdeServer1 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	            outputAServer1=new PrintWriter(socket.getOutputStream(), true);
	            

	           do {
	            String recibido=inputDesdeServer1.readLine();
	            if(recibido!=null) {
	            String[] parts = recibido.split(",");
	            int x=Integer.parseInt(parts[0]);
	            int y=Integer.parseInt(parts[1]);
	            String path=parts[2];
	            
	            for(int i=0;anadidos.size()>i;i++) {
					if(anadidos.get(i).getFile().getAbsolutePath().equals(path)) {
						System.out.println(recibido);		
						anadidos.get(i).setLocation(x,y); // set the horizontal alignement on the x axis !
	
					}
				}
	            }else {
	           finComunicacion=true;
	            }
	           }while (!finComunicacion);
	        } catch (IOException e) {
            	taEstado.append( "Error en cliente: " + e.getMessage() + "\n" );
	        }
	        taEstado.append( "Fin de proceso de cliente.\n" );
	        System.out.println( "Cerrando ventana cliente " + nombre + " en 2 segundos..." );
	        if (finComunicacion) {
	        	try { Thread.sleep( 2000 ); } catch (InterruptedException e) {}
	        	dispose();
	        }
	    }
	}
	    
	@SuppressWarnings("serial")
	public static class VentanaServidor extends JFrame {
		JLabel lEstado = new JLabel( " " );
		JTextArea taMensajes = new JTextArea( 10, 1 );
        boolean finComunicacion = false;
		// *VARIOS CLIENTES*
		// Como el servidor va a gestionar varios clientes hacemos una lista de sockets en lugar de solo uno, y una lista de salidas para mensajes de difusión
        ArrayList<Socket> lSockets = new ArrayList<>(); 
        ArrayList<PrintWriter> lSalidas = new ArrayList<>();
        ArrayList<PrintWriter> lper= new ArrayList<>();
        ArrayList<PrintWriter> lper1= new ArrayList<>();
        int numCliente = 0;  // Añadimos un número de cliente para saber cuántos se conectan
		public VentanaServidor() {
			setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
			setSize( 400, 300 );
			setLocation( 400, 0 );
			setTitle( "Ventana servidor" );
			taMensajes.setEditable(  false );
			getContentPane().add( new JScrollPane(taMensajes), BorderLayout.CENTER );
			getContentPane().add( lEstado, BorderLayout.SOUTH );
			getContentPane().add( lEstado, BorderLayout.SOUTH );
			addWindowListener( new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					try {
						// *VARIOS CLIENTES*
						// Se cierran todos los sockets abiertos 
						for (Socket socket : lSockets) socket.close();
					} catch (IOException e1) {
			    		lEstado.setText("Error en servidor: " + e1.getMessage());
					}
					finComunicacion = true;
				}
			});
		}
		public void lanzaServidor() {
			// *VARIOS CLIENTES*
			// Como el servidor va a gestionar varios clientes, en lugar de abrir solo una conexión, abre repetidamente conexiones hasta final
			try(ServerSocket serverSocket = new ServerSocket( PUERTO )) {
				serverSocket.setSoTimeout( 5000 );  // Para que haya un timeout en el accept - por si cerramos la aplicación para que no se quede esperando de forma infinita
				while (!finComunicacion) {
					try {
						Socket socket = serverSocket.accept(); // Se queda esperando a una conexión con timeout
						// *VARIOS CLIENTES*
						// Cada vez que un cliente se conecta, se genera un HILO que hace la comunicación (la lectura) con ese cliente. Y el servidor sigue ejecutando para esperar a otro cliente
						lSockets.add( socket );
						numCliente++;
						Thread t = new Thread ( new Runnable() { @Override public void run() {
							int numC = numCliente;
							try {
								BufferedReader inputDesdeCliente1 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
								PrintWriter outputACliente1 = new PrintWriter(socket.getOutputStream(), true);
								lper.add( outputACliente1 );
								
					    
							    lEstado.setText( "Cliente " + numC + " conectado" );
							    
								 // Para mensajes de difusión
								while(!finComunicacion) {  // ciclo de lectura desde el cliente hasta que acabe la comunicación
								 // Ojo: bloqueante (este hilo se queda esperando)
									String location = inputDesdeCliente1.readLine();

									lEstado.setText(location);

									taMensajes.append( "[" + numC + "] " + location + "\n" );
									for (PrintWriter outputp : lper) {
										
										outputp.println(location);	;								
										
										}
									
								}
								
								
								lEstado.setText( "El cliente " + numC + " se ha desconectado." );
								socket.close();
								lSockets.remove( socket );
								lper.remove(outputACliente1);
							} catch(IOException e) {
								if (finComunicacion) {
									System.out.println( "Cerrada comunicación con cliente " + numC + " por cierre de servidor." );
								} else {
									e.printStackTrace();
								}
							}
						} } );
						t.start();
					} catch (SocketTimeoutException e) {
						// Timeout en socket servidor - se reintenta (en el mismo while)
					}
				}
			} catch(IOException e) {
				lEstado.setText("Error en servidor: " + e.getMessage());
			}

		}
	}

}
