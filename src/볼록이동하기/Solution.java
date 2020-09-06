package 볼록이동하기;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    static class Robot{
        int x1;
        int y1;
        int x2;
        int y2;
        int dir;
        int count;

        public Robot(int x1, int y1, int x2, int y2, int dir, int count) {

            if (x1 == x2) {
                if (y1 > y2) {
                    int y = y2;
                    y2 = y1;
                    y1 = y;

                    int x = x2;
                    x2 = x1;
                    x1 = x;
                }
            } else if (x1 > x2) {

                int y = y2;
                y2 = y1;
                y1 = y;

                int x = x2;
                x2 = x1;
                x1 = x;
            }
            
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.dir = dir;
            this.count = count;
        }
    }

    private static int[][] map;
    private static boolean[][][] isVisited;
    private static int leastTime;

    private static final int HORIZONTAL = 0;
    private static final int VERTICAL = 1;
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};

    public static int solution(int[][] board) {
        map = new int[board.length+2][board[0].length+2];
        isVisited = new boolean[board.length+2][board[0].length+2][2];
        leastTime = 10000000;
        initMap(board);

        Queue<Robot> robotQueue = new LinkedList<>();
        robotQueue.add(new Robot(1,1,1,2,HORIZONTAL, 0));

        while (!robotQueue.isEmpty()){
            Robot robot = robotQueue.poll();
            findLeastTime(robot, robotQueue);
        }

        return leastTime;
    }

    private static void findLeastTime(Robot robot, Queue<Robot> robotQueue) {

        if((robot.x1 == map.length - 2 && robot.y1 == map[0].length -2)
                || (robot.x2 == map.length - 2 && robot.y2 == map[0].length -2)){
            if(leastTime > robot.count){
                leastTime = robot.count;
            }
            return ;
        }

        if(robot.count >= leastTime){
            return ;
        }

        isVisited[robot.x1][robot.y1][robot.dir] = true;
        isVisited[robot.x2][robot.y2][robot.dir] = true;

        switch(robot.dir){
            case HORIZONTAL:
                for(int i = 0; i < 4; i++){
                    int nx1 = robot.x1 + dx[i];
                    int ny1 = robot.y1 + dy[i];
                    int nx2 = robot.x2 + dx[i];
                    int ny2 = robot.y2 + dy[i];

                    if(map[nx1][ny1] == 0 && map[nx2][ny2] == 0){
                        if(i == 2){
                            int lowY = ny1 > ny2 ? ny2 : ny1;
                            if(!isVisited[nx1][lowY][robot.dir]){
                                robotQueue.add(new Robot(nx1,ny1,nx2,ny2,HORIZONTAL,robot.count+1));
                            }
                        }
                        else if(i == 3){
                            int highY = ny1 < ny2 ? ny2 : ny1;
                            if(!isVisited[nx1][highY][robot.dir]){
                                robotQueue.add(new Robot(nx1,ny1,nx2,ny2,HORIZONTAL,robot.count+1));
                            }
                        }
                        else if(i == 0){
                            if(!isVisited[nx1][ny1][robot.dir] && !isVisited[nx2][ny2][robot.dir]){
                                isVisited[nx1][ny1][robot.dir] = true;
                                isVisited[nx2][ny2][robot.dir] = true;
                                robotQueue.add(new Robot(nx1,ny1,nx2,ny2,HORIZONTAL,robot.count+1));
                                robotQueue.add(new Robot(robot.x1,robot.y1,robot.x2-1,robot.y2-1,VERTICAL,robot.count+1));
                                robotQueue.add(new Robot(robot.x1-1,robot.y1+1,robot.x2,robot.y2,VERTICAL,robot.count+1));
                            }
                        }
                        else if(i == 1){
                            if(!isVisited[nx1][ny1][robot.dir] && !isVisited[nx2][ny2][robot.dir]){
                                isVisited[nx1][ny1][robot.dir] = true;
                                isVisited[nx2][ny2][robot.dir] = true;
                                robotQueue.add(new Robot(nx1,ny1,nx2,ny2,HORIZONTAL,robot.count+1));
                                robotQueue.add(new Robot(robot.x1,robot.y1,robot.x2+1,robot.y2-1,VERTICAL,robot.count+1));
                                robotQueue.add(new Robot(robot.x1+1,robot.y1+1,robot.x2,robot.y2,VERTICAL,robot.count+1));
                            }
                        }
                    }
                }
                break;

            case VERTICAL:
                for(int i = 0; i < 4; i++){
                    int nx1 = robot.x1 + dx[i];
                    int ny1 = robot.y1 + dy[i];
                    int nx2 = robot.x2 + dx[i];
                    int ny2 = robot.y2 + dy[i];

                    if(map[nx1][ny1] == 0 && map[nx2][ny2] == 0){
                        if(i == 0){
                            int lowX = nx1 > nx2 ? nx2 : nx1;
                            if(!isVisited[lowX][ny1][robot.dir]){
                                robotQueue.add(new Robot(nx1,ny1,nx2,ny2,VERTICAL,robot.count+1));
                            }
                        }
                        else if(i == 1){
                            int highX = nx1 < nx2 ? nx2 : nx1;
                            if(!isVisited[highX][ny1][robot.dir]){
                                robotQueue.add(new Robot(nx1,ny1,nx2,ny2,VERTICAL,robot.count+1));
                            }
                        }
                        else if(i == 2){
                            if(!isVisited[nx1][ny1][robot.dir] && !isVisited[nx2][ny2][robot.dir]){
                                isVisited[nx1][ny1][robot.dir] = true;
                                isVisited[nx2][ny2][robot.dir] = true;
                                robotQueue.add(new Robot(nx1,ny1,nx2,ny2,VERTICAL,robot.count+1));
                                robotQueue.add(new Robot(robot.x1,robot.y1,robot.x2-1,robot.y2-1,HORIZONTAL,robot.count+1));
                                robotQueue.add(new Robot(robot.x1+1,robot.y1-1,robot.x2,robot.y2,HORIZONTAL,robot.count+1));
                            }
                        }
                        else if(i == 3){
                            if(!isVisited[nx1][ny1][robot.dir] && !isVisited[nx2][ny2][robot.dir]){
                                isVisited[nx1][ny1][robot.dir] = true;
                                isVisited[nx2][ny2][robot.dir] = true;
                                robotQueue.add(new Robot(nx1,ny1,nx2,ny2,VERTICAL,robot.count+1));
                                robotQueue.add(new Robot(robot.x1,robot.y1,robot.x2-1,robot.y2+1,HORIZONTAL,robot.count+1));
                                robotQueue.add(new Robot(robot.x1+1,robot.y1+1,robot.x2,robot.y2,HORIZONTAL,robot.count+1));
                            }
                        }
                    }
                }
                break;

        }
    }

    private static void initMap(int[][] board) {

        for(int i = 0; i < map.length; i++){
            Arrays.fill(map[i],1);
        }

        for(int i = 1; i <= board.length; i++){
            for(int j = 1; j <= board[0].length; j++){
                map[i][j] = board[i-1][j-1];
            }
        }
    }

    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 1, 1},{0, 0, 0, 1, 0},{0, 1, 0, 1, 1},{1, 1, 0, 0, 1},{0, 0, 0, 0, 0}};
        System.out.println(solution(board));
    }
}
