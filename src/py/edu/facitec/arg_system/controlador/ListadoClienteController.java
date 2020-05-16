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
import py.edu.facitec.arg_system.dao.ClienteDao;
import py.edu.facitec.arg_system.dao.ConfiguracionDao;
import py.edu.facitec.arg_system.entidad.Cliente;
import py.edu.facitec.arg_system.entidad.Configuracion;
import py.edu.facitec.arg_system.informe_listado.VentanaListadoClientes;
import py.edu.facitec.arg_system.tabla.ModeloTablaListadoCliente;
import py.edu.facitec.arg_system.util.ConexionReportes;
import py.edu.facitec.arg_system.util.FechaUtil;

public class ListadoClienteController {
	private List<Cliente> clientes;
	private ModeloTablaListadoCliente mtlCliente;
	private ClienteDao dao;
	private VentanaListadoClientes ventana;
	private String dNombre, hNombre;
	private Configuracion configuracion;
//	private ConfiguracionDAO daoConfig;
	
	public ListadoClienteController(VentanaListadoClientes ventanaListadoClientes) {
		this.ventana = ventanaListadoClientes;
		dao = new ClienteDao();
		mtlCliente = new ModeloTablaListadoCliente();
		this.ventana.getTable().setModel(mtlCliente);
		cargarConfiguracion();
		setUpEvents();
	}
	
	private void setUpEvents() {
		ventana.getTfDesdeNombre().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					ventana.getTfHastaNombre().requestFocus();
				}
				if (ventana.getTfDesdeNombre().getText().length() == 50) {
						e.consume();
				}
				if (Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
			
		});
		ventana.getTfHastaNombre().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					ventana.getBtnFiltrar().requestFocus();
				}
				if (ventana.getTfHastaNombre().getText().length() == 50) {
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
		clientes = dao.filtroListadoClientes(dNombre, hNombre, this.ventana.getCbOrder().getSelectedItem().toString());
		mtlCliente.setLista(clientes);
		
		ventana.getLblTotalRegistro().setText(clientes.size()+"");
		
		if (clientes.size()<=0) {
			this.ventana.getBtnImprimir().setEnabled(false);
			this.ventana.getBtnCancelar().setEnabled(false);
			JOptionPane.showMessageDialog(null, "No encontrado!!");
		}else {
			this.ventana.getBtnImprimir().setEnabled(true);
			this.ventana.getBtnCancelar().setEnabled(true);
		}
	}
	
	private void cargarFiltros() {
		dNombre = "A";
		hNombre = "Z";
		
		if (!this.ventana.getTfDesdeNombre().getText().isEmpty()) dNombre = this.ventana.getTfDesdeNombre().getText();
		if (!this.ventana.getTfHastaNombre().getText().isEmpty()) hNombre = this.ventana.getTfHastaNombre().getText();

	}
	
	private void imprimir() {
		//Pasando parametros
		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("emision", FechaUtil.convertirDateAString(new Date()));
		parametros.put("filtradoDe", dNombre);
		parametros.put("hasta", hNombre);
		parametros.put("totalRegistros", (clientes.size()+""));
		parametros.put("desdeNombre", dNombre);
		parametros.put("hastaNombre", hNombre);
		parametros.put("empresa", configuracion.getEmpresa());
		parametros.put("telefono", configuracion.getTelefono());
		parametros.put("email", configuracion.getEmail());
		parametros.put("direccion", configuracion.getDireccion());
		
		
		//Creando reporte
		ConexionReportes<Cliente> conexionReportes = new ConexionReportes<>();
		try {
			conexionReportes.generarReporte(clientes, parametros, "ListadoClientes");
			conexionReportes.ventanaReporte.setLocationRelativeTo(ventana);
			conexionReportes.ventanaReporte.setVisible(true);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
	
	private void cancelar() {
		ventana.getTfDesdeNombre().setText("");
		ventana.getTfHastaNombre().setText("");
		
		clientes.removeAll(clientes);
		mtlCliente.setLista(clientes);
		mtlCliente.fireTableDataChanged();
		
		ventana.getLblTotalRegistro().setText(clientes.size()+"");
		
		if (clientes.size()<=0) {
			this.ventana.getBtnImprimir().setEnabled(false);
			this.ventana.getBtnCancelar().setEnabled(false);
		}else {
			this.ventana.getBtnImprimir().setEnabled(true);
			this.ventana.getBtnCancelar().setEnabled(true);
		}

	}
		
}
