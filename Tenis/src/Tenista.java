
public class Tenista extends DatoTabular{
	private String nombre;
	private String nacionalidad;
	private int nvic=0;
	
	public Tenista(String nombre, String nacionalidad) {
		super();
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		

	}
	@Override
	public String toString() {
		return "Tenista [nombre=" + nombre + ", nacionalidad=" + nacionalidad + ", nvic=" + nvic + "]";
	}
	public void getnumvic() {
		nvic=0;
		for (int i=0;i<main.getListares().size();i++) {

			if(main.getListares().get(i).getGanador().getNombre().equals(nombre)){
				nvic+=1;
				setNvic(nvic);
				
			}
		}
			}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getNvic() {
		return nvic;
	}
	public void setNvic(int nvic) {
		this.nvic = nvic;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	
	
}
