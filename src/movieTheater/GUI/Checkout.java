package movieTheater.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.TextArea;
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
import java.awt.Font;

@SuppressWarnings("serial")
public class Checkout extends JFrame {
	
	private JPanel contentPane;
	private JPanel panel;
	private TextArea ticketL;
	private JTextField textAmount;
	private JLabel price;
	private JLabel jChange;
	private JButton btnAnnuler;
	private String amount;
	private int cancel = 0;
	private boolean amountOk = false;
	public CountDownLatch amountReturn = new CountDownLatch(1); //venter på brugerens input.
	public CountDownLatch latch = new CountDownLatch(1); //venter på brugerens input.
	
	
	/**
	 * @author Jesper og Martin
	 */
	public Checkout() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 407, 224);
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
		
		ticketL = new TextArea("", 1, 1, TextArea.SCROLLBARS_NONE);
		ticketL.setBounds(229, 42, 145, 123);
		panel.add(ticketL);
		
		JLabel lblBillet = new JLabel("Billet");
		lblBillet.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBillet.setBounds(231, 15, 56, 25);
		panel.add(lblBillet);
		
	}
	
	public String getAmount()
	{
		return amount;
	}
	public void setChange(double change)
	{
		jChange.setText("retur: "+change);
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
	public void setButton()
	{
		btnAnnuler.setText("Afslut");
	}
}


