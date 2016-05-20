package view;

import javax.swing.JButton;
import javax.swing.JPanel;

import view.interfaces.ObserverInterface;
import view.toolbar.MyToolbar;

import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;


public class RiunioniGUI extends InitializeFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8816191580108552347L;
	private JPanel contentPane;
	private static final String TITOLO = "Riunioni";
	private static final LayoutManager LAYOUT = new BorderLayout();
	private static final Dimension DIMFRAME = new Dimension(830,568);
	private MyToolbar toolbar;
	private JPanel panel = new JPanel();
	private JButton btnOk = new JButton("OK");


	/**
	 * Create the frame.
	 */
	public RiunioniGUI(final ObserverInterface o) {
		super(TITOLO,LAYOUT,DIMFRAME);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(RiunioniGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		this.setContentPane(contentPane);
		this.toolbar = new MyToolbar(o);
		getContentPane().add(toolbar, BorderLayout.NORTH);
		panel.add(btnOk);
		getContentPane().add(panel, BorderLayout.SOUTH);
		

	}

}
