package movieTheater.SQL;


import java.sql.SQLException;

import movieTheater.Persons.Costumer;

public class SQLCustomerEdit extends SQL {
	private static final String updateCustomer = "UPDATE Customers SEt fName = ? lName = ?, road = ?, houseNo = ?, postCode = ?, phone = ?, username = ?, pW = ?";
	
	public SQLCustomerEdit()
	{
		statement = null;
		connection = null;
	}
	
   
	public int createCustomer(Costumer customer) throws SQLException 
	{
		openConnection();
		preparedStatement = connection.prepareStatement(updateCustomer); // create statement object
       int rows=0;
       try
       {
	   	   preparedStatement.setString(1, customer.getfName());
	   	   preparedStatement.setString(2, customer.getlName());
	   	   preparedStatement.setString(3, customer.getRoad());
	   	   preparedStatement.setString(4, customer.getHouseNo());  
	   	   preparedStatement.setInt(5, customer.getPostCode());
	   	   preparedStatement.setInt(6, customer.getPhone());
	   	   preparedStatement.setString(7, customer.getUserName());
	   	   preparedStatement.setString(8,customer.getPW());
	      
	   	   rows = preparedStatement.executeUpdate();                     
        
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
       return rows;
	}  
}