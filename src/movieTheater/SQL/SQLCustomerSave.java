package movieTheater.SQL;


import movieTheater.Persons.Costumer;

public class SQLCustomerSave extends SQL {
	private static final String createCustomer = "INSERT INTO Costumers(fName, lName, road, houseNo, postCode, phone, username, pW) VALUES(?,?,?,?,?,?,?,?)";
	private static final String updateCustomer = "UPDATE Costumers SET fName = ?, lName = ?, road = ?, houseNo = ?, postCode = ?, phone = ?, username = ?, pW = ? WHERE costNo=?";
	private static final String deleteCustomer = "DELETE FROM Costumers WHERE costNo=";
	
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
    	   System.out.println("An error occured while saving user information");
    	   e.printStackTrace();
       }
       finally
       {  
    	   closeConnectionSave();      
       } 
	}  
	
	public void editCustomer(Costumer customer)
	{
		openConnection();
		try
		{
			preparedStatement = connection.prepareStatement(updateCustomer); 
			preparedStatement.setString(1, customer.getfName());
			preparedStatement.setString(2, customer.getlName());
			preparedStatement.setString(3, customer.getRoad());
			preparedStatement.setString(4, customer.getHouseNo());  
			preparedStatement.setInt(5, customer.getPostCode());
			preparedStatement.setInt(6, customer.getPhone());
			preparedStatement.setString(7, customer.getUserName());
			preparedStatement.setString(8,customer.getPW());
			preparedStatement.setInt(9,customer.getCostumerNo());
	   	
			preparedStatement.executeUpdate();                     
		}
		catch(Exception e)
		{
			System.out.println("fejl i ændring af kunde");
			e.printStackTrace();
		}
		finally
		{
    	   closeConnectionSave();
		}
	}
	
	/**
	 * @author Jesper
	 * delete one customer
	 * @param Customer customer
	 */
	public void deleteEmployee(Costumer customer)  
	{
		openConnection();
		try 
		{                     
			statement.executeUpdate(deleteCustomer+customer.getCostumerNo());      

		}
		catch (Exception e)
		{
			System.out.println("fejl i sletning af kunde"); 
			e.printStackTrace();
		}
		finally
		{   

			closeConnectionSave();      
		} 
	}  
}