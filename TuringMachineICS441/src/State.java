import java.util.ArrayList;

public class State {
	
	private String name;
	private ArrayList<Transition> transitionList;
	
	public State(String name)
	{
		this.name = name;	
		transitionList = new ArrayList<Transition>();
	}
	
	public String getName()
	{
		return name;
	}
	
	public void addTransition(Transition newTransition)
	{
		transitionList.add(newTransition);
	}
	
	/*Throws a NullPointerException if the transition doesn't exist. */
	public Transition getTransition(char inputChar)
	{
		for(int i = 0; i < transitionList.size(); i++)
			if(inputChar == transitionList.get(i).getTransitionChar())
			{
				return transitionList.get(i);
			}
		return null;		
	}
}
