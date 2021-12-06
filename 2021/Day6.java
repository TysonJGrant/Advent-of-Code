import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.*;

public class Day6 {
    static Long[] fish = new Long[]{0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L};
	public static void main(String[] args) {
	   	try {
    		File myObj = new File("input6.txt");
    		Scanner myReader = new Scanner(myObj);
    		while (myReader.hasNextLine()) {
                String[] line = myReader.nextLine().split(",");;
                for(int i = 0; i < line.length; i++)
                    fish[Integer.parseInt(line[i])]++;
      		}
      		myReader.close();
    	} catch (FileNotFoundException e) {
    		System.out.println("An error occurred.");
    		e.printStackTrace();
    	}
        int p1 = 0;
        for(int i = 0; i < 256; i++){
            Long temp = fish[i%7];
            fish[i%7] += fish[7+i%2]; 
            fish[7+i%2] = temp;  
            if(i == 79){
                for(int j = 0; j < fish.length; j++)
                    p1 += fish[j];
            }        
        }
        Long p2 = 0L;
        for(int i = 0; i < fish.length; i++)
            p2 += fish[i];
        System.out.println("part 1: " + p1);
        System.out.println("part 2: " + p2);
    }
}
