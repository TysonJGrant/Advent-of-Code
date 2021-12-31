import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.*;

public class Day25 {
    static HashSet<String> east = new HashSet<>();
    static HashSet<String> south = new HashSet<>();
    static int p1 = 0;
    static int xlen = 0;
    static int ylen = 0;
    static boolean finished = false;
    public static void main(String[] args) {
        try {
            File myObj = new File("input25.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                xlen = line.length();
                for(int i = 0; i < xlen; i++){               
                    if(line.charAt(i) == '>')
                        east.add(i+","+ylen);
                    if(line.charAt(i) == 'v')
                        south.add(i+","+ylen);
                }
                ylen++;               
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } 
        while(!finished){
            finished = true;
            east = move(east, 1, 0);
            south = move(south, 0, 1);
            p1++;
        }
        System.out.println("Part 1: " + p1);
    }

    private static HashSet<String> move(HashSet<String> direction, int x, int y){
        HashSet<String> temp = new HashSet<>();
        for(String val: direction){
            String[] coords = val.split(",");
            if(east.contains((Integer.parseInt(coords[0])+x)%xlen + "," + (Integer.parseInt(coords[1])+y)%ylen) || south.contains((Integer.parseInt(coords[0])+x)%xlen + "," + (Integer.parseInt(coords[1])+y)%ylen))  
                temp.add(val);
            else{
                temp.add((Integer.parseInt(coords[0])+x)%xlen + "," + (Integer.parseInt(coords[1])+y)%ylen);
                finished = false;
            }
        }
        return temp;
    }
}