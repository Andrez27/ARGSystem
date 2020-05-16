package py.edu.facitec.arg_system.abm;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import py.edu.facitec.arg_system.componente.JtextFieldPersonalizado;
import py.edu.facitec.arg_system.componente.VentanaGenerica;
import py.edu.facitec.arg_system.controlador.VentanaClienteController;

@SuppressWarnings("serial")
public class VentanaCliente extends VentanaGenerica {

	private JtextFieldPersonalizado tfNombre, tfTelefono, tfDireccion, tfDocumento;
	private JLabel lblAvisoNombre, lblAvisoDocumento, lblAvisoTelefono, lblAvisoDireccion, lblDocumentoDuplicado;
	private VentanaClienteController controller;

	// enlaza la ventana con el controlador
	public void setUpController() {
		controller = new VentanaClienteController(this);
	}

	public VentanaCliente() {
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

		getLblTitulo().setText("Clientes");

		{
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNombre.setBounds(10, 30, 80, 14);
			getPanelFormulario().add(lblNombre);
		}
		{
			JLabel lblDocumento = new JLabel("Documento:");
			lblDocumento.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDocumento.setBounds(10, 80, 80, 14);
			getPanelFormulario().add(lblDocumento);
		}
		{
			JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
			lblTelfono.setHorizontalAlignment(SwingConstants.RIGHT);
			lblTelfono.setBounds(10, 130, 80, 14);
			getPanelFormulario().add(lblTelfono);
		}
		{
			JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
			lblDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDireccion.setBounds(12, 183, 80, 14);
			getPanelFormulario().add(lblDireccion);
		}

		tfNombre = new JtextFieldPersonalizado();
		tfNombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				lblAvisoNombre.setVisible(false);
			}
		});
		tfNombre.setToolTipText("");
		tfNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isDigit(c) & c != KeyEvent.VK_ENTER) {
					e.consume();
					lblAvisoNombre.setText("Sólo letras");
					lblAvisoNombre.setVisible(true);
				} else {
					lblAvisoNombre.setVisible(false);
				}

				if (tfNombre.getText().length() == 50) {
					e.consume();
					lblAvisoNombre.setText("No se permiten más caráteres");
					lblAvisoNombre.setVisible(true);
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == KeyEvent.VK_ENTER) {
					tfDocumento.requestFocus();
					tfDocumento.selectAll();
				}

			}
		});
		tfNombre.setBounds(100, 26, 260, 21);
		getPanelFormulario().add(tfNombre);

		tfDocumento = new JtextFieldPersonalizado();
		tfDocumento.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				lblAvisoDocumento.setVisible(false);
			}
		});

		tfDocumento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				int k = (int) e.getKeyChar();
				if (!Character.isDigit(c) & c != KeyEvent.VK_ENTER & c != KeyEvent.VK_BACK_SPACE & k != 45) {
					e.consume();
					lblAvisoDocumento.setText("Sólo números");
					lblAvisoDocumento.setVisible(true);
				} else {
					lblAvisoDocumento.setVisible(false);
				}

				if (tfDocumento.getText().length() == 15) {
					e.consume();
					lblAvisoDocumento.setText("No se permiten más carácteres");
					lblAvisoDocumento.setVisible(true);
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == KeyEvent.VK_ENTER) {
					if (controller.verificarDocumento()) {
						JOptionPane.showMessageDialog(null, "Documento duplicado!!", "ATENCIÓN!",
								JOptionPane.INFORMATION_MESSAGE);
						return;

					} else {
						tfTelefono.requestFocus();
						tfTelefono.selectAll();
					}

				}
			}

		});
		tfDocumento.setBounds(100, 76, 130, 18);
		getPanelFormulario().add(tfDocumento);

		tfTelefono = new JtextFieldPersonalizado();
		tfTelefono.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				lblAvisoTelefono.setVisible(false);
			}
		});

		tfTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				int k = (int) e.getKeyChar(); // 32= ESPACIO, 40= "(", 41= ")", 43= +, 45= -
				if (!Character.isDigit(c) & e.getKeyChar() != KeyEvent.VK_ENTER
						& e.getKeyChar() != KeyEvent.VK_BACK_SPACE & k != 32 & k != 43 & k != 40 & k != 41 & k != 45) {
					e.consume();
					lblAvisoTelefono.setText("Sólo numeros");
					lblAvisoTelefono.setVisible(true);
				} else {
					lblAvisoTelefono.setVisible(false);
				}

				if (tfTelefono.getText().length() == 20) {
					e.consume();
					lblAvisoTelefono.setText("No se permiten más carácteres");
					lblAvisoTelefono.setVisible(true);

				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
//				char c = e.getKeyChar();
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					tfDireccion.requestFocus();
					tfDireccion.selectAll();
				}
			}
		});
		tfTelefono.setBounds(100, 126, 130, 21);
		getPanelFormulario().add(tfTelefono);

		tfDireccion = new JtextFieldPersonalizado();
		tfDireccion.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				lblAvisoDireccion.setVisible(false);
			}
		});
		tfDireccion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
//				char c = e.getKeyChar();
				if (tfDireccion.getText().length() == 100) {
					e.consume();
					lblAvisoDireccion.setText("No se permiten más caráteres");
					lblAvisoDireccion.setVisible(true);
				} else {
					lblAvisoDireccion.setVisible(false);
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
//				char c = e.getKeyChar();
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					getBtnGuardar().requestFocus();
				}
			}
		});
		{
			lblAvisoDocumento = new JLabel("*S\u00F3lo numeros");
			lblAvisoDocumento.setForeground(Color.RED);
			lblAvisoDocumento.setVisible(false);
			lblAvisoDocumento.setBounds(100, 101, 260, 14);
			getPanelFormulario().add(lblAvisoDocumento);
		}
		{
			lblAvisoTelefono = new JLabel("*S\u00F3lo numeros");
			lblAvisoTelefono.setForeground(Color.RED);
			lblAvisoTelefono.setVisible(false);
			lblAvisoTelefono.setBounds(100, 151, 260, 14);
			getPanelFormulario().add(lblAvisoTelefono);
		}
		{
			lblAvisoDireccion = new JLabel("");
			lblAvisoDireccion.setForeground(Color.RED);
			lblAvisoDireccion.setVisible(false);
			lblAvisoDireccion.setBounds(101, 207, 260, 14);
			getPanelFormulario().add(lblAvisoDireccion);
		}

		tfDireccion.setBounds(101, 179, 260, 21);
		getPanelFormulario().add(tfDireccion);

		lblAvisoNombre = new JLabel("");
		lblAvisoNombre.setForeground(Color.RED);
		lblAvisoNombre.setBounds(100, 51, 260, 14);
		getPanelFormulario().add(lblAvisoNombre);
		{
			lblDocumentoDuplicado = new JLabel("");
			lblDocumentoDuplicado.setForeground(Color.RED);
			lblDocumentoDuplicado.setBounds(100, 101, 290, 14);
			getPanelFormulario().add(lblDocumentoDuplicado);
		}

	}

	// Se asigna el nombre de la ventana
	@Override
	protected String getTitulo() {
		return "Registro de Cliente";
	}

	// GETTERS AND SETTERS
	public JtextFieldPersonalizado getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(JtextFieldPersonalizado tfNombre) {
		this.tfNombre = tfNombre;
	}

	public JtextFieldPersonalizado getTfTelefono() {
		return tfTelefono;
	}

	public void setTfTelefono(JtextFieldPersonalizado tfTelefono) {
		this.tfTelefono = tfTelefono;
	}

	public JtextFieldPersonalizado getTfDireccion() {
		return tfDireccion;
	}

	public void setTfDireccion(JtextFieldPersonalizado tfDireccion) {
		this.tfDireccion = tfDireccion;
	}

	public JtextFieldPersonalizado getTfDocumento() {
		return tfDocumento;
	}

	public void setTfDocumento(JtextFieldPersonalizado tfDocumento) {
		this.tfDocumento = tfDocumento;
	}

	public JLabel getLblAvisoNombre() {
		return lblAvisoNombre;
	}

	public void setLblAvisoNombre(JLabel lblAvisoNombre) {
		this.lblAvisoNombre = lblAvisoNombre;
	}

	public JLabel getLblAvisoDocumento() {
		return lblAvisoDocumento;
	}

	public void setLblAvisoDocumento(JLabel lblAvisoDocumento) {
		this.lblAvisoDocumento = lblAvisoDocumento;
	}

	public JLabel getLblAvisoTelefono() {
		return lblAvisoTelefono;
	}

	public void setLblAvisoTelefono(JLabel lblAvisoTelefono) {
		this.lblAvisoTelefono = lblAvisoTelefono;
	}

	public JLabel getLblAvisoDireccion() {
		return lblAvisoDireccion;
	}

	public void setLblAvisoDireccion(JLabel lblAvisoDireccion) {
		this.lblAvisoDireccion = lblAvisoDireccion;
	}

	public JLabel getLblDocumentoDuplicado() {
		return lblDocumentoDuplicado;
	}

	public void setLblDocumentoDuplicado(JLabel lblDocumentoDuplicado) {
		this.lblDocumentoDuplicado = lblDocumentoDuplicado;
	}

}
