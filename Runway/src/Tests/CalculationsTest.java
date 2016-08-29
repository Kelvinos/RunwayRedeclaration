package Tests;

import static org.junit.Assert.*;

import java.util.AbstractMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Controller.CalculationMethods;
import Controller.CalculationResult;
import Model.LogicalRunway;
import Model.Obstacle;
import Model.PhysicalRunway;


/**
 * Class to test the calculations class and that it calculates the correct values given a runway.
 *
 */
public class CalculationsTest 
{
	public int tora;
	public int toda;
	public int asda;
	public int lda;
	public int thres;
	public LogicalRunway left;
	public LogicalRunway right;
	public PhysicalRunway runway;
	public AbstractMap.SimpleEntry<CalculationResult, CalculationResult> result;
	public Obstacle obs;
	
	//Sets up the logical runway we will be using for the tests
	@Before
	public void setup()
	{
		tora = 3884;
		toda = 3962;
		asda = 3884;
		lda = 3884;
		thres = 0;

		int tora2 = 3902;
		int toda2 = 3902;
		int asda2 = 3902;
		int lda2 = 3595;
		int thres2 = 306;
		try 
		{
			 right = new LogicalRunway(27, 'R', tora, toda, asda, lda, thres);
		} 
		catch (Exception e) 
		{
			fail("Problem creatin runway: " + e.getMessage());
		}
		try 
		{
			 left = new LogicalRunway(9, 'L', tora2, toda2, asda2, lda2, thres2);
		} 
		catch (Exception e) 
		{
			fail("Problem creatin runway: " + e.getMessage());
		}
		runway = new PhysicalRunway(left,right);
		obs = new Obstacle();
	}
	
	//Test for when the plane is landing over an obstacle.
	@Test
	public void testLandOver()
	{
		try
		{
			obs.setDflt(-50);
			obs.setDfrt(3646);
			obs.setHeight(12);
		} 
		catch (Exception e1)
		{
			fail("Should not have thrown an exception for valid input");
		}

		try 
		{
			result = CalculationMethods.redeclare(runway, obs);
		} 
		catch (Exception e) 
		{
			fail("Problem computing result" + e);
		}
		CalculationResult rresult = result.getKey();
		assertEquals(rresult.getUpdatedLda(),2985);
		
	}
	
	//Test for when the plane is landing towards an obstacle
	@Test
	public void testLandTowards()
	{
		try
		{
			obs.setDflt(-50);
			obs.setDfrt(3646);
			obs.setHeight(12);
		} 
		catch (Exception e1)
		{
			fail("Should not have thrown an exception for valid input");
		}
		try 
		{
			result = CalculationMethods.redeclare(runway, obs);
		} 
		catch (Exception e) 
		{
			fail("Problem computing result" + e);
		}
		CalculationResult rresult = result.getValue();
		assertEquals(rresult.getUpdatedLda(),3346);
	}
	
	//Test for when the plane is taking off towards an obstacle
	@Test
	public void testTakeOffTowards()
	{
		try
		{
			obs.setDflt(-50);
			obs.setDfrt(3646);
			obs.setHeight(12);
		} 
		catch (Exception e1)
		{
			fail("Should not have thrown an exception for valid input");
		}
		try 
		{
			result = CalculationMethods.redeclare(runway, obs);
		} 
		catch (Exception e) 
		{
			fail("Problem computing result" + e);
		}
		CalculationResult rresult = result.getValue();
		assertEquals(rresult.getUpdatedTora(),2986);
		assertEquals(rresult.getUpdatedToda(),2986);
		assertEquals(rresult.getUpdatedAsda(),2986);
	}
	
	//Test for a plane taking off away from an obstacle.
	@Test
	public void testtakeOffAway()
	{
		try
		{
			obs.setDflt(-50);
			obs.setDfrt(3646);
			obs.setHeight(12);
		} 
		catch (Exception e1)
		{
			fail("Should not have thrown an exception for valid input");
		}
		try 
		{
			result = CalculationMethods.redeclare(runway, obs);
		} 
		catch (Exception e) 
		{
			fail("Problem computing result" + e);
		}
		CalculationResult rresult = result.getKey();
		assertEquals(rresult.getUpdatedTora(),3346);
		assertEquals(rresult.getUpdatedToda(),3346);
		assertEquals(rresult.getUpdatedAsda(),3346);
	}
	
	@After
	public void after()
	{
		left = null;
		right = null;
		runway = null;
		obs = null;
		result = null;
	}

}
