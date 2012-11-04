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
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.border.EtchedBorder;
import javax.swing.text.MaskFormatter;

import movieTheater.Movie.Actor;
import movieTheater.Movie.Cast;
import movieTheater.Movie.Director;
import movieTheater.Movie.Genre;
import movieTheater.Movie.Movie;
import movieTheater.SQL.SQLMovieLoad;

import java.awt.Font;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JComboBox;

public class NewMovie extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JTextField tfTitel;
	private JTextField tfOriginalTitel;
	private JLabel lblTitel;
	private JLabel lblOriginalTitel;
	private JLabel lblInstruktr;
	private JLabel lblPrmiere;
	private JLabel lblSpilletid;
	private JFormattedTextField ftfPremier;
	private JFormattedTextField ftfPlayingTime;
	private JLabel lblGenre;
	private JLabel lblUdlbsdato;
	private JFormattedTextField ftfOffday;
	private JButton btnAddActor;
	private JToggleButton tglbtnNewToggleButton;
	private JButton btnAbort;
	private JButton btnCreateMovie;
	private JList listCast;
	private JLabel lblOpretFilm;
	private MaskFormatter maskFormatDate;
	private MaskFormatter maskFormatLength;
	private SimpleDateFormat dateFormat;
	private Date premierDate;
	private Date offDate;
	private int playingTime; 
	private String genre;
	private ArrayList<Genre> genres;
	private ArrayList<Director> directors;
	private ArrayList<Actor> actors;
	private SQLMovieLoad load;
	private ComboBoxGenre comboBoxGenre;
	private ComboBoxDirector comboBoxDirector;
	private ComboBoxActor comboBoxActor;
	private JComboBox comboBoxGenres;
	private JComboBox comboBoxDirectors;
	private JComboBox comboBoxActors;
	private Director director;
	private Cast cast;
	private Actor actor;

	/**
	 * Create the frame.
	 */
	public NewMovie() 
	{
		director = null;
		cast = null;
		actor = null;
		
		load = new SQLMovieLoad();
		try
		{
			genres = load.LoadGenres();
			directors = load.LoadDirector();
			actors = load.LoadActors();
		} 
		catch (SQLException e2)
		{
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		comboBoxGenre = new ComboBoxGenre(genres);
		comboBoxDirector = new ComboBoxDirector(directors);
		comboBoxActor = new ComboBoxActor(actors);
		
		try
		{
			maskFormatDate = new MaskFormatter("##-##-####");
			maskFormatLength = new MaskFormatter("###");
		} catch (ParseException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

		ftfPremier = new JFormattedTextField(maskFormatDate);
		ftfPremier.setBounds(102, 147, 166, 20);
		panel.add(ftfPremier);

		ftfPlayingTime = new JFormattedTextField(maskFormatLength);
		ftfPlayingTime.setBounds(102, 179, 166, 20);
		panel.add(ftfPlayingTime);

		lblGenre = new JLabel("Genre");
		lblGenre.setBounds(28, 212, 46, 14);
		panel.add(lblGenre);

		lblUdlbsdato = new JLabel("Udl\u00F8bsdato");
		lblUdlbsdato.setBounds(28, 240, 64, 14);
		panel.add(lblUdlbsdato);

		ftfOffday = new JFormattedTextField(maskFormatDate);
		ftfOffday.setBounds(102, 240, 166, 20);
		panel.add(ftfOffday);

		btnAddActor = new JButton("Tilf\u00F8j skuespiller");
		btnAddActor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				final CreateActor createActor = new CreateActor();
				createActor.setVisible(true);
				final ArrayList<Actor> actors = new ArrayList<Actor>();
				createActor.addComponentListener(new ComponentListener(){

					@Override
					public void componentHidden(ComponentEvent arg0)
					{
						Actor actor = createActor.getActor();
						actors.add(actor);
						createActor.dispose();
					}

					@Override
					public void componentMoved(ComponentEvent arg0)
					{
						// TODO Auto-generated method stub

					}

					@Override
					public void componentResized(ComponentEvent arg0)
					{
						// TODO Auto-generated method stub

					}

					@Override
					public void componentShown(ComponentEvent arg0)
					{
						// TODO Auto-generated method stub

					}

				});
			}
		});
		btnAddActor.setBounds(430, 53, 107, 23);
		panel.add(btnAddActor);

		tglbtnNewToggleButton = new JToggleButton("3D");
		tglbtnNewToggleButton.setBounds(289, 236, 121, 23);
		panel.add(tglbtnNewToggleButton);

		btnAbort = new JButton("Annuller");
		btnAbort.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
				NewMovie.this.dispose();
			} 
		});
		btnAbort.setBackground(Color.RED);
		btnAbort.setBounds(437, 208, 100, 23);
		panel.add(btnAbort);

		btnCreateMovie = new JButton("Opret film");
		btnCreateMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{	
					premierDate = dateFormat.parse(ftfPremier.getText());
					offDate = dateFormat.parse(ftfOffday.getText());
					playingTime = Integer.parseInt(ftfPlayingTime.getText());
					
					if (comboBoxGenres.getSelectedItem() instanceof Genre)
					{
						genre = ((Genre) comboBoxGenres.getSelectedItem()).getGenreName();
					}
					if (comboBoxDirectors.getSelectedItem() instanceof Director)
					{
					director = (Director)comboBoxDirectors.getSelectedItem();
					}
				} 
				catch (ParseException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Movie newMovie = new Movie(tfTitel.getText(), director, playingTime, genre, premierDate, offDate, tfOriginalTitel.getText(), tglbtnNewToggleButton.isSelected(), cast);			}
		});
		btnCreateMovie.setBackground(Color.GREEN);
		btnCreateMovie.setBounds(437, 236, 100, 23);
		panel.add(btnCreateMovie);

		listCast = new JList();
		listCast.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listCast.setBounds(289, 87, 125, 139);
		panel.add(listCast);

		lblOpretFilm = new JLabel("Opret film");
		lblOpretFilm.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblOpretFilm.setBounds(28, 11, 158, 31);
		panel.add(lblOpretFilm);
		
		comboBoxGenres = comboBoxGenre.set();
		comboBoxGenres.setBounds(102, 209, 166, 20);
		panel.add(comboBoxGenres);
		
		comboBoxDirectors = comboBoxDirector.set();
		comboBoxDirectors.setBounds(102, 116, 166, 20);
		panel.add(comboBoxDirectors);
		
		comboBoxActors = comboBoxActor.set();
		comboBoxDirectors.setBounds(289, 53, 125, 23);
		panel.add(comboBoxDirectors);
	}
}
