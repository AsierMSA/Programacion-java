import java.io.Serializable;

public abstract class Tarea implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private Hora hora;
	
	public Tarea(String nombre, Hora hora) {
		super();
		this.nombre = nombre;
		this.hora = hora;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Hora getHora() {
		return hora;
	}
	public void setHora(Hora hora) {
		this.hora = hora;
	}
	@Override
	public String toString() {
		return "Tarea [nombre=" + nombre + ", hora=" + hora + "]";
	}
	
}
