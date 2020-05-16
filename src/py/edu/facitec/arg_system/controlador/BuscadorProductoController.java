package py.edu.facitec.arg_system.controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import py.edu.facitec.arg_system.buscador.BuscadorProducto;
import py.edu.facitec.arg_system.dao.ProductoDao;
import py.edu.facitec.arg_system.entidad.Producto;
import py.edu.facitec.arg_system.interfaz.BuscadorProductoInterface;
import py.edu.facitec.arg_system.tabla.ModeloTablaProducto;

public class BuscadorProductoController implements KeyListener, MouseListener {

	private BuscadorProducto bProducto;
	private List<Producto> lista;
	private ProductoDao dao;
	private ModeloTablaProducto mtProducto;
	private BuscadorProductoInterface interfaz;

	public void setInterfaz(BuscadorProductoInterface interfaz) {
		this.interfaz = interfaz;
	}

	public BuscadorProductoController(BuscadorProducto bProducto) {
		this.bProducto = bProducto;
		mtProducto = new ModeloTablaProducto();
		this.bProducto.getTable().setModel(mtProducto);

		dao = new ProductoDao();
		recuperarPorFiltro();
		setUpEvents();// se agregan los eventos
	}

	private void setUpEvents() {
		bProducto.gettBuscador().addKeyListener(this);
		bProducto.getTable().addMouseListener(this);
	}

	private void seleccionarRegistro(int posicion) {
		interfaz.setProducto(lista.get(posicion));
		bProducto.dispose();// cierra la ventana
	}

	private void recuperarPorFiltro() {
		lista = dao.recuperarPorFiltro(bProducto.gettBuscador().getText());
		mtProducto.setLista(lista);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getSource() == bProducto.gettBuscador() && e.getKeyCode() == KeyEvent.VK_ENTER) {
			recuperarPorFiltro();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// si se da doble clic se selecciona el registro
		if (e.getSource() == bProducto.getTable() && e.getClickCount() == 2) {
			seleccionarRegistro(bProducto.getTable().getSelectedRow());
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
