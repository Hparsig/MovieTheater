package movieTheater.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import movieTheater.GUI.AvaliableSeats;
import movieTheater.GUI.LoadBookingW;
import movieTheater.GUI.Pay;
import movieTheater.Persons.Costumer;
import movieTheater.SQL.SQLBookingLoad;
import movieTheater.SQL.SQLBookingSave;
import movieTheater.Show.Booking;
import movieTheater.Show.Payment;
import movieTheater.Show.Seat;
import movieTheater.Show.Show;


public class BookingController 
{
	public static Map<Integer,ArrayList<Seat>> av;
	public static Map<Seat,Integer> bookings;
	public static double amount;
	private SQLBookingSave saveBooking;
	private SQLBookingLoad loadBooking;
	
	private Booking currentBooking;
	
	public BookingController()
	{
		bookings =  new HashMap<Seat,Integer>();
		saveBooking = new SQLBookingSave();
		loadBooking = new SQLBookingLoad();
	}
	/**
	 * @author Jesper
	 * @param show
	 * show the window avaliableseats
	 */
	public void showNewBookings(Show show, Costumer costumer)
	{

		//create new booking object
		int bookingID = saveBooking.createNewBooking(new Booking(show,null,costumer,MainController.loggedOn,false)); 		
		//load the new booking
		currentBooking = loadBooking.getBooking(bookingID);
		
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
						av.get(row).get(i).setReservation(); //sætter sædet til reserveret
						bookings.put(av.get(row).get(i), row); //puttet i ordre mappet
						avaliableSeats.addOrders(); //adder ordren til skærmen
						saveBooking.saveSeatBookings(row, av.get(row).get(i), currentBooking); //gemmer bookingen til databasen
						av = show.getHallBooking().getAvailableSeats(); //henter en ny lise med sæder
						avaliableSeats.setSeat(); //udskriver den nye lise til skærmen
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
			//adding the selected seats to the booking 
			currentBooking.setSeats(bookings);
			ShowPayment();
		}
		else //the user cancel the booking
		{
			saveBooking.delteBooking(currentBooking);
		}
	}
	
	/**
	 * @author Jesper
	 * @param show
	 * show the payment window
	 */
	public void ShowPayment()
	{
			
		//get the price from the booking objct
		amount = currentBooking.getPrice();
		
		//open the pay window
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
		
		Payment payment;
		int paymentMethod = payWindow.getPaymentMethode();
		switch(paymentMethod)
		{
			case -1://cancel the booking and payment
			{
				saveBooking.delteBooking(currentBooking);
				break;
			}
			case 0: //pay with cash
			{
				payment = new Payment(amount,0);
				currentBooking.setSeatsPayed();
				currentBooking.setPayed(payment);
				currentBooking.pickedUp();
				saveBooking.updateBooking(currentBooking);
				break;
			}
			case 1: //pay with creditcard
			{
				payment = new Payment(amount,1);
				currentBooking.setSeatsPayed();
				currentBooking.pickedUp();
				currentBooking.setPayed(payment);
				saveBooking.updateBooking(currentBooking);
				break;
			}
		}
		payWindow.dispose();
	}
	
	
	/**
	 * @author Jesper
	 * show the window loadOrder
	 */
	public void showLoadOrder(Costumer costumer)
	{
		LoadBookingW loadBookingW = new LoadBookingW();
		loadBookingW.setVisible(true);
		ArrayList<Booking> currentbookings =null;
		
		try
		{
			currentbookings=loadBooking.getBookings(costumer);
			//writes the bookings to the screen
			for(int i=0; i < currentbookings.size(); i++){
					loadBookingW.addBookings(currentbookings.get(i).toString());
			}
		}
		catch(Exception e)
		{
				loadBookingW.showError();
		}
		
		try
		{
			loadBookingW.latch.await();
		} 
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//get the selected item and close the window
		int selected = loadBookingW.getSelected();
		loadBookingW.dispose();
		if(selected!=-1)
		{
			currentBooking = currentbookings.get(selected);
			if(currentBooking.getPayment()==null)//are not payd
			{
				bookings = currentBooking.getSeats();
				ShowPayment();
			}
			else //the seats are already payd
			{
				//TODO udskrift af billet. 
			}
		}
	}
	
}
