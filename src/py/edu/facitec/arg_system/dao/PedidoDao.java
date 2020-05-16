package py.edu.facitec.arg_system.dao;

import java.util.List;

import org.hibernate.query.Query;

import py.edu.facitec.arg_system.entidad.Pedido;

public class PedidoDao extends GenericDao<Pedido> {

	public PedidoDao() {
		super(Pedido.class);
	}


	public List<Pedido> recuperarPorFiltro(String filtro) {
		getSession().beginTransaction();
		//Sentencia para recuperar y filtrar los pedidos de la base de datos
		String hql = "from tb_pedido where ped_id like :ped_id order by id ";
		@SuppressWarnings("unchecked")
		Query<Pedido> query = getSession().createQuery(hql);
		query.setParameter("ped_id", filtro);

		List<Pedido> lista = query.getResultList();
		commit();
		return lista;
	}
	
	public List<Pedido> filtroInformePedido(String desde, String hasta, String cliente) {
		getSession().beginTransaction();
		
		//Sentencia para recuperar y filtrar los pedidos de la base de datos
		String hql = "from tb_pedido where ped_fecha >= '"+desde+" 00:00:00' and ped_fecha <= '"+hasta+" 23:59:59' "
				+ " and UPPER(cliente.nombre) like :cliente order by id ";
		
		@SuppressWarnings("unchecked")
		Query<Pedido> query = getSession().createQuery(hql);
		query.setParameter("cliente", "%"+cliente.toUpperCase()+"%");

		List<Pedido> lista = query.getResultList();
		commit();
		return lista;
	}
	
	
	
}