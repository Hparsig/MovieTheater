package movieTheater.main;

import java.util.ArrayList;
import java.util.Date;

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
	}
	
	public ArrayList<Show> getShows(String Titel, Date date)
	{
		
		return shows;
	}
	
	public void setShow(Date timeStart, Date timeEnd)
	{
		int choise = 0;
		//GUI brugerinput omkring tid start og slut
		ArrayList<Movie> availableFilms = movieController.getAvailableMovies(timeStart, timeEnd);
		// choise = GUI brugervalg
		int movieID = availableFilms.get(choise).getID();
		ArrayList<Hall> availableHalls = hallController.getAvailableHalls(movieLength, timeStart, timeEnd);
		//choise = GUI brugervalg
		int hallNo = availableHalls.get(choise).getHallNo();
		sqlShowSave.createShow(hallNo,timeStart,timeEnd,movieID);
	}
	
	
	
}
