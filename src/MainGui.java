
import java.awt.Dimension;

import javax.swing.*;
import java.awt.Window.Type;
import java.awt.Panel;
import java.awt.Font;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainGui extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField userField;
	private JPasswordField passwordField;

	public MainGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("GUI");
		getContentPane().setLayout(null);
		
		JLabel mainLabel = new JLabel("Welcome to the security Panel");
		mainLabel.setFont(new Font("Tahoma", Font.BOLD, 34));
		mainLabel.setBounds(44, 11, 553, 70);
		getContentPane().add(mainLabel);
		
		JLabel userLabel = new JLabel("User Name");
		userLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		userLabel.setBounds(117, 116, 99, 39);
		getContentPane().add(userLabel);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setToolTipText("Enter the password");
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordLabel.setBounds(117, 222, 99, 39);
		getContentPane().add(passwordLabel);
		
		userField = new JTextField();
		userField.setBounds(279, 123, 221, 28);
		getContentPane().add(userField);
		userField.setColumns(10);
		
		Button signInButton = new Button("Sign In");
		signInButton.setFont(new Font("Dialog", Font.PLAIN, 15));
		signInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (userField.getText().equalsIgnoreCase("Forensic Expert") && passwordField.getText().equalsIgnoreCase("forensic123")) {
					ClueGui clueGui = new ClueGui();
					clueGui.setVisible(true);
					clueGui.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					clueGui.setSize(900,700);
				}
				else if (userField.getText().equalsIgnoreCase("Tech Expert") && passwordField.getText().equalsIgnoreCase("tech123"))
				{
					CrossReferenceGui crossReferenceGui = new CrossReferenceGui();
					crossReferenceGui.setVisible(true);
					crossReferenceGui.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					crossReferenceGui.setSize(900,700);
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Invalid Username/Password","Inane error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		signInButton.setBounds(250, 305, 133, 49);
		getContentPane().add(signInButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(273, 222, 227, 28);
		getContentPane().add(passwordField);


	}
}
