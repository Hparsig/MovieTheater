package movieTheater.main;

import movieTheater.GUI.CreateCostumer;
import movieTheater.GUI.MainWindow;
import movieTheater.GUI.SearchShow;
import movieTheater.Persons.Admin;
import movieTheater.Persons.Costumer;
import movieTheater.Persons.Employee;
import movieTheater.SQL.SQLCustomerSave;
import movieTheater.Show.Show;

public class MainController
{
	private EmployeeController employeeController;
	private ShowController showController;
	private MovieController movieController;
	private CreateCostumer createCostumer;
	private SQLCustomerSave saveCostumer;
	private LoginController loginController;
	private SearchShow searchShow;
	private int userChoice;
	public static Employee loggedOn;
	private Costumer costumer;
	private boolean menuOn;
	private boolean programOn;
	private boolean isAdmin;


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
			isAdmin = (loggedOn instanceof Admin);
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
				Show show = showController.showSearchShow();
				if(show!=null)
				{
					BookingController bookingCon = new BookingController();
					bookingCon.showNewBookings(show);
				}
				break;
			}
			case MainWindow.GETORDER:
			{
				BookingController bookingCon = new BookingController();
				bookingCon.showLoadOrder();
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
				employeeController = new EmployeeController();
				employeeController.setEmployee(isAdmin);
				break;
			}
			case MainWindow.EDITEMPLOYEE:
			{
				employeeController = new EmployeeController();
				employeeController.editEmployee(isAdmin);
				break;
			}
			case MainWindow.DELETEEMPLOYEE:
			{
				employeeController = new EmployeeController();
				employeeController.searchEmployees(isAdmin);
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
				showController = new ShowController();
				showController.setShow();
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
				employeeController = new EmployeeController();
				employeeController.setEmployee(isAdmin);
				break;
			}
			case MainWindow.EDITMANAGER:
			{
				employeeController = new EmployeeController();
				employeeController.editEmployee(isAdmin);
				break;
			}
			case MainWindow.DELETEMANAGER:
			{
				employeeController = new EmployeeController();
				employeeController.searchEmployees(isAdmin);
				break;
			}
			}
		} 
	}
}
