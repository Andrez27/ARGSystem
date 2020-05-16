package py.edu.facitec.arg_system.componente;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


import javax.swing.JTextField;

@SuppressWarnings("serial")
public class JtextFieldPersonalizado extends JTextField {

	public JtextFieldPersonalizado() {
		super();
		setFont(new Font("Tahoma", Font.BOLD, 12));
		setDisabledTextColor(Color.GRAY);
	}
	
	public void soloNumerosEnteros() {
		addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!Character.isDigit(e.getKeyChar()))e.consume();
			}
		});
		
	}
	
	public void soloLetras() {
		addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (Character.isDigit(e.getKeyChar()))e.consume();
			}
		});
		
	}
	
	
}