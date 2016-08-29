package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.xml.stream.events.StartDocument;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JSeparator;

public class Calculations extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Calculations() {
		setSize(400, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel mainPanel = new JPanel();
		contentPane.add(mainPanel, BorderLayout.NORTH);
		mainPanel.setLayout(new GridLayout(15, 1, 0, 0));

		JPanel titlePanel = new JPanel();
		mainPanel.add(titlePanel);
		titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel titleLabel = new JLabel("View Calculations");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		titlePanel.add(titleLabel);

		JPanel blankPanel1 = new JPanel();
		mainPanel.add(blankPanel1);
		blankPanel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel oldPanel = new JPanel();
		mainPanel.add(oldPanel);
		oldPanel.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel oldLabel = new JLabel("Old Calculations");
		oldPanel.add(oldLabel);

		JSeparator oldSeperator = new JSeparator();
		oldPanel.add(oldSeperator);

		JPanel dataPanel1 = new JPanel();
		mainPanel.add(dataPanel1);
		dataPanel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel dataLabel1 = new JLabel("Test Data 1");
		dataPanel1.add(dataLabel1);

		JPanel dataPanel2 = new JPanel();
		mainPanel.add(dataPanel2);
		dataPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel dataLabel2 = new JLabel("Test Data 2");
		dataPanel2.add(dataLabel2);

		JPanel dataPanel3 = new JPanel();
		mainPanel.add(dataPanel3);
		dataPanel3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel dataLabel3 = new JLabel("Test Data 3");
		dataPanel3.add(dataLabel3);

		JPanel dataPanel4 = new JPanel();
		mainPanel.add(dataPanel4);
		dataPanel4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel dataLabel4 = new JLabel("Test Data 4");
		dataPanel4.add(dataLabel4);

		JPanel dataPanel5 = new JPanel();
		mainPanel.add(dataPanel5);
		dataPanel5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel dataLabel5 = new JLabel("Test Data 5");
		dataPanel5.add(dataLabel5);

		JPanel newPanel = new JPanel();
		mainPanel.add(newPanel);
		newPanel.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel newLabel = new JLabel("New Calculations");
		newPanel.add(newLabel);

		JSeparator newSeperator = new JSeparator();
		newPanel.add(newSeperator);

		JPanel dataPanel6 = new JPanel();
		mainPanel.add(dataPanel6);

		JLabel dataLabel6 = new JLabel("Test Data 6");
		dataPanel6.add(dataLabel6);

		JPanel dataPanel7 = new JPanel();
		mainPanel.add(dataPanel7);

		JLabel dataLabel7 = new JLabel("Test Data 7");
		dataPanel7.add(dataLabel7);

		JPanel dataPanel8 = new JPanel();
		mainPanel.add(dataPanel8);

		JLabel dataLabel8 = new JLabel("Test Data 8");
		dataPanel8.add(dataLabel8);

		JPanel dataPanel9 = new JPanel();
		mainPanel.add(dataPanel9);

		JLabel dataLabel9 = new JLabel("Test Data 9");
		dataPanel9.add(dataLabel9);

		JPanel dataPanel10 = new JPanel();
		mainPanel.add(dataPanel10);

		JLabel dataLabel10 = new JLabel("Test Data 10");
		dataPanel10.add(dataLabel10);

		JPanel buttonPanel = new JPanel();
		mainPanel.add(buttonPanel);

		JButton exportButton = new JButton("Export");
		buttonPanel.add(exportButton);
		exportButton.setEnabled(false);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}

		});
		buttonPanel.add(cancelButton);

		setLocationRelativeTo(null);
	}
}
