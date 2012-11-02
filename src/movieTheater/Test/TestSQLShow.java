package movieTheater.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import movieTheater.SQL.SQLShowLoad;
import movieTheater.SQL.SQLShowSave;
import movieTheater.Show.Show;

public class TestSQLShow{

	SQLShowLoad showLoad;
	Show show;
	ArrayList<Show> shows;
	SQLShowSave showSave;

	public TestSQLShow()
	{
		showLoad = new SQLShowLoad();
	}

	public void runTest()
	{
		for(int i = 0; i < shows.size(); i++)
			testShowLoad(i);
		for(int i = 0; i < 10; i++)
			testShowSave(i);


	}
	public void testShowLoad(int i)
	{
		try
		{
			shows = showLoad.loadShow(i);	

			for(Show currentShow: shows)
			{
				System.out.println(currentShow.getHallno() + " " + currentShow.getMovieID() + " " + currentShow.getShowID() + 
						" " + currentShow.getTimeE() + " " + currentShow.getTimeS());	
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void testShowSave(int i)
	{
		try {
			showSave.createShow(i, Integer.parseInt(Generator(1, false)), Generator(6, false), Generator(6, false), 0);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
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