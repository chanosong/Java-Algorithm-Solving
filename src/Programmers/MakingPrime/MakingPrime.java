package Programmers.MakingPrime;

import java.util.HashMap;
import java.util.Map;

// https://school.programmers.co.kr/learn/courses/30/lessons/12977
public class MakingPrime {

    // 기존 저장
    static Map<Integer, Boolean> map = new HashMap<>();
    static int answer;
    public static void main(String[] args) {
        System.out.println(solution(new int[]{1,2,3,4}));
        System.out.println(solution(new int[]{1,2,7,6,4}));
    }

    static int solution(int[] nums) {

        answer = 0;

        combination(nums, 0, 3, 0);

        return answer;
    }

    static void combination(int[] nums, int start, int r, int sum) {
        // 모두 선택한 경우 계산
        if (r == 0) {
            if (isPrime(sum)) answer += 1;
            return;
        }

        // 아닌 경우 계속 진행
        for (int i = start; i < nums.length; i++) {
            sum += nums[i];
            combination(nums, i + 1, r - 1, sum);
            sum -= nums[i];
        }
    }

    static boolean isPrime(int n) {
        // 이미 존재한다면
        if (map.containsKey(n)) return map.get(n);

        // 존재가 없다고 한다면 계산 진행
        boolean isPrime = true;
        int limit = n / 2;

        for (int i = 2; i < limit + 1; i++) {
            if (n % i == 0) {
                isPrime = false;
                break;
            }
        }

        map.put(n, isPrime);

        return isPrime;
    }
}
