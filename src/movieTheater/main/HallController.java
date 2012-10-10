package movieTheater.main;

import java.util.ArrayList;
import java.util.Date;


public class HallController {
	
	private ArrayList<Hall> halls;
	private int[] seatsPrRowHall1 = {8, 10, 12, 12, 14, 14, 16, 16, 16, 16};
	private int[] seatsPrRowHall2 = {10, 10, 12, 12, 14, 14, 16, 16, 16, 18, 18, 18};
	private int[] seatsPrRowHall3 = {6, 6, 8, 8, 10, 10, 10, 12};

	public HallController()
	{
		halls = new ArrayList<Hall>();
		halls.add(new Hall(1, seatsPrRowHall1));
		halls.add(new Hall(2, seatsPrRowHall2));
		halls.add(new Hall(2, seatsPrRowHall3));
	}
	/**
	 * 
	 * @param filmLength - the actual length of the film
	 * @param timeStart - the start of the time slut for the search
	 * @param timeEnd - the end of the time slut for the search
	 * @return ArrayList<Hall> - a list of refferences to the halls available in the given time slut. Returns null if non available. 
	 */
	public ArrayList<Hall> getAvailableHalls(int filmLength, int timeStart, int timeEnd)
	{
		ArrayList<Hall> availableHalls = new ArrayList<Hall>();
		// løbe igennem arrayet af halls og se om der er ledige sluts. 
		return availableHalls;
	}
// test og mere test
}

