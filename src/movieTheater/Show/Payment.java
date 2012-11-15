package movieTheater.Show;

import java.sql.Timestamp;

import movieTheater.Persons.Costumer;
import movieTheater.Persons.Employee;

public class Payment 
{
	private double amount;
	private int paymentMethod;
	private int paymentID;
	private Timestamp date;
	
//	/**
//	 * This constructor is used for non-registered costumers paying cash.
//	 * @param amount
//	 */
//	public Payment(double amount)
//	{
//		this(amount, null, 0);
//	}
	/**
	 * This constructor is used for non-registered costumers paying by creditcard. 
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
//	/**
//	 * This constructor is used for registered costumers paying cash. 
//	 * @param amount
//	 * @param costumer
//	 */
//	public Payment(double amount, Costumer costumer)
//	{
//		this(amount, costumer, 0);
//	}
//	/**
//	 * this Constructor is used for registered costumers paying by creditcard
//	 * @param amount
//	 * @param costumer
//	 * @param paymentMethod
//	 */
//	public Payment(double amount, int paymentMethod)
//	{
//		this.amount = amount;
//		this.paymentMethod = paymentMethod;
//	}
	
	public double getAmount()
	{
		return amount;
	}
	
	public int getPaymentMethod()
	{
		return paymentMethod;
	}

}

