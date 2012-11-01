package movieTheater.main;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


import movieTheater.Movie.Movie;
import movieTheater.SQL.SQLShowLoad;
import movieTheater.SQL.SQLShowSave;

public class ShowController {
	
	private ArrayList<Show> shows;
	private SQLShowLoad sqlShowLoad;
	private SQLShowSave sqlShowSave;
	

	public ShowController(){
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
			
			for(int i=0; i < shows.size(); i++){
				HashMap<Integer,ArrayList<Integer>> av = shows.get(i).getHallBooking().getAvailableSeats();
				Map map = av;
				Iterator entries = map.entrySet().iterator();
				System.out.println(shows.get(i));
				while (entries.hasNext())
				{
					Map.Entry entry = (Map.Entry) entries.next();
					Integer key = (Integer)entry.getKey();
					ArrayList<Integer> value = (ArrayList<Integer>)entry.getValue();
					System.out.println("Række = " + key + ", Ledige sæder = " + value);
				}
				System.out.println("\n");
			}
//			for (Integer key : av.keySet())
//			{
//				System.out.println(key);
//				
//			}
//			for (Integer value : av.values()) {
//			    System.out.println("Value = " + value);
//			}
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
		//ArrayList<Movie> availableFilms = movieController.getAvailableMovies(timeStart, timeEnd);
		// choise = GUI brugervalg
		//int movieID = availableFilms.get(choise).getID();
		//ArrayList<Hall> availableHalls = hallController.getAvailableHalls(movieLength, timeStart, timeEnd);
		//choise = GUI brugervalg
		//int hallNo = availableHalls.get(choise).getHallNo();
		//sqlShowSave.createShow(hallNo,timeStart,timeEnd,movieID);
	}
	
	
	
}
