import java.io.File; 
import java.io.FileNotFoundException;  
import java.util.*;

public class Day12 {
    static HashMap<String, HashSet<String>> caves = new HashMap<>();
    static int p1 = 0;
    static int p2 = 0;

	public static void main(String[] args) {
	   	try {
    		File myObj = new File("input12.txt");
    		Scanner myReader = new Scanner(myObj);
    		while (myReader.hasNextLine()) {
    			String[] line = myReader.nextLine().split("-");
                populateCaves(line[0], line[1]);
                populateCaves(line[1], line[0]);
      		}
      		myReader.close();
    	} catch (FileNotFoundException e) {
    		System.out.println("An error occurred.");
    		e.printStackTrace();
    	}
        findPaths("start", new HashSet<>());
        System.out.println("Part 1: " + p1);
        System.out.println("Part 2: " + p2);
  	}

    private static void populateCaves(String s1, String s2){
        if(!s2.equals("start") && !s1.equals("end")){
            if(caves.containsKey(s1))
                caves.get(s1).add(s2);     
            else{
                HashSet<String> temp = new HashSet<>();
                temp.add(s2);
                caves.put(s1, temp);
            }
        }
    }
    
    private static void findPaths(String current, HashSet<String> visited){        
        for(String next: caves.get(current)){
            if(!(Character.isLowerCase(next.charAt(0)) && visited.contains(next) && visited.contains("2"))){
                if(next.equals("end")){
                    p1 += visited.contains("2") ? 0 : 1;
                    p2 ++;
                }
                else{
                    HashSet<String> temp = new HashSet<>();
                    for(String val: visited)
                        temp.add(val);
                    temp.add(visited.contains(next) && Character.isLowerCase(next.charAt(0)) ? "2" : next);
                    findPaths(next, temp);
                }
            }
        }
    }
}
