import java.io.File; 
import java.io.FileNotFoundException;  
import java.util.*;
//Could be improved by using a heap to store the best paths so finding the min is O(logn) instead of O(n)

public class Day15 {
    static int[][] positions = new int[500][500];
    static int[][] bestPath = new int[100][100];
    static boolean[][] notVisited = new boolean[100][100];
    static int xlen = 0;
    static int ylen = 0;  
    static int p2 = 0;
    static int xpos = 0;
    static int ypos = 0;
    static int count = 260000;
    

    public static void main(String[] args) {
        try {
            File myObj = new File("input15.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {                
                String line = myReader.nextLine();
                xlen = line.length();
                for(int i = 0; i < xlen; i++){
                    positions[i][ylen] = Integer.parseInt(Character.toString(line.charAt(i)));
                    bestPath[i][ylen] = -1;
                    notVisited[i][ylen] = true;
                }
                ylen++;
            }
            myReader.close();
            } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        bestPath[0][0] = 0;
        while(!done())
          findPath(xpos, ypos);
        System.out.println("Part 1: " + bestPath[xlen-1][ylen-1]);
        
        notVisited = new boolean[xlen*5][ylen*5];
        bestPath = new int[xlen*5][ylen*5];
        for(int i = 0; i < ylen; i++){
          for(int j = 0; j < xlen; j++){
            for(int k = 0; k < 5; k++){
              for(int l = 0; l < 5; l++){
                int ind = ((positions[j][i]+k+l));
                
                positions[l*xlen+j][k*ylen+i] = (ind > 9) ? (ind+1)%10 : ind;
                bestPath[l*xlen+j][k*ylen+i] = -1;
                notVisited[l*xlen+j][k*ylen+i] = true;
              } 
            } 
          } 
        }
        xlen *= 5;
        ylen *= 5;
        bestPath[0][0] = 0;
        xpos = 0;
        ypos = 0;
        while(!done())
          findPath(xpos, ypos);
        System.out.println("Part 2: " + bestPath[xlen-1][ylen-1]);
        
    }

    private static void findPath(int x, int y){
      count--;
      if(count % 1000 == 0)
        System.out.println(count);
      int num = bestPath[x][y];
      updatePaths(x+1, y, num);
      updatePaths(x, y+1, num);
      updatePaths(x-1, y, num);
      updatePaths(x, y-1, num);
      notVisited[x][y] = false;
      findMin();
    }

    private static void updatePaths(int x, int y, int num){
      if(x >= 0 && x < xlen && y >= 0 && y < ylen){ //if the new path is shorter than the existing path (or a path doesn't exist yet) update it
        int bp = bestPath[x][y];
        bestPath[x][y] = positions[x][y] + num < bp || bp == -1 ? positions[x][y] + num : bp;
      }
    }

    private static void findMin(){
      int count = -1;
      for(int i = 0; i < ylen; i++){
        for(int j = 0; j < xlen; j++){
          if(bestPath[j][i] != -1 && notVisited[j][i] && (bestPath[j][i] < count || count == -1)){
            count = bestPath[j][i];
            xpos = j;
            ypos = i;
          }
        }
      }
    }

    private static boolean done(){
      for(int i = 0; i < ylen; i++){
        for(int j = 0; j < xlen; j++){
          if(notVisited[j][i] == true)
            return false;
        }
      }
      return true;
    }
}