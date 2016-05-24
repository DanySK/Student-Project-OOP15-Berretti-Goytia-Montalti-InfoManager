package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JPanel;

import view.interfaces.ObserverInterface;
import view.toolbar.MyToolbar;

import java.awt.Toolkit;
import javax.swing.JTextArea;
/**
 * Class that define viewRegistriIva
 * @author Alessandro
 *
 */
public class RegistriIvaGUI extends InitializeFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6419493734280735250L;
	private static final String TITOLO = "Registri IVA";
	private static final LayoutManager LAYOUT = new BorderLayout();
	private static final Dimension DIMFRAME = new Dimension(830,568);
	private RegistriIvaGUI frame = this;
	private JPanel panelTool = new JPanel();
	private JPanel panelText = new JPanel();
	private MyToolbar toolbar;
	private final JTextArea textArea = new JTextArea();

	/**
	 * Method that set Text into the TextArea of the frame
	 * @param testoIva
	 * 			Object String
	 */
	public void setTextIva(String testoIva){
		this.textArea.setText(testoIva);
	}

	/**
	 * Create the RegistriIvaGUI frame.
	 * @param o
	 * 			Object ObserverInterface
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
		this.panelText.setLayout(new BorderLayout(0, 0));
		this.panelText.add(textArea, BorderLayout.CENTER);
		
	}

}
