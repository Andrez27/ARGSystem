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
import javax.swing.SwingConstants;

import py.edu.facitec.arg_system.componente.JtextFieldPersonalizado;
import py.edu.facitec.arg_system.controlador.ListadoClienteController;

@SuppressWarnings("serial")
public class VentanaListadoClientes extends JDialog {
	
	private JtextFieldPersonalizado tfDesdeNombre;
	private JtextFieldPersonalizado tfHastaNombre;
	private JLabel lblOrdenarPor;
	private JTable table;
	private JComboBox<String> cbOrder;
	private JButton btnFiltrar;
	private JButton btnCancelar;
	private JButton btnImprimir;
	private JLabel lblTotalRegistro;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaListadoClientes dialog = new VentanaListadoClientes();
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
		new ListadoClienteController(this);

	}

	/**
	 * Create the dialog.
	 */
	public VentanaListadoClientes() {
		setTitle("Listado de Clientes");
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(this);
		setModal(true);
		getContentPane().setLayout(null);
		
		JLabel lblDesdeNombre = new JLabel("Desde nombre:");
		lblDesdeNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDesdeNombre.setBounds(22, 22, 98, 14);
		getContentPane().add(lblDesdeNombre);
		
		JLabel lblHastaNombre = new JLabel("Hasta nombre:");
		lblHastaNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHastaNombre.setBounds(35, 47, 85, 14);
		getContentPane().add(lblHastaNombre);
		
		tfDesdeNombre = new JtextFieldPersonalizado();
		tfDesdeNombre.setBounds(130, 18, 125, 20);
		getContentPane().add(tfDesdeNombre);
		tfDesdeNombre.setColumns(10);
		
		tfHastaNombre = new JtextFieldPersonalizado();
		tfHastaNombre.setBounds(130, 43, 125, 20);
		getContentPane().add(tfHastaNombre);
		tfHastaNombre.setColumns(10);
		
		lblOrdenarPor = new JLabel("Ordenar por:");
		lblOrdenarPor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOrdenarPor.setBounds(486, 22, 85, 14);
		getContentPane().add(lblOrdenarPor);
		
		cbOrder = new JComboBox<String>();
		cbOrder.setModel(new DefaultComboBoxModel<String>(new String[] {"Id", "Nombre"}));
		cbOrder.setBounds(581, 19, 85, 20);
		getContentPane().add(cbOrder);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setIcon(new ImageIcon(VentanaListadoClientes.class.getResource("/py/edu/facitec/arg_system/img/buscador.png")));
		btnFiltrar.setBounds(676, 11, 98, 33);
		getContentPane().add(btnFiltrar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 100, 740, 402);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnImprimir = new JButton("Imprimir");
		btnImprimir.setEnabled(false);
		btnImprimir.setIcon(new ImageIcon(VentanaListadoClientes.class.getResource("/py/edu/facitec/arg_system/img/impresora.png")));
		btnImprimir.setBounds(646, 513, 116, 37);
		getContentPane().add(btnImprimir);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setEnabled(false);
		btnCancelar.setIcon(new ImageIcon(VentanaListadoClientes.class.getResource("/py/edu/facitec/arg_system/img/cerrar.png")));
		btnCancelar.setBounds(517, 513, 116, 37);
		getContentPane().add(btnCancelar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(22, 87, 740, 2);
		getContentPane().add(separator);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotal.setBounds(485, 47, 86, 14);
		getContentPane().add(lblTotal);
		
		lblTotalRegistro = new JLabel("0");
		lblTotalRegistro.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalRegistro.setBounds(581, 47, 52, 14);
		getContentPane().add(lblTotalRegistro);

	}

	public JtextFieldPersonalizado getTfDesdeNombre() {
		return tfDesdeNombre;
	}

	public void setTfDesdeNombre(JtextFieldPersonalizado tfDesdeNombre) {
		this.tfDesdeNombre = tfDesdeNombre;
	}

	public JtextFieldPersonalizado getTfHastaNombre() {
		return tfHastaNombre;
	}

	public void setTfHastaNombre(JtextFieldPersonalizado tfHastaNombre) {
		this.tfHastaNombre = tfHastaNombre;
	}

	public JLabel getLblOrdenarPor() {
		return lblOrdenarPor;
	}

	public void setLblOrdenarPor(JLabel lblOrdenarPor) {
		this.lblOrdenarPor = lblOrdenarPor;
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

	public JButton getBtnFiltrar() {
		return btnFiltrar;
	}

	public void setBtnFiltrar(JButton btnFiltrar) {
		this.btnFiltrar = btnFiltrar;
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

	public JLabel getLblTotalRegistro() {
		return lblTotalRegistro;
	}

	public void setLblTotalRegistro(JLabel lblTotalRegistro) {
		this.lblTotalRegistro = lblTotalRegistro;
	}

	
	
	
}
