package Baekjoon.bj16235;

import java.util.*;
import java.io.*;

// https://www.acmicpc.net/problem/16235
// 16235 나무 재테크

public class bj16235 {

    static int n;
    static int m;
    static int k;

    static Floor[][] map;
    static int[][] aMap;

    static int[] dr = {1,1,1,0,0,-1,-1,-1};
    static int[] dc = {1,0,-1,1,-1,1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new Floor[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = new Floor();
            }
        }

        aMap = new int[n][n];

        for (int i = 0; i < n; i++) {
            aMap[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            Tree tree = new Tree(c);

            map[a - 1][b - 1].addTree(tree);
        }

        // K년 간 진행
        for (int y = 0; y < k; y++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j].spring();
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j].summer();
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int cnt = map[i][j].fall();

                    for (int ii = 0; ii < 8; ii++) {
                        int nr = i + dr[ii];
                        int nc = j + dc[ii];

                        if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;
                        for (int z = 0; z < cnt; z++) map[nr][nc].addTree(new Tree(1));
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j].winter(aMap[i][j]);
                }
            }
        }
        
        // 확인

        int answer = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer += map[i][j].treeList.size();
            }
        }

        System.out.println(answer);
    }

}
class Floor {
    PriorityQueue<Tree> treeList;
    List<Integer> deadTreeList;
    int power;

    Floor () {
        treeList = new PriorityQueue<>();
        deadTreeList = new ArrayList<>();
        power = 5;
    }

    public void addTree (Tree tree) {
        treeList.add(tree);
    }

    public void spring() {
        PriorityQueue<Tree> tmp = new PriorityQueue<>();

        while (!treeList.isEmpty()) {
            Tree now = treeList.poll();

            if(now.canEat(power)) {
                power = now.getAge(power);
                tmp.add(now);
            }
            else {
                deadTreeList.add(now.age);
            }
        }

        treeList = tmp;
    }

    public void summer() {
        for (int p : deadTreeList) {
            power += p / 2;
        }
        deadTreeList.clear();
    }

    public int fall() {
        int cnt = 0;

        for (Tree t : treeList) {
            if (t.age % 5 == 0) cnt++;
        }

        return cnt;
    }

    public void winter(int power) {
        this.power += power;
    }
}


class Tree implements Comparable<Tree> {
    int age;
    boolean isAlive;

    Tree(int age) {
        this.age = age;
        isAlive = true;
    }

    public int getAge(int health) {
        age++;

        return health - age + 1;
    }

    public boolean canEat(int health) {
        return health - age >= 0;
    }

    public int compareTo(Tree o) {
        return this.age - o.age;
    }
}
