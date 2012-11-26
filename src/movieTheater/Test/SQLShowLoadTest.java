package movieTheater.Test;

import java.sql.Date;
import java.util.ArrayList;

import movieTheater.SQL.SQLShowLoad;
import movieTheater.Show.Show;
import movieTheater.main.ShowController;

public class SQLShowLoadTest {
	
	public void test(String name, Date dato){
		SQLShowLoad load = new SQLShowLoad();
		load.loadShows(null, "sky");
		try
		{
			ArrayList<Show> shows = load.loadShows(null, "sky");
			for(int i = 0; i < shows.size(); i++){
				shows.get(i).getMovie();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
