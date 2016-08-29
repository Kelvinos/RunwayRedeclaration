package Tests;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Model.LogicalRunway;
import Model.Obstacle;
import Model.PhysicalRunway;

public class PhysicalRunwayTest
{

	LogicalRunway left;
	LogicalRunway right;
	PhysicalRunway runway;
	Obstacle obs;
	
	@Before
	public void setUp() throws Exception
	{
		left = new LogicalRunway(7, 'L');
		right = new LogicalRunway(29, 'R');
		runway = new PhysicalRunway(left,right);
		obs = new Obstacle();
	}

	@After
	public void tearDown() throws Exception
	{
		left = null;
		right = null;
		runway = null;
		obs = null;
	}

	@Test
	public void testObstacleSetting()
	{
		try
		{
			runway.setObstacle(obs);
		}
		catch (Exception e)
		{
			fail("Should not have thrown an exception when there is no obstacle in the runway yet.");
		}
		
		try
		{
			runway.setObstacle(obs);
			fail("Should have thrown an exception since there is already a obstacle set.");
		}
		catch (Exception e)
		{
		}
	}
	
	@Test
	public void testObstacleRemoval()
	{
		try
		{
			runway.removeObstacle();
			fail("Should have thrown an exception since there was no obstacle to remove");
		}
		catch (Exception e)
		{
		}
		
		try
		{
			runway.setObstacle(obs);
			runway.removeObstacle();
		}
		catch (Exception e)
		{
			fail("Should not have thrown an exception since there was an obstacle");
		}
	}
	
	@Test
	public void testObstacleRetrival()
	{
		try
		{
			runway.getObstacle();
			fail("Should have thrown an exception since there was no obstacle to get");
		}
		catch (Exception e)
		{
		}
		
		try
		{
			runway.setObstacle(obs);
			Obstacle returned = runway.getObstacle();
			assertEquals(returned, obs);
		}
		catch (Exception e)
		{
			fail("Should not have thrown an exception since there as an obstacle to get");
		}
		
		try
		{
			runway.removeObstacle();;
			@SuppressWarnings("unused")
			Obstacle returned = runway.getObstacle();
			fail("Should have thrown an exception since there was no obstacle to get");
		}
		catch (Exception e)
		{
		}
		
	}
	
	@Test
	public void testRunwayRetrival()
	{
		assertEquals(left, runway.getL());
		assertEquals(right,runway.getR());
		runway = new PhysicalRunway(left);
		assertNull(runway.getR());
	}

	@Test
	public void testNameRetrival()
	{
		assertEquals(runway.getName(),left.getName() + " / " + right.getName());
		runway = new PhysicalRunway(left);
		assertEquals(runway.getName(),left.getName());
	}
}
