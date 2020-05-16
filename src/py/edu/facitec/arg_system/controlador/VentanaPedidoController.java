package py.edu.facitec.arg_system.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import py.edu.facitec.arg_system.buscador.BuscadorCliente;
import py.edu.facitec.arg_system.buscador.BuscadorProducto;
import py.edu.facitec.arg_system.dao.ClienteDao;
import py.edu.facitec.arg_system.dao.PedidoDao;
import py.edu.facitec.arg_system.dao.ProductoDao;
import py.edu.facitec.arg_system.entidad.Cliente;
import py.edu.facitec.arg_system.entidad.Pedido;
import py.edu.facitec.arg_system.entidad.PedidoDetalle;
import py.edu.facitec.arg_system.entidad.Producto;
import py.edu.facitec.arg_system.interfaz.BuscadorClienteInterface;
import py.edu.facitec.arg_system.interfaz.BuscadorProductoInterface;
import py.edu.facitec.arg_system.tabla.ModeloTablaPedidoDetalle;
import py.edu.facitec.arg_system.transaccion.VentanaPedido;
import py.edu.facitec.arg_system.util.EventosGenericos;
import py.edu.facitec.arg_system.util.FechaUtil;
import py.edu.facitec.arg_system.util.UtilidadesNumeros;

public class VentanaPedidoController implements KeyListener, BuscadorProductoInterface, BuscadorClienteInterface {
	private VentanaPedido vPedido;
	private List<PedidoDetalle> detalles;
	private Pedido pedido;
	private Cliente cliente;
	private ModeloTablaPedidoDetalle mtPedidoDetalle;
	private PedidoDetalle pedidoDetalle;
	private PedidoDao dao;
	private Producto producto;
	private ClienteDao ClienteDao;
	private List<Cliente> lista;
	private ProductoDao productoDao;
	private List<Producto> proLista;
	@SuppressWarnings("unused")
	private Date fechaYhora;

	// -------------------------------------METODO
	// CONSTRUCTOR----------------------------------
	// -------------------------------------RECIVE LAS
	// VENTANAS---------------------------------
	public VentanaPedidoController(VentanaPedido vPedido) {
		this.vPedido = vPedido;
		mtPedidoDetalle = new ModeloTablaPedidoDetalle();
		this.vPedido.getTable().setModel(mtPedidoDetalle);
		mtPedidoDetalle.fireTableDataChanged();

		dao = new PedidoDao();
		ClienteDao = new ClienteDao();
		productoDao = new ProductoDao();
		setUpEvents(); // Agrega los eventos
		estadoInicial(true);
		inicializarCampos();
		limpiar();

	}

	// -------------------------------------METODO
	// SET-UP-EVENTS----------------------------------
	// ------------------------PASA LAS ACCIONES A LOS COMPONENTES DE LA
	// VENTANA------------------
	private void setUpEvents() {

		vPedido.getTfId().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER)
					recuperarPedido();

			}
		});
		vPedido.getBtnNuevo().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nuevo();
			}
		});

		vPedido.getBtnCancelar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelar();
			}
		});

		vPedido.getBtnGuardar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});

		vPedido.getBtnAnular().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				anular();
			}
		});

		vPedido.getBtnBuscarPedido().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				recuperarPedido();
				vPedido.getTfId().setEnabled(false);
				vPedido.getBtnBuscarPedido().setEnabled(false);
			}
		});

		vPedido.getBtnBuscarCliente().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				abrirBuscadorCliente();
			}
		});

		vPedido.getBtnBuscarProducto().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				abrirBuscadorProducto();
			}
		});

		vPedido.getBtnAgregar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				agregarItem();
			}
		});

		vPedido.getBtnQuitar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				quitarItem(vPedido.getTable().getSelectedRow());
			}
		});

		vPedido.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					seleccionarItem(vPedido.getTable().getSelectedRow());
				}

			}
		});

		vPedido.getTfCiCliente().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				int k = (int) e.getKeyChar();
				if (!Character.isDigit(c) & c != KeyEvent.VK_ENTER & c != KeyEvent.VK_BACK_SPACE & k != 32 & k != 43
						& k != 40 & k != 41 & k != 45) {
					e.consume();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					if (recuperarCliente()) {

					} else {
						JOptionPane.showMessageDialog(null, "No encontrado!!");
					}
				}
			}
		});

		vPedido.getTfCodigoProducto().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c) & c != KeyEvent.VK_ENTER) {
					e.consume();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					if (recuperarProducto()) {
						vPedido.getBtnAgregar().setEnabled(true);
					} else {
						JOptionPane.showMessageDialog(null, "No encontrado!!");
					}
				}
			}
		});
	}

	// -------------------------------------ESTADO
	// INICIAL----------------------------------
	// -----------------PASA LOS ESTADOS A LOS COMPONENTES AL ABRIR LA
	// VENTANA--------------
	// -------------------------------Y AL TERMINAR UNA
	// ACCION------------------------------
	private void estadoInicial(boolean esInicial) {
		vPedido.getBtnNuevo().setEnabled(esInicial);
		vPedido.getBtnBuscarCliente().setEnabled(!esInicial);
		vPedido.getBtnBuscarProducto().setEnabled(!esInicial);
		vPedido.getTfCiCliente().setEnabled(!esInicial);
		vPedido.getsCantidad().setEnabled(!esInicial);
		vPedido.getTfCodigoProducto().setEnabled(!esInicial);
		vPedido.getBtnAgregar().setEnabled(!esInicial);
		vPedido.getBtnQuitar().setEnabled(!esInicial);
		vPedido.getTfId().setEnabled(esInicial);
		vPedido.getBtnBuscarPedido().setEnabled(esInicial);

		vPedido.getLblAviso().setVisible(!esInicial);

		vPedido.getBtnAnular().setEnabled(!esInicial);

		vPedido.getLblTotal().setEnabled(!esInicial);
		vPedido.getTfObs().setEnabled(!esInicial);
		vPedido.getBtnCancelar().setEnabled(!esInicial);
		vPedido.getBtnGuardar().setEnabled(!esInicial);
		vPedido.getLblTotal().setText("0,0");
		vPedido.getFtFecha().setEnabled(!esInicial);
		vPedido.getFtHora().setEnabled(!esInicial);

	}

	// -------------------------------------LIMPIAR
	// FECHA----------------------------------
	private void limpiar() {
		EventosGenericos.limpiarJtexField(vPedido.getContentPane());
		vPedido.getFtFecha().setValue(null);
		vPedido.getFtHora().setValue(null);
		detalles = new ArrayList<PedidoDetalle>();
		mtPedidoDetalle.setLista(detalles);
		vPedido.getTfObs().setText("");
		cliente = null;

	}

	// -------------------------------------NUEVO----------------------------------
	// --------------HABILITA LA VENTANA PARA INGRESAR UN NUEVO REGISTRO-----------
	public void nuevo() {
		vPedido.getBtnNuevo().setEnabled(false);
		vPedido.getBtnBuscarCliente().setEnabled(true);
		vPedido.getBtnBuscarProducto().setEnabled(true);
		vPedido.getsCantidad().setEnabled(true);
		vPedido.getFtFecha().setEnabled(true);
		vPedido.getTfCiCliente().setEnabled(true);
		vPedido.getTfCodigoProducto().setEnabled(true);
		vPedido.getBtnAgregar().setEnabled(false);
		vPedido.getBtnQuitar().setEnabled(false);
		vPedido.getBtnCancelar().setEnabled(true);
		vPedido.getBtnGuardar().setEnabled(true);
		vPedido.getLblTotal().setText("0,0");
		vPedido.getLblTotal().setEnabled(true);
		vPedido.getTfObs().setEnabled(true);

		vPedido.getFtFecha().setEnabled(true);
		vPedido.getFtFecha().setText(FechaUtil.convertirDateAString(new Date()));
		vPedido.getFtHora().setEnabled(true);
		vPedido.getFtHora().setValue(FechaUtil.convertirHoraAString(new Date()));

		vPedido.getTfId().setEnabled(false);
		vPedido.getBtnBuscarPedido().setEnabled(false);
		pedido = new Pedido();
		detalles = new ArrayList<PedidoDetalle>();

	}

	// -------------------------------------METODO
	// GUARDAR----------------------------------
	private void guardar() {
		if (cliente == null) {
			JOptionPane.showMessageDialog(null, "Seleccione un Cliente!!");
			return;
		}
		if (detalles.size() == 0) {
			JOptionPane.showMessageDialog(null, "No se permite guardar sin ningún Producto");
			return;
		}
		pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setTotal(UtilidadesNumeros.stringADouble(vPedido.getLblTotal().getText()));

		pedido.setFecha(FechaUtil
				.convertirStringFechaHora(vPedido.getFtHora().getText() + ":00 " + vPedido.getFtFecha().getText()));
		pedido.setObs(vPedido.getTfObs().getText());

		pedido.setPedidoDetalles(detalles);

		for (int i = 0; i < detalles.size(); i++) {
			detalles.get(i).setPedido(pedido);
		}

		try {
			dao.insertar(pedido);
			dao.commit();

			limpiar();
			inicializarCampos();
			estadoInicial(true);

		} catch (Exception e) {
			dao.rollback();
			e.printStackTrace();
		}

	}

	// -------------------------------------CANCELAR----------------------------------
	// ----------------CANCELA LA OPERACION Y VUELVE AL ESTADO
	// INICIAL----------------
	private void cancelar() {
		vPedido.getTfCiCliente().setText("");
		vPedido.getTfCliente().setText("");
		vPedido.getTfDireccion().setText("");
		vPedido.getTfTelefono().setText("");

		estadoInicial(true);
		limpiar();

	}

	// -------------------------------------METODO INICIALIZAR
	// CAMPOS----------------------------------
	private void inicializarCampos() {
		vPedido.getTfCiCliente().setText("");
		vPedido.getTfCliente().setText("");
		vPedido.getTfDireccion().setText("");
		vPedido.getTfTelefono().setText("");
		vPedido.getFtFecha().setValue(FechaUtil.convertirDateAString(new Date()));
		vPedido.getFtHora().setValue(FechaUtil.convertirHoraAString(new Date()));

		detalles = new ArrayList<PedidoDetalle>();
		mtPedidoDetalle.setLista(detalles);

	}

	// -------------------------------------METODO AGREGAR
	// ITEM----------------------------------

	private void agregarItem() {
		pedidoDetalle = new PedidoDetalle();
		pedidoDetalle.setProducto(this.producto);
		pedidoDetalle.setCantidad((int) vPedido.getsCantidad().getValue());
		pedidoDetalle.setSubtotal(pedidoDetalle.getCantidad() * this.producto.getPrecioVenta());
		detalles.add(pedidoDetalle);
		mtPedidoDetalle.setLista(detalles);
		producto = null;
		vPedido.getTfCodigoProducto().setText("");
		vPedido.getNtfPrecio().setText("");
		vPedido.getTfProducto().setText("");
		vPedido.getsCantidad().setValue(1);
		vPedido.getBtnAgregar().setEnabled(false);
		double total = 0;
		for (int i = 0; i < detalles.size(); i++) {
			total = total + (detalles.get(i).getProducto().getPrecioVenta() * detalles.get(i).getCantidad());
		}
		vPedido.getLblTotal().setText(UtilidadesNumeros.doubleString(total));
	}

	// -------------------------------------METODO SELECCIONAR
	// ITEM----------------------------------

	private void seleccionarItem(int index) {
//		if(index<0) return;
		pedidoDetalle = detalles.get(index);
		vPedido.getTfCodigoProducto().setText(pedidoDetalle.getProducto().getCodigo());
		vPedido.getTfProducto().setText(pedidoDetalle.getProducto().getDescripcion());
		vPedido.getsCantidad().setValue(pedidoDetalle.getCantidad());
		vPedido.getBtnQuitar().setEnabled(true);

	}

	// -------------------------------------METODO QUITAR
	// ITEM----------------------------------
	private void quitarItem(int posicion) {
		detalles.remove(pedidoDetalle);
		mtPedidoDetalle.setLista(detalles);
		producto = null;
		vPedido.getTfCodigoProducto().setText("");
		vPedido.getTfProducto().setText("");
		vPedido.getsCantidad().setValue(1);
		vPedido.getBtnQuitar().setEnabled(false);
		double total = 0;
		for (int i = 0; i < detalles.size(); i++) {
			total = total + (detalles.get(i).getProducto().getPrecioVenta() * detalles.get(i).getCantidad());
		}
		vPedido.getLblTotal().setText(UtilidadesNumeros.doubleString(total));
	}

	// -------------------------------------METODO ANULAR
	// PEDIDO----------------------------------
	private void anular() {
		int resp = JOptionPane.showConfirmDialog(null,
				"Deseas anular el pedido número: " + "\n" + pedido.getId() + " ?", "ATENCIÓN!!",
				JOptionPane.YES_NO_OPTION);

		if (resp == JOptionPane.YES_OPTION) {
			pedido.setObs(JOptionPane.showInputDialog(null, "Motivo de la anulación!"));
			pedido.setEstado(true);
			try {
				dao.insertar(pedido);
				dao.commit();
				estadoInicial(true);
				limpiar();
			} catch (Exception e) {
				dao.rollback();
				e.printStackTrace();
			}
		}
	}
	// ---------------------------------FUNCION RECUPERAR
	// CLIENTE-----------------------------------

	private boolean recuperarCliente() {

		lista = ClienteDao.recuperarPorCi(vPedido.getTfCiCliente().getText());
		if (lista.size() > 0) {
			for (int i = 0; i < lista.size(); i++) {
				if (vPedido.getTfCiCliente().getText().equals(lista.get(i).getDocumento())) {
					cliente = lista.get(i);
					vPedido.getTfCliente().setText(cliente.getNombre());
					vPedido.getTfCiCliente().setText(cliente.getDocumento());
					vPedido.getTfDireccion().setText(cliente.getDireccion());
					vPedido.getTfTelefono().setText(cliente.getTelefono());
					return true;

				}
			}
			return false;
		}
		return false;
	}

	// ---------------------------------FUNCION RECUPERAR
	// PRODUCTO-----------------------------------

	private boolean recuperarProducto() {
		proLista = productoDao.recuperarPorCodigo(vPedido.getTfCodigoProducto().getText());
		if (proLista.size() > 0) {
			for (int i = 0; i < proLista.size(); i++) {
				if (vPedido.getTfCodigoProducto().getText().equals(proLista.get(i).getCodigo())) {
					producto = proLista.get(i);
					vPedido.getTfProducto().setText(producto.getDescripcion());
					vPedido.getTfCodigoProducto().setText(producto.getCodigo());
					vPedido.getNtfPrecio().setValue(producto.getPrecioVenta());
					return true;

				}
			}
			return false;
		}
		return false;
	}

	// ---------------------------------FUNCION RECUPERAR
	// PEDIDO-----------------------------------

	private void recuperarPedido() {
		pedido = dao.recuperarPorId(Integer.parseInt(vPedido.getTfId().getText()));
		if (pedido != null) {
			vPedido.getBtnNuevo().setEnabled(false);
			vPedido.getBtnAnular().setEnabled(true);
			vPedido.getBtnCancelar().setEnabled(true);
			vPedido.getTfId().setEnabled(false);
			vPedido.getBtnBuscarPedido().setEnabled(false);

			cargarCampos();
		} else {
			JOptionPane.showMessageDialog(null, "Pedido no encontrado!");
		}
	}

	// -------------------------------------METODO CARGAR
	// CAMPOS----------------------------------

	private void cargarCampos() {
		vPedido.getTfId().setText(pedido.getId() + "");
		vPedido.getFtFecha().setText(FechaUtil.convertirDateAString(pedido.getFecha()));
		vPedido.getFtHora().setText(FechaUtil.convertirHoraAString(pedido.getFecha()));
		vPedido.getTfCiCliente().setText(pedido.getCliente().getDocumento());
		vPedido.getTfCliente().setText(pedido.getCliente().getNombre());
		vPedido.getTfDireccion().setText(pedido.getCliente().getDireccion());
		vPedido.getTfTelefono().setText(pedido.getCliente().getTelefono());
		vPedido.getTfObs().setText(pedido.getObs());
		detalles = pedido.getPedidoDetalle();
		mtPedidoDetalle.setLista(detalles);
		double total = 0;
		for (int i = 0; i < detalles.size(); i++) {
			total = total + (detalles.get(i).getProducto().getPrecioVenta() * detalles.get(i).getCantidad());
		}
		vPedido.getLblTotal().setText(UtilidadesNumeros.doubleString(total));

		if (pedido.getEstado() == true) {
			vPedido.getBtnAnular().setEnabled(false);
			vPedido.getLblAviso().setText("Pedido Anulado!");
			vPedido.getLblAviso().setVisible(true);
		}
	}

	// -------------------------------------METODO INICIALIZAR
	// PEDIDO----------------------------------

	public void inicializarPedido() {
		String tabla = "tb_pedido";
		dao.eliminarTodo(tabla);
		try {
			dao.commit();
		} catch (Exception e) {
			dao.rollback();
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	private void abrirBuscadorCliente() {
		BuscadorCliente bCliente = new BuscadorCliente();
		bCliente.setUpController();
		bCliente.getController().setInterfaz(this);
		bCliente.setVisible(true);

	}

	private void abrirBuscadorProducto() {
		BuscadorProducto buscador = new BuscadorProducto();
		buscador.setUpController();
		buscador.getController().setInterfaz(this);
		buscador.setVisible(true);

	}

	@Override
	public void setProducto(Producto producto) {
		this.producto = producto;
		vPedido.getTfProducto().setText(producto.getDescripcion());
		vPedido.getTfCodigoProducto().setText(producto.getCodigo());
		vPedido.getNtfPrecio().setValue(producto.getPrecioVenta());
		vPedido.getsCantidad().setValue(1);
		vPedido.getBtnAgregar().setEnabled(true);

	}

	@Override
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
		vPedido.getTfCliente().setText(cliente.getNombre());
		vPedido.getTfCiCliente().setText(cliente.getDocumento());
		vPedido.getTfDireccion().setText(cliente.getDireccion());
		vPedido.getTfTelefono().setText(cliente.getTelefono());

	}

}
