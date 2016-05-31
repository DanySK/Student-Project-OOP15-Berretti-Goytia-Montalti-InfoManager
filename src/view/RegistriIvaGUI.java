package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import view.interfaces.ObserverInterface;
import view.toolbar.MyToolbar;

import java.awt.Toolkit;
import javax.swing.JTextArea;
/**
 * Classe che definisce viewRegistriIva.
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
	private static final Dimension DIMFRAME = new Dimension(830, 568);
	private RegistriIvaGUI frame = this;
	private JPanel panelTool = new JPanel();
	private JPanel panelText = new JPanel();
	private MyToolbar toolbar;
	private JScrollPane scrollArea = new JScrollPane();
	private final JTextArea textArea = new JTextArea();

	/**
	 * Metodo per settare il testo nellaT extArea del frame.
	 * 
	 */
	//CHECKSTYLE:OFF:
	public void setTextIva() {
		String iva = new String();
		double totDare = 0;
		double totAvere = 0;
		iva = ("\t\t\t\t\t\t\tAVERE \t\tDARE\n\n");
		for (Integer i = 0; i<10; i++){
			totAvere = totAvere + 1234;
		iva = iva + ("Iva ns/debito\t\t\t\t\t\t" + "1234\n");
		}
		for (Integer i = 0; i<10; i++){
			totDare = totDare + 1234;
		iva = iva + ("Iva ns/credito\t\t\t\t\t\t\t\t" + "1234\n");
		}
		iva = iva + ("\n\n\n\n\n\n\n\n\n\nTotale\t\t\t\t\t\t\t" + totAvere + "\t\t" + totDare);
		this.textArea.setText(iva);
	}
	//CHECKSTYLE:ON:

	/**
	 * Costruttore del RegistriIvaGUI frame.
	 * @param o
	 * 			Oggetto ObserverInterface
	 */
	public RegistriIvaGUI(final ObserverInterface o) {
		super(TITOLO, LAYOUT, DIMFRAME);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(RegistriIvaGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		this.getMainPanel().setLayout(new BorderLayout(0, 0));
		this.getMainPanel().add(panelTool, BorderLayout.CENTER);
		this.toolbar = new MyToolbar(o, frame);
		this.panelTool.setLayout(new BorderLayout(0, 0));
		this.panelTool.add(toolbar, BorderLayout.NORTH);
		this.panelTool.add(panelText, BorderLayout.CENTER);
		this.panelText.setLayout(new BorderLayout(0, 0));
		this.setTextIva();
		this.scrollArea.setViewportView(textArea);
		this.textArea.setEditable(false);
		this.panelText.add(scrollArea, BorderLayout.CENTER);
		
	}

}
