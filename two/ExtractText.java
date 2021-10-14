package two;


import org.apache.pdfbox.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import java.io.*;
import org.apache.pdfbox.text.PDFTextStripper;
import java.util.Scanner;

/**
 * This class takes in a path to a pdf file from the user and extracts the text into a text file
 *
 * @author Cameron Herbert and Claire Roeder
 * Version 1.0
 * Compiler Project 3
 * CS322 - Compiler Construction
 * Fall 2021
 *
 */
public class ExtractText {
	
	
	public static void main(String args[]) throws IOException{
		
		//Scanner
		Scanner scan = new Scanner(System.in);
		
		//Scanner takes in a path to the pdf
		System.out.print("Enter the path to the pdf: ");
		
		//Path entered is saved as a file
		String path = scan.nextLine();
		File file = new File(path);
		
		//PDDocument loads the pdf as a PDDocument object
		PDDocument pdd = PDDocument.load(file);
		
		//PDFTextStripper
		PDFTextStripper pdfStripper = new PDFTextStripper();
		
		//Text is reead from pdf and written into a new file
		String textFile = pdfStripper.getText(pdd);
		FileWriter myWriter = new FileWriter("Schedule.txt");
		myWriter.write(textFile);
		
		myWriter.close();

		}//end main
	
	}//end ExtractText