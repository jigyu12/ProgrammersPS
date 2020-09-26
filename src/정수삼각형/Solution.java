package 정수삼각형;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int[][] sum = new int[triangle.length][triangle[triangle.length-1].length];
        sum[0][0] = triangle[0][0];
        for(int i = 1; i < triangle.length; i++){
            for(int j = 0; j < triangle[i].length; j++){
                if(j == 0 || j != triangle[i].length-1){
                    if(sum[i][j] < sum[i-1][j] + triangle[i][j]){
                        sum[i][j] = sum[i-1][j] + triangle[i][j];
                    }
                }
                if(j == triangle[i].length-1 || j != 0){
                    if(sum[i][j] < sum[i-1][j-1] + triangle[i][j]){
                        sum[i][j] = sum[i-1][j-1] + triangle[i][j];
                    }
                }
            }
        }

        for(int i = 0; i < sum[triangle.length-1].length; i++){
            if(answer < sum[triangle.length-1][i]){
                answer = sum[triangle.length-1][i];
            }
        }

        return answer;
    }
}
