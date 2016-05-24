package view;

import javax.swing.JPanel;

import view.interfaces.ObserverInterface;
import view.toolbar.MyToolbar;

import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

/**
 * Class that define viewRiunioni
 * @author Alessandro
 *
 */
public class RiunioniGUI extends InitializeFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8816191580108552347L;
	private static final String TITOLO = "Riunioni";
	private static final LayoutManager LAYOUT = new BorderLayout();
	private static final Dimension DIMFRAME = new Dimension(400,400);
	private RiunioniGUI frame = this;
	private JPanel panelTool = new JPanel();
	private JPanel panelText = new JPanel();
	private MyToolbar toolbar;
	private final JLabel lblNomeEvento = new JLabel("Nome Evento");
	private final JLabel lblGiorno = new JLabel("Giorno");
	private final JLabel lblOra = new JLabel("Ora");
	private final JLabel lblNote = new JLabel("Note");
	private final JTextField txtEvento = new JTextField();
	private final JTextField txtGiorno = new JTextField();
	private final JTextField txtOra = new JTextField();
	private final JLabel lblMese = new JLabel("Mese");
	private final JTextField txtMese = new JTextField();
	private final JLabel lblAnno = new JLabel("Anno");
	private final JTextField txtAnno = new JTextField();
	private JTextArea textArea = new JTextArea();
	private GroupLayout gl_panelText = new GroupLayout(panelText);
	/**
	 * Method that get data from TextField's frame
	 * @return
	 * 			Map (String,String)
	 */		
	public Map<String,String> getTextField(){
		Map<String,String> mappa = new HashMap<>();
		mappa.put("Evento", txtEvento.getText());
		mappa.put("Giorno", txtGiorno.getText());
		mappa.put("Mese", txtMese.getText());
		mappa.put("Anno", txtAnno.getText());
		mappa.put("Note", textArea.getText());
		return mappa;
	}
	/**
	 * Create the RiunioniGUI frame.
	 * @param o
	 * 		Object ObserverInterface
	 */
	public RiunioniGUI(final ObserverInterface o) {
		super(TITOLO,LAYOUT,DIMFRAME);
		this.txtAnno.setColumns(10);
		this.txtMese.setColumns(10);
		this.txtOra.setColumns(10);
		this.txtGiorno.setColumns(10);
		this.txtEvento.setColumns(10);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(RiunioniGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		this.getMainPanel().setLayout(new BorderLayout(0, 0));
		this.getMainPanel().add(panelTool, BorderLayout.CENTER);
		this.toolbar = new MyToolbar(o, frame);
		this.panelTool.setLayout(new BorderLayout(0,0));
		this.panelTool.add(toolbar, BorderLayout.NORTH);
		this.panelTool.add(panelText, BorderLayout.CENTER);
		


		this.gl_panelText.setHorizontalGroup(
				this.gl_panelText.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelText.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelText.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNomeEvento)
						.addComponent(lblGiorno)
						.addComponent(lblOra)
						.addComponent(lblNote))
					.addGap(18)
					.addGroup(gl_panelText.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textArea)
						.addComponent(txtEvento, GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
						.addComponent(txtOra)
						.addGroup(gl_panelText.createSequentialGroup()
							.addComponent(txtGiorno, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblMese)
							.addGap(18)
							.addComponent(txtMese, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblAnno)
							.addGap(18)
							.addComponent(txtAnno, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		this.gl_panelText.setVerticalGroup(
				this.gl_panelText.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelText.createSequentialGroup()
					.addGap(32)
					.addGroup(gl_panelText.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNomeEvento)
						.addComponent(txtEvento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelText.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGiorno)
						.addComponent(txtGiorno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMese)
						.addComponent(txtMese, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAnno)
						.addComponent(txtAnno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelText.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOra)
						.addComponent(txtOra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelText.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNote)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		this.panelText.setLayout(gl_panelText);
		

	}
}
