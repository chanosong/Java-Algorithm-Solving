package Programmers.SecretMap;
// https://school.programmers.co.kr/learn/courses/30/lessons/17681
import java.util.Arrays;

public class SecretMap {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(5, new int[]{9,20,28,18,11}, new int[]{30,1,21,17,28})));
        System.out.println(Arrays.toString(solution(6, new int[]{46,33,33,22,31,50}, new int[]{27,56,19,14,14,10})));
    }

    static String[] solution (int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];

        // n번 비교
        for (int i = 0; i < n; i++) {
            StringBuffer binary1 = new StringBuffer(Integer.toString(arr1[i], 2));
            StringBuffer binary2 = new StringBuffer(Integer.toString(arr2[i], 2));

            // 길이를 n으로 맞춰준다
            while (binary1.length() < n) {
                binary1.insert(0, "0");
            }

            while (binary2.length() < n) {
                binary2.insert(0, "0");
            }

            StringBuffer row = new StringBuffer();

            // map에 적용
            for (int j = 0; j < n; j++) {
                if ((binary1.charAt(j) == '1') || (binary2.charAt(j) == '1')) {
                    row.append("#");
                    continue;
                }
                row.append(" ");
            }

            answer[i] = row.toString();
        }

        return answer;
    }
}
