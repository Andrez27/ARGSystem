package py.edu.facitec.arg_system.buscador;

import java.awt.EventQueue;

import javax.swing.JDialog;

import py.edu.facitec.arg_system.controlador.BuscadorGrupoController;

public class BuscadorGrupo extends BuscadorGenerico {

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
					BuscadorGrupo dialog = new BuscadorGrupo();
					dialog.setUpController();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private BuscadorGrupoController controller;

	public BuscadorGrupoController getController() {
		return controller;
	}

	public void setUpController() {
		controller = new BuscadorGrupoController(this);

	}

	/**
	 * Create the dialog.
	 */
	public BuscadorGrupo() {

	}

	@Override
	protected String getTitulo() {
		return "Buscador de grupos";
	}

}
