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
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class TuringMachineDriver implements ActionListener{
	
	static TuringMachine currentTuringMachine;	
	
	public static void main(String[] args) throws IOException
	{
		System.out.println("Welcome back Mr.Rome!");
		
		File fiTMdef = GetUserInput("Enter Turing Machine definition file then press enter : ");
			//remove when done; for testing purposed
		fiTMdef = new File("C:\\My Box Files\\A\\ICS441\\Program\\ma.txt");
		System.out.println(fiTMdef + " exist = " + fiTMdef.exists());
		
			//read defFilePath
				
				//validate defFilePath
		
		
		File fiTMInput = GetUserInput("Enter input file then press enter : ");
		//remove when done; for testing purposed
		fiTMInput = new File("C:\\My Box Files\\A\\ICS441\\Program\\InputFileExample\\0_1_0_1_0.txt");
		System.out.println(fiTMInput + " exist = " + fiTMInput.exists());
		
			//read input file
		
				//validate input file
		
		//run machine

		
		//using GUI
		//initGUI();
		
		System.out.println("end!");
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
			currentTuringMachine = new TuringMachine1(inputString);
			break;
		case "Machine 2":
			inputString = JOptionPane.showInputDialog("Please input a string for this machine: "); 
			currentTuringMachine = new TuringMachine2(inputString);
			break;
		case "Machine 3":
			inputString = JOptionPane.showInputDialog("Please input a string for this machine: "); 
			currentTuringMachine = new TuringMachine3(inputString);
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
