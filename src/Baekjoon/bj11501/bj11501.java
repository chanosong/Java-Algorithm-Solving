package Baekjoon.bj11501;

import java.util.Arrays;
import java.util.Scanner;

// https://www.acmicpc.net/problem/11501
public class bj11501 {
    public static void main(String[] args) {
        // 주식 사거나 원하는 만큼 갖고 있는 주식을 팔거나 아무것도 안하거나
        Scanner sc = new Scanner(System.in);
        // 테스트케이스 수 입력
        int t = sc.nextInt();

        // t번 반복
        for (int i = 0; i < t; i++) {
            // 배열 개수 입력
            int n = sc.nextInt();
            sc.nextLine();
            
            int[] price = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            long profit = 0;
            int highest = Integer.MIN_VALUE;

            // 반대로 확인
            for (int j = n - 1; j >= 0; j--) {
                // 현재까지 최고까인 경우 갱신
                if (highest < price[j]) {
                    highest = price[j];
                }
                // 만일 팔수 있다면
                else if (highest > price[j]) {
                    profit += highest - price[j];
                }
            }

            System.out.println(profit);
        }


        /*
        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            // 버퍼 초기화
            sc.nextLine();

            // array 파싱
            // int[] arr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] arr = new int[n];

            for (int j = 0; j < n; j++) {
                arr[j] = sc.nextInt();
            }

            long profit = 0L;
            int highestPrice = Integer.MIN_VALUE;

            // arr를 역순으로 진행
            for (int j = arr.length - 1; j >= 0; j--) {
                // 현재가 더 큰 경우 교체
                if (arr[j] > highestPrice) {
                    highestPrice = arr[j];
                    continue;
                }

                // 아무것도 안하고 넘기기
                if (arr[j] == highestPrice) continue;

                // 현재가 더 작은 경우
                profit += highestPrice - arr[j];
            }

            System.out.println((int) profit);
        }

         */
    }
}
