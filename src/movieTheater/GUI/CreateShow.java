package movieTheater.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import movieTheater.Show.Show;
import movieTheater.main.MovieController;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;

public class CreateShow extends JFrame
{
	private Show show;
	private MaskFormatter ShowStart;
	private MaskFormatter ShowEnd;
	
	public CreateShow(final Show show) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setTitle("Opret Forestilling");
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JComboBox comboBoxFilm = new JComboBox(MovieController.movies.toArray());
		comboBoxFilm.setBounds(107, 86, 85, 20);
		panel.add(comboBoxFilm);
		
		JLabel lblVlgFillm = new JLabel("V\u00E6lg film");
		lblVlgFillm.setBounds(36, 89, 46, 14);
		panel.add(lblVlgFillm);
		
		JLabel lblVlgSal = new JLabel("Start tid");
		lblVlgSal.setBounds(36, 120, 46, 14);
		panel.add(lblVlgSal);
		
		JComboBox comboBoxPris = new JComboBox();
		comboBoxPris.setModel(new DefaultComboBoxModel(new String[] {"1", "1.5", "2"}));
		comboBoxPris.setToolTipText("1 - Normal, 1.5 Medium, 2 - Peak");
		comboBoxPris.setMaximumRowCount(3);
		comboBoxPris.setBounds(107, 148, 85, 20);
		panel.add(comboBoxPris);
		
		JLabel lblVlgPris = new JLabel("V\u00E6lg pris");
		lblVlgPris.setBounds(36, 151, 46, 14);
		panel.add(lblVlgPris);
		
		JButton btnOpret = new JButton("Næste");
		btnOpret.setBounds(135, 228, 89, 23);
		panel.add(btnOpret);
		
		JButton btnAnnuller = new JButton("Annuller");
		btnAnnuller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAnnuller.setBounds(36, 228, 89, 23);
		panel.add(btnAnnuller);
		
		JLabel lblOpretForestilling = new JLabel("Opret Forestilling");
		lblOpretForestilling.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblOpretForestilling.setBounds(10, 11, 115, 14);
		panel.add(lblOpretForestilling);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(107, 117, 85, 20);
		panel.add(formattedTextField);
	}
}
