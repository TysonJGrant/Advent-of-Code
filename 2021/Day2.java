import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.*;

public class Day2 {
	public static void main(String[] args) {
		int xpos = 0;
		int ypos = 0;
		int xpos2 = 0;
		int ypos2 = 0;
		int aim = 0;
	   	try {
    		File myObj = new File("input2.txt");
    		Scanner myReader = new Scanner(myObj);
    		while (myReader.hasNextLine()) {
    			String line = myReader.nextLine();
    			String[] current = line.split(" ");
    			if(current[0].equals("forward")){
					xpos += Integer.parseInt(current[1]);
					ypos2 += Integer.parseInt(current[1]) * aim;
					xpos2 += Integer.parseInt(current[1]);
				}
	    		else{
					ypos += current[0].equals("down") ? Integer.parseInt(current[1]) : -Integer.parseInt(current[1]);
					aim += current[0].equals("down") ? Integer.parseInt(current[1]) : -Integer.parseInt(current[1]);
				}
      		}
      		myReader.close();
    	} catch (FileNotFoundException e) {
    		System.out.println("An error occurred.");
    		e.printStackTrace();
    	}
    	System.out.println("Part 1: " + xpos*ypos);
    	System.out.println("Part 2: " + xpos2*ypos2);
  	}
}
