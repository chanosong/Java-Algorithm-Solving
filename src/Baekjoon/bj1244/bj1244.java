package Baekjoon.bj1244;

// https://www.acmicpc.net/problem/1244
// 1244 스위치 켜고 끄기

import java.io.*;
import java.util.*;

public class bj1244 {

    static int n, m;
    static int[] switches;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        switches = new int[n + 1];

        String[] input = br.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            switches[i + 1] = Integer.parseInt(input[i]);
        }

        m = Integer.parseInt(br.readLine());

        for (int i = 1; i <= m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            // 남학생인 경우 자기 자신의 배수 스위치 조작
            if (a == 1) {
                int idx = b;
                while (idx <= n) {
                    switches[idx] = 1 - switches[idx];
                    // b가 1인 경우는 바로 끝
//                    if (b == 1) break;
                    idx += b;
                }
            }
            else {
                // 여학생인 경우 자기 자신 스위치 조작 후,
                switches[b] = 1 - switches[b];
                // 자기 양옆 같은 수일 시 조작
                int tmp = 1;
                // 벗어나지 않는 경우 반복
                while (b + tmp <= n && b - tmp >= 1) {
                    // 좌우 같은 경우에는 진행
                    if (switches[b + tmp ] == switches[b - tmp]) {
                        switches[b + tmp] = 1 - switches[b + tmp];
                        switches[b - tmp] = 1 - switches[b - tmp];
                        tmp++;
                    }
                    // 좌우가 다른 경우 종료
                    else break;
                }
            }
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= n; i++) {
            if (i % 20 == 0) sb.append(switches[i] + "\n");
            else sb.append(switches[i] + " ");
        }

        System.out.println(sb);
    }
}
