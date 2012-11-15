package movieTheater.main;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import movieTheater.GUI.AvaliableSeats;
import movieTheater.GUI.LoadBookingW;
import movieTheater.GUI.Pay;
import movieTheater.GUI.SearchShow;
import movieTheater.Persons.Costumer;
import movieTheater.Persons.Employee;
import movieTheater.Persons.SalesPerson;
import movieTheater.SQL.SQLBookingLoad;
import movieTheater.SQL.SQLBookingSave;
import movieTheater.SQL.SQLCustomerLoad;
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
	private SQLCustomerLoad loadCustomer;
	private Booking currentBooking;
	
	public BookingController()
	{
		bookings =  new HashMap<Seat,Integer>();
		saveBooking = new SQLBookingSave();
		loadBooking = new SQLBookingLoad();
		loadCustomer = new SQLCustomerLoad();
	}
	/**
	 * @author Jesper
	 * @param show
	 * show the window avaliableseats
	 */
	public void showNewBookings(Show show)
	{
		Employee employee = new SalesPerson("Name", "lName", 11, "road", "houseNo", 6950, "city", "userName", "PW", 1);
		//create new booking object
		saveBooking.createNewBooking(new Booking(show,null,null,employee,false)); //TODO hvis kunden er medlem skal han smides med ind + medarbejderen
		
		//load the new booking
		currentBooking = loadBooking.getNewestBooking();
		
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
						av.get(row).get(i).setReservation(); //s�tter s�det til reserveret
						bookings.put(av.get(row).get(i), row); //puttet i ordre mappet
						avaliableSeats.addOrders(); //adder ordren til sk�rmen
						saveBooking.saveSeatBookings(row, av.get(row).get(i), currentBooking); //gemmer bookingen til databasen
						av = show.getHallBooking().getAvailableSeats(); //henter en ny lise med s�der
						avaliableSeats.setSeat(); //udskriver den nye lise til sk�rmen
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
	public void ShowPayment(Show show)
	{
		Employee employee = new SalesPerson("fName", "Name", 123, "road", " houseNo", 6950, "city", "userName", "PW", 1); //TODO laves s� det er den der er logget ind der bliver hentet

		//adding the selected seats to the booking 
		currentBooking.setSeats(bookings);
		
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
	public void showLoadOrder()
	{
		LoadBookingW loadBookingW = new LoadBookingW();
		loadBookingW.setVisible(true);
		
		while(loadBookingW.getClose()==1)
		{	
			//Get the user data from the GUI
			String phone = loadBookingW.getPhone();
			String name = loadBookingW.getName();
			String lName = loadBookingW.getLName();
			int tlf;
			try
			{
				tlf = Integer.parseInt(phone);
				
			}
			catch(java.lang.NumberFormatException e)
			{
				tlf = 0;
			}
			try
			{
				Costumer costumer = loadCustomer.loadCostumer(tlf, name, lName).get(0);
				ArrayList<Booking> bookings=loadBooking.getBookings(costumer);
				//writes the shows to the screen
				for(int i=0; i < bookings.size(); i++){
					loadBookingW.addBookings(bookings.get(i).toString());
				}
			}
			catch( java.lang.IndexOutOfBoundsException e)
			{
				
			}

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

	}
	
}
