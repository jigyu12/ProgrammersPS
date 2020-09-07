package 무지의먹방라이브;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Solution {

    static class Food{
        int idx;
        int remain_times;

        public Food(int idx, int remain_times) {
            this.idx = idx;
            this.remain_times = remain_times;
        }
    }

    public static int solution(int[] food_times, long k) {
        LinkedList<Food> foodList = new LinkedList<>();

        long totalTime = 0;
        for(int i = 1 ; i <= food_times.length; i++){
            foodList.add(new Food(i,food_times[i-1]));
            totalTime += food_times[i-1];
        }

        if(k > totalTime){
            return -1;
        }

        Collections.sort(foodList, new Comparator<Food>() {
            @Override
            public int compare(Food o1, Food o2) {
                if(o1.remain_times == o2.remain_times){
                    return o1.idx - o2.idx;
                }
                return o1.remain_times - o2.remain_times;
            }
        });

        Food food = foodList.getFirst();
        Food beforeFood = new Food(0,0);
        int listSize = foodList.size();
        while(k > (food.remain_times - beforeFood.remain_times) * listSize){
            food = foodList.pollFirst();
            if(food.remain_times > beforeFood.remain_times){
                k -= (food.remain_times - beforeFood.remain_times) * listSize;
            }
            beforeFood = food;
            listSize = foodList.size();
            if(foodList.size() == 0){
                return beforeFood.idx;
            }
            food = foodList.getFirst();
        }

        Collections.sort(foodList, new Comparator<Food>() {
            @Override
            public int compare(Food o1, Food o2) {
                return o1.idx - o2.idx;
            }
        });

        long remainder = k % foodList.size();
        return foodList.get((int) remainder).idx;
    }

    public static void main(String[] args) {
        //int[] food_times = {3, 1, 2};
        int[] food_times = {2};
        long k = 1;
        System.out.println(solution(food_times, k));
    }
}
