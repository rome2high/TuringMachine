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
		
		if(liTMdef.get(0).contains("R")){
			stateCollection = new State[aStates.length];
		}else{
			stateCollection = new State[aStates.length + 1];
			stateCollection[aStates.length] = new State("R");
			}
		
		for(int i = 0; i < aStates.length; i++){
			//System.out.println(aStates[i]);
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
					break;
				default:
					stateCollection[i] = new State(Character.toString(aStates[i]));
					break;
			}
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
		
		for(int i = 2; i < liTMdef.size(); i++){
			//System.out.println(liTMdef.get(i));
			
			boolean moveDir;
			char[] arTrans = liTMdef.get(i).toCharArray();
			
			
			char currState = arTrans[0];
			char readChar = arTrans[1];
			char gotoState = arTrans[3];
			char writeChar = arTrans[4];
			char moveDirection = arTrans[5];
			
			if(moveDirection == 'R')
				moveDir = moveRight;
			else if (moveDirection == 'L')
				moveDir = moveLeft;
			else
				moveDir = stayPut = true;
			
			int sIndex = GetStateIndex(currState);
			int gotoIndex = GetStateIndex(gotoState);
			
			stateCollection[sIndex].addTransition(new Transition(readChar, writeChar, moveDirection, stateCollection[gotoIndex]));	
		}		
	}	

}
