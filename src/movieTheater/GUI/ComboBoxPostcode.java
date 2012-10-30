package movieTheater.GUI;


	import java.awt.BorderLayout;
	import java.awt.Container;
	import java.util.ArrayList;
	import javax.swing.AbstractListModel;
	import javax.swing.ComboBoxModel;
	import javax.swing.JComboBox;
	import javax.swing.JFrame;
	
	import movieTheater.SQL.SQLLoadPostCode;
	import movieTheater.main.City;

public class ComboBoxPostcode  extends AbstractListModel implements ComboBoxModel {	
		
		private Object selectedItem;
		private ArrayList city;
		private SQLLoadPostCode postcode;
		 
		public ComboBoxPostcode(SQLLoadPostCode postcode){
			this.postcode = postcode;
		}
		public ComboBoxPostcode(ArrayList city) {
			this.city = city;
		}

		public Object getSelectedItem() {
			return selectedItem;
		}

		public void setSelectedItem(Object newValue) {
			selectedItem = newValue;
		}

		public int getSize() {
			return city.size();
		}

		public Object getElementAt(int i) {
			return city.get(i);
		}
		public JComboBox set() {
			ArrayList city = postcode.getCitys();
			ComboBoxPostcode model = new ComboBoxPostcode(city);
	    	JComboBox comboBox = new JComboBox(model);
    	
	    return comboBox;
	  }
		public ArrayList getArraylist(){
			return city;
		}
	
}
