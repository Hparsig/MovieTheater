package movieTheater.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.concurrent.CountDownLatch;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewOrderAreYouCostumer extends JFrame {

	private JPanel contentPane;
	public final CountDownLatch latch = new CountDownLatch(1); //venter p� brugerens input.
	private static final int COSTUMER = 1;
	private static final int NOTCOSTUMER = 0;
	private static final int CANCEL = -1;
	private int choise;


	/**
	 * Create the frame.
	 */
	public NewOrderAreYouCostumer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 168, 189);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnJa = new JButton("Ja");
		btnJa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choise = COSTUMER;
				latch.countDown();
			}
		});
		btnJa.setBounds(12, 13, 97, 25);
		panel.add(btnJa);
		
		JButton btnNej = new JButton("Nej");
		btnNej.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choise = NOTCOSTUMER;
				latch.countDown();
			}
		});
		btnNej.setBounds(12, 45, 97, 25);
		panel.add(btnNej);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choise = CANCEL;
				latch.countDown();
			}
		});
		btnCancel.setBounds(12, 83, 97, 25);
		panel.add(btnCancel);
	}
	public int getChoise()
	{
		return choise;
	}

}
