import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.*;

public class Day21 {
    static Long[] players = new Long[2];
    static HashMap<String, Long[]> dp = new HashMap<>();
    public static void main(String[] args) {
        try {
            File myObj = new File("input21.txt");
            Scanner myReader = new Scanner(myObj);
            players[0] = Long.parseLong(myReader.nextLine().split(" ")[4]);
            players[1] = Long.parseLong(myReader.nextLine().split(" ")[4]);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("Part 1: " + turn(new long[]{players[0], players[1]}, new long[]{0L, 0L}, 1, 0));
        Long[] res = turns(players[0], players[1], 0L, 0L);
        System.out.println("Part 2: " + (res[0] > res[1] ? res[0] : res[1])); 
    }

    static Long[] turns(long p1, long p2, long s1, long s2){   
        if(dp.containsKey(p1 + "," + p2 + "," + s1 + "," + s2))
            return dp.get(p1 + "," + p2 + "," + s1 + "," + s2);
        Long[] ans = {0L, 0L};
        if(s1 < 21 && s2 < 21){
            for(int i = 1; i < 4; i++){
                for(int j = 1; j < 4; j++){
                    for(int k = 1; k < 4; k++){
                        Long newp1 = (p1 + i + j + k -1) % 10 +1;
                        Long news1 = s1 + newp1;
                        Long[] next = turns(p2, newp1, s2, news1);
                        ans[0] += next[1];
                        ans[1] += next[0];
                    }                
                }
            }
            dp.put(p1 + "," + p2 + "," + s1 + "," + s2, ans);
            return ans;
        }
        if(s1 > s2)
            return new Long[]{1L, 0L};
        else
            return new Long[]{0L, 1L};
    }

    static Long turn(long[] p, long[] s, int dice, int rolls){
        int turn = 0;
        while(s[0] < 1000 && s[1] < 1000){
            for(int i = 0; i < 3; i++){
                p[turn] += dice;
                dice++;
                rolls++;
                dice -= dice > 100 ? 100 : 0;
            }
            p[turn] = (p[turn] -1) % 10 + 1;
            s[turn] += p[turn];
            turn = turn == 0 ? 1 : 0;
        }
        return ((s[0] < s[1] ? s[0] : s[1]) * rolls);
    }
}