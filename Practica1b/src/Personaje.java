import java.io.File;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Personaje extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ImageIcon imagen;
	public String mensaje;
	public int x;
	public int y;
	public File file;
	public Personaje(ImageIcon imagen,String mensaje,File file) {
		setIcon(imagen);
		setVisible(true);
		Random r = new Random();
		int low = 50;
		int high = 200;
		int result = r.nextInt(high-low) + low;
		
		x=result;
		y=result;
		this.file=file;
	}
	public ImageIcon getImagen() {
		return imagen;
	}
	public void setImagen(ImageIcon imagen) {
		this.imagen = imagen;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	@Override
	public String toString() {
		return "Personaje [imagen=" + imagen + ", mensaje=" + mensaje + ", x=" + x + ", y=" + y + "]";
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	
	
}
