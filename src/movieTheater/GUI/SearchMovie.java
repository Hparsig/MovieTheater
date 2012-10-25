package movieTheater.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFormattedTextField;

import movieTheater.main.MovieController;

public class SearchMovie {

	private JFrame frame;
	private JPanel panel;
	private JFormattedTextField txtStartDato;
	private JLabel lblStartdato;
	private JFormattedTextField txtSlutDato;
	private JLabel lblSlutdato;
	private JButton btnStartSgning;
	private JButton btnFortryd;
	private MovieController filmController;

	/**
	 * Create the application.
	 */
	public SearchMovie(MovieController filmController) {
		this.filmController = filmController;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		txtStartDato = new JFormattedTextField();
		txtStartDato.setBounds(160, 30, 86, 20);
		panel.add(txtStartDato);
		txtStartDato.setColumns(10);
		
		lblStartdato = new JLabel("startdato");
		lblStartdato.setBounds(104, 33, 46, 14);
		panel.add(lblStartdato);
		
		txtSlutDato = new JFormattedTextField();
		txtSlutDato.setColumns(10);
		txtSlutDato.setBounds(160, 61, 86, 20);
		panel.add(txtSlutDato);
		
		lblSlutdato = new JLabel("slutdato");
		lblSlutdato.setBounds(104, 64, 46, 14);
		panel.add(lblSlutdato);
		
		btnStartSgning = new JButton("s\u00F8g");
		btnStartSgning.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO txtStartDato og txtSlutDato 1 laves om til Date og kontrolleres. 
				//filmController.getAvailableFilms(timeStart, timeEnd)
			}
		});
		btnStartSgning.setBounds(255, 165, 89, 23);
		panel.add(btnStartSgning);
		
		btnFortryd = new JButton("annuller");
		btnFortryd.setBounds(142, 165, 89, 23);
		panel.add(btnFortryd);
	}
}
