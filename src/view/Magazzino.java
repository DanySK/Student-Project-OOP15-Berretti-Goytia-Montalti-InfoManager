package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.tabelle.PannelloTabelle;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JToolBar;
import javax.swing.JButton;

public class Magazzino extends InitializeFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9052481395137391704L;
	private JPanel contentPane;
	private JButton btnNuovo = new JButton("Nuovo");
	private JButton btnCerca = new JButton("Cerca");
	private JButton btnSalva = new JButton("Salva");
	private JButton btnAiuto = new JButton("Aiuto");
	private PannelloTabelle jPanel = new PannelloTabelle();
	private JToolBar toolBar = new JToolBar();
	private static final String TITOLO = "Menu";
	private static final LayoutManager LAYOUT = new BorderLayout();
	private static final Dimension DIMFRAME = new Dimension(938,476);


	/**
	 * Create the frame.
	 */
	public Magazzino() {
		super(TITOLO,LAYOUT,DIMFRAME);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(Magazzino.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		this.setTitle("Magazzino");
		this.setResizable(true);
		this.setBounds(100, 100, 781, 486);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		this.contentPane.add(toolBar, BorderLayout.NORTH);
		this.toolBar.add(btnNuovo);
		this.toolBar.add(btnCerca);
		this.toolBar.add(btnSalva);
		this.toolBar.add(btnAiuto);
		this.contentPane.add(jPanel);
		this.setContentPane(contentPane);
		this.addWindowListener(new WindowListener() {

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
			
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				Menu jFMenu = new Menu();
				jFMenu.setVisible(true);
				dispose();
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
