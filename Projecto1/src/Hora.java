import java.io.Serializable;

import javax.swing.JOptionPane;

public class Hora implements Serializable {

		/**
	 * 
	 */
	private static final long serialVersionUID = -6740155555250914659L;
		private int horas;
		private int minutos;

		public Hora() {
			
			String a = JOptionPane.showInputDialog("Introduce la hora hh:mm");
			
			if(a!=null) {
				try {
			String[] parts = a.split(":");
			horas=Integer.parseInt(parts[0]);
			minutos=Integer.parseInt(parts[1]);
				}catch(Exception i) {
					JOptionPane.showMessageDialog( null, "Introduzca la hora en el formato hh:mm");
					a = JOptionPane.showInputDialog("Introduce la hora hh:mm");
					String[] parts = a.split(":");
					horas=Integer.parseInt(parts[0]);
					minutos=Integer.parseInt(parts[1]);
					
					
				}
			if (horas > 23 || minutos < 0 || minutos > 59 || horas < 0) {
				throw new IllegalArgumentException();
			} else {
				this.setHora(horas);
				this.setMinuto(minutos);
			}
		}
		}

		/**
		 * @return hora
		 */
		public int getHora() {
			return horas;
		}

		/**
		 * @param hora
		 */
		public void setHora(int hora) {
			this.horas = hora;
		}

		/**
		 * @return minutos
		 */
		public int getMinuto() {
			return minutos;
		}

		/**
		 * @param minutos
		 */
		public void setMinuto(int minutos) {
			this.minutos = minutos;
		}

		@Override
		public String toString() {
			return horas+ ":"+ minutos;
		}
	}


