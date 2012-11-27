package movieTheater.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;

import movieTheater.Show.Show;
import movieTheater.main.ShowController;

public class DeleteShow extends JFrame {
	
	private ArrayList<Show> shows;
	private Show show;
	private boolean areChangesMade;
	private JPanel contentPane;
	private JList listShow;
	public CountDownLatch latch = new CountDownLatch(1); //venter på brugerens input.
	
	/**
	 * Create the frame.
	 */
	public DeleteShow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		areChangesMade = false;
		setTitle("Slet Forestilling");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 744, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblSletForestilling = new JLabel("Slet Forestilling");
		lblSletForestilling.setBounds(10, 11, 72, 14);
		panel.add(lblSletForestilling);
		
		
		JButton btnAnnuller = new JButton("Annuller");
		btnAnnuller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DeleteShow.this.dispose();
				latch.countDown();
			}
		});
		btnAnnuller.setBounds(204, 218, 89, 23);
		panel.add(btnAnnuller);
		
		listShow = new JList(ShowController.shows.toArray());
		listShow.setBounds(188, 28, 490, 170);
		panel.add(listShow);
		
		JButton btnSletForestilling = new JButton("Slet Forestilling");
		btnSletForestilling.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				
				int chosen = listShow.getSelectedIndex();
				show = ShowController.shows.get(chosen);// evt. ShowController.shows.toArray() halløj
				areChangesMade = true;
				DeleteShow.this.dispose();
				latch.countDown();
			}
		});
		btnSletForestilling.setBounds(309, 218, 105, 23);	
		panel.add(btnSletForestilling);
	}

	public boolean getAreChangesMade() {
		return areChangesMade;
	}
	
	public Show getShow(){
		return show;
	}
	
}
