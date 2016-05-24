package view.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.interfaces.ObserverInterface;
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


	/**
	 * Create the DialogCerca.
	 * @param o
	 * 			Object ObserverInterface
	 */
	public DialogCerca(final ObserverInterface o) {
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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
				this.okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						o.abilitaFrame(true);
						dispose();
					}
				});
			}
			{
				this.cancelButton.setActionCommand("Cancel");
				this.buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					
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
