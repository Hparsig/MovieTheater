package movieTheater.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

public class MoviesWithNoShow extends JFrame {

	private JPanel contentPane;
	private List moviesList;

	/**
	 * Create the frame.
	 */
	public MoviesWithNoShow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 302, 298);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		moviesList = new List();
		moviesList.setBounds(10, 54, 254, 157);
		panel.add(moviesList);
		
		JButton btnTilbage = new JButton("Tilbage");
		btnTilbage.setBounds(10, 217, 97, 25);
		panel.add(btnTilbage);
		
		JLabel lblFilmUdenShows = new JLabel("Film uden shows");
		lblFilmUdenShows.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFilmUdenShows.setBounds(12, 13, 182, 35);
		panel.add(lblFilmUdenShows);
		
	}
}
