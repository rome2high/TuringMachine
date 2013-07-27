import javax.swing.*;

/* @Description: Creates a nice GUI for some Turing Machine */

public class TuringMachineGUI {
	
	private JFrame mainPanel;
	private JScrollPane scrollPanel;
	private JTextArea machineInformation;
	
	public TuringMachineGUI()
	{
		mainPanel = new JFrame("Machine's State Information");
		machineInformation = new JTextArea(1, 1);
		machineInformation.setEditable(false);
		scrollPanel = new JScrollPane(machineInformation);
		
		mainPanel.add(scrollPanel);
		
		
		mainPanel.setSize(1024, 200);
		mainPanel.setLocation(100, 100);		
	}
	
	public void addLine(String tapeText)
	{
		machineInformation.append(tapeText);
		mainPanel.revalidate();
	}
	
	public void setVisible(boolean visibility)
	{
		mainPanel.setVisible(visibility);
	}
	
	
	

}
