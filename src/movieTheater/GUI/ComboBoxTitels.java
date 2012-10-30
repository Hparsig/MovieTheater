package movieTheater.GUI;


	import java.awt.BorderLayout;
	import java.awt.Container;
	import java.util.ArrayList;
	import javax.swing.AbstractListModel;
	import javax.swing.ComboBoxModel;
	import javax.swing.JComboBox;
	import javax.swing.JFrame;
	
	import movieTheater.SQL.SQLLoadTitel;
	import movieTheater.main.Titel;

public class ComboBoxTitels  extends AbstractListModel implements ComboBoxModel {	
		
		private Object selectedItem;
		private ArrayList titel;
		private SQLLoadTitel loadTitel;
		 
		public ComboBoxTitels(SQLLoadTitel loadTitel){
			this.loadTitel = loadTitel;
		}
		public ComboBoxTitels(ArrayList titel) {
			this.titel = titel;
		}

		public Object getSelectedItem() {
			return selectedItem;
		}

		public void setSelectedItem(Object newValue) {
			selectedItem = newValue;
		}

		public int getSize() {
			return titel.size();
		}

		public Object getElementAt(int i) {
			return titel.get(i);
		}
		public JComboBox set() {
			ArrayList titel = loadTitel.getTitels();
			ComboBoxTitels model = new ComboBoxTitels(titel);
	    	JComboBox comboBox = new JComboBox(model);
    	
	    return comboBox;
	  }
	
}
