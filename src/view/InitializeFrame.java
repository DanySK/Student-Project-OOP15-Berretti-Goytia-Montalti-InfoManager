package view;

	import java.awt.Dimension;
	import java.awt.LayoutManager;

	import javax.swing.JFrame;
	import javax.swing.JPanel;
	/**
	 * classe de definisce gli aspetti comuni dei jframe per questo programma.
	 *
	 */
	public class InitializeFrame extends JFrame {
	    /**
	     * 
	     */
	    private static final long serialVersionUID = -6847630922237609866L;
	    private final Boolean resizable = false;
	    private final Integer close = JFrame.EXIT_ON_CLOSE;

	    /**
	     * costruttore
	     * 
	     * @param title
	     *            titolo frame
	     * @param layout
	     *            layout main panel
	     * @param dimension
	     *            dimensioni frame
	     */
	    protected InitializeFrame(final String title, final LayoutManager layout, final Dimension dimension) {
	        this.setTitle(title);
	        this.setResizable(this.resizable);
	        this.setDefaultCloseOperation(this.close);
	        this.setSize(dimension);
	        JPanel main = new JPanel();
	        main.setLayout(layout);
	        this.getContentPane().add(main);
	    }

	    /**
	     * ritorna il mainpanel
	     * 
	     * @return il main panel
	     */
	    protected JPanel getMainPanel() {
	        return (JPanel) this.getContentPane().getComponent(0);
	    }

	    /**
	     * rende visibile il frame
	     * 
	     * @param switchOn
	     *            true = visibile
	     */
	    public void display(final Boolean switchOn) {
	        this.setVisible(switchOn);
	    }
	    
	}