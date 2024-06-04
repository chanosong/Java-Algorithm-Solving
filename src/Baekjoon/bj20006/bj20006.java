package Baekjoon.bj20006;

// https://www.acmicpc.net/problem/20006
// 20006 랭킹전 대기열

import java.io.*;
import java.util.*;

public class bj20006 {

    static int p;
    static int m;

    static int[] level = new int[300];
    static Player[][] player = new Player[300][300];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        p = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        // p명의 플레이어 입력
        for (int i = 0; i < p; i++) {
            input = br.readLine().split(" ");

            int lev = Integer.parseInt(input[0]);
            String nick = input[1];

            // 방 찾기
            for (int j = 0; j < 300; j++) {
                // 빈 방이 아니면서 레벨이 10 넘게 차이나는 경우 스킵
                if (level[j] != 0 && Math.abs(level[j] - lev) > 10) continue;

                // 들어갈수 있는 경우인지 확인
                if (player[j][m - 1] != null) continue;

                // 들어갈수 있는 경우 삽입
                int idx = 0;

                while (idx < m) {
                    if (player[j][idx] == null) {
                        player[j][idx] = new Player(nick, lev);

                        // 첫 플레이어인 경우 레벨 설정
                        if (idx == 0) level[j] = lev;
                        break;
                    }
                    idx++;
                }

                break;
            }
        }

        // 방의 현황 확인
        int cursor = 0;

        while (cursor < p) {

            // 방이 더이상 없는 경우
            if (level[cursor] == 0) break;

            // 방 상황 출력
            if (player[cursor][m - 1] == null) System.out.println("Waiting!");
            else System.out.println("Started!");

            PriorityQueue<Player> pq = new PriorityQueue<>();

            for (int i = 0; i < 300; i++) {
                if (player[cursor][i] == null) break;
                pq.add(player[cursor][i]);
            }

            while (!pq.isEmpty()) {
                Player p = pq.poll();

                System.out.println(p.level + " " + p.nickname);
            }

            cursor++;
        }
    }
}

class Player implements Comparable<Player> {
    String nickname;
    int level;

    Player (String nickname, int level) {
        this.nickname = nickname;
        this.level = level;
    }

    @Override
    public int compareTo(Player p) {
        if (this.nickname.compareTo(p.nickname) < 0) return -1;
        return 1;
    }
}
