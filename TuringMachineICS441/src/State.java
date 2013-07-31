/*	@Description: A State has a name and some number of transitions.
 */

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
	
	/* Used when creating a Turing Machine to provide each state with its proper transitions */
	public void addTransition(Transition newTransition)
	{
		transitionList.add(newTransition);
	}
	
	/* Used when running a Turing Machine to see if some state has a Transition for a character read from the tape. Throws a NullPointerException if the transition doesn't exist. */
	public Transition getTransition(char inputChar)
	{
//		if(inputChar == ' '){
//			inputChar = '_';
//		}
		for(int i = 0; i < transitionList.size(); i++)
			if(inputChar == transitionList.get(i).getTransitionChar())
			{
				//System.out.println("Getting transition for: " + inputChar);
				return transitionList.get(i);
			}
		return null;		
	}
}
