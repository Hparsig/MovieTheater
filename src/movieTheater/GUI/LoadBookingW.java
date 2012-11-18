package movieTheater.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JList;
import java.awt.List;
import java.awt.Font;
import java.util.concurrent.CountDownLatch;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoadBookingW extends JFrame {

	private JPanel contentPane;
	private JTextField textPhone;
	private JTextField textLName;
	private JTextField textName;
	private List listBookings;
	private String phone;
	private String name;
	private String lastname;
	private Object lock;  // synkroniserings objekt
	private int selected;
	private int close = 1;
	
	
	public final CountDownLatch latch = new CountDownLatch(1); //venter på brugerens input.

	/**
	 * @author Jesper
	 * Create the frame.
	 */
	public LoadBookingW() {
		lock = new Object();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 265, 347);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnSg = new JButton("S\u00F8g");
		btnSg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listBookings.removeAll();
				phone = textPhone.getText();
				name = textName.getText();
				lastname =  textLName.getText();
				ok();
			}
		});
		btnSg.setBounds(154, 258, 71, 25);
		panel.add(btnSg);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ok();
				close = -1;
				latch.countDown();
			}
		});
		btnCancel.setBounds(71, 258, 71, 25);
		panel.add(btnCancel);
		
		JLabel lblFornavn = new JLabel("Fornavn");
		lblFornavn.setBounds(12, 30, 56, 16);
		panel.add(lblFornavn);
		
		JLabel lblEfternavn = new JLabel("Efternavn");
		lblEfternavn.setBounds(12, 59, 56, 16);
		panel.add(lblEfternavn);
		
		JLabel lblTlf = new JLabel("Tlf");
		lblTlf.setBounds(12, 88, 56, 16);
		panel.add(lblTlf);
		
		textPhone = new JTextField();
		textPhone.setBounds(71, 85, 116, 22);
		panel.add(textPhone);
		textPhone.setColumns(10);
		
		textLName = new JTextField();
		textLName.setColumns(10);
		textLName.setBounds(71, 56, 116, 22);
		panel.add(textLName);
		
		textName = new JTextField();
		textName.setColumns(10);
		textName.setBounds(71, 27, 116, 22);
		panel.add(textName);
		
		listBookings = new List();
		listBookings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selected = listBookings.getSelectedIndex();
				close = 0;
				ok();
				latch.countDown();
			}
		});
		listBookings.setBounds(12, 145, 213, 99);
		panel.add(listBookings);
		
		JLabel lblReserveretSder = new JLabel("Reserveret s\u00E6der");
		lblReserveretSder.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblReserveretSder.setBounds(12, 117, 158, 22);
		panel.add(lblReserveretSder);
	}
	
	private void ok() 
	{
		synchronized (lock) 
		{
			lock.notify();
		}
	}

	public String getPhone()
	{
		try {
			synchronized(lock) {
				lock.wait();
			}
		} catch (InterruptedException e) {}	
		return phone;
	}
	public String getName()
	{
		return name;
	}
	public String getLName()
	{
		return lastname;
	}
	public int getSelected()
	{
		return selected;
	}
	public int getClose()
	{
		return close;
	}
	public void addBookings(String text)
	{
		listBookings.add(text);
	}
	public void showError()
	{
		JOptionPane.showMessageDialog(new JFrame(), "Ingen bruger er fundet eller du skal være mere specifik"); 
	}
}
