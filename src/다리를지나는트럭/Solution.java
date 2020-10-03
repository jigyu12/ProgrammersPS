package 다리를지나는트럭;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    static class Truck{
        int weight;
        int time;

        public Truck(int weight, int time) {
            this.weight = weight;
            this.time = time;
        }
    }

    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Queue<Truck> bridge = new LinkedList<>();

        int bridgeWeights = 0;
        int idx = 0;
        while(idx < truck_weights.length) {
            if(bridge.size() > 0
                    && bridge.peek().time == answer){
                Truck truck = bridge.poll();
                bridgeWeights -= truck.weight;
            }

            if(bridgeWeights + truck_weights[idx] <= weight){
                bridgeWeights += truck_weights[idx];
                bridge.add(new Truck(truck_weights[idx], answer + bridge_length));
                idx++;
            }

            answer++;
        }

        Truck truck = null;

        while(!bridge.isEmpty()){
            truck = bridge.poll();
        }

        return truck.time + 1;
    }

    public static void main(String[] args) {
        int[] truck_weights = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        System.out.println(solution(100,100,truck_weights));
    }
}
