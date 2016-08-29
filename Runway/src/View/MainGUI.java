package View;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.AbstractMap.SimpleEntry;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import Controller.CalculationMethods;
import Controller.CalculationResult;
import Model.*;

public class MainGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	public static MainGUI main;
	public static Airport airport;
	public static JComboBox<String> currentObstacle, currentRunway;
	private JToggleButton viewTopBtn, viewSideBtn;
	private Container c;
	private JPanel topPanel, viewButtonsPanel, bottomPanel, runwayPanel,
	runwayLabelPanel, menuPanel, obstaclePanel, runwayTopPanel;
	private JButton importRunwaysBtn, exportRunwaysBtn, importObstaclesBtn, exportObstaclesBtn,
	viewCalcBtn, eventsBtn, helpBtn, newRunwayBtn, toggleObstacleBtn, newObstacleBtn, 
	editObstacleBtn, deleteObstacleBtn, editRunwayBtn, deleteRunwayBtn, switchRunwayBtn;
	private JLabel runwayLabelNew, runwayLabelOld, obstacleLabel, runwayTitle,
	obstacleTitle;
	private DisplayPanel displayPanel;
	public boolean obstacleSwitch, showR;

	public MainGUI() {
		super("Runway Re-declaration Tool");

		setLayout(new BorderLayout());
		setSize(800, 600);
		setResizable(false);

		initialisePanels();
		createSwingComponents();
		addSwingComponents();

		initialiseRunways();
		intialiseObstacles();
		updateAll();

		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void initialiseRunways() 
	{
		try 
		{
			airport.addRunway(new LogicalRunway(9, 'C', 3902,
					3902, 3902, 3595, 306), null);
			airport.addRunway(new LogicalRunway(9, 'L', 3902,
					3562, 3542, 3595, 297), new LogicalRunway(27, 'R', 3884,
							3962, 3884, 3884, 0));

		} 
		catch (Exception e) 
		{
			System.err.println(e);
		}
	}

	public void intialiseObstacles() 
	{
		try 
		{
			airport.newObstacle("Obstacle 1", 10, 10, 10, 10);
			airport.newObstacle("Obstacle 2", 25, 12, 12, 12);
			airport.newObstacle("Obstacle 3", 5, 5, 5, 5);
		} 
		catch (Exception e) 
		{
			System.err.println(e);
		}
	}

	public void initialisePanels() {
		// Initialise Panels
		topPanel = new JPanel(new FlowLayout());
		viewButtonsPanel = new JPanel(new GridLayout(0, 3));
		bottomPanel = new JPanel(new FlowLayout());
		menuPanel = new JPanel(new GridLayout(6, 0));
		menuPanel.setPreferredSize(new Dimension(200, 200));
		runwayPanel = new JPanel(new GridLayout(2, 0));
		runwayPanel.setPreferredSize(new Dimension(300, 200));
		runwayTopPanel = new JPanel(new FlowLayout());
		runwayLabelPanel = new JPanel(new GridLayout(1, 2));
		obstaclePanel = new JPanel(new FlowLayout());
		obstaclePanel.setPreferredSize(new Dimension(300, 200));

		// The GUI Display Panel
		displayPanel = new DisplayPanel();
		displayPanel.setPreferredSize(new Dimension(800, 500));
		displayPanel.setBackground(Color.BLACK);
	}

	public void createSwingComponents() {
		// Create Swing Components
		viewTopBtn = new JToggleButton("TOP");
		viewSideBtn = new JToggleButton("SIDE");
		viewTopBtn.setSelected(true);
		importRunwaysBtn = new JButton("Import Runways");
		exportRunwaysBtn = new JButton("Export Runways");
		importObstaclesBtn = new JButton("Import Obstacles");
		exportObstaclesBtn = new JButton("Export Obstacles");
		viewCalcBtn = new JButton("Calculations");
		eventsBtn = new JButton("Events");
		helpBtn = new JButton("Help");
		toggleObstacleBtn = new JButton("Add");
		newObstacleBtn = new JButton("New");
		editObstacleBtn = new JButton("Edit");
		deleteObstacleBtn = new JButton("Delete");
		newRunwayBtn = new JButton("New");
		editRunwayBtn = new JButton("Edit");
		deleteRunwayBtn = new JButton("Delete");
		switchRunwayBtn = new JButton("Switch");
		try
		{
			airport = new Airport();
		} 
		catch (Exception e)
		{
			System.err.println("Problem creating underlying runway: " + e);
			System.exit(-1);
		}
		currentRunway = new JComboBox<String>();
		currentRunway.setPreferredSize(new Dimension(300, 20));
		runwayLabelNew = new JLabel();
		runwayLabelNew.setVerticalAlignment(JLabel.TOP);
		runwayLabelNew.setPreferredSize(new Dimension(300, 100));
		runwayLabelOld = new JLabel();
		runwayLabelOld.setVerticalAlignment(JLabel.TOP);
		runwayLabelOld.setPreferredSize(new Dimension(300, 100));
		currentObstacle = new JComboBox<String>();
		currentObstacle.setPreferredSize(new Dimension(300, 20));
		obstacleLabel = new JLabel();
		obstacleLabel.setVerticalAlignment(JLabel.TOP);
		obstacleLabel.setPreferredSize(new Dimension(300, 200));
		runwayTitle = new JLabel(
				"<html><span style=\"font-size:14px;\">Runway</span></html>");
		runwayTitle.setPreferredSize(new Dimension(300, 25));
		runwayTitle.setHorizontalAlignment(SwingConstants.CENTER);
		obstacleTitle = new JLabel(
				"<html><span style=\"font-size:14px;\">Obstacles</span</html>");
		obstacleTitle.setPreferredSize(new Dimension(300, 25));
		obstacleTitle.setHorizontalAlignment(SwingConstants.CENTER);
	}

	public void addSwingComponents() {
		// Add Swing Components to Content Pane
		c = getContentPane();
		c.add(topPanel, BorderLayout.NORTH);
		c.add(displayPanel, BorderLayout.CENTER);
		c.add(bottomPanel, BorderLayout.SOUTH);

		viewButtonsPanel.add(viewTopBtn);
		viewButtonsPanel.add(viewSideBtn);
		// viewButtonsPanel.add(viewMapBtn);
		topPanel.add(viewButtonsPanel);

		bottomPanel.add(menuPanel);
		bottomPanel.add(runwayPanel);
		bottomPanel.add(obstaclePanel);

		menuPanel.add(importRunwaysBtn);
		menuPanel.add(exportRunwaysBtn);
		menuPanel.add(importObstaclesBtn);
		menuPanel.add(exportObstaclesBtn);
		// This can be implemented in increment 3
		//		menuPanel.add(viewCalcBtn);
		//		menuPanel.add(eventsBtn);
		//		menuPanel.add(helpBtn);

		runwayTitle.add(runwayLabelNew);
		runwayTopPanel.add(runwayTitle);
		runwayTopPanel.add(currentRunway);
		runwayTopPanel.add(switchRunwayBtn);
		runwayTopPanel.add(newRunwayBtn);
		runwayTopPanel.add(editRunwayBtn);
		runwayTopPanel.add(deleteRunwayBtn);
		runwayLabelPanel.add(runwayLabelNew);
		runwayLabelPanel.add(runwayLabelOld);
		runwayPanel.add(runwayTopPanel);
		runwayPanel.add(runwayLabelPanel);

		obstaclePanel.add(obstacleTitle);
		obstaclePanel.add(currentObstacle);
		obstaclePanel.add(toggleObstacleBtn);
		obstaclePanel.add(newObstacleBtn);
		obstaclePanel.add(editObstacleBtn);
		obstaclePanel.add(deleteObstacleBtn);
		obstaclePanel.add(obstacleLabel);

		initialiseListeners();
	}

	public void initialiseListeners() {

		viewTopBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				viewTopBtn.setSelected(true);
				viewSideBtn.setSelected(false);
				displayPanel.setView("Top");
				displayPanel.repaint();
			}
		});

		viewSideBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				viewTopBtn.setSelected(false);
				viewSideBtn.setSelected(true);
				displayPanel.setView("Side");
				displayPanel.repaint();
			}
		});

		importRunwaysBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					airport.readRunway();
					updateAll();
				} 
				catch (Exception e1)
				{
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Problem occured"
							+ " when importing runways", "Importing Runways",
								JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		exportRunwaysBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					airport.exportRunways();
					updateAll();
				} 
				catch (Exception e1)
				{
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Problem occured"
							+ " when exporting runways", "Exporting Runways",
								JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		importObstaclesBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					airport.readObstacle();
					updateAll();
				} 
				catch (Exception e1)
				{
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Problem occured"
							+ " when importing obstacles", "Importing Obstacles",
								JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		exportObstaclesBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					airport.exportObstacles();
					updateAll();
				} 
				catch (Exception e1)
				{
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Problem occured"
							+ " when exporting obstacles", "Exporting Obstacles",
								JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		viewCalcBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Calculations ex = new Calculations();
				ex.setVisible(true);
			}
		});

		eventsBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Events ex = new Events();
				ex.setVisible(true);
			}
		});

		helpBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Help ex = new Help();
				ex.setVisible(true);
			}
		});

		newRunwayBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateRunway ex = new CreateRunway(false);
				ex.setVisible(true);
			}
		});

		newObstacleBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateObstacles ex = new CreateObstacles();
				ex.setVisible(true);
			}
		});

		editRunwayBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String runwayId = currentRunway.getSelectedItem()
							.toString();
					PhysicalRunway pr = getRunway(runwayId);
					CreateRunway ex = new CreateRunway(pr, pr.isMulti());
					ex.setVisible(true);
					displayPanel.getCurrentRW();
					updateAll();
				} catch (NullPointerException e2) {
				}
			}
		});

		currentRunway.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String runwayId = currentRunway.getSelectedItem()
							.toString();
					PhysicalRunway pr = getRunway(runwayId);
					if (!pr.isMulti()) {
						switchRunwayBtn.setText("Switch");
						switchRunwayBtn.setEnabled(false);
					} else {
						if (showR) {
							switchRunwayBtn.setText("Switch to "
									+ pr.getL().getName());
						} else {
							switchRunwayBtn.setText("Switch to "
									+ pr.getR().getName());
						}
					}
					updateRunwayPanel();
					displayPanel.repaint();
				} catch (NullPointerException ne) {
				}
			}
		});

		currentObstacle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					updateObstaclePanel();
				} catch (NullPointerException ne) {
				}
			}
		});

		editObstacleBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String obstacleName = currentObstacle.getSelectedItem()
							.toString();
					Obstacle o = getObstacle(obstacleName);

					CreateObstacles ex = new CreateObstacles(o);
					ex.setVisible(true);
					SwingUtilities.invokeLater(new Runnable()
					{
						
						@Override
						public void run()
						{
							MainGUI.main.updateAll();
						}
					});

				} catch (NullPointerException e2) {
				}
			}
		});

		deleteObstacleBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String obstacleName = currentObstacle.getSelectedItem()
							.toString();
					Obstacle o = getObstacle(obstacleName);

					@SuppressWarnings("unused")
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog(null,
							"Delete " + obstacleName + "?", "Confirm Deletion",
							2);
					if (dialogResult == 0)
					{
						try
						{
							airport.removeObstacle(o.getName());
							obstacleSwitch = false;
							displayPanel.obs = null;
							displayPanel.repaint();
							toggleObstacleBtn.setText("Add");
						} 
						catch (Exception e1)
						{
							e1.printStackTrace();
						}
					}
					updateObstacleBox();
					updateObstaclePanel();

					if (airport.listOfObstacles().isEmpty()) 
					{
						displayPanel.obs = null;
						displayPanel.repaint();
						toggleObstacleBtn.setText("Add");
					}
				} catch (NullPointerException ne) {
				}
			}
		});

		deleteRunwayBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try 
				{
					String runwayId = currentRunway.getSelectedItem()
							.toString();
					PhysicalRunway pr = getRunway(runwayId);

					@SuppressWarnings("unused")
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog(null,
							"Delete " + runwayId + "?", "Confirm Deletion", 2);
					if (dialogResult == 0)
					{
						try
						{
							airport.removeRunway(pr.getName());
							obstacleSwitch = false;
							displayPanel.obs = null;
							displayPanel.repaint();
							toggleObstacleBtn.setText("Add");
						} 
						catch (Exception e1)
						{
							e1.printStackTrace();
						}
					}

					if (airport.listOfRunways().isEmpty()) 
					{
						switchRunwayBtn.setText("Switch");
						switchRunwayBtn.setEnabled(false);
					}

					updateRunwayBox();
					updateRunwayPanel();
					displayPanel.repaint();
				} 
				catch (NullPointerException ne) 
				{
				}
			}
		});

		switchRunwayBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String runwayId = currentRunway.getSelectedItem().toString();
				PhysicalRunway pr = getRunway(runwayId);
				showR = !showR;
				if (showR) {
					switchRunwayBtn.setText("Switch to " + pr.getL().getName());
				} else {
					switchRunwayBtn.setText("Switch to " + pr.getR().getName());
				}
				updateRunwayPanel();
				displayPanel.repaint();
			}
		});

		toggleObstacleBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (airport.listOfRunways().isEmpty()) 
				{
					JOptionPane.showMessageDialog(null, "No runways exist!",
							"Invalid Runway", 2);
					return;
				}
				if (airport.listOfObstacles().isEmpty()) 
				{
					JOptionPane.showMessageDialog(null, "No obstacles exist!",
							"Invalid Obstacle", 2);
				} 
				else 
				{
					if (!obstacleSwitch) 
					{
						displayPanel.obs = getObstacle(currentObstacle
								.getSelectedItem().toString());
						toggleObstacleBtn.setText("Remove");
					} 
					else 
					{
						displayPanel.obs = null;
						toggleObstacleBtn.setText("Add");
					}
					obstacleSwitch = !obstacleSwitch;
					updateAll();
				}
				displayPanel.repaint();
			}
		});

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					for (LookAndFeelInfo info : UIManager
							.getInstalledLookAndFeels()) {
						if ("Nimbus".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
				} catch (Exception e) {
				}
				main = new MainGUI();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	public PhysicalRunway getRunway(String id) 
	{
		try
		{
			return airport.getRunway(id);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}

	}

	public Obstacle getObstacle(String name) 
	{
		try
		{
			return airport.getObstacle(name);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public boolean runwayIdExists(String name) 
	{
		try
		{
			return airport.getRunway(name) != null;
		} 
		catch (Exception e)
		{
			return false;
		}
	}

	public boolean runwayExists(PhysicalRunway p) 
	{
		try
		{
			return airport.getRunway(p.getName()) != null;
		} 
		catch (Exception e)
		{
			return false;
		}
	}

	public boolean obstacleExists(Obstacle ob) 
	{
		try
		{
			return airport.getObstacle(ob.getName()) !=null;
		} 
		catch (Exception e)
		{
			return false;
		}
	}

	public void updateAll() 
	{
		updateRunwayBox();
		updateRunwayPanel();
		updateObstacleBox();
		updateObstaclePanel();
		displayPanel.repaint();
	}

	public void updateRunwayBox() 
	{
		currentRunway.removeAllItems();
		for (String pr : airport.listOfRunways())
			currentRunway.addItem(pr);
	}

	public void updateObstacleBox() 
	{
		currentObstacle.removeAllItems();
		for (String o : airport.listOfObstacles())
			currentObstacle.addItem(o);
	}


	public void updateRunwayPanel() 
	{
		int toda = 0;
		int tora = 0;
		int asda = 0;
		int lda = 0;
		int dt = 0;
		int ntoda = 0;
		int ntora = 0;
		int nasda = 0;
		int nlda = 0;
		int ndt = 0;
		PhysicalRunway pr;

		if (airport.listOfRunways().isEmpty())
		{
			System.out.println("Runway empty");
			toda = tora = asda = lda = dt = 0;
		}
		else 
		{
			String runwayId = currentRunway.getSelectedItem().toString();
			pr = getRunway(runwayId);
			LogicalRunway lrl = pr.getL();
			LogicalRunway lrr = pr.getR();

			if(obstacleSwitch == false)
			{
				if (!pr.isMulti() || pr.isMulti() && !showR) 
				{
					toda = lrl.getToda();
					tora = lrl.getTora();
					asda = lrl.getAsda();
					lda = lrl.getLda();
					dt = lrl.getThreshold();
					ntoda = lrl.getToda();
					ntora = lrl.getTora();
					nasda = lrl.getAsda();
					nlda = lrl.getLda();
					ndt = lrl.getThreshold();
				} 
				else if (pr.isMulti() && showR) 
				{
					toda = lrr.getToda();
					tora = lrr.getTora();
					asda = lrr.getAsda();
					lda = lrr.getLda();
					dt = lrr.getThreshold();
					ntoda = lrr.getToda();
					ntora = lrr.getTora();
					nasda = lrr.getAsda();
					nlda = lrr.getLda();
					ndt = lrr.getThreshold();
				} 
				else 
				{
					toda = tora = asda = lda = dt = 0;
				}
			}
			else
			{
				try
				{
					SimpleEntry<CalculationResult, CalculationResult> result = 
							CalculationMethods.redeclare(pr,getObstacle(currentObstacle
									.getSelectedItem().toString()));
					if (!pr.isMulti() || pr.isMulti() && !showR) 
					{
						CalculationResult left = result.getKey();
						ntoda = left.getUpdatedToda();
						ntora = left.getUpdatedTora();
						nasda = left.getUpdatedAsda();
						nlda = left.getUpdatedLda();
						ndt = lrl.getThreshold();
						toda = lrl.getToda();
						tora = lrl.getTora();
						asda = lrl.getAsda();
						lda = lrl.getLda();
						dt = lrl.getThreshold();
					} 
					else if (pr.isMulti() && showR) 
					{
						CalculationResult right = result.getKey();
						ntoda = right.getUpdatedToda();
						ntora = right.getUpdatedTora();
						nasda = right.getUpdatedAsda();
						nlda = right.getUpdatedLda();
						ndt = lrr.getThreshold();
						toda = lrr.getToda();
						tora = lrr.getTora();
						asda = lrr.getAsda();
						lda = lrr.getLda();
						dt = lrr.getThreshold();
					} 
					else 
					{
						ntoda = ntora = nasda = nlda = ndt = 0;
					}
				}
				catch(Exception e)
				{
					System.err.println("Error get updated values: " + e.getMessage());
				}
			

			if (!pr.isMulti())
				switchRunwayBtn.setEnabled(false);
			else
				switchRunwayBtn.setEnabled(true);

		}
	}

		runwayLabelNew.setText("<html><center><b><u> New </u></b></center>"
				+ "<b>TODA: &nbsp &nbsp &nbsp &nbsp &nbsp </b>" + ntoda + "m"
				+ "<br/><b>TORA: &nbsp &nbsp &nbsp &nbsp &nbsp </b>" + ntora
				+ "m" + "<br/><b>ASDA: &nbsp &nbsp &nbsp &nbsp &nbsp </b>"
				+ nasda + "m"
				+ "<br/><b>LDA : &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp </b>"
				+ nlda + "m" + "<br/><b>Threshold: &nbsp </b>" + ndt + "m"
				+ "</html>");
		runwayLabelNew.setHorizontalAlignment(SwingConstants.CENTER);

		runwayLabelOld.setText("<html><center><b><u> Old </u></b></center>"
				+ "<b>TODA: &nbsp &nbsp &nbsp &nbsp &nbsp </b>" + toda + "m"
				+ "<br/><b>TORA: &nbsp &nbsp &nbsp &nbsp &nbsp </b>" + tora
				+ "m" + "<br/><b>ASDA: &nbsp &nbsp &nbsp &nbsp &nbsp </b>"
				+ asda + "m"
				+ "<br/><b>LDA : &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp </b>"
				+ lda + "m" + "<br/><b>Threshold: &nbsp </b>" + dt + "m"
				+ "</html>");
		runwayLabelOld.setHorizontalAlignment(SwingConstants.CENTER);
		SwingUtilities.invokeLater(new Runnable()
		{
			
			@Override
			public void run()
			{
				repaint();
			}
		});
	}

	public void updateObstaclePanel() 
	{

		int height, lthresh, rthresh, cthresh;

		if (airport.listOfObstacles().isEmpty())
			height = lthresh = rthresh = cthresh = 0;

		else 
		{
			String obstacleName = currentObstacle.getSelectedItem().toString();
			Obstacle o = getObstacle(obstacleName);
			height = o.getHeight();
			lthresh = o.getDflt();
			rthresh = o.getDfrt();
			cthresh = o.getDfc();
		}

		obstacleLabel
		.setText("<html><b>Height : &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp </b>"
				+ height
				+ "m"
				+ "<br/><b>Dist. L. Thresh: &nbsp &nbsp &nbsp </b>"
				+ lthresh
				+ "m"
				+ "<br/><b>Dist. R. Thresh: &nbsp &nbsp &nbsp </b>"
				+ rthresh
				+ "m"
				+ "<br/><b>Dist. C. Thresh: &nbsp &nbsp &nbsp </b>"
				+ cthresh + "m" + "</html>");
		obstacleLabel.setHorizontalAlignment(SwingConstants.CENTER);
	}

	/*
	 * Pass a runway code for example '09C' with the updated values
	 */
	public void updateRunwayC(String runwayId, int tora, int toda, int asda, int lda, int threshold) 
			throws Exception{
		PhysicalRunway pr = getRunway(runwayId);
		LogicalRunway lr = pr.getL();
		lr.setTora(tora);
		lr.setToda(toda);
		lr.setAsda(asda);
		lr.setLda(lda);
		lr.setThreshold(threshold);
	}

	/*
	 * Pass a dual runway code for example '09L / 27R' with the updated values for both runways
	 */
	public void updateRunwayLR(String runwayId, int toraL, int todaL, int asdaL, int ldaL, int thresholdL,
			int toraR, int todaR, int asdaR, int ldaR, int thresholdR) throws Exception {
		PhysicalRunway pr = getRunway(runwayId);
		LogicalRunway lr = pr.getL();
		LogicalRunway rr = pr.getR();
		lr.setTora(toraL);
		lr.setToda(todaL);
		lr.setAsda(asdaL);
		lr.setLda(ldaL);
		lr.setThreshold(thresholdL);
		rr.setTora(toraR);
		rr.setToda(todaR);
		rr.setAsda(asdaR);
		rr.setLda(ldaR);
		rr.setThreshold(thresholdR);
	}

	/*
	 * Add runways and obstacles if already have
	 */
	public void addRunway(PhysicalRunway pr) 
	{
		try
		{
			airport.addRunway(pr.getL(), pr.getR());
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}	
	public void addObstacles(Obstacle o) 
	{
		try
		{
			airport.newObstacle(o.getName(), o.getHeight(), o.getDflt(), o.getDfrt(), o.getDfc());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
