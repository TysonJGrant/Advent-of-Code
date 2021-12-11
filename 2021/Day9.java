import java.io.File; 
import java.io.FileNotFoundException;  
import java.util.*;

public class Day9 {
    static HashMap<String, Integer> cave = new HashMap<>();
    static HashSet<String> basin = new HashSet<>();
    static HashSet<String> temp = new HashSet<>();
    static HashSet<String> bug = new HashSet<>();
    static int xlen;
    static int ylen;
    static int p1;
    static ArrayList<Integer> p2 = new ArrayList<>();;
	public static void main(String[] args) {
	   	try {
    		File myObj = new File("input9.txt");
    		Scanner myReader = new Scanner(myObj);
    		while (myReader.hasNextLine()) {
    			String line = myReader.nextLine();
                xlen = line.length();
                for(int i = 0; i < xlen; i++)
                    cave.put(i+"-"+ylen, Integer.parseInt(Character.toString(line.charAt(i))));
                ylen++;
      		}
      		myReader.close();
    	} catch (FileNotFoundException e) {
    		System.out.println("An error occurred.");
    		e.printStackTrace();
        }
        for(int i = 0; i < xlen; i++){
            for(int j = 0; j < ylen; j++){
                int up = cave.containsKey(i + "-" + (j+1)) ? cave.get(i + "-" + (j+1)) : 9;
                int down = cave.containsKey(i + "-" + (j-1)) ? cave.get(i + "-" + (j-1)) : 9;
                int left = cave.containsKey((i-1) + "-" + j) ? cave.get((i-1) + "-" + j) : 9;
                int right = cave.containsKey((i+1) + "-" + j) ? cave.get((i+1) + "-" + j) : 9;
                int c = cave.get(i+"-"+j);
                if(c < up && c < down && c < left && c < right){                
                    p1 += cave.get(i+"-"+j)+1;
                    findBasin(i, j);
                }
            }
        }
        System.out.println("Part 1: " + p1);
        System.out.println("Part 2: " + p2());
  	}

    private static void findBasin(int x, int y){
        int basinSize = basin.size();
        basin.add(x+"-"+y);
        int basinSizeAfter = basin.size();
        while(basinSize != basinSizeAfter){
            basinSize = basinSizeAfter;
            for(String val: basin){
                String[] pos = val.split("-");
                int i = Integer.parseInt(pos[0]);
                int j = Integer.parseInt(pos[1]);
                isValid(i+1, j);
                isValid(i-1, j);
                isValid(i, j+1);
                isValid(i, j-1);
            }
            basin.addAll(temp);
            bug.addAll(basin);
            temp = new HashSet<>();
            basinSizeAfter = basin.size();
        }
        p2.add(basin.size());
        basin = new HashSet<>();
    }

    private static boolean isValid(int x, int y){
        if(!cave.containsKey(x+"-"+y))
            return false;
        if(cave.get(x+"-"+y) < 9){
            temp.add(x+"-"+y);
            return true;
        }
        return false;
    }

    private static int p2(){
        Collections.sort(p2, Collections.reverseOrder());
        return p2.get(0)*p2.get(1)*p2.get(2);
    }
}
