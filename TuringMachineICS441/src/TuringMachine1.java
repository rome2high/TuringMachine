import java.util.List;

/*	@Description: Describes the Turing Machine 0^n 1^n 2^n */

public class TuringMachine1 extends TuringMachine {	

	public TuringMachine1(String inputString, List<String> liTMdef) 
	{
		super(inputString, liTMdef);
	}
	
	public void initStates(List<String> liTMdef)
	{		
		boolean Rexist = false;
		char[] aStates = liTMdef.get(0).toCharArray();
		
		stateCollection = new State[aStates.length];
		
		for(int i = 0; i < aStates.length; i++){
			System.out.println(aStates[i]);
			switch(aStates[i]){
				case 'S':
					stateCollection[i] = new State("S");
					qstart = i;
					break;
				case 'A':
					stateCollection[i] = new State("A");
					qaccept = i;
					break;
				case 'R':
					stateCollection[i] = new State("R");
					qreject = i;
					Rexist = true;
					break;
				default:
					stateCollection[i] = new State(Character.toString(aStates[i]));
					break;
			}
		}
		
		if(!Rexist){
			//stateCollection[aStates.length + 1] = new State("qreject");
		}
			//stateCollection[i] = new State("q" + i);
		
//		qaccept = 4;
//		qreject = 5;
//		stateCollection[qaccept] = new State("qaccept");
//		stateCollection[qreject] = new State("qreject");		
	}
	
	public void initTransitions(List<String> liTMdef)
	{
		System.out.println("Init Transition");
			//get Trans Alphabet
		
		
		String alphabet = liTMdef.get(1);
		
		for(int i = 2; i < liTMdef.size(); i++){
			String currTrans = liTMdef.get(i);
			System.out.println(currTrans);
			
			char[] arTrans = liTMdef.get(i).toCharArray();
			
			char currState = arTrans[0];
			char readChar = arTrans[1];
			char gotoState = arTrans[3];
			char writeChar = arTrans[4];
			char moveDirection = arTrans[5];
			
			int sIndex = GetStateIndex(currState);
			int gotoIndex = GetStateIndex(gotoState);
			
			
			stateCollection[sIndex].addTransition(new Transition(readChar, writeChar, moveRight, stateCollection[gotoIndex]));
			
			
		}
		
		
		
//		stateCollection[0].addTransition(new Transition('x', moveRight, stateCollection[0]));
//		stateCollection[0].addTransition(new Transition(' ', moveRight, stateCollection[qaccept]));
//		stateCollection[0].addTransition(new Transition('1', moveRight, stateCollection[qreject]));
//		stateCollection[0].addTransition(new Transition('2', moveRight, stateCollection[qreject]));
//		stateCollection[0].addTransition(new Transition('0', 'x', moveRight, stateCollection[1]));
//		
//		stateCollection[1].addTransition(new Transition('x', moveRight, stateCollection[1]));
//		stateCollection[1].addTransition(new Transition('0', moveRight, stateCollection[1]));
//		stateCollection[1].addTransition(new Transition(' ', moveRight, stateCollection[qreject]));
//		stateCollection[1].addTransition(new Transition('2', moveRight, stateCollection[qreject]));
//		stateCollection[1].addTransition(new Transition('1', 'x', moveRight, stateCollection[2]));
//		
//		stateCollection[2].addTransition(new Transition('x', moveRight, stateCollection[2]));
//		stateCollection[2].addTransition(new Transition('1', moveRight, stateCollection[2]));
//		stateCollection[2].addTransition(new Transition('0', moveRight, stateCollection[qreject]));
//		stateCollection[2].addTransition(new Transition(' ', moveRight, stateCollection[qreject]));
//		stateCollection[2].addTransition(new Transition('2', 'x', moveLeft, stateCollection[3]));
//		
//		stateCollection[3].addTransition(new Transition('x', moveLeft, stateCollection[3]));
//		stateCollection[3].addTransition(new Transition('1', moveLeft, stateCollection[3]));
//		stateCollection[3].addTransition(new Transition('0', moveLeft, stateCollection[3]));
//		stateCollection[3].addTransition(new Transition(' ', moveRight, stateCollection[0]));		
	}

//	private int GetStateIndex(char state) {
//		
//		for  (int i = 0; i < stateCollection.length; i++){
//			State  s = stateCollection[i];
//			if(s.getName().equals(Character.toString(state))){
//				return i;
//			}
//		}
//		return -1;
//	}
	

}
