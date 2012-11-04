package movieTheater.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

import movieTheater.Movie.Actor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateActor extends JFrame
{

	private JPanel contentPane;
	private JPanel panel;
	private JTextField txtFName;
	private JTextField txtLName;
	private JButton btnOpret;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					CreateActor frame = new CreateActor();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CreateActor()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		txtFName = new JTextField();
		txtFName.setBounds(111, 60, 86, 20);
		panel.add(txtFName);
		txtFName.setColumns(10);
		
		txtLName = new JTextField();
		txtLName.setColumns(10);
		txtLName.setBounds(111, 91, 86, 20);
		panel.add(txtLName);
		
		btnOpret = new JButton("Opret");
		btnOpret.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				CreateActor.this.setVisible(false);
			}
		});
		btnOpret.setBounds(325, 218, 89, 23);
		panel.add(btnOpret);
	}
	
	public Actor getActor()
	{
		return new Actor(txtFName.getText(), txtLName.getText(), 1, "blah");
	}
}
