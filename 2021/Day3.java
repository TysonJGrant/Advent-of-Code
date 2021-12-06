import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.*;

public class Day3 {
    static int[] gamma;
    static int MAX;
    static ArrayList<String> oxy = new ArrayList<>();
    static ArrayList<String> co2 = new ArrayList<>();

	public static void main(String[] args) {
        try {
    		File myObj = new File("input3.txt");
    		Scanner myReader = new Scanner(myObj);
            initialise(myReader.nextLine());
    		while (myReader.hasNextLine())
    			compute(myReader.nextLine());
      		myReader.close();
    	} catch (FileNotFoundException e) {
    		System.out.println("An error occurred.");
    		e.printStackTrace();
    	}
        String gammaFinal = "";
        for(int i = 0; i < gamma.length; i++)
            gammaFinal += gamma[i] > 0 ? 1 : 0;   
        findRating(oxy, '1');
        findRating(co2, '0');
        System.out.println("Part 1: " + ((MAX - Integer.parseInt(gammaFinal,2)) * Integer.parseInt(gammaFinal,2)));
        System.out.println("Part 2: " + Integer.parseInt(oxy.get(0), 2) * Integer.parseInt(co2.get(0), 2));
        // System.out.println("Part 2: " + trees1*trees2*trees3*trees4*trees5);
  	}

    private static void initialise(String line){
        gamma = new int[line.length()];
        MAX = (int) Math.pow((double) 2, (double) line.length()) - 1;
        compute(line);
    }

    private static void compute(String line){
        oxy.add(line);
        co2.add(line);
        for(int i = 0; i < line.length(); i++)
            gamma[i] += line.charAt(i) == '1' ? 1 : -1;        
    }

    private static void findRating(ArrayList<String> vals, char match){
        for(int i = 0; i < vals.get(0).length(); i++){
            int count = 0;
            for(int j = 0; j < vals.size(); j++)
                count += vals.get(j).charAt(i) == '1' ? 2 : 0;
            boolean less = count < vals.size();
            for(int j = vals.size()-1; j >= 0; j--){
                char temp = vals.get(j).charAt(i);
                if(less){
                    if(temp == match)
                        vals.remove(j);
                }
                else{
                    if(temp != match)
                        vals.remove(j);
                }
            }
            if(vals.size() == 1)
                break;
        }
    }
}
