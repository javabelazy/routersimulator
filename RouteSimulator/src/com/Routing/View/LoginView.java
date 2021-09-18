package com.Routing.View;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import com.Routing.DAO.LoginDatabase;
import com.Routing.Model.LoginModel;

import java.awt.Dimension;

public class LoginView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel username = null;
	private JLabel Password = null;
	private JTextField Usertext = null;
	private JPasswordField PasswordField = null;
	private JButton SubmitButton = null;
	private JButton Clear = null;
	private JLabel loginText = null;
	/**
	 * This is the default constructor
	 */
	public LoginView() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(380, 240);
		this.setContentPane(getJContentPane());
		this.setTitle("Routing Simulator");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			loginText = new JLabel();
			loginText.setBounds(new Rectangle(85, 101, 154, 23));
			loginText.setText("invalid user");
			loginText.setForeground(Color.red);
			loginText.setVisible(false);
			Password = new JLabel();
			Password.setBounds(new Rectangle(30, 59, 105, 20));
			Password.setText("  Password :");
			username = new JLabel();
			username.setBounds(new Rectangle(28, 12, 105, 20));
			username.setText("  User name :");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(username, null);
			jContentPane.add(Password, null);
			jContentPane.add(getUsertext(), null);
			jContentPane.add(getPasswordField(), null);
			jContentPane.add(getSubmitButton(), null);
			jContentPane.add(getClear(), null);
			jContentPane.add(loginText, null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes Usertext	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getUsertext() {
		if (Usertext == null) {
			Usertext = new JTextField();
			Usertext.setBounds(new Rectangle(165, 13, 150, 27));
		}
		return Usertext;
	}

	/**
	 * This method initializes PasswordField	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getPasswordField() {
		if (PasswordField == null) {
			PasswordField = new JPasswordField();
			PasswordField.setBounds(new Rectangle(165, 59, 150, 27));
		}
		return PasswordField;
	}

	/**
	 * This method initializes SubmitButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSubmitButton() {
		if (SubmitButton == null) {
			SubmitButton = new JButton();
			SubmitButton.setBounds(new Rectangle(20, 135, 116, 30));
			SubmitButton.setText("SUBMIT");
			SubmitButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					System.out.println(Usertext.getText()+ PasswordField.getText());
					LoginModel login=new LoginModel();
					LoginDatabase logDb=new LoginDatabase();
					login.setUsername(Usertext.getText());
					login.setPassword(PasswordField.getText());
					Boolean isUser=logDb.authenticate(login);
					if(isUser)
					{System.out.println("he can login");
				 
			     	RoutingOpt routing=new RoutingOpt();
			     	routing.setVisible(true);
			     	dispose();
			  
					}
					else{
						System.out.println("Incorrect Entry");
						loginText.setVisible(true);
						Usertext.setText("");
						PasswordField.setText("");
					}
				}});
		}
		return SubmitButton;
	}

	/**
	 * This method initializes Clear	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getClear() {
		if (Clear == null) {
			Clear = new JButton();
			Clear.setBounds(new Rectangle(208, 136, 116, 30));
			Clear.setText("CLEAR");
			Clear.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Usertext.setText("");
					PasswordField.setText("");
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
			
			
		}
		return Clear;
	}
	public static void main(String[] args) {
		LoginView l = new LoginView();
		l.setVisible(true);
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
