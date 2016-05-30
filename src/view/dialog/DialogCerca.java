package view.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.ClientiGUI;
import view.FattureGUI;
import view.FornitoriGUI;
import view.RiunioniGUI;
import view.ScontriniGUI;
import view.interfaces.ObserverInterface;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
/**
 * Class that define DialogCerca
 * @author Alessandro
 *
 */
public class DialogCerca extends JDialog implements DialogInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7712444928294496614L;
	private final JPanel contentPanel = new JPanel();
	private JPanel buttonPane = new JPanel();
	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");
	private JTextField txtNumero;
	private JTextField txtNome;
	private JLabel lblNumero = new JLabel("N.");
	private JLabel lblNome = new JLabel("Nome");
	private GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
	


	/**
	 * Create the DialogCerca.
	 * @param o
	 * 			Object ObserverInterface
	 */
	public DialogCerca(final ObserverInterface o,final Optional<JFrame> frame) {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(DialogCerca.class.getResource("/javax/swing/plaf/metal/icons/ocean/computer.gif")));
		this.setBounds(100, 100, 443, 252);
		this.setTitle("Cerca");
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);
		

		
		this.txtNumero = new JTextField();
		this.txtNumero.setColumns(10);
		
		this.txtNome = new JTextField();
		this.txtNome.setColumns(10);
		this.gl_contentPanel.setHorizontalGroup(
				this.gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblNome, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNumero, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(txtNumero)
						.addComponent(txtNome, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE))
					.addGap(55))
		);
		this.gl_contentPanel.setVerticalGroup(
				this.gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNumero)
						.addComponent(txtNumero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(53)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(48, Short.MAX_VALUE))
		);
		this.contentPanel.setLayout(this.gl_contentPanel);
		{
			this.buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			this.getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				this.okButton.setActionCommand("OK");
				this.buttonPane.add(okButton);
				this.okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if (frame.getClass().equals(FattureGUI.class)){
							//o.cercaFatture(txtNumero.getText(), txtNome.getText());
						}
						if (frame.getClass().equals(ClientiGUI.class)){
							//o.cercaClienti(txtNome.getText());
						}
						if (frame.getClass().equals(FornitoriGUI.class)){
							//o.cercaFornitori(txtNome.getText());
						}
						if (frame.getClass().equals(RiunioniGUI.class)){
							//o.cercaRiunioni(txtNumero.getText(), txtNome.getText());
						}
						if (frame.getClass().equals(ScontriniGUI.class)){
							//o.cercaScontrini(txtNumero.getText(), txtNome.getText());
						}
						o.abilitaFrame(true);
						dispose();
					}
				});
			}
			{
				this.cancelButton.setActionCommand("Cancel");
				this.buttonPane.add(cancelButton);
				this.cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						o.abilitaFrame(true);
						dispose();
						
					}
				});
			}
		}
	}

	
	@Override
	public Map<String, String> getDataString(final ObserverInterface o) {
		// TODO Auto-generated method stub
		return null;
	}
}
