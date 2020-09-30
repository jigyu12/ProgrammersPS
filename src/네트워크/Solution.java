package 네트워크;

import java.util.ArrayList;

public class Solution {

    private static boolean[] isVisited;
    private static ArrayList<Integer>[] list;

    public static int solution(int n, int[][] computers) {
        isVisited = new boolean[n];
        list = new ArrayList[n];

        for(int i = 0; i < n ; i++){
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < n; i++){
            for(int j = i; j < n; j++){
                if(i != j){
                    if(computers[i][j] == 1){
                        list[i].add(j);
                        list[j].add(i);
                    }
                }
            }
        }

        int answer = 0;

        for(int i = 0; i < n ; i++){
            if(!isVisited[i]){
                answer++;
                findSameNetwork(i);
            }
        }

        return answer;
    }

    private static void findSameNetwork(int start){

        isVisited[start] = true;

        for(int i = 0; i < list[start].size(); i++){
            int next = list[start].get(i);
            if(!isVisited[next]){
                findSameNetwork(next);
            }
        }
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] computers = {{1,1,0}, {1,1,0}, {0,0,1}};
        System.out.println(solution(n,computers));
    }
}
