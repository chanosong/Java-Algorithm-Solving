package Programmers.DiscountEvent;
// https://school.programmers.co.kr/learn/courses/30/lessons/131127
import java.util.HashMap;

public class DiscountEvent {
    public static void main(String[] args) {
        String[] want = {"banana", "apple", "rice", "pork", "pot"};
        int[] number = {3, 2, 2, 2, 1};
        String[] discount ={"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"};
        int answer = 0;
        HashMap<String, Integer> map = new HashMap<>();
        int sum = 0;

        // 총 개수 확인
        for (int n : number) sum += n;

        // 초기 세팅
        for (int i = 0; i < want.length; i++) {
            map.put(want[i], number[i]);
        }

        // sum의 길이를 갖는 슬라이딩 윈도우
        // 초기 세팅
        for (int i = 0; i < sum; i++) {
            if (!map.containsKey(discount[i])) {
                map.put(discount[i], -1);
                continue;
            }

            map.replace(discount[i], map.get(discount[i]) - 1);
        }

        // 조건 확인
        for (int i = sum; i <= discount.length; i++) {
            boolean flag = true;
            for (String n : want) {
                if (map.get(n) != 0) {
                    flag = false;
                    break;
                }
            }

            if (flag) answer++;
            if (i == discount.length) break;

            map.replace(discount[i - sum], map.get(discount[i - sum]) + 1);

            if (map.containsKey(discount[i])) {
                map.replace(discount[i], map.get(discount[i]) - 1);
            }
            else {
                map.put(discount[i], -1);
            }
        }

        System.out.println(map);
        System.out.println(answer);
    }
}
