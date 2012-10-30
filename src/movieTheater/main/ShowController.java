package movieTheater.main;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;


import movieTheater.Movie.Movie;
import movieTheater.SQL.SQLShowLoad;
import movieTheater.SQL.SQLShowSave;

public class ShowController {
	
	private ArrayList<Show> shows;
	private SQLShowLoad sqlShowLoad;
	private SQLShowSave sqlShowSave;
	private MovieController movieController;
	private HallController hallController;

	public ShowController(MovieController movieController, HallController hallController){
		this.movieController = movieController;
		this.hallController = hallController;
		shows = new ArrayList<Show>();
		sqlShowLoad = new SQLShowLoad();
	}
	/**
	 * @author Jesper
	 * @param String titel - titel of the movie
	 * @param Date date - date of the show
	 * @return ArrayList<Show>  
	 */
	public ArrayList<Show> getShows(String titel, Date date)
	{
		try
		{
			shows = sqlShowLoad.loadShowsByDateAndTitle(date, titel);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return shows;
	}
	public ArrayList<Show> getShows()
	{
		try
		{
		sqlShowLoad.loadAllShows();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return shows;
	}
	
	public void setShow(Timestamp timeStart, Timestamp timeEnd)
	{
		int choise = 0;
		//GUI brugerinput omkring tid start og slut
		ArrayList<Movie> availableFilms = movieController.getAvailableMovies(timeStart, timeEnd);
		// choise = GUI brugervalg
		//int movieID = availableFilms.get(choise).getID();
		//ArrayList<Hall> availableHalls = hallController.getAvailableHalls(movieLength, timeStart, timeEnd);
		//choise = GUI brugervalg
		//int hallNo = availableHalls.get(choise).getHallNo();
		//sqlShowSave.createShow(hallNo,timeStart,timeEnd,movieID);
	}
	
	
	
}
