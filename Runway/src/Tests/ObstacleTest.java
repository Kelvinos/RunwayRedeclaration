package Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Model.LogicalRunway;
import Model.Obstacle;

public class ObstacleTest 
{

	private Obstacle o;
	private int testVal;

	@Before
	public void before() 
	{
		o = new Obstacle();
	}
	
	//Test to show that a name can be correctly applied to an obstacle.
	@Test
	public void testNaming()
	{
		String name = "Airplane - Test";
		try
		{
			o = new Obstacle(name, 0, 0, 0, 0);
		} 
		catch (Exception e)
		{
			fail("Error creating obstacle");
		}
		assertEquals(o.getName(),name);
	}
	/*
	 * Test to see if the obstacle will allows correct values for the attributes (positive integers
	 * below the attribute limit.
	 */
	@Test
	public void testPositiveInteger() 
	{
		testVal = 15;
		
		try
		{
			o.setHeight(testVal);
			assertEquals(o.getHeight(), testVal);
		} 
		catch (Exception e)
		{
			fail("Should not have thrown an exception when setting height since it is a positive integer");
		}
	
		try
		{
			o.setDfc(testVal);
			assertEquals(o.getDfc(), testVal);
		} 
		catch (Exception e)
		{
			fail("Should not have thrown an exception when setting dfc since it is a positive integer");
		}
		
		try
		{
			o.setDfrt(testVal);
			assertEquals(o.getDfrt(), testVal);	
		} 
		catch (Exception e)
		{
			fail("Should not have thrown an exception when setting dfrt since it is a positive integer");
		}
		
		try
		{
			o.setDflt(testVal);
			assertEquals(o.getDflt(), testVal);	
		} 
		catch (Exception e)
		{
			fail("Should not have thrown an exception when setting dflt since it is a positive integer");
		}
	}

	@Test
	public void testNegativeInteger() 
	{
		testVal = -7;
		
		try
		{
			o.setHeight(testVal);
			fail("Should have thrown an exception when setting height since it is a negative integer");
		} 
		catch (Exception e)
		{
		}

	}
	
	/*
	 * Test Large Integer Values
	 * No change as there should be a maximum threshold value
	 */
	@Test
	public void testLargeInteger() {
		testVal = LogicalRunway.ATTRIBUTE_LIMIT + 3;
		
		try
		{
			o.setHeight(testVal);
			fail("Should have thrown an exception when setting height since it is over the limit");
		} 
		catch (Exception e)
		{
		}
	
		try
		{
			o.setDfc(testVal);
			fail("Should have thrown an exception when setting dfc since it is over the limit");
		} 
		catch (Exception e)
		{
		}
		
		try
		{
			o.setDfrt(testVal);
			fail("Should have thrown an exception when setting dfrt since it is over the limit");
		} 
		catch (Exception e)
		{
		}
		
		try
		{
			o.setDflt(testVal);
			fail("Should have thrown an exception when setting dflt since it is over the limit");
		} 
		catch (Exception e)
		{
		}	
	}
	
	@After
	public void after() {
		o = null;
	}
	
}
