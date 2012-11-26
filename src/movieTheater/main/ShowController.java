package movieTheater.main;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import movieTheater.GUI.CreateShow;
import movieTheater.GUI.SearchShow;
import movieTheater.SQL.SQLShowLoad;
import movieTheater.SQL.SQLShowSave;
import movieTheater.Show.HallBooking;
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
	 * @author Brian og Jesper
	 */
	public void setShow() 
	{
		show = new Show();
		createShow = new CreateShow(show);
		createShow.setVisible(true);

		while(!createShow.getDataOk())
		{
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

				int hall = createShow.getHall();	//get the hall number
				long time = createShow.getTime(); // get the hh:mm in long
				java.util.Date dateT = createShow.getDate(); //get the date

				boolean dateOk = checkDate(dateT.getTime()); //checking the date
				if(dateOk)
				{
					show = createShow.getShow(); //getting the show
					Calendar cal = Calendar.getInstance(); 
					cal.setTimeInMillis((time+dateT.getTime())); //setting up the calendar to the show start
					cal.add(Calendar.HOUR, 1); //adding on our, to get the correct format

					Timestamp start = new Timestamp(cal.getTimeInMillis()); //creating time start timestamp

					cal.add(Calendar.MINUTE, show.getMovie().getLength()); //adding the length of the movie
					Timestamp end = new Timestamp(cal.getTimeInMillis());	//creating the end timetamp

					HallBooking hallBooking = new HallBooking(hall, start, end); //creating the hallbooking object
					show.setHallBooking(hallBooking);	//adding the hallbooking to the show
					createShow.setDataOk(); //setting dataOk = true
					showSave.createShow(show);	//save the show to the database. 
				}
				else
				{
					createShow.showErrorWrongDate();
					createShow.createLatch();
				}
			}
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
				dateOk = checkDate(date.getTime());
			}
			
			if(!dateOk)
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

	
	/**
	 * @author Jesper
	 * @param time
	 * tjekker om den givne dato er før eller efter i dag. Returnere true hvis den er efter
	 * @return boolean dateOk
	 */
	private boolean checkDate(long time)
	{
		boolean dateOk = true;
		//convert the date
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);

		//creates the date today
		Calendar currentcal = Calendar.getInstance();
		currentcal.set(Calendar.HOUR, 0);
		currentcal.set(Calendar.MINUTE, 0);
		currentcal.set(Calendar.SECOND, 0);
		currentcal.set(Calendar.MILLISECOND, 0);
		currentcal.set(Calendar.AM_PM, 0);

		if(cal.before(currentcal))
		{
			dateOk = false;
		} 
		
		return dateOk;
	}
	
	
		
	
	
}
