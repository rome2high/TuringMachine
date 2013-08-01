/*	@Description: Simulates the tape that a Turing Machine uses for memory.
 * 		Since the tape is defined as a collection of characters, it is simulated as a String (character array) and thus is confined to java's String implementation's bounds.
 */
public class TapeMemory {
	
	private String memory;
	private int tapeHead;
	
	//Default Constructor, puts a space at the beginning and end of the tape
	public TapeMemory(String testString)
	{
		memory = "  " + testString + "  ";
		tapeHead = 2;		
	}
	
	/* Constructors that deal with Turing Machines that need some number of spaces at the beginning and/or end of the tape */
	public TapeMemory(int blankSpace, String testString)
	{
		String emptyString = "";
		
		for(int i = 0; i < blankSpace; i++)
			emptyString += " ";
		
		memory = emptyString + testString + "  ";		
		tapeHead = blankSpace;		
	}
	
	public TapeMemory(String testString, int blankSpace)
	{
		String emptyString = "";
		
		for(int i = 0; i < blankSpace; i++)
			emptyString += " ";
		
		memory = "  " + testString + emptyString;		
		tapeHead = 2;		
	}
	
	public char read()
	{
		return memory.charAt(tapeHead);
	}
	
	public void write(char inputChar)
	{
		memory = memory.substring(0, tapeHead) + inputChar + memory.substring(tapeHead+1, memory.length());
	}
	
	public void moveLeft()
	{
		if(tapeHead > 0)
			tapeHead--;		
	}
	
	public void moveRight()
	{
		if(tapeHead < memory.length() - 1)
			tapeHead++;
	}
	
	/* Used for possibly visual representation of the turing machine and/or debugging */
	public String getTape()
	{
		return memory;
	}
	
	public int getPosition()
	{
		return tapeHead;
	}
}
