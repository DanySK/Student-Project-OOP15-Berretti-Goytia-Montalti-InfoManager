package view.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class DialogNuovaRiunione extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5102396740080574158L;
	private final JPanel contentPanel = new JPanel();
	private JPanel buttonPane = new JPanel();
	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");

	/**
	 * Create the dialog.
	 */
	public DialogNuovaRiunione() {
		this.setBounds(100, 100, 450, 300);
		this.setTitle("Nuova Riunione");
		this.getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setLayout(new FlowLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			this.buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			this.getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{

				this.okButton.setActionCommand("OK");
				this.buttonPane.add(okButton);
				this.getRootPane().setDefaultButton(okButton);
			}
			{

				this.cancelButton.setActionCommand("Cancel");
				this.buttonPane.add(cancelButton);
				this.cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						dispose();
					}
				});
			}
		}
	}

}
