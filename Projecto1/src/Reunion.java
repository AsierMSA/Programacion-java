
public class Reunion extends Tarea{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Reunion(String nombre, Hora hora) {
		super(nombre, hora);
	}
	@Override
	public String toString() {
		return "Reuni�n " + getNombre() + ", a las " + getHora()+"\n" ;
	}
	

}
