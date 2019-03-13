package textExcel;

//Jeffrey Liu; March 2019
//Version Number "I don't know"

import java.io.FileNotFoundException;
import java.util.Scanner;

// Update this file with your own code.

public class TextExcel
{

	public static void main(String[] args) {
		/*Scanner input = new Scanner(System.in);
		String command = "";
		Spreadsheet sheet = new Spreadsheet();
		while(true) {
			command = input.nextLine();
			if(command.equalsIgnoreCase("quit")) {
				break;
			}
			System.out.println(sheet.processCommand(command));
		}*/
		Spreadsheet fuck = new Spreadsheet();
		System.out.print(fuck.getGridText());
		
		/*
		SpreadsheetLocation temp = new SpreadsheetLocation("A1");
		System.out.println(temp.getRow());
		*/
	}
}
