package py.edu.facitec.arg_system.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JOptionPane;

import py.edu.facitec.arg_system.abm.VentanaProducto;
import py.edu.facitec.arg_system.buscador.BuscadorGrupo;
import py.edu.facitec.arg_system.dao.ProductoDao;
import py.edu.facitec.arg_system.entidad.Grupo;
import py.edu.facitec.arg_system.entidad.Producto;
import py.edu.facitec.arg_system.interfaz.AccionesABM;
import py.edu.facitec.arg_system.interfaz.BuscadorGrupoInterface;
import py.edu.facitec.arg_system.tabla.ModeloTablaProducto;

public class VentanaProductoController implements AccionesABM, BuscadorGrupoInterface {
	private VentanaProducto ventanaProducto;
	private Producto producto;
	private ProductoDao dao;
	private ModeloTablaProducto mtProducto;
	private List<Producto> lista;
	private String accion;
	private Grupo grupo;

	public VentanaProductoController(VentanaProducto ventanaProducto) {
		this.ventanaProducto = ventanaProducto;
		this.ventanaProducto.setAccionesABM(this);

		estadoInicial(true);
		dao = new ProductoDao();
		mtProducto = new ModeloTablaProducto();
		this.ventanaProducto.getTable().setModel(mtProducto);

		recuperarTodo();
		
		setUpEvents();
		
	}

	private void setUpEvents() {
		ventanaProducto.getTable().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == ventanaProducto.getTable()) {
					cargarFormulario(ventanaProducto.getTable().getSelectedRow());
				}

				int posicion = ventanaProducto.getTable().getSelectedRow();
				if (posicion >= 0) {
					ventanaProducto.getBtnModificar().setEnabled(true);
					ventanaProducto.getBtnEliminar().setEnabled(true);
					return;
				}

			}
		});

		ventanaProducto.getBtnBuscador().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == ventanaProducto.getBtnBuscador()) {
					abrirBuscador();
				}
			}
		});

		ventanaProducto.getTfBuscador().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyChar() == KeyEvent.VK_ENTER)
					recuperarPorFiltro();
			}

		});
	}

	private void cargarFormulario(int posicion) {
		if (posicion < 0)
			return;

		producto = lista.get(posicion);

		ventanaProducto.getTfCodigo().setText(producto.getCodigo());
		ventanaProducto.getTfDescripcion().setText(producto.getDescripcion());
		grupo = producto.getGrupo();
		ventanaProducto.getTfGrupo().setText(grupo.getDescripcion());
		ventanaProducto.getcEstado().setSelected(producto.getEstado());
		ventanaProducto.getNtfPrecioCompra().setValue(producto.getPrecioCosto());
		ventanaProducto.getNtfPrecioVenta().setValue(producto.getPrecioVenta());
		ventanaProducto.getNtfPrecioMinimo().setValue(producto.getPrecioMinimo());

	}

	public void recuperarPorFiltro() {
		lista = dao.recuperarPorFiltro(ventanaProducto.getTfBuscador().getText());
		mtProducto.setLista(lista);
		mtProducto.fireTableDataChanged();
	}

	private void estadoInicial(boolean esInicial) {

		ventanaProducto.getTfCodigo().setEnabled(!esInicial);
		ventanaProducto.getTfDescripcion().setEnabled(!esInicial);
		ventanaProducto.getTfGrupo().setEnabled(!esInicial);
		ventanaProducto.getTfBuscador().setEnabled(esInicial);
		ventanaProducto.getBtnBuscador().setEnabled(!esInicial);
		ventanaProducto.getcEstado().setEnabled(!esInicial);
		ventanaProducto.getNtfPrecioCompra().setEnabled(!esInicial);
		ventanaProducto.getNtfPrecioVenta().setEnabled(!esInicial);
		ventanaProducto.getNtfPrecioMinimo().setEnabled(!esInicial);

		ventanaProducto.getBtnModificar().setEnabled(!esInicial);
		ventanaProducto.getBtnEliminar().setEnabled(!esInicial);
		ventanaProducto.getBtnCancelar().setEnabled(!esInicial);
		ventanaProducto.getBtnGuardar().setEnabled(!esInicial);
		ventanaProducto.getBtnNuevo().setEnabled(esInicial);

		ventanaProducto.getLblAvisoCodigo().setVisible(true);

	}

	private void vaciarCampos() {

		ventanaProducto.getTfCodigo().setText("");
		ventanaProducto.getTfDescripcion().setText("");
		ventanaProducto.getTfGrupo().setText("");
		ventanaProducto.getcEstado().setSelected(false);
		ventanaProducto.getNtfPrecioCompra().setValue(0d);
		ventanaProducto.getNtfPrecioVenta().setValue(0d);
		ventanaProducto.getNtfPrecioMinimo().setValue(0d);
		ventanaProducto.getBtnBuscador().setText("");

		ventanaProducto.getLblAvisoCodigo().setVisible(false);
		ventanaProducto.getLblAvisoDescripcion().setVisible(false);
		ventanaProducto.getLblAvisoGrupo().setVisible(false);

	}

	private void recuperarTodo() {
		lista = dao.recuperarTodo();
		mtProducto.setLista(lista);
	}

	@Override
	public void nuevo() {
		estadoInicial(false);
		ventanaProducto.getBtnModificar().setEnabled(false);
		ventanaProducto.getBtnEliminar().setEnabled(false);
		ventanaProducto.getTfBuscador().setEnabled(false);
		ventanaProducto.getTable().setEnabled(false);
		ventanaProducto.getTable().clearSelection();
		accion = "NUEVO";
		vaciarCampos();
	}

	@Override
	public void modificar() {
		accion = "MODIFICAR";

		if (producto == null) {
			JOptionPane.showMessageDialog(ventanaProducto, "Seleccione un registro");
			return;
		}
		estadoInicial(false);
		ventanaProducto.getBtnNuevo().setEnabled(false);
		ventanaProducto.getBtnEliminar().setEnabled(false);
		ventanaProducto.getBtnGuardar().setText("Actualizar");

		ventanaProducto.getTfDescripcion().requestFocus();
		ventanaProducto.getTfDescripcion().selectAll();
		ventanaProducto.getTfBuscador().setEnabled(false);
		ventanaProducto.getTfBuscador().setText("");
		
		ventanaProducto.getTable().setEnabled(false);
		ventanaProducto.getTable().clearSelection();

		ventanaProducto.getLblAvisoCodigo().setVisible(false);
		
		recuperarTodo();
	}

	@Override
	public void eliminar() {
		// int posicion = ventanaProducto.getTable().getSelectedRow();
		if (producto == null) {
			JOptionPane.showMessageDialog(ventanaProducto, "Seleccione un registro");
//			return;
		} else {

			int resp = JOptionPane.showConfirmDialog(ventanaProducto, "Estás seguro que deseas eliminar el producto:\n"
					+ producto.getId() + " - " + producto.getDescripcion(), "ATENCION!!", JOptionPane.YES_NO_OPTION);
			if (resp == JOptionPane.YES_OPTION) {
				try {
//					dao = new ProductoDao();
					dao.eliminar(producto);
					dao.commit();
					vaciarCampos();
					recuperarTodo();
				} catch (Exception e) {
					dao.rollback();
					JOptionPane.showMessageDialog(null,
							"No permitido eliminar, \nEl producto " + producto.getCodigo() + " - "
									+ producto.getDescripcion() + " tiene movimentación",
							"Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	public boolean validarCampos() {
		if (ventanaProducto.getTfCodigo().getText().isEmpty()) {
//			JOptionPane.showMessageDialog(null, "El campo Código: es Obligatorio!!");
			ventanaProducto.getLblAvisoCodigo().setText("El campo Código: es Obligatorio!!");
			ventanaProducto.getLblAvisoCodigo().setVisible(true);
			return false;
		}
		if (ventanaProducto.getTfDescripcion().getText().isEmpty()) {
//			JOptionPane.showMessageDialog(null, "El campo Descripción: es Obligatorio!!");
			ventanaProducto.getLblAvisoDescripcion().setText("El campo Descripción: es Obligatorio!!");
			ventanaProducto.getLblAvisoDescripcion().setVisible(true);
			return false;
		}

		if (grupo == null) {
			ventanaProducto.getLblAvisoGrupo().setText("Seleccione un grupo");
			ventanaProducto.getLblAvisoGrupo().setVisible(true);
			return false;
		}

		if (ventanaProducto.getNtfPrecioCompra().getValue() < 0) {
			JOptionPane.showMessageDialog(ventanaProducto, "El campo es obligatorio!!");
			return false;
		}

		if (ventanaProducto.getNtfPrecioVenta().getValue() < 0) {
			JOptionPane.showMessageDialog(ventanaProducto, "El campo es obligatorio!!");
//			 ventanaProducto.getLblAvisoPrecioVenta().setText("El campo es obligatorio!!");
//			 ventanaProducto.getLblAvisoPrecioVenta().setVisible(true);
			return false;
		}
		if (ventanaProducto.getNtfPrecioMinimo().getValue() < 0) {
			JOptionPane.showMessageDialog(ventanaProducto, "El campo es obligatorio!!");
			return false;
		}
		return true;
	}

	public boolean verificarPrecio() {
		if (ventanaProducto.getNtfPrecioCompra().getValue() <= 0) {
			ventanaProducto.getLblAvisoPrecioCompra().setText("Debe ser mayor que 0");
			ventanaProducto.getLblAvisoPrecioCompra().setVisible(true);
			ventanaProducto.getNtfPrecioCompra().selectAll();
			ventanaProducto.getNtfPrecioCompra().requestFocus();
			return false;
		}
		if (ventanaProducto.getNtfPrecioMinimo().getValue() <= ventanaProducto.getNtfPrecioCompra().getValue()) {
			ventanaProducto.getLblAvisoPrecioMinimo().setText("Mayor que precio de Compra!");
			ventanaProducto.getLblAvisoPrecioMinimo().setVisible(true);
			ventanaProducto.getNtfPrecioMinimo().selectAll();
			ventanaProducto.getNtfPrecioMinimo().requestFocus();
			return false;
		}
		if (ventanaProducto.getNtfPrecioVenta().getValue() <= ventanaProducto.getNtfPrecioMinimo().getValue()) {
			ventanaProducto.getLblAvisoPrecioVenta().setText("Mayor que precio Mínimo");
			ventanaProducto.getLblAvisoPrecioVenta().setVisible(true);
			ventanaProducto.getNtfPrecioVenta().selectAll();
			ventanaProducto.getNtfPrecioVenta().requestFocus();
			return false;
		}
		return true;

	}

	@Override
	public void guardar() {
		ventanaProducto.getBtnGuardar().setText("Guardar");

		ventanaProducto.getTfBuscador().setEnabled(true);
		ventanaProducto.getTable().setEnabled(true);
		ventanaProducto.getLblAvisoDescripcion().setVisible(false);

		if (verificarCodigo())
			return;
		if (!validarCampos())
			return;
		if (!verificarPrecio())
			return;

		if (accion.equals("NUEVO")) {
			producto = new Producto();
		}

		producto.setCodigo(ventanaProducto.getTfCodigo().getText());
		producto.setDescripcion(ventanaProducto.getTfDescripcion().getText());
		producto.setGrupo(grupo);
		producto.setEstado(ventanaProducto.getcEstado().isSelected());
		producto.setPrecioCosto(ventanaProducto.getNtfPrecioCompra().getValue());
		producto.setPrecioVenta(ventanaProducto.getNtfPrecioVenta().getValue());
		producto.setPrecioMinimo(ventanaProducto.getNtfPrecioMinimo().getValue());
		
		try {
			if (accion.equals("NUEVO")) {
				dao.insertar(producto);
			} else {
				dao.modificar(producto);
			}
			
			dao.commit();
			estadoInicial(true);
			validarCampos();
			vaciarCampos();
			recuperarTodo();

		} catch (Exception e) {
			e.printStackTrace();
			dao.rollback();
		}
	}

	@Override
	public void cancelar() {
		estadoInicial(true);
		ventanaProducto.getBtnGuardar().setText("Guardar");
		ventanaProducto.getLblAvisoCodigo().setVisible(true);
		ventanaProducto.getTable().setEnabled(true);
		ventanaProducto.getTable().clearSelection();

		estadoInicial(true);
		vaciarCampos();
		recuperarTodo();
	}

	public boolean verificarCodigo() {
		if (ventanaProducto.getTfCodigo().getText().isEmpty()) {
			ventanaProducto.getLblAvisoCodigo().setVisible(false);
			return false;
		}
		if (lista != null) {
			for (int i = 0; i < lista.size(); i++) {
				boolean mismo = (!accion.equals("NUEVO") && producto.getId() == lista.get(i).getId());
				if (ventanaProducto.getTfCodigo().getText().equals(lista.get(i).getCodigo()) && !mismo) {
					ventanaProducto.getLblAvisoCodigo().setVisible(true);
					ventanaProducto.getLblAvisoCodigo().setText("Documento Duplicado!!");
					ventanaProducto.getTfCodigo().requestFocus();
					ventanaProducto.getTfCodigo().selectAll();
					return true;
				}
			}
			return false;
		}
		return false;
	}

	public void inicializarProducto() {
		String tabla = "tb_producto";
		dao = new ProductoDao();
		dao.eliminarTodo(tabla);
		try {
			dao.commit();
		} catch (Exception e) {
			dao.rollback();
		}
	}

	private void abrirBuscador() {
		BuscadorGrupo buscador = new BuscadorGrupo();
		buscador.setUpController();
		buscador.getController().setInterfaz(this);
		buscador.setVisible(true);
	}

	@Override
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
		ventanaProducto.getTfGrupo().setText(grupo.getDescripcion());
	}

}
