package movieTheater.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.concurrent.CountDownLatch;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import movieTheater.Movie.Movie;
import movieTheater.Show.HallBooking;
import movieTheater.Show.Show;
import movieTheater.main.MovieController;
import movieTheater.main.ShowController;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;

public class CreateShow extends JFrame{
	private JPanel contentPane;
	private JFormattedTextField formattedTextFieldStart;
	private JFormattedTextField formattedTextFieldSlut;
	private JComboBox comboBoxPris;
	private JComboBox comboBoxSal;
	private String start;
	private String end;
	private boolean areChangesMade;
	private MaskFormatter showStart;
	private MaskFormatter showEnd;
	private Timestamp timeStart;
	private Timestamp timeEnd;
	private Movie movie;
	private Show show;
	private ShowController showController;
	public final CountDownLatch latch = new CountDownLatch(1); //venter på brugerens input.
	
	
	public CreateShow(final Show show) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		areChangesMade = false;
		
		try{
			showStart = new MaskFormatter("####-##-## ##:##:##");
			showEnd = new MaskFormatter("####-##-## ##:##:##");
		}
		catch (ParseException e1)
		{
			e1.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 269, 337);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		final JComboBox comboBoxFilm = new JComboBox(MovieController.movies.toArray());
		comboBoxFilm.setBounds(107, 86, 117, 20);
		panel.add(comboBoxFilm);
		comboBoxFilm.setSelectedItem(movie);
//		if(this.movie == null){
		
//		while(this.movie != null){
//			int mov = (int) comboBoxFilm.getSelectedItem();
//			JLabel pnlfilmlængdevrb = new JLabel(mov+"");
//			pnlfilmlængdevrb.setBounds(107, 117, 46, 14);
//			panel.add(pnlfilmlængdevrb);
//			}
//		}
		JLabel lblVlgFillm = new JLabel("V\u00E6lg film");
		lblVlgFillm.setBounds(10, 89, 72, 14);
		panel.add(lblVlgFillm);
		
		JLabel lblStartTid = new JLabel("Start tid");
		lblStartTid.setBounds(10, 139, 46, 14);
		panel.add(lblStartTid);
		
		
		comboBoxPris = new JComboBox();
		comboBoxPris.setModel(new DefaultComboBoxModel(new String[] {"1", "1.5", "2"}));
		comboBoxPris.setToolTipText("1 - Normal, 1.5 Medium, 2 - Peak");
		comboBoxPris.setMaximumRowCount(3);
		comboBoxPris.setBounds(107, 224, 117, 20);
		panel.add(comboBoxPris);
		
		JLabel lblVlgPris = new JLabel("V\u00E6lg pris");
		lblVlgPris.setBounds(10, 227, 72, 14);
		panel.add(lblVlgPris);
		
		JButton btnAnnuller = new JButton("Annuller");
		btnAnnuller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				latch.countDown();
				CreateShow.this.dispose();
			}
		});
		btnAnnuller.setBounds(55, 255, 89, 23);
		panel.add(btnAnnuller);
		
		JLabel lblOpretForestilling = new JLabel("Opret Forestilling");
		lblOpretForestilling.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblOpretForestilling.setBounds(10, 11, 115, 14);
		panel.add(lblOpretForestilling);
		
		formattedTextFieldStart = new JFormattedTextField(showStart);
		formattedTextFieldStart.setBounds(107, 136, 117, 20);
		panel.add(formattedTextFieldStart);
		
		formattedTextFieldSlut = new JFormattedTextField(showEnd);
		formattedTextFieldSlut.setBounds(107, 165, 117, 20);
		panel.add(formattedTextFieldSlut);
		
		JLabel lblDatoFormat = new JLabel("Dato format: YYYY-MM-DD HH:MM:SS");
		lblDatoFormat.setBounds(10, 114, 214, 14);
		panel.add(lblDatoFormat);
		
		JLabel lblSlutTid = new JLabel("Slut tid");
		lblSlutTid.setBounds(10, 164, 46, 14);
		panel.add(lblSlutTid);
		
		JLabel lblVlgSal = new JLabel("V\u00E6lg sal");
		lblVlgSal.setBounds(10, 189, 46, 14);
		panel.add(lblVlgSal);
		
		comboBoxSal = new JComboBox();
		comboBoxSal.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3"}));
		comboBoxSal.setBounds(154, 193, 70, 20);
		panel.add(comboBoxSal);
		
		JButton btnOpret = new JButton("Opret");
		btnOpret.setBounds(154, 255, 89, 23);
		panel.add(btnOpret);
		
		btnOpret.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				
			start 		= formattedTextFieldStart.getText();
			end 		= formattedTextFieldSlut.getText();
			double price = Double.parseDouble((String)comboBoxPris.getSelectedItem());
			int hall 	= Integer.parseInt((String)comboBoxSal.getSelectedItem());
			HallBooking hallBooking = new HallBooking(hall, start, end);
			movie		= (Movie) comboBoxFilm.getSelectedItem();
			
			show.setMovie(movie);
			show.setHallBooking(hallBooking);
			show.setPrice(price);

			
//			Show show = new Show(movie, hallBooking, price);
//			showController.createShow(show);
			areChangesMade = true;
			latch.countDown();
		}
	});
		
}
	public boolean areChangesMade(){
		return areChangesMade;
	}
	public Show getShow(){
		return show;
	}
}