package movieTheater.GUI;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.concurrent.CountDownLatch;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import movieTheater.Persons.City;
import movieTheater.Persons.Employee;
import movieTheater.Persons.Title;
import movieTheater.main.EmployeeController;

@SuppressWarnings("serial")
public class CreateEmployee extends JFrame {

	private JPanel contentPane;
	private JTextField ftfPhone;
	private JTextField txtRoad;
	private JTextField txtLName;
	private JTextField txtfName;
	private JTextField txtHouseNo;
	private JTextField txtUserName;
	private JTextField txtPW;
	private JComboBox<Object> cbxCitys;
	private JComboBox<Object> cbxTitles;
	JPanel panel;
	public final CountDownLatch latch = new CountDownLatch(1); //venter på brugerens input. 
	private int titleID;
	private Employee person;
	private boolean areChangesMade;
	private MaskFormatter maskFormatPhone;

	/**
	 * @author Jesper
	 * Create the frame createEmployee.
	 */
	public CreateEmployee(final Employee person, final boolean isAdmin) 
	{
		if (person.getEmployeeNo() == 0)
		{
			setTitle("Opret medarbejder");
		}
		else
		{
			setTitle("Rediger medarbejder");
		}
		this.person = person;
		areChangesMade = false;

		try
		{
			maskFormatPhone = new MaskFormatter("########");
//			maskFormatLength = new MaskFormatter("###");
		} 
		catch (ParseException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("Gem");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					person.setfName(txtfName.getText());
					person.setlName(txtLName.getText());
					person.setPhone(Integer.parseInt(ftfPhone.getText()));
					person.setRoad(txtRoad.getText());
					person.setHouseNo(txtHouseNo.getText());
					person.setUserName(txtUserName.getText());
					person.setPW(txtPW.getText());

					if(isAdmin)
					{
						titleID = (((Title)cbxTitles.getSelectedItem()).getTitelID());
					}
					person.setPostCode(((City)cbxCitys.getSelectedItem()).getPostcode());
					areChangesMade = true;
					latch.countDown();
				}
				catch(Exception e)
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

		ftfPhone = new JFormattedTextField(maskFormatPhone);
		ftfPhone.setColumns(10);
		ftfPhone.setBounds(91, 77, 116, 22);
		if(person.getPhone() != 0)
		{
			ftfPhone.setText(person.getPhone()+"");
		}
		panel.add(ftfPhone);

		txtRoad = new JTextField();
		txtRoad.setColumns(10);
		txtRoad.setBounds(91, 142, 116, 22);
		txtRoad.setText(person.getRoad());
		panel.add(txtRoad);

		txtLName = new JTextField();
		txtLName.setColumns(10);
		txtLName.setBounds(91, 42, 116, 22);
		txtLName.setText(person.getlName());
		panel.add(txtLName);

		txtfName = new JTextField();
		txtfName.setColumns(10);
		txtfName.setBounds(91, 7, 116, 22);
		txtfName.setText(person.getfName());
		panel.add(txtfName);

		txtHouseNo = new JTextField();
		txtHouseNo.setColumns(10);
		txtHouseNo.setBounds(91, 171, 116, 22);
		txtHouseNo.setText(person.getHouseNo()+"");
		panel.add(txtHouseNo);

		txtUserName = new JTextField();
		txtUserName.setColumns(10);
		txtUserName.setBounds(313, 7, 97, 22);
		txtUserName.setText(person.getUserName());
		panel.add(txtUserName);

		txtPW = new JTextField();
		txtPW.setColumns(10);
		txtPW.setBounds(313, 42, 97, 22);
		txtPW.setText(person.getPW());
		panel.add(txtPW);

		JLabel lblRolle = new JLabel("Rolle");
		lblRolle.setBounds(12, 203, 56, 16);
		panel.add(lblRolle);

		cbxCitys = new JComboBox<Object>(EmployeeController.postcodeArray.toArray());
		cbxCitys.setBounds(90, 106, 117, 22);
		panel.add(cbxCitys);

		if (isAdmin)
		{
			cbxTitles = new JComboBox<Object>(EmployeeController.titleArray.toArray());
			cbxTitles.setBounds(91, 200, 116, 22);
			panel.add(cbxTitles);
		}
		else
		{
			JLabel lblSalesPerson = new JLabel("Kassemedarbejder");
			lblSalesPerson.setBounds(91, 200, 56, 16);
			panel.add(lblSalesPerson);
			titleID = 2;
		}

		JButton btnAnnuller = new JButton("Annuller");
		btnAnnuller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				latch.countDown();
			}
		});
		btnAnnuller.setBounds(297, 173, 113, 25);
		panel.add(btnAnnuller);
	}

	public Employee getEmployee()
	{
		return person;
	}
	public boolean areChangesMade()
	{
		return areChangesMade;
	}
	public int getTitleID() 
	{
		return titleID;
	}
	public void setTitle(int index)
	{
		cbxTitles.setSelectedIndex(index);
	}
	public void setPostcode(int index)
	{
		cbxCitys.setSelectedIndex(index);
	}
}
