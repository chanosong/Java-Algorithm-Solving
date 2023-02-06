package Programmers.FunctionDev;
// https://school.programmers.co.kr/learn/courses/30/lessons/42586?language=java
import java.util.*;

public class FunctionDev {
    public static void main(String[] args) {

        int[] progresses = {99, 99, 99, 90, 90, 90};
        int[] speeds = {1, 1, 1, 1, 1, 1};
        int[] answer;
        int max = 0;
        int cnt = 0;
        
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> list = new LinkedList<>();

        // 초기 세팅
        for (int i = 0; i < progresses.length; i++) {
            double tmp = (100 - progresses[i]) / speeds[i];
            queue.add((int) Math.ceil(tmp));
        }

        max = queue.peek();
        while (!queue.isEmpty()) {
            int now = queue.poll();
            
            if (now - max <= 0) {
                cnt++;
            }
            else {
                list.add(cnt);
                cnt = 1;
            }
            max = Math.max(max, now);
        }
        list.add(cnt);

        answer = list.stream().mapToInt(Integer::intValue).toArray();
        System.out.println(Arrays.toString(answer));
    }
    
}
