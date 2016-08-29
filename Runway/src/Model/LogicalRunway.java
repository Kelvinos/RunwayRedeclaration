package Model;

public class LogicalRunway
{
	//Attributes for the Runway
	//Logical Runway Character - can be L (Left), R (Right), C (Centre) or U (Unused)
	private int number;
	private char character;
	private int tora; //Take-Off Run Available
	private int toda; //Take-Off Distance Available
	private int asda; //Accelerate-Stop Distance Available
	private int lda; //Landing Distance Available
	private int thres; //Displaced Threshold
	public static final int ATTRIBUTE_LIMIT = 6000; //Limit that the attributes can be set to.
	//Constructor that builds an empty runway to be filled with attribtes later,
	public LogicalRunway(int number,char character) throws Exception
	{
		if(number > 0 && number <= 36)
		{
			this.number = number;
		}
		else
		{
			throw new Exception("Invalid number given to Runway");
		}
		setCharacter(character);
		this.tora = 0;
		this.toda = 0;
		this.asda = 0;
		this.lda = 0;
		this.thres = 0;
	}
	
	public LogicalRunway(int number,char character,int tora,int toda, int asda, int lda, int thres) throws Exception
	{
		this(number,character);
		setTora(tora);
		setToda(toda);
		setAsda(asda);
		setLda(lda);
		setThreshold(thres);
	}

	public char getCharacter()
	{
		return character;
	}
	
	public int getAsda()
	{
		return asda;
	}
	
	public int getLda()
	{
		return lda;
	}
	
	public int getToda()
	{
		return toda;
	}
	
	public int getTora()
	{
		return tora;
	}
	
	public boolean setAsda(int asda) throws Exception
	{
		if(asda >= 0 && asda < ATTRIBUTE_LIMIT)
		{
			this.asda = asda;
			return true;
		}
		else
		{
			throw new Exception("Invalid value given for ASDA.");
		}
	}
	
	public boolean setLda(int lda) throws Exception
	{
		if(lda >= 0 && lda < ATTRIBUTE_LIMIT)
		{
			this.lda = lda;
			return true;
		}
		else
		{
			throw new Exception("Invalid value given for LDA.");
		}	}
	
	public boolean setToda(int toda) throws Exception
	{
		if(toda >= 0 && toda < ATTRIBUTE_LIMIT)
		{
			this.toda = toda;
			return true;
		}
		else
		{
			throw new Exception("Invalid value given for TODA.");
		}	
	}
	
	public boolean setTora(int tora) throws Exception
	{
		if(tora >= 0 && tora < ATTRIBUTE_LIMIT)
		{
			this.tora = tora;
			return true;
		}
		else
		{
			throw new Exception("Invalid value given for TORA.");
		}	
	}

	public boolean setThreshold(int thres) throws Exception
	{
		if(thres >= 0 && thres < ATTRIBUTE_LIMIT)
		{
			this.thres = thres;
			return true;
		}
		else
		{
			throw new Exception("Invalid value given for Displaced Threshold.");
		}
	}
	
	public void setCharacter(char character) throws Exception 
	{
		if(character == 'L' | character == 'R' | character == 'C')
		{
			this.character = character;
		}
		else
		{
			throw new Exception("Invalid Character given to Runway");
		}
	}

	public int getThreshold() 
	{
		return thres;
	}

	public int getStrip() 
	{
		return asda;
	}

	public Integer getClearway() 
	{
		return toda - tora;
	}

	public int getStopway() 
	{
		return asda - tora;
	}
	
	public int getDesgniatorNum() 
	{
		return number;
	}

	public String getName()
	{
		return ((getDesgniatorNum() < 10) ? '0'+String.valueOf(getDesgniatorNum()) : String.valueOf(getDesgniatorNum())) + getCharacter();
	}
}
