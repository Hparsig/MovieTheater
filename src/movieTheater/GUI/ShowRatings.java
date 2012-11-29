package movieTheater.GUI;

import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class ShowRatings extends JFrame
{

	private JPanel contentPane;
	private JPanel panel;
	private TextArea textArea;
	private JButton btnOk;

	/**
	 * Create the frame.
	 */
	public ShowRatings(String text)
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textArea = new TextArea("Anmeldelser:", 1 , 1 , TextArea.SCROLLBARS_VERTICAL_ONLY); //the way to wrap text. 
		textArea.setText(text);
		textArea.setBounds(10, 10, 404, 204);
		panel.add(textArea);
		
		btnOk = new JButton("ok");
		btnOk.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			} 
		});
		btnOk.setBounds(325, 220, 89, 23);
		panel.add(btnOk);
	}
}
