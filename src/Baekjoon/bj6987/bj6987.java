package Baekjoon.bj6987;

// https://www.acmicpc.net/problem/6987
// 6987 월드컵

import java.io.*;
import java.util.*;

public class bj6987 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < 4; i++) {
            int[][] arr = new int [6][3];
            StringTokenizer st = new StringTokenizer(br.readLine());

            // 결과표 저장
            for (int j = 0; j < 6; j++) {
                arr[j][0] = Integer.parseInt(st.nextToken());
                arr[j][1] = Integer.parseInt(st.nextToken());
                arr[j][2] = Integer.parseInt(st.nextToken());
            }

            // 승 무 패 PQ 생성
            List<Win> wins = new LinkedList<>();
            List<Draw> draws = new LinkedList<>();
            List<Loss> losses = new LinkedList<>();

            for (int j = 0; j < 6; j++) {
                wins.add(new Win(j, arr[j][0]));
                draws.add(new Draw(j, arr[j][1]));
                losses.add(new Loss(j, arr[j][2]));
            }

            Collections.sort(wins);
            Collections.sort(draws);
            Collections.sort(losses);

            boolean isPossible = true;

            // 승 - 패 상쇄
            for (int j = 0; j < 6; j++) {
                Win now = wins.get(j);

                for (int k = 0; k < 6; k++) {
                    Loss counter = losses.get(k);

                    // 자기 자신인 경우 스킵
                    if (counter.nation == now.nation) continue;

                    // 만약 차감할 패가 없는 경우 스킵
                    if (counter.cnt == 0) continue;

                    // 1씩 차감
                    counter.cnt--;
                    now.cnt--;
                }

                // 만약 승이 남은 경우 불가능
                if (now.cnt > 0) {
                    isPossible = false;
                    break;
                }

                if (!isPossible) break;
            }
            
            // 더이상 확인할 필요가 없는 경우
            if (!isPossible) {
                sb.append("0 ");
            }
            else {
                // 무승무까지 확인해봐야하는 경우
                for (int j = 0; j < 6; j++) {
                    Draw now = draws.get(j);
                    for (int k = 0; k < 6; k++) {
                        Draw counter = draws.get(k);

                        // 자기 자신인 경우 스킵
                        if (counter.nation == now.nation) continue;

                        // 차감할 무가 없는 경우 스킵
                        if (counter.cnt == 0) continue;

                        // 1씩 차감
                        counter.cnt--;
                        now.cnt--;
                    }

                    if (now.cnt > 0) {
                        isPossible = false;
                        break;
                    }
                }
                if (isPossible) sb.append("1 ");
                else sb.append("0 ");
            }


        }

        System.out.println(sb);
    }
}

class Loss implements Comparable<Loss> {
    int nation;
    int cnt;

    Loss (int nation, int cnt) {
        this.nation = nation;
        this.cnt = cnt;
    }

    @Override
    public int compareTo(Loss o) {
        return o.cnt - cnt;
    }
}

class Win implements Comparable<Win> {
    int nation;
    int cnt;

    Win (int nation, int cnt) {
        this.nation = nation;
        this.cnt = cnt;
    }

    @Override
    public int compareTo(Win o) {
        return o.cnt - cnt;
    }
}

class Draw implements Comparable<Draw> {
    int nation;
    int cnt;

    Draw (int nation, int cnt) {
        this.nation = nation;
        this.cnt = cnt;
    }

    @Override
    public int compareTo(Draw o) {
        return o.cnt - cnt;
    }
}
