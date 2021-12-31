import java.io.File; 
import java.io.FileNotFoundException;  
import java.util.*;

public class Day20 {
    static String algorithm;
    static HashSet<String> trench = new HashSet<>();
    static int xlen = 0;
    static int ylen = 0; 
    public static void main(String[] args) {
	   	try {
    		File myObj = new File("input20.txt");
    		Scanner myReader = new Scanner(myObj);
            algorithm = myReader.nextLine();
            myReader.nextLine();
    		while (myReader.hasNextLine()) {                
                String line = myReader.nextLine();
                xlen = line.length();
                for(int i = 0; i < xlen; i++){
                    if(line.charAt(i) == '#')
                        trench.add(i+","+ylen);
                }
                ylen++;
            }
      		myReader.close();
    	} catch (FileNotFoundException e) {
    		System.out.println("An error occurred.");
    		e.printStackTrace();
    	}
        for(int i = 0; i < 50; i++){
            HashSet<String> temp = new HashSet<>();
            for(int j = -51; j < ylen+51; j++){
                for(int k = -51; k < xlen+51; k++){
                    String bin = "";
                    for(int y = j-1; y < j+2; y++){
                        for(int x = k-1; x < k+2; x++)
                            bin += trench.contains(x+","+y) ? '1' : '0';
                    }
                    int pos = Integer.parseInt(bin, 2);
                    if(algorithm.charAt(pos) == '#')
                        temp.add(k+","+j);
                }
            }
            trench = temp;
            for(int k = -51; k < ylen+51; k++){
                for(int j = -51; j < xlen+51; j++){
                    if((j == -51 || k == -51 || k == ylen+50 || j == xlen+50) && i%2 == 1)
                        trench.remove(j+","+k);
                }
            }
            if(i == 1)
                System.out.println("Part 1: " + trench.size());
        }		
		System.out.println("Part 2: " + trench.size());
    }
}