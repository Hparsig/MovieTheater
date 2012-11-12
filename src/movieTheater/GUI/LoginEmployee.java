package movieTheater.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import movieTheater.main.LoginController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class LoginEmployee extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private String username;
	private String password;
	private LoginController loginController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginEmployee frame = new LoginEmployee();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginEmployee() {
		loginController = new LoginController();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblBrugernavn = new JLabel("Brugernavn");
		lblBrugernavn.setBounds(37, 32, 107, 16);
		panel.add(lblBrugernavn);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(37, 61, 107, 16);
		panel.add(lblPassword);
		
		usernameField = new JTextField();
		usernameField.setBounds(110, 29, 116, 22);
		panel.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(110, 61, 116, 22);
		panel.add(passwordField);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				try
//				{
					username = usernameField.getText();
					password = passwordField.getText();
					//loginController.employeeLogin(password, username);
					
//				}
//				catch(IndexOutOfBoundsException outOf){
//					JOptionPane.showMessageDialog(new JFrame(), "Brugeren findes ikke");
//				}
//				catch(SQLException sql)
//				{
//					JOptionPane.showMessageDialog(new JFrame(), "Fejl i load til databasen, prøv venligst igen");  
//				}	
//				catch(Exception t)
//				{
//					JOptionPane.showMessageDialog(new JFrame(), "Alle felterne skal udfyldes korrekt");  
//				}		
				
			}
		});
		btnNewButton.setBounds(313, 209, 97, 25);
		panel.add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(204, 209, 97, 25);
		panel.add(btnCancel);
	}
	
	public String getUsername()
	{
		return username;
	}
	public String getPassword()
	{
		return password;
	}
	
}
