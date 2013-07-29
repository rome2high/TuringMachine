import java.io.Console;
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
	
	/* Some Turing Machines may require additional space at the beginning and/or end of the string for proper operation */
//	public TuringMachine(String inputString, int blankSpace)
//	{
//		tape = new TapeMemory(inputString, blankSpace);
//		initStates();
//		initTransitions();
//	}
//	
//	public TuringMachine(int blankSpace, String inputString)
//	{
//		tape = new TapeMemory(blankSpace, inputString);
//		initStates();
//		initTransitions();
//	}
//	
//	public TuringMachine(int preBlankSpace, String inputString, int postBlankSpace)
//	{
//		tape = new TapeMemory(preBlankSpace, inputString, postBlankSpace);
//		initStates();
//		initTransitions();
//	}
	
	/* The default run method. Turing Machines start in some initial state and execute transitions based on what is read from the tape until they reach the accept or reject state.
	 * 		In this code, the accept state is second from the end of the collection of states, and the reject state is the last state in the array.
	 * 
	 * @NOTE: OVERRIDE this method if your specific Turing Machine does not start in a state at the beginning of the state array, or does not have the accept/reject state placed as above
	 */
	public boolean run()
	{
		TuringMachineGUI guiRepresentation;		
		int response;
		
	    //response = JOptionPane.showConfirmDialog(null,"Do you want verbose output?", "Verbose Output Selection", JOptionPane.YES_NO_OPTION);
	    //guiRepresentation = new TuringMachineGUI();
		
		response = 0;
	    
	    int sIndex = GetStateIndex('S');
	    int aIndex = GetStateIndex('A');
	    int rIndex = GetStateIndex('R');
		currentState = stateCollection[sIndex];
		State acceptState = stateCollection[aIndex];
		State rejectState = stateCollection[rIndex];
		
		
		if(response == 0)
		{
			//guiRepresentation.setVisible(true);
			//guiRepresentation.addLine("State: " + currentState.getName() + ", Memory: " + tape.getTape().substring(0, tape.getPosition()) + "[" + tape.read() + "]" + tape.getTape().substring(tape.getPosition() + 1, tape.getTape().length()) + "\n");
			System.out.println("State: " + currentState.getName() + ", Memory: " + tape.getTape().substring(0, tape.getPosition()) + "[" + tape.read() + "]" + tape.getTape().substring(tape.getPosition() + 1, tape.getTape().length()) + "\n");
		}
		
		int count = 0;
		while(currentState != acceptState && currentState != rejectState)	// stateCollection[aIndex])// && currentState != stateCollection[stateCollection.length - 1])
		{
			if(count >= 1000){
				currentState = rejectState;
				break;
			}
			currentState = transition(currentState, tape.read());
			if(response == 0){
				//guiRepresentation.addLine("State: " + currentState.getName() + ", Memory: " + tape.getTape().substring(0, tape.getPosition()) + "[" + tape.read() + "]" + tape.getTape().substring(tape.getPosition() + 1, tape.getTape().length()) + "\n");
				System.out.println("State: " + currentState.getName() + ", Memory: " + tape.getTape().substring(0, tape.getPosition()) + "[" + tape.read() + "]" + tape.getTape().substring(tape.getPosition() + 1, tape.getTape().length()) + "\n");
			}
			count++;
		}
			//print final
		System.out.println("Final....");
		return currentState == acceptState;
	}
	
	/* The default transition method. Looks for a transition for some character that the current state has. If there is none, a NullPointerException is thrown, which symbolizes that the state doesn't have a transition for that character, and the machine will immediately reject.
	 * 
	 * @NOTE: OVERRIDE this method if you want some non-typical transition behavior for your Turing Machine. This should not be necessary.
	 */
	public State transition(State currentState, char currentChar)
	{		
		try
		{
			tape.write(currentState.getTransition(currentChar).getWriteChar());
			
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
			
//			if(currentState.getTransition(currentChar).getMoveLeft())
//				tape.moveLeft();
//			else
//				tape.moveRight();	
			
			return currentState.getTransition(currentChar).getTransitionState();
		}
		catch(Exception NullPointerException)
		{
			return stateCollection[GetStateIndex('R')]; //illegal move; enter reject state
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
