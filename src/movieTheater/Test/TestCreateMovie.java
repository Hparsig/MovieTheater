package movieTheater.Test;


import java.sql.Date;
import java.util.ArrayList;

import movieTheater.SQL.*;
import movieTheater.Movie.*;

public class TestCreateMovie {

	public static void main(String[] args) {	
		
		
		Actor actor1 = new Actor("Test1","Efternavn1",1,"Han er betegnet som verdens største skuespiller",1);
		Actor actor2 = new Actor("Test2","Efternavn2",2,"Hun er betegnet som verdens største skuespiller",2);
		
		ArrayList<Cast> cast = new ArrayList<Cast>();
		cast.add(new Cast(actor1,"mande rolle"));
		cast.add(new Cast(actor2,"dame rolle"));
				
		ArrayList<Rating> ratings = new ArrayList<Rating>();
		
		Director director= new Director("FornavnDirector","LastNameDirector",1, "Stor instruktør",1);
		
		Date dateS = new Date(112,10,30);
		Date dateE = new Date(112,11,30);
		
		 				
		Movie movie = new Movie("TestMovie",director,400,"action",dateS,dateE, "Orginal titel3", true,cast,ratings);
		
		SQLMovieSave movieSave = new SQLMovieSave();
		movieSave.saveMovie(movie);
		//movieSave.saveCastList(movie);
	}
}
