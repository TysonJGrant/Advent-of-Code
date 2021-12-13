import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.*;

public class Day13 {
    static HashSet<String> dots = new HashSet<>();
    static ArrayList<String[]> folds = new ArrayList<>();
    static int p1 = 0;

	public static void main(String[] args) {
	   	try {
    		File myObj = new File("input13.txt");
    		Scanner myReader = new Scanner(myObj);
    		while (myReader.hasNextLine()) {
    			String line = myReader.nextLine();
                if(!line.equals("")){
                    if(line.charAt(0) == 'f'){
                        String[] temp = line.split("[\\s=]+");
                        folds.add(new String[]{temp[2], temp[3]});
                    }
                    else
                        dots.add(line);
                }
            }
      		myReader.close();
    	} catch (FileNotFoundException e) {
    		System.out.println("An error occurred.");
    		e.printStackTrace();
        }
        for(String[] fold: folds)
            fold(fold[0], Integer.parseInt(fold[1]));

        System.out.println("Part 1: " + p1);
        System.out.println("Part 2:");
        for(int y = 0; y < 6; y++){
            for(int x = 0; x < 40; x++){
                System.out.print(dots.contains(x + "," + y) ? '#' : ' ');            
            }
            System.out.println();
        }
  	}

    private static void fold(String axis, int ind){
        HashSet<String> add = new HashSet<>();
        HashSet<String> remove = new HashSet<>();
        for(String pos: dots){
            String[] coords = pos.split(",");
            int x = Integer.parseInt(coords[0]);
            int y = Integer.parseInt(coords[1]);
            if(axis.equals("x") && x > ind){
                add.add(ind-(x-ind) + "," + y);
                remove.add(pos);
            }
            if(axis.equals("y") && y > ind){
                add.add(x + "," + (ind-(y-ind)));
                remove.add(pos);
            }
        }
        dots.addAll(add);
        dots.removeAll(remove);
        if(p1 == 0)
            p1 = dots.size();
    }
}
