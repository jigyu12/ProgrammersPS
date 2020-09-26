package 스킬트리;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        char[] skillAr = skill.toCharArray();
        for(int i = 0; i < skill_trees.length; i++){
            char[] skill_tree = skill_trees[i].toCharArray();
            boolean[] isFind = new boolean[skillAr.length+1];
            isFind[0] = true;
            boolean isNotTree = true;
            for(int j = 0; j < skill_tree.length; j++){
                for(int k = 1; k <= skillAr.length; k++){
                    if(skill_tree[j] == skillAr[k-1]){
                        if(!isFind[k-1]){
                            isNotTree = false;
                            break;
                        }
                        isFind[k] = true;
                    }
                }
                if(!isNotTree){
                    break;
                }
            }
            if(isNotTree){
                answer++;
            }
        }


        return answer;
    }
}
