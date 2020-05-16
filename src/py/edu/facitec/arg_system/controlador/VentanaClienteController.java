package py.edu.facitec.arg_system.controlador;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JOptionPane;

import py.edu.facitec.arg_system.abm.VentanaCliente;
import py.edu.facitec.arg_system.dao.ClienteDao;
import py.edu.facitec.arg_system.entidad.Cliente;
import py.edu.facitec.arg_system.interfaz.AccionesABM;
import py.edu.facitec.arg_system.tabla.ModeloTablaCliente;

public class VentanaClienteController implements AccionesABM {

	private VentanaCliente ventanaCliente;
	private Cliente cliente;
	private ClienteDao dao;
	private ModeloTablaCliente mtCliente;
	private List<Cliente> lista;
	private String accion;

	public VentanaClienteController(VentanaCliente ventanaCliente) {
		this.ventanaCliente = ventanaCliente;
		this.ventanaCliente.setAccionesABM(this);

		this.mtCliente = new ModeloTablaCliente();
		this.ventanaCliente.getTable().setModel(mtCliente);

		dao = new ClienteDao();// Se instancia
		estadoInicial(true);
		recuperarTodo();

		setUpEvents();
	}

	private void setUpEvents() {
		ventanaCliente.getTable().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == ventanaCliente.getTable()) {
					cargarFormulario(ventanaCliente.getTable().getSelectedRow());
				}
				int posicion = ventanaCliente.getTable().getSelectedRow();
				if (posicion >= 0) {
					ventanaCliente.getBtnModificar().setEnabled(true);
					ventanaCliente.getBtnEliminar().setEnabled(true);
					return;
				}
			}
		});

		ventanaCliente.getTfBuscador().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER)
					recuperarPorFiltro();

			}
		});

	}

	@Override
	public void nuevo() {
		accion = "NUEVO";
		estadoInicial(false);
		ventanaCliente.getBtnModificar().setEnabled(false);
		ventanaCliente.getBtnEliminar().setEnabled(false);
		ventanaCliente.getTfBuscador().setEnabled(false);
		ventanaCliente.getTable().setEnabled(false);
		ventanaCliente.getTable().clearSelection();
		vaciarCampos();
	}

	@Override
	public void modificar() {
		accion = "MODIFICAR";

		if (cliente == null) {
			JOptionPane.showMessageDialog(ventanaCliente, "Seleccione un registro");
			return;
		}
		estadoInicial(false);
		ventanaCliente.getBtnNuevo().setEnabled(false);
		ventanaCliente.getBtnEliminar().setEnabled(false);
		ventanaCliente.getBtnGuardar().setText("Actualizar");

		ventanaCliente.getTfNombre().requestFocus();
		ventanaCliente.getTfNombre().selectAll();
		ventanaCliente.getTfBuscador().setEnabled(false);
		ventanaCliente.getTfBuscador().setText("");

		ventanaCliente.getTable().setEnabled(false);
		ventanaCliente.getTable().clearSelection();

		recuperarTodo();

	}

	@Override
	public void eliminar() {
		// int posicion = ventanaCliente.getTable().getSelectedRow();
		if (cliente == null) {
			JOptionPane.showMessageDialog(ventanaCliente, "Seleccione un registro");
			return;
		}

		int resp = JOptionPane.showConfirmDialog(ventanaCliente,
				"Estás seguro que deseas eliminar el Cliente: " + "\n" + cliente.getId() + "- " + cliente.getNombre(),
				"ATENCION!!", JOptionPane.YES_NO_OPTION);
		if (resp == JOptionPane.YES_OPTION) {
			try {
				dao.eliminar(cliente);
				dao.commit();
				estadoInicial(true);
				vaciarCampos();
				recuperarTodo();
			} catch (Exception e) {
				dao.rollback();
			}
		}
	}

	public void guardar() {
		ventanaCliente.getBtnGuardar().setText("Guardar");
		if (verificarDocumento())
			return;
		// si no se validaron los campos correctamente
		if (!validarCampos())
			return;

		if (accion.equals("NUEVO")) {// si es nuevo se crea un nuevo objeto
			cliente = new Cliente();
		}

		// si no, se modifica el objeto seleccionado
		cliente.setNombre(ventanaCliente.getTfNombre().getText());
		cliente.setDocumento(ventanaCliente.getTfDocumento().getText());
		cliente.setTelefono(ventanaCliente.getTfTelefono().getText());
		cliente.setDireccion(ventanaCliente.getTfDireccion().getText());

		ventanaCliente.getTfBuscador().setEnabled(true);
		ventanaCliente.getTable().setEnabled(true);

		try {
			if (accion.equals("NUEVO")) {
				dao.insertar(cliente);
			} else {
				dao.modificar(cliente);
			}
			dao.commit();

			estadoInicial(true);
			vaciarCampos();
			recuperarTodo();

		} catch (Exception e) {
			dao.rollback();
			JOptionPane.showMessageDialog(null, "Se produjo un error al guardar", "Error!", JOptionPane.ERROR_MESSAGE);
		}

	}

	@Override
	public void cancelar() {
		estadoInicial(true);
		ventanaCliente.getBtnGuardar().setText("Guardar");
		ventanaCliente.getTfBuscador().setEnabled(true);
		ventanaCliente.getLblAvisoNombre().setVisible(false);

		ventanaCliente.getLblAvisoDocumento().setVisible(false);

		ventanaCliente.getLblAvisoTelefono().setVisible(false);

		ventanaCliente.getLblAvisoDireccion().setVisible(false);

		ventanaCliente.getTable().setEnabled(true);

		estadoInicial(true);
		vaciarCampos();
		recuperarTodo();
	}

//--------------------------------------------------------------------------------------------

	private void cargarFormulario(int posicion) {
		if (posicion < 0)
			return;

		cliente = lista.get(posicion);

		ventanaCliente.getTfNombre().setText(cliente.getNombre());
		ventanaCliente.getTfDocumento().setText(cliente.getDocumento());
		ventanaCliente.getTfTelefono().setText(cliente.getTelefono());
		ventanaCliente.getTfDireccion().setText(cliente.getDireccion());

	}

	private void recuperarTodo() {
		lista = dao.recuperarTodo();
		mtCliente.setLista(lista);
	}

	public void recuperarPorFiltro() {
		lista = dao.recuperarPorFiltro(ventanaCliente.getTfBuscador().getText());
		mtCliente.setLista(lista);
		mtCliente.fireTableDataChanged();
	}

	private void estadoInicial(boolean esInicial) {
		ventanaCliente.getTfNombre().setEnabled(!esInicial);
		ventanaCliente.getTfDocumento().setEnabled(!esInicial);
		ventanaCliente.getTfTelefono().setEnabled(!esInicial);
		ventanaCliente.getTfDireccion().setEnabled(!esInicial);
		ventanaCliente.getTfBuscador().setEnabled(true);

		ventanaCliente.getBtnModificar().setEnabled(!esInicial);
		ventanaCliente.getBtnEliminar().setEnabled(!esInicial);
		ventanaCliente.getBtnCancelar().setEnabled(!esInicial);
		ventanaCliente.getBtnGuardar().setEnabled(!esInicial);
		ventanaCliente.getBtnNuevo().setEnabled(esInicial);

		ventanaCliente.getTable().setEnabled(true);
		ventanaCliente.getTable().clearSelection();

	}

	private void vaciarCampos() {
		ventanaCliente.getTfNombre().setText("");
		ventanaCliente.getTfDocumento().setText("");
		ventanaCliente.getTfTelefono().setText("");
		ventanaCliente.getTfDireccion().setText("");
		ventanaCliente.getTfBuscador().setText("");
		ventanaCliente.getTfBuscador().setText("");
	}

	// ---------------------------------VALIDACIONES

	private boolean validarCampos() {
		if (ventanaCliente.getTfNombre().getText().isEmpty()) {
			ventanaCliente.getLblAvisoNombre().setText("El campo Nombre: es Obligatorio!!");
			ventanaCliente.getLblAvisoNombre().setVisible(true);
			ventanaCliente.getTfNombre().requestFocus();

			return false;
		}
		if (ventanaCliente.getTfDocumento().getText().isEmpty()) {
			ventanaCliente.getLblAvisoDocumento().setText("El campo Documento: es Obligatorio!!");
			ventanaCliente.getLblAvisoDocumento().setVisible(true);
			return false;
		}
		if (ventanaCliente.getTfTelefono().getText().isEmpty()) {
			ventanaCliente.getLblAvisoTelefono().setText("El campo Teléfono es Obligatorio!!");
			ventanaCliente.getLblAvisoTelefono().setVisible(true);
			ventanaCliente.getTfTelefono().requestFocus();

			return false;
		}
		return true;
	}

	public boolean verificarDocumento() {
		if (ventanaCliente.getTfDocumento().getText().isEmpty()) {
			ventanaCliente.getLblAvisoDocumento().setVisible(false);
			return false;
		}
		if (lista != null) {
			for (int i = 0; i < lista.size(); i++) {
				boolean mismo = (!accion.equals("NUEVO") && cliente.getId() == lista.get(i).getId());
				if (ventanaCliente.getTfDocumento().getText().equals(lista.get(i).getDocumento()) && !mismo) {
					ventanaCliente.getLblAvisoDocumento().setVisible(true);
					ventanaCliente.getLblAvisoDocumento().setText("Documento Duplicado!!");
					ventanaCliente.getTfDocumento().requestFocus();
					ventanaCliente.getTfDocumento().selectAll();
					return true;

				}
			}
			return false;
		}
		return false;
	}

	// ---------------------------------FUNCION INICIALIZAR
	// CLIENTE-----------------------------------

	public void inicializarCliente() {
		String tabla = "tb_cliente";
		dao.eliminarTodo(tabla);
		try {
			dao.commit();
		} catch (Exception e) {
			dao.rollback();
		}
	}

}
