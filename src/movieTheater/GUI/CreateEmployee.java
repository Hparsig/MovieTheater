package movieTheater.GUI;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import movieTheater.main.EmployeeController;
import movieTheater.main.City;
import movieTheater.SQL.SQLLoadPostCode;
import movieTheater.SQL.SQLLoadTitel;
import movieTheater.main.Titel;



public class CreateEmployee extends JFrame {
	private EmployeeController emploeyyController;
	private ArrayList<City> postcodeArray;
	private ArrayList<Titel> titelArray;
	private SQLLoadPostCode loadPostcode;
	private SQLLoadTitel loadTitel;
	private ComboBoxPostcode city;
	private ComboBoxTitels titel;
	private JPanel contentPane;
	private JTextField tlf;
	private JTextField vej;
	private JTextField efternavn;
	private JTextField fornavn;
	private JTextField nr;
	private JTextField brugernavn;
	private JTextField password;
	private JComboBox citys;
	private JComboBox titels;
	
	private String name;
	private String lastname;
	private int phone;
	private String pWord;
	private int titelID;
	private String road;
	private String houseNr;
	private int postcode;
	private String cityChoosen;
	private String username;
	

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
		loadTitel = new SQLLoadTitel();
		loadPostcode = new SQLLoadPostCode();
		city = new ComboBoxPostcode(loadPostcode);
		titel  = new ComboBoxTitels(loadTitel);
		
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
				try
				{
					name = fornavn.getText();
					lastname = efternavn.getText();
				
					String phoneN = tlf.getText();
					phone = Integer.parseInt(phoneN);
								
					road = vej.getText();
					houseNr = nr.getText();

					username = brugernavn.getText();
					pWord = password.getText();
				
					int titelChoose = titels.getSelectedIndex();
					titelArray = loadTitel.getTitels();
					titelID = titelArray.get(titelChoose).getTitelID();
				
					int cityChoose = citys.getSelectedIndex();
					postcodeArray = loadPostcode.getCitys();
					postcode = postcodeArray.get(cityChoose).getPostcode();
					cityChoosen = postcodeArray.get(cityChoose).getCity();
				
					emploeyyController.createEmployee(name, lastname, phone, pWord, titelID, road, houseNr, postcode, cityChoosen, username);
				}catch(Exception e)
				{
					JOptionPane.showMessageDialog(new JFrame(), "Alle felterne skal udfyldes korrekt");  
				}
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
		
		
		citys = city.set();
		citys.setBounds(90, 106, 117, 22);
		panel.add(citys);
		
		titels = titel.set(); 
		titels.setBounds(91, 200, 116, 22);
		panel.add(titels);

	
	}
}
