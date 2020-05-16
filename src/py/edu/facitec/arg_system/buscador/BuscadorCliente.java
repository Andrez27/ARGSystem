package py.edu.facitec.arg_system.buscador;

import java.awt.EventQueue;

import javax.swing.JDialog;

import py.edu.facitec.arg_system.controlador.BuscadorClienteController;

public class BuscadorCliente extends BuscadorGenerico {

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
					BuscadorCliente dialog = new BuscadorCliente();
					dialog.setUpController();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private BuscadorClienteController controller;

	public BuscadorClienteController getController() {
		return controller;
	}

	public void setUpController() {
		controller = new BuscadorClienteController(this);
	}

	/**
	 * Create the dialog.
	 */
	public BuscadorCliente() {

	}

	@Override
	protected String getTitulo() {
		return "Buscador de clientes";
	}

}
