package py.edu.facitec.arg_system.componente;

import java.awt.Font;
import java.awt.Insets;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class BotonesFormulario extends JButton {

	public BotonesFormulario() {
		setSize(50, 50);
		setHorizontalTextPosition(SwingConstants.RIGHT);// Texto a la derecha
		setVerticalTextPosition(SwingConstants.CENTER);// Texto Centrado
		setFocusPainted(false);
		setFont(new Font("Tahoma", Font.BOLD, 12));
		setMargin(new Insets(2, 20, 2, 20));
	}

	public void setText(String string) {
		setIcon(string);
		super.setText(string);
	}

	public void setIcon(String nombreIcono) {
		try {
			// se recupera el icono del paquete img.icono segun el texto
			URL url = BotonesPrincipales.class
					.getResource("/py/edu/facitec/arg_system/img/" + nombreIcono.toLowerCase() + ".png");
			// se asigna el icono al boton
			setIcon(new ImageIcon(url));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
