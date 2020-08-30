package 괄호변환;

import java.util.Stack;

public class Solution {

    public static String solution(String p) {
        if(p.equals("")
                || isProperBracketString(p)){
            return p;
        }

        StringBuilder answer = new StringBuilder();

        String[] uv = separateString(p);

        if(isProperBracketString(uv[0])){
            answer.append(uv[0]);
            answer.append(solution(uv[1]));
        }
        else{
            answer.append("(");
            answer.append(solution(uv[1]));
            answer.append(")");
            answer.append(removeAndReverseString(uv[0]));
        }

        return answer.toString().trim();
    }

    private static String removeAndReverseString(String s) {
        StringBuilder reverseString = new StringBuilder();

        for(int i = 1; i < s.length() - 1; i++){
            char bracket = s.charAt(i);
            if(bracket == '('){
                reverseString.append(")");
            }
            else{
                reverseString.append("(");
            }
        }

        return reverseString.toString();
    }

    private static boolean isProperBracketString(String s) {
        Stack<Character> properBracketStack = new Stack<>();
        boolean isProper = true;

        for(char bracket : s.toCharArray()){
            if(!properBracketStack.empty()
                    && properBracketStack.peek() == '('
                    && bracket == ')'){
                properBracketStack.pop();
                continue;
            }
            properBracketStack.push(bracket);
        }

        if(properBracketStack.size() > 0){
            isProper = false;
        }

        return isProper;
    }

    private static String[] separateString(String p) {
        String[] uv = new String[2];

        Stack<Character> makeBalanceStack = new Stack<>();

        int idx = 0;
        for(char bracket : p.toCharArray()){
            if(!makeBalanceStack.empty() &&
                    makeBalanceStack.peek() != bracket){
                makeBalanceStack.pop();
                if(makeBalanceStack.isEmpty()){
                    break;
                }
                idx++;
                continue;
            }
            makeBalanceStack.push(bracket);
            idx++;
        }

        uv[0] = p.substring(0,idx+1);
        uv[1] = p.substring(idx+1);

        return uv;
    }

    public static void main(String[] args) {
        String p = ")))(((";
        System.out.println(solution(p));
    }
}
