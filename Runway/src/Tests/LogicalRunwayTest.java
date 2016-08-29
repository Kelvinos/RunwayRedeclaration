package Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Model.LogicalRunway;


public class LogicalRunwayTest 
{

	private LogicalRunway r;
	private int testVal;
	
	@Before
	public void before() 
	{
		try 
		{
			r = new LogicalRunway(34,'L');
		} 
		catch (Exception e) 
		{
			fail("Error when trying to create runway: " + e.getMessage());
		}
	}
	
	//Tests to see if a number can entered for the logicalrunway outside of the range 1-36
	@Test
	public void testNumberSet()
	{
		try 
		{
			r = new LogicalRunway(-1, 'R');
			fail("Should have thrown an exeption since the number was lower than 1");
		} 
		catch (Exception e) 
		{
			try 
			{
				r = new LogicalRunway(37, 'R');
				fail("Should have thrown an exception since the number was larger than 37");
			} 
			catch (Exception e2) 
			{
				
			}
		}
	}
	
	/*
	 * Test to see if positive integers can be set as the runway attributes without exception.
	 */
	@Test
	public void testPositiveInteger() 
	{
		testVal = 10;
		
		try
		{
			r.setTora(testVal);
			assertEquals(r.getTora(), testVal);
		}
		catch (Exception e)
		{
			fail("Should not have thrown Exception for setting tora");
		}
		
		try
		{
			r.setToda(testVal);
			assertEquals(r.getToda(), testVal);
		}
		catch (Exception e)
		{
			fail("Should not have thrown an exception for setting toda");
		}
		
		try
		{
			r.setAsda(testVal);
			assertEquals(r.getAsda(), testVal);
		}
		catch (Exception e)
		{
			fail("Should not have thrown an exception when setting the asda.");
		}
		
		try
		{
			r.setLda(testVal);
			assertEquals(r.getLda(), testVal);
		}
		catch (Exception e)
		{
			fail("Shouuld not have thrown an exception when setting the lda.");
		}
		
		try
		{
			r.setThreshold(testVal);
			assertEquals(r.getThreshold(), testVal);
		}
		catch (Exception e)
		{
			fail("Shouuld not have thrown an exception when setting the threshold.");
		}
		
	}
	
	/*
	 * Test Negative Integers
	 * No change as negative should not be supported
	 */
	@Test
	public void testNegativeInteger() 
	{
		testVal = -1;
		
		try
		{
			r.setToda(testVal);
			fail("Should have thrown an exception when setting the toda as a negative value");
		}
		catch (Exception e)
		{

		}
		
		try
		{
			r.setTora(testVal);
			fail("Should have thrown an exception when setting the tora as a negative value");
		}
		catch (Exception e2)
		{
			// TODO: handle exception
		}
		
		try
		{
			r.setAsda(testVal);
			fail("Should have thrown an exception when setting the asda as a negative value");
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
		
		try
		{
			r.setLda(testVal);
			fail("Should have thrown an exception when setting the lda as a negative value");
		}
		catch (Exception e)
		{
			
		}
		
		try
		{
			r.setThreshold(testVal);
			fail("Should have thrown an exception when setting the hreshold as a negative value");
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
	public void testLargeInteger() 
	{
		testVal = LogicalRunway.ATTRIBUTE_LIMIT;
		
		try
		{
			r.setToda(testVal);
			fail("Should have thrown an exception when setting the toda as a extreme value");
		}
		catch (Exception e)
		{

		}
		
		try
		{
			r.setTora(testVal);
			fail("Should have thrown an exception when setting the tora as a extreme value");
		}
		catch (Exception e2)
		{
			// TODO: handle exception
		}
		
		try
		{
			r.setAsda(testVal);
			fail("Should have thrown an exception when setting the asda as a extreme value");
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
		
		try
		{
			r.setLda(testVal);
			fail("Should have thrown an exception when setting the lda as a extreme value");
		}
		catch (Exception e)
		{
			
		}
		
		try
		{
			r.setThreshold(testVal);
			fail("Should have thrown an exception when setting the threshold as a extreme value");
		}
		catch (Exception e)
		{
			
		}
	}
	
	@After
	public void after() {
		r = null; // Remove object reference
	}
	
	
}
