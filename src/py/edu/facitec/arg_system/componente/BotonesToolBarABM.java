package py.edu.facitec.arg_system.componente;

import java.awt.Font;
import java.awt.Insets;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class BotonesToolBarABM extends JButton {

	public BotonesToolBarABM() {
		setSize(70, 70);
		setHorizontalTextPosition(SwingConstants.CENTER);// Texto debajo
		setVerticalTextPosition(SwingConstants.BOTTOM);// Texto Centrado
		setFocusPainted(false);
		setFont(new Font("Tahoma", Font.BOLD, 12));
		setMargin(new Insets(2, 30, 2, 30));
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
