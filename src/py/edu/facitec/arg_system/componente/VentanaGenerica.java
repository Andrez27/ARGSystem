package py.edu.facitec.arg_system.componente;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import py.edu.facitec.arg_system.interfaz.AccionesABM;

@SuppressWarnings("serial")
public abstract class VentanaGenerica extends JDialog implements ActionListener {

	private JPanel PanelFormulario;
	private JtextFieldPersonalizado tfBuscador;
	private JButton btnCancelar;
	private JButton btnGuardar;
	private JLabel lblTitulo;
	private BotonesToolBarABM btnNuevo;
	private BotonesToolBarABM btnModificar;
	private AccionesABM accionesABM;
	private BotonesToolBarABM btnEliminar;
	private JToolBar toolBar;
	private JTable table;

	// va permitir recibir las implementaciones de la interfaz (CONTROLADORES)
	public void setAccionesABM(AccionesABM accionesABM) {
		this.accionesABM = accionesABM;
	}

	/**
	 * Create the dialog.
	 */
	public VentanaGenerica() {
		setTitle(getTitulo());
		setBounds(100, 100, 800, 600);
		setResizable(false);
		setLocationRelativeTo(this);
		setModal(true);
		getContentPane().setLayout(null);

		toolBar = new JToolBar();
		toolBar.setBounds(10, 11, 400, 100);
		toolBar.setFloatable(false);
		getContentPane().add(toolBar);

		btnNuevo = new BotonesToolBarABM();
		btnNuevo.setText("Nuevo");
		toolBar.add(btnNuevo);

		btnModificar = new BotonesToolBarABM();
		btnModificar.setText("Modificar");
		toolBar.add(btnModificar);

		btnEliminar = new BotonesToolBarABM();
		btnEliminar.setText("Eliminar");
		toolBar.add(btnEliminar);

		PanelFormulario = new JPanel();
		PanelFormulario.setBounds(10, 122, 400, 382);
		getContentPane().add(PanelFormulario);
		PanelFormulario.setLayout(null);

		JLabel lblBuscar = new JLabel("Buscar:");
		lblBuscar.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBuscar.setBounds(420, 97, 46, 14);
		getContentPane().add(lblBuscar);

		tfBuscador = new JtextFieldPersonalizado();
		tfBuscador.setBounds(469, 94, 315, 21);
		getContentPane().add(tfBuscador);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(
				new ImageIcon(VentanaGenerica.class.getResource("/py/edu/facitec/arg_system/img/cancelar.png")));
		btnCancelar.setBounds(59, 515, 130, 33);
		getContentPane().add(btnCancelar);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setIcon(
				new ImageIcon(VentanaGenerica.class.getResource("/py/edu/facitec/arg_system/img/guardar.png")));
		btnGuardar.setBounds(229, 515, 130, 33);
		getContentPane().add(btnGuardar);

		lblTitulo = new JLabel("Titulo");
		lblTitulo.setForeground(new Color(0, 128, 0));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Elephant", Font.BOLD, 35));
		lblTitulo.setBounds(420, 11, 364, 49);
		getContentPane().add(lblTitulo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(420, 123, 363, 379);
		getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		setearEventos();

	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	abstract protected String getTitulo();

	private void setearEventos() {
		btnNuevo.addActionListener(this);
		btnModificar.addActionListener(this);
		btnEliminar.addActionListener(this);
		btnCancelar.addActionListener(this);
		btnGuardar.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Nuevo":
			accionesABM.nuevo();
			break;
		case "Modificar":
			accionesABM.modificar();
			break;
		case "Actualizar":
			accionesABM.guardar();
			break;
		case "Eliminar":
			accionesABM.eliminar();
			break;
		case "Cancelar":
			accionesABM.cancelar();
			break;
		case "Guardar":
			accionesABM.guardar();
			break;
		}

	}

	public JPanel getPanelFormulario() {
		return PanelFormulario;
	}

	public void setPanelFormulario(JPanel panelFormulario) {
		PanelFormulario = panelFormulario;
	}

	public JtextFieldPersonalizado getTfBuscador() {
		return tfBuscador;
	}

	public void setTfBuscador(JtextFieldPersonalizado tfBuscador) {
		this.tfBuscador = tfBuscador;
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

	public JLabel getLblTitulo() {
		return lblTitulo;
	}

	public void setLblTitulo(JLabel lblTitulo) {
		this.lblTitulo = lblTitulo;
	}

	public BotonesToolBarABM getBtnNuevo() {
		return btnNuevo;
	}

	public void setBtnNuevo(BotonesToolBarABM btnNuevo) {
		this.btnNuevo = btnNuevo;
	}

	public BotonesToolBarABM getBtnModificar() {
		return btnModificar;
	}

	public void setBtnModificar(BotonesToolBarABM btnModificar) {
		this.btnModificar = btnModificar;
	}

	public BotonesToolBarABM getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(BotonesToolBarABM btnEliminar) {
		this.btnEliminar = btnEliminar;
	}

	public AccionesABM getAccionesABM() {
		return accionesABM;
	}

	public JToolBar getToolBar() {
		return toolBar;
	}

	public void setToolBar(JToolBar toolBar) {
		this.toolBar = toolBar;
	}

}
