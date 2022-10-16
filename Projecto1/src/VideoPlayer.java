import java.awt.*;
import java.awt.event.*;
import java.beans.EventHandler;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uk.co.caprica.vlcj.factory.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;


/** Ventana principal de reproductor de vídeo
 * Utiliza la librería VLCj que debe estar instalada y configurada
 *     (http://www.capricasoftware.co.uk/projects/vlcj/index.html)
 * @author Andoni Eguíluz Morán
 * Facultad de Ingeniería - Universidad de Deusto
 */
public class VideoPlayer extends JFrame {
	private static final long serialVersionUID = 1L;
	
	// Varible de ventana principal de la clase
	private static VideoPlayer miVentana;

	// Atributo de VLCj
	private EmbeddedMediaPlayerComponent mediaPlayerComponent;
	// Atributos manipulables de swing
	private JList<String> lCanciones = null;  // Lista vertical de vídeos del player
	private JProgressBar pbVideo = null;      // Barra de progreso del vídeo en curso
	private JCheckBox cbAleatorio = null;     // Checkbox de reproducción aleatoria
	private JLabel lMensaje = null;           // Label para mensaje de reproducción
	// Datos asociados a la ventana
	private ListaDeReproduccion listaRepVideos;  // Modelo para la lista de vídeos
	FileTime newLastModifiedTime = null;


	public VideoPlayer() {
		// Creación de datos asociados a la ventana (lista de reproducción)
		listaRepVideos = new ListaDeReproduccion();
		
		// Creación de componentes/contenedores de swing
		lCanciones = new JList<String>( listaRepVideos );
		pbVideo = new JProgressBar( 0, 10000 );
		cbAleatorio = new JCheckBox("Rep. aleatoria");
		Locale loc = new Locale("en", "US");
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, loc);
		lMensaje = new JLabel( dateFormat.format(new Date())  );
		JPanel pBotonera = new JPanel();
		JButton bAnyadir = new JButton( new ImageIcon( VideoPlayer.class.getResource("img/Button Add.png")) );
		JButton bAtras = new JButton( new ImageIcon( VideoPlayer.class.getResource("img/Button Rewind.png")) );
		JButton bPausaPlay = new JButton( new ImageIcon( VideoPlayer.class.getResource("img/Button Play Pause.png")) );
		JButton bAdelante = new JButton( new ImageIcon( VideoPlayer.class.getResource("img/Button Fast Forward.png")) );
		JButton bMaximizar = new JButton( new ImageIcon( VideoPlayer.class.getResource("img/Button Maximize.png")) );
		
		// Componente de VCLj
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();

		// Configuración de componentes/contenedores
		setTitle("Video Player - Deusto Ingeniería");
		setLocationRelativeTo( null );  // Centra la ventana en la pantalla
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setSize( 800, 600 );
		lCanciones.setPreferredSize( new Dimension( 200,  500 ) );
		pBotonera.setLayout( new FlowLayout( FlowLayout.LEFT ));
		
		// Enlace de componentes y contenedores
		pBotonera.add( bAnyadir );
		pBotonera.add( bAtras );
		pBotonera.add( bPausaPlay );
		pBotonera.add( bAdelante );
		pBotonera.add( bMaximizar );
		pBotonera.add( cbAleatorio );
		pBotonera.add( lMensaje );
		getContentPane().add( mediaPlayerComponent, BorderLayout.CENTER );
		getContentPane().add( pBotonera, BorderLayout.NORTH );
		getContentPane().add( pbVideo, BorderLayout.SOUTH );
		getContentPane().add( new JScrollPane( lCanciones ), BorderLayout.WEST );
		
		// Escuchadores
		bAnyadir.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				File fPath = pedirCarpeta();
				if (fPath==null) return;
			
				// TODO: pedir ficheros por ventana de entrada (JOptionPane)
				// ficheros = ...
				String ficheros = JOptionPane.showInputDialog(miVentana, "Nombre del video:");
				String nfic=path.concat(ficheros );
				File nFic=new File(nfic);
				System.out.println(nfic);
				listaRepVideos.add( nFic );
				lCanciones.repaint();
				
				
			}
		});
		bAtras.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lCanciones.setSelectionBackground(Color.LIGHT_GRAY);
				if(cbAleatorio.isSelected()) {
					paraVideo();
					listaRepVideos.irARandom();
					lanzaVideo();
				}
				else {
				paraVideo();
				listaRepVideos.irAAnterior();
				lanzaVideo();
				}
			}
		});
		bAdelante.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lCanciones.setSelectionBackground(Color.LIGHT_GRAY);
				if(cbAleatorio.isSelected()) {
					paraVideo();
					listaRepVideos.irARandom();
					lanzaVideo();
				}
				else {
				paraVideo();
				listaRepVideos.irASiguiente();
				lanzaVideo();
				}
			}
		});
		bPausaPlay.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (mediaPlayerComponent.mediaPlayer().status().isPlayable()) {
					if (mediaPlayerComponent.mediaPlayer().status().isPlaying()) {
						mediaPlayerComponent.mediaPlayer().controls().setPause(true);
						} else {
						// TODO: hacer play
					
							
					mediaPlayerComponent.mediaPlayer().controls().setPause(false);
						
				
				
			}
				}else {
					System.out.print("No se puede reproducir");
				}
			}
		});
		bMaximizar.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (mediaPlayerComponent.mediaPlayer().fullScreen().isFullScreen())
			        mediaPlayerComponent.mediaPlayer().fullScreen().set(false);
				else
					mediaPlayerComponent.mediaPlayer().fullScreen().set(true);
			}
		});
		addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				mediaPlayerComponent.mediaPlayer().controls().stop();
				mediaPlayerComponent.mediaPlayer().release();
			}
		});
		pbVideo.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {

				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				mediaPlayer.seek();
			
				
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		
		   
		    });
		mediaPlayerComponent.mediaPlayer().events().addMediaPlayerEventListener( 
			new MediaPlayerEventAdapter() {
				@Override
				public void finished(MediaPlayer mediaPlayer) {
					listaRepVideos.irASiguiente();
					lanzaVideo();
				}
				@Override
				public void error(MediaPlayer mediaPlayer) {
					lCanciones.setSelectionBackground(Color.RED);
					
//					lanzaVideo();

					lCanciones.repaint();
				}
			    @Override
			    public void timeChanged(MediaPlayer mediaPlayer, long newTime) {
					pbVideo.setValue( (int) ( 10000.0 * 
							mediaPlayerComponent.mediaPlayer().status().time() /
							mediaPlayerComponent.mediaPlayer().status().length() ) );
					pbVideo.repaint();
			    }
		});
		lCanciones.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (lCanciones.getValueIsAdjusting()) { 
					lCanciones.setSelectionBackground(Color.white);
					File ficVideo = listaRepVideos.getFic(lCanciones.getSelectedIndex());
					Path path= Paths.get(ficVideo.getAbsolutePath());
					 
					try {
						newLastModifiedTime = Files.readAttributes(path,
						            BasicFileAttributes.class).lastModifiedTime();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					 lMensaje.setText(newLastModifiedTime.toString());
					listaRepVideos.setFicSeleccionado(lCanciones.getSelectedIndex());
					
					mediaPlayerComponent.mediaPlayer().media().play(  
						ficVideo.getAbsolutePath() );
					}
					else {
						
						listaRepVideos.setFicSeleccionado(lCanciones.getSelectedIndex());
					}
				}
				
		       
		    
				
			
			
		});
	
	}
	

	//
	// Métodos sobre el player de vídeo
	//
	
	// Para la reproducción del vídeo en curso
	private void paraVideo() {
		if (mediaPlayerComponent.mediaPlayer()!=null)
			mediaPlayerComponent.mediaPlayer().controls().stop();
	}

	// Empieza a reproducir el vídeo en curso de la lista de reproducción
	private void lanzaVideo() {
		
		if (mediaPlayerComponent.mediaPlayer()!=null &&
			listaRepVideos.getFicSeleccionado()!=-1) {
			File ficVideo = listaRepVideos.getFic(listaRepVideos.getFicSeleccionado());
			Path path= Paths.get(ficVideo.getAbsolutePath());
			 
			try {
				newLastModifiedTime = Files.readAttributes(path,
				            BasicFileAttributes.class).lastModifiedTime();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 lMensaje.setText(newLastModifiedTime.toString());
			mediaPlayerComponent.mediaPlayer().media().play(  
				ficVideo.getAbsolutePath() );
			lCanciones.setSelectedIndex( listaRepVideos.getFicSeleccionado() );
		} else {
			lCanciones.setSelectedIndices( new int[] {} );
		}
	}
	
	// Pide interactivamente una carpeta para coger vídeos
	// (null si no se selecciona)
	private static File pedirCarpeta() {
		// TODO: Pedir la carpeta usando JFileChooser
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.setAcceptAllFileFilterUsed(false);
		int seleccion=fc.showOpenDialog(miVentana);
		
		 
		//Si el usuario, pincha en aceptar
		if(seleccion==JFileChooser.APPROVE_OPTION){
			 File fichero=fc.getCurrentDirectory();
			 return fichero;
	}else {
		return null;
	}
		
	}

		private static String ficheros;
		private static String path;
	/** Ejecuta una ventana de VideoPlayer.
	 * El path de VLC debe estar en la variable de entorno "vlc".
	 * Comprobar que la versión de 32/64 bits de Java y de VLC es compatible.
	 * @param args	Un array de dos strings. El primero es el nombre (con comodines) de los ficheros,
	 * 				el segundo el path donde encontrarlos.  Si no se suministran, se piden de forma interactiva. 
	 */
	public static void main(String[] args) {
		// Para probar carga interactiva descomentar o comentar la línea siguiente:
		args = new String[] { "*Pentatonix*.mp4", "test/res/" };
		if (args.length < 2) {
			// No hay argumentos: selección manual
			File fPath = pedirCarpeta();
			if (fPath==null) return;
			path = fPath.getAbsolutePath();
			ficheros="*Pentatonix*.mp4";
			// ficheros = ???
		} else {
			ficheros = args[0];
			path = args[1];
		}
		
		// Inicializar VLC.
		// Probar con el buscador nativo...
		boolean found = new NativeDiscovery().discover();
    	// System.out.println( LibVlc.INSTANCE.libvlc_get_version() );  // Visualiza versión de VLC encontrada
    	// Si no se encuentra probar otras opciones:
    	if (!found) {
			// Buscar vlc como variable de entorno
			String vlcPath = System.getenv().get( "vlc" );
			if (vlcPath==null) {  // Poner VLC a mano
	        	System.setProperty("jna.library.path", "C:\\Users\\Estudiar\\OneDrive\\Documents\\vlcj-4.7.1-dist\\vlcj-4.7.1");
			} else {  // Poner VLC desde la variable de entorno
				System.setProperty( "jna.library.path", vlcPath );
			}
		}
    	
    	// Lanzar ventana
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				miVentana = new VideoPlayer();
				// Descomentar esta línea y poner una ruta válida para ver un vídeo de ejemplo
				//miVentana.listaRepVideos.add( new File("C:\\Users\\Estudiar\\Desktop\\ejercicio 3\\Projecto1\\test\\res\\[Official Video] Daft Punk - Pentatonix.mp4") );	
				//miVentana.listaRepVideos.add( new File("C:\\Users\\Estudiar\\Desktop\\ejercicio 3\\Projecto1\\test\\res\\[Official Video] I Need Your Love - Pentatonix (Calvin Harris feat. Ellie Goulding Cover).mp4") );
				miVentana.setVisible( true );
				miVentana.listaRepVideos.add( path, ficheros );
				miVentana.listaRepVideos.irAUltimo();
				miVentana.lanzaVideo();
			}
		});
	}
	
}