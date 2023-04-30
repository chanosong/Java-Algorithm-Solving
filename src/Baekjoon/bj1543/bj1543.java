package Baekjoon.bj1543;
// 1543 문서 검색
// https://www.acmicpc.net/problem/1543
import java.util.Scanner;

public class bj1543 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력
        StringBuffer document = new StringBuffer(sc.nextLine());
        String word = sc.nextLine();

        int cnt = 0;

        while (true) {
            int idx = document.indexOf(word);

            // 존재하는 경우
            if (idx > -1) {
                cnt++;
                document = new StringBuffer(document.substring(idx + word.length()));
            }
            // 더이상 없는 경우
            else {
                System.out.println(cnt);
                break;
            }
        }
    }
}
