package movieTheater.SQL;

import java.sql.SQLException;

public class SQLShowSave extends SQL {
	
	private static final String createShow = "insert into shows(showID, hallNo, timeS, timeE, movieID)";
	
	public SQLShowSave(){
		
		
		statement = null;
		connection = null;
		
	}
	
	public int createShow(int showID, int hallNo, String timeS, String timeE, int movieID) throws SQLException{
		
		openConnection();
		preparedStatement = connection.prepareStatement(createShow); // create statement object
		int rows=0;
		try{
			preparedStatement.setInt(1, showID);// TODO auto increment???
			preparedStatement.setInt(2, hallNo);
			preparedStatement.setString(3, timeS);
			preparedStatement.setString(4, timeE);
			preparedStatement.setInt(5, movieID);
			
			rows = preparedStatement.executeUpdate();         
		}catch (Exception e){
		 	   e.printStackTrace(); //boundary TODO fix
		}finally{
			closeConnectionSave();
		}
		return rows;
	}
}
