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


public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JButton btnNewOrder;
	private JButton btnGetOrder;
	private JButton btnLogOff;
	private JLabel lblHovedmenu;

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
		btnNewOrder.setBounds(164, 106, 114, 47);
		panel.add(btnNewOrder);
		
		btnGetOrder = new JButton("Hent bestilling");
		btnGetOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				
			}
		});
		btnGetOrder.setBounds(164, 164, 114, 47);
		panel.add(btnGetOrder);
		
		btnLogOff = new JButton("Log af");
		btnLogOff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				
			}
		});
		btnLogOff.setBounds(325, 218, 89, 23);
		panel.add(btnLogOff);
		
		lblHovedmenu = new JLabel("hovedmenu");
		lblHovedmenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblHovedmenu.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblHovedmenu.setBounds(138, 11, 166, 47);
		panel.add(lblHovedmenu);
	}
}
