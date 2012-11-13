package movieTheater.SQL;


import java.sql.SQLException;

import movieTheater.Persons.Costumer;

public class SQLCustomerSave extends SQL {
	private static final String createCustomer = "INSERT INTO Costumers(fName, lName, road, houseNo, postCode, phone, username, pW) VALUES(?,?,?,?,?,?,?,?)";
	
	public SQLCustomerSave()
	{
		statement = null;
		connection = null;
	}
	
   
	public void createCustomer(Costumer customer)
	{
		openConnection();
		
       try
       {
    	   preparedStatement = connection.prepareStatement(createCustomer); // create statement object
    	   
	   	   preparedStatement.setString(1, customer.getfName());
	   	   preparedStatement.setString(2, customer.getlName());
	   	   preparedStatement.setString(3, customer.getRoad());
	   	   preparedStatement.setString(4, customer.getHouseNo());  
	   	   preparedStatement.setInt(5, customer.getPostCode());
	   	   preparedStatement.setInt(6, customer.getPhone());
	   	   preparedStatement.setString(7, customer.getUserName());
	   	   preparedStatement.setString(8,customer.getPW());
	      
	   	   preparedStatement.executeUpdate();                     
        
       }
       catch (Exception e)
       {
    	   System.out.println("An error occured while saving user information"); //boundary TODO fix
    	   e.printStackTrace();
       }
       finally
       {  
    	   closeConnectionSave();      
       } 
	}  
}