package movieTheater.GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.concurrent.CountDownLatch;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import movieTheater.Persons.Admin;
import movieTheater.Persons.Manager;
import movieTheater.Persons.SalesPerson;
import movieTheater.main.MainController;

import org.eclipse.wb.swing.FocusTraversalOnArray;

/**
 * 
 * @author Henrik
 *
 */
@SuppressWarnings("serial")
public class MainWindow extends JFrame implements WindowListener
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
	public final CountDownLatch latch = new CountDownLatch(1); //venter p� brugerens input. 
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
	public static final int CREATEMANAGER = 16;
	public static final int EDITMANAGER = 17;
	public static final int DELETEMANAGER = 18;
	public static final int EXIT = 19;
	private JLabel lblLoggedOn;
	private JButton btnCreateManager;
	private JButton btnEditManager;
	private JButton btnDeleteManager;

	/**
	 * Create the frame.
	 */
	public MainWindow() 
	{
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);
	}
	public void runMainWindow()
	{
		setBounds(100, 100, 576, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

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

		lblLoggedOn = new JLabel("New label");
		lblLoggedOn.setText(MainController.loggedOn.getfName() + " " + MainController.loggedOn.getlName());
		lblLoggedOn.setBounds(287, 5, 163, 14);
		panel.add(lblLoggedOn);

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
		
		btnEditFilm = new JButton("Film");
		if (MainController.loggedOn instanceof SalesPerson)
		{
			btnEditFilm.setText("s�g/vis film");
		}
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

		if (MainController.loggedOn instanceof Manager || MainController.loggedOn instanceof Admin)
		{
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

			if (MainController.loggedOn instanceof Admin)
			{
				btnCreateManager = new JButton("Manager");
				btnCreateManager.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						choise = CREATEMANAGER;
						latch.countDown();
					}
				});
				btnCreateManager.setBounds(162, 316, 114, 47);
				panel.add(btnCreateManager);

				btnEditManager = new JButton("Manager");
				btnEditManager.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						choise = EDITMANAGER;
						latch.countDown();
					}
				});
				btnEditManager.setBounds(286, 316, 114, 47);
				panel.add(btnEditManager);

				btnDeleteManager = new JButton("Manager");
				btnDeleteManager.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						choise = DELETEMANAGER;
						latch.countDown();
					}
				});
				btnDeleteManager.setBounds(410, 316, 114, 47);
				panel.add(btnDeleteManager);
			}
		}
		panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnNewOrder, btnGetOrder, btnNewCostumer, btnEditCostumer, btnDeleteCostumer, lblSlet, btnLogOff, lblHovedmenu, btnNewEmployee, btnNewMovie, btnEditEmployee, btnEditFilm, btnNewShow, btnEditShow, btnDeleteEmployee, btnDeleteMovie, btnDeleteShow, lblOpret, lblVisndre}));

	}
	public int getChoise()
	{
		return choise;
	}
	@Override
	public void windowActivated(WindowEvent e)
	{	}
	@Override
	public void windowClosed(WindowEvent e)
	{	}
	@Override
	public void windowClosing(WindowEvent e)
	{
		choise = EXIT;
		latch.countDown();		
	}
	@Override
	public void windowDeactivated(WindowEvent e)
	{	}
	@Override
	public void windowDeiconified(WindowEvent e)
	{	}
	@Override
	public void windowIconified(WindowEvent e)
	{	}
	@Override
	public void windowOpened(WindowEvent e)
	{	}	
}

