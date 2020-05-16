package py.edu.facitec.arg_system.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import py.edu.facitec.arg_system.aaa.VentanaPrincipal;
import py.edu.facitec.arg_system.abm.VentanaConfiguracion;
import py.edu.facitec.arg_system.dao.ConfiguracionDao;
import py.edu.facitec.arg_system.entidad.Configuracion;

@SuppressWarnings("serial")
public class VentanaConfiguracionController extends JDialog {

	// private JTextComponent tfNombre, tfRuc, tfTelefono, tfEmail;
	private VentanaConfiguracion ventanaConfiguracion;
	private Configuracion configuracion;
	private ConfiguracionDao dao;
	private List<Configuracion> configuraciones;

	public VentanaConfiguracionController(VentanaConfiguracion ventanaConfiguracion) {
		this.ventanaConfiguracion = ventanaConfiguracion;

		cargarConfiguracion();

		setUpEvents();
	}

	private void setUpEvents() {

		ventanaConfiguracion.getTfEmpresa().addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == KeyEvent.VK_ENTER) {
					ventanaConfiguracion.getTfRuc().requestFocus();
					ventanaConfiguracion.getTfRuc().selectAll();
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				if (ventanaConfiguracion.getTfEmpresa().getText().length() == 40) {
					e.consume();
					ventanaConfiguracion.getLblAvisoEmpresa().setText("* No permitodo mas caracteres");
				} else {
					ventanaConfiguracion.getLblAvisoEmpresa().setVisible(false);
				}
			}
		});

		ventanaConfiguracion.getTfRuc().addKeyListener(new KeyAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				int k = (int) e.getKeyChar();
				if (!Character.isDigit(c) & c != e.VK_ENTER & c != e.VK_BACK_SPACE & k != 45) {
					e.consume();
					ventanaConfiguracion.getLblAvisoRuc().setText("* Caracter no permitido");
					ventanaConfiguracion.getLblAvisoRuc().setVisible(true);
				} else {
					ventanaConfiguracion.getLblAvisoRuc().setVisible(false);
				}
				if (ventanaConfiguracion.getTfRuc().getText().length() == 20) {
					e.consume();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == KeyEvent.VK_ENTER) {
					ventanaConfiguracion.getTfTelefono().requestFocus();
					ventanaConfiguracion.getTfTelefono().selectAll();
				}
			}
		});

		ventanaConfiguracion.getTfTelefono().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				int k = (int) e.getKeyChar(); // 32= ESPACIO, 40= (, 41= ), 43= +, 45= -
				if (!Character.isDigit(c) & c != KeyEvent.VK_ENTER & k != 8 & k != 32 & k != 40 & k != 41 & k != 43
						& k != 45) {
					e.consume();
					ventanaConfiguracion.getLblAvisoTelefono().setText("* Caracter inválido");
					ventanaConfiguracion.getLblAvisoTelefono().setVisible(true);
				} else {
					ventanaConfiguracion.getLblAvisoTelefono().setVisible(false);
				}
				if (ventanaConfiguracion.getTfTelefono().getText().length() == 50) { // PARA VALIDAR LA CANTIDAD DE
																						// DATOS A
					// ESCRIBIR
					e.consume();
					ventanaConfiguracion.getLblAvisoTelefono().setText("*No permitido más caracteres");
					ventanaConfiguracion.getLblAvisoTelefono().setVisible(true);
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == KeyEvent.VK_ENTER) {
					ventanaConfiguracion.getTfDireccion().requestFocus();
					ventanaConfiguracion.getTfDireccion().selectAll();
				}
			}
		});

		ventanaConfiguracion.getTfDireccion().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (ventanaConfiguracion.getTfDireccion().getText().length() == 100) { // PARA VALIDAR LA CANTIDAD DE
																						// DATOS A
																						// ESCRIBIR
					e.consume();
					ventanaConfiguracion.getLblAvisoDireccion().setText("* No permitido más datos");
					ventanaConfiguracion.getLblAvisoDireccion().setVisible(true);
				} else {
					ventanaConfiguracion.getLblAvisoDireccion().setVisible(false);
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == KeyEvent.VK_ENTER) {
					ventanaConfiguracion.getTfEmail().requestFocus();
				}
			}
		});

		ventanaConfiguracion.getTfEmail().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == KeyEvent.VK_ENTER) {
					ventanaConfiguracion.getBtnGuardar().requestFocus();
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				if (ventanaConfiguracion.getTfEmail().getText().length() == 50) { // PARA VALIDAR LA CANTIDAD DE DATOS A
					// ESCRIBIR
					e.consume();
					ventanaConfiguracion.getLblAvisoEmail().setText("* No permitido más datos");
					ventanaConfiguracion.getLblAvisoEmail().setVisible(true);
				} else {
					ventanaConfiguracion.getLblAvisoEmail().setVisible(false);
				}
			}
		});

		ventanaConfiguracion.getBtnGuardar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});

		ventanaConfiguracion.getBtnCancelar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelar();
			}
		});

	}

	public void guardar() {
		if (validarCampos() == false) {
			return;
		}

		configuracion = new Configuracion();
//		configuracion.setId(1);
		configuracion.setEmpresa(ventanaConfiguracion.getTfEmpresa().getText());
		configuracion.setRuc(ventanaConfiguracion.getTfRuc().getText());
		configuracion.setTelefono(ventanaConfiguracion.getTfTelefono().getText());
		configuracion.setDireccion(ventanaConfiguracion.getTfDireccion().getText());
		configuracion.setEmail(ventanaConfiguracion.getTfEmail().getText());

		dao = new ConfiguracionDao();
		dao.insertarOModificar(configuracion);

		try {
			dao.commit();
		} catch (Exception e) {
			e.printStackTrace();
			dao.rollback();
			JOptionPane.showMessageDialog(null, "Se produjo un error al actualizar datos!!!");
		}

		actualizarConfiguracion();
		ocultarLabels();
		JOptionPane.showMessageDialog(null, "Datos actualizados");

	}

	public void cancelar() {
		ventanaConfiguracion.dispose();
		ocultarLabels();
	}

	// private void cargarDatos() {
	// configuracion = new Configuracion();
	// configuracion.setId(1);
	// configuracion.setEmpresa(tfNombre.getText());
	// configuracion.setRuc(tfRuc.getText());
	// configuracion.setTelefono(tfTelefono.getText());
	// configuracion.setEmail(tfEmail.getText());
	// }

	private void actualizarConfiguracion() {
		dao = new ConfiguracionDao();
		configuracion = (Configuracion) dao.recuperarPorId(1);
		VentanaPrincipal.lblNombre.setText(configuracion.getEmpresa());
		VentanaPrincipal.lblRuc.setText(configuracion.getRuc());
		VentanaPrincipal.lblTelefono.setText(configuracion.getTelefono());
//		VentanaPrincipal.lblDireccion.setText(configuracion.getTelefono());
		VentanaPrincipal.lblEmail.setText(configuracion.getEmail());
	}

	private void cargarConfiguracion() {
		dao = new ConfiguracionDao();
		configuraciones = dao.recuperarTodo();
		if (configuraciones.size() == 0)
			return;
		ventanaConfiguracion.getTfEmpresa().setText(configuraciones.get(0).getEmpresa());
		ventanaConfiguracion.getTfRuc().setText(configuraciones.get(0).getRuc());
		ventanaConfiguracion.getTfTelefono().setText(configuraciones.get(0).getTelefono());
		ventanaConfiguracion.getTfEmail().setText(configuraciones.get(0).getEmail());
	}

	// ---------------------------------VALIDACION DE
	// CAMPOS-----------------------------------

	private boolean validarCampos() {
		if (ventanaConfiguracion.getTfEmpresa().getText().isEmpty()) {
			ventanaConfiguracion.getLblAvisoEmpresa().setText("* Campo Obligatorio!!");
			ventanaConfiguracion.getLblAvisoEmpresa().setVisible(true);
			ventanaConfiguracion.getTfEmpresa().requestFocus();

			return false;
		}
		
		if (ventanaConfiguracion.getTfRuc().getText().isEmpty()) {
			ventanaConfiguracion.getLblAvisoEmpresa().setText("* Campo Obligatorio!!");
			ventanaConfiguracion.getLblAvisoRuc().setVisible(true);
			ventanaConfiguracion.getTfRuc().requestFocus();

			return false;
		}
		
		if (ventanaConfiguracion.getTfTelefono().getText().isEmpty()) {
			ventanaConfiguracion.getLblAvisoEmpresa().setText("* Campo Obligatorio!!");
			ventanaConfiguracion.getLblAvisoTelefono().setVisible(true);
			ventanaConfiguracion.getTfTelefono().requestFocus();

			return false;
		}
		
		if (ventanaConfiguracion.getTfDireccion().getText().isEmpty()) {
			ventanaConfiguracion.getLblAvisoEmpresa().setText("* Campo Obligatorio!!");
			ventanaConfiguracion.getLblAvisoDireccion().setVisible(true);
			ventanaConfiguracion.getTfDireccion().requestFocus();

			return false;
		}
		
		if (ventanaConfiguracion.getTfEmail().getText().isEmpty()) {
			ventanaConfiguracion.getLblAvisoEmpresa().setText("* Campo Obligatorio!!");
			ventanaConfiguracion.getLblAvisoEmail().setVisible(true);
			ventanaConfiguracion.getTfEmail().requestFocus();

			return false;
		}
		
		return true;
	}

	private void ocultarLabels() {
		ventanaConfiguracion.getLblAvisoEmpresa().setVisible(false);
		ventanaConfiguracion.getLblAvisoDireccion().setVisible(false);
		ventanaConfiguracion.getLblAvisoEmail().setVisible(false);
		ventanaConfiguracion.getLblAvisoRuc().setVisible(false);
		ventanaConfiguracion.getLblAvisoTelefono().setVisible(false);
	}

	public void inicializarConfiguracion() {
		String tabla = "tb_configuracion";
		dao.eliminarTodo(tabla);
		try {
			dao.commit();
		} catch (Exception e) {
			dao.rollback();
		}
		VentanaPrincipal.lblNombre.setText("");
		VentanaPrincipal.lblRuc.setText("");
		VentanaPrincipal.lblTelefono.setText("");
		VentanaPrincipal.lblEmail.setText("");
	}

}