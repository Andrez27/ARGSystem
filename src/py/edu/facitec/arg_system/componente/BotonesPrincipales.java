package py.edu.facitec.arg_system.componente;

import java.awt.Color;
import java.awt.Dimension;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class BotonesPrincipales extends JButton {

	public BotonesPrincipales() {
		setMaximumSize(new Dimension(150, 150));
		setForeground(Color.WHITE); // Color del texto
		setHorizontalTextPosition(SwingConstants.CENTER);// Texto debajo
		setVerticalTextPosition(SwingConstants.BOTTOM);// Texto Centrado
		setBorderPainted(false);
		setBackground(new Color(240, 240, 240));
		setOpaque(false);// Quita el color de fondo
		setFocusable(false);
	}

	public void setText(String string) {
		setIcon(string);
		super.setText(string);
	}

	public void setIcon(String nombreIcono) {
		try {
			// se recupera el icono del paquete img.icono segun el texto
			URL url = BotonesPrincipales.class.getResource("/py/edu/facitec/arg_system/img/" + nombreIcono.toLowerCase() + ".png");
			// se asigna el icono al boton
			setIcon(new ImageIcon(url));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
