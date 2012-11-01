package movieTheater.Test;

import java.sql.Date;
import java.util.ArrayList;

import movieTheater.SQL.SQLShowLoad;
import movieTheater.main.Show;
import movieTheater.main.ShowController;

public class SQLShowLoadTest {
	
	public void test(String name, Date dato){
		SQLShowLoad load = new SQLShowLoad();
		ShowController con = new ShowController();
		con.getShows(name, dato);
		//try
		//{
			//ArrayList<Show> shows = load.loadShowsByDateAndTitle(dato, name);
			//for(int i = 0; i < shows.size(); i++){
				//HashMapshows.get(i).getHallBooking().getAvailableSeats();
			//}
		//}catch(Exception e){
			//e.printStackTrace();
		//}
	}
}
