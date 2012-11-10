package movieTheater.main;

public class CinemaController {

	private final HallController hallController;
	private final ShowController showController;
	
	public CinemaController()
	{	
		hallController = new HallController();
		showController = new ShowController();
	}


}
