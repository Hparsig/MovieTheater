package movieTheater.GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.CountDownLatch;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;


public class MainWindow extends JFrame 
{

	private JPanel contentPane;
	private JPanel panel;
	private JButton btnNewOrder;
	private JButton btnGetOrder;
	private JButton btnLogOff;
	private JLabel lblHovedmenu;
	private JButton btnNewEmployee;
	private JButton btnNewMovie;
	private JButton btnNewCostumer;
	private JButton btnEditCostumer;
	private JButton btnEditEmployee;
	private JButton btnEditFilm;
	private JButton btnNewShow;
	private JButton btnEditShow;
	private JButton btnDeleteCostumer;
	private JButton btnDeleteEmployee;
	private JButton btnDeleteMovie;
	private JButton btnDeleteShow;
	private JLabel lblOpret;
	private JLabel lblVisndre;
	private JLabel lblSlet;
	private int choise;
	public final CountDownLatch latch = new CountDownLatch(1); //venter på brugerens input. 
	public static final int NEWORDER = 1;
	public static final int GETORDER = 2;
	public static final int CREATECOSTUMER = 3;
	public static final int EDITCOSTUMER = 4;
	public static final int DELETECOSTUMER = 5;
	public static final int CREATEEMPLOYEE = 6;
	public static final int EDITEMPLOYEE = 7;
	public static final int DELETEEMPLOYEE = 8;
	public static final int CREATEMOVIE = 9;
	public static final int EDITMOVIE = 10;
	public static final int DELETEMOVIE = 11;
	public static final int CREATESHOW = 12;
	public static final int EDITSHOW = 13;
	public static final int DELETESHOW = 14;
	public static final int LOGOFF = 15;
	
	
	/**
	 * Create the frame.
	 */
	public MainWindow() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 575, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		btnNewOrder = new JButton("Ny bestilling");
		btnNewOrder.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				choise = NEWORDER;
				latch.countDown();
			}
		});
		btnNewOrder.setBounds(39, 81, 114, 47);
		panel.add(btnNewOrder);

		btnGetOrder = new JButton("Hent bestilling");
		btnGetOrder.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				choise = GETORDER;
				latch.countDown();
			}
		});
		btnGetOrder.setBounds(39, 140, 114, 47);
		panel.add(btnGetOrder);

		btnLogOff = new JButton("Log af");
		btnLogOff.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				choise = LOGOFF;
				latch.countDown();
			}
		});
		btnLogOff.setBounds(460, 1, 89, 23);
		panel.add(btnLogOff);

		lblHovedmenu = new JLabel("hovedmenu");
		lblHovedmenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblHovedmenu.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblHovedmenu.setBounds(10, 0, 166, 47);
		panel.add(lblHovedmenu);

		btnNewEmployee = new JButton("Medarbejder");
		btnNewEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				choise = CREATEEMPLOYEE;
				latch.countDown();
			}
		});

		btnNewEmployee.setBounds(163, 140, 114, 47);
		panel.add(btnNewEmployee);

		btnNewMovie = new JButton("Film");
		btnNewMovie.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				choise = CREATEMOVIE;
				latch.countDown();
			}
		});
		btnNewMovie.setBounds(163, 198, 114, 47);
		panel.add(btnNewMovie);

		btnNewCostumer = new JButton("Kunde");
		btnNewCostumer.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				choise = CREATECOSTUMER;
				latch.countDown();
			}
		});
		btnNewCostumer.setBounds(163, 81, 114, 47);
		panel.add(btnNewCostumer);

		btnEditCostumer = new JButton("Kunde");
		btnEditCostumer.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				choise = EDITCOSTUMER;
				latch.countDown();
			}
		});
		btnEditCostumer.setBounds(287, 81, 114, 47);
		panel.add(btnEditCostumer);

		btnEditEmployee = new JButton("Medarbejder");
		btnEditEmployee.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				choise = EDITEMPLOYEE;
				latch.countDown();
			}
		});
		btnEditEmployee.setBounds(287, 140, 114, 47);
		panel.add(btnEditEmployee);

		btnEditFilm = new JButton("Film");
		btnEditFilm.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				choise = EDITMOVIE;
				latch.countDown();
			}
		});
		btnEditFilm.setBounds(287, 198, 114, 47);
		panel.add(btnEditFilm);

		btnNewShow = new JButton("Forestilling");
		btnNewShow.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				choise = CREATESHOW;
				latch.countDown();
			}
		});
		btnNewShow.setBounds(163, 258, 114, 47);
		panel.add(btnNewShow);

		btnEditShow = new JButton("Forestilling");
		btnEditShow.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				choise = EDITSHOW;
				latch.countDown();
			}
		});
		btnEditShow.setBounds(287, 258, 114, 47);
		panel.add(btnEditShow);

		btnDeleteCostumer = new JButton("Kunde");
		btnDeleteCostumer.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				choise = DELETECOSTUMER;
				latch.countDown();
			}
		});
		btnDeleteCostumer.setBounds(411, 81, 114, 47);
		panel.add(btnDeleteCostumer);

		btnDeleteEmployee = new JButton("Medarbejder");
		btnDeleteEmployee.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				choise = DELETEEMPLOYEE;
				latch.countDown();
			}
		});
		btnDeleteEmployee.setBounds(411, 140, 114, 47);
		panel.add(btnDeleteEmployee);

		btnDeleteMovie = new JButton("Film");
		btnDeleteMovie.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				choise = DELETEMOVIE;
				latch.countDown();
			}
		});
		btnDeleteMovie.setBounds(411, 198, 114, 47);
		panel.add(btnDeleteMovie);

		btnDeleteShow = new JButton("Forestilling");
		btnDeleteShow.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				choise = DELETESHOW;
				latch.countDown();
			}
		});
		btnDeleteShow.setBounds(411, 258, 114, 47);
		panel.add(btnDeleteShow);

		lblOpret = new JLabel("opret");
		lblOpret.setHorizontalAlignment(SwingConstants.CENTER);
		lblOpret.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblOpret.setBounds(162, 35, 114, 47);
		panel.add(lblOpret);

		lblVisndre = new JLabel("vis/\u00E6ndre");
		lblVisndre.setHorizontalAlignment(SwingConstants.CENTER);
		lblVisndre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblVisndre.setBounds(287, 35, 114, 47);
		panel.add(lblVisndre);

		lblSlet = new JLabel("slet");
		lblSlet.setHorizontalAlignment(SwingConstants.CENTER);
		lblSlet.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSlet.setBounds(411, 35, 114, 47);
		panel.add(lblSlet);
		panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnNewOrder, btnGetOrder, btnNewCostumer, btnEditCostumer, btnDeleteCostumer, lblSlet, btnLogOff, lblHovedmenu, btnNewEmployee, btnNewMovie, btnEditEmployee, btnEditFilm, btnNewShow, btnEditShow, btnDeleteEmployee, btnDeleteMovie, btnDeleteShow, lblOpret, lblVisndre}));
	}
	public int getChoise()
	{
		return choise;
	}
}
