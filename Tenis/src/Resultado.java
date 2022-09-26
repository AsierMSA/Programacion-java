
public class Resultado extends DatoTabular{
	private Torneo torneo;
	private String a�o;
	private Tenista ganador;
	private Tenista subcampeon;
	private String rangan;
	private String ransub;
	private String resultado;
	public Resultado( String a�o, Tenista ganador, Tenista subcampeon, String rangan, String ransub,
			String resultado) {
		super();
		this.a�o = a�o;
		this.ganador = ganador;
		this.subcampeon = subcampeon;
		this.rangan = rangan;
		this.ransub = ransub;
		this.resultado = resultado;
	}
	@Override
	
	public String toString() {
		return "Resultado ["+ "a�o=" + a�o + ", ganador=" + ganador + ", subcampeon=" + subcampeon
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
