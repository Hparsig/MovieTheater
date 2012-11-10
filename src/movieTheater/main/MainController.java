package movieTheater.main;

import movieTheater.GUI.CreateCostumer;
import movieTheater.GUI.CreateEmployee;
import movieTheater.GUI.MainWindow;
import movieTheater.GUI.SearchEmployee;
import movieTheater.GUI.SearchShow;
import movieTheater.Persons.Costumer;
import movieTheater.Persons.Employee;
import movieTheater.SQL.SQLCustomerSave;
import movieTheater.SQL.SQLEmployeeLoad;
import movieTheater.SQL.SQLEmployeeSave;

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
	private Employee employee;
	private Costumer costumer;



	public MainController()
	{
		saveCostumer = new SQLCustomerSave();
		saveEmployee = new SQLEmployeeSave();
		loadEmployee = new SQLEmployeeLoad();
		employeeController = new EmployeeController(loadEmployee,saveEmployee);

	}

	public void run()
	{
		MainWindow mainWindow = new MainWindow();
		mainWindow.runMainWindow(); //FIXME skal udbygges til at tage en Employee med (den der er logget p�)
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
			searchShow = new SearchShow();
			searchShow.setVisible(true);

			try
			{
				searchShow.latch.await();
			} 
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Ny bestilling");
			searchShow.dispose();
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

			try
			{
				createCostumer.latch.await();
			} 
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Opret kunde");

			costumer = createCostumer.getCostumer();

			if (costumer != null)
			{
				saveCostumer.createCustomer(costumer);
			}
			createCostumer.dispose();
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


			//			createEmployee = new CreateEmployee(employeeController);
			//			createEmployee.setVisible(true);
			//			
			//			try
			//			{
			//				createEmployee.latch.await();
			//			} 
			//			catch (InterruptedException e)
			//			{
			//				e.printStackTrace();
			//			}
			//			System.out.println("Opret medarbejder");
			//			
			//
			//			employee = createEmployee.getEmployee();
			//			
			//			if (employee != null)
			//			{
			//				saveEmployee.createEmployee(employee);
			//			}
			//			createEmployee.dispose();
			break;

		}
		case MainWindow.EDITEMPLOYEE:
		{
			searchEmployee = new SearchEmployee();
			createEmployee = new CreateEmployee();
			employeeController.searchEmployees(searchEmployee, 0,createEmployee);

			//			searchEmployee = new SearchEmployee(employeeController);
			//			searchEmployee.setVisible(true);
			//			
			//			try
			//			{
			//				searchEmployee.latch.await();
			//
			//			} 
			//			catch (InterruptedException e)
			//			{
			//				// TODO Auto-generated catch block
			//				e.printStackTrace();
			//			}
			//			
			//			employee = searchEmployee.getEmployee();
			//			editEmployee = new EditEmployee(employee,employeeController);
			//			editEmployee.setVisible(true);
			//			searchEmployee.dispose();
			//			try
			//			{
			//				editEmployee.latch.await();
			//			}catch(Exception e)
			//			{
			//				e.printStackTrace();
			//			}
			//			
			//			System.out.println("�ndre medarbejder");
			//			

			break;
		}
		case MainWindow.DELETEEMPLOYEE:
		{
			searchEmployee = new SearchEmployee();
			employeeController.searchEmployees(searchEmployee, 1,null);


			//			deleteEmployee = new DeleteEmployee(employeeController);
			//			deleteEmployee.setVisible(true);
			//			
			//			try
			//			{
			//				deleteEmployee.latch.await();
			//			} 
			//			catch (InterruptedException e)
			//			{
			//				// TODO Auto-generated catch block
			//				e.printStackTrace();
			//			}
			//			System.out.println("Slet medarbejder");
			//			
			//			deleteEmployee.dispose();
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
		run(); //TODO holder ikke hvis der logges af, s� skal findes en anden m�de at k�re i ring.
	}
}
