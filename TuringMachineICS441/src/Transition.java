/* @Description: Transitions belong to States. 
 * 		Given some character read from the tape, the Transition will be able to provide the Turing Machine with:
 * 			-What to write to the tape 
 * 			-Whether to move the tapeHead left or write
 * 			-What state to transition into
 */
public class Transition {
	
	private char transitionChar;
	private char writeChar;
	private boolean moveLeft;
	
	State transitionState;
	
	/* If no writeChar is provided, the character that is read from the tape is written to the tape */
	public Transition(char transitionChar, boolean moveLeft, State transitionState)
	{
		this.transitionChar = transitionChar;
		this.writeChar = transitionChar;
		this.moveLeft = moveLeft;
		this.transitionState = transitionState;
	}
	
	public Transition(char transitionChar, char writeChar, boolean moveLeft, State transitionState)
	{
		this.transitionChar = transitionChar;
		this.writeChar = writeChar;
		this.moveLeft = moveLeft;
		this.transitionState = transitionState;
	}
	
	public char getTransitionChar()
	{
		return transitionChar;
	}
	
	public char getWriteChar()
	{
		return writeChar;
	}
	
	public boolean getMoveLeft()
	{
		return moveLeft;
	}
	
	public State getTransitionState()
	{
		return transitionState;
	}

}
