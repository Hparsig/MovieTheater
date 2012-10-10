package movieTheater.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import java.awt.List;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import movieTheater.main.CinemaController;


public class TestWindow {

	JFrame frame;
	private JList<String> lstNames;
	CinemaController cinemaController;

	public TestWindow(CinemaController cinemaController) {
		this.cinemaController = cinemaController;
		initialize();
		
		// TODO Auto-generated constructor stub
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 629, 482);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		final JButton btnJumpOcean = new JButton("hop i havet");
		btnJumpOcean.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnJumpOcean.setText("I'm wet");
			}
		});
		btnJumpOcean.setBounds(10, 80, 89, 23);
		panel.add(btnJumpOcean);
		
		lstNames = new JList<String>();
		lstNames.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				String s = (String) lstNames.getSelectedValue();
				btnJumpOcean.setText(s);
			}
		});
		lstNames.setModel(new AbstractListModel<String>() {
			String[] values = new String[] {"Sally", "Thomas", "Arne", "S\u00F8ren"};
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		lstNames.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstNames.setBounds(98, 199, 172, 188);
		panel.add(lstNames);
		
		JButton btnSgFilm = new JButton("S\u00F8g film");
		btnSgFilm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		btnSgFilm.setBounds(146, 29, 89, 23);
		panel.add(btnSgFilm);
	}
}
