package movieTheater.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import movieTheater.Movie.Actor;
import movieTheater.Movie.Cast;
import movieTheater.Movie.Director;
import movieTheater.Movie.Genre;
import movieTheater.Movie.Movie;
import movieTheater.SQL.SQLMovieLoad;
import movieTheater.SQL.SQLMovieSave;

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
	private JLabel lblOpretFilm;
	private MaskFormatter maskFormatDate;
	private MaskFormatter maskFormatLength;
	private SimpleDateFormat dateFormat;
	public final CountDownLatch latch = new CountDownLatch(1); //venter på brugerens input. 
	private String title;
	private String orgTitle;
	private Date premierDate;
	private Date offDate;
	private int playingTime; 
	private String genre;
	Boolean is3DSelected;
	private ArrayList<Genre> genres;
	private ArrayList<Director> directors;
	private ArrayList<Actor> actors;
	private SQLMovieLoad load;
	private SQLMovieSave save;
	private ComboBoxGenre comboBoxGenre;
	private ComboBoxDirector comboBoxDirector;
	private ComboBoxActor comboBoxActor;
	private JComboBox comboBoxGenres;
	private JComboBox comboBoxDirectors;
	private JComboBox comboBoxActors;
	private Movie movie;
	private Director director;
	private Cast cast;
	private Actor actor;
	private HashMap<Actor, String> castMap;
	private JButton btnAddDirector;
	private JButton btnAddGenre;
	private JPanel panel_1;
	private JPanel panel_2;

	/**
	 * Create the frame.
	 */
	public NewMovie() 
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		director = null;
		cast = null;
		actor = null;
		castMap = new HashMap<Actor, String>();
		is3DSelected = false;

		load = new SQLMovieLoad();
		save = new SQLMovieSave();
		
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

		setBounds(100, 100, 715, 319);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		tfTitel = new JTextField();
		tfTitel.setBounds(112, 53, 142, 20);
		panel.add(tfTitel);
		tfTitel.setColumns(10);

		tfOriginalTitel = new JTextField();
		tfOriginalTitel.setColumns(10);
		tfOriginalTitel.setBounds(112, 84, 142, 20);
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
		ftfPremier.setBounds(112, 146, 142, 20);
		panel.add(ftfPremier);

		ftfPlayingTime = new JFormattedTextField(maskFormatLength);
		ftfPlayingTime.setBounds(112, 178, 142, 20);
		panel.add(ftfPlayingTime);

		lblGenre = new JLabel("Genre");
		lblGenre.setBounds(28, 212, 46, 14);
		panel.add(lblGenre);

		lblUdlbsdato = new JLabel("Udl\u00F8bsdato");
		lblUdlbsdato.setBounds(28, 240, 64, 14);
		panel.add(lblUdlbsdato);

		ftfOffday = new JFormattedTextField(maskFormatDate);
		ftfOffday.setBounds(112, 239, 142, 20);
		panel.add(ftfOffday);

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
		btnAddActor.setBounds(549, 52, 130, 23);
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
		btnAbort.setBounds(579, 208, 100, 23);
		panel.add(btnAbort);

		btnCreateMovie = new JButton("Opret film");
		btnCreateMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
//				try
//				{	
					cast = new Cast(castMap);
					title = tfTitel.getText();
					orgTitle = tfOriginalTitel.getText();
//					premierDate = dateFormat.parse(ftfPremier.getText());
//					offDate = dateFormat.parse(ftfOffday.getText());
					playingTime = Integer.parseInt(ftfPlayingTime.getText());
					is3DSelected = tglbtnNewToggleButton.isSelected();

					java.sql.Date sqlDatePremier = new java.sql.Date(premierDate.getTime());	
					java.sql.Date sqlDateEnd = new java.sql.Date(offDate.getTime());
				 	 
					if (comboBoxGenres.getSelectedItem() instanceof Genre)
					{
						genre = ((Genre) comboBoxGenres.getSelectedItem()).getGenreName();
					}
					if (comboBoxDirectors.getSelectedItem() instanceof Director)
					{
						director = (Director)comboBoxDirectors.getSelectedItem();
					}
					
					movie = new Movie(title, director, playingTime, genre, sqlDatePremier, sqlDateEnd, orgTitle, tglbtnNewToggleButton.isSelected(), cast);      
					
					 
					latch.countDown();
//				} 
//				catch (ParseException e1)
//				{
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
			}
		});
		btnCreateMovie.setBackground(Color.GREEN);
		btnCreateMovie.setBounds(579, 236, 100, 23);
		panel.add(btnCreateMovie);

		lblOpretFilm = new JLabel("Opret film");
		lblOpretFilm.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblOpretFilm.setBounds(28, 11, 158, 31);
		panel.add(lblOpretFilm);

		panel_1 = new JPanel();
		panel_1.setBounds(289, 84, 121, 142);
		panel.add(panel_1);
		panel_1.setBackground(Color.WHITE);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		panel_2 = new JPanel();
		panel_2.setBounds(414, 84, 121, 142);
		panel.add(panel_2);
		panel_2.setBackground(Color.WHITE);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));

		comboBoxGenres = comboBoxGenre.set();
		comboBoxGenres.setBounds(112, 209, 142, 20);
		panel.add(comboBoxGenres);

		comboBoxDirectors = comboBoxDirector.set();
		comboBoxDirectors.setBounds(112, 116, 142, 20);
		panel.add(comboBoxDirectors);

		comboBoxActors = comboBoxActor.set();
		comboBoxActors.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
				Actor selectedActor = (Actor)comboBoxActors.getSelectedItem();
				String actorName = (selectedActor.getLName() + ", " + selectedActor.getFName()); 

				String roleName = JOptionPane.showInputDialog(comboBoxActors, actorName + " spiller" , "Tilføj til rolleliste", JOptionPane.DEFAULT_OPTION);
				if (roleName != null)
				{
					castMap.put(selectedActor, roleName);
					panel_1.removeAll();
					panel_2.removeAll();

					for (Actor actor: castMap.keySet())
					{
						panel_1.add(new JLabel(actor.getLName() + ", " + actor.getFName()));
						panel_2.add(new JLabel(castMap.get(actor)));
					}	
					panel_1.validate();
					panel_1.repaint();
					panel_2.validate();
					panel_2.repaint();
				}
			} 
		});
		comboBoxActors.setBounds(289, 53, 125, 23);
		panel.add(comboBoxActors);

		btnAddDirector = new JButton("Opret instrukt\u00F8r");
		btnAddDirector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddDirector.setBounds(549, 83, 130, 23);
		panel.add(btnAddDirector);

		btnAddGenre = new JButton("Opret genre");
		btnAddGenre.setBounds(549, 115, 130, 23);
		panel.add(btnAddGenre);
	}

	public Movie getMovie()
	{
		return movie;
	}
}
