package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JPanel;

import view.interfaces.ObserverInterface;
import view.toolbar.MyToolbar;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class ScontriniGUI extends InitializeFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3626871331020606696L;
	private static final String TITOLO = "Scontrini";
	private static final LayoutManager LAYOUT = new BorderLayout();
	private static final Dimension DIMFRAME = new Dimension(664,413);
	private JFrame frame = this;
	private JTextField txtProdotto;
	private JTextField txtPrezzoUnitario;
	private JTextField txtQuantita;
	private JTextField txtIva;
	private JTextField txtSconto;
	private JTextField txtScontrino;
	private JTextArea textArea = new JTextArea();
	private JLabel lblProdotto = new JLabel("Prodotto");
	private JLabel lblPrezzoUnitario = new JLabel("Prezzo Unitario");
	private JLabel lblQuantita = new JLabel("Quanti\u00E0");
	private JLabel lblIva = new JLabel("IVA");
	private JLabel lblSconto = new JLabel("Sconto");
	private JButton btnStampa = new JButton("Stampa");
	private JLabel lblNscontrino = new JLabel("N.Scontrino");
	private JLabel lblNewLabel = new JLabel("\u20AC");
	private JLabel label = new JLabel("%");
	private JLabel label_1 = new JLabel("%");
	private MyToolbar toolbar;
	private JPanel panelTool = new JPanel();
	private JPanel panelText = new JPanel();
	private GroupLayout gl_contentPane = new GroupLayout(panelText);
	private double imponibile;
	private double totale;
	private double totiva;
	private final JButton btnAggiungi = new JButton("Aggiungi");
	private final JLabel lblAnteprimaDiStampa = new JLabel("Anteprima di Stampa");

	/**
	 * Create the frame.
	 */
	public ScontriniGUI(final ObserverInterface o) {
		super(TITOLO,LAYOUT,DIMFRAME);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(ScontriniGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		this.getMainPanel().setLayout(new BorderLayout(0, 0));
		this.getMainPanel().add(panelTool, BorderLayout.CENTER);
		panelTool.setLayout(new BorderLayout(0, 0));
		this.toolbar = new MyToolbar(o,frame);
		this.panelTool.add(toolbar, BorderLayout.NORTH);
		this.panelTool.add(panelText);
		this.txtProdotto = new JTextField();
		this.txtProdotto.setColumns(10);
		
		this.txtPrezzoUnitario = new JTextField();
		this.txtPrezzoUnitario.setColumns(10);
		
		this.txtQuantita = new JTextField();
		this.txtQuantita.setColumns(10);
		
		this.txtIva = new JTextField();
		this.txtIva.setColumns(10);
		
		this.txtSconto = new JTextField();
		this.txtSconto.setColumns(10);

		this.txtScontrino = new JTextField();
		this.txtScontrino.setColumns(10);
		
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblProdotto)
								.addComponent(lblPrezzoUnitario)
								.addComponent(lblQuantita)
								.addComponent(lblIva)
								.addComponent(lblSconto)
								.addComponent(lblNscontrino))
							.addGap(40)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtScontrino)
								.addComponent(txtProdotto, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
								.addComponent(txtPrezzoUnitario)
								.addComponent(txtQuantita)
								.addComponent(txtIva)
								.addComponent(txtSconto))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
								.addComponent(label)
								.addComponent(label_1)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(54)
							.addComponent(btnStampa, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
							.addGap(40)
							.addComponent(btnAggiungi, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(31)
							.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(131)
							.addComponent(lblAnteprimaDiStampa, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(104, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(58)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblProdotto)
								.addComponent(txtProdotto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(33)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPrezzoUnitario)
								.addComponent(txtPrezzoUnitario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel))
							.addGap(30)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblQuantita)
								.addComponent(txtQuantita, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(29)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblIva)
								.addComponent(txtIva, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label))
							.addGap(29)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSconto)
								.addComponent(txtSconto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_1)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNscontrino)
								.addComponent(txtScontrino, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnStampa)
						.addComponent(btnAggiungi))
					.addGap(28))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAnteprimaDiStampa)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
					.addContainerGap())
		);
		this.panelText.setLayout(gl_contentPane);
		this.btnStampa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				Calcola(Integer.parseInt(txtPrezzoUnitario.getText()), Integer.parseInt(txtQuantita.getText())
						, Integer.parseInt(txtIva.getText()));
				textArea.setText("");
				textArea.setText("\tNome\n" + "\tIndirizzo\n" + "\tTelefono\n" + "\tN.Scontrino"+ "\n\n\n" + 
						"Quantità: " + txtQuantita.getText() +" "+ txtProdotto.getText() +"     Prezzo: " + txtPrezzoUnitario.getText() + "€ "
						+ "\n\tSconto:\t"
						+ txtSconto.getText() + "\n\n =====================================\n\n" +
						"Imponibile:\t\t" + String.valueOf(imponibile) + "€\nIva: " + txtIva.getText() + "%"  + "\t\t" + totiva + "€" +
						"\n\n =====================================\n\n" + "Totale:\t\t" + 
				String.valueOf(totale)+"€");
				}
				catch (NumberFormatException e1){
					JOptionPane.showMessageDialog(frame,"Inserisci i campi correttamente","Campi scorretti",JOptionPane.ERROR_MESSAGE);
				};
			}
		});
		this.btnAggiungi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//metodo con lista per permettere la vendita di più prodotti in uno scontrino
			}
		});
		
	}
	public Map<String,String> getTextField(){
		Map<String,String> mappa = new HashMap<>();
		mappa.put("Prodotto", txtProdotto.getText());
		mappa.put("PrezzoUnitario", txtPrezzoUnitario.getText());
		mappa.put("Quantita", txtQuantita.getText());
		mappa.put("Iva", txtIva.getText());
		mappa.put("Sconto", txtSconto.getText());
		mappa.put("Scontrino", txtScontrino.getText());
		return mappa;
	}
	
	private double Calcola (Integer prezzo, Integer quantita, Integer iva){
		this.imponibile=0;
		this.totale=0;
		if(txtSconto.getText().equals("")){
			this.imponibile = prezzo*quantita;
			this.totiva = (this.imponibile * iva)/100;
			this.totale = this.totiva + this.imponibile;
		} else {
			this.imponibile = ((prezzo*quantita)*Integer.parseInt(txtSconto.getText())/100);
			this.imponibile = (prezzo*quantita)-this.imponibile;
			this.totiva = (this.imponibile * iva)/100;
			this.totale = this.totiva + this.imponibile;
		}
		return this.totale;
		
	}
}
