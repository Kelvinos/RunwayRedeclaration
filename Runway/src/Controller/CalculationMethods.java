package Controller;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;

import Model.LogicalRunway;
import Model.Obstacle;
import Model.PhysicalRunway;


public class CalculationMethods 
{

	private static int RESA = 240;
	private static int EBV = 300;
	public static final int STRIP_END = 60;

	/*
	 * Redefine calculationmethods class to use updated runway and object classes.
	 */

	public static SimpleEntry<CalculationResult, CalculationResult> redeclare(PhysicalRunway runway, Obstacle plane) throws Exception
	{
		//The attributes of the runway are extracted.
		int lda = 0;
		int tora = 0;
		int toda = 0;
		int asda = 0;
		CalculationResult left = null;
		CalculationResult right = null;

		LogicalRunway l = runway.getL();
		tora = l.getTora();
		toda =l.getToda();
		asda = l.getAsda();
		lda = l.getLda();
		if(plane.getDflt() < (l.getTora()/2))
		{
			tora = takeOffAwayObstacleTORA1(tora, EBV, plane.getDflt(), l.getThreshold());
			toda = tora + l.getStopway();
			asda = tora + l.getClearway();
			lda = landAwayObstacleLDA(lda, plane.getHeight(), plane.getDflt(), 500);
		}
		else
		{
			tora = takeOffTowardObstacleTORA(plane.getHeight(), tora, plane.getDflt());
			toda = tora;
			asda = tora;
			lda = landTowardObstacle(plane.getDflt());
		}
		left = new CalculationResult(tora, toda, asda, lda);

		LogicalRunway r = runway.getR();
		if(r != null)
		{
			tora = r.getTora();
			toda = r.getToda();
			asda = r.getAsda();
			lda = r.getLda();
			if(plane.getDfrt() < (r.getTora()/2))
			{
				tora = takeOffAwayObstacleTORA1(tora, EBV, plane.getDfrt(), r.getThreshold());
				toda = tora + r.getStopway();
				asda = tora + r.getClearway();
				lda = landAwayObstacleLDA(lda, plane.getHeight(), plane.getDfrt(), 500);
			}
			else
			{
				tora = takeOffTowardObstacleTORA(plane.getHeight(), tora, plane.getDfrt());
				toda = tora;
				asda = tora;
				lda = landTowardObstacle(plane.getDfrt());
			}
			right = new CalculationResult(tora, toda, asda, lda);
		}
		return new AbstractMap.SimpleEntry<CalculationResult, CalculationResult>(left, right);

	}


	public static Integer slopeCalc(Integer obstacleHeight)
	{ 
		return obstacleHeight * 50; 
	}
	/*
	 * 
	 * 
	 * LANDING AWAY FROM OBSTACLE
	 * 
	 * 
	 */
	public static Integer landAwayObstacleLDA
	(Integer LDA, Integer obstacleHeight, Integer distFrmThresh, Integer BPV)
	{
		//work out the Approach Landing Surface
		Integer result = LDA;
		Integer total = 0;
		Integer ALS = slopeCalc(obstacleHeight);

		// Return the Larger value of RESA or ALS
		if(RESA > ALS)
		{ 
			total = total + RESA;
		}
		else
		{ 
			total  = total + ALS;
		}

		total = total + distFrmThresh;
		total = total + STRIP_END; // remove the Strip end value

		if(BPV > total)
		{
			return result - BPV;
		}
		else
		{
			return result - total;
		}

	}
	//When a plane is landing on a runway with an obstacle at the other end
	/*
	 * 
	 *  LANDING TOWARD AN OBSTACLE
	 */
	public static Integer landTowardObstacle(Integer LDA)
	{
		Integer result;
		//Take away the RESA value and the strip end from the LDA
		result = LDA - RESA - STRIP_END;

		return result;
	}
	/*
	 * 
	 *   TAKE OFF TOWARD OBSTACLE
	 * 
	 */
	public static Integer takeOffTowardObstacleTORA(Integer obstacleHeight,
			Integer TORA, Integer distFromThresh)
	{
		// no stopway avalible because Obstacle blocks the stopway or clearway
		Integer result;
		result = distFromThresh - STRIP_END - Math.max(slopeCalc(obstacleHeight), RESA);
		return result;
	}

	/*
	 * 
	 * TAKE OFF AWAY FROM OBSTACLE
	 * 
	 */

	// Have them in two so as for the different directions of a logical runway
	public static Integer takeOffAwayObstacleTORA1(Integer TORA, Integer EBA, Integer distFromThresh, Integer displacedThreash)
	{
		Integer result;
		//So you take the threshold away if the plane is at the start of the runway
		//otherwise it is at the other end and you may not be ab
		result = TORA - EBA - distFromThresh - displacedThreash;

		return result;
	}
	public static Integer takeOffAwayObstacleTORA2(Integer TORA, Integer EBA, Integer distFromThresh, Integer displacedThreash)
	{
		Integer result;
		//Original TORA takeaway strip end value, RESA and the distance from the threshold
		result = TORA - STRIP_END - RESA - distFromThresh;
		return result;
	}
	/*
	 * If there isn't a clear way then the value for TODA is the same as TORA
	 */
	public static Integer takeOffAwayObstacleTODA1(Integer TORA, Integer EBA, Integer distFromThresh, Integer displacedThreash, Integer clearway)
	{
		return takeOffAwayObstacleTORA1(TORA, EBA, distFromThresh, displacedThreash) + clearway;
	}

}
