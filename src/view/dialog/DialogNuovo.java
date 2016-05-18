package view.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.interfaces.ObserverInterface;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JRadioButton;


public class DialogNuovo extends JDialog implements DialogInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5569305998629847130L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome = new JTextField();
	private JTextField txtCognome = new JTextField();
	private JTextField txtTelefono = new JTextField();
	private JTextField txtIndirizzo = new JTextField();
	private JLabel lblTelefono = new JLabel("Telefono");
	private JLabel lblNome = new JLabel("Nome(*)");
	private JLabel lblCognome = new JLabel("Cognome(*)");
	private JLabel lblIndirizzo = new JLabel("Indirizzo");
	private GridBagConstraints gbc_lblIndirizzo = new GridBagConstraints();
	private GridBagConstraints gbc_lblTelefono = new GridBagConstraints();
	private GridBagConstraints gbc_lblCognome = new GridBagConstraints();
	private GridBagConstraints gbc_txtNome = new GridBagConstraints();
	private GridBagConstraints gbc_lblNome = new GridBagConstraints();
	private GridBagConstraints gbc_txtIndirizzo = new GridBagConstraints();
	private GridBagConstraints gbc_txtCognome = new GridBagConstraints();
	private GridBagConstraints gbc_txtTelefono = new GridBagConstraints();
	private GridBagConstraints gbc_rdbtnFornitore = new GridBagConstraints();
	private GridBagConstraints gbc_rdbtnCliente = new GridBagConstraints();
	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");
	private GridBagLayout gbl_contentPanel = new GridBagLayout();
	private JRadioButton rdbtnCliente = new JRadioButton("Cliente");
	private JRadioButton rdbtnFornitore = new JRadioButton("Fornitore");
	private JPanel buttonPane = new JPanel();
	private Map<String,String> map = new HashMap<>();


	public Map<String,String> getDataString(final ObserverInterface o){
		if(txtNome.getText().equals(null)){
			if(txtCognome.getText().equals(null)){
				map.put("Nome",txtNome.getText());
				map.put("Cognome", txtCognome.getText());
				map.put("Telefono", txtTelefono.getText());
				map.put("Indirizzo", txtIndirizzo.getText());
				if(rdbtnCliente.isSelected()){
					map.put("TipoDiRapporto", rdbtnCliente.getText());
				} else {
					map.put("TipoDiRapporto", rdbtnFornitore.getText());
				}
				return map;
			} else {
				DialogCampoObbligatorio errore = new DialogCampoObbligatorio(o);
				errore.setVisible(true);
				return null;
			}
		} else {
			DialogCampoObbligatorio errore = new DialogCampoObbligatorio(o);
			errore.setVisible(true);
			return null;
		}
	}
	
	/**
	 * Create the dialog.
	 */
	public DialogNuovo(final ObserverInterface o) {
		this.setTitle("Nuovo Fornitore o Cliente");
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 450, 300);
		this.getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);
		this.gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		this.gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		this.gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		this.gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		
		this.rdbtnCliente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(rdbtnCliente.isSelected()){
					rdbtnFornitore.setEnabled(false);
				} else {
					rdbtnFornitore.setEnabled(true);
				}
			}
		});
		
		this.rdbtnFornitore.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (rdbtnFornitore.isSelected()){
					rdbtnCliente.setEnabled(false);
				} else {
					rdbtnCliente.setEnabled(true);
				}
			}
		});
		this.contentPanel.setLayout(gbl_contentPanel);
		{
			this.gbc_lblNome.gridwidth = 4;
			this.gbc_lblNome.insets = new Insets(0, 0, 5, 5);
			this.gbc_lblNome.gridx = 0;
			this.gbc_lblNome.gridy = 0;
			this.contentPanel.add(lblNome, gbc_lblNome);
		}
		{
			this.gbc_txtNome.gridwidth = 2;
			this.gbc_txtNome.insets = new Insets(0, 0, 5, 0);
			this.gbc_txtNome.fill = GridBagConstraints.HORIZONTAL;
			this.gbc_txtNome.gridx = 4;
			this.gbc_txtNome.gridy = 0;
			this.contentPanel.add(txtNome, gbc_txtNome);
			this.txtNome.setColumns(10);
		}
		{
			this.gbc_lblCognome.gridwidth = 4;
			this.gbc_lblCognome.insets = new Insets(0, 0, 5, 5);
			this.gbc_lblCognome.gridx = 0;
			this.gbc_lblCognome.gridy = 2;
			this.contentPanel.add(lblCognome, gbc_lblCognome);
		}
		{
			this.gbc_txtCognome.insets = new Insets(0, 0, 5, 0);
			this.gbc_txtCognome.gridwidth = 2;
			this.gbc_txtCognome.fill = GridBagConstraints.HORIZONTAL;
			this.gbc_txtCognome.gridx = 4;
			this.gbc_txtCognome.gridy = 2;
			this.contentPanel.add(txtCognome, gbc_txtCognome);
			this.txtCognome.setColumns(10);
		}
		{
			this.gbc_lblTelefono.gridwidth = 4;
			this.gbc_lblTelefono.insets = new Insets(0, 0, 5, 5);
			this.gbc_lblTelefono.gridx = 0;
			this.gbc_lblTelefono.gridy = 4;
			this.contentPanel.add(lblTelefono, gbc_lblTelefono);
		}
		{
			this.gbc_txtTelefono.gridwidth = 2;
			this.gbc_txtTelefono.insets = new Insets(0, 0, 5, 0);
			this.gbc_txtTelefono.fill = GridBagConstraints.HORIZONTAL;
			this.gbc_txtTelefono.gridx = 4;
			this.gbc_txtTelefono.gridy = 4;
			this.contentPanel.add(txtTelefono, gbc_txtTelefono);
			this.txtTelefono.setColumns(10);
		}
		{
			this.gbc_lblIndirizzo.gridwidth = 4;
			this.gbc_lblIndirizzo.insets = new Insets(0, 0, 5, 5);
			this.gbc_lblIndirizzo.gridx = 0;
			this.gbc_lblIndirizzo.gridy = 6;
			this.contentPanel.add(lblIndirizzo, gbc_lblIndirizzo);
		}
		{
			this.gbc_txtIndirizzo.insets = new Insets(0, 0, 5, 0);
			this.gbc_txtIndirizzo.gridwidth = 2;
			this.gbc_txtIndirizzo.fill = GridBagConstraints.HORIZONTAL;
			this.gbc_txtIndirizzo.gridx = 4;
			this.gbc_txtIndirizzo.gridy = 6;
			this.contentPanel.add(txtIndirizzo, gbc_txtIndirizzo);
			this.txtIndirizzo.setColumns(10);
		}
		{
			this.gbc_rdbtnFornitore.insets = new Insets(0, 0, 0, 5);
			this.gbc_rdbtnFornitore.gridx = 4;
			this.gbc_rdbtnFornitore.gridy = 7;
			this.contentPanel.add(rdbtnFornitore, gbc_rdbtnFornitore);
		}
		{
			this.gbc_rdbtnCliente.gridx = 5;
			this.gbc_rdbtnCliente.gridy = 7;
			this.contentPanel.add(rdbtnCliente, gbc_rdbtnCliente);
		}
		{
			this.buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			this.getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				this.okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						dispose();
					}
				});
				this.buttonPane.add(okButton);
				this.getRootPane().setDefaultButton(okButton);
			}
			{
				this.cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						dispose();
					}
				});
				this.buttonPane.add(cancelButton);
			}
		}
	}

}
