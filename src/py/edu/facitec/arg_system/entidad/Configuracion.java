package py.edu.facitec.arg_system.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "tb_configuracion")
public class Configuracion {
	@Id
	@GenericGenerator(name = "con_generator", strategy = "increment") // Genera el numero
	@GeneratedValue(generator = "con_generator") // ultimo id +1
	@Column(name = "con_id") // Indica el nombre de la columna en la DB
	private int id;

	@Column(name = "con_empresa", nullable = false, length = 50)
	private String empresa;

	@Column(name = "con_ruc", nullable = false, length = 50)
	private String ruc;

	@Column(name = "con_telefono", nullable = false, length = 20)
	private String telefono;

	@Column(name = "con_email", nullable = false, length = 50)
	private String email;

	@Column(name = "con_direccion", nullable = false, length = 100)
	private String direccion;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}
