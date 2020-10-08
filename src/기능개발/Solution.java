package 기능개발;

import java.util.ArrayList;

public class Solution {

    public static int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> deployList = new ArrayList<>();
        int day = 0;
        int endCount = 1;

        for(int i = 0; i < progresses.length; i++){
            if(day * speeds[i] + progresses[i] < 100){
                if(i > 0){
                    deployList.add(endCount);
                }
                day = (100 - progresses[i]) / speeds[i];
                if((100 - progresses[i]) % speeds[i] > 0){
                    day++;
                }
                endCount = 1;
                continue;
            }
            endCount++;
        }
        deployList.add(endCount);

        int[] answer = new int[deployList.size()];
        for(int i = 0; i < answer.length; i++){
            answer[i] = deployList.get(i);
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] progresses = {93,30,55};
        int[] speeds = {1,30,5};
        int[] answer = solution(progresses,speeds);

        for(int i = 0 ; i < answer.length ; i++){
            System.out.print(answer[i] + " ");
        }
        System.out.println();
    }
}
