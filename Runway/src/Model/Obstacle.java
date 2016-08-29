package Model;
public class Obstacle
{

	//Attributes for the obstacle.
	private String name;
	private int height;
	private int dfc; //distance from centreline
	private int dflt; //distance from Left threshold
	private int dfrt; //distance from Right threshold
	private int eba; //Engine 
	
	public Obstacle(String name, int height, int dflt, int dfrt, int dfc) throws Exception
	{
		this.name = name;
		this.height = height;
		setDfc(dfc);
		setDflt(dflt);
		setDfrt(dfrt);
		setEba(0);
	}
	
	//Stub constructor used for testing purposes.
	public Obstacle() 
	{
		this.name = "";
		this.height = 0;
		this.dfc = 0;
		this.dflt = 0;	
		this.dfrt = 0;	
		this.eba = 0;
	}


	//Getters and setters for the variables
	public int getDfc()
	{
		return dfc;
	}
	
	public String getName() {
		return name;
	}
	
	public int getDflt()
	{
		return dflt;
	}
	
	public int getDfrt()
	{
		return dfrt;
	}
	
	
	public int getHeight()
	{
		return height;
	}
	
	public Integer getEBA() 
	{
		return eba;
	}
	
	public boolean setDfc(int dfc) throws Exception
	{
		if(dfc >= Integer.MIN_VALUE && dfc < LogicalRunway.ATTRIBUTE_LIMIT)
		{
			this.dfc= dfc;
			return true;
		}
		else
		{
			throw new Exception("Invalid value given for DFC.");
		}	
	}
	
	public boolean setDflt(int dflt) throws Exception
	{
		if(dflt >= Integer.MIN_VALUE && dflt < LogicalRunway.ATTRIBUTE_LIMIT)
		{
			this.dflt = dflt;
			return true;
		}
		else
		{
			throw new Exception("Invalid value given for DFT.");
		}	
	}
	
	public boolean setDfrt(int dfrt) throws Exception
	{
		if(dfrt >= Integer.MIN_VALUE && dfrt< LogicalRunway.ATTRIBUTE_LIMIT)
		{
			this.dfrt = dfrt;
			return true;
		}
		else
		{
			throw new Exception("Invalid value given for DFT.");
		}	
	}
	
	public boolean setHeight(int height) throws Exception
	{
		if(height >= 0 && height < LogicalRunway.ATTRIBUTE_LIMIT)
		{
			this.height = height;
			return true;
		}
		else
		{
			throw new Exception("Invalid value given for Height.");
		}	
	}

	//Getters and setters for the engine blast allowance (only used in the calculations
	public boolean setEba(int eba) throws Exception 
	{
		if(eba >= 0 && eba < LogicalRunway.ATTRIBUTE_LIMIT)
		{
			this.eba = eba;
			return true;
		}
		else
		{
			throw new Exception("Invalid value given for Height.");
		}	
	}




}
