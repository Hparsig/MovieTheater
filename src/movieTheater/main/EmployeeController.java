package movieTheater.main;

import java.sql.SQLException;
import java.util.ArrayList;

import movieTheater.GUI.CreateEmployee;
import movieTheater.GUI.SearchEmployee;
import movieTheater.Persons.Employee;
import movieTheater.Persons.Manager;
import movieTheater.Persons.SalesPerson;
import movieTheater.SQL.SQLEmployeeLoad;
import movieTheater.SQL.SQLEmployeeSave;
import movieTheater.SQL.SQLLoadPostCode;
import movieTheater.SQL.SQLLoadTitel;

public class EmployeeController {

	private SQLEmployeeLoad load;
	private SQLEmployeeSave save;
	private ArrayList<Employee> employees;
	private Employee employee;
	private SQLLoadPostCode loadPostcode;
	private SQLLoadTitel loadTitle;
	private ArrayList<City> postcodeArray;
	private ArrayList<Title> titleArray;
	
	/**
	 * @author Jesper
	 * @param load
	 * @param save
	 */
	
	public EmployeeController(SQLEmployeeLoad load, SQLEmployeeSave save) 
	{
		this.load = load;
		this.save = save;
		employees = new ArrayList<Employee>();
		
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
	public void showCreateEmployee(CreateEmployee createEmployee)
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
	public void searchEmployees(SearchEmployee searchEmployee, int choose,CreateEmployee createEmployee)
	{
		employees.clear();	
	
		searchEmployee.setVisible(true);
		String fName = searchEmployee.getName();
		String lName = searchEmployee.getLastname();
		String username = searchEmployee.getUsername();
		String employeeID = searchEmployee.getEmpNo();
		
		try
		{
			int num = Integer.parseInt(employeeID);
			employees = load.LoadEmployee(fName, lName, username,num);
		}catch(java.lang.NumberFormatException e)
		{
			try
			{
				employees = load.LoadEmployee(fName, lName, username);
			}catch(Exception e1)
			{
				
			}
		}catch(Exception e)
		{	
		}
		
		for(int i=0; i <employees.size(); i++)
		{
			searchEmployee.addEmployee(employees.get(i).toString());
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
		employee = employees.get(selected);
		searchEmployee.dispose();
		
		if(choose==1)
		{
			try
			{
				int delete = searchEmployee.delete(employee.getfName());
				deleteEmployee(delete,employee);
			}catch(Exception e)
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
		
		createEmployee.setFornavn(employee.getfName());
		createEmployee.setEfternavn(employee.getlName());
		
		Integer phone1 = employee.getPhone();
		createEmployee.setTlf(phone1.toString());
		createEmployee.setVej(employee.getRoad());		
		createEmployee.setNr(employee.getHouseNo());
		createEmployee.setBrugernavn(employee.getUserName());
		createEmployee.setPassword(employee.getPW());
		
		int indexTitle = 1;
		if(employee instanceof Manager)
		{	
			indexTitle = 0;
		}
		createEmployee.setTitle(indexTitle);
		
		int indexPostcode = 0;
		for(int i=0; i < postcodeArray.size(); i++)
		{
			if(postcodeArray.get(i).getPostcode()==employee.getPostCode())
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
		
		int employeeID = employee.getEmployeeNo();
		
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
			employee = new Manager(name,lastname,phone,road,houseNr,postcode,cityChoosen,username,pWord);
		}
		else
		{
			employee = new SalesPerson(name,lastname,phone,road,houseNr,postcode,cityChoosen,username,pWord);
		}
		
		if(employee.getfName()!=null)
		{
		save.createEmployee(employee);
		}
	}
	public void EditEmployee(int titleID, String name, String lastname,int phone, String road, String houseNr, int postcode, String cityChoosen,String username, String pWord, int employeeNum)
	{
		if(titleID==1)
		{
			employee = new Manager(name,lastname,phone,road,houseNr,postcode,cityChoosen,username,pWord,employeeNum);
		}
		else
		{
			employee = new SalesPerson(name,lastname,phone,road,houseNr,postcode,cityChoosen,username,pWord,employeeNum);
		}
		
		if(employee.getfName()!=null)
		{
			save.editEmployee(employee);
		}
		
	}

	public void deleteEmployee(int choose,Employee employee) throws SQLException
	{
		if(choose==0)
		{
			save.deleteEmployee(employee.getEmployeeNo());
		}
	}


}
