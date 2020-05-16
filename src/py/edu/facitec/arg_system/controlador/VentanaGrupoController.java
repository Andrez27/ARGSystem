
package py.edu.facitec.arg_system.controlador;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.exception.ConstraintViolationException;

import py.edu.facitec.arg_system.abm.VentanaGrupo;
import py.edu.facitec.arg_system.dao.GrupoDao;
import py.edu.facitec.arg_system.entidad.Grupo;
import py.edu.facitec.arg_system.interfaz.AccionesABM;
import py.edu.facitec.arg_system.tabla.ModeloTablaGrupo;

public class VentanaGrupoController implements AccionesABM {

	private VentanaGrupo ventanaGrupo;
	private Grupo grupo;
	private GrupoDao dao;
	private ModeloTablaGrupo mtGrupo;
	private List<Grupo> lista;
	private String accion;

	public VentanaGrupoController(VentanaGrupo ventanaGrupo) {
		this.ventanaGrupo = ventanaGrupo;
		this.ventanaGrupo.setAccionesABM(this);

		dao = new GrupoDao();
		estadoInicial(true);
		this.mtGrupo = new ModeloTablaGrupo();
		this.ventanaGrupo.getTable().setModel(mtGrupo);

		recuperarTodo();

		setUpEvents();
	}

	private void setUpEvents() {

		ventanaGrupo.getTable().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == ventanaGrupo.getTable()) {
					cargarFormulario(ventanaGrupo.getTable().getSelectedRow());

				}
				int posicion = ventanaGrupo.getTable().getSelectedRow();
				if (posicion >= 0) {
					ventanaGrupo.getBtnModificar().setEnabled(true);
					ventanaGrupo.getBtnEliminar().setEnabled(true);
					return;
				}

			}

		});

		ventanaGrupo.getTfDescripcion().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					validarCampo();
				}
			}
		});

		ventanaGrupo.getTfBuscador().addKeyListener(new KeyAdapter() {
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
		grupo = lista.get(posicion);
		ventanaGrupo.getTfDescripcion().setText(grupo.getDescripcion());
	}

	private void recuperarTodo() {
		lista = dao.recuperarTodo();
		mtGrupo.setLista(lista);
	}

	public void recuperarPorFiltro() {
		lista = dao.recuperarPorFiltro(ventanaGrupo.getTfBuscador().getText());
		mtGrupo.setLista(lista);
		mtGrupo.fireTableDataChanged();
	}

	private void estadoInicial(boolean esInicial) {
		ventanaGrupo.getTfDescripcion().setEnabled(!esInicial);
		ventanaGrupo.getTfBuscador().setEnabled(true);

		ventanaGrupo.getBtnModificar().setEnabled(!esInicial);
		ventanaGrupo.getBtnEliminar().setEnabled(!esInicial);
		ventanaGrupo.getBtnCancelar().setEnabled(!esInicial);
		ventanaGrupo.getBtnGuardar().setEnabled(!esInicial);
		ventanaGrupo.getBtnNuevo().setEnabled(esInicial);

		ventanaGrupo.getTable().setEnabled(true);
		ventanaGrupo.getTable().clearSelection();
	}

	private void vaciarCampos() {
		ventanaGrupo.getTfDescripcion().setText("");
		ventanaGrupo.getTfBuscador().setText("");

		ventanaGrupo.getLblAvisoDescripcion().setVisible(false);

	}

	@Override
	public void nuevo() {
		accion = "NUEVO";
		estadoInicial(false);
		ventanaGrupo.getBtnModificar().setEnabled(false);
		ventanaGrupo.getBtnEliminar().setEnabled(false);
		ventanaGrupo.getTfBuscador().setEnabled(false);
		ventanaGrupo.getTable().setEnabled(false);
		ventanaGrupo.getTable().clearSelection();
		vaciarCampos();
	}

	private boolean validarCampo() {
		if (ventanaGrupo.getTfDescripcion().getText().isEmpty()) {
			ventanaGrupo.getLblAvisoDescripcion().setText("El campo Descripción es Obligatorio!!");
			ventanaGrupo.getLblAvisoDescripcion().setVisible(true);
			return false;
		}
		return true;
	}

	@Override
	public void modificar() {
		accion = "MODIFICAR";

		if (grupo == null) {
			JOptionPane.showMessageDialog(null, "Seleccione un registro");
			return;
		}

		estadoInicial(false);
		ventanaGrupo.getBtnNuevo().setEnabled(false);
		ventanaGrupo.getBtnEliminar().setEnabled(false);
		ventanaGrupo.getBtnGuardar().setText("Actualizar");

		ventanaGrupo.getTfDescripcion().requestFocus();
		ventanaGrupo.getTfDescripcion().selectAll();
		ventanaGrupo.getTfBuscador().setEnabled(false);
		ventanaGrupo.getTfBuscador().setText("");

		ventanaGrupo.getTable().setEnabled(false);
		ventanaGrupo.getTable().clearSelection();

		recuperarTodo();

	}

	@Override
	public void eliminar() {
		if (grupo == null) {
			JOptionPane.showMessageDialog(ventanaGrupo, "Seleccione un registro");
			return;
		}

		int resp = JOptionPane.showConfirmDialog(ventanaGrupo,
				"Estás seguro que deseas eliminar el Grupo: " + "\n" + grupo.getId() + "- " + grupo.getDescripcion(),
				"ATENCIÓN!!", JOptionPane.YES_NO_OPTION);

		if (resp == JOptionPane.YES_OPTION) {
			try {
				dao.eliminar(grupo);
				dao.commit();
				estadoInicial(true);
				vaciarCampos();
				recuperarTodo();
			} catch (Exception e) {
				if (e.getCause().getClass() == ConstraintViolationException.class) {
					JOptionPane.showMessageDialog(null,
							"El Grupo: ¡" + grupo.getDescripcion() + "! no se puede eliminar, está siendo utilizado");
				}
				dao.rollback();
			}
		}
	}

	@Override
	public void guardar() {
		ventanaGrupo.getBtnGuardar().setText("Guardar");
		if (verificarDescripcion())
			return;

		if (!validarCampo())
			return;

		if (accion.equals("NUEVO")) {
			grupo = new Grupo();
		}

		grupo.setDescripcion(ventanaGrupo.getTfDescripcion().getText());
		ventanaGrupo.getLblAvisoDescripcion().setVisible(false);
		ventanaGrupo.getTfBuscador().setEnabled(true);
		ventanaGrupo.getTable().setEnabled(true);

		try {
			if (accion.equals("NUEVO")) {
				dao.insertar(grupo);
			} else {
				dao.modificar(grupo);
			}
			
			dao.commit();
			estadoInicial(true);
			validarCampo();
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
		ventanaGrupo.getBtnGuardar().setText("Guardar");
		ventanaGrupo.getTfBuscador().setEnabled(true);
		ventanaGrupo.getLblAvisoDescripcion().setVisible(false);// Para cerrar el aviso que el campo es obligatorio

		ventanaGrupo.getTable().setEnabled(true);
		vaciarCampos();
		recuperarTodo();
	}

	public boolean verificarDescripcion() {
		if (ventanaGrupo.getTfDescripcion().getText().isEmpty()) {
			ventanaGrupo.getLblAvisoDescripcion().setVisible(false);
			return false;
		}
		if (lista != null) {
			for (int i = 0; i < lista.size(); i++) {
				boolean mismo = (!accion.equals("NUEVO") && grupo.getId() == lista.get(i).getId());
				if (ventanaGrupo.getTfDescripcion().getText().equals(lista.get(i).getDescripcion()) && !mismo) {
					ventanaGrupo.getLblAvisoDescripcion().setVisible(true);
					ventanaGrupo.getLblAvisoDescripcion().setText("*Descripción Duplicada!!");
					ventanaGrupo.getTfDescripcion().requestFocus();
					ventanaGrupo.getTfDescripcion().selectAll();
					return true;

				}
			}
			return false;
		}
		return false;
	}

	public void inicializarGrupo() {
		String tabla = "tb_grupo";
		dao = new GrupoDao();
		dao.eliminarTodo(tabla);
		try {
			dao.commit();
		} catch (Exception e) {
			dao.rollback();
		}
	}

}