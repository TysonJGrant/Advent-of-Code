import java.io.File;  
import java.io.FileNotFoundException;  
import java.util.*;

public class Day19 {
    static ArrayList<ArrayList<Integer[]>> scanners = new ArrayList<>();
    static ArrayList<ArrayList<Integer[]>> oriented = new ArrayList<>();
    static HashMap<Integer, Integer[]> positions = new HashMap<>();
    static HashSet<String> p1 = new HashSet<>();
    static int p2 = 0;
	public static void main(String[] args) {
	   	try {
    		File myObj = new File("input19.txt");
    		Scanner myReader = new Scanner(myObj);
            ArrayList<Integer[]> temp = new ArrayList<>();
    		while (myReader.hasNextLine()) {
    			String line = myReader.nextLine();
                if(line.contains("scanner"))
                    temp = new ArrayList<>();
                else if(line.length() == 0)
                    scanners.add(temp);
                else{
                    String[] pos = line.split(",");
                    temp.add(new Integer[]{Integer.parseInt(pos[0]), Integer.parseInt(pos[1]), Integer.parseInt(pos[2])});
                }
            }
            scanners.add(temp);
      		myReader.close();
    	} catch (FileNotFoundException e) {
    		System.out.println("An error occurred.");
    		e.printStackTrace();
    	}
        oriented.add(scanners.get(0));
        positions.put(0, new Integer[]{0, 0, 0});
        scanners.remove(0);
        while(scanners.size() > 0){
            for(int o = oriented.size()-1; o >=0; o--){              
                for(int x = -1; x <= 1; x+=2){
                    for(int y = -1; y <= 1; y+=2){
                        for(int z = -1; z <= 1; z+=2){
                            for(int rotation = 0; rotation < 3; rotation++){    
                                for(int s = scanners.size()-1; s >=0; s--)
                                    match(o, scanners.get(s), x, y, z, rotation, false);
                            }
                        }
                    }                    
                }
            }
        }
        for(ArrayList<Integer[]> arr: oriented){
            for(Integer[] in: arr)
                p1.add(in[0]+","+in[1]+","+in[2]);
        }
        System.out.println("Part 1: " + p1.size());
        for(Integer[] a: positions.values()){
            for(Integer[] b: positions.values())
                p2 = (Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]) + Math.abs(a[2] - b[2]) > p2) ? Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]) + Math.abs(a[2] - b[2]) : p2;    
        }
        System.out.println("Part 2: " + p2);
  	}

    private static void match(int o, ArrayList<Integer[]> sc, int x, int y, int z, int rotation, boolean reverse){
        ArrayList<Integer[]> or = oriented.get(o);  
        ArrayList<Integer[]> test = new ArrayList<>();
        for(Integer[] arr: sc){
            if(reverse)
                test.add(new Integer[]{arr[(2+rotation)%3]*x, arr[(1+rotation)%3]*y, arr[(0+rotation)%3]*z});
            else
                test.add(new Integer[]{arr[(0+rotation)%3]*x, arr[(1+rotation)%3]*y, arr[(2+rotation)%3]*z});
        }
        for(Integer[] orient: or){
            for(Integer[] scan: test){
                ArrayList<Integer[]> temp = new ArrayList<>();
                for(Integer[] arr: sc){
                    if(reverse)
                        temp.add(new Integer[]{arr[(2+rotation)%3]*x, arr[(1+rotation)%3]*y, arr[(0+rotation)%3]*z});
                    else
                        temp.add(new Integer[]{arr[(0+rotation)%3]*x, arr[(1+rotation)%3]*y, arr[(2+rotation)%3]*z});
                }
                int xdif = orient[0] - scan[0];
                int ydif = orient[1] - scan[1];
                int zdif = orient[2] - scan[2];
                for(Integer[] arr: temp){
                    arr[0] += xdif;
                    arr[1] += ydif;
                    arr[2] += zdif;                    
                } 
                int count = 0;
                for(Integer[] orient2: or){
                    for(Integer[] scan2: temp){
                        if(orient2[0].equals(scan2[0]) && orient2[1].equals(scan2[1]) && orient2[2].equals(scan2[2]))
                            count++;
                    }
                }
                if(count >= 12){
                    oriented.add(temp);
                    scanners.remove(sc);
                    positions.put(positions.size(), new Integer[]{xdif, ydif, zdif});
                    System.out.println(scanners.size());
                    return;
                }
            }
        }
        if(!reverse)
            match(o, sc, x, y, z, rotation, true);
    }
}