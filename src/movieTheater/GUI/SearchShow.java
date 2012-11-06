package movieTheater.GUI;

import java.awt.EventQueue;
import java.awt.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import javax.swing.JFormattedTextField;

import javax.swing.JList;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import movieTheater.Show.Show;
import movieTheater.main.ShowController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import java.awt.Font;

public class SearchShow extends JFrame{

	private JFrame frame;
	private JPanel contentPane;
	private JPanel panel;
	private JFormattedTextField txtDato;
	private JLabel lblStartdato;
	private JFormattedTextField txtTitel;
	private JLabel lblSlutdato;
	private JButton btnStartSgning;
	private JButton btnFortryd;
	private List showList;
	private List listSæder;
	
	private MaskFormatter maskFormatDate;
	private SimpleDateFormat dateFormat;
	private Date date;
	private String title;
	private ShowController showController; 
	private ArrayList<Show> shows;
		
	public final CountDownLatch latch = new CountDownLatch(1); //venter på brugerens input. 

	/**
	 * @author Jesper
	 * Create the application.
	 */
	
	public SearchShow() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 795, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		showController = new ShowController();
		
		try
		{
			maskFormatDate = new MaskFormatter("##-##-####");
		} 
		catch (ParseException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		
		txtDato = new JFormattedTextField(maskFormatDate);
		txtDato.setBounds(68, 13, 86, 20);
		panel.add(txtDato);
		txtDato.setColumns(10);
		
		lblStartdato = new JLabel("Dato");
		lblStartdato.setBounds(12, 16, 46, 14);
		panel.add(lblStartdato);
		
		txtTitel = new JFormattedTextField();
		txtTitel.setColumns(10);
		txtTitel.setBounds(68, 44, 86, 20);
		panel.add(txtTitel);
		
		lblSlutdato = new JLabel("Titel");
		lblSlutdato.setBounds(12, 47, 46, 14);
		panel.add(lblSlutdato);
		
		btnStartSgning = new JButton("s\u00F8g");
		btnStartSgning.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				showList.clear();
				
				try
				{
					date = dateFormat.parse(txtDato.getText());
					java.sql.Date sqlDate = new java.sql.Date(date.getTime());
					title = txtTitel.getText();
					shows = showController.getShows(title, sqlDate);
					
					for(int i=0; i < shows.size(); i++){
						showList.add(shows.get(i).toString());
					}	
					
				
				}catch(java.text.ParseException e)
				{
					
					JOptionPane.showMessageDialog(new JFrame(), "Dato er ikke udfyldt korrekt");
					
				}catch(Exception e)
				{
					e.printStackTrace();
				}		
				
			}
		});
		btnStartSgning.setBounds(166, 27, 89, 23);
		panel.add(btnStartSgning);
		
		btnFortryd = new JButton("Tilbage");
		btnFortryd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				latch.countDown();
				SearchShow.this.dispose();
			}
		});
		btnFortryd.setBounds(12, 245, 89, 23);
		panel.add(btnFortryd);
		
		showList = new List();
		showList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listSæder.removeAll();
				int selected = showList.getSelectedIndex();
				HashMap<Integer,ArrayList<Integer>> av = shows.get(selected).getHallBooking().getAvailableSeats();
				Map map = av;
				Iterator entries = map.entrySet().iterator();
				
				while (entries.hasNext())
				{
					Map.Entry entry = (Map.Entry) entries.next();
					Integer key = (Integer)entry.getKey();
					ArrayList<Integer> value = (ArrayList<Integer>)entry.getValue();
					listSæder.add("Række = " + key + ", Ledige sæder = " + value);
				}
				
			}
				
			
		});
		showList.setBounds(12, 108, 273, 131);
		panel.add(showList);
		
		JLabel lblForestillinger = new JLabel("Forestillinger");
		lblForestillinger.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblForestillinger.setBounds(12, 77, 130, 25);
		panel.add(lblForestillinger);
		
		JLabel lblLedigeSder = new JLabel("Ledige s\u00E6der");
		lblLedigeSder.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLedigeSder.setBounds(331, 3, 347, 37);
		panel.add(lblLedigeSder);
		
		listSæder = new List();
		listSæder.setBounds(331, 44, 426, 224);
		panel.add(listSæder);
	}
}
