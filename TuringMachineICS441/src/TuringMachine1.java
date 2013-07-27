/*	@Description: Describes the Turing Machine 0^n 1^n 2^n */

public class TuringMachine1 extends TuringMachine {	

	public TuringMachine1(String inputString) 
	{
		super(inputString);
	}
	
	public void initStates()
	{
		stateCollection = new State[6];
		
		for(int i = 0; i < 4; i++)
			stateCollection[i] = new State("q" + i);
		
		qaccept = 4;
		qreject = 5;
		stateCollection[qaccept] = new State("qaccept");
		stateCollection[qreject] = new State("qreject");		
	}
	
	public void initTransitions()
	{
		stateCollection[0].addTransition(new Transition('x', moveRight, stateCollection[0]));
		stateCollection[0].addTransition(new Transition(' ', moveRight, stateCollection[qaccept]));
		stateCollection[0].addTransition(new Transition('1', moveRight, stateCollection[qreject]));
		stateCollection[0].addTransition(new Transition('2', moveRight, stateCollection[qreject]));
		stateCollection[0].addTransition(new Transition('0', 'x', moveRight, stateCollection[1]));
		
		stateCollection[1].addTransition(new Transition('x', moveRight, stateCollection[1]));
		stateCollection[1].addTransition(new Transition('0', moveRight, stateCollection[1]));
		stateCollection[1].addTransition(new Transition(' ', moveRight, stateCollection[qreject]));
		stateCollection[1].addTransition(new Transition('2', moveRight, stateCollection[qreject]));
		stateCollection[1].addTransition(new Transition('1', 'x', moveRight, stateCollection[2]));
		
		stateCollection[2].addTransition(new Transition('x', moveRight, stateCollection[2]));
		stateCollection[2].addTransition(new Transition('1', moveRight, stateCollection[2]));
		stateCollection[2].addTransition(new Transition('0', moveRight, stateCollection[qreject]));
		stateCollection[2].addTransition(new Transition(' ', moveRight, stateCollection[qreject]));
		stateCollection[2].addTransition(new Transition('2', 'x', moveLeft, stateCollection[3]));
		
		stateCollection[3].addTransition(new Transition('x', moveLeft, stateCollection[3]));
		stateCollection[3].addTransition(new Transition('1', moveLeft, stateCollection[3]));
		stateCollection[3].addTransition(new Transition('0', moveLeft, stateCollection[3]));
		stateCollection[3].addTransition(new Transition(' ', moveRight, stateCollection[0]));		
	}
	

}
