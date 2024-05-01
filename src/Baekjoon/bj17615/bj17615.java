package Baekjoon.bj17615;

import java.util.*;
import java.io.*;

// https://www.acmicpc.net/problem/17615
// 17615 볼 모으기

public class bj17615 {

    static int n;
    static String[] ball;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        ball = br.readLine().split("");

        // 연속된 볼의 개수 정보 저장
        List<Integer> list = new ArrayList<>();

        int cnt = 1;
        String color = ball[0];
        
        // 순회 하면서 체킹
        for (int i = 1; i < n; i++) {
            // 이전과 같은 색깔인 경우
            if (ball[i].equals(color)) {
                cnt++;
            }
            // 이전과 다른 색깔인 경우
            else {
                list.add(cnt);
                cnt = 1;
                color = ball[i];
            }
        }

        list.add(cnt);

        // list 순회
        int odd = 0;
        int even = 0;

        for (int i = 0; i < list.size(); i++) {
            if (i % 2 == 0) {
                even += list.get(i);
            }
            else {
                odd += list.get(i);
            }
        }

        // 덩어리가 2개 이하인 경우
        if (list.size() <= 2) {
            System.out.println(0);
        }
        // 덩어리가 3개인 경우
        else if (list.size() == 3) {
            System.out.println(Math.min(Math.min(list.get(0), list.get(2)), list.get(1)));
        }
        // 덩어리가 4개 이상인 경우
        else {
            // 전체 덩어리 짝수
            if (list.size() % 2 == 0) {
                System.out.println(Math.min(odd - list.get(list.size() - 1), even - list.get(0)));
            }
            // 전체 덩어리 홀수
            else {
                System.out.println(Math.min(odd , Math.min(even - list.get(0), even - list.get(list.size() - 1))));
            }
        }
    }
}
