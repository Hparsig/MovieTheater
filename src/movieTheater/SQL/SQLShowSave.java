package movieTheater.SQL;

import movieTheater.Show.Show;

public class SQLShowSave extends SQL {
	
	private static final String createShow = "INSERT INTO shows (hallNo,timeS,timeE,movieID,priceCategory) values(?,?,?,?,?)";
	
	public SQLShowSave(){
		
		
		statement = null;
		connection = null;
		
	}
	
	public void createShow(Show show)
	{
		
		openConnection();
		
		
		try{
			preparedStatement = connection.prepareStatement(createShow);
			
			preparedStatement.setInt(1, show.getHallBooking().getHalleNo());
			preparedStatement.setString(2, show.getHallBooking().getTimeStart());
			preparedStatement.setString(3, show.getHallBooking().getTimeEnd());
			preparedStatement.setInt(4, show.getMovie().getMovieID());
			preparedStatement.setDouble(5, show.getPriceCategory());
			
			preparedStatement.executeUpdate();         
		}catch (Exception e){
		 	   System.out.println("fejl i save af forestilling");
		 	   e.printStackTrace();
		}finally{
			closeConnectionPreparedStatement();
		}
	}
}
