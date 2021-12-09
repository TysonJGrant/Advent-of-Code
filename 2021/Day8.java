import java.io.File; 
import java.io.FileNotFoundException;  
import java.util.*;

public class Day8 {
    static ArrayList<String[]> signals = new ArrayList<>();
    static ArrayList<String[]> output = new ArrayList<>();
    static String[] nums;
    static int p1 = 0;
    static int p2 = 0;
    
	public static void main(String[] args) {
	   	try {
    		File myObj = new File("input8.txt");
    		Scanner myReader = new Scanner(myObj);
    		while (myReader.hasNextLine()) {
    			String[] line = myReader.nextLine().split(" \\| ");
    			String[] current = line[1].split(" ");
                signals.add(line[0].split(" "));
                output.add(current);
                for(String val: current)
                    p1 = val.length() == 2 || val.length() == 3 || val.length() == 4 || val.length() == 7 ? p1+1 : p1;
      		}
      		myReader.close();
    	} catch (FileNotFoundException e) {
    		System.out.println("An error occurred.");
    		e.printStackTrace();
    	}
        for(int i = 0; i < signals.size(); i++)
            p2 += resolveSegments(signals.get(i), output.get(i));
        System.out.println("Part 1: " + p1);
        System.out.println("Part 2: " + p2);
  	}

    private static int resolveSegments(String[] vals, String[] out){
        nums = new String[10];
        Arrays.sort(vals, Comparator.comparingInt(String::length));
        nums[1] = vals[0]; //assign 1
        nums[4] = vals[2]; //assign 4
        nums[7] = vals[1]; //assign 7
        nums[8] = vals[9]; //assign 8
        nums[9] = diffOf1(new String[]{vals[6], vals[7], vals[8]}, nums[4]+nums[7]);     //finds 6 using 7 and 4 combined
        nums[2] = diffOf1(new String[]{vals[3], vals[4], vals[5]}, nums[9]);             //finds 2 using 9 etc
        nums[3] = diffOf1(new String[]{vals[3], vals[4], vals[5]}, nums[2]); 
        nums[5] = diffOf1(new String[]{vals[3], vals[4], vals[5]}, nums[3]); 
        nums[6] = diffOf1(new String[]{vals[6], vals[7], vals[8]}, nums[5]); 
        nums[0] = diffOf1(new String[]{vals[6], vals[7], vals[8]}, nums[9]); 
        return translate(out);
    }

    private static int translate(String[] out){  //Converts the output once numbers are solved
        String numbers = "";
        for(int i = 0; i < out.length; i++){
            for(int j = 0; j < nums.length; j++){
                if(nums[j].length() == out[i].length()){
                    boolean found = true;
                    for(int k = 0; k < out[i].length(); k++){
                        if(!nums[j].contains(Character.toString(out[i].charAt(k))))
                            found = false;                    
                    }
                    if(found)
                        numbers += Integer.toString(j);
                }
            }
        }
        return Integer.parseInt(numbers);
    }

    private static String diffOf1(String[] arr, String str){  //Finds string in arr with 1 char difference to str
        for(String cur: arr){                                 //of numbers that have not be found yet            
            if(!Arrays.asList(nums).contains(cur)){
                int count = 0;
                for(int i = 0; i < cur.length(); i++){
                    if(!str.contains(Character.toString(cur.charAt(i))))
                        count++;
                }
                if(count == 1)
                    return cur;
            }
        }
        return "z";
    }
}
