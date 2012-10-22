package CreateUser;
import java.util.ArrayList;



public class Users{


	public ArrayList<Users.inner> user = new ArrayList<Users.inner>();

	public class inner{

		private int userNumber;
		private String firstName;
		private String lastName;
		private String address;
		private String telephone;
		private String password;
		private int role;

		// INNER CLASS
		public inner(int userNumber, String firstName, String lastName, String address, 
				String telephone, String password, int role){

			this.userNumber = userNumber;
			this.firstName = firstName;
			this.lastName = lastName;
			this.address = address;
			this.telephone = telephone;
			this.password = password;
			this.role = role;
		}

		public int getUserNumber(){
			return userNumber;
		}

		public String getFirstName(){
			return firstName;
		}

		public String getLastName(){
			return lastName;
		}

		public String getAddress(){
			return address;
		}

		public String getTelephone(){
			return telephone;
		}

		public String getPassword(){
			return password;
		}

		public int getRole(){
			return role;
		}

		public void setUserNumber(){
			//Auto-increment ting...
		}

		public void setFirstName(String firstName){
			this.firstName = firstName;
		}

		public void setLastName(String lastName){
			this.lastName = lastName;
		}

		public void setAddress(String address){
			this.address = address;
		}

		public void setTelephone(String telephone){
			this.telephone = telephone;
		}

		public void setRole(int role){
			this.role = role;
		}

		public String toString(){
			return "Number: " + userNumber + " Name: " + firstName  + " " + lastName + 
					" Address: " + address + " Telephone: " + telephone  +
					" Password: " + password + " Role: " + role;
		}
	}
}
