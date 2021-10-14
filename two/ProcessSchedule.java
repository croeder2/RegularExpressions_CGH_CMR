package two;
import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;

/**
 * 
 * This class works with the ExtractText class.
 * There are methods that perform regex pattern operations that find Course Information, etc.
 *
 * @authors Claire Roeder and Cameron Herbert
 * Version 1.0
 * Compiler Project 3
 * CS322 - Compiler Construction
 * Fall 2021
 *
 */
public class ProcessSchedule {
	
	public static void main(String args[]) throws IOException {
		
		try {
			
			courseinfoandTitle();
			classStatus();
			uniqueClasses();
			
		}//end try
		catch(Exception e){
			
			System.out.println("ERROR!");
			e.printStackTrace();
			
		}//end catch
		
	}//end main
	
	
	//This method retrieves course information and title
	public static void courseinfoandTitle() throws IOException{
		
		BufferedReader br = new BufferedReader(new FileReader("Schedule.txt"));
		FileWriter myWriter = new FileWriter("courseInfo.txt");
		
		String regex = "([A-Z]{4}|[A-Z]{3}|[A-Z]{2})-(\\d{3}[A-Z]|\\d{3})-(\\d{2})(.+?(?=\\d))";
		Pattern pattern = Pattern.compile(regex);
		
		String currentLine;
		
		//while loop that parses through the file
		while((currentLine = br.readLine()) != null) {
			
			Matcher matcher = pattern.matcher(currentLine);
			
			if(matcher.find()) {
				
				myWriter.write(matcher.group());
				
			}
		}
		
		br.close();
		myWriter.close();
		
		
	}
	
	//This method retrieves whether or not the class is open or closed
	public static void classStatus() throws IOException{
		
		BufferedReader br = new BufferedReader(new FileReader("Schedule.txt"));
		FileWriter myWriter = new FileWriter("classStatus.txt");
		
		String regex = "(Open|CLOSED)\\s(\\d{2}|\\d{1})\\s(\\d{2}|\\d|-\\d)";
		Pattern pattern = Pattern.compile(regex);
		
		String currentLine;
		
		//while loop that parses through the file
		while((currentLine = br.readLine()) != null) {
			
			Matcher matcher = pattern.matcher(currentLine);
			
			if(matcher.find()) {
				
				myWriter.write(matcher.group());
				
			}
		}
		
		br.close();
		myWriter.close();
		
		
	}
	
	//This method counts the number of unique courses
	public static void uniqueClasses() throws IOException{
		
		BufferedReader br = new BufferedReader(new FileReader("Schedule.txt"));
		FileWriter myWriter = new FileWriter("uniqueClasses.txt");
		
		//Regex Pattern
		String regex = "([A-Z]{4}|[A-Z]{3}|[A-Z]{2})-(\\d{3}[A-Z]|\\d{3})";
		Pattern pattern = Pattern.compile(regex);
		
		String currentLine;
		
		//ArrayList that holds unique instances
		ArrayList<String> unique = new ArrayList<String>(); 
		int counter = 0;
		
		//while loop that parses through the file
		while((currentLine = br.readLine()) != null) {
			
			Matcher matcher = pattern.matcher(currentLine);
			
			if(matcher.find()) {
				
				//checks to make sure course is unique
				if(!unique.contains(matcher.group())) {
					
				       unique.add(matcher.group());
				       counter ++;
				       myWriter.write(matcher.group());
				       
				}       
			}
		}
		
		br.close();
		myWriter.close();
		
		System.out.println(counter + " unique classes.");
	}
	
}