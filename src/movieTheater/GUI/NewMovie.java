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
import movieTheater.SQL.SQLMovieSave;

import java.awt.Font;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JComboBox;
import java.awt.List;

public class NewMovie extends JFrame {

	private JPanel contentPane;
	private JPanel castList;
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
	private ComboBoxGenre comboBoxGenre;
	private ComboBoxDirector comboBoxDirector;
	private ComboBoxActor comboBoxActor;
	private JComboBox comboBoxGenres;
	private JComboBox comboBoxDirectors;
	private JComboBox comboBoxActors;
	private Director director;
	private Cast cast;
	private ArrayList<Actor> maleActors;
	private ArrayList<Actor> femaleActors;
	private JButton btnAddDirector;
	private JButton btnAddGenre;
	private SQLMovieLoad load;
	private SQLMovieSave save;
	private List male;
	private List female;
	

	/**
	 * Create the frame.
	 */
	public NewMovie() 
	{
		director = null;
		cast = null;
		load = new SQLMovieLoad();
		save = new SQLMovieSave();

		
		try
		{
			genres = load.LoadGenres();
			directors = load.LoadDirector();
			maleActors = load.LoadMaleActors();
			femaleActors = load.LoadFemaleActors();
		} 
		catch (SQLException e2)
		{
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		comboBoxGenre = new ComboBoxGenre(genres);
		comboBoxDirector = new ComboBoxDirector(directors);
		//comboBoxActor = new ComboBoxActor(actors);
		
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
		setBounds(100, 100, 986, 408);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		castList = new JPanel();
		contentPane.add(castList, BorderLayout.CENTER);
		castList.setLayout(null);

		tfTitel = new JTextField();
		tfTitel.setBounds(112, 53, 142, 20);
		castList.add(tfTitel);
		tfTitel.setColumns(10);

		tfOriginalTitel = new JTextField();
		tfOriginalTitel.setColumns(10);
		tfOriginalTitel.setBounds(112, 84, 142, 20);
		castList.add(tfOriginalTitel);

		lblTitel = new JLabel("Titel");
		lblTitel.setBounds(28, 57, 46, 14);
		castList.add(lblTitel);

		lblOriginalTitel = new JLabel("Original titel");
		lblOriginalTitel.setBounds(28, 88, 74, 14);
		castList.add(lblOriginalTitel);

		lblInstruktr = new JLabel("Instrukt\u00F8r");
		lblInstruktr.setBounds(28, 119, 74, 14);
		castList.add(lblInstruktr);

		lblPrmiere = new JLabel("Pr\u00E6miere");
		lblPrmiere.setBounds(28, 151, 74, 14);
		castList.add(lblPrmiere);

		lblSpilletid = new JLabel("Spilletid");
		lblSpilletid.setBounds(28, 182, 74, 14);
		castList.add(lblSpilletid);

		ftfPremier = new JFormattedTextField(maskFormatDate);
		ftfPremier.setBounds(112, 146, 142, 20);
		castList.add(ftfPremier);

		ftfPlayingTime = new JFormattedTextField(maskFormatLength);
		ftfPlayingTime.setBounds(112, 178, 142, 20);
		castList.add(ftfPlayingTime);

		lblGenre = new JLabel("Genre");
		lblGenre.setBounds(28, 212, 46, 14);
		castList.add(lblGenre);

		lblUdlbsdato = new JLabel("Udl\u00F8bsdato");
		lblUdlbsdato.setBounds(28, 240, 64, 14);
		castList.add(lblUdlbsdato);

		ftfOffday = new JFormattedTextField(maskFormatDate);
		ftfOffday.setBounds(112, 239, 142, 20);
		castList.add(ftfOffday);

		btnAddActor = new JButton("Opret skuespiller");
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
		btnAddActor.setBounds(816, 56, 130, 23);
		castList.add(btnAddActor);

		tglbtnNewToggleButton = new JToggleButton("3D");
		tglbtnNewToggleButton.setBounds(28, 272, 121, 23);
		castList.add(tglbtnNewToggleButton);

		btnAbort = new JButton("Annuller");
		btnAbort.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
				NewMovie.this.dispose();
			} 
		});
		btnAbort.setBackground(Color.RED);
		btnAbort.setBounds(846, 291, 100, 23);
		castList.add(btnAbort);

		btnCreateMovie = new JButton("Opret film");
		btnCreateMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{	
					
					premierDate = dateFormat.parse(ftfPremier.getText());
					offDate = dateFormat.parse(ftfOffday.getText());
					java.sql.Date sqlDatePremier = new java.sql.Date(premierDate.getTime());
					java.sql.Date sqlDateEnd = new java.sql.Date(offDate.getTime());
					
					playingTime = Integer.parseInt(ftfPlayingTime.getText());
					
					if (comboBoxGenres.getSelectedItem() instanceof Genre)
					{
						genre = ((Genre) comboBoxGenres.getSelectedItem()).getGenreName();
					}
					if (comboBoxDirectors.getSelectedItem() instanceof Director)
					{
						director = (Director)comboBoxDirectors.getSelectedItem();
					}
					Movie newMovie = new Movie(tfTitel.getText(), director, playingTime, genre, sqlDatePremier, sqlDateEnd, tfOriginalTitel.getText(), tglbtnNewToggleButton.isSelected(), cast);	
					save.saveMovie(newMovie);
				} 
				catch (ParseException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}});
		
		btnCreateMovie.setBackground(Color.GREEN);
		btnCreateMovie.setBounds(846, 319, 100, 23);
		castList.add(btnCreateMovie);

		lblOpretFilm = new JLabel("Opret film");
		lblOpretFilm.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblOpretFilm.setBounds(28, 11, 158, 31);
		castList.add(lblOpretFilm);
		
		comboBoxGenres = comboBoxGenre.set();
		comboBoxGenres.setBounds(112, 209, 142, 20);
		castList.add(comboBoxGenres);
		
		comboBoxDirectors = comboBoxDirector.set();
		comboBoxDirectors.setBounds(112, 116, 142, 20);
		castList.add(comboBoxDirectors);
		
//		comboBoxActors = comboBoxActor.set();
//		comboBoxActors.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent e) 
//			{
//				AddToCast addToCast = new AddToCast((Actor)comboBoxActors.getSelectedItem());
//				addToCast.setVisible(true);
//			} 
//		});
		//comboBoxActors.setBounds(289, 53, 125, 23);
		//panel.add(comboBoxActors);
		
		btnAddDirector = new JButton("Opret instrukt\u00F8r");
		btnAddDirector.setBounds(816, 86, 130, 23);
		castList.add(btnAddDirector);
		
		btnAddGenre = new JButton("Opret genre");
		btnAddGenre.setBounds(816, 118, 130, 23);
		castList.add(btnAddGenre);
		
		male = new List();
		male.setMultipleMode(true);
		male.setBounds(260, 53, 168, 206);
		castList.add(male);
		
		female = new List();
		female.setMultipleMode(true);
		female.setBounds(441, 53, 168, 206);
		castList.add(female);
		for (int i=0; i < maleActors.size(); i++)
		{
			male.add(maleActors.get(i).toString());
		}
		for (int i=0; i < femaleActors.size(); i++)
		{
			female.add(femaleActors.get(i).toString());
		}
	}
}
