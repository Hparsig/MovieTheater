package movieTheater.main;

import movieTheater.GUI.CreateCostumer;
import movieTheater.GUI.CreateEmployee;
import movieTheater.GUI.MainWindow;
import movieTheater.GUI.SearchEmployee;
import movieTheater.GUI.SearchShow;
import movieTheater.Persons.Costumer;
import movieTheater.Persons.Manager;
import movieTheater.Persons.Person;
import movieTheater.SQL.SQLCustomerSave;

public class MainController
{
	private CreateEmployee createEmployee;
	private SearchEmployee searchEmployee;
	private EmployeeController employeeController;
	private MovieController movieController;

	private CreateCostumer createCostumer;
	private SQLCustomerSave saveCostumer;
	private LoginController loginController;
	private SearchShow searchShow;
	private int userChoice;
	public static Person loggedOn;
	private Costumer costumer;
	private boolean menuOn;
	private boolean programOn;
	private boolean isManager;


	public MainController()
	{
		saveCostumer = new SQLCustomerSave();
		loginController = new LoginController();
		employeeController = new EmployeeController();
		menuOn = true;
		programOn = true;
	}

	public void run()
	{
		while (programOn)
		{
			loggedOn = loginController.employeeLogin();
			isManager = (loggedOn instanceof Manager);
			runMenu();
		}
	}
	public void runMenu()
	{
		while (menuOn)
		{
			MainWindow mainWindow = new MainWindow();
			mainWindow.runMainWindow(); 
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
				isManager = false;
				createEmployee = new CreateEmployee();
				employeeController.showCreateEmployee(createEmployee, isManager);
				break;
			}
			case MainWindow.EDITEMPLOYEE:
			{
				isManager = false;
				searchEmployee = new SearchEmployee();
				createEmployee = new CreateEmployee();
				employeeController.searchEmployees(searchEmployee, 0,createEmployee, isManager);
				break;
			}
			case MainWindow.DELETEEMPLOYEE:
			{
				isManager = false;
				searchEmployee = new SearchEmployee();
				employeeController.searchEmployees(searchEmployee, 1,null, isManager);
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
				loggedOn = null;
				menuOn = false;
				mainWindow.dispose();
				break;
			}
			case MainWindow.CREATEMANAGER:
			{
				createEmployee = new CreateEmployee();
				employeeController.showCreateEmployee(createEmployee, isManager);
				break;
			}
			case MainWindow.EDITMANAGER:
			{
				searchEmployee = new SearchEmployee();
				createEmployee = new CreateEmployee();
				employeeController.searchEmployees(searchEmployee, 0,createEmployee, isManager);
				break;
			}
			case MainWindow.DELETEMANAGER:
			{
				searchEmployee = new SearchEmployee();
				employeeController.searchEmployees(searchEmployee, 1,null, isManager);
				break;
			}
			}
		} 
	}
}
