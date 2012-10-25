package movieTheater.main;

import java.util.ArrayList;
import java.util.Date;


public class CinemaController {


	private ArrayList<Show> shows;
	private final MovieController movieController;
	private final HallController hallController;
	
	public CinemaController()
	{
		shows = new ArrayList<Show>();
		movieController = new MovieController();
		hallController = new HallController();
	}
	public void setShow(Date timeStart, Date timeEnd)
	{
		int choise = 0;
		//GUI brugerinput omkring tid start og slut
		ArrayList<Movie> availableFilms = movieController.getAvailableMovies(timeStart, timeEnd);
		// choise = GUI brugervalg
		Movie filmChoosen = availableFilms.get(choise);
		ArrayList<Hall> availableHalls = hallController.getAvailableHalls(movieLength, timeStart, timeEnd);
		//choise = GUI brugervalg
		Hall hallChoosen = availableHalls.get(choise);
		shows.add(new Show(movieChoosen, hallChoosen));
	}
}
