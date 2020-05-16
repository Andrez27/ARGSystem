package py.edu.facitec.arg_system.dao;

import java.util.List;

import org.hibernate.query.Query;

import py.edu.facitec.arg_system.entidad.Cliente;

public class ClienteDao extends GenericDao<Cliente> {

	public ClienteDao() {
		super(Cliente.class);
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> recuperarPorFiltro(String filtro) {
		getSession().beginTransaction();
		String hql = "from tb_cliente where UPPER(cli_nombre) like :cli_nombre order by id";
		Query<Cliente> query = getSession().createQuery(hql);
		query.setParameter("cli_nombre", "%" + filtro.toUpperCase() + "%");

		List<Cliente> lista = query.getResultList();
		commit();
		return lista;
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> recuperarPorCi(String filtro) {
		getSession().beginTransaction();
		// Sentencia para recuperar y filtrar los nombres de la base de datos
		String hql = "from tb_cliente where UPPER(cli_documento) like :cli_documento ";
		Query<Cliente> query = getSession().createQuery(hql);
		query.setParameter("cli_documento", "%" + filtro + "%");

		List<Cliente> lista = query.getResultList();
		commit();
		return lista;
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> filtroListadoClientes(String dNombre, String hNombre, String order) {
		getSession().beginTransaction();

		String hql = "from tb_cliente where UPPER(cli_nombre) BETWEEN :dNombre and :hNombre " + "order by "
				+ order.toLowerCase();

		Query<Cliente> query = getSession().createQuery(hql);

		query.setParameter("dNombre", dNombre.toUpperCase());
		query.setParameter("hNombre", hNombre.toUpperCase() + "zzz");

		List<Cliente> lista = query.getResultList();
		commit();
		return lista;
	}

}
