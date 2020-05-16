package py.edu.facitec.arg_system.componente;
//package componentes.botones;
//
//import java.awt.Dimension;
//
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//
//public class MyButton extends JButton {
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	public MyButton() {
//		super("Texto del boton");//texto por defecto del boton
//		setMaximumSize(new Dimension(100,80));
//		setPreferredSize(new Dimension(100,80));
//		
//		setVerticalTextPosition(BOTTOM);//texto debajo
//		setHorizontalTextPosition(CENTER);//texto centrado
//	}
//	
//	@Override
//	public void setText(String text) {
//		super.setText(text);
//		setIconoPorTexto(text);
//	}
//	
//	public void setIconoPorTexto(String texto) {
//			try {
//				//se recupera el icono del paquete img.icono segun el texto
//				ImageIcon icono = new ImageIcon(MyButton.class.getResource("/img/"+texto.toLowerCase()+".png"));
//				//se asigna el icono al boton
//				setIcon(icono);
//				
//			} catch (Exception e) {}
//	}
//}
