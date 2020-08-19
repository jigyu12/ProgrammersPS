package 실패율;

import java.util.Arrays;

public class Solution {

    static class Stage implements Comparable<Stage>{
        int stageNum;
        double failRate;


        public Stage(int stageNum, double failRate) {
            this.stageNum = stageNum;
            this.failRate = failRate;
        }

        @Override
        public int compareTo(Stage o) {
            return calculateFailRate(this,o);
        }

        private int calculateFailRate(Stage stage, Stage o) {

            if(o.failRate == this.failRate) {
                return stage.stageNum - o.stageNum;
            }
            return o.failRate < this.failRate ? -1 : 1;
        }

        @Override
        public String toString() {
            return "num : " + stageNum + " failRate : " + failRate;
        }
    }

    public static int[] solution(int N, int[] stages) {
        long[] stopStageCntArr = new long[N+2];
        int[] answer = new int[N];

        for(int i = 0; i < stages.length; i++) {
            stopStageCntArr[stages[i]]++;
        }

        Stage[] stageArr = new Stage[N];

        long totalUserCnt = stages.length;
        for(int i = 0; i < N; i++) {
            double failRate = totalUserCnt > 0 ?
                    ((double)stopStageCntArr[i+1]) / totalUserCnt
                    : 0.0;
            System.out.println(failRate);
            stageArr[i] = new Stage(i+1, failRate);
            totalUserCnt -= stopStageCntArr[i+1];
        }

        Arrays.parallelSort(stageArr);

        for(int i = 0; i < N; i++) {
            System.out.println(stageArr[i].toString());
        }

        for(int i = 0; i < N; i++) {
            answer[i] = stageArr[i].stageNum;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
        int[] answer = solution(5,stages);

        for(int i = 0; i < answer.length; i++) {
            System.out.print(answer[i] + " ");
        }
        System.out.println();
    }

}
