import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.*;

public class Day16 {
    static String value = "";
    static int count = 0;
    static int p1 = 0;
    static long p2 = 0;
    public static void main(String[] args) {
	   	try {
    		File myObj = new File("input16.txt");
    		Scanner myReader = new Scanner(myObj);
        String temp = myReader.nextLine();
        for(int i = temp.length()-1; i >= 0; i--){
          value = Long.toString(Long.parseLong((Character.toString(temp.charAt(i))), 16), 2) + value;
          while(value.length() % 4 != 0)
            value = "0" + value;
        }
      	myReader.close();
    	} catch (FileNotFoundException e) {
    		System.out.println("An error occurred.");
    		e.printStackTrace();
    	}
      p2 = parse();
      System.out.println("Part 1: " + p1);
      System.out.println("Part 2: " + p2);
    }

    private static long parse(){
      long version = Long.parseLong(value.substring(count, count+3), 2);
      long type = Long.parseLong(value.substring(count+3, count+6), 2);
      p1 += version;
      count += 6;
      if(type == 4)    //Literal
        return literal();
      else{            //Operator
        ArrayList<Long> packets = new ArrayList<>();
        if(value.charAt(count) == '0'){   //Length type 0 - next 15 bits
          long lengthOfPack = Long.parseLong(value.substring(count+1, count+16), 2);
          count += 16;
          long mark = count + lengthOfPack;
          while(count < mark)
            packets.add(parse());
        }
        else if(value.charAt(count) == '1'){  //Length type 1 - next 11 bits
          long numOfPacks = Long.parseLong(value.substring(count+1, count+12), 2);
          count += 12;
          int parsed = 0;
          while(parsed < numOfPacks){
            parsed++;
            packets.add(parse());
          }
        }
        if(type == 0)
          return sum(packets);
        else if(type == 1)
          return product(packets);
        else if(type == 2)
          return minimum(packets);
        else if(type == 3)
          return maximum(packets);
        else if(type == 5)
          return greater(packets);
        else if(type == 6)
          return less(packets);
        else
          return equal(packets);
      }
    }

    public static long sum(ArrayList<Long> packets){
      long sum = 0;
      for(long val: packets)
        sum += val;
      return sum;
    }

    public static long product(ArrayList<Long> packets){
      long prod = 1;
      for(long val: packets)
        prod *= val;
      return prod;
    }

    public static long minimum(ArrayList<Long> packets){
      long min = -1L;
      for(long val: packets)
        min = val < min || min == -1 ? val : min;
      return min;
    }

    public static long maximum(ArrayList<Long> packets){
      long max = 0L;
      for(long val: packets)
        max = val > max ? val : max;
      return max;
    }

    public static long greater(ArrayList<Long> packets){
      return packets.get(0) > packets.get(1) ? 1L : 0L;
    }

    public static long less(ArrayList<Long> packets){
      return packets.get(0) < packets.get(1) ? 1L : 0L;
    }

    public static long equal(ArrayList<Long> packets){
      return (packets.get(0).equals(packets.get(1))) ? 1L : 0L;
    }

    public static long literal(){
      String current = "";
      while(value.charAt(count) == '1'){  //Need 5 full bits for each val
        current += value.substring(count+1, count+5);
        count+=5;
      }
      current += value.substring(count+1, count+5);
      count+=5;
      return Long.parseLong(current, 2);
    }
}