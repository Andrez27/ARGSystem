package py.edu.facitec.arg_system.dao;

import java.util.List;

import org.hibernate.query.Query;

import py.edu.facitec.arg_system.entidad.Producto;

@SuppressWarnings("unchecked")
public class ProductoDao extends GenericDao<Producto> {

	public ProductoDao() {
		super(Producto.class);
	}

	public List<Producto> recuperarPorFiltro(String filtro) {
		getSession().beginTransaction();
		String hql = "from tb_producto "
				+ "where UPPER(pro_descripcion) like :filtro and pro_estado = true order by id";
		Query<Producto> query = getSession().createQuery(hql);

		query.setParameter("filtro", "%" + filtro.toUpperCase() + "%");

		List<Producto> lista = query.getResultList();
		commit();
		return lista;
	}

	public List<Producto> recuperarPorCodigo(String filtro) {
		getSession().beginTransaction();
		// Sentencia para recuperar y filtrar los nombres de la base de datos
		String hql = "from tb_producto where UPPER(pro_codigo) like :pro_codigo and pro_estado = true";
		Query<Producto> query = getSession().createQuery(hql);
		query.setParameter("pro_codigo", "%" + filtro + "%");

		List<Producto> lista = query.getResultList();
		commit();
		return lista;
	}

	public List<Producto> filtroListadoProductos(String dCodigo, String hCodigo, String dDescripcion,
			String hDescripcion, String order) {
		getSession().beginTransaction();

		String hql = "from tb_producto where pro_codigo BETWEEN :dCodigo and :hCodigo "
				+ "and pro_descripcion BETWEEN :dDescripcion and :hDescripcion " + "order by " + order.toLowerCase();

		Query<Producto> query = getSession().createQuery(hql);

		query.setParameter("dCodigo", dCodigo);
		query.setParameter("hCodigo", hCodigo);
		query.setParameter("dDescripcion", dDescripcion);
		query.setParameter("hDescripcion", hDescripcion);

		List<Producto> lista = query.getResultList();
		commit();
		return lista;
	}
}