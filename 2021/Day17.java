import java.io.File; 
import java.io.FileNotFoundException;  
import java.util.*;

public class Day17 {
    public static void main(String[] args) {
	   	try {
    		File myObj = new File("input17.txt");
    		Scanner myReader = new Scanner(myObj);
    		String[] line = myReader.nextLine().split("[\\s.,=]+");
            int xmin = Integer.parseInt(line[3]);
            int xmax = Integer.parseInt(line[4]);
            int ymin = Integer.parseInt(line[6]);
            int ymax = Integer.parseInt(line[7]);
            System.out.println("Part 1 " + ymin*(ymin+1)/2);
            int p2 = 0;
            for(int i = 1; i <= xmax; i++){
                for(int j = ymin; j < ymin*-1+1; j++){
                    int x = 0;
                    int y = 0;
                    int xvel = i;
                    int yvel = j;
                    while(x <= xmax && y >= ymin){
                        if(x <= xmax && x >= xmin && y <= ymax && y >= ymin){
                            p2++;
                            break;
                        }
                        x += xvel;
                        y += yvel;
                        xvel -= (xvel == 0) ? 0 : 1;
                        yvel--;
                    }
                }
            }
            System.out.println("Part 2 " + p2);
          	myReader.close();
    	} catch (FileNotFoundException e) {
    		System.out.println("An error occurred.");
    		e.printStackTrace();
    	}        
    }
}