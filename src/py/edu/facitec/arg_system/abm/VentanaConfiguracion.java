package py.edu.facitec.arg_system.abm;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import py.edu.facitec.arg_system.controlador.VentanaConfiguracionController;

@SuppressWarnings("serial")
public class VentanaConfiguracion extends JDialog {

	private JTextField tfEmpresa, tfRuc, tfTelefono, tfDireccion, tfEmail;
	private JButton btnGuardar, btnCancelar;
	private JLabel lblAvisoEmpresa, lblAvisoTelefono, lblAvisoRuc, lblAvisoDireccion, lblAvisoEmail;
	private VentanaConfiguracionController controller;

	public void setUpController() {
		controller = new VentanaConfiguracionController(this);
	}

	public VentanaConfiguracion() {
		setBounds(100, 100, 450, 340);
		setTitle("Configuración");
		setLocationRelativeTo(this);
		setModal(true);
		getContentPane().setLayout(null);

		JLabel lblEmpresa = new JLabel("Empresa:");
		lblEmpresa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmpresa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmpresa.setBounds(10, 17, 75, 18);
		getContentPane().add(lblEmpresa);

		tfEmpresa = new JTextField();
		tfEmpresa.setBounds(97, 18, 327, 20);
		getContentPane().add(tfEmpresa);
		tfEmpresa.setColumns(10);

		JLabel lblRuc = new JLabel("R.U.C. :");
		lblRuc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRuc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRuc.setBounds(10, 63, 75, 14);
		getContentPane().add(lblRuc);

		tfRuc = new JTextField();
		tfRuc.setBounds(95, 62, 160, 20);
		getContentPane().add(tfRuc);
		tfRuc.setColumns(10);

		JLabel lblTlefono = new JLabel("Tel\u00E9fono:");
		lblTlefono.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTlefono.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTlefono.setBounds(10, 110, 75, 14);
		getContentPane().add(lblTlefono);

		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDireccin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDireccin.setBounds(10, 155, 75, 14);
		getContentPane().add(lblDireccin);

		tfTelefono = new JTextField();
		tfTelefono.setColumns(10);
		tfTelefono.setBounds(95, 109, 160, 20);
		getContentPane().add(tfTelefono);

		tfDireccion = new JTextField();
		tfDireccion.setColumns(10);
		tfDireccion.setBounds(95, 154, 329, 20);
		getContentPane().add(tfDireccion);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(10, 203, 75, 14);
		getContentPane().add(lblEmail);

		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(95, 202, 329, 20);
		getContentPane().add(tfEmail);

		btnGuardar = new JButton("Guardar");
		btnGuardar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					controller.guardar();
			}
		});
		btnGuardar.setIcon(
				new ImageIcon(VentanaConfiguracion.class.getResource("/py/edu/facitec/arg_system/img/actualizar.png")));
		btnGuardar.setBounds(166, 257, 133, 33);
		getContentPane().add(btnGuardar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(
				new ImageIcon(VentanaConfiguracion.class.getResource("/py/edu/facitec/arg_system/img/cancel.png")));
		btnCancelar.setBounds(309, 257, 115, 33);
		getContentPane().add(btnCancelar);

		lblAvisoEmpresa = new JLabel("* Campo obligat\u00F3rio");
		lblAvisoEmpresa.setVisible(false);
		lblAvisoEmpresa.setForeground(Color.RED);
		lblAvisoEmpresa.setBounds(97, 41, 202, 14);
		getContentPane().add(lblAvisoEmpresa);

		lblAvisoRuc = new JLabel("* Campo obligat\u00F3rio");
		lblAvisoRuc.setVisible(false);
		lblAvisoRuc.setForeground(Color.RED);
		lblAvisoRuc.setBounds(97, 85, 202, 14);
		getContentPane().add(lblAvisoRuc);

		lblAvisoTelefono = new JLabel("* Campo obligat\u00F3rio");
		lblAvisoTelefono.setVisible(false);
		lblAvisoTelefono.setForeground(Color.RED);
		lblAvisoTelefono.setBounds(97, 131, 202, 14);
		getContentPane().add(lblAvisoTelefono);

		lblAvisoDireccion = new JLabel("* Campo obligat\u00F3rio");
		lblAvisoDireccion.setVisible(false);
		lblAvisoDireccion.setForeground(Color.RED);
		lblAvisoDireccion.setBounds(97, 177, 202, 14);
		getContentPane().add(lblAvisoDireccion);

		lblAvisoEmail = new JLabel("* Campo obligat\u00F3rio");
		lblAvisoEmail.setVisible(false);
		lblAvisoEmail.setForeground(Color.RED);
		lblAvisoEmail.setBounds(97, 225, 202, 14);
		getContentPane().add(lblAvisoEmail);

	}

	public JTextField getTfEmpresa() {
		return tfEmpresa;
	}

	public void setTfEmpresa(JTextField tfEmpresa) {
		this.tfEmpresa = tfEmpresa;
	}

	public JTextField getTfRuc() {
		return tfRuc;
	}

	public void setTfRuc(JTextField tfRuc) {
		this.tfRuc = tfRuc;
	}

	public JTextField getTfTelefono() {
		return tfTelefono;
	}

	public void setTfTelefono(JTextField tfTelefono) {
		this.tfTelefono = tfTelefono;
	}

	public JTextField getTfDireccion() {
		return tfDireccion;
	}

	public void setTfDireccion(JTextField tfDireccion) {
		this.tfDireccion = tfDireccion;
	}

	public JTextField getTfEmail() {
		return tfEmail;
	}

	public void setTfEmail(JTextField tfEmail) {
		this.tfEmail = tfEmail;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public JLabel getLblAvisoEmpresa() {
		return lblAvisoEmpresa;
	}

	public void setLblAvisoEmpresa(JLabel lblAvisoEmpresa) {
		this.lblAvisoEmpresa = lblAvisoEmpresa;
	}

	public JLabel getLblAvisoTelefono() {
		return lblAvisoTelefono;
	}

	public void setLblAvisoTelefono(JLabel lblAvisoTelefono) {
		this.lblAvisoTelefono = lblAvisoTelefono;
	}

	public JLabel getLblAvisoRuc() {
		return lblAvisoRuc;
	}

	public void setLblAvisoRuc(JLabel lblAvisoRuc) {
		this.lblAvisoRuc = lblAvisoRuc;
	}

	public JLabel getLblAvisoDireccion() {
		return lblAvisoDireccion;
	}

	public void setLblAvisoDireccion(JLabel lblAvisoDireccion) {
		this.lblAvisoDireccion = lblAvisoDireccion;
	}

	public JLabel getLblAvisoEmail() {
		return lblAvisoEmail;
	}

	public void setLblAvisoEmail(JLabel lblAvisoEmail) {
		this.lblAvisoEmail = lblAvisoEmail;
	}

}
