import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.*;

public class Day11 {
    static HashMap<String, Integer> octopi = new HashMap<>();
    static HashSet<String> flashed;
    static int p1;
    static int p2 = 0;

	public static void main(String[] args) {
	   	try {
    		File myObj = new File("input11.txt");
    		Scanner myReader = new Scanner(myObj);
            int ylen = 0;
    		while (myReader.hasNextLine()) {
    			String line = myReader.nextLine();
                int xlen = line.length();
                for(int i = 0; i < xlen; i++)
                    octopi.put(i+"-"+ylen, Integer.parseInt(Character.toString(line.charAt(i))));
                ylen++;
      		}
      		myReader.close();
    	} catch (FileNotFoundException e) {
    		System.out.println("An error occurred.");
    		e.printStackTrace();
    	}
        while(true){
            flashed = new HashSet<>();
            for(String pos: octopi.keySet()){
                octopi.put(pos, octopi.get(pos)+1);
            }
            boolean allFlashed = false;
            while(!allFlashed){
                allFlashed = true;
                for(String pos: octopi.keySet()){
                    if(octopi.get(pos) > 9 && !flashed.contains(pos)){
                        allFlashed = false;
                        flashed.add(pos);
                        flash(pos);
                    }                    
                }                
            }
            for(String val: flashed)
                octopi.put(val, 0);
            if(p2 < 100)
               p1 += flashed.size();
            p2++;
            if(flashed.size() == 100)
                break;
        }  
        System.err.println("Part 1: " + p1); 
        System.err.println("Part 1: " + p2);      
    }

    private static void flash(String pos){
        int x = Integer.parseInt(String.valueOf(pos.charAt(0)));
        int y = Integer.parseInt(String.valueOf(pos.charAt(2)));
        for(int i = x-1; i < x+2;  i++){
            for(int j = y-1; j < y+2;  j++){
                if(octopi.containsKey(i+"-"+j))
                    octopi.put(i+"-"+j, octopi.get(i+"-"+j)+1);
            }
        }
    }
}
