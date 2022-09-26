
public class Resultado extends DatoTabular{
	private Torneo torneo;
	private String año;
	private Tenista ganador;
	private Tenista subcampeon;
	private String rangan;
	private String ransub;
	private String resultado;
	public Resultado( String año, Tenista ganador, Tenista subcampeon, String rangan, String ransub,
			String resultado) {
		super();
		this.año = año;
		this.ganador = ganador;
		this.subcampeon = subcampeon;
		this.rangan = rangan;
		this.ransub = ransub;
		this.resultado = resultado;
	}
	@Override
	
	public String toString() {
		return "Resultado ["+ "año=" + año + ", ganador=" + ganador + ", subcampeon=" + subcampeon
				+ ", rangan=" + rangan + ", ransub=" + ransub + ", resultado=" + resultado + "]";
	}
	public Tenista getGanador() {
		return ganador;
	}
	public void setGanador(Tenista ganador) {
		this.ganador = ganador;
	}
	public Tenista getSubcampeon() {
		return subcampeon;
	}
	public void setSubcampeon(Tenista subcampeon) {
		this.subcampeon = subcampeon;
	}
	
}
