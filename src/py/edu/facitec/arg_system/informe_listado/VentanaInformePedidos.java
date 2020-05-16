package py.edu.facitec.arg_system.informe_listado;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import py.edu.facitec.arg_system.controlador.InformePedidosController;
import py.edu.facitec.arg_system.util.FechaUtil;

@SuppressWarnings("serial")
public class VentanaInformePedidos extends JDialog {
	
	private JTable table;
	private JTable tableDetalle;
	private JLabel lblTotalRegistro;
	private JButton btnFiltrar;
	private JFormattedTextField tfHastaFecha;
	private JFormattedTextField tfDesdeFecha;
	private JComboBox<String> cbTipoInforme;
	private JButton btnCancelar;
	private JButton btnImprimir;
	private JTextField tfCliente;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaInformePedidos dialog = new VentanaInformePedidos();
//					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//					//dialog.setUpController();
//					dialog.setVisible(true);
//					System.out.println("RDP-"+Math.random()*50);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public void setUpController() {
		new InformePedidosController(this);
	}

	/**
	 * Create the dialog.
	 */
	public VentanaInformePedidos() {
		setTitle("Informe de Pedidos");
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(this);
		setModal(true);
		getContentPane().setLayout(null);

		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setIcon(new ImageIcon(VentanaInformePedidos.class.getResource("/py/edu/facitec/arg_system/img/buscador.png")));
		btnFiltrar.setBounds(675, 28, 95, 33);
		getContentPane().add(btnFiltrar);

		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotal.setBounds(571, 37, 38, 14);
		getContentPane().add(lblTotal);

		lblTotalRegistro = new JLabel("0");
		lblTotalRegistro.setBounds(619, 37, 46, 14);
		getContentPane().add(lblTotalRegistro);

		tfHastaFecha = new JFormattedTextField(FechaUtil.getMascara());
		tfHastaFecha.setBounds(185, 16, 79, 20);
		getContentPane().add(tfHastaFecha);

		JLabel lblPeriodo = new JLabel("Periodo:");
		lblPeriodo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPeriodo.setBounds(10, 20, 60, 14);
		getContentPane().add(lblPeriodo);

		JLabel lblA = new JLabel("a");
		lblA.setHorizontalAlignment(SwingConstants.RIGHT);
		lblA.setBounds(159, 20, 15, 14);
		getContentPane().add(lblA);

		tfDesdeFecha = new JFormattedTextField(FechaUtil.getMascara());
		tfDesdeFecha.setBounds(78, 17, 79, 20);
		getContentPane().add(tfDesdeFecha);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 120, 760, 200);
		getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JLabel lblDetalleDePedido = new JLabel("Detalle de Pedido");
		lblDetalleDePedido.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblDetalleDePedido.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetalleDePedido.setBounds(274, 331, 250, 27);
		getContentPane().add(lblDetalleDePedido);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 369, 764, 136);
		getContentPane().add(scrollPane_1);

		tableDetalle = new JTable();
		scrollPane_1.setViewportView(tableDetalle);

		btnImprimir = new JButton("Imprimir");
		btnImprimir.setEnabled(false);
		btnImprimir.setIcon(new ImageIcon(VentanaInformePedidos.class.getResource("/py/edu/facitec/arg_system/img/impresora.png")));
		btnImprimir.setBounds(658, 513, 116, 37);
		getContentPane().add(btnImprimir);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setEnabled(false);
		btnCancelar.setIcon(new ImageIcon(VentanaInformePedidos.class.getResource("/py/edu/facitec/arg_system/img/cerrar.png")));
		btnCancelar.setBounds(529, 513, 116, 37);
		getContentPane().add(btnCancelar);

		cbTipoInforme = new JComboBox<String>();
		cbTipoInforme.setModel(new DefaultComboBoxModel<String>(new String[] { "Simples", "Detallado" }));
		cbTipoInforme.setBounds(410, 522, 103, 23);
		getContentPane().add(cbTipoInforme);

		JLabel lblTipoInforme = new JLabel("Informe:");
		lblTipoInforme.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipoInforme.setBounds(326, 527, 74, 14);
		getContentPane().add(lblTipoInforme);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(10, 11, 764, 2);
		getContentPane().add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(10, 78, 764, 2);
		getContentPane().add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 11, 1, 68);
		getContentPane().add(separator_2);

		JLabel lblPedido = new JLabel("Pedido");
		lblPedido.setHorizontalAlignment(SwingConstants.CENTER);
		lblPedido.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblPedido.setBounds(274, 82, 250, 27);
		getContentPane().add(lblPedido);

		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCliente.setBounds(10, 47, 60, 14);
		getContentPane().add(lblCliente);

		tfCliente = new JTextField();
		tfCliente.setBounds(78, 46, 186, 20);
		getContentPane().add(tfCliente);
		tfCliente.setColumns(10);

	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JTable getTableDetalle() {
		return tableDetalle;
	}

	public void setTableDetalle(JTable tableDetalle) {
		this.tableDetalle = tableDetalle;
	}

	public JLabel getLblTotalRegistro() {
		return lblTotalRegistro;
	}

	public void setLblTotalRegistro(JLabel lblTotalRegistro) {
		this.lblTotalRegistro = lblTotalRegistro;
	}

	public JButton getBtnFiltrar() {
		return btnFiltrar;
	}

	public void setBtnFiltrar(JButton btnFiltrar) {
		this.btnFiltrar = btnFiltrar;
	}

	public JFormattedTextField getTfHastaFecha() {
		return tfHastaFecha;
	}

	public void setTfHastaFecha(JFormattedTextField tfHastaFecha) {
		this.tfHastaFecha = tfHastaFecha;
	}

	public JFormattedTextField getTfDesdeFecha() {
		return tfDesdeFecha;
	}

	public void setTfDesdeFecha(JFormattedTextField tfDesdeFecha) {
		this.tfDesdeFecha = tfDesdeFecha;
	}

	public JComboBox<String> getCbTipoInforme() {
		return cbTipoInforme;
	}

	public void setCbTipoInforme(JComboBox<String> cbTipoInforme) {
		this.cbTipoInforme = cbTipoInforme;
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

	public JTextField getTfCliente() {
		return tfCliente;
	}

	public void setTfCliente(JTextField tfCliente) {
		this.tfCliente = tfCliente;
	}

}
