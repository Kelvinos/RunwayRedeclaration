package View;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class Help extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Help() {
		setSize(400, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		setContentPane(contentPane);

		JPanel mainPanel = new JPanel();
		contentPane.add(mainPanel);
		mainPanel.setLayout(new GridLayout(4, 0, 0, 0));

		JPanel titlePanel = new JPanel();
		mainPanel.add(titlePanel);

		JLabel titleLabel = new JLabel("Help");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		titlePanel.add(titleLabel);

		JPanel textPanel = new JPanel();
		mainPanel.add(textPanel);

		JLabel textLabel = new JLabel(
				"This will describe the system and how it works.");
		textPanel.add(textLabel);

		setLocationRelativeTo(null);
	}
}