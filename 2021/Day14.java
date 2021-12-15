import java.io.File; 
import java.io.FileNotFoundException;  
import java.util.*;

public class Day14 {
	static String polymer;
    static HashMap<String, String> rules = new HashMap<>();
	static HashMap<String, Long> counts = new HashMap<>();
	public static void main(String[] args) {
	   	try {
    		File myObj = new File("input14.txt");
    		Scanner myReader = new Scanner(myObj);
			polymer = myReader.nextLine();
			myReader.nextLine();
    		while (myReader.hasNextLine()) {
    			String[] line = myReader.nextLine().split("[\\s->\\s]+");
				rules.put(line[0], line[1]);  
      		}
      		myReader.close();
    	} catch (FileNotFoundException e) {
    		System.out.println("An error occurred.");
    		e.printStackTrace();
    	}    

		for(int j = polymer.length()-2; j >= 0; j--)
			counts.put(polymer.substring(j, j+2), counts.containsKey(polymer.substring(j, j+2)) ? counts.get(polymer.substring(j, j+2))+1 : 1);
		for(int i = 0; i < 40; i++){
			HashMap<String, Long> temp = new HashMap<>();
			for(String key: counts.keySet()){				
				Long num = counts.get(key);
				String val = rules.get(key);	//Map character to center of existing characters and increment for the 2 new values (ab & bc)
				temp.put(key.charAt(0) + val, temp.containsKey(key.charAt(0) + val) ? temp.get(key.charAt(0) + val) + num : num);				
				temp.put(val + key.charAt(1), temp.containsKey(val + key.charAt(1)) ? temp.get(val + key.charAt(1)) + num : num);	
			}
			counts = temp;
			if(i == 9)
				System.out.println("Part 1: " + getAns());			
		}
		System.out.println("Part 2: " + getAns());
  	}

	private static long getAns(){
		long min = -1L;
		long max = 0L;
		HashMap<Character, Long> chars = new HashMap<>();
		chars.put(polymer.charAt(0), 1L);
		chars.put(polymer.charAt(polymer.length()-1), 1L);
		for(String val: counts.keySet()){
			char ch = val.charAt(0);
			long num = counts.get(val);
			chars.put(ch, chars.containsKey(ch) ? chars.get(ch) + num : num);
		}
		for(long num: chars.values()){
			min = num < min || min == -1 ? num : min;
			max = num > max ? num : max;
		}
		return max-min;
	}
}