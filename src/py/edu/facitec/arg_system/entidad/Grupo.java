package py.edu.facitec.arg_system.entidad;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "tb_grupo")
public class Grupo {
	@Id
	@GenericGenerator(name = "gru_generator", strategy = "increment")
	@GeneratedValue(generator = "gru_generator")
	@Column(name = "gru_id")
	private int id;

	@Column(name = "gru_descripcion", length = 30)
	private String descripcion;

	@OneToMany(mappedBy = "grupo")
	private List<Producto> productos;

	public Grupo() {
	}

	// GETTERS AND SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

}