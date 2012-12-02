package movieTheater.Test;

import java.util.ArrayList;

import movieTheater.Movie.Movie;
import movieTheater.SQL.SQLMovieLoad;

public class TestLoadMovie {

	public static void main(String[] args) {	
		SQLMovieLoad movieLoad = new SQLMovieLoad();
		
			ArrayList<Movie> movie = movieLoad.LoadMovie("Taken 2");
			System.out.println(movie.get(0).getLength());		

			
	
	}

}
