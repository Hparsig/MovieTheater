package movieTheater.main;

import java.sql.SQLException;
import java.util.ArrayList;

import movieTheater.GUI.CreateEmployee;
import movieTheater.GUI.SearchEmployee;
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
	private ArrayList<Person> persons;
	private Person person;
	private SQLLoadPostCode loadPostcode;
	private SQLLoadTitel loadTitle;
	private ArrayList<City> postcodeArray;
	private ArrayList<Title> titleArray;
	
	/**
	 * @author Jesper and a bit Henrik
	 * @param load
	 * @param save
	 */
	
	public EmployeeController() 
	{
		load = new SQLEmployeeLoad();
		save = new SQLEmployeeSave();
		persons = new ArrayList<Person>();
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
	public void showCreateEmployee(CreateEmployee createEmployee, boolean isManager)
	{
		createEmployee.setVisible(true);
		
		for (int i=0; i<postcodeArray.size(); i++)
		{
			createEmployee.setPostcodeArray(postcodeArray.get(i).toString());
		}
		for (int i=0; i<titleArray.size(); i++)
		{
			createEmployee.setTitleArray(titleArray.get(i).toString());
		}
		
		try
		{
			createEmployee.latch.await();
			
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		int selectedTitle = createEmployee.getTitleID();
		int titleID = titleArray.get(selectedTitle).getTitelID(); //FIXME kun Admin må oprette managers
		
		String name = createEmployee.getName();
		String lastname = createEmployee.getLastname();
		int phone = createEmployee.getPhone();
		String road = createEmployee.getRoad();
		String houseNr = createEmployee.getHouseNr();
		
		int cityChoose = createEmployee.getPostcode();
		int postcode = postcodeArray.get(cityChoose).getPostcode();
		String cityChoosen = postcodeArray.get(cityChoose).getCity();
		String username = createEmployee.getUsername();
		String pWord = createEmployee.getpWord();
		
		saveEmployee(titleID, name, lastname,phone, road, houseNr, postcode, cityChoosen,username, pWord);
		
	
		createEmployee.dispose();
	}
	
	/**
	 * @author Jesper
	 * @param searchEmployee
	 * @param choose
	 * @throws SQLException
	 * open the window search employee
	 */
	public void searchEmployees(SearchEmployee searchEmployee, int choose,CreateEmployee createEmployee, boolean isManager)
	{
		persons.clear();	
	
		searchEmployee.setVisible(true);
		String fName = searchEmployee.getName();		//FIXME kun Admin må søge på managers
		String lName = searchEmployee.getLastname();
		String username = searchEmployee.getUsername();
		String employeeID = searchEmployee.getEmpNo();
		
		try
		{
			int num = Integer.parseInt(employeeID);
			persons = load.LoadEmployee(fName, lName, username,num);
		}catch(java.lang.NumberFormatException e)
		{
			try
			{
				persons = load.LoadEmployee(fName, lName, username);
			}
			catch(Exception e1)
			{
				
			}
		}catch(Exception e)
		{	
		}
		
		for(int i=0; i <persons.size(); i++)
		{
			searchEmployee.addEmployee(persons.get(i).toString());
		}

		try
		{
			searchEmployee.latch.await();

		} 
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		int selected = searchEmployee.getChoosen();
		person = persons.get(selected);
		searchEmployee.dispose();
		
		if(choose==1)
		{
			try
			{
				int delete = searchEmployee.delete(person.getfName());
				deleteEmployee(delete,person);
			}
			catch(Exception e)
			{
				
			}
		}
		else
		{
			showEditEmployee(createEmployee);
		}		
	}
	
	/**
	 * @author Jesper
	 * @param createEmployee
	 * show the createEmployeeWindow
	 */
	public void showEditEmployee(CreateEmployee createEmployee)
	{
		createEmployee.setVisible(true);
		
		for (int i=0; i<postcodeArray.size(); i++)
		{
			createEmployee.setPostcodeArray(postcodeArray.get(i).toString());
		}
		for (int i=0; i<titleArray.size(); i++)
		{
			createEmployee.setTitleArray(titleArray.get(i).toString());
		}
		
		createEmployee.setFornavn(person.getfName());
		createEmployee.setEfternavn(person.getlName());
		
		Integer phone1 = person.getPhone();
		createEmployee.setTlf(phone1.toString());
		createEmployee.setVej(person.getRoad());		
		createEmployee.setNr(person.getHouseNo());
		createEmployee.setBrugernavn(person.getUserName());
		createEmployee.setPassword(person.getPW());
		
		int indexTitle = 1;
										//FIXME skal også håndtere Admin
		if(person instanceof Manager)
		{	
			indexTitle = 0;
		}
		createEmployee.setTitle(indexTitle);
		
		int indexPostcode = 0;
		for(int i=0; i < postcodeArray.size(); i++)
		{
			if(postcodeArray.get(i).getPostcode()==person.getPostCode())
			{
				indexPostcode = i;
			}
		}
		createEmployee.setPostcode(indexPostcode);

		try
		{
			createEmployee.latch.await();
			
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		int employeeID = 0;
		if (person instanceof Employee)
		{
		employeeID = ((Employee)person).getEmployeeNo();
		}
		int selectedTitle = createEmployee.getTitleID();
		int titleID = titleArray.get(selectedTitle).getTitelID();
		
		String name = createEmployee.getName();
		String lastname = createEmployee.getLastname();
		int phone = createEmployee.getPhone();
		String road = createEmployee.getRoad();
		String houseNr = createEmployee.getHouseNr();
		
		int cityChoose = createEmployee.getPostcode();
		int postcode = postcodeArray.get(cityChoose).getPostcode();
		String cityChoosen = postcodeArray.get(cityChoose).getCity();	
		
	
		String username = createEmployee.getUsername();
		String pWord = createEmployee.getpWord();
		EditEmployee(titleID, name, lastname,phone, road, houseNr, postcode, cityChoosen,username, pWord,employeeID);
	
		createEmployee.dispose();
	}
	
	
	//Følgende skal måske i et funtionalitetslag??
	public void saveEmployee(int titleID, String name, String lastname,int phone, String road, String houseNr, int postcode, String cityChoosen,String username, String pWord)
	{
		
		if(titleID==1)
		{
			person = new Manager(name,lastname,phone,road,houseNr,postcode,cityChoosen,username,pWord);
		}
		else
		{
			person = new SalesPerson(name,lastname,phone,road,houseNr,postcode,cityChoosen,username,pWord);
		}
		
		if(person.getfName()!=null)
		{
		save.createEmployee(person);
		}
	}
	public void EditEmployee(int titleID, String name, String lastname,int phone, String road, String houseNr, int postcode, String cityChoosen,String username, String pWord, int employeeNum)
	{
		if(titleID==1)
		{
			person = new Manager(name,lastname,phone,road,houseNr,postcode,cityChoosen,username,pWord,employeeNum);
		}
		else
		{
			person = new SalesPerson(name,lastname,phone,road,houseNr,postcode,cityChoosen,username,pWord,employeeNum);
		}
		
		if(person.getfName()!=null)
		{
			save.editEmployee(person);
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
