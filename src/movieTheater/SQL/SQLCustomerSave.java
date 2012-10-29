package movieTheater.SQL;


import java.sql.SQLException;

public class SQLCustomerSave extends SQL {
	private static final String createCustomer = "insert into Customers(custNo, fName, lname, street, houseNo, postCode, phone, username, password) values(?,?,?,?,?,?,?,?,?)";
	
	public SQLCustomerSave()
	{
		statement = null;
		connection = null;
	}
	
   
	public int createCustomer(String fname, String lname, String street, int houseNo, int postCode, String phone, String username, String password) throws SQLException 
	{
		openConnection();
		preparedStatement = connection.prepareStatement(createCustomer); // create statement object
       int rows=0;
       try
       {
	   	   preparedStatement.setString(1, fname);
	   	   preparedStatement.setString(2, lname);
	   	   preparedStatement.setString(3, street);
	   	   preparedStatement.setInt(4, houseNo);  
	   	   preparedStatement.setInt(5, postCode);
	   	   preparedStatement.setString(6, phone);
	   	   preparedStatement.setString(7, username);
	   	   preparedStatement.setString(8, password);
	      
	   	   rows = preparedStatement.executeUpdate();                     
        
       }
       catch (Exception e)
       {
    	   System.out.println("An error occured while saving user information"); //boundary TODO fix
       }
       finally
       {  
    	   closeConnection();      
       } 
       return rows;
	}  
}