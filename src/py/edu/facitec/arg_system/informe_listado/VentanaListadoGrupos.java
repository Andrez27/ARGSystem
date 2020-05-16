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
import py.edu.facitec.arg_system.controlador.ListadoGrupoController;

@SuppressWarnings("serial")
public class VentanaListadoGrupos extends JDialog {
	
	private JtextFieldPersonalizado tfDesdeDescripcion;
	private JtextFieldPersonalizado tfHastaDescripcion;
	private JComboBox<String> cbOrder;
	private JButton btnFiltrar;
	private JTable table;
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
//					VentanaListadoGrupos dialog = new VentanaListadoGrupos();
//					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//					dialog.setUpEvents();
//					dialog.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	public void setUpController() {
		new ListadoGrupoController(this);

	}

	/**
	 * Create the dialog.
	 */
	public VentanaListadoGrupos() {
		setTitle("Listado de Grupos");
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(this);
		setModal(true);
		getContentPane().setLayout(null);
		
		JLabel lblDesdeDescripcin = new JLabel("Desde descripci\u00F3n");
		lblDesdeDescripcin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDesdeDescripcin.setBounds(30, 30, 110, 14);
		getContentPane().add(lblDesdeDescripcin);
		
		JLabel lblHastaDescripcin = new JLabel("Hasta descripci\u00F3n");
		lblHastaDescripcin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHastaDescripcin.setBounds(30, 60, 110, 14);
		getContentPane().add(lblHastaDescripcin);
		
		tfDesdeDescripcion = new JtextFieldPersonalizado();
		tfDesdeDescripcion.setColumns(10);
		tfDesdeDescripcion.setBounds(150, 26, 125, 20);
		getContentPane().add(tfDesdeDescripcion);
		
		tfHastaDescripcion = new JtextFieldPersonalizado();
		tfHastaDescripcion.setColumns(10);
		tfHastaDescripcion.setBounds(150, 56, 125, 20);
		getContentPane().add(tfHastaDescripcion);
		
		JLabel label = new JLabel("Ordenar por:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(473, 30, 83, 14);
		getContentPane().add(label);
		
		cbOrder = new JComboBox<String>();
		cbOrder.setModel(new DefaultComboBoxModel<String>(new String[] {"Id", "Descripcion"}));
		cbOrder.setBounds(566, 27, 100, 20);
		getContentPane().add(cbOrder);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setIcon(new ImageIcon(VentanaListadoGrupos.class.getResource("/py/edu/facitec/arg_system/img/buscador.png")));
		btnFiltrar.setBounds(676, 21, 98, 33);
		getContentPane().add(btnFiltrar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 100, 764, 2);
		getContentPane().add(separator);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setEnabled(false);
		btnCancelar.setIcon(new ImageIcon(VentanaListadoGrupos.class.getResource("/py/edu/facitec/arg_system/img/cerrar.png")));
		btnCancelar.setBounds(527, 507, 117, 43);
		getContentPane().add(btnCancelar);
		
		btnImprimir = new JButton("Imprimir");
		btnImprimir.setEnabled(false);
		btnImprimir.setIcon(new ImageIcon(VentanaListadoGrupos.class.getResource("/py/edu/facitec/arg_system/img/impresora.png")));
		btnImprimir.setBounds(657, 507, 117, 43);
		getContentPane().add(btnImprimir);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 117, 764, 379);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotal.setBounds(473, 60, 83, 14);
		getContentPane().add(lblTotal);
		
		lblTotalRegistro = new JLabel("0");
		lblTotalRegistro.setBounds(566, 60, 83, 14);
		getContentPane().add(lblTotalRegistro);

	}

	public JtextFieldPersonalizado getTfDesdeDescripcion() {
		return tfDesdeDescripcion;
	}

	public void setTfDesdeDescripcion(JtextFieldPersonalizado tfDesdeDescripcion) {
		this.tfDesdeDescripcion = tfDesdeDescripcion;
	}

	public JtextFieldPersonalizado getTfHastaDescripcion() {
		return tfHastaDescripcion;
	}

	public void setTfHastaDescripcion(JtextFieldPersonalizado tfHastaDescripcion) {
		this.tfHastaDescripcion = tfHastaDescripcion;
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

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
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
