package py.edu.facitec.arg_system.tabla;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import py.edu.facitec.arg_system.entidad.Cliente;

public class ModeloTablaCliente extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] columnas = { "NOMBRE", "DOCUMENTO", "TELEFONO", "DIRECCION" };
	private List<Cliente> lista = new ArrayList<Cliente>();

	public void setLista(List<Cliente> lista) {
		this.lista = lista;
		fireTableDataChanged();
	}

	@Override
	public int getRowCount() {// cantidad de filas
		return lista.size();

	}

	@Override
	public int getColumnCount() {// cantidad de columnsa
		return columnas.length;
	}

	@Override
	public String getColumnName(int i) {
		return columnas[i];
	}

	@Override
	public Object getValueAt(int r, int c) {// valor de la celda
		switch (c) {
		case 0:
			return lista.get(r).getNombre();
		case 1:
			return lista.get(r).getDocumento();
		case 2:
			return lista.get(r).getTelefono();
		case 3:
			return lista.get(r).getDireccion();
		}
		return null;
	}

}
