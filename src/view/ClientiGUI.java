package view;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;

import view.interfaces.ObserverInterface;
import view.toolbar.MyToolbar;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;



public class ClientiGUI extends InitializeFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7113641769924370743L;
	private static final String TITOLO = "Clienti";
	private static final LayoutManager LAYOUT = new BorderLayout();
	private static final Dimension DIMFRAME = new Dimension(480,290);
	private MyToolbar toolbar;
	private JPanel panelTool = new JPanel();
	private JPanel panelText = new JPanel();
	private JTextField txtNome;
	private JTextField txtCognome;
	private JTextField txtEmail;
	private JTextField txtTelefono;
	private GroupLayout gl_panelText = new GroupLayout(panelText);
	private JLabel lblNome = new JLabel("Nome");
	private JLabel lblCognome = new JLabel("Cognome");
	private JLabel lblEmail = new JLabel("Email");
	private JLabel lblTelefono = new JLabel("Telefono");
	
	
	

	/**
	 * Create the frame.
	 */
	public ClientiGUI(final ObserverInterface o) {
		super(TITOLO, LAYOUT, DIMFRAME);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(ClientiGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		this.getMainPanel().setLayout(new BorderLayout(0, 0));
		this.getMainPanel().add(panelTool, BorderLayout.CENTER);
		this.toolbar = new MyToolbar(o);
		this.panelTool.setLayout(new BorderLayout(0,0));
		this.panelTool.add(toolbar, BorderLayout.NORTH);
		this.panelTool.add(panelText, BorderLayout.CENTER);
		this.txtNome = new JTextField();
		this.txtNome.setColumns(10);
		
		this.txtCognome = new JTextField();
		this.txtCognome.setColumns(10);
		
		this.txtEmail = new JTextField();
		this.txtEmail.setText("");
		this.txtEmail.setColumns(10);
		
		this.txtTelefono = new JTextField();
		this.txtTelefono.setColumns(10);

		this.gl_panelText.setHorizontalGroup(
			this.gl_panelText.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelText.createSequentialGroup()
					.addGap(45)
					.addGroup(gl_panelText.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelText.createSequentialGroup()
							.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(Alignment.TRAILING, gl_panelText.createSequentialGroup()
							.addGroup(gl_panelText.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblTelefono, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
								.addComponent(lblEmail, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
								.addComponent(lblCognome, GroupLayout.PREFERRED_SIZE, 69, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_panelText.createParallelGroup(Alignment.LEADING)
						.addComponent(txtEmail, GroupLayout.DEFAULT_SIZE, 6, Short.MAX_VALUE)
						.addComponent(txtCognome, GroupLayout.DEFAULT_SIZE, 6, Short.MAX_VALUE)
						.addComponent(txtNome, GroupLayout.DEFAULT_SIZE, 6, Short.MAX_VALUE)
						.addComponent(txtTelefono, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE))
					.addGap(76))
		);
		this.gl_panelText.setVerticalGroup(
			this.gl_panelText.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelText.createSequentialGroup()
					.addGap(52)
					.addGroup(gl_panelText.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNome))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelText.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtCognome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCognome))
					.addGap(13)
					.addGroup(gl_panelText.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(gl_panelText.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTelefono)
						.addComponent(txtTelefono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(102, Short.MAX_VALUE))
		);
		this.panelText.setLayout(gl_panelText);
		
		
		
	}
	
	public Map<String,String> getTextfield(){
		Map<String,String> mappa = new HashMap<>();
		mappa.put("Nome", txtNome.getText());
		mappa.put("Cognome", txtCognome.getText());
		mappa.put("Email", txtEmail.getText());
		mappa.put("Telefono", txtTelefono.getText());
		return mappa;
	}
}
