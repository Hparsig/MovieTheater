package movieTheater.GUI;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import movieTheater.main.BookingController;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.concurrent.CountDownLatch;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class Checkout extends JFrame {
	
	private JPanel contentPane;
	private JPanel panel;
	
	private JTextField textAmount;
	private JLabel price;
	private JLabel jChange;
	private JButton btnAnnuler;
	private String amount;
	private int cancel = 0;
	private boolean amountOk = false;
	public final CountDownLatch amountReturn = new CountDownLatch(1); //venter på brugerens input.
	public final CountDownLatch latch = new CountDownLatch(1); //venter på brugerens input.
	
	
	public Checkout() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 306, 180);
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
				}
			}
		});
		btnAnnuler.setBounds(108, 75, 97, 25);
		panel.add(btnAnnuler);
		
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
		
		
	}
	
	public void showError()
	{
		JOptionPane.showMessageDialog(new JFrame(), "Beløb er ugyldigt format"); 
	}

}


