package CreateUser;


import java.util.Random;

public class PasswordGenerator {

	String available = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	String pass = "";
	
	public String Generator(){

		Random r = new Random();
		for(int i = 0; i < 8; i++){
			int temp = r.nextInt(62);
			pass = pass + available.substring(temp, temp+1);			
		}
		
		return pass;
	}

}
