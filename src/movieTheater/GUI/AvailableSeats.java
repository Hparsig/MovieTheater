package movieTheater.GUI;

import java.awt.BorderLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import movieTheater.Show.Seat;
import movieTheater.main.BookingController;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;

@SuppressWarnings("serial")
public class AvailableSeats extends JFrame {

	private JPanel contentPane;
	private List seats;
	public final CountDownLatch latch = new CountDownLatch(1); //venter på brugerens input.
	private Object lock;  // synkroniserings objekt
	private JTextField textRow;
	private JTextField textSeat;
	private int row;
	private int seat;
	private List bookedeList;
	private int close = 0; //0 = open


	/**
	 * Create the frame.
	 * @author Jesper
	 */
	public AvailableSeats() {
		
		lock = new Object();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 596, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		seats = new List();
		seats.setBounds(12, 50, 360, 232);
		panel.add(seats);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close = -1;
				ok();
				latch.countDown();
			}
		});
		btnAnnuler.setBounds(12, 330, 97, 25);
		panel.add(btnAnnuler);
		
		JLabel lblSde = new JLabel("S\u00E6de");
		lblSde.setBounds(383, 83, 56, 16);
		panel.add(lblSde);
		
		JLabel lblRkke = new JLabel("R\u00E6kke");
		lblRkke.setBounds(383, 54, 56, 16);
		panel.add(lblRkke);
		
		textRow = new JTextField();
		textRow.setBounds(426, 51, 43, 22);
		panel.add(textRow);
		textRow.setColumns(10);
		
		textSeat = new JTextField();
		textSeat.setBounds(426, 80, 43, 22);
		panel.add(textSeat);
		textSeat.setColumns(10);
		
		bookedeList = new List();
		bookedeList.setBounds(376, 148, 180, 94);
		panel.add(bookedeList);
		
		JButton btnTilfj = new JButton("Tilf\u00F8j");
		btnTilfj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = textSeat.getText();
				String r = textRow.getText();
				
				try
				{
					seat = Integer.parseInt(s);
					row = Integer.parseInt(r);
					ok();
					
				}catch(Exception e1)
				{
					JOptionPane.showMessageDialog(new JFrame(), "sæde og række er ikke udfyldt korrekt"); 
				}
			}
		});
		btnTilfj.setBounds(481, 79, 75, 25);
		panel.add(btnTilfj);
		
		JLabel lblBookedeSder = new JLabel("Bookede s\u00E6der");
		lblBookedeSder.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBookedeSder.setBounds(378, 117, 116, 25);
		panel.add(lblBookedeSder);
		
		JLabel lblLedigeSder = new JLabel("Ledige s\u00E6der");
		lblLedigeSder.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblLedigeSder.setBounds(12, 18, 124, 26);
		panel.add(lblLedigeSder);
		
		JButton btnVidereTilBetaling = new JButton("Til betaling->");
		btnVidereTilBetaling.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				close = 1;
				latch.countDown();
				ok();
			}
		});
		btnVidereTilBetaling.setBounds(440, 257, 116, 25);
		panel.add(btnVidereTilBetaling);
		
		setSeat();
	}
	private void ok() 
	{
		synchronized (lock) 
		{
			lock.notify();
		}
	}
	public void addOrders()
	{
		bookedeList.add("Række: "+row+": sæde: "+seat);
	}
	public int getRow()
	{
		try {
			synchronized(lock) {
				lock.wait();
			}
		} catch (InterruptedException e) {}	
		return row;
	}
	public int getSeat()
	{
		return seat;
	}
	public int getClose()
	{
		return close;
	}
	public void showError()
	{
		JOptionPane.showMessageDialog(new JFrame(), "kombination af sæde og række findes ikke"); 
	}
	public void alreadyBooked()
	{
		JOptionPane.showMessageDialog(new JFrame(), "Sædet er desværre allerede booket"); 
	}
	public void setSeat()
	{
		seats.removeAll();
		for (Map.Entry<Integer, ArrayList<Seat>> entry : BookingController.av.entrySet())
		{
			seats.add("Række = " + (entry.getKey()+1) + ", Ledige sæder = " + entry.getValue());
		}	
	}

}
