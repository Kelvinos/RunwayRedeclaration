package View;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;

import Model.Obstacle;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateObstacles extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, mainPanel;
	private JTextField nameText, heightText, cThreshText, lThreshText, rThreshText;
	private Obstacle obstacle;

	public static void main (String args[]) {
		new CreateObstacles();
	}
	
	public CreateObstacles(Obstacle o) {
		this();
		obstacle = o;
		nameText.setEnabled(false);
		nameText.setText(o.getName());
		heightText.setText(Integer.toString(o.getHeight()));
		lThreshText.setText(Integer.toString(o.getDflt()));
		rThreshText.setText(Integer.toString(o.getDfrt()));
		cThreshText.setText(Integer.toString(o.getDfc()));
	}
	
	public CreateObstacles() {
		setSize(250, 300);
		setTitle("Obstacle Manager");
		
		createMainPanel();
		createTitlePanel();
		createNamePanel();
		createHeightPanel();
		createDistanceLThresh();
		createDistanceRThresh();
		createDistanceCThresh();
		createButtonPanel();
		
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	public void createMainPanel() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		mainPanel = new JPanel();
		contentPane.add(mainPanel, BorderLayout.NORTH);
		mainPanel.setLayout(new GridLayout(7, 1, 0, 0));
	}
	
	public void createTitlePanel() {
		JPanel titlePanel = new JPanel();
		mainPanel.add(titlePanel);
		titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel titleLabel = new JLabel("Create / Edit an Obstacle");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		titlePanel.add(titleLabel);
	}
	
	public void createNamePanel() {
		JPanel namePanel = new JPanel();
		mainPanel.add(namePanel);
		namePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel nameLabel = new JLabel("Name");
		namePanel.add(nameLabel);
		nameText = new JTextField();
		namePanel.add(nameText);
		nameText.setColumns(10);
	}
	
	public void createHeightPanel() {
		JPanel heightPanel = new JPanel();
		mainPanel.add(heightPanel);
		heightPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel heightLabel = new JLabel("Height");
		heightPanel.add(heightLabel);
		heightText = new JTextField();
		heightText.setColumns(5);
		heightPanel.add(heightText);
	}
	
	public void createDistanceLThresh() {
		JPanel thresholdLPanel = new JPanel();
		mainPanel.add(thresholdLPanel);
		thresholdLPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel thresholdLabel = new JLabel("Distance Left Threshold");
		thresholdLPanel.add(thresholdLabel);
		lThreshText = new JTextField();
		lThreshText.setColumns(5);
		thresholdLPanel.add(lThreshText);
	}
	
	public void createDistanceRThresh() {
		JPanel thresholdRPanel = new JPanel();
		mainPanel.add(thresholdRPanel);
		thresholdRPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel thresholdLabel = new JLabel("Distance Right Threshold");
		thresholdRPanel.add(thresholdLabel);
		rThreshText = new JTextField();
		rThreshText.setColumns(5);
		thresholdRPanel.add(rThreshText);
	}
	
	public void createDistanceCThresh() {
		JPanel centerlinePanel = new JPanel();
		mainPanel.add(centerlinePanel);
		centerlinePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel centerlineLabel = new JLabel("Distance Centerline");
		centerlinePanel.add(centerlineLabel);
		cThreshText = new JTextField();
		cThreshText.setColumns(5);
		centerlinePanel.add(cThreshText);
	}
	
	public void createButtonPanel() {
		JPanel buttonPanel = new JPanel();
		mainPanel.add(buttonPanel);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton addButton = new JButton("Save");
		buttonPanel.add(addButton);

		JButton cancelButton = new JButton("Cancel");
		buttonPanel.add(cancelButton);

		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (nameText.getText().equals("")
						|| heightText.getText().equals("")
						|| cThreshText.getText().equals("")
						|| lThreshText.getText().equals("")
						|| rThreshText.getText().equals("")) {
					JOptionPane.showMessageDialog(null,
							"Fill in all the boxes", "InfoBox: ",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					try {

						if (MainGUI.main.obstacleExists(obstacle)) {

							//obstacle.setName(nameText.getText());
							obstacle.setHeight(Integer.parseInt(heightText
									.getText()));
							obstacle.setDfc(Integer.parseInt(cThreshText
									.getText()));
							obstacle.setDflt(Integer.parseInt(lThreshText
									.getText()));
							obstacle.setDfrt(Integer.parseInt(rThreshText
									.getText()));
							System.out.println("Editing Obstacle");

						} else {
							obstacle = new Obstacle(
									nameText.getText(),
									Integer.parseInt(heightText.getText()),
									Integer.parseInt(lThreshText.getText()),
									Integer.parseInt(rThreshText.getText()),
									Integer.parseInt(cThreshText.getText()));
							MainGUI.airport.newObstacle(obstacle.getName(), obstacle.getHeight(),
									obstacle.getDflt(), obstacle.getDfrt(), obstacle.getDfc());
							System.out.println("Created a new Obstacle");
						}

						MainGUI.main.updateObstacleBox();
						MainGUI.currentObstacle.setSelectedItem(obstacle
								.getName());
						MainGUI.main.updateObstaclePanel();
						dispose();
					} catch (Exception e1) {
						System.err.println(e1);
					}
				}
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}