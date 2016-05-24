package view;

	import java.awt.Dimension;
	import java.awt.LayoutManager;

	import javax.swing.JFrame;
	import javax.swing.JPanel;
	/**
	 * 
	 * Class that define the common aspect of JFrame for this program
	 *@author Alessandro
	 */
	public class InitializeFrame extends JFrame {
	    /**
	     * 
	     */
	    private static final long serialVersionUID = -6847630922237609866L;
	    private final Boolean resizable = false;
	    private final Integer close = JFrame.EXIT_ON_CLOSE;

	    /**
	     * Constructor
	     * 
	     * @param title
	     *            Frame's title
	     * @param layout
	     *            Layout main panel
	     * @param dimension
	     *            Dimension of the frame
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
	     * Method for return MainPanel
	     * 
	     * @return 
	     * 			MainPanel
	     */
	    protected JPanel getMainPanel() {
	        return (JPanel) this.getContentPane().getComponent(0);
	    }

	    /**
	     * Set the frame visible
	     * 
	     * @param switchOn
	     *            true = visible
	     */
	    public void display(final Boolean switchOn) {
	        this.setVisible(switchOn);
	    }
	    
	}