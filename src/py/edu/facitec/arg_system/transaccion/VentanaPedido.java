package py.edu.facitec.arg_system.transaccion;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import py.com.cs.xnumberfield.component.NumberTextField;
import py.edu.facitec.arg_system.componente.BotonesFormulario;
import py.edu.facitec.arg_system.componente.JtextFieldPersonalizado;
import py.edu.facitec.arg_system.controlador.VentanaPedidoController;
import py.edu.facitec.arg_system.util.FechaUtil;

public class VentanaPedido extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfCliente;
	private JTextField tfDireccion;
	private JFormattedTextField ftFecha;
	private JTextField tfTelefono;
	private JTextField tfProducto;
	private JTextField tfCodigoProducto;
	private JSpinner sCantidad;
	private JTable table;
	private BotonesFormulario btnNuevo;
	private JButton btnBuscarCliente;
	private JButton btnBuscarProducto;
	private JButton btnAgregar;
	private JTextField tfObs;
	private JTextField tfCiCliente;
	private NumberTextField ntfPrecio;
	private JButton btnCancelar;
	private JButton btnGuardar;
	private JButton btnQuitar;
	private JScrollPane scrollPane;
	private JLabel lblTotal;
	private JButton btnBuscarPedido;
	private JButton btnAnular;
	private JLabel lblAviso;
	private JtextFieldPersonalizado tfId;
	private JFormattedTextField ftHora;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//	EventQueue.invokeLater(new Runnable() {
//		public void run() {
//			try {
//				VentanaPedido dialog = new VentanaPedido();
//				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//				dialog.setUpController();
//				dialog.setVisible(true);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	});
//}

	public void setUpController() {
		new VentanaPedidoController(this);
	}

	/**
	 * Create the dialog.
	 */
	public VentanaPedido() {
		setBounds(100, 100, 950, 700);
		setTitle("Pedido");
		setLocationRelativeTo(this);
		setModal(true);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Formulario de Pedido");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(141, 14, 221, 20);
		getContentPane().add(lblNewLabel);

		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCliente.setBounds(10, 79, 90, 14);
		getContentPane().add(lblCliente);

		tfCliente = new JTextField();
		tfCliente.setEditable(false);
		tfCliente.setBounds(110, 76, 280, 17);
		getContentPane().add(tfCliente);
		tfCliente.setColumns(10);

		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDireccin.setBounds(10, 104, 90, 14);
		getContentPane().add(lblDireccin);

		tfDireccion = new JTextField();
		tfDireccion.setEditable(false);
		tfDireccion.setBounds(110, 101, 280, 17);
		getContentPane().add(tfDireccion);
		tfDireccion.setColumns(10);

		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFecha.setBounds(451, 25, 60, 14);
		getContentPane().add(lblFecha);

		ftFecha = new JFormattedTextField(FechaUtil.getMascara());
		ftFecha.setBounds(521, 23, 80, 17);
		getContentPane().add(ftFecha);

		btnNuevo = new BotonesFormulario();
		btnNuevo.setText("Nuevo");
		btnNuevo.setBounds(778, 14, 131, 40);
		getContentPane().add(btnNuevo);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 132, 899, 2);
		getContentPane().add(separator);

		JLabel lblNewLabel_1 = new JLabel("Ruc o CI:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(451, 76, 60, 14);
		getContentPane().add(lblNewLabel_1);

		JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelfono.setBounds(451, 101, 60, 14);
		getContentPane().add(lblTelfono);

		tfTelefono = new JTextField();
		tfTelefono.setEditable(false);
		tfTelefono.setBounds(521, 98, 150, 20);
		getContentPane().add(tfTelefono);
		tfTelefono.setColumns(10);

		btnBuscarCliente = new JButton("");
		btnBuscarCliente
				.setIcon(new ImageIcon(VentanaPedido.class.getResource("/py/edu/facitec/arg_system/img/buscador.png")));
		btnBuscarCliente.setBounds(687, 70, 47, 29);
		getContentPane().add(btnBuscarCliente);

		JLabel lblProducto = new JLabel("Producto:");
		lblProducto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProducto.setBounds(10, 155, 90, 14);
		getContentPane().add(lblProducto);

		tfProducto = new JTextField();
		tfProducto.setEditable(false);
		tfProducto.setBounds(110, 152, 280, 17);
		getContentPane().add(tfProducto);
		tfProducto.setColumns(10);

		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrecio.setBounds(10, 180, 90, 14);
		getContentPane().add(lblPrecio);

		ntfPrecio = new NumberTextField();
		ntfPrecio.setEditable(false);
		ntfPrecio.setBounds(110, 177, 100, 17);
		getContentPane().add(ntfPrecio);

		btnBuscarProducto = new JButton("");
		btnBuscarProducto
				.setIcon(new ImageIcon(VentanaPedido.class.getResource("/py/edu/facitec/arg_system/img/buscador.png")));
		btnBuscarProducto.setBounds(410, 145, 47, 29);
		getContentPane().add(btnBuscarProducto);

		JLabel lblCdigo = new JLabel("C\u00F3digo:");
		lblCdigo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCdigo.setBounds(494, 155, 72, 14);
		getContentPane().add(lblCdigo);

		tfCodigoProducto = new JTextField();
		tfCodigoProducto.setBounds(584, 152, 87, 20);
		getContentPane().add(tfCodigoProducto);
		tfCodigoProducto.setColumns(10);

		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCantidad.setBounds(494, 180, 72, 14);
		getContentPane().add(lblCantidad);

		sCantidad = new JSpinner();
		sCantidad.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		sCantidad.setBounds(584, 178, 87, 17);
		getContentPane().add(sCantidad);

		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(786, 176, 89, 23);
		getContentPane().add(btnAgregar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 210, 760, 335);

		table = new JTable();
		scrollPane.setViewportView(table);
		getContentPane().add(scrollPane);
		btnQuitar = new JButton("X");
		btnQuitar.setBounds(796, 210, 60, 23);
		getContentPane().add(btnQuitar);

		JLabel lblObs = new JLabel("Obs.:");
		lblObs.setBounds(10, 556, 90, 17);
		getContentPane().add(lblObs);

		tfObs = new JTextField();
		tfObs.setBounds(10, 584, 560, 69);
		getContentPane().add(tfObs);
		tfObs.setColumns(10);

		JLabel lblTotalGs = new JLabel("Total Gs:");
		lblTotalGs.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTotalGs.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalGs.setBounds(795, 487, 80, 20);
		getContentPane().add(lblTotalGs);

		btnCancelar = new JButton();
		btnCancelar.setText("Cancelar");
		btnCancelar.setBounds(660, 583, 90, 70);
		getContentPane().add(btnCancelar);

		btnGuardar = new JButton();
		btnGuardar.setText("Guardar");
		btnGuardar.setBounds(785, 583, 90, 70);
		getContentPane().add(btnGuardar);

		tfCiCliente = new JTextField();
		tfCiCliente.setBounds(521, 73, 150, 17);
		getContentPane().add(tfCiCliente);
		tfCiCliente.setColumns(10);

		lblTotal = new JLabel("0,0");
		lblTotal.setBackground(new Color(255, 255, 255));
		lblTotal.setFont(new Font("AR ESSENCE", Font.BOLD, 25));
		lblTotal.setBounds(796, 505, 128, 40);
		getContentPane().add(lblTotal);

		JLabel lblBuscarPedido = new JLabel("Buscar Pedido:");
		lblBuscarPedido.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBuscarPedido.setBounds(10, 50, 90, 14);
		getContentPane().add(lblBuscarPedido);

		btnBuscarPedido = new JButton("");
		btnBuscarPedido
				.setIcon(new ImageIcon(VentanaPedido.class.getResource("/py/edu/facitec/arg_system/img/buscador.png")));
		btnBuscarPedido.setBounds(173, 39, 47, 29);
		getContentPane().add(btnBuscarPedido);

		btnAnular = new JButton("Anular");
		btnAnular.setIcon(
				new ImageIcon(VentanaPedido.class.getResource("/py/edu/facitec/arg_system/img/anularpedido.png")));
		btnAnular.setBounds(650, 16, 100, 32);
		getContentPane().add(btnAnular);

		lblAviso = new JLabel("");
		lblAviso.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAviso.setForeground(Color.RED);
		lblAviso.setBounds(650, 50, 100, 14);
		getContentPane().add(lblAviso);

		tfId = new JtextFieldPersonalizado();
		tfId.setBounds(110, 44, 60, 22);
		getContentPane().add(tfId);

		JLabel lblHora = new JLabel("Hora:");
		lblHora.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHora.setBounds(451, 50, 60, 14);
		getContentPane().add(lblHora);

		ftHora = new JFormattedTextField(FechaUtil.getFormatoHora());
		ftHora.setEnabled(false);
		ftHora.setEditable(false);
		ftHora.setHorizontalAlignment(SwingConstants.CENTER);
		ftHora.setFocusLostBehavior(JFormattedTextField.REVERT);
		ftHora.setBounds(521, 48, 50, 17);
		getContentPane().add(ftHora);

	}

	public JtextFieldPersonalizado getTfId() {
		return tfId;
	}

	public void setTfId(JtextFieldPersonalizado tfId) {
		this.tfId = tfId;
	}

	public JTextField getTfCiCliente() {
		return tfCiCliente;
	}

	public void setTfCiCliente(JTextField tfCiCliente) {
		this.tfCiCliente = tfCiCliente;
	}

	public JTextField getTfCliente() {
		return tfCliente;
	}

	public void setTfCliente(JTextField tfCliente) {
		this.tfCliente = tfCliente;
	}

	public JTextField getTfDireccion() {
		return tfDireccion;
	}

	public void setTfDireccion(JTextField tfDireccion) {
		this.tfDireccion = tfDireccion;
	}

	public JTextField getTfCodigoProducto() {
		return tfCodigoProducto;
	}

	public void setTfCodigoProducto(JTextField tfCodigoProducto) {
		this.tfCodigoProducto = tfCodigoProducto;
	}

	public NumberTextField getNtfPrecio() {
		return ntfPrecio;
	}

	public void setNtfPrecio(NumberTextField ntfPrecio) {
		this.ntfPrecio = ntfPrecio;
	}

	public JFormattedTextField getFtFecha() {
		return ftFecha;
	}

	public void setFtFecha(JFormattedTextField ftFecha) {
		this.ftFecha = ftFecha;
	}

	public JTextField getTfTelefono() {
		return tfTelefono;
	}

	public void setTfTelefono(JTextField tfTelefono) {
		this.tfTelefono = tfTelefono;
	}

	public JTextField getTfProducto() {
		return tfProducto;
	}

	public void setTfProducto(JTextField tfProducto) {
		this.tfProducto = tfProducto;
	}

	public JTextField getTextField() {
		return tfCodigoProducto;
	}

	public void setTextField(JTextField textField) {
		this.tfCodigoProducto = textField;
	}

	public JSpinner getsCantidad() {
		return sCantidad;
	}

	public void setsCantidad(JSpinner sCantidad) {
		this.sCantidad = sCantidad;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public BotonesFormulario getBtnNuevo() {
		return btnNuevo;
	}

	public void setBtnNuevo(BotonesFormulario btnNuevo) {
		this.btnNuevo = btnNuevo;
	}

	public JButton getBtnBuscarCliente() {
		return btnBuscarCliente;
	}

	public void setBtnBuscarCliente(JButton btnBuscarCliente) {
		this.btnBuscarCliente = btnBuscarCliente;
	}

	public JButton getBtnBuscarProducto() {
		return btnBuscarProducto;
	}

	public void setBtnBuscarProducto(JButton btnBuscarProducto) {
		this.btnBuscarProducto = btnBuscarProducto;
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public void setBtnAgregar(JButton btnAgregar) {
		this.btnAgregar = btnAgregar;
	}

	public JTextField getTfObs() {
		return tfObs;
	}

	public void setTfObs(JTextField tfObs) {
		this.tfObs = tfObs;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public JButton getBtnQuitar() {
		return btnQuitar;
	}

	public void setBtnQuitar(JButton btnQuitar) {
		this.btnQuitar = btnQuitar;
	}

	public JLabel getLblTotal() {
		return lblTotal;
	}

	public void setLblTotal(JLabel lblTotal) {
		this.lblTotal = lblTotal;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public JButton getBtnBuscarPedido() {
		return btnBuscarPedido;
	}

	public void setBtnBuscarPedido(JButton btnBuscarPedido) {
		this.btnBuscarPedido = btnBuscarPedido;
	}

	public JButton getBtnAnular() {
		return btnAnular;
	}

	public void setBtnAnular(JButton btnAnular) {
		this.btnAnular = btnAnular;
	}

	public JLabel getLblAviso() {
		return lblAviso;
	}

	public void setLblAviso(JLabel lblAviso) {
		this.lblAviso = lblAviso;
	}

	public JFormattedTextField getFtHora() {
		return ftHora;
	}

	public void setFtHora(JFormattedTextField ftHora) {
		this.ftHora = ftHora;
	}

}
