package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JPanel;

import view.interfaces.ObserverInterface;
import view.tabelle.PannelloTabelle;
import view.toolbar.MyToolbar;

import java.awt.Toolkit;

public class RegistriIvaGUI extends InitializeFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6419493734280735250L;
	private PannelloTabelle jPanel = new PannelloTabelle();
	private static final String TITOLO = "Registri IVA";
	private static final LayoutManager LAYOUT = new BorderLayout();
	private static final Dimension DIMFRAME = new Dimension(830,568);
	private JFrame frame = this;
	private JPanel panelTool = new JPanel();
	private JPanel panelText = new JPanel();
	private MyToolbar toolbar;


	/**
	 * Create the frame.
	 */
	public RegistriIvaGUI(final ObserverInterface o) {
		super(TITOLO,LAYOUT,DIMFRAME);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(RegistriIvaGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		this.getMainPanel().setLayout(new BorderLayout(0, 0));
		this.getMainPanel().add(panelTool, BorderLayout.CENTER);
		this.toolbar = new MyToolbar(o, frame);
		this.panelTool.setLayout(new BorderLayout(0,0));
		this.panelTool.add(toolbar, BorderLayout.NORTH);
		this.panelTool.add(panelText, BorderLayout.CENTER);
		this.panelText.add(jPanel);
		
	}

}
