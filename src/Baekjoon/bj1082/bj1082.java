package Baekjoon.bj1082;
// https://www.acmicpc.net/problem/1082

import java.util.Arrays;
import java.util.Scanner;

public class bj1082 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String answer;
        StringBuffer sb = new StringBuffer();
        int n = sc.nextInt();

        // 버퍼 초기화
        sc.nextLine();

        // 배열 입력
        int[] arr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int m = sc.nextInt();

        int usedCost = 0;

        // 0만 주어진 경우 바로 처리
        if (arr.length > 1) {
            // 0을 제외하고 가장 가격이 싼
            int cheapestNoZero = Integer.MAX_VALUE;
            int cheapestNoZeroIdx = arr.length - 1;

            for (int i = arr.length - 1; i > 0; i--) {
                if (cheapestNoZero > arr[i]) {
                    cheapestNoZero = arr[i];
                    cheapestNoZeroIdx = i;
                }
            }

            // 0 포함 가장 가격이 싼
            int cheapest = arr[0];
            int cheapestIdx = 0;

            // 더 싼것이 있다면 변경
            if (cheapest >= cheapestNoZero) {
                cheapest = cheapestNoZero;
                cheapestIdx = cheapestNoZeroIdx;
            }

            // 살 수 있는 경우
            if (m >= arr[cheapestNoZeroIdx]) {
                sb.append(cheapestNoZeroIdx);
                usedCost += arr[cheapestNoZeroIdx];
            }

            // 1 ~ N까지 중 가장 저렴한 것을 사고 나서 남은 금액
            int lastCost = m - cheapestNoZero;

            // 1 ~ N까지 가장 작은 것을 사고 남은 경우
            if (m > usedCost) {
                // 0 ~ N까지 가장 작은 것을 가능한 만큼 구매
                while (true) {
                    // 구매를 못하는 경우 스킵
                    if (usedCost + arr[cheapestIdx] > m) break;
                    // 구매 가능하다면 구매
                    sb.append(cheapestIdx);
                    usedCost += arr[cheapestIdx];
                }

                // 높은 자리 수 부터 숫자 변경 시도
                for (int i = 0; i < sb.length(); i++) {
                    int limit = Character.getNumericValue(sb.charAt(i));

                    // 현재 보다 더 큰 숫자들만 비교
                    for (int j = arr.length - 1; j > limit; j--){
                        // 바꿀 수 있다면 스위칭 후 탈출
                        if (usedCost + arr[j] - arr[limit] <= m) {
                            sb.replace(i, i+1, Integer.toString(j));
                            usedCost += arr[j] - arr[limit];
                            break;
                        }
                    }

                    // 딱코딱뎀으로 쓴 경우 최적의 경우니 탈출
                    if (usedCost == m) break;
                }
            }

            if (sb.charAt(0) == '0') answer ="0";
            else {
                answer = sb.toString();
            }
        }
        else {
            answer = "0";
        }

        System.out.println(answer + " " + usedCost);
    }
}
