package movieTheater.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @author Henrik
 *
 */
public class Cast 
{

	private Map<Actor, String> cast;
	/**
	 * 
	 */
	public Cast()
	{
		cast = new HashMap<Actor, String>();
	}
	/**
	 * 
	 * @param Map<Actor, String> cast
	 */
	public Cast(Map<Actor, String> cast)
	{
		this.cast = cast;
	}

	public Map<Actor, String> getCast()
	{
		return cast;
	}

	public ArrayList<String> getActorNames()
	{
		ArrayList<String> castString = new ArrayList<String>();

		for(Map.Entry<Actor, String> entry : cast.entrySet())
		{
			String name = (entry.getKey().getFName() + " " + entry.getKey().getLName() + " ");
			String role = entry.getValue();

			castString.add("\n" + role+ ":" + name);
		}
		return castString;
	}
	public void putActor(Actor selectedActor, String roleName)
	{
		cast.put(selectedActor, roleName);
	}
}
