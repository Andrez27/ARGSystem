package py.edu.facitec.arg_system.controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import py.edu.facitec.arg_system.buscador.BuscadorGrupo;
import py.edu.facitec.arg_system.dao.GrupoDao;
import py.edu.facitec.arg_system.entidad.Grupo;
import py.edu.facitec.arg_system.interfaz.BuscadorGrupoInterface;
import py.edu.facitec.arg_system.tabla.ModeloTablaGrupo;

public class BuscadorGrupoController implements KeyListener, MouseListener {

	private BuscadorGrupo bGrupo;
	private List<Grupo> lista;
	private GrupoDao dao;
	private ModeloTablaGrupo mtGrupo;
	private BuscadorGrupoInterface interfaz;

	public void setInterfaz(BuscadorGrupoInterface interfaz) {
		this.interfaz = interfaz;
	}

	public BuscadorGrupoController(BuscadorGrupo bGrupo) {
		this.bGrupo = bGrupo;
		mtGrupo = new ModeloTablaGrupo();
		this.bGrupo.getTable().setModel(mtGrupo);

		dao = new GrupoDao();
		recuperarPorFiltro();
		setUpEvents();// se agregan los eventos
	}

	private void setUpEvents() {
		bGrupo.gettBuscador().addKeyListener(this);
		bGrupo.getTable().addMouseListener(this);
	}

	private void seleccionarRegistro(int posicion) {
		interfaz.setGrupo(lista.get(posicion));
		bGrupo.dispose();// cierra la ventana
	}

	private void recuperarPorFiltro() {
		lista = dao.recuperarPorFiltro(bGrupo.gettBuscador().getText());
		mtGrupo.setLista(lista);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getSource() == bGrupo.gettBuscador() && e.getKeyCode() == KeyEvent.VK_ENTER) {
			recuperarPorFiltro();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// si se da doble clic se selecciona el registro
		if (e.getSource() == bGrupo.getTable() && e.getClickCount() == 2) {
			seleccionarRegistro(bGrupo.getTable().getSelectedRow());
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