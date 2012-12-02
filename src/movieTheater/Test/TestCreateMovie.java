package movieTheater.Test;


import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import movieTheater.Movie.Actor;
import movieTheater.Movie.Cast;
import movieTheater.Movie.Director;
import movieTheater.Movie.Genre;
import movieTheater.Movie.Movie;
import movieTheater.Movie.Rating;
import movieTheater.SQL.SQLMovieSave;

public class TestCreateMovie {

	public static void main(String[] args) {	
		
		
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
		
		SQLMovieSave movieSave = new SQLMovieSave();
		movieSave.saveMovie(movie);
		//movieSave.saveCastList(movie);
	}
}
