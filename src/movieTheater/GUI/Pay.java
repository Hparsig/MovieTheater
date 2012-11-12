package movieTheater.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.List;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import javax.swing.JButton;

import movieTheater.Show.Seat;
import movieTheater.main.BookingController;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Pay extends JFrame {

	private JPanel contentPane;
	public final CountDownLatch latch = new CountDownLatch(1); //venter på brugerens input.
	private static final int ABORT = -1;
	private static final int CASH = 0;
	private static final int CARD = 1;
	private int paymentMethode;

	/**
	 * Create the frame.
	 */
	public Pay() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 304, 272);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		List list = new List();
		list.setBounds(10, 73, 119, 102);
		panel.add(list);
		
		for (Map.Entry<Seat, Integer> entry : BookingController.bookings.entrySet())
		{
			list.add("Sæde: "+entry.getKey().toString()+", række: "+entry.getValue());
		}
		
		JLabel lblNewLabel = new JLabel("Betaling");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 13, 192, 33);
		panel.add(lblNewLabel);
		
		JLabel lblReserveretSder = new JLabel("Reserveret s\u00E6der");
		lblReserveretSder.setBounds(10, 51, 119, 16);
		panel.add(lblReserveretSder);
		
		JButton btnBetalMedDankort = new JButton("Betal med dankort");
		btnBetalMedDankort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paymentMethode = CARD;
				latch.countDown();
			}
		});
		btnBetalMedDankort.setBounds(135, 129, 137, 25);
		panel.add(btnBetalMedDankort);
		
		JButton btnBetalMedKontant = new JButton("Betal med kontant");
		btnBetalMedKontant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paymentMethode = CASH;
				latch.countDown();
			}
		});
		btnBetalMedKontant.setBounds(135, 164, 137, 25);
		panel.add(btnBetalMedKontant);
		
		JLabel lblSamletBelb = new JLabel("Samlet bel\u00F8b");
		lblSamletBelb.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblSamletBelb.setBounds(135, 73, 125, 25);
		panel.add(lblSamletBelb);
		
		JLabel labelBeløb = new JLabel(""+BookingController.amount);
		labelBeløb.setBounds(135, 100, 80, 16);
		panel.add(labelBeløb);
		
		JButton btnStopBetaling = new JButton("STOP betaling");
		btnStopBetaling.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paymentMethode = ABORT;
				latch.countDown();
			}
		});
		btnStopBetaling.setBounds(10, 181, 119, 25);
		panel.add(btnStopBetaling);
	}
	public int getPaymentMethode()
	{
		return paymentMethode;
	}

}
