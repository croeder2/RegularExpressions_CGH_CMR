package three;
/**
 * This class takes a log file and print flag and then searches through for IP addresses and usernames.
 * It puts all of the IP addresses into a hash map with the number of occurrences
 * and puts all of the usernames into a hasmap with the number of occurrences.
 * It also includes a parser that counts the number of lines parsed through.
 * There is an if statement that calls methods depending on what the print flag input was
 * 
 * @authors Claire Roeder and Cameron Herbert
 * Version 1.0
 * Compiler Project 3
 * CS322 - Compiler Construction
 * Fall 2021
 */
import java.util.Scanner;
import java.util.HashMap;
import java.io.*;
import java.util.regex.*;

public class LogFileProcessor {
	
	public static int parsed = 0; //to count how many lines are parsed
	public static int IPAddresses = 0; //to count unique addresses
	public static int uniqueUsers = 0; //to count unique users
	public static String parsedString; //to print how many lines are parsed
	public static String IP; //to print how many unique IP addresses
	public static String Users; //to print how many unique users
	public static HashMap<String, Integer> ipmap = new HashMap<String,Integer>(); //HashMap for IP addresses
	public static HashMap<String, Integer> user = new HashMap<String,Integer>(); //HashMap for users
	
	
	public static void main(String[] args) {
		
			//Get flags and filename
			Scanner scan = new Scanner(System.in); 
			String fileName;
			int flag;
			System.out.print("Please enter the file name: ");
			fileName = scan.nextLine();
			System.out.print("Please enter the print flag: ");
			flag = scan.nextInt();
			scan.close();
			
			try {
				
				IPAddresses(fileName);
				users(fileName);
			
			}//end try
			catch(Exception e){
				
				System.out.println("ERROR!");
				e.printStackTrace();
				
			}//end catch
			
			
			
			if(flag == 1) {
				
				for (String print: ipmap.keySet()){
					
		            System.out.println(print + ": " + ipmap.get(print)); //print every key and value in hashmap
		            
		        } //close for loop
				
				Default(); //print default output
			} //close if
			
			else if(flag == 2) {
				
				for (String print: ipmap.keySet()){
					
		            System.out.println(print + ": " + ipmap.get(print)); //print every key and value in hashmap
		            
		        } //close for loop
				
				Default(); //print default output
			}//close else
			
			else {
				
				Default(); //print default output
				
			}//close else
			
	} //end main		
	
	
		public static void Default() {
			
			//print out strings for default output
			System.out.println(parsedString);
			System.out.println(IP);
			System.out.println(Users);
			
		} //close default method
	
		public static void IPAddresses(String fileName) throws Exception{ //METHOD ZERO
			
			
			String regexPattern = "\\d*[.]\\d*[.]\\d*[.]\\d*"; //pattern to get IP addresses
			
			File logFile = new File(fileName); //create file object
			BufferedReader br = new BufferedReader(new FileReader(logFile)); //create buffered reader object to read file
			Pattern pattern = Pattern.compile(regexPattern); //create pattern object to hold regex pattern
			
			String currentLine; //Holds current line being parsed
			int occurrence; //Holds value from hash map so it can be incremented
			
			while((currentLine = br.readLine()) != null){
				
				parsed ++; //keep track of lines being parsed
				Matcher matcher = pattern.matcher(currentLine); //matcher object to match with regex pattern
				
	    		if(matcher.find()) {
	    			
	    			//if an IP address match is found and it is already in the hashmap then the current integer value is incremented
	    			if(ipmap.containsKey(matcher.group())) {
	    				
	    				occurrence = ipmap.get(matcher.group());
	    				occurrence ++;
	    				ipmap.put(matcher.group(), occurrence);
	    					
	    			}//close if
	    			
	    			//if the iP address has not been found yet it is added to the hashmap as a key along with a value of 1 since it is the first occurrence
	    			else {
	    				
	    				IPAddresses ++;
	    				ipmap.put(matcher.group(), 1);
	    				
	    			} //close else
	    		}//end if	 
	    	}//end while
	    	
			parsedString = parsed + " lines in the log file were parsed."; //string to be printed
			IP = "There are " + IPAddresses + " unique IP addresses in the log."; //string to be printed
			br.close();
			
		} // Close IPAddress method
		
		public static void users(String fileName) throws Exception{ 
			
			String regexPattern = "\\[(\\d{5})\\]"; //pattern to get users
			
			File logFile = new File(fileName); //create file object
			BufferedReader br = new BufferedReader(new FileReader(logFile)); //create buffered reader object to read file
			Pattern pattern = Pattern.compile(regexPattern); //create pattern object to hold regex pattern
			
			String currentLine; //Holds current line being parsed
			int occurrence; //Holds value from hash map so it can be incremented
			
			while((currentLine = br.readLine()) != null){

				Matcher matcher = pattern.matcher(currentLine); //matcher object to match with regex pattern
				
	    		if(matcher.find()) {
	    			
	    			//if a user match is found and it is already in the hashmap then the current integer value is incremented
	    			if(user.containsKey(matcher.group())) {
	    				
	    				occurrence = user.get(matcher.group());
	    				occurrence ++;
	    				user.put(matcher.group(), occurrence);
	    					
	    			}//close if
	    			
	    			//if the iP address has not been found yet it is added to the hashmap as a key along with a value of 1 since it is the first occurrence
	    			else {
	    				
	    				uniqueUsers ++;
	    				user.put(matcher.group(), 1);
	    				
	    			}//close else
	    		} //close if	 
	    	} //close while
			
			br.close();
			Users = "There are " + uniqueUsers + " unique users in the log."; //string to be printed
	    	
		}//Close Users method
		
}