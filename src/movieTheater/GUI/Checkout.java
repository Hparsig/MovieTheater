package movieTheater.GUI;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import movieTheater.main.BookingController;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Checkout extends JFrame {
	
	private JTextField textField;
	double whatever = 0;
	
	public Checkout() {
		setTitle("Checkout");
		
		final JLabel sum = new JLabel("To pay: "+ whatever);
		sum.setBounds(10, 73, 86, 20);
		
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				whatever = BookingController.amount - Integer.parseInt(textField.getText());
				sum.setText("To pay: " + whatever);
				sum.validate();
				sum.repaint();
				System.out.println("Hlæsjdkf");
			}
		});
		
		textField.setBounds(10, 42, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel amount = new JLabel(""+BookingController.amount);
		amount.setBounds(10, 11, 86, 20);
		panel.add(amount);
		panel.add(sum);

		
	}
}

//textField.addKeyListener(new KeyListener() {
//	
//
//	@Override
//	public void keyPressed(KeyEvent arg0) {
//		// TODO Auto-generated method stub
//		System.out.println(arg0.getKeyCode());
//		if(arg0.getKeyCode() == 10){
//		whatever = BookingController.amount - Integer.parseInt(textField.getText());
//		sum.setText("To pay: " + whatever);
//		sum.validate();
//		sum.repaint();
//		System.out.println("Hlæsjdkf");
//		}
//	}
//
//	@Override
//	public void keyReleased(KeyEvent arg0) {
//		// TODO Auto-generated method stub
//		System.out.println("Released");
//	}
//
//	@Override
//	public void keyTyped(KeyEvent arg0) {
//		// TODO Auto-generated method stub
//		System.out.println("typed!");
//	}
//});

