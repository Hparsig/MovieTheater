package movieTheater.main;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import movieTheater.GUI.CreateShow;
import movieTheater.GUI.SearchShow;
import movieTheater.SQL.SQLShowLoad;
import movieTheater.SQL.SQLShowSave;
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
		if (createShow.areChangesMade())
		{
			createShow();
		}		
		createShow.dispose();
	}
	
	/**
	 * @author Jesper
	 * @param SearchShow searchShow
	 * show the window searchShow
	 */
	public Show searchShow()
	{
		SearchShow searchShow = new SearchShow();
		searchShow.setVisible(true);
		
		while(searchShow.getClose()==0)
		{	
			//Get the date and title from the GUI
			String title = searchShow.getTitel();
			Date date = searchShow.getSqlDate();
			
			boolean dateOk = true;
			
			if(date!=null)
			{
				//convert the date
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);

				//creates the date today
				Calendar currentcal = Calendar.getInstance();
				currentcal.set(Calendar.HOUR, 0);
				currentcal.set(Calendar.MINUTE, 0);
				currentcal.set(Calendar.SECOND, 0);
				currentcal.set(Calendar.MILLISECOND, 0);



				if(cal.before(currentcal))
				{
					dateOk = false;
				} 
			}
			
			if(dateOk==false)
			{
				searchShow.showErrorWrongDate();
			}
			else
			{
				//Genereates the show array
				getShows(title,date);
			}
		
			if(shows.size()==0)
			{
				searchShow.addShowList("Ingen shows opfylder kriterierne");
			}
			else
			{
				//writes the shows to the screen
				for(int i=0; i < shows.size(); i++){
					searchShow.addShowList(shows.get(i).toString());
				}
			}
		}		
		try
		{
			searchShow.latch.await();
		} 
		catch (InterruptedException e)
		{
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
	
	
	/**
	 * @author Jesper
	 * @param String titel - titel of the movie
	 * @param Date date - date of the show
	 * @return void  
	 */
	private void getShows(String titel, Date date)
	{
		shows.clear();
		try
		{
			shows = showLoad.loadShows(date, titel);			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
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
	public void createShow() {
		// TODO Auto-generated method stub
		createShow.getShow();
		showSave.createShow(show);
		
	}
	
		
	
	
}
