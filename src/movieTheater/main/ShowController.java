package movieTheater.main;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


import movieTheater.GUI.AvaliableSeats;
import movieTheater.GUI.SearchShow;
import movieTheater.Movie.Movie;
import movieTheater.SQL.SQLShowLoad;
import movieTheater.SQL.SQLShowSave;
import movieTheater.Show.Seat;
import movieTheater.Show.Show;

public class ShowController {
	
	private ArrayList<Show> shows;
	private SQLShowLoad showLoad;
	private SQLShowSave showSave;
	

	public ShowController(SQLShowLoad showLoad, SQLShowSave showSave)
	{
		shows = new ArrayList<Show>();
		this.showLoad = showLoad;
		this.showSave = showSave;

	}
	/**
	 * @author Jesper
	 * @param SearchShow searchShow
	 * show the window searchShow
	 */
	public void showSearchShow()
	{
		SearchShow searchShow = new SearchShow();
		searchShow.setVisible(true);
		
		while(searchShow.getClose()==0)
		{	
			//Get the date and title from the GUI
			String title = searchShow.getTitel();
			Date date = searchShow.getSqlDate();
	
			//Genereates the show array
			getShows(title,date);
		
			//writes the shows to the screen
			for(int i=0; i < shows.size(); i++){
				searchShow.addShowList(shows.get(i).toString());
			}
		}		
		try
		{
			searchShow.latch.await();
		} 
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//get the selected item and close the window
		int selected = searchShow.getSelected();
		searchShow.dispose();
		
		if(selected!=-1)
		{
			BookingController bookingCon = new BookingController();//FIXME skal smides i main controller
			bookingCon.showNewBookings(shows.get(selected));
		}
		
	}
	
	
	//Følgende skal måske placeres i et funktionslag??
	/**
	 * @author Jesper
	 * @param String titel - titel of the movie
	 * @param Date date - date of the show
	 * @return ArrayList<Show>  
	 */
	public ArrayList<Show> getShows(String titel, Date date)
	{
		shows.clear();
		try
		{
			shows = showLoad.loadShowsByDateAndTitle(date, titel);			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return shows;
	}
	
	public ArrayList<Show> getShows()
	{
		shows.clear();
		try
		{
			showLoad.loadAllShows();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return shows;
	}
	
}
