package Model;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Airport
{
	private HashMap<String, PhysicalRunway> runways;
	private HashMap<String, Obstacle> obstacles;
	private PhysicalRunway currentRunway;

	public Airport() throws Exception 
	{
		runways = new HashMap<String, PhysicalRunway>();
		obstacles = new HashMap<String, Obstacle>();
		currentRunway = null;
	}

	public Airport(Boolean tru) throws Exception
	{
		this();
		newObstacle("Pre-1", 12, -10, 3700, 0);
		newObstacle("Pre-2", 50, 200, 3000, 0);
		newObstacle("Pre-3", 25, 400, 2500, 20);
	}

	/*
	 * Method to set a runway as the current runway based on a given runway name.
	 * The given name is a string consisting of a number and a character.
	 */
	public void currentRunwaySet(String name) throws Exception
	{
		PhysicalRunway current = null;

		if((current = runways.get(name)) == null)
		{
			throw new Exception("The given runway (" + name + ") does not exsist.");
		}

		setCurrentRunway(current);
	}

	//Private to set the current runway only if it is not already the current Runway.
	private void setCurrentRunway(PhysicalRunway currentRunway) 
	{
		synchronized (currentRunway)
		{
			this.currentRunway = currentRunway;
		}
	}

	public PhysicalRunway getCurrentRunway() throws Exception
	{
		synchronized (currentRunway) 
		{
			return currentRunway;
		}
	}

	//Method for finding the current selection of runways across the airport.
	public ArrayList<String> listOfRunways()
	{
		ArrayList<String> list = new ArrayList<String>();
		synchronized (runways) 
		{
			for (Entry<String, PhysicalRunway> entry : runways.entrySet()) 
			{
				list.add(entry.getKey());
			}
		}
		return list;
	}
	
	public boolean RunwayNameCheck(String name)
	{
		synchronized (runways) 
		{
			for (Entry<String, PhysicalRunway> entry : runways.entrySet()) 
			{
				PhysicalRunway runway = entry.getValue();
				if(runway.getL().getName().equals(name))
				{
					return false;
				}
				else if(runway.getR() != null && runway.getR().getName().equals(name))
				{
					return false;
				}
			}
			return true;
		}
	}

	public boolean obstacleNameCheck(String name)
	{
		synchronized (obstacles)
		{
			for (Entry<String, Obstacle> entry : obstacles.entrySet())
			{
				Obstacle obstacle = entry.getValue();
				if(obstacle.getName().equals(name))
					return false;
			}
			return true;
		}
	}
	
	public ArrayList<String> listOfObstacles()
	{
		ArrayList<String> list = new ArrayList<String>();
		synchronized (obstacles)
		{
			for (Entry<String, Obstacle> entry : obstacles.entrySet())
			{
				list.add(entry.getKey());
			}
		}
		return list;
	}

	public PhysicalRunway addRunway(LogicalRunway left,LogicalRunway right) throws Exception
	{
		synchronized (runways)
		{
			PhysicalRunway run = new PhysicalRunway(left,right);
			return addRunway(run);
		}
	}


	private PhysicalRunway addRunway(PhysicalRunway run)
	{
		synchronized (runways) 
		{
			return runways.put(run.getName(), run);
		}
	}

	public Obstacle newObstacle(String name, int height, int dflt, int dfrt, int dfc) throws Exception
	{
		synchronized (obstacles) 
		{
			if(obstacles.containsKey(name))
			{
				throw new Exception("Non-uniue obstacle name given");
			}
			Obstacle newobs = new Obstacle(name,height, dflt, dfrt, dfc);
			obstacles.put(name,newobs);
			return newobs;
		}	
	}

	public PhysicalRunway getRunway(String name) throws Exception
	{
		synchronized (runways) 
		{
			if(!runways.containsKey(name))
				throw new Exception(name + " does not exsist in this airport.");
			else
				return runways.get(name);
		}
	}

	public Obstacle getObstacle(String name) throws Exception
	{
		synchronized (obstacles) 
		{
			if(obstacles.containsKey(name))
			{
				return obstacles.get(name);
			}
			else
			{
				throw new Exception("Name given did not  match any obstacle.");
			}
		}
	}


	public boolean removeRunway(String name) throws Exception
	{
		synchronized (runways) 
		{
			return runways.remove(name) != null;
		}
	}

	public boolean removeObstacle(String name) throws Exception
	{
		synchronized (obstacles)
		{
			return obstacles.remove(name) != null;
		}
	}

	//Method for reading a runway into the system given you have the configuration file filename.
	public boolean readRunway(File file) throws Exception
	{
		if(!file.getName().endsWith(".xml"))
			throw new Exception("Input must be an xml file");

		int number = 1;
		char character = 'C';
		int tora = 0;
		int toda = 0;
		int asda = 0;
		int lda = 0;
		int thres = 0;

		//Create a document from the given file to access it's XML Tree
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(file);
		Element root = doc.getDocumentElement();
		root.normalize();

		//Get the list of nodes in the XML document
		NodeList list = doc.getElementsByTagName("Runway");

		//Loop though the nodes and get the data related with each obstacle
		for (int temp = 0; temp < list.getLength(); temp++) 
		{
			Node runway = list.item(temp);
			if (runway.getNodeType() == Node.ELEMENT_NODE) 
			{
				NodeList nodes = runway.getChildNodes();
				ArrayList<LogicalRunway> newrunway = new ArrayList<LogicalRunway>();
				Node node = nodes.item(1);
				if (node.getNodeType() == Node.ELEMENT_NODE && newrunway.size() == 0)
				{
					Element element = (Element) node;
					String tname = element.getAttribute("name");
					number = Integer.parseInt(tname.substring(0, tname.length()-1));
					character = tname.substring(tname.length()-1, tname.length()).charAt(0);
					tora = Integer.parseInt(element.getElementsByTagName("TORA").item(0).getTextContent());
					toda = Integer.parseInt(element.getElementsByTagName("TODA").item(0).getTextContent());
					asda = Integer.parseInt(element.getElementsByTagName("ASDA").item(0).getTextContent());
					lda = Integer.parseInt(element.getElementsByTagName("LDA").item(0).getTextContent());
					thres = Integer.parseInt(element.getElementsByTagName("Dis_Thres").item(0).getTextContent());
					newrunway.add(new LogicalRunway(number, character, tora, toda, asda, lda, thres));
				}
				node = nodes.item(3);
				if (node.getNodeType() == Node.ELEMENT_NODE && newrunway.size() == 1)
				{
					Element element = (Element) node;
					String tname = element.getAttribute("name");
					number = Integer.parseInt(tname.substring(0, tname.length()-1));
					character = tname.substring(tname.length()-1, tname.length()).charAt(0);
					tora = Integer.parseInt(element.getElementsByTagName("TORA").item(0).getTextContent());
					toda = Integer.parseInt(element.getElementsByTagName("TODA").item(0).getTextContent());
					asda = Integer.parseInt(element.getElementsByTagName("ASDA").item(0).getTextContent());
					lda = Integer.parseInt(element.getElementsByTagName("LDA").item(0).getTextContent());
					thres = Integer.parseInt(element.getElementsByTagName("Dis_Thres").item(0).getTextContent());
					newrunway.add(new LogicalRunway(number, character, tora, toda, asda, lda, thres));
				}
				
				for (LogicalRunway logicalRunway : newrunway)
				{
					if(!RunwayNameCheck(logicalRunway.getName()))
					{
						throw new Exception(logicalRunway.getName() + " is ont a unique name");
					}
				}
				//Add the new physical runway to the collection
				if(newrunway.size() >= 1)
				{
					PhysicalRunway run;
					if(newrunway.size() == 2)
						run = new PhysicalRunway(newrunway.get(0),newrunway.get(1));
					else
						run = new PhysicalRunway(newrunway.get(0));
					addRunway(run);
					newrunway.clear();
				}

			}
		} 
		return true;

	}

	//Method for reading a runway into the system by selecting the configuration file from a mmenu.
	public boolean readRunway() throws Exception
	{
		JFileChooser chooser = new JFileChooser();
		//Sets the file chooser so it only displays dictories and xml files.
		chooser.setFileFilter(new FileFilter()
		{

			@Override
			public String getDescription()
			{
				return "Only xml files are allowed";
			}

			@Override
			public boolean accept(File f)
			{
				if(f.isDirectory())
					return true;

				if(f.getName().endsWith(".xml"))
					return true;

				return false;
			}
		});

		int result = chooser.showOpenDialog(null);
		if(result != JFileChooser.APPROVE_OPTION)
		{
			JOptionPane.showMessageDialog(null, "Runway loading has been cancelled");
			return false;
		}

		return readRunway(chooser.getSelectedFile());
	}

	//Method for reading in an obstacle given that you have the filename of the configuration file
	public boolean readObstacle(File file) throws Exception
	{
		if(!file.getName().endsWith(".xml"))
			throw new Exception("Input must be an xml file");

		String name = "";
		int height = 0;
		int dflt = 0;
		int dfrt = 0;
		int dfc = 0;


		//Create a document from the given file to access it's XML Tree
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(file);
		Element root = doc.getDocumentElement();
		root.normalize();

		//Get the list of nodes in the XML document
		NodeList list = doc.getElementsByTagName("Obstacle");

		//Loop though the nodes and get the data related with each obstacle
		for (int temp = 0; temp < list.getLength(); temp++) 
		{
			Node node = list.item(temp);
			if (node.getNodeType() == Node.ELEMENT_NODE) 
			{
				Element element = (Element) node;
				name = element.getAttribute("name");
				height = Integer.parseInt(element.getElementsByTagName("Height").item(0).getTextContent());
				dflt = Integer.parseInt(element.getElementsByTagName("Distance_from_LThres").item(0).getTextContent());
				dfrt = Integer.parseInt(element.getElementsByTagName("Distance_from_RThres").item(0).getTextContent());
				dfc = Integer.parseInt(element.getElementsByTagName("Distance_from_Centre").item(0).getTextContent());
				//Add the new obstacle to the collection
				if(obstacleNameCheck(name))
					newObstacle(name, height, dflt, dfrt, dfc);
				else
					throw new Exception(name + "is not a unique name");
			}
		} 
		return true;
	}

	//Method for reading a new obstacle intot he system by selecting the configuration file from a menu.
	public boolean readObstacle() throws Exception
	{
		JFileChooser chooser = new JFileChooser();
		//Sets the file chooser so it only displays dictories and xml files.
		chooser.setFileFilter(new FileFilter()
		{

			@Override
			public String getDescription()
			{
				return "Only xml files are allowed";
			}

			@Override
			public boolean accept(File f)
			{
				if(f.isDirectory())
					return true;

				if(f.getName().endsWith(".xml"))
					return true;

				return false;
			}
		});
		int result = chooser.showOpenDialog(null);
		if(result != JFileChooser.APPROVE_OPTION)
		{
			JOptionPane.showMessageDialog(null, "Obstacle loading has been cancelled");
			return false;
		}
		return readObstacle(chooser.getSelectedFile());
	}

	public void exportObstacles(File file) throws Exception
	{
		if(!file.getName().endsWith(".xml"))
			throw new Exception("You can only save with xml files.");
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = builder.newDocument();
		Element root = doc.createElement("Class");
		doc.appendChild(root);
		synchronized (obstacles) 
		{
			for (Entry<String, Obstacle> element : obstacles.entrySet()) 
			{
				Obstacle obs = element.getValue();
				Element obstacle = doc.createElement("Obstacle");
				root.appendChild(obstacle);

				Attr name = doc.createAttribute("name");
				name.setValue(obs.getName());
				obstacle.setAttributeNode(name);

				Element height = doc.createElement("Height");
				height.appendChild(doc.createTextNode(String.valueOf(obs.getHeight())));
				obstacle.appendChild(height);

				Element lthres = doc.createElement("Distance_from_LThres");
				lthres.appendChild(doc.createTextNode(String.valueOf(obs.getDflt())));
				obstacle.appendChild(lthres);

				Element rthres = doc.createElement("Distance_from_RThres");
				rthres.appendChild(doc.createTextNode(String.valueOf(obs.getDfrt())));
				obstacle.appendChild(rthres);

				Element centre = doc.createElement("Distance_from_Centre");
				centre.appendChild(doc.createTextNode(String.valueOf(obs.getDfc())));
				obstacle.appendChild(centre);
			}
		}

		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(file);
		transformer.transform(source, result);

		System.out.println("This is what was produced to " + file.getName());
		System.out.println("----------------------------------");
		StreamResult concsole = new StreamResult(System.out);
		transformer.transform(source, concsole);
	}

	public void exportRunways(File file) throws Exception
	{
		if(!file.getName().endsWith(".xml"))
			throw new Exception("You can only save with xml files.");
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = builder.newDocument();
		Element root = doc.createElement("Class");
		doc.appendChild(root);
		synchronized (runways) 
		{
			for (Entry<String, PhysicalRunway> element : runways.entrySet()) 
			{
				PhysicalRunway run = element.getValue();
				Element runway = doc.createElement("Runway");
				root.appendChild(runway);

				//Left Runway
				Element l = doc.createElement("L");
				runway.appendChild(l);

				Attr name = doc.createAttribute("name");
				name.setValue(run.getL().getName());
				l.setAttributeNode(name);

				Element tora = doc.createElement("TORA");
				tora.appendChild(doc.createTextNode(String.valueOf(run.getL().getTora())));
				l.appendChild(tora);

				Element toda = doc.createElement("TODA");
				toda.appendChild(doc.createTextNode(String.valueOf(run.getL().getToda())));
				l.appendChild(toda);

				Element asda = doc.createElement("ASDA");
				asda.appendChild(doc.createTextNode(String.valueOf(run.getL().getAsda())));
				l.appendChild(asda);

				Element lda = doc.createElement("LDA");
				lda.appendChild(doc.createTextNode(String.valueOf(run.getL().getLda())));
				l.appendChild(lda);

				Element thres = doc.createElement("Dis_Thres");
				thres.appendChild(doc.createTextNode(String.valueOf(run.getL().getThreshold())));
				l.appendChild(thres);

				if(run.getR() != null)
				{
					//Right Runway
					Element r = doc.createElement("R");
					runway.appendChild(r);

					name = doc.createAttribute("name");
					name.setValue(run.getR().getName());
					r.setAttributeNode(name);

					tora = doc.createElement("TORA");
					tora.appendChild(doc.createTextNode(String.valueOf(run.getR().getTora())));
					r.appendChild(tora);

					toda = doc.createElement("TODA");
					toda.appendChild(doc.createTextNode(String.valueOf(run.getR().getToda())));
					r.appendChild(toda);

					asda = doc.createElement("ASDA");
					asda.appendChild(doc.createTextNode(String.valueOf(run.getR().getAsda())));
					r.appendChild(asda);

					lda = doc.createElement("LDA");
					lda.appendChild(doc.createTextNode(String.valueOf(run.getR().getLda())));
					r.appendChild(lda);

					thres = doc.createElement("Dis_Thres");
					thres.appendChild(doc.createTextNode(String.valueOf(run.getR().getThreshold())));
					r.appendChild(thres);
				}

			}
		}

		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(file);
		transformer.transform(source, result);

		System.out.println("This is what was produced to " + file.getName());
		System.out.println("----------------------------------");
		StreamResult concsole = new StreamResult(System.out);
		transformer.transform(source, concsole);
	}

	public void exportRunways() throws Exception
	{
		JFileChooser chooser = new JFileChooser();
		//Sets the file chooser so it only displays dictories and xml files.
		chooser.setFileFilter(new FileFilter()
		{

			@Override
			public String getDescription()
			{
				return "Only xml files are allowed";
			}

			@Override
			public boolean accept(File f)
			{
				if(f.isDirectory())
					return true;

				if(f.getName().endsWith(".xml"))
					return true;

				return false;
			}
		});
		int result = chooser.showSaveDialog(null);
		if(result != JFileChooser.APPROVE_OPTION)
		{
			JOptionPane.showMessageDialog(null, "Runway exporting has been cancelled");
			return;
		}
		exportRunways(chooser.getSelectedFile());
	}

	public void exportObstacles() throws Exception
	{
		JFileChooser chooser = new JFileChooser();
		//Sets the file chooser so it only displays dictories and xml files.
		chooser.setFileFilter(new FileFilter()
		{

			@Override
			public String getDescription()
			{
				return "Only xml files are allowed";
			}

			@Override
			public boolean accept(File f)
			{
				if(f.isDirectory())
					return true;

				if(f.getName().endsWith(".xml"))
					return true;

				return false;
			}
		});

		int result = chooser.showSaveDialog(null);
		if(result != JFileChooser.APPROVE_OPTION)
		{
			JOptionPane.showMessageDialog(null, "Obstacle exporting has been cancelled");
			return;
		}
		exportObstacles(chooser.getSelectedFile());
	}

}
