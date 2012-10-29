package movieTheater.SQL;

import java.util.Date;
import java.sql.SQLException;

public class SQLShowSave extends SQL {
	
	private static final String createShow = "INSERT INTO shows (hallNo,timeS,timeE,movieID) values(?,?,?,?)";
	
	public SQLShowSave(){
		
		
		statement = null;
		connection = null;
		
	}
	
	public int createShow(int hallNo, Date timeS, Date timeE, int movieID) throws SQLException{
		
		openConnection();
		preparedStatement = connection.prepareStatement(createShow);
		int rows=0;
		try{
			
			preparedStatement.setInt(1, hallNo);
			preparedStatement.setDate(2, (java.sql.Date) timeS);
			preparedStatement.setDate(3, (java.sql.Date) timeE);
			preparedStatement.setInt(4, movieID);
			
			rows = preparedStatement.executeUpdate();         
		}catch (Exception e){
		 	   System.out.println("fejl i save af forestilling"); //boundary TODO fix
		}finally{
			closeConnectionSave();
		}
		return rows;
	}
}
