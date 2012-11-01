package movieTheater.main;

public class CinemaController {

	private final MovieController movieController;
	private final HallController hallController;
	private final ShowController showController;
	
	public CinemaController()
	{	
		movieController = new MovieController();
		hallController = new HallController();
		showController = new ShowController();
	}


}
