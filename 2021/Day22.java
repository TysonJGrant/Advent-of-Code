import java.io.File;  
import java.io.FileNotFoundException; 
import java.util.*;

public class Day22 {
    static ArrayList<Long[]> p1 = new ArrayList<>();
    static ArrayList<Long[]> p2 = new ArrayList<>();

    public static void main(String[] args) {
        try {
            File myObj = new File("input22.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String[] line = myReader.nextLine().split("[(\\sx=)(,sy=)(,z=)(..)]+");
                Long[] cube = new Long[7];
                cube[0] = (line[0].equals("on") ? 1L : 0L);
                for(int i = 1; i < 6; i+=2){
                    Long n1 = Long.parseLong(line[i]);
                    Long n2 = Long.parseLong(line[i+1]);
                    cube[i] = n1 < n2 ? n1 : n2;
                    cube[i+1] = n1 < n2 ? n2 : n1;
                }
                populateCubes(p2, cube);
                Long[] cube2 = new Long[7];
                cube2[0] = cube[0];
                for(int i = 1; i <= 6; i+=2){               
                    cube2[i] = cube[i] < -50 ? -50L : cube[i];
                    cube2[i+1] = cube[i+1] > 50 ? 50L : cube[i+1];
                }
                if(cube2[1] <= cube2[2] && cube2[3] <= cube2[4] && cube2[5] <= cube2[6])
                    populateCubes(p1, cube2);    
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }        
        System.out.println("Part 1: " + findVolume(p1));
        System.out.println("Part 2: " + findVolume(p2));
    }

    private static void populateCubes(ArrayList<Long[]> arr, Long[] cube){
        for(int i = arr.size()-1; i >= 0; i--){
            Long[] cube2 = arr.get(i);
            if((cube[2] >= cube2[1] && cube[1] <= cube2[2]) &&    //x overlap
            (cube[4] >= cube2[3] && cube[3] <= cube2[4])  &&      //y overlap
            (cube[6] >= cube2[5] && cube[5] <= cube2[6])){        //z overlap
                breakCube(cube, cube2, arr);
            }
        }  
        if(cube[0] == 1)  
            arr.add(cube);   //Add cube if no more clashes and cube is on        
    }

    //Get and sort the 4 coords for each x, y, z pos (left, right and 2 points of intersection)
    //break cube2 into smaller cubes within the coordinates leaving up to 8 columns surrounding the centre column if not overlapping with cube1
    //Break centre column into 3 sections. Delete original full cube. Add or remove new cube depending on on/off.
    private static void breakCube(Long[] cube, Long[] cube2, ArrayList<Long[]> arr){
        ArrayList<Long[]> temp = new ArrayList<>();
        Long[] x = new Long[]{cube[1], cube[2], cube2[1], cube2[2]};
        Long[] y = new Long[]{cube[3], cube[4], cube2[3], cube2[4]};
        Long[] z = new Long[]{cube[5], cube[6], cube2[5], cube2[6]};
        Arrays.sort(x);
        Arrays.sort(y);
        Arrays.sort(z);
        for(int i = 0; i < 3; i++){                 //Add 8 columns surrounding the exploded block
            for(int j = 0; j < 3; j++){
                if(cube2[1] <= x[i] && cube2[2] >= x[i+1] && (x[i] != x[i+1] || i == 1) && cube2[3] <= y[j] && cube2[4] >= y[j+1] && (y[j] != y[j+1] || j == 1) && !(i == 1 && j == 1)){
                    int x1 = i == 2 ? 1 : 0;
                    int x2 = i == 0 ? 1 : 0;
                    int y1 = j == 2 ? 1 : 0;
                    int y2 = j == 0 ? 1 : 0;
                    temp.add(new Long[]{1L, x[i]+x1, x[i+1]-x2, y[j]+y1, y[j+1]-y2, cube2[5], cube2[6]});
                }
            }   
        }
        if(cube2[5] <= z[0] && cube2[6] >= z[1] && z[0] != z[1])  //Add top and bottom of intersection
            temp.add(new Long[]{1L, x[1], x[2], y[1], y[2], z[0], z[1]-1});
        if(cube2[5] <= z[2] && cube2[6] >= z[3] && z[2] != z[3]) 
            temp.add(new Long[]{1L, x[1], x[2], y[1], y[2], z[2]+1, z[3]});

        arr.remove(cube2);
        arr.addAll(temp);
    }

    private static long findVolume(ArrayList<Long[]> arr){
        long ans = 0L;
        for(Long[] num: arr)
            ans += (num[2] - num[1]+1) * (num[4] - num[3]+1) * (num[6] - num[5]+1);
        return ans;
    }
}