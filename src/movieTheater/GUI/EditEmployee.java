package movieTheater.GUI;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import movieTheater.main.City;
import movieTheater.Persons.Employee;
import movieTheater.Persons.Manager;
import movieTheater.Persons.SalesPerson;
import movieTheater.SQL.SQLLoadPostCode;
import movieTheater.SQL.SQLLoadTitel;
import movieTheater.main.Title;



public class EditEmployee extends JFrame {
		private ArrayList<City> postcodeArray;
	private ArrayList<Title> titleArray;
	private SQLLoadPostCode loadPostcode;
	private SQLLoadTitel loadTitle;
	private ComboBoxPostcode city;
	private ComboBoxTitels title;
	private JPanel contentPane;
	private JTextField tlf;
	private JTextField vej;
	private JTextField efternavn;
	private JTextField fornavn;
	private JTextField nr;
	private JTextField brugernavn;
	private JTextField password;
	private JComboBox citys;
	private JComboBox titles;
	public final CountDownLatch latch = new CountDownLatch(1); //venter på brugerens input. 
	
	private String name;
	private String lastname;
	private int phone;
	private String pWord;
	private int titleID;
	private String road;
	private String houseNr;
	private int postcode;
	private String cityChoosen;
	private String username;
	private Employee employee;


	
	/**
	 * @author Jesper
	 * Create the frame createEmployee.
	 */
	public EditEmployee(Employee empl) {
		employee = empl;
				
		loadTitle = new SQLLoadTitel();
		loadPostcode = new SQLLoadPostCode();
		city = new ComboBoxPostcode(loadPostcode);
		title  = new ComboBoxTitels(loadTitle);
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Opret");
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
				
					int titelChoose = titles.getSelectedIndex();
					titleArray = loadTitle.getTitels();
					titleID = titleArray.get(titelChoose).getTitelID();
				
					int cityChoose = citys.getSelectedIndex();
					postcodeArray = loadPostcode.getCitys();
					postcode = postcodeArray.get(cityChoose).getPostcode();
					cityChoosen = postcodeArray.get(cityChoose).getCity();
					
					if(titleID==1)
					{
						employee = new Manager(name,lastname,phone,road,houseNr,postcode,cityChoosen,username,pWord);
					}
					else
					{
						employee = new SalesPerson(name,lastname,phone,road,houseNr,postcode,cityChoosen,username,pWord);
					}
					latch.countDown();
					
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
		
		tlf = new JTextField(employee.getPhone());
		tlf.setColumns(10);
		tlf.setBounds(91, 77, 116, 22);
		panel.add(tlf);
		
		vej = new JTextField(employee.getRoad());
		vej.setColumns(10);
		vej.setBounds(91, 142, 116, 22);
		panel.add(vej);
		
		efternavn = new JTextField(employee.getlName());
		efternavn.setColumns(10);
		efternavn.setBounds(91, 42, 116, 22);
		panel.add(efternavn);
		
		fornavn = new JTextField(employee.getfName());
		fornavn.setColumns(10);
		fornavn.setBounds(91, 7, 116, 22);
		panel.add(fornavn);
		
		nr = new JTextField(employee.getHouseNo());
		nr.setColumns(10);
		nr.setBounds(91, 171, 116, 22);
		panel.add(nr);
		
		brugernavn = new JTextField(employee.getUserName());
		brugernavn.setColumns(10);
		brugernavn.setBounds(313, 7, 97, 22);
		panel.add(brugernavn);
		
		password = new JTextField(employee.getPW());
		password.setColumns(10);
		password.setBounds(313, 42, 97, 22);
		panel.add(password);
		
		JLabel lblRolle = new JLabel("Rolle");
		lblRolle.setBounds(12, 203, 56, 16);
		panel.add(lblRolle);
		
		
		citys = city.set();
		citys.setBounds(90, 106, 117, 22);
		panel.add(citys);
		
		titles = title.set(); 
		titles.setBounds(91, 200, 116, 22);
		panel.add(titles);
		
		JButton btnAnnuller = new JButton("annuller");
		btnAnnuller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				EditEmployee.this.dispose();
			}
		});
		btnAnnuller.setBounds(297, 173, 113, 25);
		panel.add(btnAnnuller);
	}
	
	public Employee getEmployee(){
		return employee;
	}
}
