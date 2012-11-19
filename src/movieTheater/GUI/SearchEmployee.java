package movieTheater.GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class SearchEmployee extends JFrame {

	private JPanel contentPane;
	private List employeesList;
	private JTextField textFornavn;
	private JTextField textEfternavn;
	private JTextField textBrugernavn;
	private JTextField textMedarbejderNr;
	private int choosen;
	private String fName;
	private String lName;
	private String userName;
	private String empNo;
	public final CountDownLatch latchSearch = new CountDownLatch(1); //venter på brugerens input. 
	public final CountDownLatch latchChoose = new CountDownLatch(1); //venter på brugerens input. 
	
	/**
	 * @author Jesper and Henrik
	 * Create the frame.
	 */
	public SearchEmployee()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 522, 359);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		employeesList = new List();
		employeesList.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				choosen = employeesList.getSelectedIndex();
				latchChoose.countDown();
			}
		});
		employeesList.setBounds(275, 13, 207, 280);
		panel.add(employeesList);
		
		textFornavn = new JTextField();
		textFornavn.setBounds(153, 85, 116, 22);
		panel.add(textFornavn);
		textFornavn.setColumns(10);
		
		textEfternavn = new JTextField();
		textEfternavn.setColumns(10);
		textEfternavn.setBounds(153, 120, 116, 22);
		panel.add(textEfternavn);
		
		textBrugernavn = new JTextField();
		textBrugernavn.setColumns(10);
		textBrugernavn.setBounds(153, 155, 116, 22);
		panel.add(textBrugernavn);
		
		
		textMedarbejderNr = new JTextField();
		textMedarbejderNr.setColumns(10);
		textMedarbejderNr.setBounds(153, 190, 116, 22);
		panel.add(textMedarbejderNr);
		
		JLabel lblFornavn = new JLabel("Fornavn");
		lblFornavn.setBounds(12, 85, 56, 16);
		panel.add(lblFornavn);
		
		JLabel lblEfternavn = new JLabel("Efternavn");
		lblEfternavn.setBounds(12, 120, 56, 16);
		panel.add(lblEfternavn);
		
		JLabel lblBrugernavn = new JLabel("Brugernavn");
		lblBrugernavn.setBounds(12, 155, 70, 16);
		panel.add(lblBrugernavn);
		
		JLabel lblPassword = new JLabel("Medarbejder nummer");
		lblPassword.setBounds(12, 190, 129, 16);
		panel.add(lblPassword);
		
		JButton btnSearch = new JButton("S\u00F8g");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try
				{
					employeesList.removeAll();
					fName = textFornavn.getText();
					lName = textEfternavn.getText();
					userName = textBrugernavn.getText();
					empNo =  textMedarbejderNr.getText();
					
					latchSearch.countDown();
//					ok();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
						
			}
		});
		btnSearch.setBounds(172, 225, 97, 25);
		panel.add(btnSearch);
		
		JButton btnTilbage = new JButton("Tilbage");
		btnTilbage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				latchSearch.countDown();
				SearchEmployee.this.dispose();
			}
		});
		btnTilbage.setBounds(0, 268, 97, 25);
		panel.add(btnTilbage);
		
		JLabel lblSgMedarbejder = new JLabel("S\u00F8g medarbejder");
		lblSgMedarbejder.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSgMedarbejder.setBounds(12, 14, 172, 32);
		panel.add(lblSgMedarbejder);
	}
	
	public void addToList(String personString)
	{
		employeesList.add(personString);
	}
	
	public String getName() 
	{
		return fName;
	}
	public String getLastname() 
	{	
		return lName;
	}
	public String getUsername() 
	{
		return userName;
	}

	public String getEmpNo() 
	{
		return empNo;
	}
	public int getChoosen()
	{
		return choosen;
	}

	public int delete(String name)
	{
		int result = JOptionPane.showConfirmDialog((Component) null, "Er du sikker på du vil slette "+name,"Advarsel", JOptionPane.OK_CANCEL_OPTION);
		return result;
	}
}
