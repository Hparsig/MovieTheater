package movieTheater.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import movieTheater.Movie.Director;

public class CreateDirector extends JFrame
{

	private JPanel contentPane;
	private JPanel panel;
	private JTextField txtFName;
	private JTextField txtLName;
	private JLabel lblFName;
	private JLabel lblLName;
	private JTextField txtDescript;
	private JLabel lblDescript;
	private JToggleButton tglbtnGender;
	private JLabel lblNewLabel;
	private JButton btnAbort;
	private JButton btnOpret;
	private Director director;
	private String fName;
	private String lName;
	private String description;
	private String genderText;
	private int gender;
	private JLabel lblcreateDirector;


	/**
	 * Create the frame.
	 */
	public CreateDirector()
	{
		director = null;
		gender = 1; 		// sets gender to mail

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		txtFName = new JTextField();
		txtFName.setBounds(106, 70, 129, 20);
		panel.add(txtFName);
		txtFName.setColumns(10);

		txtLName = new JTextField();
		txtLName.setColumns(10);
		txtLName.setBounds(106, 101, 129, 20);
		panel.add(txtLName);

		lblFName = new JLabel("First Name");
		lblFName.setBounds(25, 73, 71, 14);
		panel.add(lblFName);

		lblLName = new JLabel("Last Name");
		lblLName.setBounds(25, 104, 71, 14);
		panel.add(lblLName);

		txtDescript = new JTextField();
		txtDescript.setColumns(10);
		txtDescript.setBounds(106, 130, 200, 111);
		panel.add(txtDescript);

		lblDescript = new JLabel("Description");
		lblDescript.setBounds(25, 133, 71, 14);
		panel.add(lblDescript);

		tglbtnGender = new JToggleButton("K\u00F8n");
		tglbtnGender.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				lblNewLabel.setText(toggleGender());
			} 
		});
		tglbtnGender.setBounds(316, 69, 98, 23);
		panel.add(tglbtnGender);

		lblNewLabel = new JLabel("Mand");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(343, 104, 46, 14);
		panel.add(lblNewLabel);

		btnAbort = new JButton("Annuller");
		btnAbort.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			} 
		});
		btnAbort.setBackground(Color.RED);
		btnAbort.setBounds(316, 190, 100, 23);
		panel.add(btnAbort);

		btnOpret = new JButton("Opret");
		btnOpret.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				fName = txtFName.getText();
				lName = txtLName.getText();
				description = txtDescript.getText();
				if (tglbtnGender.isSelected())
					gender = 0;							// 0 = Female, 1 = male
				
				director = new Director(fName, lName, gender, description);
				setVisible(false);
			}
		});	
		btnOpret.setBackground(Color.GREEN);
		btnOpret.setBounds(316, 218, 100, 23);
		panel.add(btnOpret);
		
		lblcreateDirector = new JLabel("Opret instruktør");
		lblcreateDirector.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblcreateDirector.setBounds(10, 11, 193, 20);
		panel.add(lblcreateDirector);
	}

	public String toggleGender()
	{
		if( gender == 1)
		{
			gender = 0;
			genderText = "female";
		}
		else
		{
			gender = 1;
			genderText = "male";
		}
		return genderText;
	}
	
	public Director getDirector()
	{
		return director;
	}
}
