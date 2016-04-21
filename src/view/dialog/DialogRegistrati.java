package view.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class DialogRegistrati extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9034440890511608840L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField = new JTextField(10);
	private JTextField textField_2 = new JTextField(10);
	private JTextField textField_3 = new JTextField(10);
	private JTextField textField_4 = new JTextField(10);
	private JPasswordField passwordField = new JPasswordField();
	private JLabel lblUserName = new JLabel("Username");
	private JLabel lblPassword = new JLabel("Password");
	private JLabel lblEmail = new JLabel("Email");
	private JLabel lblIndirizzo = new JLabel("Indirizzo");
	private JLabel lblNegozio = new JLabel("Negozio");
	private GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
	private JPanel buttonPane = new JPanel();
	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");

	/**
	 * Create the dialog.
	 */
	public DialogRegistrati() {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 286, 318);
		this.setTitle("Registrazione");
		this.getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);
	
		this.gl_contentPanel.setHorizontalGroup(
				this.gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblIndirizzo, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
						.addComponent(lblEmail, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(lblPassword, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblUserName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
						.addComponent(lblNegozio, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(passwordField)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
							.addComponent(textField, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
							.addComponent(textField_2)
							.addComponent(textField_3)
							.addComponent(textField_4)))
					.addGap(263))
		);
		this.gl_contentPanel.setVerticalGroup(
				this.gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUserName, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIndirizzo, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNegozio, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
