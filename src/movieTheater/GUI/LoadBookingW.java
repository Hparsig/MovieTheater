package movieTheater.GUI;

import java.awt.BorderLayout;
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
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class LoadBookingW extends JFrame {

	private JPanel contentPane;
	private List listBookings;
	private int selected;
	public final CountDownLatch latch = new CountDownLatch(1); //venter på brugerens input.

	/**
	 * @author Jesper
	 * Create the frame.
	 */
	public LoadBookingW() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 236);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected = -1;
				latch.countDown();
			}
		});
		btnCancel.setBounds(12, 152, 71, 25);
		panel.add(btnCancel);
		
		listBookings = new List();
		listBookings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selected = listBookings.getSelectedIndex();
				latch.countDown();
			}
		});
		listBookings.setBounds(12, 41, 389, 99);
		panel.add(listBookings);
		
		JLabel lblReserveretSder = new JLabel("Bookings");
		lblReserveretSder.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblReserveretSder.setBounds(12, 13, 158, 22);
		panel.add(lblReserveretSder);
	}
	
	public int getSelected()
	{
		return selected;
	}

	public void addBookings(String text)
	{
		listBookings.add(text);
	}
	public void showError()
	{
		JOptionPane.showMessageDialog(new JFrame(), "Fejl"); 
	}

}
