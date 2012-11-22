package movieTheater.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import movieTheater.GUI.AvailableSeats;
import movieTheater.GUI.Checkout;
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
	public static double change;
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
		currentBooking = new Booking(show,null,costumer,MainController.loggedOn,false);
		//create new booking object
		int bookingNo = saveBooking.createNewBooking(currentBooking); 		
		currentBooking.setBookingNo(bookingNo);
		
		//Create the hashMap
		av = show.getHallBooking().getAvailableSeats();
		
		//open the new window avaliable seats
		AvailableSeats avaliableSeats = new AvailableSeats();
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
			e.printStackTrace();
		}
		
		
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
				showCheckout(0);
				break;
			}
			case 1: //pay with creditcard
			{
				showCheckout(1);
				break;
			}
		}
		payWindow.dispose();
	}
	
	public void showCheckout(int paymentMethod)
	{
		//open the checkout window
		Checkout checkoutWindow  = new Checkout();
		checkoutWindow.setVisible(true);
		
		while(checkoutWindow.getAmountok()==false)
		{
			try
			{
				checkoutWindow.amountReturn.await();
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}

			String payM = checkoutWindow.getAmount();
			double received = 0;
			if(checkoutWindow.getCancel()!=-1)
			{
				try
				{
					received = Double.parseDouble(payM);
				}
				catch(Exception e)
				{
					checkoutWindow.showError();
				}
			}
			change  = received-amount;

			if(change<0 && checkoutWindow.getCancel()!=-1)
			{
				checkoutWindow.showAmountError();
			}
			else
			{
				checkoutWindow.setChange(change);

				Payment payment = new Payment(amount,paymentMethod);
				currentBooking.setSeatsPayed();
				currentBooking.pickedUp();
				currentBooking.setPayed(payment);
				saveBooking.updateBooking(currentBooking);
				checkoutWindow.setAmountok();
			}
			
		}
		
		try
		{
			checkoutWindow.latch.await();
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		if(checkoutWindow.getCancel()==-1)
		{
			saveBooking.delteBooking(currentBooking);
		}
		checkoutWindow.dispose();
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
