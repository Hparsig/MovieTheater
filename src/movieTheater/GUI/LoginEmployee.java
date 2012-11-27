package movieTheater.GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.concurrent.CountDownLatch;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import movieTheater.main.LoginController;
import java.awt.event.KeyAdapter;

@SuppressWarnings("serial")
public class LoginEmployee extends JFrame 
{

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private String username;
	private String password;
	private LoginController loginController;
	private boolean isExitChosen;
	public  CountDownLatch latch = new CountDownLatch(1); //venter på brugerens input. 

	/**
	 * Create the frame.
	 */
	public LoginEmployee() 
	{
		isExitChosen = false;
		loginController = new LoginController();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 357, 176);
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
		passwordField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					close(false);
				}
			}
		});

		passwordField.setBounds(110, 61, 116, 22);
		panel.add(passwordField);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				close(false);
			}
		});
		btnLogin.setBounds(227, 94, 97, 25);
		panel.add(btnLogin);

		JButton btnCancel = new JButton("afslut");
		btnCancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				close(true);
			}
		});
		btnCancel.setBounds(120, 94, 97, 25);
		panel.add(btnCancel);
	}
	private void close(boolean isExitChosen)
	{
		this.isExitChosen = isExitChosen;
		username = usernameField.getText();
		password = passwordField.getText();
		latch.countDown();
	}

	public String getUsername()
	{
		return username;
	}
	public String getPassword()
	{
		return password;
	}
	public void setLatch()
	{
		latch = new CountDownLatch(1);
	}
	public boolean getIsExitChosen()
	{
		return isExitChosen;
	}
	public void showDiaglog(String text)
	{
		JOptionPane.showMessageDialog(new JFrame(), text);
	}
}
