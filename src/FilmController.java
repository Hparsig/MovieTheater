import java.util.ArrayList;
import java.util.Date;


public class FilmController {

	private ArrayList<Film> films;
	
	public ArrayList<Film> getAvailableFilms(Date timeStart, Date timeEnd)
	{
		ArrayList<Film> availableFilms = new ArrayList<Film>();
		
		for(Film currentFilm: films)
		{
			if(timeStart.after(currentFilm.getReleaseDate()) && timeEnd.before(currentFilm.getTimeEnd()))
			{
				availableFilms.add(currentFilm);
			}
		}
		return availableFilms;
	}
}
