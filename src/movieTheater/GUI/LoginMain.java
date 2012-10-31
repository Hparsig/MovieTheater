package movieTheater.GUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginMain extends JFrame {

	private JPanel contentPane;
	private JPanel panel;

	/**
	 * Create the frame.
	 */
	public LoginMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Medarbejder");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				LoginEmployee frame = new LoginEmployee();
				frame.setVisible(true);
				
			}
		});
		
		btnNewButton.setBounds(136, 37, 139, 71);
		panel.add(btnNewButton);
		
		JButton btnKunde = new JButton("Kunde");
		btnKunde.setBounds(136, 125, 139, 71);
		panel.add(btnKunde);
	}

}
