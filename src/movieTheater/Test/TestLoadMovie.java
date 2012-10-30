package movieTheater.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;


import movieTheater.Movie.Movie;
import movieTheater.SQL.SQLMovieLoad;

public class TestLoadMovie {

	public static void main(String[] args) {	
		SQLMovieLoad movieLoad = new SQLMovieLoad();
		
		try {
			ArrayList<Movie> movie = movieLoad.LoadMovie("Taken 2");
			System.out.println(movie.get(0).getLength());		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	
	}

}
