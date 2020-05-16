package py.edu.facitec.arg_system.tabla;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import py.edu.facitec.arg_system.entidad.PedidoDetalle;
import py.edu.facitec.arg_system.util.UtilidadesNumeros;

public class ModeloTablaPedidoDetalle extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String[] columnas = { "DESCRIPCION", "CANTIDAD", "PRECIO", "SUBTOTAL" };
	private List<PedidoDetalle> lista = new ArrayList<PedidoDetalle>();

	public void setLista(List<PedidoDetalle> lista) {
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
			return lista.get(r).getProducto().getDescripcion();
		case 1:
			return lista.get(r).getCantidad();
		case 2:
			return  UtilidadesNumeros.doubleString(lista.get(r).getProducto().getPrecioVenta());
		case 3:
			return UtilidadesNumeros.doubleString(lista.get(r).getCantidad() * lista.get(r).getProducto().getPrecioVenta());
		}
		return null;
	}

}
