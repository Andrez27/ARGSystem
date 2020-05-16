package py.edu.facitec.arg_system.tabla;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import py.edu.facitec.arg_system.entidad.Producto;

@SuppressWarnings("serial")
public class ModeloTablaListadoProducto extends AbstractTableModel {

	private String[] columnas = { "CODIGO", "DESCRIPCION", "PRECIO COSTO", "PRECIO MINIMO", "PRECIO VENTA", "GRUPO",
			"ESTADO" };
	private List<Producto> lista = new ArrayList<Producto>();

	public void setLista(List<Producto> lista) {
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
			return lista.get(r).getCodigo();
		case 1:
			return lista.get(r).getDescripcion();
		case 2:
			return lista.get(r).getPrecioCosto();
		case 3:
			return lista.get(r).getPrecioMinimo();
		case 4:
			return lista.get(r).getPrecioVenta();
		case 5:
			return lista.get(r).getGrupo().getDescripcion();
		case 6:
			if (lista.get(r).getEstado() == true) {
				return "Activo";
			} else {
				return "Inactivo";
			}

		}
		return null;
	}
}
