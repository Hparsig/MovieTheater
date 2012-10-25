package movieTheater.GUI;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import movieTheater.main.EmployeeController;


public class CreateEmployee extends JFrame {
	private EmployeeController emploeyyController;
	private JPanel contentPane;
	private JTextField tlf;
	private JTextField vej;
	private JTextField efternavn;
	private JTextField postnr;
	private JTextField fornavn;
	private JTextField nr;
	private JTextField brugernavn;
	private JTextField password;
	private JTextField rolle;
	
	private String name;
	private String lastname;
	
	private String road;
	
	private String userName;
	private String pWord;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateEmployee frame = new CreateEmployee();
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
	public CreateEmployee() {
		emploeyyController = new EmployeeController();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Create user");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				name = fornavn.getText();
				lastname = efternavn.getText();
				String phoneN = tlf.getText();
				int phone = Integer.parseInt(phoneN);
				String rolle1 = rolle.getText();
				int title = Integer.parseInt(rolle1);
				
				String postN = postnr.getText();
				int postNr = Integer.parseInt(postN);
				
				road = vej.getText();
				String roadN = nr.getText();
				int number = Integer.parseInt(roadN);

				userName = brugernavn.getText();
				pWord = password.getText();
				
				emploeyyController.createUser(name, lastname, phone, pWord, title, road, number, postNr, userName);
				
				
			}
		});
		btnNewButton.setBounds(297, 209, 113, 25);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Fornavn");
		lblNewLabel.setBounds(12, 10, 56, 16);
		panel.add(lblNewLabel);
		
		JLabel lblTlf = new JLabel("Tlf");
		lblTlf.setBounds(12, 80, 56, 16);
		panel.add(lblTlf);
		
		JLabel lblEfternavn = new JLabel("Efternavn");
		lblEfternavn.setBounds(12, 45, 56, 16);
		panel.add(lblEfternavn);
		
		JLabel lblPostNr = new JLabel("nr.");
		lblPostNr.setBounds(12, 174, 56, 16);
		panel.add(lblPostNr);
		
		JLabel lblRolleNr = new JLabel("Vej");
		lblRolleNr.setBounds(12, 145, 56, 16);
		panel.add(lblRolleNr);
		
		JLabel lblPassword = new JLabel("post nr");
		lblPassword.setBounds(12, 109, 56, 16);
		panel.add(lblPassword);
		
		JLabel lblBrugernavn = new JLabel("Password");
		lblBrugernavn.setBounds(242, 44, 75, 16);
		panel.add(lblBrugernavn);
		
		JLabel lblVej = new JLabel("Brugernavn");
		lblVej.setBounds(242, 10, 75, 16);
		panel.add(lblVej);
		
		tlf = new JTextField();
		tlf.setColumns(10);
		tlf.setBounds(91, 77, 116, 22);
		panel.add(tlf);
		
		vej = new JTextField();
		vej.setColumns(10);
		vej.setBounds(91, 142, 116, 22);
		panel.add(vej);
		
		efternavn = new JTextField();
		efternavn.setColumns(10);
		efternavn.setBounds(91, 42, 116, 22);
		panel.add(efternavn);
		
		postnr = new JTextField();
		postnr.setColumns(10);
		postnr.setBounds(91, 109, 116, 22);
		panel.add(postnr);
		
		fornavn = new JTextField();
		fornavn.setColumns(10);
		fornavn.setBounds(91, 7, 116, 22);
		panel.add(fornavn);
		
		nr = new JTextField();
		nr.setColumns(10);
		nr.setBounds(91, 171, 116, 22);
		panel.add(nr);
		
		brugernavn = new JTextField();
		brugernavn.setColumns(10);
		brugernavn.setBounds(313, 7, 97, 22);
		panel.add(brugernavn);
		
		password = new JTextField();
		password.setColumns(10);
		password.setBounds(313, 42, 97, 22);
		panel.add(password);
		
		JLabel lblRolle = new JLabel("Rolle");
		lblRolle.setBounds(12, 203, 56, 16);
		panel.add(lblRolle);
		
		rolle = new JTextField();
		rolle.setColumns(10);
		rolle.setBounds(91, 200, 116, 22);
		panel.add(rolle);

	
	}
}
