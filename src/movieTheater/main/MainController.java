package movieTheater.main;

import movieTheater.GUI.MainWindow;
import movieTheater.GUI.CustomerCheck;
import movieTheater.Persons.Admin;
import movieTheater.Persons.Costumer;
import movieTheater.Persons.Employee;
import movieTheater.Show.Show;
/**
 * Main controller orchestring the entire system. 
 * @author Outline by Henrik
 *
 */
public class MainController
{
	private CostumerController costumerController;
	private EmployeeController employeeController;
	private LoginController loginController;
	private MovieController movieController;
	private ShowController showController;
	private int userChoice;
	public static Employee loggedOn;
	private boolean menuOn;
	private boolean programOn;
	private boolean isAdmin;

	/**
	 * 
	 */
	public MainController()
	{
		loginController = new LoginController();
		
		programOn = true;
	}
	/**
	 * @author Henrik
	 * The method called from main.
	 * Starts an login session. 
	 * If acces is granted the main menu turn on.  
	 */
	public void run()
	{
		while (programOn)		// the program is on as long as the dosn't close the login window. 
		{
			loggedOn = loginController.employeeLogin();
			isAdmin = (loggedOn instanceof Admin);
			menuOn = true;
			runMainMenu();
		}
	}
	/**
	 * @author outline by Henrik
	 */
	public void runMainMenu()
	{
	
		while (menuOn)			// the menu is on, as long as the user dosn't log out. 
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
				e.printStackTrace();
			}

			userChoice = mainWindow.getChoise();		// the int userChoise comes from the mainWindow. 
			mainWindow.dispose();


			switch(userChoice)
			{
			case MainWindow.NEWORDER:					// int = 1
			{
				CustomerCheck customerCheck = new CustomerCheck();
				customerCheck.setVisible(true);
				try
				{
					customerCheck.latch.await();
				} 
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				int choise = customerCheck.getChoise();
				customerCheck.dispose();

				if(choise!=-1)//-1 = cancel 
				{
					if(choise==1)//the costumer is a member
					{
						CostumerController cosController = new CostumerController();
						Costumer costumer = cosController.showSearchCostumer();

						if(costumer!=null) //if costumer==null, the user has cancelled the order
						{
							ShowController showController = new ShowController();
							Show show = showController.searchShow();
							if(show!=null)
							{
								BookingController bookingCon = new BookingController();
								bookingCon.newBooking(show,costumer);
							}
						}
					}
					else //the costumer is not a member
					{
						ShowController showController = new ShowController();
						Show show = showController.searchShow();
						if(show!=null)
						{
							BookingController bookingCon = new BookingController();
							bookingCon.newBooking(show,null);
						}
					}
				}

				break;
			}
			case MainWindow.GETORDER:
			{
				costumerController = new CostumerController();
				Costumer costumer = costumerController.showSearchCostumer();
				if(costumer!=null)
				{
					BookingController bookingCon = new BookingController();
					bookingCon.showLoadOrder(costumer);
				}
				break;
			}
			case MainWindow.CREATECOSTUMER:
			{
				costumerController = new CostumerController();
				costumerController.setCostumer(null);
				break;
			}
			case MainWindow.EDITCOSTUMER:
			{
				costumerController = new CostumerController();
				Costumer costumer = costumerController.showSearchCostumer();
				if(costumer!=null)
				{
					costumerController.setCostumer(costumer);
				}
				break;
			}
			case MainWindow.DELETECOSTUMER:
			{
				costumerController = new CostumerController();
				Costumer costumer = costumerController.showSearchCostumer();
				if(costumer!=null)
				{
					costumerController.deleteCostumer(costumer);
				}
				break;
			}
			case MainWindow.CREATEEMPLOYEE:
			{	
				employeeController = new EmployeeController();
				employeeController.setEmployee(false);
				break;
			}
			case MainWindow.EDITEMPLOYEE:
			{
				employeeController = new EmployeeController();
				employeeController.editEmployee(false);
				break;
			}
			case MainWindow.DELETEEMPLOYEE:
			{
				employeeController = new EmployeeController();
				employeeController.deleteEmployee(false);
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
				movieController = new MovieController();
				movieController.deleteMovie();
				break;
			}
			case MainWindow.CREATESHOW:
			{
				movieController = new MovieController();
				movieController.loadMovies();
				showController = new ShowController();
				showController.setShow();
				break;
			}
			case MainWindow.EDITSHOW:
			{
				break;
			}
			case MainWindow.DELETESHOW:
			{
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
				employeeController.deleteEmployee(isAdmin);
				break;
			}
			}
		} 
	}
}
