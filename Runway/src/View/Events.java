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
import javax.swing.JTextArea;
import javax.swing.BoxLayout;

public class Events extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton exportButton, cancelButton;

	/**
	 * Create the frame.
	 */
	public Events() {
		setSize(400, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridLayout(1, 1, 0, 0));
		setContentPane(contentPane);

		JPanel mainPanel = new JPanel();
		contentPane.add(mainPanel);
		mainPanel.setLayout(new BorderLayout(0, 0));

		JPanel titlePanel = new JPanel();
		mainPanel.add(titlePanel, BorderLayout.NORTH);

		JLabel titleLabel = new JLabel("Events");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		titlePanel.add(titleLabel);

		JTextArea textArea = new JTextArea();
		textArea.setColumns(1);
		textArea.setEditable(false);
		textArea.setWrapStyleWord(true);
		textArea.setRows(1);

		JScrollPane scrollPanel = new JScrollPane(textArea);
		mainPanel.add(scrollPanel, BorderLayout.CENTER);
		scrollPanel
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		JPanel buttonPanel = new JPanel();
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		exportButton = new JButton("Export");
		buttonPanel.add(exportButton);
		exportButton.setEnabled(false);

		cancelButton = new JButton("Cancel");
		buttonPanel.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}

		});
		setLocationRelativeTo(null);
	}
}