package movieTheater.main;

import movieTheater.GUI.AvaliableSeats;
import movieTheater.GUI.CreateCostumer;
import movieTheater.GUI.CreateEmployee;
import movieTheater.GUI.MainWindow;
import movieTheater.GUI.SearchEmployee;
import movieTheater.GUI.SearchShow;
import movieTheater.Persons.Admin;
import movieTheater.Persons.Costumer;
import movieTheater.Persons.Person;
import movieTheater.SQL.SQLCustomerSave;
import movieTheater.SQL.SQLEmployeeLoad;
import movieTheater.SQL.SQLEmployeeSave;
import movieTheater.SQL.SQLShowLoad;
import movieTheater.SQL.SQLShowSave;

public class MainController
{
	private CreateEmployee createEmployee;
	private SearchEmployee searchEmployee;
	private EmployeeController employeeController;
	private SQLEmployeeSave saveEmployee;
	private SQLEmployeeLoad loadEmployee;
	private MovieController movieController;

	private CreateCostumer createCostumer;
	private SQLCustomerSave saveCostumer;

	private SearchShow searchShow;
	private int userChoice;
	public static Person loggedOn;
	private Costumer costumer;
	private boolean menuOn;



	public MainController()
	{
		saveCostumer = new SQLCustomerSave();
		saveEmployee = new SQLEmployeeSave();
		loadEmployee = new SQLEmployeeLoad();

		employeeController = new EmployeeController(loadEmployee,saveEmployee);
		menuOn = true;
	}

	public void run()
	{
		//kør metode til at logge på og sæt loggedOn. 
		//		loggedOn = new Manager("henrik", "Parsig", 20744864, "Egebo", "19", 2600, "Glostrup", "Hlkdaf", "dflaksdjf");
		//		loggedOn = new SalesPerson("henrik", "Parsig", 20744864, "Egebo", "19", 2600, "Glostrup", "Hlkdaf", "dflaksdjf");
		loggedOn = new Admin("henrik", "Parsig", 20744864, "Egebo", "19", 2600, "Glostrup", "Hlkdaf", "dflaksdjf");

		MainWindow mainWindow = new MainWindow();
		mainWindow.runMainWindow(); //FIXME skal udbygges til at tage en Employee med (den der er logget på)
		mainWindow.setVisible(true);
		try
		{
			mainWindow.latch.await();
		} 
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userChoice = mainWindow.getChoise();
		mainWindow.dispose();

		while (menuOn)
		{
			switch(userChoice)
			{
				case MainWindow.NEWORDER:
				{
					ShowController showController = new ShowController();
					showController.showSearchShow();
					break;
				}
				case MainWindow.GETORDER:
				{
					System.out.println("Hent Bestilling");
					break;
				}
				case MainWindow.CREATECOSTUMER:
				{
					createCostumer = new CreateCostumer();
					createCostumer.setVisible(true);
					break;
				}

				case MainWindow.EDITCOSTUMER:
				{
					System.out.println("Rediger kunde");
					break;
				}
				case MainWindow.DELETECOSTUMER:
				{
					System.out.println("Slet kunde");
					break;
				}
				case MainWindow.CREATEEMPLOYEE:
				{
					createEmployee = new CreateEmployee();
					employeeController.showCreateEmployee(createEmployee);
					break;
				}
				case MainWindow.EDITEMPLOYEE:
				{
					searchEmployee = new SearchEmployee();
					createEmployee = new CreateEmployee();
					employeeController.searchEmployees(searchEmployee, 0,createEmployee);
					break;
				}
				case MainWindow.DELETEEMPLOYEE:
				{
					searchEmployee = new SearchEmployee();
					employeeController.searchEmployees(searchEmployee, 1,null);

					break;
				}
				case MainWindow.CREATEMOVIE:
				{
					movieController = new MovieController();
					movieController.setMovie();
					break;
				}
				case MainWindow.EDITMOVIE:
				{
					movieController = new MovieController();
					movieController.EditMovie();
					break;
				}
				case MainWindow.DELETEMOVIE:
				{
					System.out.println("Slet film");
					break;
				}
				case MainWindow.CREATESHOW:
				{
					System.out.println("Opret forestilling");
					break;
				}
				case MainWindow.EDITSHOW:
				{
					System.out.println("Rediger forestilling");
					break;
				}
				case MainWindow.DELETESHOW:
				{
					System.out.println("Slet forestilling");
				break;
				}
				case MainWindow.LOGOFF:
				{
					System.out.println("Log af");
					break;
				}
			}
		}
	}

}
