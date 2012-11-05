package movieTheater.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;


public class MainWindow extends JFrame {

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

	/**
	 * Create the frame.
	 */
	public MainWindow() {
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
		btnNewOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				
			}
		});
		btnNewOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewOrder.setBounds(39, 81, 114, 47);
		panel.add(btnNewOrder);
		
		btnGetOrder = new JButton("Hent bestilling");
		btnGetOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				
			}
		});
		btnGetOrder.setBounds(39, 140, 114, 47);
		panel.add(btnGetOrder);
		
		btnLogOff = new JButton("Log af");
		btnLogOff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				
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
				CreateEmployee createEmployee = new CreateEmployee();
				createEmployee.setVisible(true);
			}
		});

		btnNewEmployee.setBounds(163, 140, 114, 47);
		panel.add(btnNewEmployee);
		
		btnNewMovie = new JButton("Film");
		btnNewMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				NewMovie newMovie = new NewMovie();
				newMovie.setVisible(true);
			}
		});
		btnNewMovie.setBounds(163, 198, 114, 47);
		panel.add(btnNewMovie);
		
		btnNewCostumer = new JButton("Kunde");
		btnNewCostumer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewCostumer.setBounds(163, 81, 114, 47);
		panel.add(btnNewCostumer);
		
		btnEditCostumer = new JButton("Kunde");
		btnEditCostumer.setBounds(287, 81, 114, 47);
		panel.add(btnEditCostumer);
		
		btnEditEmployee = new JButton("Medarbejder");
		btnEditEmployee.setBounds(287, 140, 114, 47);
		panel.add(btnEditEmployee);
		
		btnEditFilm = new JButton("Film");
		btnEditFilm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEditFilm.setBounds(287, 198, 114, 47);
		panel.add(btnEditFilm);
		
		btnNewShow = new JButton("Forestilling");
		btnNewShow.setBounds(163, 258, 114, 47);
		panel.add(btnNewShow);
		
		btnEditShow = new JButton("Forestilling");
		btnEditShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEditShow.setBounds(287, 258, 114, 47);
		panel.add(btnEditShow);
		
		btnDeleteCostumer = new JButton("Kunde");
		btnDeleteCostumer.setBounds(411, 81, 114, 47);
		panel.add(btnDeleteCostumer);
		
		btnDeleteEmployee = new JButton("Medarbejder");
		btnDeleteEmployee.setBounds(411, 140, 114, 47);
		panel.add(btnDeleteEmployee);
		
		btnDeleteMovie = new JButton("Film");
		btnDeleteMovie.setBounds(411, 198, 114, 47);
		panel.add(btnDeleteMovie);
		
		btnDeleteShow = new JButton("Forestilling");
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
}
