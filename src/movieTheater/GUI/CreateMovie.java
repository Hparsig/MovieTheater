package movieTheater.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import movieTheater.main.MovieController;

public class CreateMovie extends JFrame {

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
	private Date premierDate;
	private Date offDate;
	private int playingTime; 
	private Genre genre;
	private JComboBox<Genre> comboBoxGenres;
	private JComboBox<Director> comboBoxDirectors;
	private JComboBox<Actor> comboBoxActors;
	private Director director;
	private Cast cast;
	private HashMap<Actor, String> castMap;
	private JButton btnAddDirector;
	private JButton btnAddGenre;
	private JPanel panel_1;
	private JPanel panel_2;
	boolean areChangesMade;
	private Movie movie;

	/**
	 * Create the frame.
	 */
	public CreateMovie(final Movie movie) 
	{
		this.movie = movie;
		
		if(movie.getInstructedBy() == null)
		{	
			setTitle("Opret film");
		}
		else
		{
			setTitle("Rediger film");
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		areChangesMade = false;

		if(movie.getInstructedBy() != null)
		{	
			castMap = (HashMap<Actor, String>) movie.getCast().getCast();
		}
		else
		{
			castMap = new HashMap<Actor, String>();
			cast = new Cast(castMap);
		}

		try
		{
			maskFormatDate = new MaskFormatter("##-##-####");
			maskFormatLength = new MaskFormatter("###");
		} 
		catch (ParseException e1)
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
		tfTitel.setText(movie.getMovieName());
		tfTitel.setColumns(10);

		tfOriginalTitel = new JTextField();
		tfOriginalTitel.setColumns(10);
		tfOriginalTitel.setBounds(112, 84, 142, 20);
		tfOriginalTitel.setText(movie.getOriginalName());
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
		if (movie.getReleaseDate() != null)
		{
			ftfPremier.setText(movie.getReleaseDate().toLocaleString());
		}
		panel.add(ftfPremier);

		ftfPlayingTime = new JFormattedTextField(maskFormatLength);
		ftfPlayingTime.setBounds(112, 178, 142, 20);
		if (movie.getLength() != 0)
		{
			ftfPlayingTime.setText(movie.getLength()+"");
		}
		panel.add(ftfPlayingTime);

		lblGenre = new JLabel("Genre");
		lblGenre.setBounds(28, 212, 46, 14);
		panel.add(lblGenre);

		lblUdlbsdato = new JLabel("Udl\u00F8bsdato");
		lblUdlbsdato.setBounds(28, 240, 64, 14);
		panel.add(lblUdlbsdato);

		ftfOffday = new JFormattedTextField(maskFormatDate);
		ftfOffday.setBounds(112, 239, 142, 20);
		if (movie.getTimeEnd() != null)
		{
			ftfOffday.setText(movie.getTimeEnd().toLocaleString());
		}
		panel.add(ftfOffday);

		btnAddActor = new JButton("Opret skuespiller");
		btnAddActor.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				final CreateActor createActor = new CreateActor();
				createActor.setVisible(true);

				createActor.addComponentListener(new ComponentListener()
				{
					@Override
					public void componentHidden(ComponentEvent arg0)
					{
						Actor actor = createActor.getActor();
						MovieController.actors.add(actor);
						MovieController.newActors.add(actor);
						createActor.dispose();
						comboBoxActors.addItem(actor);
						comboBoxActors.setSelectedItem(actor);
					}
					@Override
					public void componentMoved(ComponentEvent arg0)
					{	}

					@Override
					public void componentResized(ComponentEvent arg0)
					{	}

					@Override
					public void componentShown(ComponentEvent arg0)
					{	}
				});
			}
		});
		btnAddActor.setBounds(549, 52, 130, 23);
		panel.add(btnAddActor);

		tglbtnNewToggleButton = new JToggleButton("3D");
		tglbtnNewToggleButton.setBounds(289, 236, 121, 23);
		panel.add(tglbtnNewToggleButton);

		btnAbort = new JButton("Annuller");
		btnAbort.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				latch.countDown();
				CreateMovie.this.dispose();
			} 
		});
		btnAbort.setBackground(Color.RED);
		btnAbort.setBounds(579, 208, 100, 23);
		panel.add(btnAbort);


		if(movie.getInstructedBy() == null)
		{	
			btnCreateMovie = new JButton("Opret film");
		}
		else
		{
			btnCreateMovie = new JButton("Gem");	
		}		
		btnCreateMovie.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try
				{	

					premierDate = dateFormat.parse(ftfPremier.getText());
					offDate = dateFormat.parse(ftfOffday.getText());
					playingTime = Integer.parseInt(ftfPlayingTime.getText());

					java.sql.Date sqlDatePremier = new java.sql.Date(premierDate.getTime());	
					java.sql.Date sqlDateEnd = new java.sql.Date(offDate.getTime());

					if (comboBoxGenres.getSelectedItem() instanceof Genre)
					{
						genre = (Genre)comboBoxGenres.getSelectedItem();
					}
					if (comboBoxDirectors.getSelectedItem() instanceof Director)
					{
						director = (Director)comboBoxDirectors.getSelectedItem();
					}

					movie.setTitle(tfTitel.getText());
					movie.setOriginalTitle(tfOriginalTitel.getText());
					movie.setReleaseDate(sqlDatePremier);
					movie.setTimeEnd(sqlDateEnd);
					movie.setLength(playingTime);
					movie.setDirector(director);
					movie.setGenre(genre);
					movie.setCast(cast);
					movie.setIs3D(tglbtnNewToggleButton.isSelected());

					areChangesMade = true;
					latch.countDown();
				} 
				catch (ParseException e1)
				{
					e1.printStackTrace();
				}
			}
		});
		btnCreateMovie.setBackground(Color.GREEN);
		btnCreateMovie.setBounds(579, 236, 100, 23);
		panel.add(btnCreateMovie);

		if(movie.getInstructedBy() == null)
		{	
			lblOpretFilm = new JLabel("Opret film");
		}
		else
		{
			lblOpretFilm = new JLabel("Rediger film");	
		}
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

		if (movie.getCast() != null)
		{
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

		comboBoxGenres = new JComboBox(MovieController.genres.toArray());
		comboBoxGenres.setBounds(112, 209, 142, 20);
		comboBoxGenres.setSelectedItem(movie.getGenre());
		panel.add(comboBoxGenres);

		comboBoxDirectors = new JComboBox(MovieController.directors.toArray());
		comboBoxDirectors.setBounds(112, 116, 142, 20);
		comboBoxDirectors.setSelectedItem(movie.getInstructedBy());
		panel.add(comboBoxDirectors);

		comboBoxActors = new JComboBox(MovieController.actors.toArray());
		comboBoxActors.addActionListener(new ActionListener()
		{
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
		btnAddDirector.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				final CreateDirector createDirector = new CreateDirector();
				createDirector.setVisible(true);

				createDirector.addComponentListener(new ComponentListener()
				{
					@Override
					public void componentHidden(ComponentEvent arg0)
					{
						Director director = createDirector.getDirector();
						MovieController.directors.add(director);
						MovieController.newDirectors.add(director);
						createDirector.dispose();
						comboBoxDirectors.addItem(director);
						comboBoxDirectors.setSelectedItem(director);
					}
					@Override
					public void componentMoved(ComponentEvent arg0)
					{	}

					@Override
					public void componentResized(ComponentEvent arg0)
					{	}

					@Override
					public void componentShown(ComponentEvent arg0)
					{	}
				});
			}
		});

		btnAddDirector.setBounds(549, 83, 130, 23);
		panel.add(btnAddDirector);

		btnAddGenre = new JButton("Opret genre");
		btnAddGenre.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String genreName = JOptionPane.showInputDialog(CreateMovie.this, "Genretekst" , "Opret ny genre", JOptionPane.DEFAULT_OPTION);
				if (genreName != null)
				{
					Genre newGenre = new Genre(genreName);
					MovieController.genres.add(newGenre);
					MovieController.newGenres.add(newGenre);
					comboBoxGenres.addItem(newGenre);
					comboBoxGenres.setSelectedItem(newGenre);
				}
			}
		});
		btnAddGenre.setBounds(549, 115, 130, 23);
		panel.add(btnAddGenre);
	}
	public Movie getMovie()
	{
		return movie;
	}
	public boolean areChangesMade()
	{
		return areChangesMade;
	}
	public void setCastMap()
	{

	}
}
