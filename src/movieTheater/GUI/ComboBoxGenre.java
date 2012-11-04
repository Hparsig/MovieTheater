package movieTheater.GUI;


	import java.awt.BorderLayout;
import java.awt.Container;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
	
import movieTheater.Movie.Genre;
	import movieTheater.SQL.SQLLoadPostCode;
import movieTheater.main.City;

public class ComboBoxGenre  extends AbstractListModel implements ComboBoxModel {	
		
		private Object selectedItem;
		private ArrayList<Genre> genre;
		 
		public ComboBoxGenre(ArrayList<Genre> genre) 
		{
			this.genre = genre;
		}

		public Object getSelectedItem() 
		{
			return selectedItem;
		}

		public void setSelectedItem(Object newValue) {
			selectedItem = newValue;
		}

		public int getSize() {
			return genre.size();
		}

		public Object getElementAt(int i) {
			return genre.get(i);
		}
		public JComboBox set() 
		{
			
			ComboBoxGenre model = new ComboBoxGenre(genre);
	    	JComboBox comboBox = new JComboBox(model);
    	
	    return comboBox;
	  }
		public ArrayList<Genre> getArraylist()
		{
			return genre;
		}
}
