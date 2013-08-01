import java.io.IOException;
import java.util.List;

public abstract class TuringMachine {
	
	public abstract void InitStates(List<String> liTMdef);
	public abstract void InitTransitions(List<String> liTMdef);;
	
	protected Tape tape;
	protected State [] stateCollection;
	
	protected State currentState;
	protected boolean progReject = false;
	
	public TuringMachine(String inputString, List<String> liTMdef)
	{
		tape = new Tape(inputString);
		InitStates(liTMdef);
		InitTransitions(liTMdef);
	}
	
	public boolean Run()
	{
		int counter = 0;
		String result = "";
		String userInput = "";
		
		while (!userInput.equals("V") && !userInput.equals("S")){
			System.out.print("Enter 'V' for Verbose Mode or 'S' for Summary Mode: ");
			try {
				userInput = TuringMachineMain.ConsoleInput().toUpperCase();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(userInput.equals("V")){
			System.out.println("\nRunning Verbose Mode...");
		}else{
			System.out.println("\nRunning Summary Mode...");
		}
		
		currentState = stateCollection[GetStateIndex('S')];
		
		System.out.println("---- Tape Initial Configuration ----");
		System.out.println(tape.getTape().substring(0, tape.getPosition()) + currentState.getName() + "["+ tape.read()+"]" + tape.getTape().substring(tape.getPosition() + 1, tape.getTape().length()) + "\n");
		
		while(currentState != stateCollection[GetStateIndex('A')] && !currentState.getName().equals("R"))
		{
			if(counter >= 1000){
				result = "Loop";
				break;
			}else if (progReject){
				result = "Reject";
				break;
			}
			currentState = Transition(currentState, tape.read());
			if(userInput.equals("V")){
				System.out.println(tape.getTape().substring(0, tape.getPosition()) + currentState.getName() + "["+ tape.read()+"]" + tape.getTape().substring(tape.getPosition() + 1, tape.getTape().length()));
			}
			counter++;
		}
		
		if(!result.equals("Loop")){
			if(currentState.getName().equals("A"))
				result = "Accept";
			else if (currentState.getName().equals("R"))
				result = "Reject";
		}
		
		System.out.println("\n---- Tape Final Configuration ----");
		System.out.println(result + " : " + tape.getTape().substring(0, tape.getPosition()) + currentState.getName() + "["+ tape.read()+"]" + tape.getTape().substring(tape.getPosition() + 1, tape.getTape().length()));
		
		return currentState == stateCollection[GetStateIndex('A')];
	}
	
	public State Transition(State currentState, char currentChar)
	{
		//Throws NullPointerException if the transition doesn't exist. -> machine reject
		try
		{
			if(currentChar == ' '){
				tape.write('_');
				currentChar = '_';
			}else{
				tape.write(currentState.getTransition(currentChar).getWriteChar());
			}
			switch(currentState.getTransition(currentChar).getMoveChar()){
				case 'R':
					tape.moveRight();
					break;
				case 'L':
					tape.moveLeft();
					break;
				case 'S':
					//no transition -> stay put
					break;
				default:
					System.out.println("Invalid Move; Reject");
					break;
			}
			
			return currentState.getTransition(currentChar).getTransitionState();
		}
		catch(Exception NullPointerException)
		{
			progReject = true;
			return currentState;
		}
	}
	
	public int GetStateIndex(char state) {
		for  (int i = 0; i < stateCollection.length; i++){
			State  s = stateCollection[i];
			if(s.getName().equals(Character.toString(state))){
				return i;
			}
		}
		return -1;
	}
}
