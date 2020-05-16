package py.edu.facitec.arg_system.tabla;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import py.edu.facitec.arg_system.entidad.Grupo;

@SuppressWarnings("serial")
public class ModeloTablaListadoGrupo extends AbstractTableModel {

	private String[] columnas = { "ID", "DESCRIPCION" };
	private List<Grupo> lista = new ArrayList<Grupo>();

	public void setLista(List<Grupo> lista) {
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
			return lista.get(r).getId();
		case 1:
			return lista.get(r).getDescripcion();
		}
		return null;
	}

}
