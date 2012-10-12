package movieTheater.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.border.EtchedBorder;
import java.awt.Font;

public class NewMovie extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JTextField tfTitel;
	private JTextField tfOriginalTitel;
	private JTextField tfDirector;
	private JLabel lblTitel;
	private JLabel lblOriginalTitel;
	private JLabel lblInstruktr;
	private JLabel lblPrmiere;
	private JLabel lblSpilletid;
	private JFormattedTextField ftfPremier;
	private JFormattedTextField ftfPlayingTime;
	private JLabel lblGenre;
	private JTextField tfGenre;
	private JLabel lblUdlbsdato;
	private JFormattedTextField ftfOffday;
	private JButton btnAddActor;
	private JToggleButton tglbtnNewToggleButton;
	private JToggleButton tglbtnHelaftensfilm;
	private JButton btnAbort;
	private JButton btnCreateMovie;
	private JList listCast;
	private JLabel lblOpretFilm;

	/**
	 * Create the frame.
	 */
	public NewMovie() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 573, 319);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		tfTitel = new JTextField();
		tfTitel.setBounds(102, 54, 166, 20);
		panel.add(tfTitel);
		tfTitel.setColumns(10);
		
		tfOriginalTitel = new JTextField();
		tfOriginalTitel.setColumns(10);
		tfOriginalTitel.setBounds(102, 85, 166, 20);
		panel.add(tfOriginalTitel);
		
		tfDirector = new JTextField();
		tfDirector.setColumns(10);
		tfDirector.setBounds(102, 116, 166, 20);
		panel.add(tfDirector);
		
		lblTitel = new JLabel("Titel");
		lblTitel.setBounds(28, 57, 46, 14);
		panel.add(lblTitel);
		
		lblOriginalTitel = new JLabel("Original titel");
		lblOriginalTitel.setBounds(28, 88, 74, 14);
		panel.add(lblOriginalTitel);
		
		lblInstruktr = new JLabel("Instrukt\u00F8r");
		lblInstruktr.setBounds(28, 119, 74, 14);
		panel.add(lblInstruktr);
		
		lblPrmiere = new JLabel("Pr\u00E6miere");
		lblPrmiere.setBounds(28, 151, 74, 14);
		panel.add(lblPrmiere);
		
		lblSpilletid = new JLabel("Spilletid");
		lblSpilletid.setBounds(28, 182, 74, 14);
		panel.add(lblSpilletid);
		
		ftfPremier = new JFormattedTextField();
		ftfPremier.setBounds(102, 147, 166, 20);
		panel.add(ftfPremier);
		
		ftfPlayingTime = new JFormattedTextField();
		ftfPlayingTime.setBounds(102, 179, 166, 20);
		panel.add(ftfPlayingTime);
		
		lblGenre = new JLabel("Genre");
		lblGenre.setBounds(28, 212, 46, 14);
		panel.add(lblGenre);
		
		tfGenre = new JTextField();
		tfGenre.setColumns(10);
		tfGenre.setBounds(102, 209, 166, 20);
		panel.add(tfGenre);
		
		lblUdlbsdato = new JLabel("Udl\u00F8bsdato");
		lblUdlbsdato.setBounds(28, 240, 64, 14);
		panel.add(lblUdlbsdato);
		
		ftfOffday = new JFormattedTextField();
		ftfOffday.setBounds(102, 240, 166, 20);
		panel.add(ftfOffday);
		
		btnAddActor = new JButton("Tilf\u00F8j skuespiller");
		btnAddActor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddActor.setBounds(289, 53, 125, 23);
		panel.add(btnAddActor);
		
		tglbtnNewToggleButton = new JToggleButton("3D");
		tglbtnNewToggleButton.setBounds(293, 208, 121, 23);
		panel.add(tglbtnNewToggleButton);
		
		tglbtnHelaftensfilm = new JToggleButton("Helaftensfilm");
		tglbtnHelaftensfilm.setBounds(293, 236, 121, 23);
		panel.add(tglbtnHelaftensfilm);
		
		btnAbort = new JButton("Annuller");
		btnAbort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAbort.setBackground(Color.RED);
		btnAbort.setBounds(448, 208, 89, 23);
		panel.add(btnAbort);
		
		btnCreateMovie = new JButton("Opret film");
		btnCreateMovie.setBackground(Color.GREEN);
		btnCreateMovie.setBounds(448, 236, 89, 23);
		panel.add(btnCreateMovie);
		
		listCast = new JList();
		listCast.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listCast.setBounds(289, 87, 125, 109);
		panel.add(listCast);
		
		lblOpretFilm = new JLabel("Opret film");
		lblOpretFilm.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblOpretFilm.setBounds(28, 11, 158, 31);
		panel.add(lblOpretFilm);
	}
}
