package view.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mattiaberretti.utenti.IUtente;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class DialogRegistrati extends JDialog implements DialogInterface{
	//DA MODIFICARE IN JFRAME FORSE aggiungere metodo per confrontare la password
	/**
	 * 
	 */
	private static final long serialVersionUID = 9034440890511608840L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldUsername = new JTextField(10);
	private JTextField textFieldEmail = new JTextField(10);
	private JTextField textFieldIndirizzo = new JTextField(10);
	private JTextField textFieldNegozio = new JTextField(10);
	private JPasswordField passwordField = new JPasswordField();
	private JLabel lblUserName = new JLabel("Username(*)");
	private JLabel lblPassword = new JLabel("Password(*)");
	private JLabel lblEmail = new JLabel("Email(*)");
	private JLabel lblIndirizzo = new JLabel("Indirizzo");
	private JLabel lblNegozio = new JLabel("Negozio");
	private JPasswordField passwordFieldConfirm = new JPasswordField(10);
	private GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
	private JPanel buttonPane = new JPanel();
	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");
	private Map<String,String> map = new HashMap<>();
	private JLabel lblConferma = new JLabel("Conferma Password(*)");

	private registrazioneDelegate delegate;
	
	/**
	 * Create the dialog.
	 */
	public DialogRegistrati(registrazioneDelegate delegate) {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 310, 375);
		this.setTitle("Registrazione");
		this.getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);
		this.delegate = delegate;
	
		this.gl_contentPanel.setHorizontalGroup(
				this.gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblIndirizzo, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
						.addComponent(lblEmail, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(lblPassword, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblConferma, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblUserName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
						.addComponent(lblNegozio, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(passwordField)
						.addComponent(passwordFieldConfirm)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
							.addComponent(textFieldUsername, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
							.addComponent(textFieldEmail)
							.addComponent(textFieldIndirizzo)
							.addComponent(textFieldNegozio)))
					.addGap(263))
		);
		this.gl_contentPanel.setVerticalGroup(
				this.gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUserName, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblConferma, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addComponent(passwordFieldConfirm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIndirizzo, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldIndirizzo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNegozio, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldNegozio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(48, Short.MAX_VALUE))
		);
		this.contentPanel.setLayout(gl_contentPanel);
		
		{
			this.buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			this.getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				this.okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						Map<String, String> valori = getDataString();
						if(valori != null){
							try {
								Optional<IUtente> nuovo = IUtente.registrati("utente", "utente", valori.get("Username"), valori.get("Password"), valori.get("Email"), valori.get("Negozio"));
								if(nuovo.isPresent()){
									DialogRegistrati.this.delegate.registrazioneCompletata(nuovo.get(), DialogRegistrati.this);
								}
								else{
									//TODO: comunicare messaggio errore registrazine
								}
							} catch (ClassNotFoundException | SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
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
	
	private void creaDialogCampoObbligatorio(){
		DialogCampoObbligatorio errore = new DialogCampoObbligatorio();
		errore.setVisible(true);
	}
	private boolean verificaCampiObbligatori(){
		if(textFieldUsername.getText().equals(null)){
			return false;
		} else { 
			if(textFieldEmail.getText().equals(null)){
				return false;
			} else {
				if(passwordField.equals(null)){
					return false;
				} else {
					if(passwordFieldConfirm.equals(null)){
						return false;
					} else {
						return true;						
					}
				}
			}
		}
	}

	@Override
	public Map<String, String> getDataString() {
		if (this.verificaCampiObbligatori() == true){
			this.map.put("Username",textFieldUsername.getText());
			this.map.put("Email", textFieldEmail.getText());
			this.map.put("Indirizzo", textFieldIndirizzo.getText());
			this.map.put("Negozio", textFieldNegozio.getText());
			
			this.map.put("Password", new String(passwordField.getPassword()));
			return map;
		} else {
			this.creaDialogCampoObbligatorio();
			return null;
		}
	}
}
