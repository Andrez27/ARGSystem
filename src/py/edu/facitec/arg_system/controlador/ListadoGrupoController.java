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
import py.edu.facitec.arg_system.dao.GrupoDao;
import py.edu.facitec.arg_system.entidad.Configuracion;
import py.edu.facitec.arg_system.entidad.Grupo;
import py.edu.facitec.arg_system.informe_listado.VentanaListadoGrupos;
import py.edu.facitec.arg_system.tabla.ModeloTablaListadoGrupo;
import py.edu.facitec.arg_system.util.ConexionReportes;
import py.edu.facitec.arg_system.util.FechaUtil;

public class ListadoGrupoController {
	private List<Grupo> grupos;
	private ModeloTablaListadoGrupo mtlGrupo;
	private GrupoDao dao;
	private VentanaListadoGrupos ventana;
	private String dGrupo , hGrupo;
	private Configuracion configuracion;
//	private ConfiguracionDAO daoConfig;
	
	public ListadoGrupoController(VentanaListadoGrupos ventanaListadoGrupos) {
		this.ventana = ventanaListadoGrupos;
		dao = new GrupoDao();
		mtlGrupo = new ModeloTablaListadoGrupo();
		this.ventana.getTable().setModel(mtlGrupo);
		cargarConfiguracion();
		setUpEvents();
	}
	
	private void setUpEvents() {
		ventana.getTfDesdeDescripcion().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					ventana.getTfHastaDescripcion().requestFocus();
				}
				if (ventana.getTfDesdeDescripcion().getText().length() == 30) {
						e.consume();
				}
				if (Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
			
		});
		ventana.getTfHastaDescripcion().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					ventana.getBtnFiltrar().requestFocus();
				}
				if (ventana.getTfHastaDescripcion().getText().length() == 30) {
						e.consume();
				}
				if (Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}	
		});
		//Filtra al presionar ENTER
		ventana.getBtnFiltrar().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					ventana.getBtnImprimir().requestFocus();
					filtrar();
				}
			}
		});
		//Filtra al hacer CLICK
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
		//Cancela el Listado
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
		grupos = dao.filtroListadoGrupos(dGrupo, hGrupo, this.ventana.getCbOrder().getSelectedItem().toString());
		mtlGrupo.setLista(grupos);
		
		ventana.getLblTotalRegistro().setText(grupos.size()+"");
		
		if (grupos.size()<=0) {
			this.ventana.getBtnImprimir().setEnabled(false);
			this.ventana.getBtnCancelar().setEnabled(false);
			JOptionPane.showMessageDialog(null, "No encontrado!!");
		}else {
			this.ventana.getBtnImprimir().setEnabled(true);
			this.ventana.getBtnCancelar().setEnabled(true);
		}
	}
	
	private void cargarFiltros() {
		dGrupo = "A";
		hGrupo = "ZZZ";
		
		if (!this.ventana.getTfDesdeDescripcion().getText().isEmpty()) dGrupo = this.ventana.getTfDesdeDescripcion().getText();
		if (!this.ventana.getTfHastaDescripcion().getText().isEmpty()) hGrupo = this.ventana.getTfHastaDescripcion().getText();

	}

	
	
	private void imprimir() {
		//Pasando parametros
				HashMap<String, Object> parametros = new HashMap<>();
				parametros.put("emision", FechaUtil.convertirDateAString(new Date()));
				parametros.put("totalRegistros", (grupos.size()+""));
				parametros.put("order", this.ventana.getCbOrder().getSelectedItem().toString());
				parametros.put("empresa", configuracion.getEmpresa());
				parametros.put("telefono", configuracion.getTelefono());
				parametros.put("email", configuracion.getEmail());
				parametros.put("direccion", configuracion.getDireccion());
				
				
				//Creando reporte
				ConexionReportes<Grupo> conexionReportes = new ConexionReportes<>();
				try {
					conexionReportes.generarReporte(grupos, parametros, "ListadoGrupos");
					conexionReportes.ventanaReporte.setLocationRelativeTo(ventana);
					conexionReportes.ventanaReporte.setVisible(true);
				} catch (JRException  e) {
					e.printStackTrace();
				}
	}
	
	private void cancelar() {
		ventana.getTfDesdeDescripcion().setText("");
		ventana.getTfHastaDescripcion().setText("");
		
		grupos.removeAll(grupos);
		mtlGrupo.setLista(grupos);
		mtlGrupo.fireTableDataChanged();
		
		ventana.getLblTotalRegistro().setText(grupos.size()+"");
		
		if (grupos.size()<=0) {
			this.ventana.getBtnImprimir().setEnabled(false);
			this.ventana.getBtnCancelar().setEnabled(false);
		}

	}
		
}
