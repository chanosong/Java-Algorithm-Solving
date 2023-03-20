package Programmers.CoverUp;
// https://school.programmers.co.kr/learn/courses/30/lessons/161989
public class CoverUp {
    public static void main(String[] args) {
        int n = 8;
        int m = 4;
        int[] section = {1,3};
        int answer = section.length;

        // 직전 커서, 현재 커서
        int start = 0;
        int end = 0;

        if (m != 1) {
            answer = 1;

            while (true) {
                if (end >= section.length) break;
                // 오버한 경우 횟수 증가
                if (section[end] - section[start] + 1 > m) {
                    answer++;
                    start = end;
                }
                // 횟수 증가, 커서 증가
                end++;
            }
        }

        System.out.println(answer);
    }
}
