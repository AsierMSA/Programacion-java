import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

public class VentanaGrandSlam extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton selec=new JButton("Seleccionar");
	public VentanaGrandSlam() {
		TablaDatos tb= new TablaDatos();
		JTabbedPane pestana= new JTabbedPane();
		pestana.addTab("Grand Slam", tb.getScrollpane());
		pestana.addTab("Tenistas",tb.getScrollpanet());
		listeners();
		add(selec, BorderLayout.SOUTH);
		add(pestana);
		setBounds(10,20,800,600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
public void listeners() {
	
	selec.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			VentanaGrandSlam v=new VentanaGrandSlam();
			
		}
		
		
	});
	addWindowListener(new WindowAdapter() {
		@Override
		public void windowClosing(WindowEvent e) {
			
		}
		
	});
	
}

}
