package View;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.AbstractMap.SimpleEntry;
import javax.swing.JPanel;

import Controller.CalculationMethods;
import Controller.CalculationResult;
import Model.*;

public class DisplayPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	public static int REAL_RW_LENGTH;
	public static final int RW_LENGTH = 700, RW_WIDTH = 60;
	public static final int RW_START_X = 50, RW_START_Y = 200;
	public static final int RW_END_X = RW_START_X + RW_LENGTH;

	public static final int SIDE_Y = 200;
	public static final int SIDE_RW_HEIGHT = 10;
	public static final int SIDE_HEIGHT_SCALE = 10;

	private String view;
	public LogicalRunway selectedRW, otherRW;
	public boolean showR;
	public Obstacle obs;

	public DisplayPanel() {
		view = "Top";
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D gx = (Graphics2D) g;

		getCurrentRW();

		switch (view) {
		case "Top":
			drawBackgroundTop(gx);
			break;
		case "Side":
			drawBackgroundSide(gx);
			break;
		}

		g.dispose();
	}

	public void getCurrentRW() {
		PhysicalRunway r;
		try 
		{

			r = MainGUI.main.getRunway(MainGUI.currentRunway
					.getSelectedItem().toString());
			if(obs != null)
			{
				SimpleEntry<CalculationResult, CalculationResult> result = CalculationMethods.redeclare(MainGUI.main.getRunway(MainGUI.currentRunway
						.getSelectedItem().toString()), obs);
				if(result.getValue() == null)
				{
					LogicalRunway left = r.getL();
					CalculationResult l = result.getKey();
					r = new PhysicalRunway(new LogicalRunway(left.getDesgniatorNum(),
							left.getCharacter(), l.getUpdatedTora(), l.getUpdatedToda(),
							l.getUpdatedAsda(), l.getUpdatedLda(), left.getThreshold()));
				}
				else
				{
					LogicalRunway left = r.getL();
					CalculationResult l = result.getKey();
					LogicalRunway right = r.getR();
					CalculationResult ri = result.getValue();
					r = new PhysicalRunway(new LogicalRunway(left.getDesgniatorNum(),
							left.getCharacter(), l.getUpdatedTora(), l.getUpdatedToda(),
							l.getUpdatedAsda(), l.getUpdatedLda(), left.getThreshold()),
							new LogicalRunway(right.getDesgniatorNum(),
									right.getCharacter(), ri.getUpdatedTora(), ri.getUpdatedToda(),
									ri.getUpdatedAsda(), ri.getUpdatedLda(), right.getThreshold()));
				}
			}
		} 
		catch ( Exception e) 
		{
			selectedRW = otherRW = null;
			return;
		}
		showR = MainGUI.main.showR;
		if (showR && r.isMulti()) {
			selectedRW = r.getR();
			otherRW = r.getL();
		} else {
			selectedRW = r.getL();
			otherRW = r.getR();
		}
	}

	public void drawBackgroundTop(Graphics2D gx) {
		drawLayout(gx);

		if (selectedRW == null)
			return;

		REAL_RW_LENGTH = selectedRW.getTora();

		int thresholdX = RW_START_X + lengthwrt(selectedRW.getThreshold());
		for (int i = 200; i <= 260; i += 10) {
			gx.drawLine(thresholdX - 20, i, thresholdX, i);
		}
		gx.setFont(new Font("Arial", Font.BOLD, 20));
		gx.drawString(selectedRW.getName(), thresholdX, 235);

		if (otherRW != null) {
			int threshold2X = RW_END_X - lengthwrt(otherRW.getThreshold());
			for (int i = 200; i <= 260; i += 10) {
				gx.drawLine(threshold2X, i, threshold2X + 20, i);
			}
			gx.setFont(new Font("Arial", Font.BOLD, 20));
			gx.drawString(otherRW.getName(), threshold2X - 35, 235);
		}

		if (obs != null) {
			gx.setColor(Color.RED);
			gx.fillOval(
					RW_START_X + lengthwrt(selectedRW.getThreshold())
					+ obs.getDflt() - 10, RW_START_Y + (RW_WIDTH / 2)
					+ obs.getDfc(), 20, 20);
		}

		gx.setColor(Color.WHITE);
		gx.setFont(new Font("Arial", Font.PLAIN, 16));

		// gx.drawLine(RW_START_X, 190, thresholdX, 190);
		// gx.drawString("Threshold", 50, 180);

		drawMeasure(gx, thresholdX, 190, lengthwrt(selectedRW.getLda()), 190);
		gx.drawString("LDA, " + selectedRW.getLda() + "m", thresholdX + 5, 180);

		drawMeasure(gx, thresholdX, 160, lengthwrt(selectedRW.getTora()), 160);
		gx.drawString("TORA, " + selectedRW.getTora() + "m", thresholdX + 5, 150);

		drawMeasure(gx, thresholdX, 130, lengthwrt(selectedRW.getToda()), 130);
		gx.drawString("TODA, " + selectedRW.getToda() + "m", thresholdX + 5, 120);

		drawMeasure(gx, thresholdX, 100, lengthwrt(selectedRW.getAsda()), 100);
		gx.drawString("ASDA, " + selectedRW.getAsda() + "m", thresholdX + 5, 90);
	}

	public void drawLayout(Graphics2D gx) {
		gx.setColor(Color.BLUE);
		gx.fillRect(0, 0, this.getWidth(), this.getHeight());

		// gx.translate(200, -100);
		// gx.rotate(Math.toRadians(45));

		// Polygon p = new Polygon();
		// int curX = RW_START_X - lengthwrt(60);
		// int curY = RW_START_Y - lengthwrt(75);
		// p.addPoint(curX, curY);
		// curX += lengthwrt(60 + 150);
		// p.addPoint(curX, curY);
		// curX += lengthwrt(150);
		// curY -= lengthwrt(105 - 75);
		// p.addPoint(curX, curY);
		// curX += lengthwrt(selectedRW.getLda());
		// p.addPoint(curX, curY);
		// curX += lengthwrt(150);
		// curY += lengthwrt(105 - 75);
		// p.addPoint(curX, curY);
		// curX += lengthwrt(60 + 150);
		// p.addPoint(curX, curY);
		// curY += lengthwrt(150);
		// p.addPoint(curX, curY);
		// curX -= lengthwrt(60 + 150);
		// p.addPoint(curX, curY);
		// curX -= lengthwrt(150);
		// curY += lengthwrt(105 - 75);
		// p.addPoint(curX, curY);
		// curX -= lengthwrt(selectedRW.getLda());
		// p.addPoint(curX, curY);
		// curX -= lengthwrt(150);
		// curY -= lengthwrt(105 - 75);
		// p.addPoint(curX, curY);
		// curX -= lengthwrt(60 + 150);
		// p.addPoint(curX, curY);
		// gx.setColor(Color.GREEN);
		// gx.fillPolygon(p);

		int[] polyX = new int[12];
		int[] polyY = new int[12];
		int n = polyX.length;
		polyX[0] = 20;
		polyY[0] = 150;
		polyX[1] = 100;
		polyY[1] = 150;
		polyX[2] = 150;
		polyY[2] = 100;
		polyX[3] = this.getWidth() - 150;
		polyY[3] = 100;
		polyX[4] = this.getWidth() - 100;
		polyY[4] = 150;
		polyX[5] = this.getWidth() - 20;
		polyY[5] = 150;
		polyX[6] = this.getWidth() - 20;
		polyY[6] = 310;
		polyX[7] = this.getWidth() - 100;
		polyY[7] = 310;
		polyX[8] = this.getWidth() - 150;
		polyY[8] = 360;
		polyX[9] = 150;
		polyY[9] = 360;
		polyX[10] = 100;
		polyY[10] = 310;
		polyX[11] = 20;
		polyY[11] = 310;
		Polygon p = new Polygon(polyX, polyY, n);
		gx.setColor(Color.GREEN);
		gx.fillPolygon(p);

		gx.setColor(Color.GRAY);
		gx.fillRect(RW_START_X, RW_START_Y, RW_LENGTH, RW_WIDTH);

		gx.setColor(Color.WHITE);
		for (int i = 175; i <= 600; i += 25) {
			gx.drawLine(i, 230, i + 15, 230);
		}
	}

	public void drawBackgroundSide(Graphics2D gx) {
		gx.setColor(Color.BLUE);
		gx.fillRect(0, 0, this.getWidth(), this.getHeight());

		gx.setColor(Color.GRAY);
		gx.fillRect(RW_START_X, SIDE_Y, RW_LENGTH, SIDE_RW_HEIGHT);

		if (selectedRW == null)
			return;

		if (obs != null) {
			gx.setColor(Color.RED);

			Polygon o = new Polygon();
			int oX = RW_START_X + lengthwrt(selectedRW.getThreshold())
					+ obs.getDflt();
			int oY = SIDE_Y - (SIDE_HEIGHT_SCALE * lengthwrt(obs.getHeight()));
			o.addPoint(oX, SIDE_Y);
			o.addPoint(oX, oY);
			o.addPoint(oX + 50 * lengthwrt(obs.getHeight()), SIDE_Y);
			gx.fillPolygon(o);

			gx.setColor(Color.WHITE);
			gx.setFont(new Font("Arial", Font.PLAIN, 16));
			gx.drawLine(oX - 5, oY, oX - 5, SIDE_Y);
			gx.drawString(obs.getHeight() + "m", oX - 50, SIDE_Y
					- (SIDE_HEIGHT_SCALE * lengthwrt(obs.getHeight())) / 2);
		}

		gx.setColor(Color.WHITE);
		gx.setFont(new Font("Arial", Font.PLAIN, 16));
		int thresholdX = RW_START_X + lengthwrt(selectedRW.getThreshold());

		// gx.drawLine(RW_START_X, 190, thresholdX, 190);
		// gx.drawString("Threshold", 50, 180);

		drawMeasure(gx, thresholdX, SIDE_Y + 140,
				lengthwrt(selectedRW.getLda()), SIDE_Y + 140);
		gx.drawString("LDA, " + selectedRW.getLda() + "m", thresholdX + 5, SIDE_Y + 130);

		drawMeasure(gx, thresholdX, SIDE_Y + 110,
				lengthwrt(selectedRW.getTora()), SIDE_Y + 110);
		gx.drawString("TORA, " + selectedRW.getTora() + "m", thresholdX + 5, SIDE_Y + 100);

		drawMeasure(gx, thresholdX, SIDE_Y + 80,
				lengthwrt(selectedRW.getToda()), SIDE_Y + 80);
		gx.drawString("TODA, " + selectedRW.getToda() + "m", thresholdX + 5, SIDE_Y + 70);

		drawMeasure(gx, thresholdX, SIDE_Y + 50,
				lengthwrt(selectedRW.getAsda()), SIDE_Y + 50);
		gx.drawString("ASDA, " + selectedRW.getAsda() + "m", thresholdX + 5, SIDE_Y + 40);
	}

	public void drawMeasure(Graphics2D gx, int x1, int y1, int x2, int y2) {
		gx.drawLine(x1, y1, x2, y2);
		gx.fillOval(x1 - 5, y1 - 5, 10, 10);
		gx.fillOval(x2 - 5, y2 - 5, 10, 10);
	}

	public int lengthwrt(int length) {
		return (length * RW_LENGTH) / REAL_RW_LENGTH;
	}

	public void actionPerformed(ActionEvent e) {

	}

	public String getView() {
		return this.view;
	}

	public void setView(String view) {
		this.view = view;
	}

}