import java.io.File;
import java.io.FileNotFoundException; 
import java.util.*;

public class Day7 {
    static int min;
    static int max;
    static int[] pos;
	public static void main(String[] args) {
	   	try {
    		File myObj = new File("input7.txt");
    		Scanner myReader = new Scanner(myObj);            
    		while (myReader.hasNextLine()) {
                String[] line = myReader.nextLine().split(",");
                pos = new int[line.length];
                min = max = Integer.parseInt(line[0]);
                for(int i = 0; i < line.length; i++){
                    int val = Integer.parseInt(line[i]);
                    min = val < min ? val : min;
                    max = val > max ? val : max;
                    pos[i] = val;
                }               
      		}
      		myReader.close();
    	} catch (FileNotFoundException e) {
    		System.out.println("An error occurred.");
    		e.printStackTrace();
    	}        
        System.out.println("part 1: " + calc(false));
        System.out.println("part 2: " + calc(true));
    }

    private static int calc(boolean part2){     //Binary search to find value between 2 higher values
        int mn = min;
        int mx = max;
        int sum = 0;
        while(mn < mx){
            sum = 0;
            int current = (mn+mx)/2;  
            int sumPrev = 0;          
            int sumNext = 0;
            for(int p: pos){
                sum += part2 ? Math.abs(p-current)*(Math.abs(p-current)+1)/2 : Math.abs(p-current);
                sumPrev += part2 ? Math.abs(p-(current-1))*(Math.abs(p-(current-1))+1)/2 : Math.abs(p-(current-1));
                sumNext += part2 ? Math.abs(p-(current+1))*(Math.abs(p-(current+1))+1)/2 : Math.abs(p-(current+1));
            }
            mn = sum < sumPrev ? current : mn;
            mx = sum < sumNext ? current : mx;
        }
        return sum;
    }
}
