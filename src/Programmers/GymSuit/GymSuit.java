package Programmers.GymSuit;
//https://school.programmers.co.kr/learn/courses/30/lessons/42862
import java.util.*;

public class GymSuit {
    public static void main(String[] args) {
        int n = 5;
        int[] lost = {2,4};
        int[] reserve = {3};
        int answer = 0;
        
        Set<Integer> Lset = new HashSet<>(Arrays.asList(Arrays.stream(lost).boxed().toArray(Integer[]::new)));
        Set<Integer> Sset = new HashSet<>(Arrays.asList(Arrays.stream(reserve).boxed().toArray(Integer[]::new)));
        List<Integer> sbr = new LinkedList<>();

        // check stolen guys and they has reserve
        for (int num : Lset) {
            if (Sset.contains(num)){
                sbr.add(num);
            }
        }

        for (int num : sbr) {
            Sset.remove(num);
            Lset.remove(num);
        }
        
        for (int i = 1; i <= n; i++) {

            // if not stolen
            if (!Lset.contains(i)) {
                answer++;
                continue;
            }

            // if he lost
            // if sub suits available former ~
            if (Sset.contains(i-1)) {
                Sset.remove(i-1);
                answer++;
                continue;
            }

            // if sub suits available latter ~
            if (Sset.contains(i+1)) {
                Sset.remove(i+1);
                answer++;
                continue;
            }

        }

        System.out.println(answer);
    }
}
