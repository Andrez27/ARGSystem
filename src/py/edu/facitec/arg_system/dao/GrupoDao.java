package py.edu.facitec.arg_system.dao;

import java.util.List;

import org.hibernate.query.Query;

import py.edu.facitec.arg_system.entidad.Grupo;

@SuppressWarnings("unchecked")
public class GrupoDao extends GenericDao<Grupo> {

	public GrupoDao() {
		super(Grupo.class);
	}

	public List<Grupo> recuperarPorFiltro(String filtro) {
		getSession().beginTransaction();
		String hql = "from tb_grupo where UPPER(gru_descripcion) like :gru_descrip order by id";
		Query<Grupo> query = getSession().createQuery(hql);

		query.setParameter("gru_descrip", "%" + filtro.toUpperCase() + "%");

		List<Grupo> lista = query.getResultList();
		commit();
		return lista;

	}
	
	public List<Grupo> filtroListadoGrupos(String dGrupo, String hGrupo, String order) {
		getSession().beginTransaction();
		
		String hql = "from tb_grupo where UPPER(gru_descripcion) BETWEEN :dGrupo and :hGrupo "
				+ "order by "+order.toLowerCase();
		
		Query<Grupo> query = getSession().createQuery(hql);
		
		query.setParameter("dGrupo", dGrupo.toUpperCase());
		query.setParameter("hGrupo", hGrupo.toUpperCase()+"zzz");

		List<Grupo> lista = query.getResultList();
		commit();
		return lista;
	}


	
}