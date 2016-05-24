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
 * Class that define viewReportVendite
 * @author Alessandro
 *
 */
public class ReportVenditeGUI extends InitializeFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5756256366498583375L;
	private static final String TITOLO = "Report Vendite";
	private static final LayoutManager LAYOUT = new BorderLayout();
	private static final Dimension DIMFRAME = new Dimension(938,476);
	private ReportVenditeGUI frame = this;
	private JPanel panelTool = new JPanel();
	private JPanel panelText = new JPanel();
	private MyToolbar toolbar;
	private final JTextArea textArea = new JTextArea();
	/**
	 * Method that set text into the textArea of the frame
	 * @param testoVendite
	 * 				Object String
	 */
	public void setTextVendite(String testoVendite){
		this.textArea.setText(testoVendite);
	}
	/**
	 * Create the ReportVenditeGUI frame.
	 * @param o
	 * 			Object ObserverInterface
	 */
	public ReportVenditeGUI(final ObserverInterface o) {
		super(TITOLO,LAYOUT,DIMFRAME);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(ReportVenditeGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		this.setTitle("Report Vendite");
		this.getMainPanel().setLayout(new BorderLayout(0, 0));
		this.getMainPanel().add(panelTool, BorderLayout.CENTER);
		this.toolbar = new MyToolbar(o, frame);
		this.panelTool.setLayout(new BorderLayout(0,0));
		this.panelTool.add(toolbar, BorderLayout.NORTH);
		this.panelTool.add(panelText, BorderLayout.CENTER);
		panelText.setLayout(new BorderLayout(0, 0));
		
		panelText.add(textArea, BorderLayout.CENTER);
		
	}

}
