package movieTheater.GUI;


	import java.awt.BorderLayout;
import java.awt.Container;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
	
import movieTheater.Movie.Actor;

public class ComboBoxActor  extends AbstractListModel implements ComboBoxModel {	
		
		private Object selectedItem;
		private ArrayList<Actor> actor;
		 
		public ComboBoxActor(ArrayList<Actor> actor) 
		{
			this.actor = actor;
		}

		public Object getSelectedItem() 
		{
			return selectedItem;
		}

		public void setSelectedItem(Object newValue) {
			selectedItem = newValue;
		}

		public int getSize() {
			return actor.size();
		}

		public Object getElementAt(int i) {
			return actor.get(i);
		}
		public JComboBox set() 
		{
			
			ComboBoxActor model = new ComboBoxActor(actor);
	    	JComboBox comboBox = new JComboBox(model);
    	
	    return comboBox;
	  }
		public ArrayList<Actor> getArraylist()
		{
			return actor;
		}
}
