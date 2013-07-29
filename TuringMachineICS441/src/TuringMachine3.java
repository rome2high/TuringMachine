import java.util.List;

/* @Description: Describes the Turing Machine 0^n 1^n^2 */

public class TuringMachine3 extends TuringMachine{

	public TuringMachine3(String inputString, List<String> liTMdef) 
	{
		//super(inputString, inputString.length());
		super(inputString, liTMdef);
	}
	
	public void initStates(List<String>	liTMdef)
	{
		stateCollection = new State[15];
		for(int i = 0; i < 13; i++)
			stateCollection[i] = new State("q" + i);
		
		qaccept = 13;
		qreject = 14;
		
		stateCollection[qaccept] = new State("qaccept");
		stateCollection[qreject] = new State("qreject");		
	}
	
	public void initTransitions(List<String> liTMdef)
	{
		stateCollection[0].addTransition(new Transition(' ', moveRight, stateCollection[qaccept]));
		stateCollection[0].addTransition(new Transition('0', moveRight, stateCollection[0]));
		stateCollection[0].addTransition(new Transition('1', moveRight, stateCollection[1]));
		
		stateCollection[1].addTransition(new Transition('1', moveLeft, stateCollection[2]));
		
		stateCollection[2].addTransition(new Transition('0', 'x', moveRight, stateCollection[3]));
		stateCollection[2].addTransition(new Transition('1', moveLeft, stateCollection[6]));
		stateCollection[2].addTransition(new Transition('z', moveLeft, stateCollection[9]));
		
		stateCollection[3].addTransition(new Transition('1', moveRight, stateCollection[3]));
		stateCollection[3].addTransition(new Transition('0', moveRight, stateCollection[3]));
		stateCollection[3].addTransition(new Transition('z', moveLeft, stateCollection[4]));
		stateCollection[3].addTransition(new Transition('y', moveLeft, stateCollection[4]));
		stateCollection[3].addTransition(new Transition(' ', moveLeft, stateCollection[4]));
		
		stateCollection[4].addTransition(new Transition('0', moveRight, stateCollection[qreject]));
		stateCollection[4].addTransition(new Transition('1', 'z', moveLeft, stateCollection[5]));
		
		stateCollection[5].addTransition(new Transition('0', moveLeft, stateCollection[5]));
		stateCollection[5].addTransition(new Transition('1', moveLeft, stateCollection[5]));
		stateCollection[5].addTransition(new Transition(' ', moveRight, stateCollection[2]));
		stateCollection[5].addTransition(new Transition('x', moveRight, stateCollection[2]));
		
		stateCollection[6].addTransition(new Transition('x', '0', moveLeft, stateCollection[6]));
		stateCollection[6].addTransition(new Transition('0', '0', moveLeft, stateCollection[6]));
		stateCollection[6].addTransition(new Transition(' ', moveRight, stateCollection[7]));
		
		stateCollection[7].addTransition(new Transition('z', moveRight, stateCollection[7]));
		stateCollection[7].addTransition(new Transition('x', moveRight, stateCollection[7]));
		stateCollection[7].addTransition(new Transition('y', moveRight, stateCollection[7]));
		stateCollection[7].addTransition(new Transition('0', moveRight, stateCollection[7]));
		stateCollection[7].addTransition(new Transition('1', moveRight, stateCollection[7]));
		stateCollection[7].addTransition(new Transition(' ', 'y', moveLeft, stateCollection[8]));
		
		stateCollection[8].addTransition(new Transition('z', moveLeft, stateCollection[8]));
		stateCollection[8].addTransition(new Transition('x', moveLeft, stateCollection[8]));
		stateCollection[8].addTransition(new Transition('y', moveLeft, stateCollection[8]));
		stateCollection[8].addTransition(new Transition('0', moveLeft, stateCollection[8]));
		stateCollection[8].addTransition(new Transition('1', moveLeft, stateCollection[8]));
		stateCollection[8].addTransition(new Transition(' ', moveRight, stateCollection[2]));
		
		stateCollection[9].addTransition(new Transition('x', moveLeft, stateCollection[9]));
		stateCollection[9].addTransition(new Transition(' ', moveRight, stateCollection[10]));
		
		stateCollection[10].addTransition(new Transition('z', moveRight, stateCollection[10]));
		stateCollection[10].addTransition(new Transition(' ', moveRight, stateCollection[qaccept]));
		stateCollection[10].addTransition(new Transition('x', 'z', moveRight, stateCollection[11]));
		stateCollection[10].addTransition(new Transition('y', moveRight, stateCollection[qreject]));
		
		stateCollection[11].addTransition(new Transition('x', moveRight, stateCollection[11]));
		stateCollection[11].addTransition(new Transition('z', moveRight, stateCollection[11]));
		stateCollection[11].addTransition(new Transition('y', 'z', moveLeft, stateCollection[12]));
		stateCollection[11].addTransition(new Transition(' ', moveLeft, stateCollection[qreject]));
		
		stateCollection[12].addTransition(new Transition('x', moveLeft, stateCollection[12]));
		stateCollection[12].addTransition(new Transition('z', moveLeft, stateCollection[12]));
		stateCollection[12].addTransition(new Transition(' ', moveRight, stateCollection[10]));		
	}
	

}
