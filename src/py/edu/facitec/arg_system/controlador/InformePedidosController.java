package py.edu.facitec.arg_system.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRException;
import py.edu.facitec.arg_system.dao.ConfiguracionDao;
import py.edu.facitec.arg_system.dao.PedidoDao;
import py.edu.facitec.arg_system.entidad.Configuracion;
import py.edu.facitec.arg_system.entidad.Pedido;
import py.edu.facitec.arg_system.informe_listado.VentanaInformePedidos;
import py.edu.facitec.arg_system.tabla.ModeloTablaPedido;
import py.edu.facitec.arg_system.tabla.ModeloTablaPedidoDetalle;
import py.edu.facitec.arg_system.util.ConexionReportes;
import py.edu.facitec.arg_system.util.FechaUtil;

public class InformePedidosController implements MouseListener {

	private List<Pedido> pedidos;
	private ModeloTablaPedido mtlPedido;
	private ModeloTablaPedidoDetalle mtlPedidoDetalle;
	private PedidoDao dao;
	private VentanaInformePedidos ventana;
	private String desde, hasta;
	private Configuracion configuracion;
//	private ConfiguracionDAO daoConfig;

	public InformePedidosController(VentanaInformePedidos ventanaInformePedidos) {
		this.ventana = ventanaInformePedidos;
		dao = new PedidoDao();
		mtlPedido = new ModeloTablaPedido();
		this.ventana.getTable().setModel(mtlPedido);

		mtlPedidoDetalle = new ModeloTablaPedidoDetalle();
		this.ventana.getTableDetalle().setModel(mtlPedidoDetalle);

		cargarConfiguracion();
		setUpEvents();
	}

	private void setUpEvents() {

		ventana.getTable().addMouseListener(this);

		ventana.getTfCliente().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					ventana.getBtnFiltrar().requestFocus();
				}
				if (ventana.getTfCliente().getText().length() == 50) {
					e.consume();
				}
				if (Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}

		});

		ventana.getBtnFiltrar().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					ventana.getBtnImprimir().requestFocus();
					filtrar();
				}
			}
		});

		ventana.getBtnFiltrar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrar();
			}
		});

		ventana.getBtnImprimir().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imprimir();
			}
		});

		ventana.getBtnCancelar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelar();
			}
		});

	}

	private void cargarConfiguracion() {
		ConfiguracionDao daoConfig = new ConfiguracionDao();
		configuracion = daoConfig.recuperarPorId(1);
	}

	private void filtrar() {
		cargarFiltros();
		pedidos = dao.filtroInformePedido(desde, hasta, ventana.getTfCliente().getText());
		mtlPedido.setLista(pedidos);

		ventana.getLblTotalRegistro().setText(pedidos.size() + "");

		if (pedidos.size() <= 0) {
			this.ventana.getBtnImprimir().setEnabled(false);
			this.ventana.getBtnCancelar().setEnabled(false);
			JOptionPane.showMessageDialog(null, "No encontrado!!");
		} else {
			this.ventana.getBtnImprimir().setEnabled(true);
			this.ventana.getBtnCancelar().setEnabled(true);
		}
	}

	private void cargarFiltros() {
		desde = "11/11/1000";
		hasta = FechaUtil.convertirDateAString(new Date());

		if (FechaUtil.convertirStringADate(ventana.getTfDesdeFecha().getText()) != null)
			desde = ventana.getTfDesdeFecha().getText();
		if (FechaUtil.convertirStringADate(ventana.getTfHastaFecha().getText()) != null)
			hasta = ventana.getTfHastaFecha().getText();

	}

	private void imprimir() {
		// Pasando parametros
		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("emision", FechaUtil.convertirDateAString(new Date()));
		parametros.put("desde", desde);
		parametros.put("hasta", hasta);
		parametros.put("totalRegistro", (pedidos.size() + ""));
		parametros.put("empresa", configuracion.getEmpresa());
		parametros.put("telefono", configuracion.getTelefono());
		parametros.put("email", configuracion.getEmail());
		parametros.put("direccion", configuracion.getDireccion());

		// Creando reporte
		ConexionReportes<Pedido> conexionReportes = new ConexionReportes<>();
		try {
			if (ventana.getCbTipoInforme().getSelectedIndex() == 0) {
				conexionReportes.generarReporte(pedidos, parametros, "InformePedidos");
			} else {
				conexionReportes.generarReporte(pedidos, parametros, "InformePedidosDetallado");
			}
			conexionReportes.ventanaReporte.setLocationRelativeTo(ventana);
			conexionReportes.ventanaReporte.setVisible(true);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	private void cancelar() {
		ventana.getTfDesdeFecha().setValue(null);
		ventana.getTfHastaFecha().setValue(null);
		ventana.getTfCliente().setText("");

		pedidos.removeAll(pedidos);
		mtlPedido.setLista(pedidos);
		mtlPedido.fireTableDataChanged();

		ventana.getLblTotalRegistro().setText(pedidos.size() + "");

		if (pedidos.size() <= 0) {
			this.ventana.getBtnImprimir().setEnabled(false);
			this.ventana.getBtnCancelar().setEnabled(false);
		} else {
			this.ventana.getBtnImprimir().setEnabled(true);
			this.ventana.getBtnCancelar().setEnabled(true);
		}

	}

	private void visualizarDetalles(int posicion) {
		if (posicion < 0)
			return;
		mtlPedidoDetalle.setLista(pedidos.get(posicion).getPedidoDetalle());
		mtlPedidoDetalle.fireTableDataChanged();
		System.out.println(pedidos.get(posicion).getPedidoDetalle().size());

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == ventana.getTable() && e.getClickCount() == 2) {
			visualizarDetalles(ventana.getTable().getSelectedRow());
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
