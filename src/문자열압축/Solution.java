package 문자열압축;

public class Solution {
    public static int solution(String s) {
        if(s.length() == 1){
            return 1;
        }

        int answer = 1001;

        int maxUnit = s.length() / 2;

        for(int unit = 1; unit <= maxUnit; unit++){
            StringBuilder compactString = new StringBuilder();
            int sameCount = 1;
            String beforeString = s.substring(0,unit);
            for(int i = unit; i < s.length(); i+=unit){
                String curString = null;
                if(i+unit >= s.length()){
                    curString = s.substring(i, s.length());
                }
                else{
                    curString = s.substring(i,i+unit);
                }
                if(beforeString.equals(curString)){
                    sameCount++;
                    continue;
                }

                if(sameCount > 1){
                   compactString.append(sameCount);
                }
                compactString.append(beforeString);
                sameCount = 1;
                beforeString = curString;
            }
            if(sameCount > 1){
                compactString.append(sameCount);
            }
            compactString.append(beforeString);
            if(compactString.length() < answer){
                answer = compactString.length();
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        String s = "a";
        System.out.println(solution(s));
    }
}
