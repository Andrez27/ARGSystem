package py.edu.facitec.arg_system.buscador;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
abstract public class BuscadorGenerico extends JDialog {
	private JTextField tBuscador;
	private JTable table;

	/**
	 * Create the dialog.
	 */
	public BuscadorGenerico() {
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(this);
		setModal(true);
		
		setTitle(getTitulo());
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		tBuscador = new JTextField();
		tBuscador.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Filtro de busqueda", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		getContentPane().add(tBuscador, BorderLayout.NORTH);
		tBuscador.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);

	}
	
	public JTextField gettBuscador() {
		return tBuscador;
	}

	public JTable getTable() {
		return table;
	}

	abstract protected String getTitulo();
	
	
}
