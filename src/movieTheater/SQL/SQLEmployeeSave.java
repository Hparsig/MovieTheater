package movieTheater.SQL;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import movieTheater.main.Employee;

public class SQLEmployeeSave extends SQL {
	private static final String createEmployee = "insert into employees(fName, lName, titelID, road, houseNo, postCode, phone, pW, username) values(?,?,?,?,?,?,?,?,?)";
	
	public SQLEmployeeSave()
	{
		statement = null;
		connection = null;
		
	}
	
   
	public int createEmployee(String fNavn, String eNavn, int tlf, String pWord, int rolle, String vej, int nr, int postNr, String brugernavn) throws SQLException 
	{
		openConnection();
		preparedStatement = connection.prepareStatement(createEmployee); // create statement object
       int rows=0;
       try
       {
    	   preparedStatement.setString(1,fNavn);				
	   	   preparedStatement.setString(2,eNavn);
	   	   preparedStatement.setInt(3, rolle);
	   	   preparedStatement.setString(4, vej);
	   	   preparedStatement.setInt(5, nr);  
	   	   preparedStatement.setInt(6, postNr);
	   	   preparedStatement.setInt(7,tlf);
	   	   preparedStatement.setString(8, pWord);
	   	   preparedStatement.setString(9, brugernavn);
	      
	   	   rows = preparedStatement.executeUpdate();                     
        
       }
       catch (Exception e)
       {
    	   System.out.println("fejl i save af medarbejder"); //boundary TODO fix
       }
       finally
       {  
    	   closeConnection();      
       } 
       return rows;
	}  

}
