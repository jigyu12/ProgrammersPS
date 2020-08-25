package 가사검색;

import java.util.HashMap;
import java.util.HashSet;

public class Solution {

    private static class Trie{
        private TrieNode rootNode;
        private TrieNode tailNode;

        public Trie() {
            this.rootNode = new TrieNode();
            this.tailNode = new TrieNode();
        }

        public void insertFront(String word){
            TrieNode trieNode = this.rootNode;

            for(int i = 0; i < word.length(); i++){
                trieNode = trieNode.getChildHashMap().computeIfAbsent(word.charAt(i),character -> new TrieNode());
                trieNode.postCount++;
            }

            trieNode.setLastChar(true);
        }

        public void insertBack(String word){
            TrieNode trieNode = this.tailNode;

            for(int i = word.length() -1; i >= 0; i--){
                trieNode.preCount++;
                trieNode = trieNode.getChildHashMap().computeIfAbsent(word.charAt(i),character -> new TrieNode());
            }

            trieNode.setLastChar(true);
        }

        public int findPostFixCount(TrieNode trieNode, String postFix, int idx){
            int postFixCount = 0;

            HashMap<Character,TrieNode> childMap = trieNode.getChildHashMap();

            if(childMap.containsKey(postFix.charAt(idx))){
                postFixCount += findPostFixCount(childMap.get(postFix.charAt(idx)) ,postFix,idx+1);
            }
            else if(postFix.charAt(idx) == '?'){
                return trieNode.postCount;
            }

            return postFixCount;
        }

        public int findPreFixCount(TrieNode trieNode, String preFix, int idx){
            int preFixCount = 0;

            HashMap<Character,TrieNode> childMap = trieNode.getChildHashMap();

            if(childMap.containsKey(preFix.charAt(idx))){
                preFixCount += findPreFixCount(childMap.get(preFix.charAt(idx)) ,preFix,idx-1);
            }
            else if(preFix.charAt(idx) == '?'){
                return trieNode.preCount;
            }

            return preFixCount;
        }

    }

    private static class TrieNode{

        private HashMap<Character, TrieNode> childHashMap;
        private boolean isLastChar;
        private int postCount;
        private int preCount;

        public TrieNode() {
            this.childHashMap = new HashMap<>();
        }

        public void setLastChar(boolean lastChar) {
            isLastChar = lastChar;
        }

        public HashMap<Character, TrieNode> getChildHashMap() {
            return childHashMap;
        }

    }

    public static int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        HashSet<String> wordSet = new HashSet<>();
        Trie[] trieList = new Trie[100001];

        for(String word : words){
            if(wordSet.contains(word)){
                continue;
            }
            if(trieList[word.length()] == null){
                trieList[word.length()] = new Trie();
            }
            trieList[word.length()].insertFront(word);
            trieList[word.length()].insertBack(word);
            wordSet.add(word);
        }

        int idx = 0;
        for(String query : queries){
            if(trieList[query.length()] == null){
                idx++;
                continue;
            }
            if(query.charAt(0) == '?'){
                answer[idx++] = trieList[query.length()].findPreFixCount(trieList[query.length()].tailNode,query,query.length()-1);
            }
            else{
                answer[idx++] = trieList[query.length()].findPostFixCount(trieList[query.length()].rootNode,query,0);
            }
        }

        return answer;
    }


    public static void main(String[] args) {
        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao", "frodo"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?", "?????"};

        int[] answer = solution(words,queries);

        for(int i = 0; i < answer.length; i++){
            System.out.print(answer[i] + " ");
        }
        System.out.println();
    }
}
