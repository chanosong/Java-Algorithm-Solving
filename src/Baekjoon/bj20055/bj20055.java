package Baekjoon.bj20055;

// https://www.acmicpc.net/problem/20055
// 20055 컨베이어 벨트 위의 로봇

import java.io.*;
import java.util.*;

public class bj20055 {
    static int n,k;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        // 위, 아래 벨트로 구분
        List<Belt> upper = new LinkedList<>();
        List<Belt> lower = new LinkedList<>();

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < n; i++) {
            upper.add(new Belt(input[i]));
        }
        for (int i = n; i < 2 * n; i++) {
            lower.add(new Belt(input[i]));
        }

        int time = 0;
        int brokenCnt = 0;
        

        while (brokenCnt < k) {
            // 단계 증가
            time++;

            // 회전
            Belt toLower = upper.remove(upper.size() - 1);
            Belt toUpper = lower.remove(lower.size() - 1);

            // 내려가면서 로봇도 같이 내려감
            toLower.isOnRobot = false;

            upper.add(0 ,toUpper);
            lower.add(0, toLower);

            // 로봇 이동하기
            for (int i = n - 1; i >= 0; i--) {
                Belt now = upper.get(i);
                // 로봇이 없는 경우 무시
                if (!now.isOnRobot) continue;

                // 마지막이 아닌 경우 그 다음 칸으로 옮김
                if (i < n - 1) {
                    Belt next = upper.get(i + 1);
                    // 옮길 수 있는지 확인 후 이동
                    if (!next.isOnRobot && next.durability > 0) {
                        next.isOnRobot = true;
                        next.durability--;
                        now.isOnRobot = false;

                        // 다 단 경우 확인
                        if (next.durability == 0) brokenCnt++;
                    }
                }
                // 마지막인 경우 그냥 하차
                else {
                    now.isOnRobot = false;
                }
            }

            // 로봇 올리기
            if (!upper.get(0).isOnRobot && upper.get(0).durability > 0) {
                upper.get(0).isOnRobot = true;
                upper.get(0).durability--;
                if (upper.get(0).durability == 0) brokenCnt++;
            }
        }

        System.out.println(time);
    }
}

class Belt {
    int durability;
    boolean isOnRobot;

    Belt(int durability) {
        this.durability = durability;
        this.isOnRobot = false;
    }
}
