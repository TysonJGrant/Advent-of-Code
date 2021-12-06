import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.*;

public class Day4 {
    static String[] numbers;
    static ArrayList<int[][]> boards = new ArrayList<>();
    static int p1 = -1;
    static int p2 = -1;

	public static void main(String[] args) {
	   	try {
    		File myObj = new File("input4.txt");
    		Scanner myReader = new Scanner(myObj);
            numbers = myReader.nextLine().split(",");
    		while (myReader.hasNextLine()) {
                myReader.nextLine();
                int[][] board = new int[5][5];
                for(int i = 0; i < 5; i ++){
                    String[] line = myReader.nextLine().trim().split("\\s+");
                    for(int j = 0; j < 5; j ++){
                        board[j][i] = Integer.parseInt(line[j]);
                    }
                }
                boards.add(board);
      		}
      		myReader.close();
    	} catch (FileNotFoundException e) {
    		System.out.println("An error occurred.");
    		e.printStackTrace();
    	}
        
        for(int i = 0; i < numbers.length; i++){
            int current = Integer.parseInt(numbers[i]);
            for(int j = boards.size()-1; j >= 0; j--){
                markAndCheck(boards.get(j), current);
                if(p2 != -1)
                    break;
            }
            if(p2 != -1)
                break;
        }
        System.out.println("part1: " + p1);
        System.out.println("part2: " + p2);
  	}

    private static void markAndCheck(int[][] board, int val){
        for(int i = 0; i < board.length; i++){
            boolean xmatch = true;
            boolean ymatch = true;
            for(int j = 0; j < board.length; j++){
                if(board[i][j] == val){
                    board[i][j] = -1;
                }
                else if(board[i][j] > -1)
                    xmatch = false;
                if(board[j][i] == val){
                    board[j][i] = -1;
                }
                else if(board[j][i] > -1)
                    ymatch = false;
            }
            if((xmatch || ymatch) && p1 == -1)
                p1 = result(board) * val;
            if(xmatch || ymatch){
                if(boards.size() == 1)
                    p2 = result(board) * val;
                boards.remove(board);
            }
        }
    }

    private static int result(int[][] board){
        int sum = 0;
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                if(board[i][j] > -1)
                    sum += board[i][j];
            }
        }
        return sum;
    }
}
