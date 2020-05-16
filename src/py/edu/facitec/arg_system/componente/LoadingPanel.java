package py.edu.facitec.arg_system.componente;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class LoadingPanel extends JPanel {

	URL url = getClass().getResource("/py/edu/facitec/arg_system/img/argsystem.jpg");
	Image image = new ImageIcon(url).getImage();

	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		setOpaque(false);
		super.paintComponent(g);
	}
}
