package movieTheater.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import movieTheater.GUI.AvaliableSeats;
import movieTheater.GUI.Pay;
import movieTheater.Show.Booking;
import movieTheater.Show.Seat;
import movieTheater.Show.Show;


public class BookingController 
{
	public static Map<Integer,ArrayList<Seat>> av;
	public static Map<Seat,Integer> bookings;
	public static double amount;
	
	public BookingController()
	{
		bookings =  new HashMap<Seat,Integer>();
	}
	
	public void showNewBookings(Show show)
	{
		//Create the hashMap
		av = show.getHallBooking().getAvailableSeats();
		
		//open the new window avaliable seats
		AvaliableSeats avaliableSeats = new AvaliableSeats();
		avaliableSeats.setVisible(true);
		while(avaliableSeats.getClose()==0)
		{
			int row = avaliableSeats.getRow()-1;
			int seat = avaliableSeats.getSeat();
			int ok = -1; //-1 = seat and row combination doesent excist
			
			if(av.containsKey(row)) 
			{
				for(int i=0; i < av.get(row).size(); i++)
				{
					if(av.get(row).get(i).getSeatNo()==seat)
					{
						ok = 0; 
						av.get(row).get(i).setReservation();
						avaliableSeats.addOrders();
						av = show.getHallBooking().getAvailableSeats();
						avaliableSeats.setSeat();
						bookings.put(av.get(row).get(i), row);
						
					}
				}
			}
			//Write error to the screen
			if(ok!=0 && avaliableSeats.getClose()==0)
			{
				avaliableSeats.showError();
			}
		}
		try
		{
			avaliableSeats.latch.await();
		} 
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int close = avaliableSeats.getClose();
		avaliableSeats.dispose();
		
		if(close==1) //open payment
		{
			ShowPayment(show);
		}
	}
	
	public void ShowPayment(Show show)
	{
		ArrayList<Seat> seats = new ArrayList<Seat>();
		for (Map.Entry<Seat, Integer> entry : bookings.entrySet())
		{
			seats.add(entry.getKey());
		}
		
		Booking currentBooking = new Booking(show,seats,MainController.loggedOn);
		amount = currentBooking.getPrice();
		Pay payWindow  = new Pay();
		payWindow.setVisible(true);
		try
		{
			payWindow.latch.await();
		} 
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int paymentMethod = payWindow.getPaymentMethode();
		switch(paymentMethod)
		{
//			case CASH:
//			{
//				
//			}
//		
		}
	}

	
	
}
