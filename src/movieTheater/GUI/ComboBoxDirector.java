package movieTheater.GUI;


	import java.awt.BorderLayout;
import java.awt.Container;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
	
import movieTheater.Movie.Director;

public class ComboBoxDirector  extends AbstractListModel implements ComboBoxModel {	
		
		private Object selectedItem;
		private ArrayList<Director> director;
		 
		public ComboBoxDirector(ArrayList<Director> director) 
		{
			this.director = director;
		}

		public Object getSelectedItem() 
		{
			return selectedItem;
		}

		public void setSelectedItem(Object newValue) 
		{
			selectedItem = newValue;
		}

		public int getSize() 
		{
			return director.size();
		}

		public Object getElementAt(int i) 
		{
			return director.get(i);
		}
		public JComboBox set() 
		{
			ComboBoxDirector model = new ComboBoxDirector(director);
	    	JComboBox comboBox = new JComboBox(model);
    	
	    return comboBox;
	  }
		public ArrayList<Director> getArraylist()
		{
			return director;
		}
}
