package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Model.LogicalRunway;
import Model.PhysicalRunway;

public class CreateRunway extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, mainPanel;
	private JTextField runwayTextL, todaTextL, toraTextL, asdaTextL, ldaTextL,
			thresholdTextL, runwayTextR, todaTextR, toraTextR, asdaTextR,
			ldaTextR, thresholdTextR;
	private JComboBox<String> runwayBox;
	private PhysicalRunway runway;
	public boolean multi = false;
	
	public CreateRunway(boolean multi) {
		this.multi = multi;
		setTitle("Runway Manager");
		setSize(300, 400);
		setUpGUI();
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	public CreateRunway(PhysicalRunway pr, boolean multi) {
		this(multi);
		
		runway = pr;
		runwayBox.setSelectedItem(String.valueOf(pr.getL().getCharacter()));
		runwayBox.setEnabled(false);
		runwayTextL.setText(Integer.toString(pr.getL().getDesgniatorNum()));
		runwayTextL.setEnabled(false);
		todaTextL.setText(Integer.toString(pr.getL().getTora()));
		toraTextL.setText(Integer.toString(pr.getL().getToda()));
		asdaTextL.setText(Integer.toString(pr.getL().getAsda()));
		thresholdTextL.setText(Integer.toString(pr.getL().getThreshold()));
		ldaTextL.setText(Integer.toString(pr.getL().getLda()));
		
		if (multi) {
			runwayTextR.setText(Integer.toString(pr.getR().getDesgniatorNum()));
			runwayTextR.setEnabled(false);
			todaTextR.setText(Integer.toString(pr.getR().getTora()));
			toraTextR.setText(Integer.toString(pr.getR().getToda()));
			asdaTextR.setText(Integer.toString(pr.getR().getAsda()));
			thresholdTextR.setText(Integer.toString(pr.getR().getThreshold()));
			ldaTextR.setText(Integer.toString(pr.getR().getLda()));
		}


	}
	
	public void setUpGUI() {
		createMainPanel();
		createTitleSection();
		createTypeSection();
		createNumberSection();
		createTodaSection();
		createToraSection();
		createAsdaSection();
		createThresholdSection();
		createLdaSection();
		createButtonSection();
	}
	
	public void createMainPanel() {
		try {
			contentPane.removeAll();
		} catch (Exception e) {
		}
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		mainPanel = new JPanel();
		contentPane.add(mainPanel, BorderLayout.NORTH);
		mainPanel.setLayout(new GridLayout(10, 1, 0, 0));
	}
	
	public void createTitleSection() {
		JPanel titlePanel = new JPanel();
		mainPanel.add(titlePanel);
		titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel titleLabel = new JLabel("Create / Edit a Runway");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		titlePanel.add(titleLabel);
	}

	public void createTypeSection() {
		JPanel selectPanel = new JPanel();
		mainPanel.add(selectPanel);
		selectPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel runwayTypeLabel = new JLabel("<html><b>Runway Type</b></html>");
		selectPanel.add(runwayTypeLabel);
		runwayBox = new JComboBox<String>();
		runwayBox.addItem("C");
		runwayBox.addItem("L/R");
		
		if (multi)
			runwayBox.setSelectedItem("L/R");
		else
			runwayBox.setSelectedItem("C");
		
		selectPanel.add(runwayBox);
		runwayBox.addActionListener(new RunBoxListener(runwayBox));
	}
	
	public void createNumberSection() {
		JPanel runwayPanel = new JPanel();
		mainPanel.add(runwayPanel);
		runwayPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel runwayLabel = new JLabel(
				"<html><b>Number&nbsp&nbsp&nbsp&nbsp&nbsp</b></html>");
		runwayPanel.add(runwayLabel);
		if (multi) {
			JLabel runwayLLabel = new JLabel("L:");
			runwayPanel.add(runwayLLabel);
		}
		runwayTextL = new JTextField();
		runwayPanel.add(runwayTextL);
		runwayTextL.setColumns(5);
		if (multi) {
			JLabel runwayRLabel = new JLabel("R:");
			runwayPanel.add(runwayRLabel);
			runwayTextR = new JTextField();
			runwayPanel.add(runwayTextR);
			runwayTextR.setColumns(5);
		}
	}
	
	public void createTodaSection() {
		JPanel todaPanel = new JPanel();
		mainPanel.add(todaPanel);
		todaPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel todaLabel = new JLabel(
				"<html><b>TODA&nbsp&nbsp&nbsp&nbsp&nbsp</b></html>");
		todaPanel.add(todaLabel);
		if (multi) {
			JLabel todaLLabel = new JLabel("L:");
			todaPanel.add(todaLLabel);
		}
		todaTextL = new JTextField();
		todaTextL.setColumns(5);
		todaPanel.add(todaTextL);
		if (multi) {
			JLabel todaRLabel = new JLabel("R:");
			todaPanel.add(todaRLabel);
			todaTextR = new JTextField();
			todaPanel.add(todaTextR);
			todaTextR.setColumns(5);
		}
	}
	
	public void createToraSection() {
		JPanel toraPanel = new JPanel();
		mainPanel.add(toraPanel);
		toraPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel toraLabel = new JLabel(
				"<html><b>TORA&nbsp&nbsp&nbsp&nbsp&nbsp</b></html>");
		toraPanel.add(toraLabel);
		if (multi) {
			JLabel toraLLabel = new JLabel("L");
			toraPanel.add(toraLLabel);
		}
		toraTextL = new JTextField();
		toraTextL.setColumns(5);
		toraPanel.add(toraTextL);
		if (multi) {
			JLabel toraRLabel = new JLabel("R");
			toraPanel.add(toraRLabel);
			toraTextR = new JTextField();
			toraPanel.add(toraTextR);
			toraTextR.setColumns(5);
		}
	}
	
	public void createAsdaSection() {
		JPanel asdaPanel = new JPanel();
		mainPanel.add(asdaPanel);
		asdaPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel asdaLabel = new JLabel(
				"<html><b>ASDA&nbsp&nbsp&nbsp&nbsp&nbsp</b></html>");
		asdaPanel.add(asdaLabel);
		if (multi) {
			JLabel asdaLLabel = new JLabel("L:");
			asdaPanel.add(asdaLLabel);
		}
		asdaTextL = new JTextField();
		asdaTextL.setColumns(5);
		asdaPanel.add(asdaTextL);
		if (multi) {
			JLabel asdaRLabel = new JLabel("R:");
			asdaPanel.add(asdaRLabel);
			asdaTextR = new JTextField();
			asdaPanel.add(asdaTextR);
			asdaTextR.setColumns(5);
		}
	}
	
	public void createThresholdSection() {
		JPanel thresholdPanel = new JPanel();
		mainPanel.add(thresholdPanel);
		thresholdPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel thresholdLabel = new JLabel(
				"<html><b>Threshold&nbsp&nbsp&nbsp&nbsp&nbsp</b></html>");
		thresholdPanel.add(thresholdLabel);
		if (multi) {
			JLabel threshLLabel = new JLabel("L:");
			thresholdPanel.add(threshLLabel);
		}
		thresholdTextL = new JTextField();
		thresholdTextL.setColumns(5);
		thresholdPanel.add(thresholdTextL);
		if (multi) {
			JLabel threshRLabel = new JLabel("R:");
			thresholdPanel.add(threshRLabel);
			thresholdTextR = new JTextField();
			thresholdPanel.add(thresholdTextR);
			thresholdTextR.setColumns(5);
		}
	}
	
	public void createLdaSection() {
		JPanel LdaPanel = new JPanel();
		mainPanel.add(LdaPanel);
		LdaPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel LdaLabel = new JLabel(
				"<html><b>LDA&nbsp&nbsp&nbsp&nbsp&nbsp</b></html>");
		LdaPanel.add(LdaLabel);
		if (multi) {
			JLabel ldaLLabel = new JLabel("L:");
			LdaPanel.add(ldaLLabel);
		}
		ldaTextL = new JTextField();
		ldaTextL.setColumns(5);
		LdaPanel.add(ldaTextL);
		if (multi) {
			JLabel ldaRLabel = new JLabel("R:");
			LdaPanel.add(ldaRLabel);
			ldaTextR = new JTextField();
			LdaPanel.add(ldaTextR);
			ldaTextR.setColumns(5);
		}
	}
	
	public void createButtonSection() {
		JPanel buttonPanel = new JPanel();
		mainPanel.add(buttonPanel);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JButton addButton = new JButton("Save");
		addButton.addActionListener(new AddButtonListener(multi));
		buttonPanel.add(addButton);
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					MainGUI.currentRunway.setSelectedItem(runway.getName());	
				} catch (Exception e1) {}
				dispose();
			}
		});
		buttonPanel.add(cancelButton);
	}
	
	public class AddButtonListener implements ActionListener {

		boolean multi;
		
		public AddButtonListener(boolean multi) {
			this.multi = multi;
		}
		
		public void actionPerformed(ActionEvent e) {
			if (!checkTextFilled(multi))
				JOptionPane.showMessageDialog(null, "Fill in all the fields", "Complete Fields", 2);
			else {
				try {
					if (MainGUI.main.runwayExists(runway))
						editRunwayInfo();
					else
						createRunwayInfo();
					MainGUI.main.updateRunwayBox();
					MainGUI.currentRunway.setSelectedItem(runway.getName());
					MainGUI.main.updateRunwayPanel();
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Only number inputs accepted.", "Incorrect Input", 2);
				} catch (IllegalStateException e2) {
					JOptionPane.showMessageDialog(null, "Runway already exists", "Invalid Runway", 2);
				} catch (Exception e3) {
					JOptionPane.showMessageDialog(null, "Enter a runway between 0 and 36", "Invalid Runway Input", 2);
				}
			}
		}
	}

	public void editRunwayInfo() throws Exception {
		
		LogicalRunway L, R;
		
		L = runway.getL();

//		L.setNumber(Integer.parseInt(runwayTextL.getText()));
		L.setCharacter('C');
		L.setTora(Integer.parseInt(toraTextL.getText()));
		L.setToda(Integer.parseInt(todaTextL.getText()));
		L.setAsda(Integer.parseInt(asdaTextL.getText()));
		L.setLda(Integer.parseInt(ldaTextL.getText()));
		L.setThreshold(Integer.parseInt(thresholdTextL
				.getText()));

		if (runway.isMulti()) {
			L.setCharacter('L');
			R = runway.getR();
//			R.setNumber(Integer.parseInt(runwayTextR.getText()));
			R.setCharacter('R');
			R.setTora(Integer.parseInt(toraTextR.getText()));
			R.setToda(Integer.parseInt(todaTextR.getText()));
			R.setAsda(Integer.parseInt(asdaTextR.getText()));
			R.setLda(Integer.parseInt(ldaTextR.getText()));
			R.setThreshold(Integer.parseInt(thresholdTextR.getText()));
		}
		
		System.out.println("Editing runway");
		dispose();
	}
	
	public void createRunwayInfo() throws Exception {
		
		LogicalRunway L, R;
		PhysicalRunway pr;
		
		char character = 'C';
		if (!multi)
			character = 'C';
		else 
			character = 'L';
		
		L =  new LogicalRunway(Integer.parseInt(runwayTextL
				.getText()),
				character, Integer.parseInt(toraTextL
						.getText()), Integer.parseInt(todaTextL
						.getText()), Integer.parseInt(asdaTextL
						.getText()), Integer.parseInt(ldaTextL
						.getText()),
				Integer.parseInt(thresholdTextL.getText()));
		
		
		if (multi) {
			R =  new LogicalRunway(Integer.parseInt(runwayTextL
					.getText()),
					'R', Integer.parseInt(toraTextR
							.getText()), Integer.parseInt(todaTextR
							.getText()), Integer.parseInt(asdaTextR
							.getText()), Integer.parseInt(ldaTextR
							.getText()),
					Integer.parseInt(thresholdTextR.getText()));
			
			pr = new PhysicalRunway(L, R);
		} else
			pr = new PhysicalRunway(L);

		if (MainGUI.main.runwayIdExists(pr.getName()))
			throw new IllegalStateException();
		else {
				MainGUI.airport.addRunway(pr.getL(),pr.getR());
				runway = pr;
				System.out.println("Created a new runway");
		}

		dispose();
	}

	public class RunBoxListener implements ActionListener {

		JComboBox<String> runwayBox;

		public RunBoxListener(JComboBox<String> runwayBox) {
			this.runwayBox = runwayBox;
		}

		public void actionPerformed(ActionEvent e) {
			if (runwayBox.getSelectedItem().equals("C"))
				multi = false;
			else if (runwayBox.getSelectedItem().equals("L/R"))
				multi = true;
			setUpGUI();
		}
	}

	public boolean checkTextFilled(boolean multi) {
		
		if (runwayTextL.getText().equals("")
				|| todaTextL.getText().equals("")
				|| toraTextL.getText().equals("")
				|| asdaTextL.getText().equals("")
				|| thresholdTextL.getText().equals("")
				|| ldaTextL.getText().equals("")) {
					return false;
				}
		
		if (multi) {
			if (runwayTextL.getText().equals("")
					|| todaTextR.getText().equals("")
					|| toraTextR.getText().equals("")
					|| asdaTextR.getText().equals("")
					|| thresholdTextR.getText().equals("")
					|| ldaTextR.getText().equals("")) {
						return false;
					}
		}
		
		return true;
		
	}
	
}
