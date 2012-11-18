package movieTheater.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.List;
import javax.swing.JButton;
import java.awt.Font;
import java.util.concurrent.CountDownLatch;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchCostumer extends JFrame {

	private JPanel contentPane;
	private JTextField textTlf;
	private JTextField textLName;
	private JTextField textName;
	private String phone;
	private String name;
	private String lastname;
	private Object lock;  // synkroniserings objekt
	private int selected;
	private int close = 1;
	private List listKunder;
	
	
	public final CountDownLatch latch = new CountDownLatch(1); //venter på brugerens input.


	/**
	 * @author Jesper
	 * Create the frame.
	 */
	public SearchCostumer() {
		
		lock = new Object();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 269, 337);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textTlf = new JTextField();
		textTlf.setColumns(10);
		textTlf.setBounds(59, 85, 116, 22);
		panel.add(textTlf);
		
		textLName = new JTextField();
		textLName.setColumns(10);
		textLName.setBounds(59, 48, 116, 22);
		panel.add(textLName);
		
		textName = new JTextField();
		textName.setColumns(10);
		textName.setBounds(59, 13, 116, 22);
		panel.add(textName);
		
		JLabel label = new JLabel("Fornavn");
		label.setBounds(0, 16, 56, 16);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Efternavn");
		label_1.setBounds(0, 51, 56, 16);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Tlf");
		label_2.setBounds(0, 88, 56, 16);
		panel.add(label_2);
		
		listKunder = new List();
		listKunder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected = listKunder.getSelectedIndex();
				close = 0;
				ok();
				latch.countDown();
			}
		});
		listKunder.setBounds(0, 141, 213, 99);
		panel.add(listKunder);
		
		JButton button = new JButton("S\u00F8g");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listKunder.removeAll();
				phone = textTlf.getText();
				name = textName.getText();
				lastname =  textLName.getText();
				ok();
			}
		});
		button.setBounds(142, 246, 71, 25);
		panel.add(button);
		
		JButton button_1 = new JButton("Cancel");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					ok();
					close = -1;
					latch.countDown();
			}
		});
		button_1.setBounds(59, 246, 71, 25);
		panel.add(button_1);
		
		JLabel lblKunde = new JLabel("Kunde");
		lblKunde.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblKunde.setBounds(0, 113, 158, 22);
		panel.add(lblKunde);
	}
	public int getClose()
	{
		return close;
	}
	public int getSelected()
	{
		return selected;
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
	private void ok() 
	{
		synchronized (lock) 
		{
			lock.notify();
		}
	}
	public void addCostumer(String text)
	{
		listKunder.add(text);
	}
	public void showError()
	{
		JOptionPane.showMessageDialog(new JFrame(), "Ingen bruger er fundet eller du skal være mere specifik"); 
	}
}
