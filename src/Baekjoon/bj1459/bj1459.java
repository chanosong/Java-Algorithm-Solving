package Baekjoon.bj1459;
// https://www.acmicpc.net/problem/1459
// 걷기
import java.util.Scanner;

public class bj1459 {

    static int x;
    static int y;
    static int w;
    static int s;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        x = sc.nextInt();
        y = sc.nextInt();
        w = sc.nextInt();
        s = sc.nextInt();

        // 총 걸리는 시간
        long total = 0;

        // 좌표의 합이 짝수인 경우
        boolean onlyCross = ((long) x + (long) y) % 2 == 0;

        // 한 칸씩 가는 경우가 가로지르는 것보다 저렴한 경우
        // 정직하게 이동
        if (2 * w < s) {
            total += (long) (x + y) * w;
        }
        // 가로지르는 것이 더 저렴한 경우
        else {
            // 최대로 땡기고
            total += (long) Math.max(x, y) * s;

            // 가로질러서만 갈 수 없다면 후처리
            if (!onlyCross) {
                total += w - s;
            }

            // 최소치까지 가로로 갔다가 가는 경우도 비교
            total = Math.min(total, (long) Math.min(x,y) * s + (long) Math.abs(x-y) * w);
        }

        System.out.println(total);
    }
}
