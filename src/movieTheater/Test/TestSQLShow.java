package movieTheater.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import movieTheater.Movie.Actor;
import movieTheater.Movie.Cast;
import movieTheater.Movie.Director;
import movieTheater.Movie.Genre;
import movieTheater.Movie.Movie;
import movieTheater.Movie.Rating;
import movieTheater.SQL.SQLShowLoad;
import movieTheater.SQL.SQLShowSave;
import movieTheater.Show.HallBooking;
import movieTheater.Show.Seat;
import movieTheater.Show.Show;

public class TestSQLShow{

	SQLShowLoad showLoad;
	Show show;
	ArrayList<Show> shows;
	SQLShowSave showSave;

	public TestSQLShow()
	{
		showLoad = new SQLShowLoad();
		showSave = new SQLShowSave();
	}


	public void testShowLoad(int i)
	{
		try
		{
			shows = showLoad.loadShow(i);	

			for(Show currentShow: shows)
			{
				System.out.println(currentShow.getHallBooking().getHalleNo() + " " + currentShow.getMovie().getMovieID() + " " + currentShow.getShowID() + 
						" " + currentShow.getHallBooking().getStart() + " " + currentShow.getHallBooking().getEndTime()+"  "+currentShow.getPriceCategory());	
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void testShowSave()
	{
		Actor actor1 = new Actor("Test1","Efternavn1",1,"Han er betegnet som verdens største skuespiller",1);
		Actor actor2 = new Actor("Test2","Efternavn2",2,"Hun er betegnet som verdens største skuespiller",2);
		
		HashMap<Actor, String> actors = new HashMap<Actor, String>();
		
		actors.put(actor1, "Mande rolle");
		actors.put(actor2, "Dame rolle");
		
		Cast cast = new Cast(actors);
			Genre genre = null;	
		ArrayList<Rating> ratings = new ArrayList<Rating>();
		
		Director director= new Director("FornavnDirector","LastNameDirector",1, "Stor instruktør",1);
		
		Date dateS = new Date(112,10,30);
		Date dateE = new Date(112,11,30);
		
		Movie movie = new Movie(1,"TestMovie",director,400,genre,dateS,dateE, "Orginal titel3", true,cast,ratings);
		
		ArrayList<ArrayList<Seat>> seatBooking = new ArrayList<ArrayList<Seat>>();
		Timestamp startT = new Timestamp(2012-1900,9,26,1,1,1,1);
		Timestamp startE = new Timestamp(2012-1900,9,26,1,1,1,1);
		System.out.println(startT);
		HallBooking hallbooking = new HallBooking(1,seatBooking,startT,startE);
		
		Show show = new Show(movie,hallbooking, 1.5);
		showSave.createShow(show);
	}

	// String/int generator til brugerdata. Ascii == true til strings, og ascii == false til int. Huske at parse! 
	protected static String Generator(int length, boolean ascii){
		String available = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";; 
		String pass = "";
		int size;
		if(ascii)
			size = 62;
		else
			size = 10;			//Integer.parseInt(Generator(1, false))

		Random r = new Random();
		for(int i = 0; i < length; i++){
			int temp = r.nextInt(size);
			pass = pass + available.substring(temp, temp+1);			
		}
		return pass;
	}
}