package 길찾기게임;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Solution {

    static class Node implements Comparable<Node>{
        int num;
        int x;
        int y;
        Node left;
        Node right;

        public Node(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node o) {
            if(o.y == this.y){
                return this.x - o.x;
            }
            return o.y - this.y;
        }
    }

    private static ArrayList<Integer> preOrder = new ArrayList<>();
    private static ArrayList<Integer> postOrder = new ArrayList<>();

    public static int[][] solution(int[][] nodeinfo) {
        PriorityQueue<Node> nodePriorityQueue = new PriorityQueue<>();
        for(int i = 1; i <= nodeinfo.length; i++){
            nodePriorityQueue.add(new Node(i,nodeinfo[i-1][0],nodeinfo[i-1][1]));
        }

        Node rootNode = nodePriorityQueue.poll();

        while(!nodePriorityQueue.isEmpty()){
            makeBinaryTree(rootNode,nodePriorityQueue.poll());
        }

        visitPreOrder(rootNode);
        visitPostOrder(rootNode);

        int[][] answer = new int[2][nodeinfo.length];

        for(int i = 0; i < answer[0].length; i++){
            answer[0][i] = preOrder.get(i);
            answer[1][i] = postOrder.get(i);
        }

        return answer;
    }

    private static void visitPostOrder(Node rootNode) {
        if(rootNode.left != null){
            visitPostOrder(rootNode.left);
        }

        if(rootNode.right != null){
            visitPostOrder(rootNode.right);
        }

        postOrder.add(rootNode.num);
    }

    private static void visitPreOrder(Node rootNode) {
        preOrder.add(rootNode.num);

        if(rootNode.left != null){
            visitPreOrder(rootNode.left);
        }

        if(rootNode.right != null){
            visitPreOrder(rootNode.right);
        }
    }

    private static void makeBinaryTree(Node parent, Node sibling) {
        if(sibling.x < parent.x){
            if(parent.left == null){
                parent.left = sibling;
                return ;
            }

            makeBinaryTree(parent.left,sibling);
        }
        else{
            if(parent.right == null){
                parent.right = sibling;
                return ;
            }

            makeBinaryTree(parent.right,sibling);
        }
    }

    public static void main(String[] args) {
        int[][] nodeinfo = {{5,3},{11,5},{13,3},{3,5},{6,1},{1,3}
        ,{8,6},{7,2},{2,2}};
        int[][] answer = solution(nodeinfo);

        for(int i = 0; i < answer.length; i++){
            for(int j = 0; j < answer[0].length; j++){
                System.out.print(answer[i][j] + " ");
            }
            System.out.println();
        }
    }
}
