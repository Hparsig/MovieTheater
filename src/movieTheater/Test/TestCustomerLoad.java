package movieTheater.Test;

import java.util.ArrayList;
import movieTheater.SQL.*;
import movieTheater.Movie.Cast;
import movieTheater.Persons.*;


public class TestCustomerLoad {

	public static void main(String[] args) {	
		SQLCustomerLoad load = new SQLCustomerLoad();
		try{
			ArrayList<Costumer> customers = load.LoadCustomer(1);
			for (int i=0; i < customers.size();i++)
			{
				System.out.println(customers.get(i).getCostumerNo());
				System.out.println(customers.get(i).getfName());
				System.out.println(customers.get(i).getlName());
				System.out.println(customers.get(i).getRoad());
				System.out.println(customers.get(i).getHouseNo());
				System.out.println(customers.get(i).getCostumerNo());
				System.out.println(customers.get(i).getCity());
				System.out.println(customers.get(i).getPhone());
				System.out.println(customers.get(i).getUserName());
				System.out.println(customers.get(i).getPW());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		
		
		
		
	}
}
