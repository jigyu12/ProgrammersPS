package 숫자게임;

import java.util.Arrays;

public class Solution {

    public static int solution(int[] A, int[] B) {
        int answer = 0;
        int AIdx = 0;
        int BIdx = 0;

        Arrays.sort(A);
        Arrays.sort(B);

        int N = 5;
        String doubleStr = String.valueOf(55);
        if(doubleStr.matches("^[" + N + "]+$")){
            doubleStr.concat(String.valueOf(N));
            System.out.println("asd");
        }
        for(;BIdx < B.length; BIdx++){
            for(;AIdx < A.length && BIdx < B.length; AIdx++){
                if(A[AIdx] >= B[BIdx]){
                    break;
                }
                answer++;
                BIdx++;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] A = {};
        int[] B = {};

        System.out.println(solution(A,B));
    }
}
