package py.edu.facitec.arg_system.componente;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

//Sirve para implementar fondo a la ventana principal
@SuppressWarnings("serial")
public class PanelFondo extends JPanel {

//	Image img;

	public PanelFondo() {
		setLayout(null);
//		try {
//			// se recupera la imagen de fondo del paquete img
//			ImageIcon icono = new ImageIcon(PanelFondo.class.getResource("/img/" + nombreImg));
//			img = icono.getImage();
//		} catch (Exception e) {
//		}
//		setOpaque(false);// le quita el color de fondo para visualizar la img
	}

	// ESTIRA LA IMAGEN DESDE LA CARPETA
	URL url = getClass().getResource("/py/edu/facitec/arg_system/img/fondo.jpg");
	Image img = new ImageIcon(url).getImage();

	// PASA LA IMAGEN A LA PANTALLA
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		setOpaque(false);
		super.paintComponent(g);
	}

//	@Override
//	public void paint(Graphics g) {// dibuja la imagen sobre el panel
//		if (img != null) {
//			int ancho = getWidth();
//			int alto = getHeight();
//			g.drawImage(img, 0, 0, ancho, alto, null);
//		}
//		super.paint(g);
//	}
}