import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.*;

public class Day1 {
	public static void main(String[] args) {
		int p1 = 0;
		int p2 = 0;
		ArrayList<Integer> data = new ArrayList<>();
    	
		try {
    		File myObj = new File("input1.txt");
    		Scanner myReader = new Scanner(myObj);
    		while (myReader.hasNextLine()) {
				data.add(Integer.parseInt(myReader.nextLine()));
      		}
      		myReader.close();
    	} catch (FileNotFoundException e) {
    		System.out.println("An error occurred.");
    		e.printStackTrace();
    	}
		for(int i=1; i < data.size(); i++){
			if(data.get(i) > data.get(i-1))
				p1++;
		}
    	for(int i=3; i < data.size(); i++){
			if(data.get(i) > data.get(i-3))
				p2++;
  		}
		System.out.println("Part 1: " + p1);
		System.out.println("Part 2: " + p2);
  	}
}