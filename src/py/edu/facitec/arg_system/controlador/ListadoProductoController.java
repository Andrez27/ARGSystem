package py.edu.facitec.arg_system.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRException;
import py.edu.facitec.arg_system.dao.ConfiguracionDao;
import py.edu.facitec.arg_system.dao.ProductoDao;
import py.edu.facitec.arg_system.entidad.Configuracion;
import py.edu.facitec.arg_system.entidad.Producto;
import py.edu.facitec.arg_system.informe_listado.VentanaListadoProductos;
import py.edu.facitec.arg_system.tabla.ModeloTablaListadoProducto;
import py.edu.facitec.arg_system.util.ConexionReportes;
import py.edu.facitec.arg_system.util.FechaUtil;

public class ListadoProductoController {
	private List<Producto> productos;
	private ModeloTablaListadoProducto mtlProducto;
	private ProductoDao daoProducto;
	private VentanaListadoProductos ventana;
	private String dCodigo, hCodigo, dDescripcion, hDescripcion;
	private Configuracion configuracion;
	@SuppressWarnings("unused")
	private ConfiguracionDao daoConfi;

	public ListadoProductoController(VentanaListadoProductos ventanaListadoProductos) {
		this.ventana = ventanaListadoProductos;
		daoProducto = new ProductoDao();
		mtlProducto = new ModeloTablaListadoProducto();
		this.ventana.getTable().setModel(mtlProducto);

		cargarConfiguracion();
		setUpEvents();
	}

	private void setUpEvents() {
		ventana.getTfDesdeCodigo().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					ventana.getTfHastaCodigo().requestFocus();
				}
				if (ventana.getTfDesdeCodigo().getText().length() == 30) {
					e.consume();
				}
				if (!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}

		});
		ventana.getTfHastaCodigo().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					ventana.getBtnFiltrar().requestFocus();
				}
				if (ventana.getTfHastaCodigo().getText().length() == 30) {
					e.consume();
				}
				if (!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});

		ventana.getTfDesdeDescripcion().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					ventana.getTfHastaDescripcion().requestFocus();
				}
				if (ventana.getTfDesdeDescripcion().getText().length() == 100) {
					e.consume();
				}
			}

		});
		ventana.getTfHastaDescripcion().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					ventana.getBtnFiltrar().requestFocus();
				}
				if (ventana.getTfHastaDescripcion().getText().length() == 100) {
					e.consume();
				}
			}
		});

		// Filtra al presionar ENTER
		ventana.getBtnFiltrar().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ventana.getBtnImprimir().requestFocus();
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
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
		productos = daoProducto.filtroListadoProductos(dCodigo, hCodigo, dDescripcion, hDescripcion,
				this.ventana.getCbOrder().getSelectedItem().toString());
		mtlProducto.setLista(productos);

		ventana.getLblTotalRegistro().setText(productos.size() + "");

		if (productos.size() <= 0) {
			this.ventana.getBtnImprimir().setEnabled(false);
			this.ventana.getBtnCancelar().setEnabled(false);
			JOptionPane.showMessageDialog(null, "No encontrado!!");
		} else {
			this.ventana.getBtnImprimir().setEnabled(true);
			this.ventana.getBtnCancelar().setEnabled(true);
		}
	}

	private void cargarFiltros() {
		dCodigo = "0";
		hCodigo = "999999999";

		if (!this.ventana.getTfDesdeCodigo().getText().isEmpty())
			dCodigo = this.ventana.getTfDesdeCodigo().getText();
		if (!this.ventana.getTfHastaCodigo().getText().isEmpty())
			hCodigo = this.ventana.getTfHastaCodigo().getText();

		dDescripcion = "1";
		hDescripcion = "ZZZ";

		if (!this.ventana.getTfDesdeDescripcion().getText().isEmpty())
			dDescripcion = this.ventana.getTfDesdeDescripcion().getText();
		if (!this.ventana.getTfHastaDescripcion().getText().isEmpty())
			hDescripcion = this.ventana.getTfHastaDescripcion().getText();
	}

	private void imprimir() {
		// Pasando parametros
		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("emision", FechaUtil.convertirDateAString(new Date()));
		parametros.put("totalRegistros", (productos.size() + ""));
		parametros.put("order", this.ventana.getCbOrder().getSelectedItem().toString());
		parametros.put("empresa", configuracion.getEmpresa());
		parametros.put("telefono", configuracion.getTelefono());
		parametros.put("email", configuracion.getEmail());
		parametros.put("direccion", configuracion.getDireccion());

		// Creando reporte
		ConexionReportes<Producto> conexionReportes = new ConexionReportes<>();
		try {
			conexionReportes.generarReporte(productos, parametros, "ListadoProductos");
			conexionReportes.ventanaReporte.setLocationRelativeTo(ventana);
			conexionReportes.ventanaReporte.setVisible(true);
		} catch (JRException e) {
			e.printStackTrace();
		}

	}

	private void cancelar() {
		ventana.getTfDesdeCodigo().setText("");
		ventana.getTfHastaCodigo().setText("");

		productos.removeAll(productos);
		mtlProducto.setLista(productos);
		mtlProducto.fireTableDataChanged();

		ventana.getLblTotalRegistro().setText(productos.size() + "");

		if (productos.size() <= 0) {
			this.ventana.getBtnImprimir().setEnabled(false);
			this.ventana.getBtnCancelar().setEnabled(false);
		} else {
			this.ventana.getBtnImprimir().setEnabled(true);
			this.ventana.getBtnCancelar().setEnabled(true);
		}

	}

}
