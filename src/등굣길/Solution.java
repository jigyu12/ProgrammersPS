package 등굣길;

import java.util.Arrays;

public class Solution {

    static int[][] map;
    static int[][] dp;

    public static void main(String[] args) {
        int[][] puddles = {{2,2}};
        System.out.println(solution(4,3,puddles));
    }

    public static int solution(int m, int n, int[][] puddles) {
        dp = new int[n+2][m+2];
        map = new int[n+2][m+2];

        init(m,n,puddles);

        return countLeastPath(1,1) % 1000000007;
    }

    static int countLeastPath(int x, int y){
        if(map[x][y] == 2){
            return 1;
        }

        if(map[x][y] == 1){
            return 0;
        }

        if(dp[x][y] > 0){
            return dp[x][y];
        }

        if(map[x+1][y] != 1){
            dp[x][y] += countLeastPath(x+1,y);
        }

        if(map[x][y+1] != 1){
            dp[x][y] += countLeastPath(x,y+1);
        }

        return dp[x][y] %= 1000000007;
    }

    static void init(int m, int n, int[][] puddles) {
        for (int i = 0; i < n + 2; i++) {
            Arrays.fill(map[i], 1);
            if (i > 0 && i < n + 1) {
                for (int j = 1; j <= m; j++) {
                    map[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < puddles.length; i++) {
            map[puddles[i][1]][puddles[i][0]] = 1;
        }
        map[n][m] = 2;
    }
}
