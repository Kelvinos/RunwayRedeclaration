package Model;
import java.util.ArrayList;


public class PhysicalRunway 
{

	private LogicalRunway L;
	private LogicalRunway R;
	private Obstacle obstacle;
	
	public PhysicalRunway(LogicalRunway l)
	{
		this.L = l;
		this.R = null;
	}
	public PhysicalRunway(LogicalRunway l, LogicalRunway r)
	{
		this(l);
		this.R = r;
	}
	
	public void setObstacle(Obstacle obstacle) throws Exception
	{
		if(this.obstacle == null)
			this.obstacle = obstacle;
		else
			throw new Exception("Cannot set a obstacle if one already exsists on the runway");
	}
	
	public Obstacle getObstacle() throws Exception
	{
		if(this.obstacle != null)
			return this.obstacle;
		else
			throw new Exception("No obstacle to get!");
	}
	
	public void removeObstacle() throws Exception
	{
		if(this.obstacle != null)
			this.obstacle = null;
		else
			throw new Exception("No obstacle to remove.");
	}
	
	
	
	public LogicalRunway getL() 
	{
		return L;
	}
	
	public LogicalRunway getR() 
	{
		return R;
	}
	
	//Method for collecting a list of names for all currently used runways in the this runway.
	public ArrayList<String> getRunways() 
	{
		ArrayList<String> runways = new ArrayList<String>();
		LogicalRunway current;
		if((current = getL()) != null)
		{
			runways.add(String.valueOf(current.getDesgniatorNum())+current.getCharacter());
		}
		if((current = getR()) != null)
		{
			runways.add(String.valueOf(current.getDesgniatorNum())+current.getCharacter());
		}
		return runways;
	}
	
	public int getTotalLength()
	{
		int stopway = (R == null) ? 0 : getR().getStopway();
		return getL().getToda() + stopway;
	}
	
	public boolean isMulti() 
	{
		return getR() != null;
	}
	
	public String getName()
	{
		if(getR() != null)
			return getL().getName() + " / " + getR().getName();
		else
			return getL().getName();
	}
	

}
