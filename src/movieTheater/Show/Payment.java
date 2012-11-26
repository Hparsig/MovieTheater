package movieTheater.Show;

import java.sql.Timestamp;

public class Payment 
{
	private double amount;
	private int paymentMethod;
	private int paymentID;
	private Timestamp date;
	

	/**
	 *  
	 * @param amount
	 * @param paymentMethod
	 */
	public Payment(double amount, int paymentMethod)
	{
		this.amount = amount;
		this.paymentMethod = paymentMethod;
	}
	
	public Payment(int paymentID, double amount, int paymentMethod, Timestamp date)
	{
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.paymentID = paymentID;
		this.date = date;
	}

	
	public double getAmount()
	{
		return amount;
	}
	
	public int getPaymentMethod()
	{
		return paymentMethod;
	}
	public int getPaymentID()
	{
		return paymentID;
	}
	public Timestamp getDate()
	{
		return date;
	}

}

