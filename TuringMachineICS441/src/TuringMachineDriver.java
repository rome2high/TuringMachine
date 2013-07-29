/* @Description: Driver class for the provided Turing Machine examples 
 * @Author: Eli Pinkerton
 */

import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TuringMachineDriver implements ActionListener{
	
	static TuringMachine currentTuringMachine;
	static List<String> liTMdef;
	
	public static void main(String[] args) throws IOException
	{
		System.out.println("Welcome back Mr.Rome!");
		
		File fiTMdef = null;// GetUserInput("Enter Turing Machine definition file then press enter : ");
			//remove when done; for testing purposed
		fiTMdef = new File("C:\\My Box Files\\A\\ICS441\\Program\\ma.txt");
		//fiTMdef = new File("H:\\TuringMachineICS441\\doc\\ma.txt");
		System.out.println(fiTMdef + " exist = " + fiTMdef.exists());
		
			//read defFilePath
		liTMdef = ReadFile(fiTMdef.toString());
		
			//return if definition file less than 3 line; kill app for now but can handle better
		if(liTMdef.size() < 3)
			return;
		
				//validate defFilePath
		String[] arTMdef = liTMdef.toArray(new String[liTMdef.size()]);
		
		for (int i = 0; i < arTMdef.length; i++){
			System.out.println(arTMdef[i]);
		}
		
		
		File fiTMInput = null;// GetUserInput("Enter input file then press enter : ");
		//remove when done; for testing purposed
		fiTMInput = new File("C:\\My Box Files\\A\\ICS441\\Program\\InputFileExample\\all0s.txt");
		//fiTMInput = new File("H:\\TuringMachineICS441\\doc\\InputFileExample\\0_1_0_1_0.txt");
		System.out.println(fiTMInput + " exist = " + fiTMInput.exists());
		
			//read input file
		List<String> liTMInput = ReadFile(fiTMInput.toString());
		
				//validate input file
		String[] arTMInput = liTMInput.toArray(new String[liTMInput.size()]);
				
		for (int j = 0; j < arTMInput.length; j++){
			System.out.println(arTMInput[j]);
		}
		
		//run machine
		
		currentTuringMachine = new TuringMachine1(arTMInput[0], liTMdef);

		currentTuringMachine.run();
		
		//using GUI
		//initGUI();
		
		System.out.println("end!");
	}

	private static List<String> ReadFile(String path) throws IOException {
		
		List<String> liString =  new ArrayList<String>();
		
		BufferedReader br = new BufferedReader(new FileReader(path));
	    try {
	        String line = br.readLine();

	        while (line != null) {
	        	liString.add(line);
	            line = br.readLine();
	        }
	    } finally {
	        br.close();
	    }
		return liString;
	}

	private static File GetUserInput(String prompt) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = "";
        
        try{
        	System.out.print(prompt);
        	s = br.readLine();
        	File dir = new File(s);
        	while (!dir.exists()){		//user must enter a string
        		System.out.print("File Not Exist!; " + prompt);
        		dir = new File(br.readLine());
        	}
        	return dir;
        }catch(NumberFormatException nfe){
            System.err.println("Invalid Format!");
        }
		return null;
	}

	public static void initGUI()
	{
		TuringMachineDriver actionListener = new TuringMachineDriver();
		JFrame container;
		
		JButton turingMachine1;
		JButton turingMachine2;
		JButton turingMachine3;
		JButton exitButton;
		container = new JFrame();

		turingMachine1 = new JButton("Machine 1");
		turingMachine1.setSelected(true);
		turingMachine1.addActionListener(actionListener);
		turingMachine2 = new JButton("Machine 2");
		turingMachine2.addActionListener(actionListener);
		turingMachine3 = new JButton("Machine 3");
		turingMachine3.addActionListener(actionListener);
		exitButton = new JButton("Exit");
		exitButton.addActionListener(actionListener);
		

		container.setLayout(new FlowLayout());
		container.add(turingMachine1);
		container.add(turingMachine2);
		container.add(turingMachine3);
		container.add(exitButton);
		
		container.setVisible(true);
		container.setSize(300, 200);
		container.setLocation(500,500);
		container.setDefaultCloseOperation(3);
		container.setResizable(false);
		
	}
	
	public void actionPerformed(ActionEvent someEvent)
	{
	    String inputString;
		
		switch(someEvent.getActionCommand())
		{
		case "Machine 1":
			inputString = JOptionPane.showInputDialog("Please input a string for this machine: "); 
			currentTuringMachine = new TuringMachine1(inputString, liTMdef);
			break;
		default:
			System.exit(1);
			break;		
		}
		
		runMachine();
		
	}
	
	public static void runMachine()
	{
		if(currentTuringMachine.run())
			JOptionPane.showMessageDialog(null, "The machine accepts the string!", "Sucess", JOptionPane.QUESTION_MESSAGE); 
		else
			JOptionPane.showMessageDialog(null, "The machine does not accept the string!", "Failure", JOptionPane.ERROR_MESSAGE); 
	}

}
