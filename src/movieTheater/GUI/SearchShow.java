package movieTheater.GUI;

import java.awt.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import javax.swing.JFormattedTextField;

import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

@SuppressWarnings("serial")
public class SearchShow extends JFrame{

	
	private JPanel contentPane;
	private JPanel panel;
	private JFormattedTextField txtDato;
	private JLabel lblStartdato;
	private JFormattedTextField txtTitel;
	private JLabel lblSlutdato;
	private JButton btnStartSgning;
	private JButton btnFortryd;
	private List showList;
	
	private MaskFormatter maskFormatDate;
	private SimpleDateFormat dateFormat;
	private Date date;
	private String title;
	private java.sql.Date sqlDate;
	private int selected=-1;
	public final CountDownLatch latch = new CountDownLatch(1); //venter på brugerens input.
	private Object lock;  // synkroniserings objekt
	private int close = 0;
	


	/**
	 * @author Jesper
	 * Create the application.
	 */
	
	public SearchShow() {
		
		lock = new Object();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 345, 347);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		try
		{
			maskFormatDate = new MaskFormatter("##-##-####");
		} 
		catch (ParseException e1)
		{
			e1.printStackTrace();
		}
		dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		
		txtDato = new JFormattedTextField(maskFormatDate);
		txtDato.setBounds(68, 13, 86, 20);
		panel.add(txtDato);
		txtDato.setColumns(10);
		
		lblStartdato = new JLabel("Dato");
		lblStartdato.setBounds(12, 16, 46, 14);
		panel.add(lblStartdato);
		
		txtTitel = new JFormattedTextField();
		txtTitel.setColumns(10);
		txtTitel.setBounds(68, 44, 86, 20);
		panel.add(txtTitel);
		
		lblSlutdato = new JLabel("Titel");
		lblSlutdato.setBounds(12, 47, 46, 14);
		panel.add(lblSlutdato);
		
		btnStartSgning = new JButton("s\u00F8g");
		btnStartSgning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showList.removeAll();
				
				try
				{
					date = dateFormat.parse(txtDato.getText());
					sqlDate = new java.sql.Date(date.getTime());

				}catch(java.text.ParseException ee)
				{
					sqlDate = null;
				}
				
				title = txtTitel.getText();
				ok();
				txtTitel.setText("");
				txtDato.setText("");
			}
		});
		
		showList = new List();
		showList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ok();
				selected = showList.getSelectedIndex();	
				close = 1;
				latch.countDown();

			}
		});
		showList.setBounds(12, 108, 273, 131);
		panel.add(showList);

		btnStartSgning.setBounds(166, 27, 89, 23);
		panel.add(btnStartSgning);
		
		btnFortryd = new JButton("Tilbage");
		btnFortryd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ok();
				close = 1;
				latch.countDown();
			}
		});
		btnFortryd.setBounds(12, 245, 89, 23);
		panel.add(btnFortryd);
		
	
		JLabel lblForestillinger = new JLabel("Forestillinger");
		lblForestillinger.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblForestillinger.setBounds(12, 77, 130, 25);
		panel.add(lblForestillinger);
	}
	private void ok() 
	{
		synchronized (lock) 
		{
			lock.notify();
		}
	}

	public String getTitel() 
	{
		try {
			synchronized(lock) {
				lock.wait();
			}
		} catch (InterruptedException e) {}	
		return title;
	}
	
	public int getClose()
	{
		return close;
	}
	public java.sql.Date getSqlDate() {
		return sqlDate;
	}
	
	public void addShowList(String text)
	{
		showList.add(text);
	}
	public int getSelected()
	{
		return selected;
	}
	
	public void showErrorWrongDate()
	{
		JOptionPane.showMessageDialog(new JFrame(), "Der kan ikke søges på gamle forestillinger"); 
	}

	
}
