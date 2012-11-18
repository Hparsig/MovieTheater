package movieTheater.main;

import java.util.ArrayList;

import movieTheater.GUI.CreateCostumer;
import movieTheater.GUI.SearchCostumer;
import movieTheater.Persons.Costumer;
import movieTheater.SQL.SQLCustomerLoad;
import movieTheater.SQL.SQLCustomerSave;
import movieTheater.SQL.SQLLoadPostCode;


public class CostumerController {

	private SQLCustomerLoad loadCostumer;
	private SQLCustomerSave saveCostumer;
	private Costumer costumer;
	private SQLLoadPostCode loadPostcode;
	public static ArrayList<City> postcodeArray;
	
	public CostumerController()
	{
		loadCostumer = new SQLCustomerLoad();
		saveCostumer = new SQLCustomerSave();
		loadPostcode = new SQLLoadPostCode();
		postcodeArray = loadPostcode.getCitys();
	}
	
	/**
	 * @author Jesper
	 * @param createEmployee
	 * show the createEmployeeWindow
	 */
	public void setCostumer(Costumer costumer)
	{
		if(costumer==null)
		{
			costumer = new Costumer();
		}
		CreateCostumer createCostumer= new CreateCostumer(costumer);
		createCostumer.setVisible(true);

		try
		{
			createCostumer.latch.await();
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		costumer = createCostumer.getCostumer();
		
		if (createCostumer.areChangesMade())
		{
			saveCostumer(costumer);
		}		
		createCostumer.dispose();
	}
	
	public void saveCostumer( Costumer costum)
	{
		String name = costum.getfName();
		String lastname = costum.getlName();
		int phone = costum.getPhone();
		String road = costum.getRoad();
		String houseNr = costum.getHouseNo();
		String username = costum.getUserName();
		String pWord = costum.getPW();
		int postcode =costum.getPostCode();
		String cityChoosen = costum.getCity();
		int costumNum = costum.getCostumerNo();
		
		if(costumNum==0)
		{
			costum = new Costumer(name,lastname,phone,road,houseNr,postcode,cityChoosen,username,pWord);
			saveCostumer.createCustomer(costum);
		}
		else
		{
			costum = new Costumer(name,lastname,phone,road,houseNr,postcode,cityChoosen,username,pWord,costumNum);
			try
			{
				saveCostumer.editCustomer(costum);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}
	
	public Costumer showSearchCostumer()
	{
		ArrayList<Costumer> costumers = new ArrayList<Costumer>();
		SearchCostumer searchCostumer = new SearchCostumer();
		searchCostumer.setVisible(true);
		while(searchCostumer.getClose()==1)
		{	
			costumers.clear();
			//Get the user data from the GUI
			String phone = searchCostumer.getPhone();
			String name = searchCostumer.getName();
			String lName = searchCostumer.getLName();
			int tlf;
			try
			{
				tlf = Integer.parseInt(phone);
				
			}
			catch(java.lang.NumberFormatException e)
			{
				tlf = 0;
			}
			try
			{
				costumers = loadCostumer.loadCostumer(tlf, name, lName);
				//writes the bookings to the screen
				if(costumers.size()>0)
				{
					for(int i=0; i < costumers.size(); i++)
					{
						searchCostumer.addCostumer(costumers.get(i).toString());
					}
				}
				else
				{
					searchCostumer.showError(); //hvis brugeren ikke kunne blive fundet. 
				}
			}
			catch(Exception e)
			{
				searchCostumer.showError();
			}
		}
		
		try
		{
			searchCostumer.latch.await();
		} 
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int close = searchCostumer.getClose();
		
		if(close!=-1)
		{
			int selected = searchCostumer.getSelected();
			costumer = costumers.get(selected);
		}
		else 
		{
			costumer = null;
		}
		searchCostumer.dispose();
		return costumer; 
	}
}
