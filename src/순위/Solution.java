package 순위;

import java.util.*;

class Solution {

    private ArrayList<Integer>[] winAr;
    private ArrayList<Integer>[] loseAr;
    private int[][] winLoseCount;
    private boolean[] isVisited;

    public int solution(int n, int[][] results) {
        winAr = new ArrayList[n+1];
        loseAr = new ArrayList[n+1];
        winLoseCount = new int[n+1][2];
        for(int i = 1; i <= n; i++){
            winAr[i] = new ArrayList<Integer>();
            loseAr[i] = new ArrayList<Integer>();
        }

        for(int i = 0; i < results.length; i++){
            winAr[results[i][0]].add(results[i][1]);
            loseAr[results[i][1]].add(results[i][0]);
        }

        for(int i = 1; i <= n; i++){
            isVisited = new boolean[n+1];
            winLoseCount[i][0] = findWin(i) - 1;
            isVisited[i] = false;
            winLoseCount[i][1] = findLose(i) - 1;
        }

        int answer = 0;
        for(int i = 1; i <= n; i++){
            System.out.println(winLoseCount[i][0] + " " + winLoseCount[i][1]);
            if(winLoseCount[i][0] + winLoseCount[i][1] == n-1){
                answer++;
            }
        }
        return answer;
    }

    private int findWin(int parent){
        if(isVisited[parent]){
            return 0;
        }
        isVisited[parent] = true;

        int count = 0;

        for(int i = 0; i < winAr[parent].size(); i++){
            count += findWin(winAr[parent].get(i));
        }

        return count+1;
    }

    private int findLose(int parent){
        if(isVisited[parent]){
            return 0;
        }
        isVisited[parent] = true;

        int count = 0;

        for(int i = 0; i < loseAr[parent].size(); i++){
            count += findLose(loseAr[parent].get(i));
        }

        return count+1;
    }
}
