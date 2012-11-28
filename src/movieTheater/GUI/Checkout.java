package movieTheater.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
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

import movieTheater.main.BookingController;

@SuppressWarnings("serial")
public class Checkout extends JFrame {
	
	private JPanel contentPane;
	private JPanel panel;
	private JLabel ticketL;
	private JTextField textAmount;
	private JLabel price;
	private JLabel jChange;
	private JButton btnAnnuler;
	private String amount;
	private int cancel = 0;
	private boolean amountOk = false;
	public CountDownLatch amountReturn = new CountDownLatch(1); //venter på brugerens input.
	public CountDownLatch latch = new CountDownLatch(1); //venter på brugerens input.
	
	
	public Checkout() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 414, 291);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		jChange = new JLabel();
		jChange.setBounds(10, 75, 86, 20);
		
	
		textAmount = new JTextField();	
		textAmount.setBounds(10, 42, 86, 20);
		panel.add(textAmount);
		textAmount.setColumns(10);
			
		price = new JLabel(""+BookingController.amount);
		price.setBounds(10, 11, 86, 20);
		panel.add(price);
		panel.add(jChange);
		
		JButton btnBetal = new JButton("Betal");
		btnBetal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				amount = textAmount.getText();
				amountReturn.countDown();
				
				btnAnnuler.setText("Afslut");
			}
		});
		btnBetal.setBounds(108, 40, 97, 25);
		panel.add(btnBetal);
		
		btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				amountReturn.countDown();
				latch.countDown();
				if(btnAnnuler.getText().equals("Annuler"))
				{
					cancel = -1;
					amountOk = true;
				}
			}
		});
		btnAnnuler.setBounds(108, 75, 97, 25);
		panel.add(btnAnnuler);
		
		ticketL = new JLabel("");
		ticketL.setForeground(Color.BLUE);
		ticketL.setBackground(Color.BLUE);
		ticketL.setBounds(220, 44, 154, 181);
		panel.add(ticketL);
		
	}
	
	public String getAmount()
	{
		return amount;
	}
	public void setChange(double change)
	{
		jChange.setText("Change: "+change);
	}
	public int getCancel()
	{
		return cancel;
	}
	public void setAmountok()
	{
		amountOk = true;
	}
	public boolean getAmountok()
	{
		return amountOk;
	}
	public void showAmountError()
	{
		JOptionPane.showMessageDialog(new JFrame(), "Beløb er for småt");
		amountReturn = new CountDownLatch(1); 
	}
	
	public void showError()
	{
		JOptionPane.showMessageDialog(new JFrame(), "Beløb er ugyldigt format"); 
		latch = new CountDownLatch(1);
	}
	public void addTicket(String text)
	{
		ticketL.setText(text);
	}
}


