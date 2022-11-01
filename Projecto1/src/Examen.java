
public class Examen extends Tarea {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Examen(String nombre, Hora hora) {
		super(nombre, hora);
	}
	

	@Override
	public String toString() {
		return "Examen " + getNombre() + ", a las " + getHora()+"\n" ;
	}
	

	

	
	
}
