package py.edu.facitec.arg_system.informe_listado;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import py.edu.facitec.arg_system.controlador.ListadoProductoController;

@SuppressWarnings("serial")
public class VentanaListadoProductos extends JDialog {
	
	private JTextField tfDesdeCodigo;
	private JTextField tfHastaDescripcion;
	private JTextField tfDesdeDescripcion;
	private JTextField tfHastaCodigo;
	private JTable table;
	private JComboBox<String> cbOrder;
	private JButton btnCancelar;
	private JButton btnImprimir;
	private JButton btnFiltrar;
	private JLabel lblTotal;
	private JLabel lblTotalRegistro;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaListadoProductos dialog = new VentanaListadoProductos();
//					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//					dialog.setUpController();
//					dialog.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	public void setUpController() {
		new ListadoProductoController(this);

	}

	/**
	 * Create the dialog.
	 */
	public VentanaListadoProductos() {
		setTitle("Listado de Productos");
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(this);
		setModal(true);
		getContentPane().setLayout(null);
		
		JLabel lblDesdeCdigo = new JLabel("Desde c\u00F3digo:");
		lblDesdeCdigo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDesdeCdigo.setBounds(10, 23, 90, 14);
		getContentPane().add(lblDesdeCdigo);
		
		JLabel lblHastaCdigo = new JLabel("Hasta c\u00F3digo:");
		lblHastaCdigo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHastaCdigo.setBounds(10, 48, 90, 14);
		getContentPane().add(lblHastaCdigo);
		
		JLabel lblDesdeDescripcin = new JLabel("Desde descripci\u00F3n:");
		lblDesdeDescripcin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDesdeDescripcin.setBounds(199, 23, 110, 14);
		getContentPane().add(lblDesdeDescripcin);
		
		JLabel lblHastaDescripcin = new JLabel("Hasta descripci\u00F3n:");
		lblHastaDescripcin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHastaDescripcin.setBounds(199, 48, 110, 14);
		getContentPane().add(lblHastaDescripcin);
		
		tfDesdeCodigo = new JTextField();
		tfDesdeCodigo.setBounds(110, 21, 79, 17);
		getContentPane().add(tfDesdeCodigo);
		tfDesdeCodigo.setColumns(10);
		
		tfHastaDescripcion = new JTextField();
		tfHastaDescripcion.setBounds(319, 46, 150, 17);
		getContentPane().add(tfHastaDescripcion);
		tfHastaDescripcion.setColumns(10);
		
		tfDesdeDescripcion = new JTextField();
		tfDesdeDescripcion.setBounds(319, 21, 150, 17);
		getContentPane().add(tfDesdeDescripcion);
		tfDesdeDescripcion.setColumns(10);
		
		tfHastaCodigo = new JTextField();
		tfHastaCodigo.setBounds(110, 46, 79, 17);
		getContentPane().add(tfHastaCodigo);
		tfHastaCodigo.setColumns(10);
		
		JLabel lblOrdenarPor = new JLabel("Ordenar por:");
		lblOrdenarPor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOrdenarPor.setBounds(479, 23, 72, 14);
		getContentPane().add(lblOrdenarPor);
		
		cbOrder = new JComboBox<String>();
		cbOrder.setModel(new DefaultComboBoxModel<String>(new String[] {"Codigo", "Descripcion"}));
		cbOrder.setBounds(561, 20, 100, 20);
		getContentPane().add(cbOrder);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setIcon(new ImageIcon(VentanaListadoProductos.class.getResource("/py/edu/facitec/arg_system/img/buscador.png")));
		btnFiltrar.setBounds(671, 14, 103, 33);
		getContentPane().add(btnFiltrar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 80, 764, 2);
		getContentPane().add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 93, 764, 402);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnImprimir = new JButton("Imprimir");
		btnImprimir.setEnabled(false);
		btnImprimir.setIcon(new ImageIcon(VentanaListadoProductos.class.getResource("/py/edu/facitec/arg_system/img/impresora.png")));
		btnImprimir.setBounds(657, 507, 117, 43);
		getContentPane().add(btnImprimir);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setEnabled(false);
		btnCancelar.setIcon(new ImageIcon(VentanaListadoProductos.class.getResource("/py/edu/facitec/arg_system/img/cerrar.png")));
		btnCancelar.setBounds(527, 507, 117, 43);
		getContentPane().add(btnCancelar);
		
		lblTotal = new JLabel("Total:");
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotal.setBounds(505, 48, 46, 14);
		getContentPane().add(lblTotal);
		
		lblTotalRegistro = new JLabel("0");
		lblTotalRegistro.setBounds(561, 48, 59, 14);
		getContentPane().add(lblTotalRegistro);

	}

	public JTextField getTfDesdeCodigo() {
		return tfDesdeCodigo;
	}

	public void setTfDesdeCodigo(JTextField tfDesdeCodigo) {
		this.tfDesdeCodigo = tfDesdeCodigo;
	}

	public JTextField getTfHastaDescripcion() {
		return tfHastaDescripcion;
	}

	public void setTfHastaDescripcion(JTextField tfHastaDescripcion) {
		this.tfHastaDescripcion = tfHastaDescripcion;
	}

	public JTextField getTfDesdeDescripcion() {
		return tfDesdeDescripcion;
	}

	public void setTfDesdeDescripcion(JTextField tfDesdeDescripcion) {
		this.tfDesdeDescripcion = tfDesdeDescripcion;
	}

	public JTextField getTfHastaCodigo() {
		return tfHastaCodigo;
	}

	public void setTfHastaCodigo(JTextField tfHastaCodigo) {
		this.tfHastaCodigo = tfHastaCodigo;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JComboBox<String> getCbOrder() {
		return cbOrder;
	}

	public void setCbOrder(JComboBox<String> cbOrder) {
		this.cbOrder = cbOrder;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public JButton getBtnImprimir() {
		return btnImprimir;
	}

	public void setBtnImprimir(JButton btnImprimir) {
		this.btnImprimir = btnImprimir;
	}

	public JButton getBtnFiltrar() {
		return btnFiltrar;
	}

	public void setBtnFiltrar(JButton btnFiltrar) {
		this.btnFiltrar = btnFiltrar;
	}

	public JLabel getLblTotalRegistro() {
		return lblTotalRegistro;
	}

	public void setLblTotalRegistro(JLabel lblTotalRegistro) {
		this.lblTotalRegistro = lblTotalRegistro;
	}
	
	
	

}
