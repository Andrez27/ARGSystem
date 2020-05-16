package py.edu.facitec.arg_system.abm;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import py.com.cs.xnumberfield.component.NumberTextField;
import py.edu.facitec.arg_system.componente.BotonesPrincipales;
import py.edu.facitec.arg_system.componente.JtextFieldPersonalizado;
import py.edu.facitec.arg_system.componente.VentanaGenerica;
import py.edu.facitec.arg_system.controlador.VentanaProductoController;

@SuppressWarnings("serial")
public class VentanaProducto extends VentanaGenerica {
	private JtextFieldPersonalizado tfCodigo;
	private JtextFieldPersonalizado tfDescripcion;
	private JtextFieldPersonalizado tfGrupo;
	private JButton btnBuscador;
	private NumberTextField ntfPrecioCompra;
	private NumberTextField ntfPrecioVenta;
	private NumberTextField ntfPrecioMinimo;
	private JCheckBox cEstado;
	private JLabel lblAvisoDescripcion;
	private JLabel lblAvisoGrupo;
	private JLabel lblAvisoPrecioCompra;
	private JLabel lblAvisoPrecioVenta;
	private JLabel lblAvisoPrecioMinimo;
	private VentanaProductoController controller;
	private JLabel lblAvisoCodigo;

	public void setUpController() {
		controller = new VentanaProductoController(this);
	}

	public VentanaProducto() {
		getBtnEliminar().setText("Eliminar");
		getBtnModificar().setText("Modificar");
		getBtnNuevo().setText("Nuevo");
		getTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getLblTitulo().setText("Productos");

		getBtnGuardar().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					controller.guardar();
				}
			}
		});

		JLabel lblCodigo = new JLabel("C\u00F3digo:");
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodigo.setBounds(10, 11, 122, 14);
		getPanelFormulario().add(lblCodigo);

		JLabel lblDescripcion = new JLabel("Descripci\u00F3n:");
		lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescripcion.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDescripcion.setBounds(10, 65, 122, 14);
		getPanelFormulario().add(lblDescripcion);

		JLabel lblGrupo = new JLabel("Grupo:");
		lblGrupo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGrupo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGrupo.setBounds(10, 123, 122, 14);
		getPanelFormulario().add(lblGrupo);

		JLabel lblPrecioDeCompra = new JLabel("Precio de Compra:");
		lblPrecioDeCompra.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrecioDeCompra.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrecioDeCompra.setBounds(10, 207, 122, 14);
		getPanelFormulario().add(lblPrecioDeCompra);

		JLabel lblPrecioDeVenta = new JLabel("Precio de Venta:");
		lblPrecioDeVenta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrecioDeVenta.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrecioDeVenta.setBounds(10, 315, 122, 14);
		getPanelFormulario().add(lblPrecioDeVenta);

		JLabel lblPrecioDeMinmo = new JLabel("Precio de M\u00EDnimo:");
		lblPrecioDeMinmo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrecioDeMinmo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrecioDeMinmo.setBounds(10, 260, 122, 14);
		getPanelFormulario().add(lblPrecioDeMinmo);

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEstado.setBounds(10, 171, 122, 14);
		getPanelFormulario().add(lblEstado);

		tfCodigo = new JtextFieldPersonalizado();
		tfCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				lblAvisoCodigo.setVisible(false);
			}
		});

		tfCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == KeyEvent.VK_ENTER) {
					tfDescripcion.requestFocus();
					tfDescripcion.selectAll();
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				int k = (int) e.getKeyChar();
				if (!Character.isDigit(c) & c != KeyEvent.VK_ENTER & c != KeyEvent.VK_BACK_SPACE & k != 45) {
					e.consume();
					lblAvisoCodigo.setText("Sólo numeros");
					lblAvisoCodigo.setVisible(true);
				} else {
					lblAvisoCodigo.setVisible(false);
				}

				if (tfCodigo.getText().length() == 30) {
					e.consume();
					lblAvisoCodigo.setText("Sólo se permite ingresar 30 caráteres");
					lblAvisoCodigo.setVisible(true);
				}

				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					if (controller.verificarCodigo()) {
						JOptionPane.showMessageDialog(null, "Código duplicado!!");
						// lblAvisoCodigo.setVisible(true);
						tfCodigo.requestFocus();
					}
				}

			}
		});
		tfCodigo.setBounds(142, 8, 122, 17);
		getPanelFormulario().add(tfCodigo);

		tfDescripcion = new JtextFieldPersonalizado();
		tfDescripcion.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				controller.validarCampos();
				lblAvisoDescripcion.setVisible(false);
			}
		});
		tfDescripcion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					getBtnBuscador().requestFocus();
				}
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					controller.validarCampos();
					lblAvisoDescripcion.setVisible(true);
				} else {
					lblAvisoDescripcion.setVisible(false);
				}

			}

			@Override
			public void keyTyped(KeyEvent e) {
				if (tfDescripcion.getText().length() == 100) {
					e.consume();
					lblAvisoDescripcion.setText("Sólo se permite ingresar 100 caráteres");
					lblAvisoDescripcion.setVisible(true);
				} else {
					lblAvisoDescripcion.setVisible(false);
				}
			}
		});
		tfDescripcion.setBounds(142, 64, 248, 17);
		getPanelFormulario().add(tfDescripcion);

		ntfPrecioCompra = new NumberTextField();
		ntfPrecioCompra.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					getNtfPrecioMinimo().requestFocus();
				}

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					controller.verificarPrecio();
				} else {
					lblAvisoPrecioCompra.setVisible(false);
				}
			}
		});
		ntfPrecioCompra.setBounds(142, 206, 130, 17);
		getPanelFormulario().add(ntfPrecioCompra);

		ntfPrecioVenta = new NumberTextField();
		ntfPrecioVenta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					getBtnGuardar().requestFocus();
				}

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					controller.verificarPrecio();
				} else {
					lblAvisoPrecioVenta.setVisible(false);
				}
			}
		});
		ntfPrecioVenta.setBounds(142, 314, 130, 17);
		getPanelFormulario().add(ntfPrecioVenta);

		ntfPrecioMinimo = new NumberTextField();
		ntfPrecioMinimo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					getNtfPrecioVenta().requestFocus();
				}
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					controller.verificarPrecio();

				} else {
					lblAvisoPrecioMinimo.setVisible(false);
				}
			}
		});
		ntfPrecioMinimo.setBounds(142, 259, 130, 17);
		getPanelFormulario().add(ntfPrecioMinimo);

		tfGrupo = new JtextFieldPersonalizado();
		tfGrupo.setEditable(false);
		tfGrupo.setBounds(142, 122, 200, 17);
		getPanelFormulario().add(tfGrupo);

		BotonesPrincipales botonesPrincipales = new BotonesPrincipales();
		botonesPrincipales.setBounds(352, 95, 10, -14);
		getPanelFormulario().add(botonesPrincipales);

		btnBuscador = new JButton("");
		btnBuscador.setIcon(
				new ImageIcon(VentanaProducto.class.getResource("/py/edu/facitec/arg_system/img/buscador.png")));
		btnBuscador.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				lblAvisoGrupo.setVisible(false);
				getcEstado().requestFocus();
			}
		});
		btnBuscador.setBounds(352, 116, 38, 33);
		getPanelFormulario().add(btnBuscador);

		cEstado = new JCheckBox("Disponible");
		cEstado.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					getNtfPrecioCompra().requestFocus();
				}
			}
		});
		cEstado.setBounds(138, 168, 97, 23);
		getPanelFormulario().add(cEstado);

		lblAvisoDescripcion = new JLabel("");
		lblAvisoDescripcion.setForeground(Color.RED);
		lblAvisoDescripcion.setBounds(142, 91, 248, 14);
		getPanelFormulario().add(lblAvisoDescripcion);

		lblAvisoGrupo = new JLabel("");
		lblAvisoGrupo.setForeground(Color.RED);
		lblAvisoGrupo.setBounds(142, 147, 248, 14);
		getPanelFormulario().add(lblAvisoGrupo);

		lblAvisoPrecioCompra = new JLabel("");
		lblAvisoPrecioCompra.setForeground(Color.RED);
		lblAvisoPrecioCompra.setBounds(142, 234, 248, 14);
		getPanelFormulario().add(lblAvisoPrecioCompra);

		lblAvisoPrecioVenta = new JLabel("");
		lblAvisoPrecioVenta.setForeground(Color.RED);
		lblAvisoPrecioVenta.setBounds(142, 342, 248, 14);
		getPanelFormulario().add(lblAvisoPrecioVenta);

		lblAvisoPrecioMinimo = new JLabel("");
		lblAvisoPrecioMinimo.setToolTipText("");
		lblAvisoPrecioMinimo.setForeground(Color.RED);
		lblAvisoPrecioMinimo.setBounds(142, 287, 248, 14);
		getPanelFormulario().add(lblAvisoPrecioMinimo);

		lblAvisoCodigo = new JLabel("");
		lblAvisoCodigo.setToolTipText("");
		lblAvisoCodigo.setForeground(Color.RED);
		lblAvisoCodigo.setBounds(142, 36, 248, 14);
		getPanelFormulario().add(lblAvisoCodigo);

	}

	@Override
	protected String getTitulo() {
		return "Registro de Producto";
	}

	public JtextFieldPersonalizado getTfCodigo() {
		return tfCodigo;
	}

	public void setTfCodigo(JtextFieldPersonalizado tfCodigo) {
		this.tfCodigo = tfCodigo;
	}

	public JtextFieldPersonalizado getTfDescripcion() {
		return tfDescripcion;
	}

	public void setTfDescripcion(JtextFieldPersonalizado tfDescripcion) {
		this.tfDescripcion = tfDescripcion;
	}

	public JtextFieldPersonalizado getTfGrupo() {
		return tfGrupo;
	}

	public void setTfGrupo(JtextFieldPersonalizado tfGrupo) {
		this.tfGrupo = tfGrupo;
	}

	public JButton getBtnBuscador() {
		return btnBuscador;
	}

	public void setBtnBuscador(JButton btnBuscador) {
		this.btnBuscador = btnBuscador;
	}

	public NumberTextField getNtfPrecioCompra() {
		return ntfPrecioCompra;
	}

	public void setNtfPrecioCompra(NumberTextField ntfPrecioCompra) {
		this.ntfPrecioCompra = ntfPrecioCompra;
	}

	public NumberTextField getNtfPrecioVenta() {
		return ntfPrecioVenta;
	}

	public void setNtfPrecioVenta(NumberTextField ntfPrecioVenta) {
		this.ntfPrecioVenta = ntfPrecioVenta;
	}

	public NumberTextField getNtfPrecioMinimo() {
		return ntfPrecioMinimo;
	}

	public void setNtfPrecioMinimo(NumberTextField ntfPrecioMinimo) {
		this.ntfPrecioMinimo = ntfPrecioMinimo;
	}

	public JCheckBox getcEstado() {
		return cEstado;
	}

	public void setcEstado(JCheckBox cEstado) {
		this.cEstado = cEstado;
	}

	public JLabel getLblAvisoCodigo() {
		return lblAvisoCodigo;
	}

	public void setLblAvisoCodigo(JLabel lblAvisoCodigo) {
		this.lblAvisoCodigo = lblAvisoCodigo;
	}

	public JLabel getLblAvisoDescripcion() {
		return lblAvisoDescripcion;
	}

	public void setLblAvisoDescripcion(JLabel lblAvisoDescripcion) {
		this.lblAvisoDescripcion = lblAvisoDescripcion;
	}

	public JLabel getLblAvisoGrupo() {
		return lblAvisoGrupo;
	}

	public void setLblAvisoGrupo(JLabel lblAvisoGrupo) {
		this.lblAvisoGrupo = lblAvisoGrupo;
	}

	public JLabel getLblAvisoPrecioCompra() {
		return lblAvisoPrecioCompra;
	}

	public void setLblAvisoPrecioCompra(JLabel lblAvisoPrecioCompra) {
		this.lblAvisoPrecioCompra = lblAvisoPrecioCompra;
	}

	public JLabel getLblAvisoPrecioVenta() {
		return lblAvisoPrecioVenta;
	}

	public void setLblAvisoPrecioVenta(JLabel lblAvisoPrecioVenta) {
		this.lblAvisoPrecioVenta = lblAvisoPrecioVenta;
	}

	public JLabel getLblAvisoPrecioMinimo() {
		return lblAvisoPrecioMinimo;
	}

	public void setLblAvisoPrecioMinimo(JLabel lblAvisoPrecioMinimo) {
		this.lblAvisoPrecioMinimo = lblAvisoPrecioMinimo;
	}

}
