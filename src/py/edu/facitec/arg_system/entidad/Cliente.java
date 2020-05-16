package py.edu.facitec.arg_system.entidad;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "tb_cliente") // Indica el nombre de la tabla en la DB
public class Cliente {
	@Id // Indica que es Clave primaria
	@GenericGenerator(name = "cli_generator", strategy = "increment") // Genera el numero
	@GeneratedValue(generator = "cli_generator") // ultimo id +1
	@Column(name = "cli_id") // Indica el nombre de la columna en la DB
	private int id;
	// Indica que el campo no puede estar vacio con maximo de caracteres de 50
	// caracteres
	@Column(name = "cli_nombre", nullable = false, length = 50)
	private String nombre;

	@Column(name = "cli_documento", nullable = false, length = 15)
	private String documento;

	@Column(name = "cli_direccion", length = 100)
	private String direccion;

	@Column(name = "cli_telefono", nullable = false, length = 20)
	private String telefono;

	// Relacion de uno a muchos
	@OneToMany(mappedBy = "cliente", fetch=FetchType.EAGER)
	private List<Pedido> pedidos;

	public Cliente() {
	}

	// GETTERS AND SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

}