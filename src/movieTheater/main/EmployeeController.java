package movieTheater.main;

import java.sql.SQLException;
import java.util.ArrayList;

import movieTheater.GUI.CreateEmployee;
import movieTheater.GUI.SearchEmployee;
import movieTheater.Persons.Admin;
import movieTheater.Persons.Employee;
import movieTheater.Persons.Manager;
import movieTheater.Persons.Person;
import movieTheater.Persons.SalesPerson;
import movieTheater.SQL.SQLEmployeeLoad;
import movieTheater.SQL.SQLEmployeeSave;
import movieTheater.SQL.SQLLoadPostCode;
import movieTheater.SQL.SQLLoadTitel;

public class EmployeeController {

	private SQLEmployeeLoad load;
	private SQLEmployeeSave save;
	public static ArrayList<Employee> persons;
	private Employee person;
	private SQLLoadPostCode loadPostcode;
	private SQLLoadTitel loadTitle;
	public static ArrayList<City> postcodeArray;
	public static ArrayList<Title> titleArray;
	private CreateEmployee createEmployee;
	private SearchEmployee searchEmployee;
	private int titleID;

	/**
	 * @author Jesper and Henrik
	 * @param load
	 * @param save
	 */

	public EmployeeController() 
	{
		load = new SQLEmployeeLoad();
		save = new SQLEmployeeSave();
		persons = new ArrayList<Employee>();
		loadTitle = new SQLLoadTitel();
		loadPostcode = new SQLLoadPostCode();

		titleArray = loadTitle.getTitels();
		postcodeArray = loadPostcode.getCitys();
	}

	/**
	 * @author Jesper
	 * @param createEmployee
	 * show the createEmployeeWindow
	 */
	public void setEmployee(boolean isAdmin)
	{
		person = new SalesPerson();
		createEmployee = new CreateEmployee(person, isAdmin);
		createEmployee.setVisible(true);

		try
		{
			createEmployee.latch.await();
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		person = createEmployee.getEmployee();
		titleID = createEmployee.getTitleID(); 

		if (createEmployee.areChangesMade())
		{
			saveEmployee(person);
		}		
		createEmployee.dispose();
	}

	public void editEmployee(boolean isAdmin)
	{
		searchEmployees(isAdmin);
		showEditEmployee(isAdmin);
		if (createEmployee.areChangesMade())
		{
			saveEmployee(person);
		}
	}
	/**
	 * @author Jesper
	 * @param searchEmployee
	 * @param choose
	 * @throws SQLException
	 * open the window search employee
	 */
	public void searchEmployees(boolean isAdmin)
	{
		searchEmployee = new SearchEmployee();
		persons.clear();	

		searchEmployee.setVisible(true);
		try
		{
			searchEmployee.latchSearch.await();
		} 
		catch (InterruptedException e2)
		{
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String fName = searchEmployee.getName();		//FIXME kun Admin må søge på managers
		String lName = searchEmployee.getLastname();
		String username = searchEmployee.getUsername();
		String employeeID = searchEmployee.getEmpNo();

		try
		{
			int num = Integer.parseInt(employeeID);
			persons = load.LoadEmployee(fName, lName, username, num, isAdmin);
		}
		catch(java.lang.NumberFormatException e)
		{
			try
			{
				persons = load.LoadEmployee(fName, lName, username, isAdmin);
			}
			catch(Exception e1)
			{

			}
		}
		catch(Exception e)
		{	
		}
		try
		{
			for (Employee person : persons)
			{
				searchEmployee.addToList(person.toString());
			}
			searchEmployee.latchChoose.await();
		} 
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		person = persons.get(searchEmployee.getChoosen());
		searchEmployee.dispose();
	}

	/**
	 * @author Jesper and Henrik
	 * @param createEmployee
	 * show the createEmployeeWindow
	 */
	public void showEditEmployee(boolean isAdmin)
	{
		createEmployee = new CreateEmployee(person, isAdmin);

		if (isAdmin)
		{
			int indexTitle = 1;
			if (person instanceof Manager)
				indexTitle = 0;
			if (person instanceof Admin)
				indexTitle = 2;
			createEmployee.setTitle(indexTitle);
		}
		int indexPostcode = 0;
		for(int i=0; i < postcodeArray.size(); i++)
		{
			if(postcodeArray.get(i).getPostcode()==person.getPostCode())
			{
				indexPostcode = i;
			}
		}
		createEmployee.setPostcode(indexPostcode);
		createEmployee.setVisible(true);
		try
		{
			createEmployee.latch.await();
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		createEmployee.dispose();
	}


	//Følgende skal måske i et funtionalitetslag??
	public void saveEmployee(Employee person)
	{
		System.out.println(titleID);
		String fName = person.getfName();
		String lName = person.getlName();
		int phone = person.getPhone();
		String road = person.getRoad();
		String houseNo = person.getHouseNo();
		String city = person.getCity();
		int postCode = person.getPostCode();
		String userName = person.getUserName();
		String pWord = person.getPW();

		if(titleID == 1)
		{
			person = new Manager(fName,lName,phone,road,houseNo,postCode,city,userName, pWord);
		}
		if(titleID == 3)
		{
			person = new Admin(fName, lName, phone, road, houseNo, postCode, city, userName, pWord);
		}
		if(titleID == 2)
		{
			person = new SalesPerson(fName, lName, phone, road, houseNo, postCode, city, userName, pWord);
		}

		if(person.getEmployeeNo() == 0) //dvs. en ny person der ikke har fået tilddelt empNo. 
		{
			save.createEmployee(person);
		}
		else
		{
			System.out.println("opdaterer");
			//			save.updateEmployee(person);
		}
	}

	public void deleteEmployee(int choose,Person person) throws SQLException
	{
		if(choose==0 && person instanceof Employee)
		{
			save.deleteEmployee(((Employee)person).getEmployeeNo());
		}
	}


}
