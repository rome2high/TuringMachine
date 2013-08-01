import java.io.Console;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

/* @Description: This class describes a TuringMachine. Individual Turing Machines that you wish to implement must extend this class and provide their own initStates() and initTransitions() method contents.
 * 		Turing Machines consist of a tape, a collection of states, and the knowledge of the state that it is currently in.
 */
public abstract class TuringMachine {
	
	protected TapeMemory tape;
	protected State [] stateCollection;
	
	protected State currentState;
	
	//Variables used for readability-sake in instanced Turing Machine code
	protected int qstart;
	protected int qaccept;
	protected int qreject;
	
	//In correspondence with Transition constructors
	protected boolean progReject = false;
	protected boolean moveRight = false;
	protected boolean moveLeft = true;
	protected boolean stayPut = false;
	
	protected char moveDir;
	
	public TuringMachine(String inputString, List<String> liTMdef)
	{
		tape = new TapeMemory(inputString);
		initStates(liTMdef);
		initTransitions(liTMdef);
	}
	
	/* The default run method. Turing Machines start in some initial state and execute transitions based on what is read from the tape until they reach the accept or reject state.
	 * 		In this code, the accept state is second from the end of the collection of states, and the reject state is the last state in the array.
	 * 
	 * @NOTE: OVERRIDE this method if your specific Turing Machine does not start in a state at the beginning of the state array, or does not have the accept/reject state placed as above
	 */
	public boolean run()
	{
		int counter = 0;
		String result = "";
		String input = "";
		
		while (!input.equals("Y") && !input.equals("N")){
			System.out.print("Do you want verbose output mode? (Y/N) : ");
			try {
				input = TuringMachineDriver.ConsoleInput().toUpperCase();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		boolean verboseMode = input.equals("Y");
		
		currentState = stateCollection[GetStateIndex('S')];
		State acceptState = stateCollection[GetStateIndex('A')];
		
		
		//State rejectState = stateCollection[GetStateIndex('R')];

		
		System.out.println("---- Tape Initial Configuration ----");
		System.out.println("Current State: " + currentState.getName() + ", Tape: " + tape.getTape().substring(0, tape.getPosition()) + "[" + tape.read() + "]" + tape.getTape().substring(tape.getPosition() + 1, tape.getTape().length()) + "\n");

		
		while(currentState != acceptState && !currentState.getName().equals("R"))	// stateCollection[aIndex])// && currentState != stateCollection[stateCollection.length - 1])
		{
			if(counter >= 1000){
				result = "Loop";
				//currentState = rejectState;
				break;
			}else if (progReject){
				result = "Reject";
				break;
			}
			currentState = transition(currentState, tape.read());
			if(verboseMode){
				//guiRepresentation.addLine("State: " + currentState.getName() + ", Memory: " + tape.getTape().substring(0, tape.getPosition()) + "[" + tape.read() + "]" + tape.getTape().substring(tape.getPosition() + 1, tape.getTape().length()) + "\n");
				
				System.out.println("Current State: " + currentState.getName() + ", Tape: " + tape.getTape().substring(0, tape.getPosition()) + "[" + tape.read() + "]" + tape.getTape().substring(tape.getPosition() + 1, tape.getTape().length()));
			}
			counter++;
		}
		
		if(!result.equals("Loop")){
			if(currentState.getName().equals("A"))
				result = "Accept";
		}
		
		
			//print final
		System.out.println("---- Tape Final Configuration ----");
		System.out.println(tape.getPosition());
		System.out.println(result + " State: " + currentState.getName() + ", Tape: " + tape.getTape().substring(0, tape.getPosition()) + "[" + tape.read() + "]" + tape.getTape().substring(tape.getPosition() + 1, tape.getTape().length()) + "\n");
		
		//System.out.println("Final: Count-" +count + " State-"+currentState.getName());
		
		return currentState == acceptState;
	}
	
	/* The default transition method. Looks for a transition for some character that the current state has. If there is none, a NullPointerException is thrown, which symbolizes that the state doesn't have a transition for that character, and the machine will immediately reject.
	 * 
	 * @NOTE: OVERRIDE this method if you want some non-typical transition behavior for your Turing Machine. This should not be necessary.
	 */
	public State transition(State currentState, char currentChar)
	{
//		if(currentChar == ' '){
//			currentChar = '_';
//		}
		try
		{
			if(currentChar == ' '){
				tape.write('_');
			}else{
				tape.write(currentState.getTransition(currentChar).getWriteChar());
			}
			switch(currentState.getTransition(currentChar).getMoveDir()){
				case 'R':
					tape.moveRight();
					break;
				case 'L':
					tape.moveLeft();
					break;
				case 'S':
					//no transition
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
			//return stateCollection[GetStateIndex('R')]; //illegal move; enter reject state
			//return stateCollection[stateCollection.length - 1];
		}
	}
	
	// @OVERRIDE these functions in any specific implementation to properly initialize the States and Transitions for some Turing Machine. Look at the provided example Turing Machines for guidance.
	public abstract void initStates(List<String> liTMdef);
	public abstract void initTransitions(List<String> liTMdef);;
	
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
