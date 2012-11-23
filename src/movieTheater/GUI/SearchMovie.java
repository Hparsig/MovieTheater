package movieTheater.GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import movieTheater.Movie.Movie;
import movieTheater.main.MovieController;

@SuppressWarnings("serial")
public class SearchMovie extends JFrame implements WindowListener
{

	private JPanel contentPane;
	private List movieList;
	private JTextField txtTitle;
	private JTextField txtOrgTitle;
	private JTextField txtActorFirstName;
	private JTextField txtDirectorFirstName;
	private String title;
	private String orgTitle;
	private String actorName;
	private String director;
	private ArrayList<Movie> movies;
	private Movie movie;
	public final CountDownLatch latch = new CountDownLatch(1); //venter på brugerens input. 

	/**
	 * Create the frame.
	 */
	public SearchMovie(final MovieController movieController) 
	{
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);
		setBounds(100, 100, 522, 359);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);


		movieList = new List();
		movieList.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				int choosen = movieList.getSelectedIndex();
				movie = movies.get(choosen); 
				latch.countDown();
			}
		});
		movieList.setBounds(275, 13, 207, 280);
		panel.add(movieList);

		txtTitle = new JTextField();
		txtTitle.setBounds(153, 85, 116, 22);
		panel.add(txtTitle);
		txtTitle.setColumns(10);

		txtOrgTitle = new JTextField();
		txtOrgTitle.setColumns(10);
		txtOrgTitle.setBounds(153, 120, 116, 22);
		panel.add(txtOrgTitle);
//
		txtActorFirstName = new JTextField();
//		txtActorFirstName.setColumns(10);
//		txtActorFirstName.setBounds(153, 155, 116, 22);
//		panel.add(txtActorFirstName);
//
//
		txtDirectorFirstName = new JTextField();
//		txtDirectorFirstName.setColumns(10);
//		txtDirectorFirstName.setBounds(153, 190, 116, 22);
//		panel.add(txtDirectorFirstName);
//
		JLabel lblFornavn = new JLabel("Titel");
		lblFornavn.setBounds(12, 85, 56, 16);
		panel.add(lblFornavn);

		JLabel lblEfternavn = new JLabel("Org. titel");
		lblEfternavn.setBounds(12, 120, 56, 16);
		panel.add(lblEfternavn);

//		JLabel lblBrugernavn = new JLabel("Skuespiller fornavn");
//		lblBrugernavn.setBounds(12, 155, 70, 16);
//		panel.add(lblBrugernavn);
//
//		JLabel lblPassword = new JLabel("Instruktør fornavn");
//		lblPassword.setBounds(12, 190, 129, 16);
//		panel.add(lblPassword);

		JButton btnSearch = new JButton("S\u00F8g");
		btnSearch.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{

				try
				{
					movieList.removeAll();
					title = txtTitle.getText();
					orgTitle = txtOrgTitle.getText();
					actorName = txtActorFirstName.getText();
					director =  txtDirectorFirstName.getText();
					movies = movieController.searchMovie(title, orgTitle, actorName, director);	

					for(Movie curentMovie: movies)
					{
						movieList.add(curentMovie.toString());
					}	
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		btnSearch.setBounds(172, 225, 97, 25);
		panel.add(btnSearch);

		JButton btnAbort = new JButton("Tilbage");
		btnAbort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				movie = null;
				SearchMovie.this.dispose();
				latch.countDown();
			}
		});
		btnAbort.setBounds(0, 268, 97, 25);
		panel.add(btnAbort);

		JLabel lblSearchMovie = new JLabel("S\u00F8g film");
		lblSearchMovie.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSearchMovie.setBounds(12, 14, 172, 32);
		panel.add(lblSearchMovie);
	}

	public Movie getMovie()
	{
		return movie;
	}

	@Override
	public void windowActivated(WindowEvent e)
	{
	}

	@Override
	public void windowClosed(WindowEvent e)
	{
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		movie = null;
		SearchMovie.this.dispose();		
		latch.countDown();
	}

	@Override
	public void windowDeactivated(WindowEvent e)
	{
	}

	@Override
	public void windowDeiconified(WindowEvent e)
	{
	}

	@Override
	public void windowIconified(WindowEvent e)
	{
	}

	@Override
	public void windowOpened(WindowEvent e)
	{
	}
	
}
