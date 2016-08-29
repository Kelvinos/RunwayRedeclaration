package Tests;

import static org.junit.Assert.*;

import java.io.File;

import Model.Airport;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class AirportTest 
{

	private Airport a;

	@Before
	public void before() 
	{
		try 
		{
			a = new Airport();
		} 
		catch (Exception e) 
		{
			fail("Should not have thrown an exception when setting up the airport: " + e.getMessage());
		}
		
		try
		{
			createRunwayTestXML();
		} 
		catch (Exception e)
		{
			fail("Should not have thrown an exception when setting up the runways: " + e.getMessage());

		}
		try
		{
			createObstacleTestXML();
		} 
		catch (Exception e)
		{
			fail("Should not have thrown an exception when setting up the obstacles: " + e.getMessage());

		}

	}

	/*
	 * Test that the system accepts correct configuration file types
	 */
	@Test
	public void testCorrectFileTypeForRunway() 
	{
		File f = null;
		try 
		{
			f = new File("RunwayTest.xml");
		} 
		catch (Exception e) 
		{
			f.delete();
			fail(e.getMessage());
		}

		try
		{
			a.readRunway(f);
			f.delete();
		} 
		catch (Exception e)
		{
			f.delete();
			fail("Should not throw an exception when reading runways in.");
		}
	}

	/*
	 * Test that the system rejects configuration files
	 * which are not of the correct format.
	 */
	@Test
	public void testIncorrectFileTypeForRunway() 
	{
		File f  = null;
		try 
		{
			f = new File("RunwayTest.pdf");
			a.readRunway(f);
			f.delete();
			fail("Test should of failed");
		} 
		catch (Exception e) 
		{
			f.delete();
		}
	}
	
	/*
	 * Test that the system accepts correct configuration file types
	 */
	@Test
	public void testCorrectFileTypeForObstacle() 
	{
		File f = null;
		try 
		{
			f = new File("ObstacleTest.xml");
		} 
		catch (Exception e) 
		{
			f.delete();
			fail(e.getMessage());
		}

		try
		{
			a.readObstacle(f);
			f.delete();
		} 
		catch (Exception e)
		{
			f.delete();
			fail("Should not throw an exception when reading runways in.");
		}
	}

	/*
	 * Test that the system rejects configuration files
	 * which are not of the correct format.
	 */
	@Test
	public void testIncorrectFileTypeForObstacle() 
	{
		File f  = null;
		try 
		{
			f = new File("ObstacleTest.pdf");
			a.readObstacle(f);
			f.delete();
			fail("Test should of failed");
		} 
		catch (Exception e) 
		{
			f.delete();
		}
	}

	@Test
	public void testRunwayLoading()
	{
		try
		{
			assertEquals(a.listOfRunways().size(),0);
			a.readRunway(new File("RunwayTest.xml"));
			assertEquals(a.listOfRunways().size(),1);
		} 
		catch (Exception e)
		{
			fail(e.getMessage());
		}
	}

	//Test for checking to see if a runway added to the airport can  be set as the current airport.
	@Test
	public void testsettingCurrentRunway()
	{
		try
		{
			a.readRunway(new File("RunwayTest.xml"));
		} 
		catch (Exception e1)
		{
			fail(e1.getMessage());
		}
		try
		{
			a.currentRunwaySet("09L / 27R");
		} 
		catch (Exception e)
		{
			fail(e.getMessage() + "\n" + a.listOfRunways().get(0));
		}
	}
	
	@Test
	public void testCurrentRunwayRetrival()
	{
		try
		{
			a.readRunway(new File("RunwayTest.xml"));
			a.currentRunwaySet("09L / 27R");
			assertNotNull(a.getCurrentRunway());
		} 
		catch (Exception e)
		{
			fail(e.getMessage());
		}
	}

	@After
	public void after() 
	{
		a = null; // Remove reference
		new File("RunwayTest.xml").delete();
		new File("ObstacleTest.xml").delete();
	}


	private void createObstacleTestXML() throws Exception
	{
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = builder.newDocument();
		Element root = doc.createElement("Class");
		doc.appendChild(root);

		Element obstacle = doc.createElement("Obstacle");
		root.appendChild(obstacle);

		Attr name = doc.createAttribute("name");
		name.setValue("Test Obs");
		obstacle.setAttributeNode(name);

		Element height = doc.createElement("Height");
		height.appendChild(doc.createTextNode("12"));
		obstacle.appendChild(height);

		Element lthres = doc.createElement("Distance_from_LThres");
		lthres.appendChild(doc.createTextNode("-50"));
		obstacle.appendChild(lthres);

		Element rthres = doc.createElement("Distance_from_RThres");
		rthres.appendChild(doc.createTextNode("3646"));
		obstacle.appendChild(rthres);

		Element centre = doc.createElement("Distance_from_Centre");
		centre.appendChild(doc.createTextNode("0"));
		obstacle.appendChild(centre);


		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("ObstacleTest.xml"));
		transformer.transform(source, result);

	}

	private void createRunwayTestXML() throws Exception
	{
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = builder.newDocument();
		Element root = doc.createElement("Class");
		doc.appendChild(root);

		Element runway = doc.createElement("Runway");
		root.appendChild(runway);

		//Left Runway
		Element l = doc.createElement("L");
		runway.appendChild(l);

		Attr name = doc.createAttribute("name");
		name.setValue("09L");
		l.setAttributeNode(name);

		Element tora = doc.createElement("TORA");
		tora.appendChild(doc.createTextNode("3902"));
		l.appendChild(tora);

		Element toda = doc.createElement("TODA");
		toda.appendChild(doc.createTextNode("3902"));
		l.appendChild(toda);

		Element asda = doc.createElement("ASDA");
		asda.appendChild(doc.createTextNode("3902"));
		l.appendChild(asda);

		Element lda = doc.createElement("LDA");
		lda.appendChild(doc.createTextNode("3595"));
		l.appendChild(lda);

		Element thres = doc.createElement("Dis_Thres");
		thres.appendChild(doc.createTextNode("306"));
		l.appendChild(thres);

		//Right Runway
		Element r = doc.createElement("R");
		runway.appendChild(r);

		Attr name2 = doc.createAttribute("name");
		name2.setValue("27R");
		r.setAttributeNode(name2);

		Element tora2 = doc.createElement("TORA");
		tora2.appendChild(doc.createTextNode("3884"));
		r.appendChild(tora2);

		Element toda2 = doc.createElement("TODA");
		toda2.appendChild(doc.createTextNode("3962"));
		r.appendChild(toda2);

		Element asda2 = doc.createElement("ASDA");
		asda2.appendChild(doc.createTextNode("3884"));
		r.appendChild(asda2);

		Element lda2 = doc.createElement("LDA");
		lda2.appendChild(doc.createTextNode("3884"));
		r.appendChild(lda2);

		Element thres2 = doc.createElement("Dis_Thres");
		thres2.appendChild(doc.createTextNode("0"));
		r.appendChild(thres2);


		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("RunwayTest.xml"));
		transformer.transform(source, result);
		
		StreamResult concsole = new StreamResult(System.out);
		transformer.transform(source, concsole);
	}

}

