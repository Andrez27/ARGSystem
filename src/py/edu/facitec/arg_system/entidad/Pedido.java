package py.edu.facitec.arg_system.entidad;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.transaction.Transactional;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "tb_pedido")
@Transactional
public class Pedido {
	@Id
	@GenericGenerator(name = "ped_generator", strategy = "increment")
	@GeneratedValue(generator = "ped_generator")
	@Column(name = "ped_id")
	private int id;

	@Column(name = "ped_fecha", nullable = false)
	private Date fecha;

	@Column(name = "ped_obs", length = 100)
	private String obs;

	@Column(name = "ped_total", nullable = false)
	private Double total;
	
	@Column(name = "ped_estado")
	private Boolean estado;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Cliente cliente;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<PedidoDetalle> pedidoDetalles;

	public Pedido() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<PedidoDetalle> getPedidoDetalle() {
		return pedidoDetalles;
	}

	public void setPedidoDetalles(List<PedidoDetalle> pedidoDetalles) {
		this.pedidoDetalles = pedidoDetalles;
	}

	public List<PedidoDetalle> getPedidoDetalles() {
		return pedidoDetalles;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	
	
	
}