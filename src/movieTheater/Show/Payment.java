package movieTheater.Show;

import movieTheater.Persons.Costumer;
import movieTheater.Persons.Employee;

public class Payment 
{
	private double amount;
	private Costumer costumer;
	private Employee employee;
	private int payID;
	
	/**
	 * This constructor is used for non-registered costumers paying cash.
	 * @param amount
	 */
	public Payment(double amount)
	{
		this(amount, null, 0);
	}
	/**
	 * This constructor is used for non-registered costumers paying by creditcard. 
	 * @param amount
	 * @param payID
	 */
	public Payment(double amount, int payID)
	{
		this(amount, null, payID);
	}
	/**
	 * This constructor is used for registered costumers paying cash. 
	 * @param amount
	 * @param costumer
	 */
	public Payment(double amount, Costumer costumer)
	{
		this(amount, costumer, 0);
	}
	/**
	 * this Constructor is used for registered costumers paying by creditcard
	 * @param amount
	 * @param costumer
	 * @param payID
	 */
	public Payment(double amount, Costumer costumer, int payID)
	{
		this.amount = amount;
		this.costumer = costumer;
		this.payID = payID;
	}
	
	public double getAmount()
	{
		return amount;
	}
		
	public Costumer getCostumer()
	{
		return costumer;
	}
	
	public int getPayID()
	{
		return payID;
	}
	
	public Employee getEmployee()
	{
		return employee;
	}
}

