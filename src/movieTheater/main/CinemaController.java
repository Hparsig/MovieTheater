package movieTheater.main;

import java.util.ArrayList;
import java.util.Date;


public class CinemaController {


	private ArrayList<Show> shows;
	private final FilmController filmController;
	private final HallController hallController;
	
	public CinemaController()
	{
		shows = new ArrayList<Show>();
		filmController = new FilmController();
		hallController = new HallController();
	}
	public void setShow(Date timeStart, Date timeEnd)
	{
		int choise = 0;
		//GUI brugerinput omkring tid start og slut
		ArrayList<Film> availableFilms = filmController.getAvailableFilms(timeStart, timeEnd);
		// choise = GUI brugervalg
		Film filmChoosen = availableFilms.get(choise);
		ArrayList<Hall> availableHalls = hallController.getAvailableHalls(filmLength, timeStart, timeEnd);
		//choise = GUI brugervalg
		Hall hallChoosen = availableHalls.get(choise);
		shows.add(new Show(filmChoosen, hallChoosen));
	}
}
