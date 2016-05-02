package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLayeredPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.mattiaberretti.utenti.IUtente;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.FlowLayout;
import view.dialog.DialogRegistrati;
import view.dialog.DialogWrongPass;
import view.dialog.DialogWrongUser;
import view.dialog.registrazioneDelegate;

public class Login {

	private JFrame frmInfoManager;
	private JTextField tUser = new JTextField(10);
	private JTextField tPass = new JPasswordField(10);
	private JLayeredPane layeredPane = new JLayeredPane();
	private JLabel lUser = new JLabel("Username  :");
	private JLabel lPass = new JLabel("Password  :");
	private JLayeredPane layeredPane_1 = new JLayeredPane();
	private GroupLayout gl_layeredPane = new GroupLayout(layeredPane);
	private GroupLayout gl_layeredPane_1 = new GroupLayout(layeredPane_1);
	private JPanel buttonPane = new JPanel();
	private JButton bAccendi = new JButton("Accedi");
	private JButton bRegistrati = new JButton("Registrati");
	private DialogWrongPass dErrorPass = new DialogWrongPass();
	private DialogWrongUser dErrorUser = new DialogWrongUser();
	private DialogRegistrati dRegistrati = new DialogRegistrati( new registrazioneDelegate() {
		
		@Override
		public void registrazioneCompletata(IUtente utente, DialogRegistrati view) {
			view.dispose();
			new Menu().setVisible(true);
			setStatus(false);
		}
	});

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmInfoManager.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void setStatus(boolean a){
		frmInfoManager.setVisible(a);
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}
	
	public int checkLogin(){
		try {
			Optional<IUtente> utente = IUtente.accedi(tUser.getText(), tPass.getText());
			if(utente.isPresent()){
				return 1;
			}
			else{
				return 0;
			}
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.frmInfoManager = new JFrame();	
		this.frmInfoManager.setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		this.frmInfoManager.setTitle("Login");
		this.frmInfoManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frmInfoManager.setBounds(100, 100, 462, 315);
		
		this.tUser.setEditable(true);
		this.tPass.setEditable(true);
		this.gl_layeredPane.setHorizontalGroup(
				this.gl_layeredPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_layeredPane.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_layeredPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lUser, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
						.addComponent(lPass, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(layeredPane_1, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
					.addContainerGap())
		);
		this.gl_layeredPane.setVerticalGroup(
				this.gl_layeredPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_layeredPane.createSequentialGroup()
					.addGroup(gl_layeredPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_layeredPane.createSequentialGroup()
							.addGap(45)
							.addComponent(lUser, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addGap(57)
							.addComponent(lPass, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_layeredPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(layeredPane_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(86, Short.MAX_VALUE))
		);

		this.gl_layeredPane_1.setHorizontalGroup(
				this.gl_layeredPane_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_layeredPane_1.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_layeredPane_1.createParallelGroup(Alignment.LEADING)
						.addComponent(tUser, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
						.addComponent(tPass, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
					.addGap(16))
		);
		this.gl_layeredPane_1.setVerticalGroup(
				this.gl_layeredPane_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_layeredPane_1.createSequentialGroup()
					.addGap(36)
					.addComponent(tUser, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(52)
					.addComponent(tPass, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(10, Short.MAX_VALUE))
		);
		this.layeredPane_1.setLayout(gl_layeredPane_1);
		this.layeredPane.setLayout(gl_layeredPane);
		this.frmInfoManager.getContentPane().setLayout(new BorderLayout(0, 0));
		this.frmInfoManager.getContentPane().add(layeredPane, BorderLayout.NORTH);
		

		this.frmInfoManager.getContentPane().add(buttonPane, BorderLayout.SOUTH);
		this.buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		this.bAccendi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				final int a = checkLogin();
				if(a == 1){
					Menu jFMenu = new Menu();
					jFMenu.setVisible(true);
					setStatus(false);
				} else {
					if(a == 0){
						dErrorPass.setVisible(true);
					} else {
						dErrorUser.setVisible(true);
					}
				}
			}
		});
		this.buttonPane.add(bAccendi);
		
		this.bRegistrati.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dRegistrati.setVisible(true);
			}
		});
		this.buttonPane.add(bRegistrati);
	}
}
