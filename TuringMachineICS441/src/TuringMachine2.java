import java.util.List;

/*	@Description: Describes the Turing Machine w w^R */

public class TuringMachine2 extends TuringMachine{
	
	public TuringMachine2(String inputString) 
	{
		super(inputString, null);
	}
	
	public void initStates(List<String> liTMdef)
	{
		stateCollection = new State[8];
		
		for(int i = 0; i < 6; i++)
			stateCollection[i] = new State("q" + i);
		
		qaccept = 6;
		qreject = 7;
		
		stateCollection[qaccept] = new State("qaccept");
		stateCollection[qreject] = new State("qreject");		
	}
	
	public void initTransitions(List<String> liTMdef)
	{
		stateCollection[0].addTransition(new Transition('x', moveRight, stateCollection[0]));
		stateCollection[0].addTransition(new Transition(' ', moveRight, stateCollection[qaccept]));
		stateCollection[0].addTransition(new Transition('1', 'x', moveRight, stateCollection[2]));
		stateCollection[0].addTransition(new Transition('0', 'x', moveRight, stateCollection[1]));
		
		stateCollection[1].addTransition(new Transition('0', moveRight, stateCollection[1]));
		stateCollection[1].addTransition(new Transition('1', moveRight, stateCollection[1]));
		stateCollection[1].addTransition(new Transition(' ', moveLeft, stateCollection[3]));
		stateCollection[1].addTransition(new Transition('x', moveLeft, stateCollection[3]));
		
		stateCollection[2].addTransition(new Transition('0', moveRight, stateCollection[2]));
		stateCollection[2].addTransition(new Transition('1', moveRight, stateCollection[2]));
		stateCollection[2].addTransition(new Transition(' ', moveLeft, stateCollection[4]));
		stateCollection[2].addTransition(new Transition('x', moveLeft, stateCollection[4]));
		
		stateCollection[3].addTransition(new Transition('0', 'x', moveLeft, stateCollection[5]));
		stateCollection[3].addTransition(new Transition('1', moveRight, stateCollection[qreject]));
		
		stateCollection[4].addTransition(new Transition('1', 'x', moveLeft, stateCollection[5]));
		stateCollection[4].addTransition(new Transition('0', moveRight, stateCollection[qreject]));
		
		stateCollection[5].addTransition(new Transition('x', moveLeft, stateCollection[5]));
		stateCollection[5].addTransition(new Transition('0', moveLeft, stateCollection[5]));
		stateCollection[5].addTransition(new Transition('1', moveLeft, stateCollection[5]));
		stateCollection[5].addTransition(new Transition(' ', moveRight, stateCollection[0]));		
	}
	
	

}
