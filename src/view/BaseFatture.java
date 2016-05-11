package view;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.util.LinkedList;
import javax.swing.JLayeredPane;
import view.tabelle.PannelloTabelle;
import view.toolbar.MyToolbar;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.BorderLayout;


public class BaseFatture extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7696186269898876284L;
	private JTextField textNumeroOrdine;
	private JTextField textDataOrdine;
	private JTextField textFornitore;
	private JTextField textDal;
	private JTextField textAl;
	private JTextField textTipoOrdine;
	private JTextField textNegozio;
	private JTextField textFieldPagamento;
	private JTextField textFieldBanca;
	private JTextField textFieldNote;
	private JTextField textFieldSconto;
	private JTextField textFieldProdotto;
	private JTextField textFieldPrezzo;
	private JTextField textFieldIVA;
	private JTextField textFieldQuantita;
	private JLabel lblFornitore = new JLabel("Fornitore/Cliente");
	private JLabel lblDataOrdine = new JLabel("Data Ordine");
	private LinkedList<String> list = new LinkedList<>();
	private JLabel lblNumeroOrdine = new JLabel("Numero Ordine");
	private JButton btnProssimo = new JButton("Prossimo>");
	private JButton btnPrecedente = new JButton("<Precedente");
	private JButton btnAnnulla = new JButton("Annulla");
	private JButton btnOk = new JButton("OK");
	private JLayeredPane layeredPane = new JLayeredPane();
	private MyToolbar toolBar = new MyToolbar();
	private JComboBox<?> comboBoxFornitore = new JComboBox<>();
	private JLabel lblConsegna = new JLabel("Consegna Dal");
	private JLabel lblAl = new JLabel("Al");
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private JPanel panelTestata = new JPanel();
	private JLabel lblTipoOrdine = new JLabel("Tipo Ordine");
	private JComboBox<?> comboBoxTipoOrdine = new JComboBox<>();
	private JLabel lblNegozio = new JLabel("Negozio");
	private JLabel lblPagamento = new JLabel("Pagamento");
	private JLabel lblBanca = new JLabel("Banca");
	private JLabel lblNote = new JLabel("Note");
	private JLabel lblSconto = new JLabel("Sconto");
	private JLabel label = new JLabel("%");
	private JLabel lblProdotto = new JLabel("Prodotto");
	private JLabel lblNewLabel = new JLabel("P.U.");
	private JLabel label_1 = new JLabel("\u20AC");
	private JButton bAggiungi = new JButton("Aggiungi");
	private JLabel lblIva = new JLabel("IVA");
	private JLabel label_2 = new JLabel("%");
	private JLabel lblQuantita = new JLabel("Quantit\u00E0");
	private PannelloTabelle textArea = new PannelloTabelle();
	private JPanel panelFattura = new JPanel();
	private JLabel lblCodFornitore = new JLabel("Cod. Fornitore");
	
	public String getTextfield(){
		return "Numero Ordine: " + textNumeroOrdine.getText() + "\n" +
				"Data Ordine: " + textDataOrdine.getText() + "\n" +
				"Fornitore/Cliente: " + textFornitore.getText() + "\n" +
				"Dal: " + textDal.getText() + "\n" +
				"Al: " + textAl.getText() + "\n" +
				"Tipo Ordine: " + textTipoOrdine.getText() + "\n" +
				"Negozio: " + textNegozio.getText() + "\n" +
				"Pagamento: " + textFieldPagamento.getText() + "\n" +
				"Banca: " + textFieldBanca.getText() + "\n" +
				"Note: " + textFieldNote.getText() + "\n" +
				"Sconto: " + textFieldSconto.getText()+ "\n" +
				"Prodotto: " + textFieldProdotto.getText() + "\n" +
				"Prezzo: " + textFieldPrezzo.getText() + "\n" +
				"IVA: " + textFieldIVA.getText() + "\n" +
				"Quantita: " + textFieldQuantita.getText();
	}
	
	public void setForCli(){
		this.textFornitore.setText("q");
	}
	public void setTipoOrd(){
		this.textTipoOrdine.setText("c");
	}
	
	/**
	 * Create the panel.
	 */
	public BaseFatture() {
		this.setEnabled(true);
		this.add(toolBar);

		this.btnOk.setBounds(377, 464, 67, 23);
		this.btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String a = new String(getTextfield());
				System.out.println(a);
			}
		});
		this.layeredPane.add(btnOk);
		
		this.btnAnnulla.setBounds(454, 464, 85, 23);
		this.layeredPane.add(btnAnnulla);
		
		this.btnPrecedente.setBounds(549, 464, 117, 23);
		this.layeredPane.add(btnPrecedente);
		
		this.btnProssimo.setBounds(676, 464, 117, 23);
		this.layeredPane.add(btnProssimo);
		

		this.lblNumeroOrdine.setBounds(10, 11, 97, 14);
		this.layeredPane.add(lblNumeroOrdine);
		
		this.lblDataOrdine.setBounds(10, 54, 97, 14);
		this.layeredPane.add(lblDataOrdine);
		
		this.lblFornitore.setBounds(226, 14, 100, 14);
		this.layeredPane.add(lblFornitore);
		
		this.textNumeroOrdine = new JTextField();
		this.textNumeroOrdine.setBounds(117, 11, 86, 20);
		this.layeredPane.add(textNumeroOrdine);
		this.textNumeroOrdine.setColumns(10);
		
		this.textDataOrdine = new JTextField();
		this.textDataOrdine.setBounds(117, 54, 86, 20);
		this.layeredPane.add(textDataOrdine);
		this.textDataOrdine.setColumns(10);
		
		this.textFornitore = new JTextField();
		this.textFornitore.setBounds(336, 8, 86, 20);
		this.layeredPane.add(textFornitore);
		this.textFornitore.setColumns(10);
		
		this.comboBoxFornitore.setBounds(336, 51, 86, 20);
		this.comboBoxFornitore.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				setForCli();
			}
		});
		this.layeredPane.add(comboBoxFornitore);
		

		this.lblConsegna.setBounds(432, 11, 86, 14);
		this.layeredPane.add(lblConsegna);
		

		this.lblAl.setBounds(432, 54, 86, 14);
		this.layeredPane.add(lblAl);
		
		this.textDal = new JTextField();
		this.textDal.setBounds(528, 8, 86, 20);
		this.layeredPane.add(textDal);
		this.textDal.setColumns(10);
		
		this.textAl = new JTextField();
		this.textAl.setBounds(528, 51, 86, 20);
		this.layeredPane.add(textAl);
		this.textAl.setColumns(10);
		

		this.tabbedPane.setBounds(10, 79, 797, 374);
		this.layeredPane.add(tabbedPane);
		

		this.tabbedPane.addTab("Testata", null, panelTestata, null);
		this.panelTestata.setLayout(null);
		

		this.lblTipoOrdine.setBounds(10, 11, 86, 14);
		this.panelTestata.add(lblTipoOrdine);
		
		this.textTipoOrdine = new JTextField();
		this.textTipoOrdine.setBounds(106, 11, 62, 20);
		this.panelTestata.add(textTipoOrdine);
		this.textTipoOrdine.setColumns(10);
		

		this.comboBoxTipoOrdine.setBounds(178, 11, 109, 20);
		this.comboBoxTipoOrdine.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				setTipoOrd();
			}
		});
		this.panelTestata.add(comboBoxTipoOrdine);
		
		this.lblNegozio.setBounds(10, 59, 86, 14);
		this.panelTestata.add(lblNegozio);
		
		this.textNegozio = new JTextField();
		this.textNegozio.setBounds(106, 59, 181, 20);
		this.panelTestata.add(textNegozio);
		this.textNegozio.setColumns(10);
		
		this.lblPagamento.setBounds(10, 108, 86, 14);
		this.panelTestata.add(lblPagamento);
		
		this.textFieldPagamento = new JTextField();
		this.textFieldPagamento.setBounds(106, 108, 181, 20);
		this.panelTestata.add(textFieldPagamento);
		this.textFieldPagamento.setColumns(10);
		
		this.lblBanca.setBounds(10, 158, 86, 14);
		this.panelTestata.add(lblBanca);
		
		this.textFieldBanca = new JTextField();
		this.textFieldBanca.setBounds(106, 158, 181, 20);
		this.panelTestata.add(textFieldBanca);
		this.textFieldBanca.setColumns(10);
		
		this.lblNote.setBounds(10, 214, 86, 14);
		this.panelTestata.add(lblNote);
		
		this.textFieldNote = new JTextField();
		this.textFieldNote.setBounds(106, 214, 181, 124);
		this.panelTestata.add(textFieldNote);
		this.textFieldNote.setColumns(10);
		
		this.lblSconto.setBounds(315, 11, 72, 14);
		this.panelTestata.add(lblSconto);
		
		this.textFieldSconto = new JTextField();
		this.textFieldSconto.setBounds(387, 8, 86, 20);
		this.panelTestata.add(textFieldSconto);
		this.textFieldSconto.setColumns(10);
		
		this.label.setBounds(483, 11, 21, 14);
		this.panelTestata.add(label);
		
		this.lblProdotto.setBounds(315, 62, 72, 14);
		this.panelTestata.add(lblProdotto);
		
		this.textFieldProdotto = new JTextField();
		this.textFieldProdotto.setBounds(387, 59, 141, 20);
		this.panelTestata.add(textFieldProdotto);
		this.textFieldProdotto.setColumns(10);
		
		this.lblNewLabel.setBounds(538, 62, 26, 14);
		this.panelTestata.add(lblNewLabel);
		
		this.textFieldPrezzo = new JTextField();
		this.textFieldPrezzo.setBounds(574, 59, 86, 20);
		this.panelTestata.add(textFieldPrezzo);
		this.textFieldPrezzo.setColumns(10);
		
		this.label_1.setBounds(662, 62, 21, 14);
		this.panelTestata.add(label_1);
		

		this.bAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				list.add("Prodotto: "+textFieldProdotto.getText()+" Quantita': "+ textFieldQuantita.getText()+" Prezzo: "+textFieldPrezzo.getText());
				JTextArea textArea = new JTextArea();
				textArea.setBounds(383, 153, 399, 185);
				panelTestata.add(textArea);
				textArea.setText(list.toString());
				}
		});
		this.bAggiungi.setBounds(538, 104, 97, 23);
		this.panelTestata.add(bAggiungi);
		
		
		this.lblIva.setBounds(538, 11, 26, 14);
		this.panelTestata.add(lblIva);
		
		this.textFieldIVA = new JTextField();
		this.textFieldIVA.setBounds(574, 8, 86, 20);
		this.panelTestata.add(textFieldIVA);
		this.textFieldIVA.setColumns(10);
		

		this.label_2.setBounds(670, 11, 46, 14);
		this.panelTestata.add(label_2);
		
		this.lblQuantita.setBounds(315, 108, 46, 14);
		this.panelTestata.add(lblQuantita);
		
		this.textFieldQuantita = new JTextField();
		this.textFieldQuantita.setBounds(387, 105, 86, 20);
		this.panelTestata.add(textFieldQuantita);
		this.textFieldQuantita.setColumns(10);
		
		this.textArea.setBounds(315, 153, 467, 193);
		this.panelTestata.add(textArea);
		

		this.tabbedPane.addTab("Fattura", null, panelFattura, null);
		this.panelFattura.setLayout(null);
		
		this.lblCodFornitore.setBounds(226, 57, 100, 14);
		this.layeredPane.add(lblCodFornitore);
		this.setLayout(new BorderLayout(0, 0));
		this.add(toolBar, BorderLayout.NORTH);
		this.add(layeredPane);

	}
}
