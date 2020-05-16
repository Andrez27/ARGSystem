package py.edu.facitec.arg_system.tabla;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import py.edu.facitec.arg_system.entidad.Pedido;
import py.edu.facitec.arg_system.util.FechaUtil;

@SuppressWarnings("serial")
public class ModeloTablaPedido extends AbstractTableModel {

	private String[] columnas = { "NUMERO", "FECHA", "CLIENTE", "TOTAL" };
	private List<Pedido> lista = new ArrayList<Pedido>();

	public void setLista(List<Pedido> lista) {
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
			return FechaUtil.convertirDateAString(lista.get(r).getFecha());
		case 2:
			return lista.get(r).getCliente().getNombre();
		case 3:
			return lista.get(r).getTotal();
			
		}
		return null;
	}

}
