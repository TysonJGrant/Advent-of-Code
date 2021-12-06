import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.*;

public class Day5 {
    static HashMap<String, Integer> coords = new HashMap<>();
    static HashMap<String, Integer> coords2 = new HashMap<>();

	public static void main(String[] args) {
	   	try {
    		File myObj = new File("input5.txt");
    		Scanner myReader = new Scanner(myObj);
    		while (myReader.hasNextLine()) {
                String[] line = myReader.nextLine().split("[,\\s->]+"); //any combination of comma, space, hyphen and right arrow repeated
    			applyLines(line);
      		}
      		myReader.close();
    	} catch (FileNotFoundException e) {
    		System.out.println("An error occurred.");
    		e.printStackTrace();
    	}
        int p1 = countOverlaps(coords);
        int p2 = countOverlaps(coords2);
        System.out.println("part 1: " + p1);
        System.out.println("part 2: " + p2);
    }

    private static int countOverlaps(HashMap<String, Integer> vals){
        int count = 0;
        for (Integer value : vals.values()) {
            count += value > 1 ? 1 : 0;
        }
        return count;
    }

    private static void applyLines(String[] line){
        int startx = Integer.parseInt(line[0]);
        int xdir = Integer.parseInt(line[0]) < Integer.parseInt(line[2]) ? 1 : line[0].equals(line[2]) ? 0 : -1;
        int starty = Integer.parseInt(line[1]);
        int ydir = Integer.parseInt(line[1]) < Integer.parseInt(line[3]) ? 1 : line[1].equals(line[3]) ? 0 : -1;
        for(int i = 0; i < Math.max(Math.abs(Integer.parseInt(line[2])-startx), Math.abs(Integer.parseInt(line[3])-starty))+1; i++){
            String key = (startx+(i*xdir) + "," + (starty+(i*ydir)));
            int count = coords2.containsKey(key) ? coords2.get(key) : 0;
            coords2.put(key, count + 1);
            if(line[0].equals(line[2]) || line[1].equals(line[3])){
                count = coords.containsKey(key) ? coords.get(key) : 0;
                coords.put(key, count + 1);
            }
        }
    }
}
