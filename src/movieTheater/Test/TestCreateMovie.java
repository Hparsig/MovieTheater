package movieTheater.Test;


import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import movieTheater.SQL.*;
import movieTheater.Movie.*;

public class TestCreateMovie {

	public static void main(String[] args) {	
		
		
		Actor actor1 = new Actor("Test1","Efternavn1",1,"Han er betegnet som verdens største skuespiller",1);
		Actor actor2 = new Actor("Test2","Efternavn2",2,"Hun er betegnet som verdens største skuespiller",2);
		
		HashMap<Actor, String> actors = new HashMap<Actor, String>();
		
		actors.put(actor1, "Mande rolle");
		actors.put(actor2, "Dame rolle");
		
		Cast cast = new Cast(actors);
				
		ArrayList<Rating> ratings = new ArrayList<Rating>();
		
		Director director= new Director("FornavnDirector","LastNameDirector",1, "Stor instruktør",1);
		
		Date dateS = new Date(112,10,30);
		Date dateE = new Date(112,11,30);
		
		//String movieName, Director directedBy, int length, String genre, Date releaseDate, Date timeEnd,
		//String originalName, boolean is3D, Cast cast 	
		
		Movie movie = new Movie("TestMovie",director,400,"action",dateS,dateE, "Orginal titel3", true,cast);
		
		SQLMovieSave movieSave = new SQLMovieSave();
		movieSave.saveMovie(movie);
		//movieSave.saveCastList(movie);
	}
}
