package movieTheater.main;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


import movieTheater.GUI.AvaliableSeats;
import movieTheater.GUI.CreateShow;
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
	private Show show;
	private CreateShow createShow;
	

	public ShowController()
	{
		shows = new ArrayList<Show>();
		showLoad = new SQLShowLoad();
		showSave = new SQLShowSave();

	}
	/**
	 * @author Brian
	 */
	public void setShow() {
		//System.out.println("setshow()");
		// TODO Auto-generated method stub
		show = new Show();
		createShow = new CreateShow(show);
		createShow.setVisible(true);
		
		try
		{
			createShow.latch.await();
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * @author Jesper
	 * @param SearchShow searchShow
	 * show the window searchShow
	 */
	public Show showSearchShow()
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
		Show selectedShow;
		if(selected!=-1)
		{
			selectedShow = shows.get(selected);
		}
		else 
		{
			selectedShow = null;
		}
		
		return selectedShow;
		
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
		catch(Exception e)
		{
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
