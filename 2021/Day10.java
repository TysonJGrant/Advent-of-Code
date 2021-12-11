import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.*;

public class Day10 {
    static String open = "([{<";
    static String close = ")]}>";
    static int[] worth = {3, 57, 1197, 25137};
    static Stack<Character> stack = new Stack<>();
    static int p1 = 0;
    static ArrayList<Long> p2 = new ArrayList<>();

	public static void main(String[] args) {
	   	try {
    		File myObj = new File("input10.txt");
    		Scanner myReader = new Scanner(myObj);
    		while (myReader.hasNextLine()) {
                stack = new Stack<>();
    			String line = myReader.nextLine();
                for(int i = 0; i < line.length(); i++){
                    char val = line.charAt(i);
                    if(open.contains(Character.toString(val)))
                        stack.push(val);
                    else{
                        if(open.indexOf(stack.pop()) != close.indexOf(val)){
                            p1 += worth[close.indexOf(val)];
                            stack = new Stack<>();
                            break;
                        }
                    }
                }
                if(stack.size() > 0){
                    long count = 0;
                    while(stack.size() > 0){
                        count *= 5;
                        count += open.indexOf(stack.pop())+1;
                    }
                    p2.add(count);
                }
      		}
      		myReader.close();
    	} catch (FileNotFoundException e) {
    		System.out.println("An error occurred.");
    		e.printStackTrace();
    	}
        Collections.sort(p2);
        System.out.println("Part 1: " + p1);
        System.out.println("Part 2: " + p2.get(p2.size()/2));
  	}
}
