package 후보키;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

public class Solution {

    private static boolean[] singleCandidate;
    private static boolean[] multiCandidate;
    private static boolean[] notCandidate;
    private static ArrayList<String> candidateList;

    public static int solution(String[][] relation) {
        singleCandidate = new boolean[relation[0].length];
        multiCandidate = new boolean[relation[0].length];

        candidateList = new ArrayList<>();

//        int singleCandidateCount = findSingleCandidateKey(relation);

        findAllCandidateList(relation,0);
        notCandidate = new boolean[candidateList.size()];
        return findNotMinimalityKey();
    }

    private static int findNotMinimalityKey() {
        candidateList.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        for(int i = 0; i < candidateList.size(); i++){
            System.out.println(candidateList.get(i));
        }

        for(int i = 0; i < notCandidate.length; i++){
            if(notCandidate[i]){
                continue;
            }
            for(int j = i+1; j < notCandidate.length; j++){
                if(isContainKey(candidateList.get(i),candidateList.get(j))){
                    notCandidate[j] = true;
                }
            }
        }

        int answer = 0;
        for(int i = 0; i < notCandidate.length; i++){
            if(!notCandidate[i]){
                answer++;
            }
        }
        return answer;
    }

    private static boolean isContainKey(String s1, String s2) {
        String[] s1Arr = s1.split(" ");
        String[] s2Arr = s2.split(" ");

        int sameCount = 0;
        for(int i = 0; i < s1Arr.length; i++){
            for(int j = 0; j < s2Arr.length; j++){
                if(s2Arr[j].equals(s1Arr[i])){
                    sameCount++;
                }
            }
        }

        if(sameCount == s1Arr.length){
            return true;
        }

        return false;
    }

    private static void findAllCandidateList(String[][] relation, int start) {
        if(start >= relation[0].length){
            return ;
        }

        for(int i = start; i < relation[0].length; i++){
            multiCandidate[i] = true;
            if(isCandidate(relation)){
                StringBuilder candidate = new StringBuilder();
                for(int j = 0; j < relation[0].length; j++){
                    if(multiCandidate[j]){
                        candidate.append(j);
                        candidate.append(" ");
                    }
                }
                candidateList.add(candidate.toString().trim());
           //     System.out.println(candidate.toString().trim());
            }
            findAllCandidateList(relation,i+1);
            multiCandidate[i] = false;
        }
    }

    private static boolean isCandidate(String[][] relation) {
        boolean isMultiCandidate = true;
        HashSet<String> attributeSet = new HashSet<>();
        for(int i = 0 ; i < relation.length; i++){
            StringBuilder makeKey = new StringBuilder();
            for(int j = 0; j < relation[0].length; j++){
                if(multiCandidate[j]){
                    makeKey.append(relation[i][j]);
                    makeKey.append(" ");
                }
            }
            if(attributeSet.contains(makeKey.toString().trim())){
                isMultiCandidate = false;
                break;
            }
            attributeSet.add(makeKey.toString().trim());
        }

        return isMultiCandidate;
    }

    public static void main(String[] args) {
        String[][] relation = {
                {"b","2","a","a","b"},
                {"b","2","7","1","b"},
                {"1","0","a","a","8"},
                {"7","5","a","a","9"},
                {"3","0","a","f","9"}
        };
        System.out.println(solution(relation));
    }

}

