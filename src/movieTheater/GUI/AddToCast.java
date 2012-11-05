package movieTheater.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import movieTheater.Movie.Actor;
import movieTheater.Movie.Cast;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class AddToCast extends JFrame
{

	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblActor;
	private JTextField textField;
	private Actor actor;
	private Cast cast;
	private JButton btnAbort;
	private JButton btnCreate;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args)
//	{
//		EventQueue.invokeLater(new Runnable()
//		{
//			public void run()
//			{
//				try
//				{
//					AddToCast frame = new AddToCast();
//					frame.setVisible(true);
//				} catch (Exception e)
//				{
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public AddToCast(Actor actor, Cast cast)
	{
		this.actor = actor;
		this.cast = cast;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 198);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		lblActor = new JLabel(actor.getLName() + " " + actor.getFName());
		lblActor.setBounds(67, 72, 98, 21);
		panel.add(lblActor);
		
		textField = new JTextField();
		textField.setBounds(175, 72, 119, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		btnAbort = new JButton("Annuller");
		btnAbort.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
				AddToCast.this.dispose();
			} 
		});
		btnAbort.setBackground(Color.RED);
		btnAbort.setBounds(314, 88, 100, 23);
		panel.add(btnAbort);
		
		btnCreate = new JButton("Opret rolle");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					
				}
				catch (Exception e)
				{
					
				}
			}
		});
		btnCreate.setBackground(Color.GREEN);
		btnCreate.setBounds(314, 116, 100, 23);
		panel.add(btnCreate);
		
		lblNewLabel = new JLabel("Tilf\u00F8j til cast");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 11, 124, 21);
		panel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Indtast rolle");
		lblNewLabel_1.setBounds(175, 47, 91, 14);
		panel.add(lblNewLabel_1);
	}
}
