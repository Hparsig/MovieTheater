package movieTheater.Test;

import movieTheater.SQL.*;
import movieTheater.Persons.*;


public class CustomerSave {

	public static void main(String[] args) {	
		SQLCustomerSave save = new SQLCustomerSave();
		
		Costumer customer = new Costumer("Kunde fornavn", "Kune efternavn", 98899889, "Vej", "numme", 6950, "Ringkøbing", "user2", "123" );
		try{
		save.createCustomer(customer);
		}
		catch(Exception e){
			e.printStackTrace();
		}

		
		
		
		
	}
}
