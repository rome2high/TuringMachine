public class Transition {
	
	private char transitionChar;
	private char writeChar;
	private char moveChar;
	State transitionState;
	
	public Transition(char transitionChar, char writeChar, char moveChar, State transitionState)
	{
		this.transitionChar = transitionChar;
		this.writeChar = writeChar;
		this.moveChar = moveChar;
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
	
	public char getMoveChar(){
		return moveChar;
	}
	
	public State getTransitionState()
	{
		return transitionState;
	}

}
