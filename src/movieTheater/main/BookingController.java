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
import movieTheater.Show.Ticket;


public class BookingController 
{
	public static Map<Integer,ArrayList<Seat>> av;
	public static Map<Seat,Integer> bookings;
	public static double amount;
	public static double change;
	private SQLBookingSave saveBooking;
	private SQLBookingLoad loadBooking;
	private AvailableSeats avaliableSeats;
	private Show show;
	private Ticket ticket;
	
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
	public void newBooking(Show show, Costumer costumer)
	{
		this.show = show;
		currentBooking = new Booking(show,null,costumer,MainController.loggedOn,false);
		//create new booking object
		int bookingNo = saveBooking.createNewBooking(currentBooking); 		
		currentBooking.setBookingNo(bookingNo);
		
		//Create the hashMap
		av = show.getHallBooking().getAvailableSeats();
		
		//open the new window avaliable seats
		avaliableSeats = new AvailableSeats();
		avaliableSeats.setVisible(true);
		
		while(avaliableSeats.getClose()==0)
		{
			int row = avaliableSeats.getRow()-1;
			int seat = avaliableSeats.getSeat();
			
			boolean succes = false; //= seat and row combination doesent excist
			
			if(av.containsKey(row)) 
			{
				succes = putInMap(row, seat);
			}
			//Write error to the screen
			if(!succes && avaliableSeats.getClose()==0)
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
			saveBooking.deleteBooking(currentBooking);
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
				
		int paymentMethod = payWindow.getPaymentMethod();
		switch(paymentMethod)
		{
			case -1://cancel the booking and payment
			{
				saveBooking.deleteBooking(currentBooking);
				break;
			}
			case 0: //pay with cash
			{
				checkout(0);
				break;
			}
			case 1: //pay with creditcard
			{
				checkout(1);
				break;
			}
		}
		payWindow.dispose();
	}
	
	/**
	 * @author Jesper og Martin
	 * @param int payMethod
	 * Show the checkout window
	 */
	public void checkout(int payMethod)
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

				Payment payment = new Payment(amount,payMethod);//making new paymnet
				currentBooking.setSeatsPayed(); //adding the payment to the booking
				currentBooking.pickedUp(); //set the ticket to picked up
				currentBooking.setPayed(payment); //sets the booking to payd
				saveBooking.updateBooking(currentBooking);
				checkoutWindow.setAmountok();
				ticket = new Ticket(currentBooking);
				System.out.println(ticket.toString());
				checkoutWindow.addTicket(ticket.toString());
				
				
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
			saveBooking.deleteBooking(currentBooking);
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
	/**
	 * @author Jesper
	 * @param row
	 * @param seat
	 * adding the selected seat to the booking hashmap
	 * @return boolean succes
	 */
	private boolean putInMap(int row, int seat)
	{
		boolean succes = false;
		boolean saveOk = false;

			for(int i=0; i < av.get(row).size(); i++)
			{
				if(av.get(row).get(i).getSeatNo()==seat)
				{
					succes = true; 
					av.get(row).get(i).setReservation(); //sætter sædet til reserveret
					saveOk = saveBooking.saveSeatBookings(row, av.get(row).get(i), currentBooking); //gemmer bookingen til databasen
					
					if(saveOk)
					{
						bookings.put(av.get(row).get(i), row); //putter i ordre mappet
						avaliableSeats.addOrders(); //adder ordren til skærmen
					}
					else
					{
						avaliableSeats.alreadyBooked();
					}
					av = show.getHallBooking().getAvailableSeats(); //henter en ny liste med sæder
					avaliableSeats.setSeat(); //udskriver den nye lise til skærmen
				}
			}
		

		return succes;
	}
	
}
