package py.edu.facitec.arg_system.controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import py.edu.facitec.arg_system.buscador.BuscadorCliente;
import py.edu.facitec.arg_system.dao.ClienteDao;
import py.edu.facitec.arg_system.entidad.Cliente;
import py.edu.facitec.arg_system.interfaz.BuscadorClienteInterface;
import py.edu.facitec.arg_system.tabla.ModeloTablaCliente;

public class BuscadorClienteController implements KeyListener, MouseListener {

	private BuscadorCliente bCliente;
	private List<Cliente> lista;
	private ClienteDao dao;
	private ModeloTablaCliente mtCliente;
	private BuscadorClienteInterface interfaz;

	public void setInterfaz(BuscadorClienteInterface interfaz) {
		this.interfaz = interfaz;
	}

	public BuscadorClienteController(BuscadorCliente bCliente) {
		this.bCliente = bCliente;
		mtCliente = new ModeloTablaCliente();
		this.bCliente.getTable().setModel(mtCliente);
		dao = new ClienteDao();
		recuperarPorFiltro();
		setUpEvents();// se agregan los eventos
	}

	private void setUpEvents() {
		bCliente.gettBuscador().addKeyListener(this);
		bCliente.getTable().addMouseListener(this);
	}

	private void seleccionarRegistro(int posicion) {
		interfaz.setCliente(lista.get(posicion));
		bCliente.dispose();// cierra la ventana
	}

	private void recuperarPorFiltro() {
		lista = dao.recuperarPorFiltro(bCliente.gettBuscador().getText());
		mtCliente.setLista(lista);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getSource() == bCliente.gettBuscador() && e.getKeyCode() == KeyEvent.VK_ENTER) {
			recuperarPorFiltro();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// si se da doble clic se selecciona el registro
		if (e.getSource() == bCliente.getTable() && e.getClickCount() == 2) {
			seleccionarRegistro(bCliente.getTable().getSelectedRow());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
