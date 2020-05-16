package py.edu.facitec.arg_system.abm;

import java.awt.Color;

import javax.swing.JLabel;

import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import py.edu.facitec.arg_system.componente.JtextFieldPersonalizado;
import py.edu.facitec.arg_system.componente.VentanaGenerica;
import py.edu.facitec.arg_system.controlador.VentanaGrupoController;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



@SuppressWarnings("serial")
public class VentanaGrupo extends VentanaGenerica {
	private JtextFieldPersonalizado tfDescripcion;
	private JLabel lblAvisoDescripcion;
	private VentanaGrupoController controller;

	// enlaza la ventana con el controlador
	public void setUpController() {
		controller = new VentanaGrupoController(this);
	}

	public VentanaGrupoController getController() {
		return controller;
	}

	public VentanaGrupo() {
		getBtnEliminar().setText("Eliminar");
		getBtnModificar().setText("Modificar");
		getBtnNuevo().setText("Nuevo");
		getTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		getBtnGuardar().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					controller.guardar();
			}
		});

		getLblTitulo().setText("Grupos");

		JLabel lblDescripcin = new JLabel("Descripci\u00F3n:");
		lblDescripcin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescripcin.setBounds(10, 36, 80, 14);
		getPanelFormulario().add(lblDescripcin);

		tfDescripcion = new JtextFieldPersonalizado();
		tfDescripcion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
	
				if (e.getSource() == tfDescripcion & e.getKeyChar() == KeyEvent.VK_ENTER) {
					if (e.getKeyChar() == KeyEvent.VK_ENTER)
							getBtnGuardar().requestFocus();
				}
			}
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getSource() == tfDescripcion & Character.isDigit(e.getKeyChar())) {
					e.consume();
					lblAvisoDescripcion.setText("Sólo letras");
					lblAvisoDescripcion.setVisible(true);
				} else {
					lblAvisoDescripcion.setVisible(false);
				}
			
				if (tfDescripcion.getText().length() == 30) {
				
					e.consume();
					lblAvisoDescripcion.setText("*Sólo se permite ingresar 30 caráteres");
					lblAvisoDescripcion.setVisible(true);

				} else {
					lblAvisoDescripcion.setVisible(false);
				}
			
				
				if (e.getKeyChar() == KeyEvent.VK_ENTER)
					if (controller.verificarDescripcion()) {
						lblAvisoDescripcion.setText("*Descripción Duplicada!!");
						lblAvisoDescripcion.setVisible(true);

					}else {
						lblAvisoDescripcion.setVisible(false);
					}

			}
		});
		tfDescripcion.setBounds(100, 32, 290, 18);
		tfDescripcion.soloLetras();
		getPanelFormulario().add(tfDescripcion);

		lblAvisoDescripcion = new JLabel("*Solo letras");
		lblAvisoDescripcion.setVisible(false);
		lblAvisoDescripcion.setForeground(Color.RED);
		lblAvisoDescripcion.setBounds(100, 51, 290, 14);
		getPanelFormulario().add(lblAvisoDescripcion);
		
	}

	// Se asigna el nombre de la ventana
	@Override
	protected String getTitulo() {
		return "Registro de Grupos";
	}

	// GETTERS AND SETTERS
	public JtextFieldPersonalizado getTfDescripcion() {
		return tfDescripcion;
	}

	public void setTfDescripcion(JtextFieldPersonalizado tfDescripcion) {
		this.tfDescripcion = tfDescripcion;
	}

	public JLabel getLblAvisoDescripcion() {
		return lblAvisoDescripcion;
	}

	public void setLblAvisoDescripcion(JLabel lblAvisoDescripcion) {
		this.lblAvisoDescripcion = lblAvisoDescripcion;
	}

}
