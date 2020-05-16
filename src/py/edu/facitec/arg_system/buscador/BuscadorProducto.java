package py.edu.facitec.arg_system.buscador;

import java.awt.EventQueue;

import javax.swing.JDialog;

import py.edu.facitec.arg_system.controlador.BuscadorProductoController;

public class BuscadorProducto extends BuscadorGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscadorProducto dialog = new BuscadorProducto();
					dialog.setUpController();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private BuscadorProductoController controller;
	
	public BuscadorProductoController getController() {
		return controller;
	}
	
	public void setUpController() {
		controller = new BuscadorProductoController(this);
	}

	/**
	 * Create the dialog.
	 */
	public BuscadorProducto() {
		
	}

	@Override
	protected String getTitulo() {
		return "Buscador de Productos";
	}

}
