import java.io.File;  
import java.io.FileNotFoundException;  
import java.util.*;

public class Day18 {
    static int p2 = -1;
    static int inc;
    static int count = 0;
    static String snailfish;
    static ArrayList<String> list = new ArrayList<>();
    public static void main(String[] args) {
	   	try {
    		File myObj = new File("input18.txt");
    		Scanner myReader = new Scanner(myObj);
            list.add(myReader.nextLine());
            snailfish = list.get(0);
    		while (myReader.hasNextLine()) {
                list.add(myReader.nextLine());
                snailfish = "[" + snailfish + "," + list.get(list.size()-1) + "]";
                while(true){
                    if(!explode()){
                        if(!split())
                            break;
                    }
                }
            }
      		myReader.close();
    	} catch (FileNotFoundException e) {
    		System.out.println("An error occurred.");
    		e.printStackTrace();
    	}  
        System.out.println("Part 1: " + magnitude());
        for(int i = 0; i < list.size(); i++){
            for(int j = 0; j < list.size(); j++){
                count = 0;
                snailfish = "[" + list.get(i) + "," + list.get(j) + "]";
                while(true){
                    if(!explode()){
                        if(!split())
                            break;
                    }
                }
                int val = magnitude();
                if(val > p2 || p2 == -1)
                    p2 = val;
            }
        }
		System.out.println("Part 2: " + p2);
  	}

    private static boolean split(){
        String num = "";
        for(int i = 0; i < snailfish.length(); i++){
            char val = snailfish.charAt(i);
            if(val == '[' || val == ']' || val == ','){
                if(num.length() > 1){
                    int temp = Integer.parseInt(num);
                    snailfish = snailfish.substring(0, i-num.length()) + "[" + temp/2 + "," + ((int) Math.ceil(temp/2.0)) + "]" + snailfish.substring(i);
                    return true;
                }
                else
                    num = "";
            }
            else
                num += val;
        }
        return false;
    }

    private static boolean explode(){
        int nests = 0;
        for(int i = 0; i < snailfish.length(); i++){
            if(snailfish.charAt(i) == '[')
                nests++;
            else if(snailfish.charAt(i) == ']')
                nests--;
            if(nests == 5){
                incrementLR(i);
                return true;
            }
        }
        return false;
    }

    static private void incrementLR(int i){
        String currentLeft = "";
        String currentRight = ""; 
        String leftNum = "";
        String rightNum = "";
        inc = i+1;
        currentLeft = assignPair(currentLeft, snailfish.charAt(inc));
        inc++;
        currentRight = assignPair(currentRight, snailfish.charAt(inc));
        int last;
        for(last = inc; last < snailfish.length(); last++){
            char val = snailfish.charAt(last);
            if(val != '[' && val != ']' && val != ','){
                rightNum = "";
                while(val != '[' && val != ']' && val != ','){
                    rightNum += val;
                    last++;
                    val = snailfish.charAt(last);
                }
                snailfish = snailfish.substring(0, last-rightNum.length()) + (Integer.parseInt(rightNum) + Integer.parseInt(currentRight)) + snailfish.substring(last);
                break;
            }
        }
        snailfish = snailfish.substring(0, i) + '0' + snailfish.substring(inc+1);
        for(int j = i-1; j > 0; j--){
            char val = snailfish.charAt(j);
            if(val != '[' && val != ']' && val != ','){
                leftNum = "";
                while(val != '[' && val != ']' && val != ','){
                    leftNum = val + leftNum;
                    j--;
                    val = snailfish.charAt(j);
                }
                snailfish = snailfish.substring(0, j+1) + (Integer.parseInt(leftNum) + Integer.parseInt(currentLeft)) + snailfish.substring(j+leftNum.length()+1);
                break;
            }
        }
    }

    static private String assignPair(String current, char val){
        while(val != '[' && val != ']' && val != ','){
            current += val;
            inc++;
            val = snailfish.charAt(inc);
        }
        return current;
    }

    static private int magnitude(){
        char val = snailfish.charAt(count);
        count++;
        if(val == '[')
            return 3*magnitude() + 2*magnitude();
        else if(val == ',' || val == ']')
            return magnitude();
        else
            return Integer.parseInt(Character.toString(val));
    }        
}