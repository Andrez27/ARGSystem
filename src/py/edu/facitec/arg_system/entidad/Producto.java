package py.edu.facitec.arg_system.entidad;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "tb_producto")
public class Producto {
	@Id
	@GenericGenerator(name = "pro_generator", strategy = "increment")
	@GeneratedValue(generator = "pro_generator")
	@Column(name = "pro_id")
	private int id;

	@Column(name = "pro_codigo", nullable = false, length = 30)
	private String codigo;

	@Column(name = "pro_descripcion", nullable = false, length = 100)
	private String descripcion;

	@Column(name = "pro_precio_costo", nullable = false)
	private Double precioCosto;

	@Column(name = "pro_precio_venta", nullable = false)
	private Double precioVenta;

	@Column(name = "pro_precio_minimo", nullable = false)
	private Double precioMinimo;

	@Column(name = "pro_estado")
	private Boolean estado;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Grupo grupo;

	@OneToMany(mappedBy = "producto")
	private List<PedidoDetalle> pedidoDetalles;

	public Producto() {
	}

	// GETTERS AND SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPrecioCosto() {
		return precioCosto;
	}

	public void setPrecioCosto(Double precioCosto) {
		this.precioCosto = precioCosto;
	}

	public Double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(Double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public Double getPrecioMinimo() {
		return precioMinimo;
	}

	public void setPrecioMinimo(Double precioMinimo) {
		this.precioMinimo = precioMinimo;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public List<PedidoDetalle> getPedidoDetalles() {
		return pedidoDetalles;
	}

	public void setPedidoDetalles(List<PedidoDetalle> pedidoDetalles) {
		this.pedidoDetalles = pedidoDetalles;
	}
	
	

}