package textExcel;

import java.util.Scanner;

//Jeffrey Liu; March 2019 - May 2019
//Version Number "I don't know"

// Update this file with your own code.

public class TextExcel{

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String command = "";
		Spreadsheet sheet = new Spreadsheet();
		while(true) {
			System.out.print("Enter command: ");
			command = input.nextLine();
			if(command.equalsIgnoreCase("quit")) {
				break;
			}
			System.out.println(sheet.processCommand(command));
			
		}
	}
}
