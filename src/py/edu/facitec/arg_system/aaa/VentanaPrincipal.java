package py.edu.facitec.arg_system.aaa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.KeyEventDispatcher;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import py.edu.facitec.arg_system.abm.VentanaCliente;
import py.edu.facitec.arg_system.abm.VentanaConfiguracion;
import py.edu.facitec.arg_system.abm.VentanaGrupo;
import py.edu.facitec.arg_system.abm.VentanaProducto;
import py.edu.facitec.arg_system.componente.BotonesPrincipales;
import py.edu.facitec.arg_system.componente.PanelFondo;
import py.edu.facitec.arg_system.controlador.VentanaClienteController;
import py.edu.facitec.arg_system.controlador.VentanaConfiguracionController;
import py.edu.facitec.arg_system.controlador.VentanaGrupoController;
import py.edu.facitec.arg_system.controlador.VentanaPedidoController;
import py.edu.facitec.arg_system.controlador.VentanaProductoController;
import py.edu.facitec.arg_system.dao.ConfiguracionDao;
import py.edu.facitec.arg_system.entidad.Configuracion;
import py.edu.facitec.arg_system.informe_listado.VentanaInformePedidos;
import py.edu.facitec.arg_system.informe_listado.VentanaListadoClientes;
import py.edu.facitec.arg_system.informe_listado.VentanaListadoGrupos;
import py.edu.facitec.arg_system.informe_listado.VentanaListadoProductos;
import py.edu.facitec.arg_system.transaccion.VentanaPedido;

@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame implements KeyEventDispatcher {

	private PanelFondo panelFondo;
	private List<Configuracion> configuracion;
	private ConfiguracionDao configuracionDao;
	private JPanel jPanelConfig;
	public static JLabel lblNombre, lblRuc, lblTelefono, lblEmail;

	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setExtendedState(MAXIMIZED_BOTH);
//		this.setPreferredSize(new Dimension(1366, 768));
		// setResizable(false);
		// setMaximumSize(new Dimension(1366, 768));
		this.setMinimumSize(new Dimension(800, 600));
		// setBounds(100, 100, 1366, 768);

		// setBounds(100, 100, 1600, 800);
		setLocationRelativeTo(this);// centra la ventana
		setTitle("ARG System - Sistema de Gestion de Pedidos V1.8");// Se asigna el nombre de la ventana

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnProcesos = new JMenu("Procesos");
		menuBar.add(mnProcesos);

		JMenuItem mntmPedidos = new JMenuItem("Pedidos");
		mntmPedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaPedido();
			}
		});
		mnProcesos.add(mntmPedidos);

		JMenu mnTablas = new JMenu("Tablas");
		menuBar.add(mnTablas);

		JMenuItem mntmCliente = new JMenuItem("Cliente");
		mntmCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaCliente();
			}
		});
		mnTablas.add(mntmCliente);

		JMenuItem mntmProducto = new JMenuItem("Producto");
		mntmProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaProducto();
			}
		});
		mnTablas.add(mntmProducto);

		JMenuItem mntmGrupo = new JMenuItem("Grupo");
		mntmGrupo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaGrupo();
			}
		});
		mnTablas.add(mntmGrupo);

		JMenu mnInformes = new JMenu("Informes");
		menuBar.add(mnInformes);

		JMenuItem mntmListadoDeCliente = new JMenuItem("Listado de Cliente");
		mntmListadoDeCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaListadoClientes();
			}
		});
		mnInformes.add(mntmListadoDeCliente);

		JMenuItem mntmListadoDeProducto = new JMenuItem("Listado de Producto");
		mntmListadoDeProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaListadoProductos();
			}
		});
		mnInformes.add(mntmListadoDeProducto);

		JMenuItem mntmListadoDeGrupo = new JMenuItem("Listado de Grupo");
		mntmListadoDeGrupo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaListadoGrupos();
			}
		});
		mnInformes.add(mntmListadoDeGrupo);

		JMenuItem mntmInformeDePedidos = new JMenuItem("Informe de Pedidos");
		mntmInformeDePedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaInformePedidos();
			}
		});
		mnInformes.add(mntmInformeDePedidos);

		JMenu mnUtilidades = new JMenu("Utilidades");
		menuBar.add(mnUtilidades);

		JMenuItem mntmConfiguraciones = new JMenuItem("Configuraciones");
		mntmConfiguraciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaConfiguracion();
			}
		});
		mnUtilidades.add(mntmConfiguraciones);

		JMenuItem mntmInicializacionDeDatos = new JMenuItem("Inicializaci\u00F3n de Datos");
		mntmInicializacionDeDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int respuesta = JOptionPane.showConfirmDialog(null,
						"Deseas restablecer la base de datos?\nSerán eliminados permanentemente todos los datos almacenados",
						"ATENCIÓN!", JOptionPane.YES_NO_OPTION);
				if (respuesta == JOptionPane.YES_OPTION) {
					inicializarDB();
//					cargarConfiguracion();
					JOptionPane.showMessageDialog(null, "La base de datos ha sido restablecida!");
				} else {
					JOptionPane.showMessageDialog(null, "Operación cancelada", "ATENCIÓN!",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		mnUtilidades.add(mntmInicializacionDeDatos);

		// Se asigna el fondo de pantalla

		panelFondo = new PanelFondo();
		panelFondo.setBackground(Color.BLACK);
		setContentPane(panelFondo);
		panelFondo.setLayout(new BorderLayout(0, 0));

//		PanelFondo panelFondo = new PanelFondo(("argsystem.jpg"));
//		panelFondo.setAlignmentY(Component.BOTTOM_ALIGNMENT);
//		getContentPane().add(panelFondo, BorderLayout.CENTER);
//		panelFondo.setLayout(null);
//		mnUtilidades.add(mntmConfiguraciones);

		JToolBar toolBar = new JToolBar();
		toolBar.setBackground(Color.WHITE);
		toolBar.setForeground(Color.WHITE);
		toolBar.setEnabled(false);
		toolBar.setFloatable(false);
		toolBar.setOpaque(false); // PARA COLOCAR TRANSPARENTE
		panelFondo.add(toolBar, BorderLayout.NORTH);

//		contentPane = new PanelFondo("argsystem.jpg");
//		contentPane.setBackground(Color.BLACK);
//		setContentPane(contentPane);
//		contentPane.setLayout(new BorderLayout(0, 0));

		BotonesPrincipales btnsprncplsPedidos = new BotonesPrincipales();
		btnsprncplsPedidos.setText("Pedidos");
		btnsprncplsPedidos.setIcon("pedidos");
		btnsprncplsPedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaPedido();
			}
		});
		btnsprncplsPedidos.setBounds(10, 11, 93, 93);
		toolBar.add(btnsprncplsPedidos);

		BotonesPrincipales btnsprncplsCliente = new BotonesPrincipales();
		btnsprncplsCliente.setText("Cliente");
		btnsprncplsCliente.setIcon("cliente");
		btnsprncplsCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaCliente();
			}
		});
		btnsprncplsCliente.setBounds(113, 11, 93, 93);
		toolBar.add(btnsprncplsCliente);

		BotonesPrincipales btnsprncplsProducto = new BotonesPrincipales();
		btnsprncplsProducto.setText("Producto");
		btnsprncplsProducto.setIcon("producto");
		btnsprncplsProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaProducto();
			}
		});
		btnsprncplsProducto.setBounds(216, 11, 93, 93);
		toolBar.add(btnsprncplsProducto);

		BotonesPrincipales btnsprncplsSalir = new BotonesPrincipales();
		btnsprncplsSalir.setText("Salir");
		btnsprncplsSalir.setIcon("salir");
		btnsprncplsSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnsprncplsSalir.setBounds(319, 11, 93, 93);
		toolBar.add(btnsprncplsSalir);

		jPanelConfig = new JPanel();
		jPanelConfig.setOpaque(false);
		panelFondo.add(jPanelConfig, BorderLayout.SOUTH);
		GridBagLayout gbl_jPanelConfig = new GridBagLayout();
		gbl_jPanelConfig.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_jPanelConfig.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_jPanelConfig.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_jPanelConfig.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		jPanelConfig.setLayout(gbl_jPanelConfig);

		lblNombre = new JLabel("");
		lblNombre.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblNombre.setHorizontalTextPosition(SwingConstants.LEFT);
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 33));
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.WEST;
		gbc_lblNombre.gridheight = 2;
		gbc_lblNombre.insets = new Insets(0, 0, 8, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 2;
		jPanelConfig.add(lblNombre, gbc_lblNombre);

		lblRuc = new JLabel("");
		lblRuc.setHorizontalTextPosition(SwingConstants.LEFT);
		lblRuc.setHorizontalAlignment(SwingConstants.LEFT);
		lblRuc.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblRuc.setForeground(Color.WHITE);
		lblRuc.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 28));
		GridBagConstraints gbc_lblRuc = new GridBagConstraints();
		gbc_lblRuc.anchor = GridBagConstraints.WEST;
		gbc_lblRuc.gridheight = 2;
		gbc_lblRuc.insets = new Insets(0, 0, 5, 5);
		gbc_lblRuc.gridx = 1;
		gbc_lblRuc.gridy = 4;
		jPanelConfig.add(lblRuc, gbc_lblRuc);

		lblTelefono = new JLabel("");
		lblTelefono.setForeground(Color.WHITE);
		lblTelefono.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 28));
		GridBagConstraints gbc_lblTelefono = new GridBagConstraints();
		gbc_lblTelefono.anchor = GridBagConstraints.WEST;
		gbc_lblTelefono.gridheight = 2;
		gbc_lblTelefono.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefono.gridx = 1;
		gbc_lblTelefono.gridy = 6;
		jPanelConfig.add(lblTelefono, gbc_lblTelefono);

		lblEmail = new JLabel("");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 28));
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.WEST;
		gbc_lblEmail.gridheight = 2;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 8;
		jPanelConfig.add(lblEmail, gbc_lblEmail);

		JLabel label = new JLabel("Desarrollado Por: Michel Britez ");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Arial", Font.BOLD, 17));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.SOUTHEAST;
		gbc_label.gridx = 3;
		gbc_label.gridy = 10;
		jPanelConfig.add(label, gbc_label);

		cargarConfiguracion();

	}

	// ---------------FORMULARIOS-------------------------------------------------------------
	public void abrirVentanaGrupo() {
		VentanaGrupo ventanaGrupo = new VentanaGrupo();
		ventanaGrupo.setUpController();
		ventanaGrupo.setVisible(true);
	}

	public void abrirVentanaCliente() {
		VentanaCliente ventanaCliente = new VentanaCliente();
		ventanaCliente.setUpController();
		ventanaCliente.setVisible(true);
	}

	public void abrirVentanaProducto() {
		VentanaProducto ventanaProducto = new VentanaProducto();
		ventanaProducto.setUpController();
		ventanaProducto.setVisible(true);
	}

	private void abrirVentanaPedido() {
		VentanaPedido ventanaPedido = new VentanaPedido();
		ventanaPedido.setUpController();
		ventanaPedido.setVisible(true);

	}

	private void abrirVentanaListadoClientes() {
		VentanaListadoClientes ventanaListadoClientes = new VentanaListadoClientes();
		ventanaListadoClientes.setUpController();
		ventanaListadoClientes.setVisible(true);
	}

	private void abrirVentanaListadoGrupos() {
		VentanaListadoGrupos ventanaListadoGrupos = new VentanaListadoGrupos();
		ventanaListadoGrupos.setUpController();
		ventanaListadoGrupos.setVisible(true);
	}

	private void abrirVentanaListadoProductos() {
		VentanaListadoProductos ventanaListadoProductos = new VentanaListadoProductos();
		ventanaListadoProductos.setUpController();
		ventanaListadoProductos.setVisible(true);
	}

	private void abrirVentanaInformePedidos() {
		VentanaInformePedidos ventanaInformePedidos = new VentanaInformePedidos();
		ventanaInformePedidos.setUpController();
		ventanaInformePedidos.setVisible(true);
	}

	private void abrirVentanaConfiguracion() {
		VentanaConfiguracion ventanaConfiguracion = new VentanaConfiguracion();
		ventanaConfiguracion.setUpController();
		ventanaConfiguracion.setVisible(true);

	}

	// ----------------DESACTIVAR FALLA TECLA
	// F10------------------------------------------
	@SuppressWarnings("static-access")
	public boolean dispatchKeyEvent(KeyEvent e) {
		if (e.getID() == e.KEY_PRESSED) {
			if (e.getKeyCode() == e.VK_F10 | e.getKeyCode() == e.VK_SPACE) {
				e.consume();
			}
		}
		return false;
	}

	// -------------------------------CONFIGURACION
	public void cargarConfiguracion() {
		configuracionDao = new ConfiguracionDao();
		configuracion = configuracionDao.recuperarTodo();
		if (configuracion.size() == 0)
			return;
		lblNombre.setText(configuracion.get(0).getEmpresa());
		lblRuc.setText(configuracion.get(0).getRuc());
		lblTelefono.setText(configuracion.get(0).getTelefono());
		lblEmail.setText(configuracion.get(0).getEmail());

	}

	// -------------------------------INICIALIZAR BASE DE DATOS

	public void inicializarDB() {
		VentanaGrupo ventanaGrupo = new VentanaGrupo();
		VentanaGrupoController vgc = new VentanaGrupoController(ventanaGrupo);
		vgc.inicializarGrupo();

		VentanaCliente ventanaCliente = new VentanaCliente();
		VentanaClienteController vcc = new VentanaClienteController(ventanaCliente);
		vcc.inicializarCliente();

		VentanaProducto ventanaProducto = new VentanaProducto();
		VentanaProductoController vpc = new VentanaProductoController(ventanaProducto);
		vpc.inicializarProducto();

		VentanaPedido ventanaPedido = new VentanaPedido();
		VentanaPedidoController vpedc = new VentanaPedidoController(ventanaPedido);
		vpedc.inicializarPedido();

		VentanaConfiguracion ventanaConfiguracion = new VentanaConfiguracion();
		VentanaConfiguracionController vconficc = new VentanaConfiguracionController(ventanaConfiguracion);
		vconficc.inicializarConfiguracion();

	}
}