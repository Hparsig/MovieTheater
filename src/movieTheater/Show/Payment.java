package movieTheater.Show;

import movieTheater.Persons.Costumer;
import movieTheater.Persons.Employee;

public class Payment 
{
	private double amount;
	private Costumer costumer;
	private Employee employee;
	private int paymentMethod;
	
//	/**
//	 * This constructor is used for non-registered costumers paying cash.
//	 * @param amount
//	 */
//	public Payment(double amount,Employee employee)
//	{
//		this(amount, null, 0,employee);
//	}
	/**
	 * This constructor is used for non-registered costumers paying by creditcard. 
	 * @param amount
	 * @param paymentMethod
	 */
	public Payment(double amount, int paymentMethod,Employee employee)
	{
		this(amount, null, paymentMethod,employee);
	}
//	/**
//	 * This constructor is used for registered costumers paying cash. 
//	 * @param amount
//	 * @param costumer
//	 */
//	public Payment(double amount, Costumer costumer,Employee employee)
//	{
//		this(amount, costumer, 0,employee);
//	}
	/**
	 * this Constructor is used for registered costumers paying by creditcard
	 * @param amount
	 * @param costumer
	 * @param paymentMethod
	 */
	public Payment(double amount, Costumer costumer, int paymentMethod,Employee employee)
	{
		this.amount = amount;
		this.costumer = costumer;
		this.paymentMethod = paymentMethod;
		this.employee = employee;
	}
	
	public double getAmount()
	{
		return amount;
	}
		
	public Costumer getCostumer()
	{
		return costumer;
	}
	
	public int getPaymentMethod()
	{
		return paymentMethod;
	}
	public Employee getEmployee()
	{
		return employee;
	}
}

