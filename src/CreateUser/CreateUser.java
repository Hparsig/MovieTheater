package CreateUser;
import java.util.Random;
import java.util.Scanner;

public class CreateUser {

	int userNumber;
	String firstName;
	String lastName;
	String address;
	String telephone;
	String password;
	int role;
	
	Scanner scan = new Scanner(System.in);
	Users con = new Users();
	
	public void Create(){

		System.out.println("User Number: ");
		userNumber = scan.nextInt();
		scan.nextLine();
		
		System.out.println("First name: ");
		firstName = scan.next();
		scan.nextLine();
		
		System.out.println("Last name: ");
		lastName = scan.next();
		scan.nextLine();
		
		System.out.println("Address: ");
		address = scan.next();
		scan.nextLine();
		
		System.out.println("Telephone number: ");
		telephone = scan.next();
		scan.nextLine();
		
		System.out.println("Role: ");
		role = scan.nextInt();
		scan.nextLine();
		
		con.user.add(con.new inner(userNumber, firstName, lastName, address, telephone, Generator(), role));
	}
	
	public void test(){
		System.out.println(con.user.toString());
	}
		
	public String Generator(){

		String available = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		
		Random r = new Random();
		String pass = "";
		for(int i = 0; i < 8; i++){
			int temp = r.nextInt(62);
			pass = pass + available.substring(temp, temp+1);
		}
		return pass;
	}
}