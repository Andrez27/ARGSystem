package py.edu.facitec.arg_system.dao;

import java.util.List;

import py.edu.facitec.arg_system.entidad.PedidoDetalle;

public class PedidoDetalleDao extends GenericDao<PedidoDetalle> {

	public PedidoDetalleDao() {
		super(PedidoDetalle.class);
	}

	public List<PedidoDetalle> recuperarPorFiltro(String filtro) {
		return null;
	}
}